package application.Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sconnection {
	private static String url="jdbc:mysql://localhost:3306/gestiondecomptdb" ;
	private static String utilisateur = "root" ;
	private static String motPasse= "" ;
	private static Connection cnx=null ;
	
	
	//getInstance(): java.sql.Connection
	
	public static Connection getInstance(){
		try
		{ cnx= DriverManager.getConnection(url,utilisateur, motPasse);}
		catch (SQLException e)
		{System.out.println("Erreur pendant la connexion :"+ e.getMessage());}
		return cnx ;
	}
	
	public static void close() {
		try {
			cnx.close();
		} catch (SQLException e)
		{System.out.println("Erreur pendant la fermeture du connexion : "+ e.getMessage());}
	}
	
}
