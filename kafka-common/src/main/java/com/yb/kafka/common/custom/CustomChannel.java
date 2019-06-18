package com.yb.kafka.common.custom;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义的通道接口类
 *
 * @author biaoyang
 */
public interface CustomChannel {

    /**
     * 输入通道
     */
    String INPUT_MSG = "input_msg";

    /**
     * 输出通道
     */
    String OUTPUT_MSG = "output_msg";

    /**
     * 输入通道one
     *
     * @return
     */
    @Input(INPUT_MSG)
    SubscribableChannel inputOne();

    /**
     * 输出通道one
     *
     * @return
     */
    @Output(OUTPUT_MSG)
    MessageChannel outputOne();

}
