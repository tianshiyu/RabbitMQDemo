package org.dishi.common.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailMessage {
    private String to;
    private String message;
    private String subject;
}
