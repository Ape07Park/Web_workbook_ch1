package org.zerock.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@ToString
@Service
@RequiredArgsConstructor // 필요한 매개변수를 포함해서 생성자를 만들어준다.
public class SampleService {

    // SampleService는 인터페이스를 보고 있기에 실제 객체가 인스턴스인지는 상관 x
    // 객체와 객체의 의존 관계의 실체를 몰라도 가능한 방식을 느슨한 결합이라 부름
    private final SampleDao sampleDao;
}
