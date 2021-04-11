package br.com.ufc.SD.smart_home;


public class House {
	
	private float temperatureDefault = 20.5f;
	private float luminosityDefault = 150f;
	private float maximumDistance = 150f;
	private float distanceDoorOpen = 8;
	private boolean stateDoor = false;

	
	public House() {
		
	}
	
	
	public void turningOnLights(String message) {
		
		float msg = Float.parseFloat(message);
		if(msg < this.luminosityDefault) {
			System.out.println("[*] A luminosidade da casa está em  "+message+" lux \n  LUZES LIGADAS");
		}else {
			System.out.println("[*] A luminosidade da casa está em  "+message+" lux \n  LUZES DESGADAS");
		}
		
	}

	public void onAirConditioning(String message) {
		
		float msg = Float.parseFloat(message);
		if(msg > this.temperatureDefault) {
			System.out.println("[<>] Temperatura em  "+message+" Graus °C \n O AR CONDICIONADO LIGADO");
		}else{
			System.out.println("[<>] Temperatura em  "+message+" Graus °C \n O AR CONDICIONADO DESLIGADO");
		}
	}
	
	
	
	public void setStateDoor(String message) {
		float msg = Float.parseFloat(message);
		if(msg > getDistanceDoorOpen())
			System.out.println("[>>] O dono da casa está a  "+message+" metros da portar\n A PORTA ESTÁ FECHADA");
		if(msg < getDistanceDoorOpen())
			System.out.println("[<<] O dono da casa está a   "+message+" metros da portar \n A PORTA ESTÁ ABERTA");
	}
	
	public void turnOnVacuum(String message) {
		if(message.equals("LIGADO")) {
			System.out.println("[#] Comando de voz = "+message+"  Aspirador");
		}else {
			System.out.println("[#] Comando de voz = "+message+"  Aspirador");
		}
		
	}

	public float getTemperatureDefault() {
		return temperatureDefault;
	}

	public void setTemperatureDefault(float temperatureDefault) {
		this.temperatureDefault = temperatureDefault;
	}

	public float getLuminosityDefault() {
		return luminosityDefault;
	}

	public void setLuminosityDefault(float luminosityDefault) {
		this.luminosityDefault = luminosityDefault;
	}

	public boolean isStateDoor() {
		return stateDoor;
	}

	

	public float getMaximumDistance() {
		return maximumDistance;
	}

	public void setMaximumDistance(float maximumDistance) {
		this.maximumDistance = maximumDistance;
	}
	
	
	public float getDistanceDoorOpen() {
		return distanceDoorOpen;
	}

	public void setDistanceDoorOpen(float distanceDoorOpen) {
		this.distanceDoorOpen = distanceDoorOpen;
	}


  
}
