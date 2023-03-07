package com.powersi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class IMApplicationConfig {

    @Value("${im.server.port}")
    private int serverPort;

    @Value("${im.server.allIdleTimeSeconds}")
    private int allIdleTimeSeconds;

    @Value("${im.server.readerIdleTimeSeconds}")
    private int readerIdleTimeSeconds;

    @Value("${im.server.port}")
    private int writerIdleTimeSeconds;
}
