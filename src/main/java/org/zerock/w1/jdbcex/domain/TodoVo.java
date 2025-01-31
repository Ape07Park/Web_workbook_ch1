package org.zerock.w1.jdbcex.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class TodoVo {

    private Long tno;
    private String title;
    private LocalDateTime dueDate;
    private boolean finished;

}
