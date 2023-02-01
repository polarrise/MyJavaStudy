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
}
