package application.Model.Entities;

import java.util.ArrayList;

public class Client extends Personne {
	private String NumClient ;
	ArrayList<Compte> Compte ;
	ArrayList<Operation> Operation ;
	ArrayList<Reclamation> Reclamation ;
	 
	public Client(int id ,String nom, String prenom, String adresse, String dN, String email, String numeroTel, String numClient,String mdp) {
		super(id, nom, prenom, adresse, dN, email, numeroTel, mdp);
		NumClient =numClient ;	
		ArrayList<Compte> Compte= new ArrayList<Compte>();
		ArrayList<Operation> Operation= new ArrayList<Operation>();
		ArrayList<Reclamation> Reclamation= new ArrayList<Reclamation>();
	}
	
	public Client(String nom, String prenom, String adresse, String dN, String email, String numeroTel, String numClient,String mdp) {
		super(nom, prenom, adresse, dN, email, numeroTel, mdp);
		NumClient =numClient ;
		ArrayList<Compte> Compte= new ArrayList<Compte>();
		ArrayList<Operation> Operation= new ArrayList<Operation>();
		ArrayList<Reclamation> Reclamation= new ArrayList<Reclamation>();
	}

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Compte> getCompte() {
		return Compte;
	}

	public void setCompte(ArrayList<Compte> compte) {
		Compte = compte;
	}
		
	public ArrayList<Operation> getOperation() {
		return Operation;
	}

	public void setOperation(ArrayList<Operation> operation) {
		Operation = operation;
	}

	public ArrayList<Reclamation> getReclamation() {
		return Reclamation;
	}

	public void setReclamation(ArrayList<Reclamation> reclamation) {
		Reclamation = reclamation;
	}

	public String getNumClient() {
		return NumClient;
	}

	public void setNumClient(String numClient) {
		NumClient = numClient;
	}

	public void DemandeCarnetCheque () {
		
	}

	public void ModifInfo ( String nom, String prenom, String adresse, String dN, String email, String numeroTel) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresse(adresse);
		this.setDN(dN);
		this.setEmail(email);
		this.setNumeroTel(numeroTel) ;		
	}


	
	
	
}
