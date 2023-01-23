 module Gestion_Comptes {
	requires javafx.controls;
	requires java.sql ;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	/*
	opens application.Controller to javafx.graphics, javafx.fxml;
	*/
	opens application.Controller to javafx.fxml ;
	opens application.Model.Entities to javafx.base;
	
	exports application to javafx.graphics ;
	exports application.Controller to javafx.fxml ;
	
}
