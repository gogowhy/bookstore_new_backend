package book.demo.daoimpl;


import book.demo.dao.VisitCountDao;
import book.demo.entity.VisitCount;
import book.demo.repository.VisitCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Repository
public class VisitCountDaoImpl extends Thread implements VisitCountDao {
    @Autowired
    public VisitCountRepository visitCountRepository;

    @Override
    public void reset(){
        VisitCount visitCount = new VisitCount();
        visitCount.setCountname("countnumber");
        visitCount.setVisitcount(0);
        visitCountRepository.save(visitCount);
    }


    @Override
    @Transactional(isolation = READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void addcount(){
        synchronized(VisitCountDaoImpl.class) {
            VisitCount visitCount = new VisitCount();
            visitCount = visitCountRepository.findByCountname("countnumber");
            Integer  num = visitCount.getVisitcount();
            num++;
            visitCount.setVisitcount(num);
            System.out.println(num);
            visitCountRepository.save(visitCount);
        }
    }


}

