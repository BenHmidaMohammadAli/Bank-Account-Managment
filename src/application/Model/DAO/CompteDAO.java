package application.Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Model.Entities.Client;
import application.Model.Entities.Compte;
import application.Model.Entities.Operation;

public class CompteDAO {
	
	
	//FindBy ID
		public Compte findById (String code) {
		Connection	cnx = Sconnection.getInstance() ;
		
		Client clt =null;
		Compte cmpt=null ;
		
		try {		
		PreparedStatement req = cnx.prepareStatement("select * from Compte where NumeroCompt = ? ");
		req.setString(1, code);
		
		ResultSet res=	req.executeQuery();
			while (res.next())
			{
				
				//Client
				String NumClient =res.getString(6);
				PreparedStatement req2 = cnx.prepareStatement("select * from Client where NumeroClient = ? ");
				req2.setString(1, NumClient);
				ResultSet res2=	req2.executeQuery();
				clt = new Client (res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9) );
				
				
				//compte
				cmpt = new Compte(res.getString(1),res.getString(2),res.getFloat(3),clt) ;
				System.out.println(res.getString(1)+" "+res.getString(2) +" " +res.getString(3)+" " +res.getString(4));
				
				//liste des Operations
				PreparedStatement req3 = cnx.prepareStatement("select * from operation where NumeroCompt = ? ");
				req3.setString(1, res.getString(4));
				ResultSet rs = req3.executeQuery();

				ArrayList <Operation> result = new ArrayList<Operation>();
				for (int i=0; rs.next(); i++)
				{
				   result.add(new Operation(rs.getString(1),rs.getString(1),rs.getString(1), cmpt,clt ) );
				}
				
				cmpt.setOperation(result);
				
				req2.close();
				req3.close();
				
			}
		req.close();
		}catch (SQLException e)
		{System.out.println("Erreur de chargement de client.. verifier !!"+ e.getMessage());}
		
		return cmpt;
		}
		
		
		
	//SAVE
		public void save (Compte cmpt) {		
			Connection	cnx = Sconnection.getInstance() ;
			
			if(findById(cmpt.getNumeroCompt())==null) 
			{	
				try {
				PreparedStatement req = cnx.prepareStatement("insert into Compte values(?,?,?,?)");
				req.setString(1, cmpt.getNumeroCompt() );
				req.setString(2, cmpt.getIdSiege() );	
				req.setFloat(3, cmpt.getMontant() );	
				req.setString(4, cmpt.getClient().getNumClient() );	

				int res=req.executeUpdate();
				
				if(res==1)
					System.out.println("Enregistrer de compte avec succès");
				else
					System.out.println("Aucun compte n'a enregistré !" );
				req.close();
				
				}catch (SQLException e)
				{System.out.println("Erreur en niveau de ajouter.. verifier !!"+ e.getMessage());}
				
			}else System.out.println("Ce compte existe dans la base");
			}
		
	
	//Update 
		public void update (Compte cmpt) {		
			Connection	cnx = Sconnection.getInstance() ;
			
			if(findById(cmpt.getNumeroCompt()) !=null )
			{	
				int n=0;
				try {
					PreparedStatement req=cnx.prepareStatement("update Compte Set IdSiege= ? Montant=?	NumeroClient=? where NumeroCompt=? ");
					req.setString(1, cmpt.getIdSiege()  );	
					req.setFloat(2, cmpt.getMontant()  );	
					req.setString(3, cmpt.getClient().getNumClient()  );	
					req.setString(4, cmpt.getNumeroCompt()  );						
					
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
					//compte
					req1= cnx.prepareStatement("delete from Compte where NumeroCompt=?");
					req1.setString(1, code);
					n=req1.executeUpdate();		
					if(n==1)
						System.out.println("Suppression de compte avec succès");
					else
						System.out.println("Aucune compte a été supprimée" );
					req1.close();
					
					//operation
					req2= cnx.prepareStatement("delete from operation where NumeroCompt=?");
					req2.setString(1, code);
					n=req2.executeUpdate();
					if(n==1)
						System.out.println("Suppression des operations liée de ce compte avec succès");
					else
						System.out.println("Aucune des operations liée de ce compte a été supprimée" );
					req2.close();
								
				}
			catch(Exception e) {
				System.out.println("La requête n'a pas pu être exécutée");
				e.printStackTrace();
			}	
		}
		
	
}
