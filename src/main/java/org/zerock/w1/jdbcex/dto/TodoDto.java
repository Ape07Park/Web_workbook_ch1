package org.zerock.w1.jdbcex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data // getters and setters, toString, equals, hashCode 등의 메소드를 모두 생성해줌

// equals는 원래는 비교할 대상이 객체일 경우 객체의 주소를 이용.
// 그러나 객체의 필드값을 기준으로 동등 비교 기준을 변경하고 싶다면 equals 메서드를 오버라이딩해서 주소가 아닌 필드값을 비교하도록 재정의

@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Long tno;

    private String title;

    private LocalDate dueDate;

    private boolean finished;

}
