package book.demo.controller;


import book.demo.entity.BookRemark;
import book.demo.entity.Books;
import book.demo.service.BookRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.rmi.RemoteException;
import java.util.List;

@RestController
@Scope("singleton")
@RequestMapping("/bookremark")
public class BookRemarkController {
    //@Autowired
    //private BookRemarkService bookRemarkService;
    @Autowired
    WebApplicationContext applicationContext;



    @RequestMapping("queryAll")
    @ResponseBody
    public List<BookRemark> queryAll()throws RemoteException {
        BookRemarkService bookRemarkService = applicationContext.getBean(BookRemarkService.class);
        return bookRemarkService.queryAll();
    }



    @RequestMapping("newbookremark")
    @ResponseBody
    public String newbookremark(HttpServletRequest request)throws RemoteException
    {

        BookRemarkService bookRemarkService = applicationContext.getBean(BookRemarkService.class);
        return  bookRemarkService.newbookremark(request);
    }

    @RequestMapping("deleteAll")
    @ResponseBody
    public void deleteAll()throws RemoteException
    {
        BookRemarkService bookRemarkService = applicationContext.getBean(BookRemarkService.class);
        bookRemarkService.deleteAll();
    }

    @RequestMapping("querythebook")
    @ResponseBody
    public List<BookRemark> querythebook(HttpServletRequest request)throws RemoteException
    {
        BookRemarkService bookRemarkService = applicationContext.getBean(BookRemarkService.class);
        return bookRemarkService.querythebook(request);
    }
}
