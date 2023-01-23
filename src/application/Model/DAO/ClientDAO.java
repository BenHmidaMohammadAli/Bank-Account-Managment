package application.Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.Model.Entities.Client;
import application.Model.Entities.Compte;
import application.Model.Entities.Employe;
import application.Model.Entities.Operation;
import application.Model.Entities.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientDAO {
		//FindBy ID
		public Client findById (String code) {
		Connection	cnx = Sconnection.getInstance() ;
		Client clt=null;
		Employe emp=null;
		try {		
		PreparedStatement req = cnx.prepareStatement("select * from client where NumeroClient = ? ");
		req.setString(1, code);
		ResultSet res=	req.executeQuery();
			while (res.next())
			{
				//clt
				clt = new Client(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),code,res.getString(9)) ;
				System.out.println(res.getString(2) +" " +res.getString(3)+" " +res.getString(4));
				
				//liste des comptes -2- 
				PreparedStatement req2 = cnx.prepareStatement("select * from compte where NumeroClient = ? ");
				req2.setString(1, res.getString(2));
				
				ArrayList <Compte> result2 = new ArrayList<Compte>();
				ResultSet rs2 = req2.executeQuery();
				if (rs2.next() == true ) {
					for (int i=0; rs2.next(); i++)
					{
					   result2.add(new Compte(rs2.getString(1),rs2.getString(2),rs2.getFloat(3),clt ) );
					}
				clt.setCompte(result2); 
				}		
				//Liste des operations -4-5
				PreparedStatement req4 = cnx.prepareStatement("select * from operation where NumeroClient = ? ");
				req4.setString(1, res.getString(2));
				ResultSet rs4 = req4.executeQuery();
				
				PreparedStatement req5 = cnx.prepareStatement("select * from compte where NumeroCompt = ? ");
				req5.setString(1, rs4.getString(4));
				ResultSet rs5 = req5.executeQuery();			
				Compte cmpt = new Compte(rs5.getString(1),rs5.getString(1),rs5.getFloat(1),clt) ;
				
				ArrayList <Operation> result4 = new ArrayList<Operation>();
				if (rs4.next() == true ) {
					for (int i=0; rs2.next(); i++)
					{
					   result4.add(new Operation(rs4.getString(1),rs4.getString(2),rs4.getString(3),cmpt,clt) );
					}
					clt.setOperation(result4);
				}		
				//liste reclamation -6-
				PreparedStatement req6 = cnx.prepareStatement("select * from reclamation where NumeroClient = ? ");
				req6.setString(1, res.getString(2));
				ResultSet rs6 = req6.executeQuery();				
				ArrayList <Reclamation> result6 = new ArrayList<Reclamation>();	
				if (rs6.next() == true ) {
					for (int i=0; rs6.next(); i++)
					{
						//employe de chaque client 
						PreparedStatement req7 = cnx.prepareStatement("select * from employe where NumeroEmploye = ? ");
						req.setString(1, rs6.getString(4));
						ResultSet res7=	req.executeQuery();
						emp = new Employe(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),code,res.getString(9)) ;	
						req7.close();
						
						result6.add(new Reclamation(rs6.getString(1),rs6.getString(2),clt,emp,rs6.getBoolean(5) ) );
					}
					clt.setReclamation(result6);
				}	
				req4.close();
				req5.close();
				req6.close();
			}
		req.close();
		}catch (SQLException e)
		{System.out.println("Erreur de chargement de client.. verifier !!"+ e.getMessage());}
		
		return clt;
		}
	
		//Return List Client
		public ObservableList<Client> getClientlist (){
			ObservableList<Client> ClientList = FXCollections.observableArrayList();
			Connection	cnx = Sconnection.getInstance() ;
			
			Client clt=null;
			Employe emp=null;
			try {		
			PreparedStatement req = cnx.prepareStatement("select * from client ");
			ResultSet res=	req.executeQuery();
				while (res.next())
				{
					//clt
					clt = new Client(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9)) ;
					System.out.println(res.getString(2) +" " +res.getString(3)+" " +res.getString(4));
					ClientList.add(clt) ;					
				}
				
			req.close();
			
			}catch (SQLException e)
			{System.out.println("Erreur de chargement de client.. verifier !!"+ e.getMessage());}
			
			
			return ClientList;
		}
		
		

		//SAVE 
		public void save (Client c) {		
		Connection	cnx = Sconnection.getInstance() ;
		
		if(findById(c.getNumClient())==null) 
		{	
			try {
			PreparedStatement req = cnx.prepareStatement("insert into Client values(?,?,?,?,?,?,?,?,?)");
			req.setInt(1, c.getId());		
			req.setString(2, c.getNumClient());		
			req.setString(3, c.getNom());
			req.setString(4, c.getPrenom());
			req.setString(5, c.getAdresse());
			req.setString(6, c.getDN());
			req.setString(7, c.getEmail());
			req.setString(8, c.getNumeroTel());
			req.setString(9, c.getmdp() );
			int res=req.executeUpdate();
			
			if(res==1)
				System.out.println("Enregistrer le client avec succès");
			else
				System.out.println("Aucun client n'a enregistré !" );
			req.close();
			
			}catch (SQLException e)
			{System.out.println("Erreur en niveau de ajouter.. verifier !!"+ e.getMessage());}
			
		}else System.out.println("Ce client existe dans la base");
		}
		
		
		//Update 
		public void update (Client c) {		
			Connection	cnx = Sconnection.getInstance() ;
			
			if(findById(c.getNumClient())!=null) 
			{	
				int n=0;
				try {
					PreparedStatement req=cnx.prepareStatement("update client Set Nom=?, Prenom=?,Adresse=?, DN=?, Email=?,NumeroTel=?, mdp=? where NumeroClient=?");
					req.setString(1, c.getNom());
					req.setString(2, c.getPrenom());
					req.setString(3, c.getAdresse());
					req.setString(4, c.getDN());
					req.setString(5, c.getEmail());
					req.setString(6, c.getNumeroTel());
					req.setString(7, c.getmdp());
					req.setString(8, c.getNumClient());
					
					n=req.executeUpdate();
					if(n==1)
						System.out.println("Mise à jour du client avec succès");
					else
						System.out.println("Aucun client n'a été mis à jour" );
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
					//client 
					req= cnx.prepareStatement("delete from Client where NumeroClient=?");
					req.setString(1, code);
					n=req.executeUpdate();		
					if(n==1)
						System.out.println("Suppression du client avec succès");
					else
						System.out.println("Aucune client a été supprimée" );
					req.close();
					//Compte
					req= cnx.prepareStatement("delete from Compte where NumeroClient=?");
					req.setString(1, code);
					n=req.executeUpdate();		
					if(n==1)
						System.out.println("Suppression du compte avec succès");
					else
						System.out.println("Aucune compte a été supprimée" );
					req.close();
					//Operation  
					req= cnx.prepareStatement("delete from Operation where NumeroClient=?");
					req.setString(1, code);
					n=req.executeUpdate();		
					if(n==1)
						System.out.println("Suppression d'Operation avec succès");
					else
						System.out.println("Aucune Operation a été supprimée" );
					req.close();
					//reclamation
					/*
					req= cnx.prepareStatement("delete from Reclamation where NumeroClient=?");
					req.setString(1, code);
					n=req.executeUpdate();		
					if(n==1)
						System.out.println("Suppression de reclamation avec succès");
					else
						System.out.println("Aucune reclamation a été supprimée" );
					req.close();
					*/
				}
			catch(Exception e) {
				System.out.println("La requête n'a pas pu être exécutée");
				e.printStackTrace();
			}	
		}
	
	
}
