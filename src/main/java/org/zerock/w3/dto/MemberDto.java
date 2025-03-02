package org.zerock.w3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data // getters and setters, toString, equals, hashCode 등의 메소드를 모두 생성해줌

@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private String mid;
    private String mname;
    private String mpw;

}
