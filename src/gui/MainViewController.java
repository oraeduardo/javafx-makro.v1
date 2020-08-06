package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.listeners.LoggedListener;
import gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.LoginService;
import model.services.ParametroService;
import model.services.PrimaryKeyChangeService;
import model.services.UserService;

public class MainViewController implements Initializable, LoggedListener{

	@FXML
	private MenuItem menuLogin;

	@FXML
	private MenuItem menuItemPrimaryKeyChange;

	@FXML
	private MenuItem menuItemClose;

	@FXML
	private MenuItem menuItemUser;
	
	@FXML
	private MenuItem menuAbout;

	@FXML
	public void onMenuItemLoginAction(ActionEvent event) {
		loadView("/gui/Login.fxml", (LoginController controller) -> {
			controller.setUserService(new LoginService());
			controller.subscribeLoggedListener(this);
		});
	}

	@FXML
	public void onMenuItemPrimaryKeyChangeAction() {
		loadView("/gui/PrimaryKeyChangeList.fxml", (PrimaryKeyChangeListController controller) -> {
			controller.setPrimaryKeyChangeService(new PrimaryKeyChangeService(), new ParametroService());
			controller.loadAssociatedObject();
			controller.parameterFirstData();
			controller.onDataChange(); // controller.subscribeDataChangeListener(this);

		});
	}

	@FXML
	public void onMenuItemCloseAction(ActionEvent event) {
		// Utils.currentStage(event).close();
		Main.getMainStage().close();
	}

	@FXML
	public void onMenuItemUserAction() {
		loadView("/gui/UserList.fxml", (UserListController controller) -> {
			controller.setUserService(new UserService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		onLogged(false);
	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializinAction) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));		
			VBox newVBov = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			// Node mainAncroPane = mainVBox.getChildren().get(1);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			// mainVBox.getChildren().add(mainAncroPane);
			mainVBox.getChildren().addAll(newVBov.getChildren());

			T controller = loader.getController();
			initializinAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onLogged(Boolean x) {
		menuItemPrimaryKeyChange.setDisable(!x);
		menuItemUser.setDisable(!x);
		menuAbout.setDisable(!x);
	}
}
