package com.yb.kafka.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author biaoyang
 */
@Getter
@Setter
@ApiModel(description = "人的信息实体类")
public class People implements Serializable {
    private static final long serialVersionUID = 2037716522653150155L;

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("姓名")
    private String fullName;

    @ApiModelProperty("住址")
    private String address;

    public People(String id, String fullName, String address) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
    }
}
