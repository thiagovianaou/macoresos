package uk.ac.open.simulation;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import uk.ac.open.util.SparkConnector;

public class CISCO_SPARK_TEST {

	public static void main(String[] args) throws ClientProtocolException, IOException, InterruptedException {
		Simulation.sim();
		while(true){
			testing();
			Thread.sleep(3000);
		}

//		String ROOM_ID = "Y2lzY29zcGFyazovL3VzL1JPT00vMGQyZjAyNTAtZTE3NC0xMWU3LWEzZjItMTMwMGE3ZDBlNTI1";
//		String ROOM_RES = "Y2lzY29zcGFyazovL3VzL1JPT00vOWYxMGMyNTAtZTE2ZC0xMWU3LTliZTktYWZhNzg4ZDZiN2Nk";
//		String ACCESS_TOKEN = "NzU0MTIyMWEtZjRjNS00YTkxLWE4NzgtYTg1YjQ5ZjE0NTZiOGZhM2Y3ODMtZTk5";
//		String msg= "Stop coffee";

		
		//SparkConnector sc = new SparkConnector(ACCESS_TOKEN);
		//sc.printAllMessages(ROOM_ID);
		//sc.getLastMessage(ROOM_ID);
		//sc.send(msg, ROOM_RES);
	}
	
	public static void testing(){
		String ROOM_ID = "Y2lzY29zcGFyazovL3VzL1JPT00vMGQyZjAyNTAtZTE3NC0xMWU3LWEzZjItMTMwMGE3ZDBlNTI1";
		String ROOM_RES = "Y2lzY29zcGFyazovL3VzL1JPT00vOWYxMGMyNTAtZTE2ZC0xMWU3LTliZTktYWZhNzg4ZDZiN2Nk";
		String ACCESS_TOKEN = "NzU0MTIyMWEtZjRjNS00YTkxLWE4NzgtYTg1YjQ5ZjE0NTZiOGZhM2Y3ODMtZTk5";
		String msg= "Stop coffee";
		
		
		
		
		SparkConnector sc = new SparkConnector(ACCESS_TOKEN);
		String lastMessage = sc.getLastMessage(ROOM_ID);
		
		if(lastMessage.equals("Door Open")){
			sc.send(msg, ROOM_RES);
		}
	}
}
