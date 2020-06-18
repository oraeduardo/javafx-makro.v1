package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	/*
	 * @Override public void start(Stage primaryStage) { try { BorderPane root = new
	 * BorderPane(); Scene scene = new Scene(root,400,400);
	 * scene.getStylesheets().add(getClass().getResource("application.css").
	 * toExternalForm()); primaryStage.setScene(scene); primaryStage.show(); }
	 * catch(Exception e) { e.printStackTrace(); } }
	 */
	
	private static Scene mainScene;
	private static Stage mainStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			//Parent parent = loader.load();
			//Scene mainScene = new Scene(parent);
			
			//Trocado o Parent pelo ScrollPane conforme MainView do sceneBuilder 
			ScrollPane scrollPane = loader.load();
			
			//Deixar o scrollPane com a mesma dimenssão da cena.
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			mainScene = new Scene(scrollPane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sample JavaFX application");
			primaryStage.show();
			setMainStage(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static Stage getMainStage() {
		return mainStage;
	}
	
	public void setMainStage(Stage mainStage) {
		Main.mainStage = mainStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}