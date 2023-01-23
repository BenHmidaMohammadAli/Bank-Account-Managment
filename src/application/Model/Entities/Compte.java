package application.Model.Entities;

import java.util.ArrayList;

public class Compte {
	private String NumeroCompt ;
	private String IdSiege ;
	private float Montant ;
	private Client Client ;
	ArrayList<Operation> Operation ;
	
	public Compte(String numeroCompt,String idSiege, float montant,Client client) {
		NumeroCompt = numeroCompt;
		IdSiege = idSiege;
		Montant = montant;
		Client = client ;
		ArrayList<Operation> Operation= new ArrayList<Operation>();
	}
	
	public ArrayList<Operation> getOperation() {
		return Operation;
	}

	public void setOperation(ArrayList<Operation> operation) {
		Operation = operation;
	}

	public Client getClient() {
		return Client;
	}
	public void setClient(Client client) {
		this.Client = client;
	}
		
	public String getIdSiege() {
		return IdSiege;
	}
	public void setIdSiege(String idSiege) {
		IdSiege = idSiege;
	}
	public float getMontant() {
		return Montant;
	}
	public void setMontant(float montant) {
		Montant = montant;
	}
	public String getNumeroCompt() {
		return NumeroCompt;
	}
	public void setNumeroCompt(String numeroCompt) {
		NumeroCompt = numeroCompt;
	}
		
	public String Consulter () {
		return NumeroCompt+" "+Montant ;
	}
	
	public void Retrait (float montant) {
		//condition
		if (Montant- montant<0) {
			System.out.println("Vous ne pouvez pas faire des retraits !!");
		}
		Montant = Montant- montant ;
	}
	
	public void Versement (float montant) {		
		Montant = Montant+ montant ;
	}
	
	public void Transfer ( Compte compt ,float montant) {
		this.Retrait(montant);
		compt.Versement(montant);		
	}
	
	
}
