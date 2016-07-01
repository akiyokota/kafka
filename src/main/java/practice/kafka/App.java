package practice.kafka;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//    	String producerPropertyFile = "src/main/resources/producer.props";
//    	Producer producer = new Producer(producerPropertyFile);
//    	producer.sendMsg("test", "sampleMsg");
//    	producer.close();
    	
    	try {
	    	String consumerPropertyFile = "src/main/resources/consumer.props";
	    	InputStream input = new FileInputStream(consumerPropertyFile);
	    	Properties properties = new Properties();
	    	properties.load(input);
	    	KafkaConsumer<String, String> consumer = new KafkaConsumer<String,String>(properties);
	    	consumer.subscribe(Arrays.asList("test"));
	    	System.out.println("Consumer running...");	
	    	
	    	
	    	while (true) {
	    		for(ConsumerRecord<String, String> record : consumer.poll(100))
	    		{

	    			System.out.println(record.topic() + "\t" + record.value());
	    		}
	    	}
	    	   

    	} catch (Exception e) {
    		e.printStackTrace();
    	} 
    }
}
