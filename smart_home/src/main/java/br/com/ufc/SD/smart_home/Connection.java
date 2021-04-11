	package br.com.ufc.SD.smart_home;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Connection {
	
	private static final String LUMINOSITY = "luminosity_queue";
	private static final String TEMPERATURE = "temperature_queue";
	private static final String LOCATION = "location_queue";
	private static final String VACUUMCLEANER = "vacuum_queue";
	private static final String DOORLOCK = "door_queue";
	
	public static void main(String[] argv) throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		com.rabbitmq.client.Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(TEMPERATURE, "direct");
		String temperature_queue = channel.queueDeclare().getQueue();
		channel.queueBind(temperature_queue, TEMPERATURE, "");
		
		channel.exchangeDeclare(LUMINOSITY, "direct");
		String luminosity_queue = channel.queueDeclare().getQueue();
		channel.queueBind(luminosity_queue, LUMINOSITY, "");
		
		channel.exchangeDeclare(DOORLOCK, "direct");
		String door_queue = channel.queueDeclare().getQueue();
		channel.queueBind(door_queue, DOORLOCK, "");
		
		channel.exchangeDeclare(LOCATION, "direct");
		String location_queue = channel.queueDeclare().getQueue();
		channel.queueBind(location_queue, LOCATION, "");
		
		channel.exchangeDeclare(VACUUMCLEANER, "direct");
		String vacuum_queue = channel.queueDeclare().getQueue();
		channel.queueBind(vacuum_queue, VACUUMCLEANER, "");
		
		System.out.println(" [*] Aguardando mensagem...");
		
		House house = new House();
		DeliverCallback deliverCallback =(consumerTag, delivery) -> {
		String message = new String(delivery.getBody(), "UTF-8");
		if(delivery.getEnvelope().getExchange().equals("temperature_queue"))
			house.onAirConditioning(message);
		if(delivery.getEnvelope().getExchange().equals("luminosity_queue")) 
				house.turningOnLights(message);
		if(delivery.getEnvelope().getExchange().equals("door_queue")) 
				house.setStateDoor(message);
		if(delivery.getEnvelope().getExchange().equals("vacuum_queue"))
				house.turnOnVacuum(message);
					
		};		
				
			channel.basicConsume(temperature_queue, true, deliverCallback, consumerTag -> { });
			channel.basicConsume(luminosity_queue, true, deliverCallback, consumerTag -> { });
			channel.basicConsume(door_queue, true, deliverCallback, consumerTag -> { });
			channel.basicConsume(location_queue, true, deliverCallback, consumerTag -> { });
			channel.basicConsume(vacuum_queue, true, deliverCallback, consumerTag -> { });

		
	}


}
