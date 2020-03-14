package book.demo.serviceimpl;

import book.demo.dao.UserDao;
import book.demo.entity.Order;
import book.demo.entity.User;
import book.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("session")
public class UserServiceImpl implements UserService {
    @Autowired
    public UserDao userDao;


    @Override
    public List<User> queryAll(){
        return userDao.queryAll();
    }


    @Override
    public void adduser(String username,  String passw,
                        String tell, Integer state)
    {
        userDao.adduser(username,passw,tell,state);
    }

    @Override
    public  void update(Integer userid){
       userDao.update(userid);
    }

    @Override
    public  void delete(Integer userid){
        userDao.delete(userid);
    }

    @Override
    public  void forbiduser(Integer userid){
        userDao.forbiduser(userid);
    }

    @Override
    public String Login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("username",username);
        return userDao.Login(request,response);
    }

    @Override
    public  Integer checkuser(HttpServletRequest request){

        return userDao.checkuser(request);
    }


    @Override
    public List<Order> tryit3(HttpServletRequest request){

        return userDao.tryit3(request);
    }


    @Override
    public String Register(HttpServletRequest request) {
       return userDao.Register(request);
    }

    @Override
    public String userdorbid(HttpServletRequest request)
    {
        return userDao.userdorbid(request);
    }
    @Override
    public String userbanlifting(HttpServletRequest request)
    {
        return userDao.userbanlifting(request);
    }
}
