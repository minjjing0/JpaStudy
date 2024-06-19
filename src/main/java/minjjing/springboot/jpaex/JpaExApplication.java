package minjjing.springboot.jpaex;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JpaExApplication implements CommandLineRunner {

    @Autowired
    EntityManagerFactory emf;

    public static void main(String[] args) {

        SpringApplication.run(JpaExApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        User user = new User();
        user.setId("aaa");
        user.setPassword("1234");
        user.setName("LEe");
        user.setEmail("aaa@aa.com");
        user.setInDate(new Date());
        user.setUpDate(new Date());

        tx.begin(); //트랜잭션 시작
        //1.저장
        em.persist(user); //user 엔티티를 em에 영속화(저장)
        em.persist(user); //같은 user 엔티티를 여러번 저장해도 한번만 insert
        //2.변경
        user.setPassword("4321"); //Persistence Context가 변경 감지 updqte
        user.setPassword("bbb@bbb.com");
        tx.commit(); //트랜잭션 종료 (DB에 반영)

        //3. 조회
        User user2 = em.find(User.class,"aaa"); //em 에 있으면 DB 조회 안함
        System.out.println("user==user2="+(user==user2));
        User user3 = em.find(User.class,"bbb"); //em에 없으면 DB 조회
        System.out.println("user3="+user3); //null , DB에 없음

        //4. 삭제
        tx.begin(); //위의 트랜잭션과 별개의 트랜잭션
        em.remove(user); //em 에서 user 엔티티 삭제
        tx.commit();
    }
}
