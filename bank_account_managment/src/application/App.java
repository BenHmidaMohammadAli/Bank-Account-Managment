package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			*/ 			
			
			//URL UrlLoad ="C:\\Users\\IT-USER\\OneDrive\\Bureau\\TIC1-F-S2\\Programmation JAVA\\Projet\\WorkSpaceProjet\\Gestion_Comptes\\src\\application\\Vue\\Authentification.fxml" ;
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/Vue/Authentification.fxml") )  ;
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("Authentification");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();			
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}

}
