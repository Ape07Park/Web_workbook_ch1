package org.zerock.w1.jdbcex.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
public class TodoVo {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;

}
