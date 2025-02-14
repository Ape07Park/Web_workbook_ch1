package org.zerock.w1.jdbcex.service;

import org.modelmapper.ModelMapper;
import org.zerock.w1.jdbcex.dao.TodoDao;
import org.zerock.w1.jdbcex.domain.TodoVo;
import org.zerock.w1.jdbcex.dto.TodoDto;
import org.zerock.w1.jdbcex.util.MapperUtil;

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

        System.out.println("todoVo :" + todoVo);

        dao.insert(todoVo); // int를 반환하므로 이를 이용해 예외처리 가능
    }
}
