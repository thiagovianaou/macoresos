package uk.ac.open.data;

public class DBFactory {
	
	private DBInterface db;
	private static DBFactory instance;
	
	private DBFactory(){
		db = new DBJPA();
	}
	
	public static DBInterface getDB(){
		if(instance == null){
			instance = new DBFactory();
		}
		return instance.db;
	}
}
