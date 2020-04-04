package book.demo.service;


import org.springframework.stereotype.Service;

@Service

public interface VisitCountService {

    void reset();
    void  addcount();
}
