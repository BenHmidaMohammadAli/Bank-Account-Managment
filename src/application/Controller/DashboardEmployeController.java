package application.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;


public class DashboardEmployeController {

	private Stage stage ;
	private Scene scene ;
	private Parent root ;
	
	@FXML
    void Gest_Client(ActionEvent event)  {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Vue/GestionClient.fxml") )  ;
		    stage =(Stage)((Node)event .getSource()).getScene().getWindow() ;
		    scene=new Scene(root);
		    stage.setScene(scene);
		    stage.show() ;			
		}catch (Exception e) {
			System.out.println("Erreur :"+ e.getMessage());
		}
    }

    @FXML
    void Gest_Compte(ActionEvent event) {

    }

    @FXML
    void Gest_Employe(ActionEvent event) {

    }

    @FXML
    void Gest_Retrait(ActionEvent event) {

    }

    @FXML
    void Transfert(ActionEvent event) {

    }

    @FXML
    void Versements(ActionEvent event) {

    }

    @FXML
    void deconnection(ActionEvent event) {
    	try {
		//afficher interface dashboard client
		Parent root = FXMLLoader.load(getClass().getResource("/application/Vue/Authentification.fxml") )  ;
	    stage =(Stage)((Node)event .getSource()).getScene().getWindow() ;
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show() ;
    	}catch (Exception e) {
			System.out.println("Erreur :"+ e.getMessage());
		}
    }
    
    
}
