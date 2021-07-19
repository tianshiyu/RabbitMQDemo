package org.dishi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailMessageDto {
    private String to;
    private String message;
    private String subject;
    private Long delayTimes;
}
