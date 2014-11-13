package com.tp1.ServerMain;


import com.tp1.Connection.Connexion;
import com.tp1.dbRecords.DatabaseRecords;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		AddDataToDatabase();
		
		Connexion connexion = new Connexion();
		connexion.InitListener();
	}
	
	/**
	 * Create all the data
	 */
	private static void AddDataToDatabase()
	{
		DatabaseRecords dbr = new DatabaseRecords();
		dbr.AddDataToDB();
	}

}
