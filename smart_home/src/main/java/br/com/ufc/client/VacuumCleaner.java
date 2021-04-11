package br.com.ufc.client;


import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import br.com.ufc.SD.smart_home.SimulatorSensor;

public class VacuumCleaner {
	private static final String VACUUMCLEANER = "vacuum_queue";

	  public static void main(String[] argv) throws Exception {
		  

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    try (Connection connection = (Connection) factory.newConnection();
	         Channel channel = (Channel) connection.createChannel()) {
	         channel.exchangeDeclare(VACUUMCLEANER, "direct");
	         int i = 0;
	         while (true) {
	        	i++; 
	        	TimeUnit.SECONDS.sleep(3);
	        	SimulatorSensor.setCommand("DESLIGADO"); 
	        	if(i%2 == 0) {
	        		SimulatorSensor.setCommand("LIGADO"); 
	        	}
	        
		        String message = SimulatorSensor.getCommand();
		        channel.basicPublish(VACUUMCLEANER, "", null, message.getBytes("UTF-8"));
				System.out.println(" [x] Aspirador '" + message + "'");
	         }        
		
	    }
	 } 
}
