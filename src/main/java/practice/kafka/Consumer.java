package practice.kafka;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

public class Consumer {
	private Properties properties = null;
	private KafkaConsumer<String, String> consumer = null;
	
	public Consumer (String propertyFile) {
		properties = new Properties();
        try{
        	InputStream input = new FileInputStream(propertyFile);
            properties.load(input);
            consumer = new KafkaConsumer<String, String>(properties);
        } catch (Exception e) {
        	e.printStackTrace();
        } 
	}
	
	public KafkaConsumer<String, String> getConsumer()
	{
		return this.consumer;
	}
	
	public void consumeDaemon()
	{
    	System.out.println("Consumer running...");	

		while (true) {
    		for(ConsumerRecord<String, String> record : consumer.poll(100))
    		{

    			System.out.println(record.topic() + "\t" + record.value());
    		}
    	}
	}
	
	public void close ()
	{
		consumer.close();
	}
	
    public static void main( String[] args )
	{
    	String consumerPropertyFile = "src/main/resources/consumer.props";
    	Consumer consumer = new Consumer(consumerPropertyFile);
    	consumer.getConsumer().subscribe(Arrays.asList("test"));
    	consumer.consumeDaemon();
	}
}
