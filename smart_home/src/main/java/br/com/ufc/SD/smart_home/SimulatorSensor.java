package br.com.ufc.SD.smart_home;

import java.io.IOException;


import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;



public class SimulatorSensor {
	
	
	private static final String LUMINOSITY = "luminosity_queue";
	private static final String TEMPERATURE = "temperature_queue";
	private static final String LOCATION = "location_queue";
	private static final String VACUUMCLEANER = "vacuum_queue";
	private static final String DOORLOCK = "door_queue";
	private static String luminosity;
	private static String temperature;
	private static String location;
	private static String command;
	private static String distanceDoorOpen;

	


	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		 
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
		while(true) {
			
			
	        
	        try (Connection connection = factory.newConnection();
	             Channel channel = connection.createChannel()) {
	        	
	        	 if(temperature != null) { 
	        		 
		            channel.queueDeclare(TEMPERATURE, true, false, false, null);
		            String message = String.join(" ", temperature);
		            channel.basicPublish("", TEMPERATURE,
		                    MessageProperties.PERSISTENT_TEXT_PLAIN,
		                    message.getBytes("UTF-8"));
		            System.out.println(" [x] temperatura: '" + message + "'");
		            
	        	 }
	        	 
	        	 if(luminosity != null) {
	        		 
	        		channel.queueDeclare(LUMINOSITY, true, false, false, null);
	 	            String message = String.join(" ", luminosity);
	 	            channel.basicPublish("", LUMINOSITY,
	 	                    MessageProperties.PERSISTENT_TEXT_PLAIN,
	 	                    message.getBytes("UTF-8"));
	 	            System.out.println(" [x] Luminosidade: '" + message + "'");
	 	            
	             }
	        	 if(location != null) {
	        		 
	        		channel.queueDeclare(LOCATION, true, false, false, null);
	  	            String message = String.join(" ", location);
	  	            channel.basicPublish("", LOCATION,
	  	                    MessageProperties.PERSISTENT_TEXT_PLAIN,
	  	                    message.getBytes("UTF-8"));
	  	            System.out.println(" [x] localização: '" + message + "'");
	  	            
	             }
	        	 if(command != null) {
	        		 channel.queueDeclare(VACUUMCLEANER, true, false, false, null);
		  	         String message = String.join(" ", command);
		  	         channel.basicPublish("", VACUUMCLEANER,
		  	                    MessageProperties.PERSISTENT_TEXT_PLAIN,
		  	                    message.getBytes("UTF-8"));
		  	         System.out.println(" [x] aspirador '" + message + "'");
	        		 
	        	 }
	        	 if(distanceDoorOpen != null) {
	        		 channel.queueDeclare(DOORLOCK, true, false, false, null);
		  	         String message = String.join(" ", distanceDoorOpen);
		  	         channel.basicPublish("", DOORLOCK,
		  	                    MessageProperties.PERSISTENT_TEXT_PLAIN,
		  	                    message.getBytes("UTF-8"));
		  	         System.out.println(" [x] distancia da porta '" + message + "'");
	        		 
	        	 }
	        	 connection.close();
	        }
		}
	           
      }
	
	public String getLuminosity() {
		return luminosity;
	}

	public void setLuminosity(String luminosity) {
		SimulatorSensor.luminosity = luminosity;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		SimulatorSensor.temperature = temperature;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		SimulatorSensor.location = location;
	}

	public SimulatorSensor() {

	}

	public static String getCommand() {
		return command;
	}

	public static void setCommand(String command) {
		SimulatorSensor.command = command;
	}

	public String getDistanceDoorOpen() {
		return distanceDoorOpen;
	}
	
	public void setDistanceDoorOpen(String location) {
		SimulatorSensor.location = location;
	}


}
