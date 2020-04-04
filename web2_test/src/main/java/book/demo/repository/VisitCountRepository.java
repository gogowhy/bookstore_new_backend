package book.demo.repository;


import book.demo.entity.User;
import book.demo.entity.VisitCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface VisitCountRepository extends JpaRepository<VisitCount,Integer> {
public VisitCount findByCountname(String name);
}
