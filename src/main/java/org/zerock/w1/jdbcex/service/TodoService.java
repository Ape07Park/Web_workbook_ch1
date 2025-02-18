package org.zerock.w1.jdbcex.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w1.jdbcex.dao.TodoDao;
import org.zerock.w1.jdbcex.domain.TodoVo;
import org.zerock.w1.jdbcex.dto.TodoDto;
import org.zerock.w1.jdbcex.util.MapperUtil;

@Log4j2
public enum TodoService {

    INSTANCE;

    private TodoDao dao;
    private ModelMapper modelMapper;

    TodoService() {
        dao = new TodoDao();
        modelMapper = MapperUtil.INSTANCE.getModelMapper();

    }

    public void register (TodoDto todoDto) throws Exception {

        TodoVo todoVo = modelMapper.map(todoDto, TodoVo.class); // dto -> vo

//        System.out.println("todoVo :" + todoVo);
        // log 사용
        log.info("todoVo :" + todoVo);

        dao.insert(todoVo); // int를 반환하므로 이를 이용해 예외처리 가능
    }
}
