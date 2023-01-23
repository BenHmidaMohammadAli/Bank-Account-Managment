package application.Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Model.Entities.Client;
import application.Model.Entities.Compte;
import application.Model.Entities.Employe;
import application.Model.Entities.Operation;
import application.Model.Entities.Reclamation;

public class EmployeDAO {

	//FindBy ID
			public Employe findById (String code) {
			Connection	cnx = Sconnection.getInstance() ;
			Employe emp=null;
			try {		
			PreparedStatement req = cnx.prepareStatement("select * from employe where NumeroEmploye = ? ");
			req.setString(1, code);
			ResultSet res=	req.executeQuery();
				while (res.next())
				{
					emp = new Employe(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),code, res.getString(9)) ;
					System.out.println(res.getString(2) +" " +res.getString(3)+" " +res.getString(4));
		
					//liste des reclamation			
					PreparedStatement req2 = cnx.prepareStatement("select * from reclamation where NumeroEmploye = ? ");
					req2.setString(1, code);
					
					ArrayList <Reclamation> result = new ArrayList<Reclamation>();
					ResultSet rs = req2.executeQuery();
					for (int i=0; rs.next(); i++)
					{
						//creation client qui est liée a reclamation bien definie 
						PreparedStatement req3 = cnx.prepareStatement("select * from Client where NumeroClient = ? ");
						req2.setString(1, res.getString(4));
						ResultSet rs3 = req3.executeQuery();
						Client clt =new Client(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7) ,rs.getString(8));
																				
						//creation list des reclamations selon client qui est liée avec
					   result.add(new Reclamation(rs.getString(1),rs.getString(2),clt,emp,rs.getBoolean(4)) );
					}
					
					emp.setReclamation(result);
					
					req2.close();
				
				
				}
			req.close();
			}catch (SQLException e)
			{System.out.println("Erreur de chargement de employe.. verifier !!"+ e.getMessage());}
			
			return emp;
			
			}
	
	
	//SAVE 
			public void save (Employe emp) {		
			Connection	cnx = Sconnection.getInstance() ;
			
			if(findById(emp.getNumeroEmploye())==null) 
			{	
				try {
				PreparedStatement req = cnx.prepareStatement("insert into employe values(?,?,?,?,?,?,?,?,?)");
				req.setInt(1, emp.getId());		
				req.setString(2, emp.getNumeroEmploye());		
				req.setString(3, emp.getNom());
				req.setString(4, emp.getPrenom());
				req.setString(5, emp.getAdresse());
				req.setString(6, emp.getDN());
				req.setString(7, emp.getEmail());
				req.setString(8, emp.getNumeroTel());
				req.setString(9, emp.getmdp() );
				int res=req.executeUpdate();
				
				if(res==1)
					System.out.println("Enregistrer l'employe avec succès");
				else
					System.out.println("Aucun employe n'a enregistré !" );
				req.close();
				
				}catch (SQLException e)
				{System.out.println("Erreur en niveau de ajouter.. verifier !!"+ e.getMessage());}
				
			}else System.out.println("Cet employe existe dans la base");
			}
			
			
	//Update 
			public void update (Employe emp) {		
				Connection	cnx = Sconnection.getInstance() ;
				
				if(findById(emp.getNumeroEmploye())!=null) 
				{	
					int n=0;
					try {
						PreparedStatement req=cnx.prepareStatement("update employe Set Nom=?, Prenom=?,Adresse=?, DN=?, Email=?,NumeroTel=?, mdp=?  where NumeroEmploye=?");
						req.setString(1, emp.getNom());
						req.setString(2, emp.getPrenom());
						req.setString(3, emp.getAdresse());
						req.setString(4, emp.getDN());
						req.setString(5, emp.getEmail());
						req.setString(6, emp.getNumeroTel());
						req.setString(7, emp.getmdp() );
						req.setString(8, emp.getNumeroEmploye());
							
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
						req= cnx.prepareStatement("delete from employe where NumeroEmploye=?");
						req.setString(1, code);
						n=req.executeUpdate();		
						if(n==1)
							System.out.println("Suppression d'employe avec succès");
						else
							System.out.println("Aucune employe a été supprimée" );
						req.close();
					}
				catch(Exception e) {
					System.out.println("La requête n'a pas pu être exécutée");
					e.printStackTrace();
				}	
			}
	
	
}
