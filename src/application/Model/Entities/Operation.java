package application.Model.Entities;

public class Operation {

	private String DateOp;
	private String NumeroOperation ;
	private String TypeOperation ;
	private Compte Compte ;
	private Client Client ;

	
	public Operation(String numeroOperation, String typeOperation, String dateOp, Compte compte,Client client) {
		DateOp = dateOp ;
		NumeroOperation = numeroOperation;
		TypeOperation = typeOperation;
		Compte =compte ;
		Client = client ;
	}

	public Client getClient() {
		return Client;
	}

	public void setClient(Client client) {
		Client = client;
	}
	
	public Compte getCompte() {
		return Compte;
	}

	public void setCompte(Compte compte) {
		Compte = compte;
	}
	
	public String getDateOp() {
		return DateOp;
	}

	public void setDateOp(String dateOp) {
		DateOp = dateOp;
	}

	public String getNumeroOperation() {
		return NumeroOperation;
	}

	public void setNumeroOperation(String numeroOperation) {
		NumeroOperation = numeroOperation;
	}

	public String getTypeOperation() {
		return TypeOperation;
	}

	public void setTypeOperation(String typeOperation) {
		TypeOperation = typeOperation;
	}


	public String ConsulterOperation (String NumeroOp ,String DateDebut , String DateFin) {
		return this.NumeroOperation ;
	}
	

	
}
