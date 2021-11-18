package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producers {
    @Test void produceMessage(){
        // properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // created producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>("CustomerCountry", "Laboratory Equipment", "USA");
        // create record
        for (int i = 0; i < 1000; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("my-topic111",   "mojeData" + i );
            kafkaProducer.send(producerRecord, new ProducerCallback() );
            kafkaProducer.flush();
        }

        // Because sending data may stay
        kafkaProducer.close();
    }
}
