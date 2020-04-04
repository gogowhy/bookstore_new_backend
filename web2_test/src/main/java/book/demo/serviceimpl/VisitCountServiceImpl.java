package book.demo.serviceimpl;


import book.demo.dao.UserDao;
import book.demo.dao.VisitCountDao;
import book.demo.entity.VisitCount;
import book.demo.service.VisitCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("session")
public class VisitCountServiceImpl implements VisitCountService {

    @Autowired
    public VisitCountDao visitCountDao;

    @Override
    public  void reset(){

        visitCountDao.reset();
    }
    @Override
    public  void  addcount(){
        visitCountDao.addcount();
    }

}
