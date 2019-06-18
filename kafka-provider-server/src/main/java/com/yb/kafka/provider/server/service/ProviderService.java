package com.yb.kafka.provider.server.service;

import com.alibaba.fastjson.JSONObject;
import com.yb.kafka.common.custom.CustomChannel;
import com.yb.kafka.common.model.People;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author biaoyang
 */
@EnableScheduling
@AllArgsConstructor
@EnableBinding({CustomChannel.class, Processor.class})
public class ProviderService {
    private final Processor processor;
    private final CustomChannel customChannel;

    @Scheduled(fixedRate = 9000)
    public void sendMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        People people = new People(formatter.format(LocalDateTime.now()), "jack", "London");
        boolean send = customChannel.outputOne().send(MessageBuilder.withPayload(JSONObject.toJSONString(people)).build());
        System.err.println(send ? "传递成功" : "传递失败");
    }

    @Scheduled(fixedRate = 8000)
    public void sendMsg() {
        boolean send = processor.output().send(MessageBuilder.withPayload(LocalDateTime.now()).build());
        System.err.println(send ? "投送成功" : "投送失败");
    }

}
