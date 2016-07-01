package practice.kafka;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {
	private Properties properties = null;
	private KafkaProducer<String, String> producer = null;
	
	public Producer (String propertyFile) {
		properties = new Properties();
        try{
        	InputStream input = new FileInputStream(propertyFile);
            properties.load(input);
            producer = new KafkaProducer<String, String>(properties);
        } catch (Exception e) {
        	e.printStackTrace();
        } 
	}
	
	public void sendMsg(String topic, String msg)
	{
		producer.send(new ProducerRecord<String, String>(topic, msg));
	}
	
	public void close ()
	{
		producer.close();
	}
	
    public static void main( String[] args )
    {
    	String producerPropertyFile = "src/main/resources/producer.props";
    	Producer producer = new Producer(producerPropertyFile);
    	String topic = "test";
    	String input = "";
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	
    	while(!input.equals("$quit"))
    	{
    		System.out.print("> ");
    		input = sc.nextLine();
    		if(!input.equals("$quit"))
    		{
    			producer.sendMsg(topic, input);
    		}
    	}
    	producer.close();
   	
    }
}
