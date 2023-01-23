package application.Model.Entities;

import java.util.ArrayList;

public class Employe extends Personne {

	private String NumeroEmploye  ;
	ArrayList<Reclamation> Reclamation ;
	
	public Employe(int id,  String numeroEmploye,String nom, String prenom, String adresse, String dN, String email, String numeroTel, String mdp ) {
		super(id, nom, prenom, adresse, dN, email, numeroTel,mdp);
		this.NumeroEmploye =numeroEmploye ;
		ArrayList<Reclamation> Reclamation= new ArrayList<Reclamation>();
	}
	
	public Employe(String nom, String prenom, String adresse, String dN, String email, String numeroTel,String numeroEmploye, String mdp) {
		super(nom, prenom, adresse, dN, email, numeroTel,mdp);
		this.NumeroEmploye =numeroEmploye ;
	}
		
	public ArrayList<Reclamation> getReclamation() {
		return Reclamation;
	}

	public void setReclamation(ArrayList<Reclamation> Reclamation) {
		this.Reclamation = Reclamation;
	}

	public String getNumeroEmploye() {
		return NumeroEmploye;
	}

	public void setNumeroEmploye(String numeroEmploye) {
		NumeroEmploye = numeroEmploye;
	}
	
	
	public void ModifInfo(String nom, String prenom, String adresse, String dN, String email, String numeroTel) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresse(adresse);
		this.setDN(dN);
		this.setEmail(email);
		this.setNumeroTel(numeroTel) ;
	}
	
	
	public void DemandeCarnetCheque() {}
	
}
