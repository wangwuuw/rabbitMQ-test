package com.rabbit.entity;

import lombok.Data;

@Data
public class MqDeadsMessage {
    private Integer id;

    private String message;

    private String exchange;

    private String router;

    private String errorDesc;

}