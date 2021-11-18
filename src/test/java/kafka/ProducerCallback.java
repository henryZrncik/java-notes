package kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.Callback;

class ProducerCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println("data have been send probably");

        if (e != null) {
            System.out.println("some error occured");
            e.printStackTrace();
        }
    }
}
