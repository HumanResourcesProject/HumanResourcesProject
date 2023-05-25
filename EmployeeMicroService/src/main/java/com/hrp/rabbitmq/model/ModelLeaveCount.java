package com.hrp.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModelLeaveCount implements Serializable {
    private Long leaveCount;
}
