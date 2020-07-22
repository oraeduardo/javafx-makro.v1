package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.listeners.LoggedListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.User;
import model.services.UserService;

public class UserController implements Initializable {
	
	private User user;
	
	private UserService userService;
	
	private LoggedListener loggedListeners;
	
	@FXML
	private TextField txtUser;
	
	@FXML
	private TextField pwdPassword;
	
	@FXML
	private Button btOk;

	@FXML
	private Button btCancel;
	
	@FXML
	public void onBtOkAction(ActionEvent event) {
		if (logged()) {
			Alerts.showAlert("IOInformation", "Load view", "Logado", AlertType.INFORMATION);
		} else {
			Alerts.showAlert("IOException", "Error loading view", "Não Logado", AlertType.ERROR);
		}
		notifyLoggedListeners(logged());
	}
	
	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Boolean logged() {
		if (userService == null) {
			throw new IllegalStateException("UserService was null!");
		}
		
        user = getFormData();
	    return userService.findUser(user); 
	}
	
	public User getFormData() {
		User user = new User();
		
		user.setUser(txtUser.getText());
	    user.setPassword(pwdPassword.getText());
	    
	    return user;
	}
	
	public void subscribeLoggedListener(LoggedListener listener) {
		loggedListeners = listener;
	}
	
	private void notifyLoggedListeners(Boolean x) {	
		LoggedListener listener = loggedListeners;
		listener.onLogged(x);
	}

}
