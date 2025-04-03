package org.zerock.springex.controller.formatter;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// LocalDate 타입으로 변환시켜주는 포맷터
public class LocalDateFormatter implements Formatter<LocalDate> {

    // 문자열과 지역 정보가 들어오면 그 지역 대의 시간에 맞게 특정한 형식으로 변환해준다.
    @Override
    public LocalDate parse(String text, Locale locale) {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // 변환된 것을 출력시켜준다
    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
    }
}
