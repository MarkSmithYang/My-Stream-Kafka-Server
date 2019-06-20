package com.yb.kafka.consumer.one.service;

import com.yb.kafka.common.custom.CustomChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * 可通过配置group的方式来配置消费服务启动之前为消费的消息,不配置则之前消息无法消费(丢失)
 * 这个@EnableBinding注解实测是必不可少的,否则无法消费
 *
 * @author biaoyang
 */
@EnableBinding({CustomChannel.class, Processor.class})
public class ConsumerService {

    /**
     * 消费自定义的输出通道(配置有对应的同主题输入通道的发送消息)的信息
     * @param message
     */
    @StreamListener(CustomChannel.INPUT_MSG)
    public void getMessage(@Payload String message) {
        System.err.println("消费者One消费=="+message);
    }

    /**
     * 注解埋点的操作日志推送消息消费
     * @param message
     */
    @StreamListener(Processor.INPUT)
    public void getLogMessage(@Payload String message) {
        System.err.println("操作日志被One消费=="+message);
    }

}
