package uk.ac.open.util;

import java.net.URI;

import com.ciscospark.Message;
import com.ciscospark.Spark;

public class SparkConnector {
	
	private Spark spark;
	
	public SparkConnector(String accessToken){
		 spark = Spark.builder()
	                .baseUrl(URI.create("https://api.ciscospark.com/v1"))
	                .accessToken(accessToken)
	                .build();
	}
	
	
	public void send(String msg, String roomId){
        // Post a text message to the room
        Message message = new Message();
        message.setRoomId(roomId);
        message.setText(msg);
        spark.messages().post(message);
	}
	
	public void printAllMessages(String roomID){
		spark.messages()
        .queryParam("roomId", roomID)
        .iterate()
        .forEachRemaining(message -> {
            System.out.println(message.getText());
        });
	}
	
	public String getLastMessage(String roomID){
		return spark.messages()
        .queryParam("roomId", roomID)
        .iterate().next().getText();
	}
	
}
