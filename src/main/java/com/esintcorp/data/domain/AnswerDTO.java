package com.esintcorp.data.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerDTO {
    private Long id;
    private Long billId;
    private Long billType_id;
    private String name;
    private String type;
    private String value;
}
