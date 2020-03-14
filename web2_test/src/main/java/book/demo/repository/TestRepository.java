package book.demo.repository;

import book.demo.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TestRepository extends JpaRepository<Test,Integer> {

}
