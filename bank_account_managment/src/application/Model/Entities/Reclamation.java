package application.Model.Entities;
import java.util.ArrayList ;


public class Reclamation { 
	private Boolean Etat ;
	private String NumeroREclamation ;
	private String TypeReclamation ;
	private Client Client ;
	private Employe Employe ;

	public Reclamation(String numeroREclamation,String typeReclamation,  Client client,Employe employe, Boolean etat) {
		Etat = etat;
		NumeroREclamation = numeroREclamation;
		TypeReclamation = typeReclamation;
		Client =client ;
		Employe =employe ;
		
	}	
	
	public Client getClient() {
		return Client;
	}
	public void setClient(Client client) {
		Client = client;
	}
	
	public Boolean getEtat() {
		return Etat;
	}
	public void setEtat(Boolean etat) {
		Etat = etat;
	}

	public String getNumeroREclamation() {
		return NumeroREclamation;
	}
	public void setNumeroREclamation(String numeroREclamation) {
		NumeroREclamation = numeroREclamation;
	}
	public String getTypeReclamation() {
		return TypeReclamation;
	}
	public void setTypeReclamation(String typeReclamation) {
		TypeReclamation = typeReclamation;
	}
	
	public Employe getEmploye() {
		return Employe;
	}

	public void setEmploye(Employe employe) {
		Employe = employe;
	}

	public void ModifierEtat (Boolean etat) {
		Etat = etat;
	}

}
