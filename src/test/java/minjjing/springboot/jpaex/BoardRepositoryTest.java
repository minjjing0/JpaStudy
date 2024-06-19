package minjjing.springboot.jpaex;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;


    @Test
    @Order(4)
    public void deleteTest(){
        boardRepository.deleteById(1L);
        // 못찾으면 null 반환
        Board board = boardRepository.findById(1L).orElse(null);
        assertTrue(board == null); // 삭제 되었으니 null이어야함
    }

    @Test
    @Order(3)
    public void updateTest(){
        Board board = boardRepository.findById(1L).orElse(null);
        assertTrue(board != null);

        board.setTitle("modified Title");
        boardRepository.save(board);
        Board board2 = boardRepository.findById(1L).orElse(new Board());

        assertTrue(board.getTitle().equals("modified Title"));
    }


    @Test
    @Order(2)
    public void selectTest(){
        //Board board = boardRepository.findById(1L).get(); //값이 없을 때 예외 발생
        Board board = boardRepository.findById(1L).orElse(null); //값이 없을 때 null
        System.out.println("board"+board);
        assertTrue(board != null);
    }


    @Test
    @Order(1)
    public  void insertTest(){
        Board board = new Board();
        board.setBno(1L);
        board.setTitle("test Title");
        board.setContent("This is a test");
        board.setWriter("aaa");
        board.setViewCnt(0L);
        board.setInDate(new Date());
        board.setUpDate(new Date());
        boardRepository.save(board);
    }
}