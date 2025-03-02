package org.zerock.w3.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w3.dao.MemberDao;
import org.zerock.w3.dao.TodoDao;
import org.zerock.w3.domain.MemberVo;
import org.zerock.w3.domain.TodoVo;
import org.zerock.w3.dto.MemberDto;
import org.zerock.w3.dto.TodoDto;
import org.zerock.w3.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum MemberService {

    INSTANCE;

    private MemberDao dao;
    private ModelMapper modelMapper;

    /**
     * 생성자
     */
    MemberService() {
        dao = new MemberDao();
        modelMapper = MapperUtil.INSTANCE.getModelMapper();

    }

    /**
     * 로그인 처리
     * @param mid 입력한 id
     * @param mpwd 입력한 비밀번호
     * @return memberDto
     * @throws Exception 데이터베이스에서 데이터 조회를 했는데 일치하는 회원 데이터가 없을 시
     */
    public MemberDto login(String mid, String mpwd) throws Exception {

        MemberVo memberVo = dao.findByMemberByMidAndMpw(mid, mpwd);

        if (memberVo == null) {
            throw new RuntimeException("member is null");
        }
       MemberDto memberDto = modelMapper.map(memberVo, MemberDto.class);

        return memberDto;
    }

    /**
     * 회원의 uuid 업데이트
     * @param mid 회원의 시퀀스
     * @param uuid uuid
     * @throws Exception
     */
    public void updateUuid(String mid, String uuid) throws Exception {

        dao.updateUuid(mid, uuid);
    }

    /**
     * uuid로 회원 정보 가져오기
     * @param uuid
     * @return
     * @throws Exception
     */
    public MemberDto getByUuid(String uuid) throws Exception {

        // uuid로 회원 정보 가져오기
        MemberVo vo = dao.findUuidByUuid(uuid);

        MemberDto memberDto = modelMapper.map(vo, MemberDto.class);

        return memberDto;
    }

}
