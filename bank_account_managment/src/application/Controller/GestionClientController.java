package application.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import application.Model.DAO.ClientDAO;
import application.Model.Entities.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class GestionClientController {
	@FXML
	private TableView<Client> tyClient ;
	@FXML
	private TableColumn <Client, Integer> colID;
	@FXML
	private TableColumn <Client, String > colNumClient ;
	@FXML
	private TableColumn <Client, String> colNom;
	@FXML
	private TableColumn <Client, String> colPrenom;
	@FXML
	private TableColumn <Client,String> colAdress;
	@FXML
	private TableColumn <Client, String> colDN;
	@FXML
	private TableColumn <Client, String> colEmail;
	@FXML
	private TableColumn <Client, String> colNumTel;
	@FXML
	private TableColumn <Client, String> colMADP;
	@FXML
    private URL location;
    @FXML
    private ResourceBundle resources;
    
    ///______

    @FXML
    private TextField NumClt;

    @FXML
    private TextField Nom;
    
    @FXML
    private TextField Prenom;

    @FXML
    private TextField NumTel;
    
    @FXML
    private TextField Adress;

    @FXML
    private TextField DN;

    @FXML
    private TextField Email;
    
    @FXML
    private PasswordField Pass;

    @FXML
    private PasswordField Pass2;
    @FXML
    private Label err;
    
	private Stage stage ;
	private Scene scene ;
	private Parent root ;
   
	
    public void initialize() {
		showClient();
	}
    
    
	public void showClient(){
		try {
			ClientDAO Cl_DAO = new ClientDAO ();
			ObservableList<Client> list = Cl_DAO.getClientlist() ;
			System.out.println(list);
			if (! list.isEmpty()) {
				colID.setCellValueFactory(new PropertyValueFactory <Client,Integer > ("id" ));
				colNumClient.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumClient()) ) ;
			    colNom.setCellValueFactory(new PropertyValueFactory <Client,String> ("nom" ));
				colPrenom.setCellValueFactory(new PropertyValueFactory <Client,String> ("prenom" ));
				colAdress.setCellValueFactory(new PropertyValueFactory <Client,String> ("Adresse" ));
				colDN.setCellValueFactory(new PropertyValueFactory <Client,String> ("DN" ));
				colEmail.setCellValueFactory(new PropertyValueFactory <Client,String> ("Email" ));
				colNumTel.setCellValueFactory(new PropertyValueFactory <Client,String> ("NumeroTel" ));
				colMADP.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getmdp() ) ) ;
				tyClient.setItems(list);
			}
		}catch (Exception e) {
			System.out.println("Erreur :"+ e.getMessage());
		}
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void Save(ActionEvent event) {
		if (Pass.getText().toString().equals( Pass2.getText().toString()) ) {
			//save 
			ClientDAO Cl_DAO = new ClientDAO ();	
			Client cl = new Client(Nom.getText().toString(), Prenom.getText().toString(),Adress.getText().toString(),DN.getText().toString(),Email.getText().toString(),NumTel.getText().toString(),NumClt.getText().toString(),Pass.getText().toString()    );
			Cl_DAO.save(cl);
			showClient();
			Nom.setText("");	
			Prenom.setText("");	
			Adress.setText("");	
			DN.setText("");	
			Email.setText("");	
			NumTel.setText("");	
			NumClt.setText("");	
			Pass.setText("");	
			Pass2.setText("");
			err.setText("Client bien enregistrer ");
		}else {
			err.setText("Mot de passe incorrect");
		}
		
	}
	@FXML
	public void Update(ActionEvent event) {
		if (Pass.getText().toString().equals( Pass2.getText().toString()) ) {
			ClientDAO Cl_DAO = new ClientDAO ();
			if (Cl_DAO.findById(NumClt.getText().toString())!=null  ) {
				Client cl = new Client(Nom.getText().toString(), Prenom.getText().toString(),Adress.getText().toString(),DN.getText().toString(),Email.getText().toString(),NumTel.getText().toString(),NumClt.getText().toString(),Pass.getText().toString()    );		
				Cl_DAO.update(cl);
				showClient();
				Nom.setText("");	
				Prenom.setText("");	
				Adress.setText("");	
				DN.setText("");	
				Email.setText("");	
				NumTel.setText("");	
				NumClt.setText("");	
				Pass.setText("");	
				Pass2.setText("");
				err.setText("Client bien enregistrer ");
			}else {err.setText("Numero Client n'est liée a un Client dans la base ");}
		}else {err.setText("Mot de passe incorrect");}
	}
		
	// Event Listener on Button.onAction
	@FXML
	public void Delete(ActionEvent event) {
		ClientDAO Cl_DAO = new ClientDAO ();
		if (Cl_DAO.findById(NumClt.getText().toString())!=null  ) {
			Cl_DAO.delete(NumClt.getText().toString());
			showClient();
		}else {
			err.setText("Numero Client n'est liée a un Client dans la base ");
		}
	}

    @FXML
    void Retour(ActionEvent event) {
    	try {
		//afficher interface dashboard client
		Parent root = FXMLLoader.load(getClass().getResource("/application/Vue/DashboardClient.fxml") )  ;
	    stage =(Stage)((Node)event .getSource()).getScene().getWindow() ;
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show() ;
    	}catch (Exception e) {
			System.out.println("Erreur :"+ e.getMessage());
		}
    }
    
}
