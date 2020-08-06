package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.entities.User;
import model.exceptions.ValidationException;
import model.services.UserService;

public class UserFormController implements Initializable {

	private User entity;

	private UserService service;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtName;

	@FXML
	private Label labelErrorName;
	
	@FXML
	private PasswordField pwdPassword;

	@FXML
	private Label labelErrorPassword;

	@FXML
	private PasswordField pwdPasswordConfirmation;

	@FXML
	private Label labelErrorPasswordConfirmation;
	
	@FXML
	private CheckBox chkAdmin;

	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;

	public void setUser(User entity) {
		this.entity = entity;
	}

	public void setUserService(UserService service) {
		this.service = service;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			if (service.findByName(entity.getName().trim()) == null ) {
				service.saveOrUpdate(entity);
				notifyDataChangeListeners();
				Utils.currentStage(event).close();
			} else {
				Alerts.showAlert("IOException", null, "Login already exists", AlertType.ERROR);
			}
		} 
		catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		}
		catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChange();
		}
	}

	private User getFormData() {
		User obj = new User();

		ValidationException exception = new ValidationException("Validation error");	

		obj.setId(Utils.tryParseToInt(txtId.getText()));

		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
			exception.addErrors("name", "Field can't be empty");
		}
		obj.setName(txtName.getText());

		if (pwdPassword.getText() == null || pwdPassword.getText().trim().equals("")) {
			exception.addErrors("password", "Field can't be empty");
		}
		obj.setPassword(pwdPassword.getText());

		if (pwdPasswordConfirmation.getText() == null || pwdPasswordConfirmation.getText().trim().equals("")) {
			exception.addErrors("passwordconfirmation", "Field can't be empty");
		}
		
		if (! pwdPassword.getText().equals(pwdPasswordConfirmation.getText())) {
			exception.addErrors("passwordconfirmation", "Password does not match");
		}

		if (chkAdmin.isSelected()) {
			obj.setAdmin("S");
		} else {
			obj.setAdmin("N");
		}
		
		if (exception.getErrors().size() > 0) {
			throw exception;
		}

		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializableNodes();
	}

	public void initializableNodes() {
		Constraints.setTextFieldDouble(txtId);
		Constraints.setTextFieldMaxLength(txtName, 10);
		Constraints.setTextFieldMaxLength(pwdPassword, 6);
		Constraints.setTextFieldMaxLength(pwdPasswordConfirmation, 6);
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
		pwdPassword.setText(entity.getPassword());
		pwdPasswordConfirmation.setText(entity.getPassword());
		if (entity.getAdmin() == null) {
			chkAdmin.setSelected(false);
		} else {
			if (entity.getAdmin().equals("S")) {
				chkAdmin.setSelected(true);
			} else {
				chkAdmin.setSelected(false);
			}
		}
	}
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		labelErrorName.setText("");
		labelErrorPassword.setText("");
		labelErrorPasswordConfirmation.setText("");

		if (fields.contains("name")) {
			labelErrorName.setText(errors.get("name"));
		}

		if (fields.contains("password")) {
			labelErrorPassword.setText(errors.get("password"));
		}
		
		if (fields.contains("passwordconfirmation")) {
			labelErrorPasswordConfirmation.setText(errors.get("passwordconfirmation"));
		}
	}

}
