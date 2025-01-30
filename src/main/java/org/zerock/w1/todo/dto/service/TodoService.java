package org.zerock.w1.todo.dto.service;

import org.zerock.w1.todo.dto.TodoDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// enum 타입으로 클래스 작성 시 정해진 수(INSTANCE 개수) 만큼 객체 생성 o
public enum TodoService {
    INSTANCE; // INSTANCE 개수로 객체의 개수 결정 한 개만 있기에 하나의 객체만 생성 가능
    // 객체 사용 시 TodoService.INSTANCE로 사용함. 객체를 하나만 생성해 사용하는 걸 싱글톤 패턴이라 부름

    public void register(TodoDto todo) {
        System.out.println("DEBUG.........." + todo);
    }

    public List<TodoDto> getTodoList(){
        /* 매핑이란 어떤 값을 다른 값에 대응시키는 과정
         맵(map)은 스트림 내 요소들을 하나씩 특정 값으로 변환한다. 이 때 값을 변환하기 위한 람다를 인자로 받는다
         스트림에 들어가 있는 값이 input 이 되어서 특정 로직을 거친 후 output 이 되어 (리턴되는) 새로운 스트림에 담기게 된다. 이러한 작업을 맵핑(mapping)이라고 한다.
        */

//       0부터 9까지의 정수 스트림을 만들기 위해 IntStream.range() 메서드를 사용(생성)
//       IntStream을 일반 스트림으로 변환하기 위해 mapToObj() 메서드 사용. 일반 스트림이어야 다양한 타입을 넣을 수 있음(가공)
        List<TodoDto> todoList = IntStream.range(0, 10).mapToObj(i -> {

            // 0번 스트림 객체에 todo라는 값(객체) 넣기.
            TodoDto todo = new TodoDto();
            todo.setTno((long) i);
            todo.setTitle("Todo" + i);
            todo.setCompleted(false);
            todo.setDueDate(LocalDate.now());

            return todo; // 반환(결과)
        }).collect(Collectors.toList()); // 0~ 9 까지의 Stream -> List 변환

        return todoList;
    }

    public TodoDto getTodoDetail(Long tno) {
        TodoDto dto = new TodoDto();
        dto.setTno(tno);
        dto.setCompleted(true);
        dto.setDueDate(LocalDate.now());
        dto.setTitle("sample todo");

        return dto;
    }
}
