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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.entities.Login;
import model.services.LoginService;

public class LoginController implements Initializable {
	
	private Login login;
	
	private LoginService loginService;
	
	private LoggedListener loggedListeners;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private PasswordField pwdPassword;
	
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
	
	public void setUserService(LoginService loginService) {
		this.loginService = loginService;
	}

	public Boolean logged() {
		if (loginService == null) {
			throw new IllegalStateException("LoginService was null!");
		}
		
        login = getFormData();
	    return loginService.findLogin(login); 
	}
	
	public Login getFormData() {
		Login login = new Login();
		
		login.setName(txtName.getText());
	    login.setPassword(pwdPassword.getText());
	    
	    return login;
	}
	
	public void subscribeLoggedListener(LoggedListener listener) {
		loggedListeners = listener;
	}
	
	private void notifyLoggedListeners(Boolean x) {	
		LoggedListener listener = loggedListeners;
		listener.onLogged(x);
	}
}
