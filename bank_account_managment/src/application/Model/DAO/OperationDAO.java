package application.Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Model.Entities.Client;
import application.Model.Entities.Compte;
import application.Model.Entities.Employe;
import application.Model.Entities.Operation ;

public class OperationDAO {
	
	//FindBy ID
	public Operation findById (String code) {
	Connection	cnx = Sconnection.getInstance() ;
	Operation op=null;
	Client clt =null;
	Compte cmpt=null ;
	
	try {		
	PreparedStatement req = cnx.prepareStatement("select * from operation where numerOperation = ? ");
	req.setString(1, code);
	
	ResultSet res=	req.executeQuery();
		while (res.next())
		{
			//String idSiege, float montant, String numeroCompt,String numeroOperation,Client client
			//Client
			String NumClient =res.getString(6);
			PreparedStatement req2 = cnx.prepareStatement("select * from Client where NumeroClient = ? ");
			req2.setString(1, NumClient);
			ResultSet res2=	req2.executeQuery();
			clt = new Client (res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9) );
			
			//Compte
			String NumCompt =res.getString(5);
			PreparedStatement req1 = cnx.prepareStatement("select * from Compte where NumCompt = ? ");
			req1.setString(1, NumCompt);
			ResultSet res1=	req.executeQuery();
			cmpt= new Compte(res1.getString(1),res1.getString(2),res1.getFloat(3),clt);
			
			//operation
			op = new Operation(code, res.getString(2),res.getString(3),cmpt,clt) ;
			System.out.println(res.getString(1) +" " +res.getString(2));
		}
	req.close();
	}catch (SQLException e)
	{System.out.println("Erreur de chargement de client.. verifier !!"+ e.getMessage());}
	
	return op;
	}

	//FindBy ID , DateDebut, DateFin
	public Operation findByIdDateDebFin (String code, String DateDebut, String DateFin) {
		Connection	cnx = Sconnection.getInstance() ;
		Operation op=null;
		Client clt =null;
		Compte cmpt=null ;
		
		try {		
		PreparedStatement req = cnx.prepareStatement("SELECT * FROM operation WHERE NumeroCompt= ? and Date> ? and Date<?");													//;
		req.setString(1, code);
		req.setString(2, DateDebut);
		req.setString(3, DateFin);
		
		ResultSet res=	req.executeQuery();
			while (res.next())
			{
				//String idSiege, float montant, String numeroCompt,String numeroOperation,Client client
				//Client
				String NumClient =res.getString(6);
				PreparedStatement req2 = cnx.prepareStatement("select * from Client where NumeroClient = ? ");
				req2.setString(1, NumClient);
				ResultSet res2=	req2.executeQuery();
				clt = new Client (res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9) );
				
				//Compte
				String NumCompt =res.getString(5);
				PreparedStatement req1 = cnx.prepareStatement("select * from Compte where NumCompt = ? ");
				req1.setString(1, NumCompt);
				ResultSet res1=	req.executeQuery();
				cmpt= new Compte(res1.getString(1),res1.getString(2),res1.getFloat(3),clt);
				
				//operation
				op = new Operation(code, res.getString(2),res.getString(3),cmpt,clt) ;
				System.out.println(res.getString(2) +" " +res.getString(3)+" " +res.getString(4));
			}
		req.close();
		}catch (SQLException e)
		{System.out.println("Erreur de chargement de client.. verifier !!"+ e.getMessage());}
		
		return op;
		}
	
	
	//SAVE 
	public void save (Operation op) {		
	Connection	cnx = Sconnection.getInstance() ;
	
	if(findById(op.getNumeroOperation())==null) 
	{	
		try {
		PreparedStatement req = cnx.prepareStatement("insert into operation values(?,?,?,?,?)");
		req.setString(1, op.getNumeroOperation() );		
		req.setString(2, op.getTypeOperation()  );	
		req.setString(3,op.getDateOp());
		req.setString(4, op.getCompte().getNumeroCompt() );	
		req.setString(5, op.getClient().getNumClient() );	

		int res=req.executeUpdate();
		
		if(res==1)
			System.out.println("Enregistrer l'operation avec succès");
		else
			System.out.println("Aucun operation n'a enregistré !" );
		req.close();
		
		}catch (SQLException e)
		{System.out.println("Erreur en niveau de ajouter.. verifier !!"+ e.getMessage());}
		
	}else System.out.println("Cet operation existe dans la base");
	}

	
	//Update 
	public void update (Operation op) {		
		Connection	cnx = Sconnection.getInstance() ;
		
		if(findById(op.getNumeroOperation())!=null) 
		{	
			int n=0;
			try {
				PreparedStatement req=cnx.prepareStatement("update operation Set TypeOperation=?	Date =?	NumeroCompt =?	NumeroClient=? where numerOperation=? ");
				
				req.setString(1, op.getTypeOperation()  );	
				req.setString(2,op.getDateOp());
				req.setString(3, op.getCompte().getNumeroCompt() );	
				req.setString(4, op.getClient().getNumClient() );	
				req.setString(5, op.getNumeroOperation() );		
				
				n=req.executeUpdate();
				if(n==1)
					System.out.println("Mise à jour du employe avec succès");
				else
					System.out.println("Aucun employe n'a été mis à jour" );
				req.close();					
				} 
			catch (SQLException e)
			{System.out.println("Erreur en niveau de ajouter.. verifier !!"+ e.getMessage());}
			
		}else System.out.println("Ce client n'existe dans la base");
		}
	
	
	//Delete
	public void delete (String code) {
		Connection cnx= Sconnection.getInstance();
		PreparedStatement req=null;
		int n=0;
		try {
				req= cnx.prepareStatement("delete from operation where numerOperation=?");
				req.setString(1, code);
				n=req.executeUpdate();		
				if(n==1)
					System.out.println("Suppression d'operation avec succès");
				else
					System.out.println("Aucune operation a été supprimée" );
				req.close();
			}
		catch(Exception e) {
			System.out.println("La requête n'a pas pu être exécutée");
			e.printStackTrace();
		}	
	}

}
