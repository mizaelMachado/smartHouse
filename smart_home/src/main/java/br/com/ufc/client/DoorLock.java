package br.com.ufc.client;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import br.com.ufc.SD.smart_home.SimulatorSensor;

public class DoorLock {
	
	 private static final String DOORLOCK = "door_queue";


	  public static void main(String[] argv) throws Exception {
		  Random values_random = new Random(); 

		    ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost("localhost");
		    try (Connection connection = (Connection) factory.newConnection();
		         Channel channel = (Channel) connection.createChannel()) {
		         channel.exchangeDeclare(DOORLOCK, "direct");

		         while (true) {
		        	 TimeUnit.SECONDS.sleep(3);
		        	 SimulatorSensor sensor = new SimulatorSensor();
		        	 sensor.setDistanceDoorOpen(String.valueOf(values_random.nextInt(500)));
		        	 String message = sensor.getDistanceDoorOpen();
		
		        	 channel.basicPublish(DOORLOCK, "", null, message.getBytes("UTF-8"));	
		        	 System.out.println(" [x] a distancia que ta da porta é '" + message + "' metros");
		        }

				        
			
		    }
	 } 

}
