package application.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardClientController {

    @FXML
    private Button NBCompte;

    @FXML
    private Button NBOp;

    @FXML
    private Button NBRec;
    
	private Stage stage ;
	private Scene scene ;
	private Parent root ;

    @FXML
    void Deconnection(ActionEvent event) {
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
    
    
    @FXML
    void AddReclamation(ActionEvent event) {

    }

    @FXML
    void ConsulteCompte(ActionEvent event) {

    }

    @FXML
    void ConsulterOperation(ActionEvent event) {

    }

    @FXML
    void DemandeCarnet(ActionEvent event) {

    }

    @FXML
    void ModifierCompte(ActionEvent event) {

    }

}