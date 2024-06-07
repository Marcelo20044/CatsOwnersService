package com.catService.contract.exceptions.kafka;

public class SyncKafkaRequestException extends RuntimeException {
    public SyncKafkaRequestException() {
    }

    public SyncKafkaRequestException(String message) {
        super(message);
    }
}
