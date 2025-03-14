package org.zerock.springex.mapper;

import org.apache.ibatis.annotations.Select;

// 메퍼 인터페이스: root-context.xml에 등록이 필요하다
// mybatis와 스프링을 연동하고 매퍼 인터페이스를 이용하면 클래스와 객체를 개발자가 생성하지 않고 스프링에서 자동생성 해준다.
// 그렇기에 수정할 수 없다는 단점이 있지만 인터페이스만으로도 개발이 가능하다.
public interface TimeMapper {

    @Select("select now()") // TimeMapper는 db의 현재 시간을 문자열로 처리
    String getTime();


}
