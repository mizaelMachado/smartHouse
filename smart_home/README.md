# Smart House

## project objectives
This project was proposed by Professor Almada in the field of Distributed Systems, in which his objective is to simulate a smart home, for that, I used RabbitMQ that implemented the "Advanced Message Queuing Protocol" protocol, which was extended with a plug-in architecture in to support the "Streaming Text Oriented Messaging Protocol" protocol, the MQTT, that is, a message broker, I also used the operating system level virtualization docker, the containers.
To simulate the smart home I created smart objects belonging to the home, as an example the air conditioner that turns on when it reaches above 20 degrees and the vacuum cleaner that is turned on from the simulation of a "on" or "off" voice command.
An object was also created that simulates the necessary sensors, such as temperature sensor and geolocation.


1. Get adoptopenjdk-8: https://adoptopenjdk.net/
2. Get maven 3.6.x: https://maven.apache.org/
3. Add above to your path if neccessary.
4. git clone git@github.com:https://github.com/mizaelMachado/smartHouse.git`
5. `mvn clean package`
6. `java -jar target/test-1.0.0-SNAPSHOT.jar`
