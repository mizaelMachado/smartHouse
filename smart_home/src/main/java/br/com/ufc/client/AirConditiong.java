package br.com.ufc.client;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


import br.com.ufc.SD.smart_home.SimulatorSensor;

public class AirConditiong {
	
	private static final String TEMPERATURE = "temperature_queue";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Random values_random = new Random(); 
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    
	    
	    try (Connection connection = (Connection) factory.newConnection();
	         Channel channel = (Channel) connection.createChannel()) {
	         channel.exchangeDeclare(TEMPERATURE, "direct");
	         
	         while (true) {
	        	TimeUnit.SECONDS.sleep(3);
	        	SimulatorSensor sensor = new SimulatorSensor();
	        	sensor.setTemperature(String.valueOf(values_random.nextInt(50))); 
		        String message =  sensor.getTemperature();
		        channel.basicPublish(TEMPERATURE, "", null, message.getBytes("UTF-8"));	
			    System.out.println(" [x] Temperatura do ar: '" + message + "'");
		
	         }
	
    	}
	}   
}


