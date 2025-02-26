package org.zerock.w3.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TodoVo {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;

}
