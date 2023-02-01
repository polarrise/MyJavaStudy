package com.powersi.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


/**
 * kafka生产者
 *
 * @author gaocan
 * @since 2020/9/8
 */
@Component
@Slf4j
public class KafkaProducer {

    @Value("${spring.kafka.im-topic-name}")
    private String topic;

    @Autowired
    KafkaTemplate kafkaTemplate;

    /**
     * 发送消息
     *
     * @param message
     */
    public void sendMsg(String message) {
        log.info("开始发送kafka消息，topic={}, message={}", topic, message);
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                log.info("kafka消息异步发送成功，ok={}", result);
            }

            @Override
            public void onFailure(Throwable e) {
                log.error("kafka消息发送失败：topic = {}, msg = {}", topic, message, e);
            }

        });
    }
}
