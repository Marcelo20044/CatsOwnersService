package com.catService.kafka.services;

import com.catService.contract.exceptions.kafka.SyncKafkaRequestException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@RequestScope
public class KafkaService {
    private final ReplyingKafkaTemplate<String, Object, Object> replyingKafka;
    private final KafkaTemplate<String, Object> kafka;

    public <T> T sendSync(Object request, String topic, Class<T> awaitedType) {
        ConsumerRecord<String, Object> consumerRecord;
        try {
            ProducerRecord<String, Object> record = new ProducerRecord<>(topic, request);
            RequestReplyFuture<String, Object, Object> replyFuture = replyingKafka.sendAndReceive(record);
            SendResult<String, Object> sendResult = replyFuture.getSendFuture().get();
            consumerRecord = replyFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new SyncKafkaRequestException("Что то пошло не так во время запроса: " + e.getMessage());
        }

        Object reply = consumerRecord.value();

        try {
            return awaitedType.cast(reply);
        } catch (ClassCastException e) {
            throw new SyncKafkaRequestException(String.format("Ожидался тип %s, но вернулся %s", awaitedType, reply.getClass()));
        }
    }

    public void sendAsync(Object request, String topic) {
        kafka.send(topic, request);
    }
}
