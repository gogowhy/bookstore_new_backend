import book.demo.repository.orderkafkaRepository;
import book.demo.service.BookService;
import org.apache.catalina.core.ApplicationContext;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import book.demo.dao.OrderDao;

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import book.demo.repository.orderkafkaRepository;
import book.demo.entity.orderkafka;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class KafkaCon {


    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test"));


        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1));
            for (ConsumerRecord<String, String> record : records) {
                //System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                System.out.printf(record.value());
                String str = record.value();
                String str1 = str.substring(9);
                //System.out.println(str1);
                String[] strsplit = str1.split(", ");

                String userid = strsplit[0];
                Integer userid_int = Integer.parseInt(userid);

                System.out.println(userid);
                String bookname = strsplit[1];


                System.out.println(bookname);
                String booknumber = strsplit[2];
                String booknumber1 = booknumber.substring(0, booknumber.length() - 1);
                Integer booknumber_int = Integer.parseInt(booknumber1);
                System.out.println(booknumber);

                orderkafka ordk = new orderkafka();
                ordk.setUserid(userid_int);
                ordk.setBookname(bookname);
                ordk.setBooknumber(booknumber_int);

                String filenm = "/Users/wang/Desktop/web2_test/kafka_base/kafkaorder.txt";
                RandomAccessFile randomFile = new RandomAccessFile(filenm,"rw");

                long fileLength = randomFile.length();
                randomFile.seek(fileLength);
                randomFile.writeBytes(str1.substring(0,str1.length()-1)+"\n");
                randomFile.close();

            }
        }
    }
}
