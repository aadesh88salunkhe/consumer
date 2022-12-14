package com.ignite.consumer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsumerException extends Exception {
    private final int code;
    private final String message;
}
