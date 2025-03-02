package org.zerock.w3.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class MemberVo {

    private String mid;
    private String mname;
    private String mpw;
}
