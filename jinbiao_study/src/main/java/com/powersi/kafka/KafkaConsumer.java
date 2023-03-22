package com.powersi.kafka;

import com.powersi.entity.User;
import com.powersi.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * kafka消费者
 * @author gaocan
 * @since 2020/9/8
 */
@Component
@Slf4j
public class KafkaConsumer {


    /**
     * 消息监听
     * @param records
     */
    @KafkaListener(topics = {"${spring.kafka.im-topic-name}"})
    public void listen1(List<ConsumerRecord<String, Object>> records) {
        try {
            log.info("kafka消息监听：共收到{}条消息, records = {}", records.size(), records);
            if (!records.isEmpty()) {
                // IM消息批量入库
                List<User> list = new ArrayList<>();
                for (ConsumerRecord<String, Object> record : records) {
                    try {
                        String json = String.valueOf(record.value());
                        User user = GsonUtils.fromJson(json, User.class);
                        // 通过kafka offset保证消息的时序性
//                        imBaseMsg.setOffset(record.offset());
                        list.add(user);
                    } catch (Exception e) {
                        log.error("kafka消息转化异常，msg = {}", record, e);
                    }

                    System.out.println("kafka消费者收到的消息:"+list);
                }
            }
        } catch (Exception e) {
            log.error("kafka消息监听异常：", e);
        }
    }


    /**
     * @KafkaListener(groupId = "testGroup", topicPartitions = {
     *             @TopicPartition(topic = "topic1", partitions = {"0", "1"}),
     *             @TopicPartition(topic = "topic2", partitions = "0",
     *                     partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
     *     },concurrency = "6")
     *  //concurrency就是同组下的消费者个数，就是并发消费数，必须小于等于分区总数
     * @param records    演示消费异常造成的重复消费问题===
     */
    @KafkaListener(topics = "Jinbiao_topic",groupId ="jinbiaoGroup")
    public void listenJinbiaoGroup(List<ConsumerRecord<String, Object>> records){
        /**
         *  retries: 3 重试次数, 所以执行三次都没成功就不重试了--   会造成1/2/3条数据消费3次!!!
         */
        log.info("kafka消息监听主题Jinbiao_topic：共收到{}条消息, records = {}", records.size(), records);
        int count =0;
        for (ConsumerRecord<String, Object> record:records) {
            count++;
            System.out.println(record.value());
            if(count == 3){
                throw new RuntimeException();
            }
        }
        //手动提交offset
        //ack.acknowledge();
    }

    @KafkaListener(topics = "Jinbiao_topic",groupId ="jinbiaoGroup5")
    public void listenJinbiaoGroup2(List<ConsumerRecord<String, Object>> records) throws InterruptedException {
        log.info("kafka消息监听主题Jinbiao_topic：共收到{}条消息, records = {}", records.size(), records);
        int count =0;
        for (ConsumerRecord<String, Object> record:records) {
            count++;
            System.out.println(record.value());
            if(count == 3){
               Thread.sleep(10000);
            }
        }
        //手动提交offset
        //ack.acknowledge();
    }

    //配置多个消费组
    /*@KafkaListener(topics = "my-replicated-topic",groupId = "tulingGroup")
    public void listenTulingGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
        ack.acknowledge();
    }*/
}
