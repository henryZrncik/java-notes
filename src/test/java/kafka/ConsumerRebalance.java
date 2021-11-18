//package kafka;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.TopicPartition;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.Duration;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Properties;
//import java.util.concurrent.ExecutionException;
//
//public class ConsumerRebalance {
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Logger logger = LoggerFactory.getLogger(Object.class);
//        Properties properties = new Properties();
//        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,  "group-id-3");
//        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,  "false");
//        // earliest, latest possible configuration
//        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest" );
//
//        // creating consumer with key-value topic
//        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
//
//        // subscribing to collection of topics
//        kafkaConsumer.subscribe(Collections.singleton("my-topic111"));
//
//        ConsumerRebalanceListener rebalanceListener = new ConsumerRebalanceListener() {
//            @Override
//            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
//
//            }
//
//            @Override
//            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
//
//            }
//        }
//
//        //
//        int i = 0 ;
//        while (true){
//            i++;
//            System.out.println(String.format("consumer is consuming %d", i));
//            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(1));
//            for( ConsumerRecord<String,String> record  : records){
//                System.out.println("----------------");
//                System.out.println(record.key());
//                System.out.println(record.value());
//                System.out.println(record.offset());
//                System.out.println(record.partition());
//                rebalanceListener.
//            }
//
//        }
//    }
//}
