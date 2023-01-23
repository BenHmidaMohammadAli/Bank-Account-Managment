package application.Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Model.Entities.Client;
import application.Model.Entities.Compte;
import application.Model.Entities.Reclamation;
import application.Model.Entities.Employe;

public class ReclamationDAO {
	
	//FindBy ID
	public Reclamation findById (String code) {
	Connection	cnx = Sconnection.getInstance() ;
	
	Client clt =null;
	Employe emp=null ;
	Reclamation reclam =null; 
	
	try {		
	PreparedStatement req = cnx.prepareStatement("select * from reclamation where NumeroReclamation = ? ");
	req.setString(1, code);
	
	ResultSet res=	req.executeQuery();
		while (res.next())
		{
			
			//Client
			String NumClient =res.getString(3);
			PreparedStatement req2 = cnx.prepareStatement("select * from Client where NumeroClient = ? ");
			req2.setString(1, NumClient);
			
			ResultSet res2=	req2.executeQuery();
			clt = new Client (res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9) );
				
			//employe
			String NumEmp =res.getString(4);
			PreparedStatement req3 = cnx.prepareStatement("select * from Employe where NumeroEmploye = ? ");
			req2.setString(1, NumEmp);
			ResultSet res3=	req3.executeQuery();
			emp = new Employe (res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9) );
			
			//Reclamation
			reclam = new Reclamation(res.getString(1),res.getString(2),clt,emp,res.getBoolean(5)) ;
			System.out.println(res.getString(1)+" "+res.getString(2) +" " +res.getString(3)+" " +res.getString(4));
			
			req2.close();
			req3.close();						
		}
	req.close();
	}catch (SQLException e)
	{System.out.println("Erreur de chargement de reclamation.. verifier !!"+ e.getMessage());}
	
	return reclam;
	}

		
	//SAVE
	public void save (Reclamation reclam) {		
		Connection	cnx = Sconnection.getInstance() ;
		
		if(findById(reclam.getNumeroREclamation())==null) 
		{	
			try {
			PreparedStatement req = cnx.prepareStatement("insert into reclamation values(?,?,?,?,?)");
			req.setString(1, reclam.getNumeroREclamation() );
			req.setString(2, reclam.getTypeReclamation() );		
			req.setString(3, reclam.getClient().getNumClient() );
			req.setString(4, reclam.getClient().getNumClient() );
			req.setBoolean(4, reclam.getEtat());
			
			int res=req.executeUpdate();	
			if(res==1)
				System.out.println("Enregistrer de reclamation avec succès");
			else
				System.out.println("Aucun reclamation n'a enregistré !" );
			req.close();
			
			}catch (SQLException e)
			{System.out.println("Erreur en niveau de ajouter.. verifier !!"+ e.getMessage());}
			
		}else System.out.println("Ce reclamation existe dans la base");
		}
	

	//Update 
	public void update (Reclamation reclam) {		
		Connection	cnx = Sconnection.getInstance() ;
		
		if(findById(reclam.getNumeroREclamation()) !=null )
		{	
			int n=0;
			try {
				PreparedStatement req=cnx.prepareStatement("update reclamation set	TypeReclamation=?	NumeroClient=?	NumeroEmploye=?	Etat=? where NumeroReclamation=? ");
				req.setString(1, reclam.getTypeReclamation()  );	
				req.setString(2, reclam.getTypeReclamation()  );	
				req.setString(3, reclam.getClient().getNumClient()  );	
				req.setString(4, reclam.getEmploye().getNumeroEmploye() );
				req.setBoolean(5, reclam.getEtat());
				n=req.executeUpdate();
				if(n==1)
					System.out.println("Mise à jour du compte avec succès");
				else
					System.out.println("Aucun compte n'a été mis à jour" );
				req.close();					
				} 
			catch (SQLException e)
			{System.out.println("Erreur en niveau de ajouter.. verifier !!"+ e.getMessage());}
			
		}else System.out.println("Ce compte n'existe dans la base");
		}


	
	//Delete
	public void delete (String code) {
		Connection cnx= Sconnection.getInstance();
		PreparedStatement req1=null;
		PreparedStatement req2=null;
		int n=0;
		try {
				//reclamation
				req2= cnx.prepareStatement("delete from reclamation where NumeroReclamation=?");
				req2.setString(1, code);
				n=req2.executeUpdate();
				if(n==1)
					System.out.println("Suppression de reclamation liée de ce compte avec succès");
				else
					System.out.println("Aucune de reclamation liée de ce compte a été supprimée" );
				req2.close();
							
			}
		catch(Exception e) {
			System.out.println("La requête n'a pas pu être exécutée");
			e.printStackTrace();
		}	
	}
	

	
}
