package application.Model.Entities;

public abstract class Personne {
	private int Id ;
	private String Nom ;
	private String Prenom ;
	private String Adresse ;
	private String DN ;
	private String Email ;
	private String NumeroTel ;
	private String MDP ;
	
	public String getAdresse() {
		return Adresse;
	}
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
	public String getDN() {
		return DN;
	}
	public void setDN(String dN) {
		DN = dN;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public String getNumeroTel() {
		return NumeroTel;
	}
	public void setNumeroTel(String numeroTel) {
		NumeroTel = numeroTel;
	}
	
	public Personne() {
	}

	public String getmdp() {
		return MDP;
	}
	public void setmdp(String mdp) {
		MDP = mdp;
	}
	
	public Personne(int id, String nom, String prenom, String adresse, String dN, String email, String numeroTel, String mdp) {
		Id = id;
		Nom = nom;
		Prenom = prenom;
		Adresse = adresse;
		DN = dN;
		Email = email;
		NumeroTel = numeroTel;
		MDP=mdp ;
	}
	
	public Personne(String nom, String prenom, String adresse, String dN, String email, String numeroTel, String mdp) {
		Nom = nom;
		Prenom = prenom;
		Adresse = adresse;
		DN = dN;
		Email = email;
		NumeroTel = numeroTel;
		MDP=mdp ;
	}

	
	public abstract void ModifInfo (String nom, String prenom, String adresse, String dN, String email, String numeroTel);
	public abstract void DemandeCarnetCheque () ;

	
}
