package application.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node; 
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import application.Model.DAO.ClientDAO;
import application.Model.DAO.EmployeDAO;
import application.Model.Entities.Client;
import javafx.event.ActionEvent;


public class AuthentificationController {

	//application.Controller.AuthentificationController
	// Event Listener on Button.onAction
	@FXML private PasswordField mdp ;
	@FXML private TextField Login ;
	@FXML private Button cnx ;
	@FXML private Button clear ;
	
	private Stage stage ;
	private Scene scene ;
	private Parent root ;
	@FXML
    private Label OUTPUT;
	
	String x1, x2, s1,s2;
	
	@FXML
	    void On_Clear(ActionEvent event) {
		Login.setText("");	
		mdp.setText("");	
	 }
	@FXML
	   void On_connexion(ActionEvent event) {		

		try {
			x1= Login.getText().toString() ;
			x2= mdp.getText().toString() ;
			
			System.out.print(x1 +" "+ x2);
			//Client
			ClientDAO Cl_DAO = new ClientDAO ();
			if (Cl_DAO.findById(x1)!= null ) {
				String modepass1 = Cl_DAO.findById(x1).getmdp().toString() ;
				if (modepass1 != null){
					if (modepass1.equals(x2)) {
						System.out.print("Mot de passe Vrai Client");
						OUTPUT.setText("Mot de passe Vrai Client ");
						//afficher interface dashboard client
						Parent root = FXMLLoader.load(getClass().getResource("/application/Vue/DashboardClient.fxml") )  ;
					    stage =(Stage)((Node)event .getSource()).getScene().getWindow() ;
					    scene=new Scene(root);
					    stage.setScene(scene);
					    stage.show() ;
						
					}else
						System.out.print("Mot de passe Client Non Valid ");	
						OUTPUT.setText("Mot de passe Client Non Valid");
				}
			}else {
				System.out.println("Id Client Non Valide ");
				OUTPUT.setText("Id Client Non Valide ");
			}

			
			//Emploie
			EmployeDAO Emp_DAO = new EmployeDAO ();
			if (Emp_DAO.findById(x1)!= null ) {
				String modepass2 = Emp_DAO.findById(x1).getmdp() ;
				if (modepass2 != null){
					if (modepass2.equals(x2)) {
						System.out.print("Mot de passe Vrai employe");
						OUTPUT.setText("Mot de passe Vrai employe ");
						//afficher interface dashboard Employe
						Parent root = FXMLLoader.load(getClass().getResource("/application/Vue/DashboardEmploye.fxml") )  ;
					    stage =(Stage)((Node)event .getSource()).getScene().getWindow() ;
					    scene=new Scene(root);
					    stage.setScene(scene);
					    stage.show() ;	
						
					}else
						OUTPUT.setText("Mot de passe Emplyoe Non Valid  ");
						System.out.print("Mot de passe Emplyoe Non Valid ");	
				}
			}else {
				System.out.println("Id Employe Non Valide ");
				OUTPUT.setText("Id Employe Non Valid  ");
			}
			
			
		} catch (Exception e) {
			System.out.println("Erreur :"+ e.getMessage());
		}
		
		
    }

	
}
