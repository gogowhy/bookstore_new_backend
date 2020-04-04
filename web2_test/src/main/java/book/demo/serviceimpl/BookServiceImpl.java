package book.demo.serviceimpl;


import book.demo.repository.*;
import book.demo.entity.*;
import book.demo.controller.*;
import book.demo.dao.*;
import book.demo.daoimpl.*;
import book.demo.service.*;
import book.demo.serviceimpl.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.AssociationOverride;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;


@Service
public class BookServiceImpl implements BookService {

@Autowired
    public BookDao bookDao;

            @Override
    public List<Books> queryAll() throws RemoteException
{
    return bookDao.queryAll()      ;

}

            @Override
    public  void add( String isbn,String name ,
                     Integer price,String author,
                    Integer repertory,String description) throws RemoteException{
    bookDao.add( isbn,  name,price,author,repertory, description)     ;

}
              @Override
    public  void update(Integer id)throws RemoteException
    {
         bookDao.update(id);
    }

             @Override
    public String addtocart(HttpServletRequest request)throws RemoteException
    {
             return bookDao.addtocart(request);
    }

    @Override
    public  String numberbuy(HttpServletRequest request)throws RemoteException
    {
        return bookDao.numberbuy(request);
    }
              @Override
    public String checknow(HttpServletRequest request)throws RemoteException
     {
        return bookDao.checknow(request);
     }


              @Override
    public String checkall (HttpServletRequest request)throws RemoteException
     {
         return bookDao.checkall(request);
     }

              @Override
    public String bookdelete(HttpServletRequest request)throws RemoteException
    {
        return bookDao.bookdelete(request);
    }
          @Override
    public String booknumber(HttpServletRequest request)throws RemoteException
     {
         return bookDao.booknumber(request);
     }

            @Override
    public String bookdescription(HttpServletRequest request)throws RemoteException
     {
         return  bookDao.bookdescription(request);
     }


            @Override
    public String bookadd(HttpServletRequest request)throws RemoteException
     {
         return bookDao.bookadd(request);
     }

             @Override
    public void setbookdetail(HttpServletRequest request)throws RemoteException
     {
          bookDao.setbookdetail(request);
     }


     @Override
     public void setbookdetailbyisbn(HttpServletRequest request)throws RemoteException
     {
         bookDao.setbookdetailbyisbn(request);
     }


            @Override
    public List<Books> querydetail(HttpServletRequest request)throws RemoteException
     {
         return bookDao.querydetail(request);
     }

     @Override
    public void setpicture(Integer id,String picture)throws RemoteException
     {
      bookDao.setpicture(id,picture);
     }

    @Override
    public String querythebookpicture(HttpServletRequest request)throws RemoteException
    {
        return bookDao.querythebookpicture(request);
    }

    @Override
    public String rmiquery(String bookname)throws RemoteException
    {
        return bookDao.rmiquery(bookname);
    }

    @Override
    public Books redisquery(Integer bookid)
    {
        return  bookDao.redisquery(bookid);
    }


}
