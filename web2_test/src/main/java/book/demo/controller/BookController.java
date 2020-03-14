package book.demo.controller;


import book.demo.repository.*;
import book.demo.entity.*;
import book.demo.controller.*;


import book.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;
@RestController
@Scope("singleton")
@RequestMapping("/books")
public class BookController{

    //@Autowired
    //private BookService bookService;
    @Autowired
    WebApplicationContext applicationContext;



    @RequestMapping("queryAll")
    @ResponseBody
    public List<Books> queryAll() throws RemoteException {
        BookService bookService = applicationContext.getBean(BookService.class);
       return bookService.queryAll();
    }

    @RequestMapping("add/{isbn}/{name}/{price}/{author}/{repertory}/{description}")
    @ResponseBody
    public  void add(@PathVariable("isbn") String isbn, @PathVariable("name")String name ,
                     @PathVariable("price") Integer price,@PathVariable("author")String author,
                     @PathVariable("repertory") Integer repertory,@PathVariable("description") String description)throws RemoteException{
        BookService bookService = applicationContext.getBean(BookService.class);
         bookService.add(isbn,name,price,author,repertory,description);
    }

    @RequestMapping("update/{bookid}/sell")
    @ResponseBody
    public  void update(@PathVariable("bookid")Integer id)throws RemoteException{
        BookService bookService = applicationContext.getBean(BookService.class);
        bookService.update(id);
    }

    @RequestMapping("addtocart")
    @ResponseBody
    public String addtocart(HttpServletRequest request)throws RemoteException
    {
        BookService bookService = applicationContext.getBean(BookService.class);
        return bookService.addtocart(request);
    }


    @RequestMapping("numberbuy")
    @ResponseBody
    public String numberbuy(HttpServletRequest request)throws RemoteException
    {
        BookService bookService = applicationContext.getBean(BookService.class);
        return bookService.numberbuy(request);
    }

    @RequestMapping("checknow")
    @ResponseBody
    public String checknow(HttpServletRequest request)throws RemoteException
    {
        BookService bookService = applicationContext.getBean(BookService.class);
       return bookService.checknow(request);

    }

    @RequestMapping("checkall")
    @ResponseBody
    public String checkall (HttpServletRequest request)throws RemoteException
    {
        BookService bookService = applicationContext.getBean(BookService.class);
        return bookService.checkall(request);
    }


    @RequestMapping("bookdelete")
    @ResponseBody
    public String bookdelete(HttpServletRequest request)throws RemoteException
    {
        BookService bookService = applicationContext.getBean(BookService.class);
        return bookService.bookdelete(request);
    }


    @RequestMapping("booknumber")
    @ResponseBody
    public String booknumber(HttpServletRequest request)throws RemoteException
    {
        BookService bookService = applicationContext.getBean(BookService.class);
        return bookService.booknumber(request);
    }

    @RequestMapping("bookdescription")
    @ResponseBody
    public String bookdescription(HttpServletRequest request)throws RemoteException
    {
        BookService bookService = applicationContext.getBean(BookService.class);
        return  bookService.bookdescription(request);
    }

    @RequestMapping("bookadd")
    @ResponseBody
    public String bookadd(HttpServletRequest request)throws RemoteException
    {

        BookService bookService = applicationContext.getBean(BookService.class);
        return bookService.bookadd(request);
    }


    @RequestMapping("setbookdetail")
    @ResponseBody
    public void setbookdetail(HttpServletRequest request)throws RemoteException

    {
        BookService bookService = applicationContext.getBean(BookService.class);
         bookService.setbookdetail(request);
    }


    @RequestMapping("setbookdetailbyisbn")
    @ResponseBody
    public void setbookdetailbyisbn(HttpServletRequest request)throws RemoteException
    {
        BookService bookService = applicationContext.getBean(BookService.class);
        bookService.setbookdetailbyisbn(request);
    }


    @RequestMapping("querydetail")
    @ResponseBody
    public List<Books> querydetail(HttpServletRequest request)throws RemoteException
    {
        BookService bookService = applicationContext.getBean(BookService.class);
        return bookService.querydetail(request);
    }


    @RequestMapping("setpicture/{bookid}/{picture}")
    @ResponseBody
    public void setpicture(@PathVariable("bookid")Integer id,@PathVariable("picture") String picture)throws RemoteException
    {
        BookService bookService = applicationContext.getBean(BookService.class);
         bookService.setpicture(id,picture);
    }

    @RequestMapping("querythebookpicture")
    @ResponseBody
    public String querythebookpicture(HttpServletRequest request)throws RemoteException
    {
        BookService bookService = applicationContext.getBean(BookService.class);
        return bookService.querythebookpicture(request);
    }
}
