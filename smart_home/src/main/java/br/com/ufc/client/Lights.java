package br.com.ufc.client;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import br.com.ufc.SD.smart_home.SimulatorSensor;

public class Lights {
	 private static final String LUMINOSITY = "luminosity_queue";
	

	  public static void main(String[] argv) throws Exception {
		Random values_random = new Random(); 

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    try (Connection connection = (Connection) factory.newConnection();
	         Channel channel = (Channel) connection.createChannel()) {
	         channel.exchangeDeclare(LUMINOSITY, "direct");

	         while (true) {
	        	 TimeUnit.SECONDS.sleep(3);
	        	 SimulatorSensor sensor = new SimulatorSensor();
	        	 sensor.setLuminosity(String.valueOf(values_random.nextInt(500)));
	        	 String message = sensor.getLuminosity();
	
	        	 channel.basicPublish(LUMINOSITY, "", null, message.getBytes("UTF-8"));	
	        	 System.out.println(" [x] a luminosidade '" + message + "' lux");
	        }

			        
		
	    }
	 } 

}
