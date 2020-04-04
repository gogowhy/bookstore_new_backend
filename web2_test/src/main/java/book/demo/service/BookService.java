package book.demo.service;


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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;


@Service
public interface BookService extends Remote {

    List<Books> queryAll() throws RemoteException;
    void add(String isbn,String name ,Integer price,String author,
             Integer repertory,String description) throws RemoteException;
    void update(Integer id) throws RemoteException;
    String addtocart(HttpServletRequest request) throws RemoteException;
    String numberbuy(HttpServletRequest request) throws RemoteException;
    String checknow(HttpServletRequest request) throws RemoteException;
    String checkall (HttpServletRequest request) throws RemoteException;
    String bookdelete(HttpServletRequest request)throws RemoteException;
    String booknumber(HttpServletRequest request)throws RemoteException;
    String bookdescription(HttpServletRequest request)throws RemoteException;
    String bookadd(HttpServletRequest request)throws RemoteException;
    void setbookdetail(HttpServletRequest request)throws RemoteException;
    void setbookdetailbyisbn(HttpServletRequest request)throws RemoteException;
    List<Books> querydetail(HttpServletRequest request)throws RemoteException;
    void setpicture(Integer id,String picture)throws RemoteException;
    String querythebookpicture (HttpServletRequest request)throws RemoteException;
    String rmiquery(String bookname)throws RemoteException;
    Books redisquery(Integer bookid);
}

