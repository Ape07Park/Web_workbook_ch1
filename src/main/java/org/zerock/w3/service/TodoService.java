package org.zerock.w3.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w3.dao.TodoDao;
import org.zerock.w3.domain.TodoVo;
import org.zerock.w3.dto.TodoDto;
import org.zerock.w3.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {

    INSTANCE;

    private TodoDao dao;
    private ModelMapper modelMapper;

    /**
     * 생성자
     */
    TodoService() {
        dao = new TodoDao();
        modelMapper = MapperUtil.INSTANCE.getModelMapper();

    }

    /**
     * 등록
     * @param todoDto
     * @throws Exception
     */
    public void register (TodoDto todoDto) throws Exception {

        TodoVo todoVo = modelMapper.map(todoDto, TodoVo.class); // dto -> vo

//        System.out.println("todoVo :" + todoVo);
        // log 사용
        log.info("todoVo :" + todoVo);

        dao.insert(todoVo); // int를 반환하므로 이를 이용해 예외처리 가능
    }

    /**
     * 목록
     * @return
     * @throws Exception
     */
    public List<TodoDto> listAll() throws Exception {

        List<TodoVo> todoVoList = dao.selectAll();

        log.info("todoVoList: " + todoVoList);

        List<TodoDto> todoDtoList = todoVoList.stream().
                map(todoVo -> modelMapper.map(todoVo, TodoDto.class))
               .collect(Collectors.toList());

        return todoDtoList;
    }

    /**
     * 조회
     * @param id
     * @return
     * @throws Exception
     * */
    public TodoDto get(Long id) throws Exception {

        TodoVo todoVo = dao.selectOne(id);

        if (todoVo == null) {
            return null;
        }
        TodoDto todoDto = modelMapper.map(todoVo, TodoDto.class);

        return todoDto;

    }

    /**
     * 삭제
     * @param tno
     * @throws Exception
     */
    public void remove(Long tno) throws Exception {

        log.info("tno :" + tno);
        dao.delete(tno);
    }

    /**
     * 삭제
     * @param dto
     * @throws Exception
     */
    public void update(TodoDto dto) throws Exception {

        log.info("update - dto :" + dto);

        TodoVo vo = modelMapper.map(dto, TodoVo.class);

        dao.update(vo);

    }
}
