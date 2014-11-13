package com.tp1.Connection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class TamponManager {

	/** Constructeur privé */
	private TamponManager()
	{
		tamponMatch = new HashMap<Integer, ExecutorService>();
	}
 
	/** Instance unique pré-initialisée */
	private static TamponManager INSTANCE = new TamponManager();
 
	/** Point d'accès pour l'instance unique du singleton */
	public static TamponManager getInstance()
	{	return INSTANCE;
	}
	
	public static ExecutorService GetTamponMatchAt(int idMatch)
	{
        return tamponMatch.get(idMatch);	
	}
	
	public static Map<Integer, ExecutorService> tamponMatch;
	
	public static void AddToTampon(int idMatch, ExecutorService threadPool){
		tamponMatch.put(idMatch, threadPool);
	}
}
