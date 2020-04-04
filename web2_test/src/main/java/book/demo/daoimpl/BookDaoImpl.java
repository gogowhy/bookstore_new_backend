package book.demo.daoimpl;



import book.demo.repository.*;
import book.demo.entity.*;
import book.demo.controller.*;
import book.demo.dao.*;
import book.demo.daoimpl.*;
import book.demo.service.*;
import book.demo.serviceimpl.*;

import com.alibaba.fastjson.JSONArray;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.util.*;

import org.springframework.stereotype.Repository;
import book.demo.Util.RedisUtil;

import java.text.SimpleDateFormat;


@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    RedisUtil redisUtil;


@Override
    public List<Books> queryAll(){
        List<Books> list = new ArrayList<Books>();
        list = bookRepository.findAll();
        return list;
    }

    @Override
    public  void add( String isbn, String name ,
                    Integer price,String author,
                     Integer repertory, String description){
        Books book = new Books();
        book.setIsbn(isbn);
        book.setName(name);
        book.setPrice(price);
        book.setAuthor(author);
        book.setRepertory(repertory);
        book.setDescription(description);
        bookRepository.save(book);
    }


    @Override
    public  void update(Integer id){
        Books book = bookRepository.findById(id).get();

        Integer n=book.getRepertory();
        book.setRepertory(n-1);

        bookRepository.save(book);
    }

    @Override
    public String addtocart(HttpServletRequest request)
    {
        String bookname =request.getParameter("bookname");
        String thenumber=request.getParameter("number");
        Integer number=Integer.valueOf(thenumber);

        Books book=bookRepository.findByBookname(bookname);
       /* Order neworder=new Order();
         neworder.setPaid(0);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createdate = sdf.format(date);
        neworder.setOrdertime(createdate);


        ServletContext servletContext=request.getServletContext();
        String title = servletContext.getAttribute("username").toString();
        Integer userid=userRepository.findByUsername(title).getUserid();
        neworder.setUserid(userid);


        orderRepository.save(neworder);
        Integer orderid=neworder.getOrderid();*/

        /*OrderItem orderItem =new OrderItem();
        Integer bookid =book.getId();
        orderItem.setNumber(number);
        orderItem.setBookid(bookid);
        orderItem.setOrderid(orderid);
        orderItem.setUserid(userid);
        orderItemRepository.save(orderItem);*/
        ServletContext servletContext=request.getServletContext();
        String title = servletContext.getAttribute("username").toString();
        Integer userid=userRepository.findByUsername(title).getUserid();
        Cart cart=new Cart();
        Integer bookid=book.getId();
        cart.setNumber(number);
        cart.setBookid(bookid);
        cart.setUserid(userid);
        cart.setPaid(0);
        cartRepository.save(cart);




        return"添加成功！";
    }



    @Override
    public String numberbuy(HttpServletRequest request)
    {
        String thenumber=request.getParameter("number");
        Integer number=Integer.valueOf(thenumber);
        ServletContext servletContext=request.getServletContext();
        String bookname = servletContext.getAttribute("bookname").toString();

        Books book=bookRepository.findByBookname(bookname);

        String title = servletContext.getAttribute("username").toString();
        Integer userid=userRepository.findByUsername(title).getUserid();
        Cart cart=new Cart();
        Integer bookid=book.getId();
        cart.setNumber(number);
        cart.setBookid(bookid);
        cart.setUserid(userid);
        cart.setPaid(0);
        cartRepository.save(cart);




        return"添加成功！";
    }

    @Override
    public String checknow(HttpServletRequest request)
    {
        /*String theorderid=request.getParameter("orderid");
        Integer orderid=Integer.valueOf(theorderid);


        Order order=orderRepository.findByOrderid(orderid);
        List<OrderItem> orderItems =orderItemRepository.findByOrderid(orderid);
        for(Integer i=0;i<orderItems.size();i++)
        {

            Books book =bookRepository.findByBookid(orderItems.get(i).getBookid());
            Integer number=orderItems.get(i).getNumber();
            Integer repertory=book.getRepertory()-number;
            book.setRepertory(repertory);
            bookRepository.save(book);
        }
        order.setPaid(1);

        orderRepository.save(order);*/

        String thecartid=request.getParameter("cartid");
        Integer cartid=Integer.valueOf(thecartid);

        Cart cart=cartRepository.findByCartid(cartid);
        Books book =bookRepository.findByBookid(cart.bookid);
        Integer number=cart.number;
        Integer repertory=book.getRepertory()-number;
        book.setRepertory(repertory);
        bookRepository.save(book);

        Order order =new Order();
        OrderItem orderItem=new OrderItem();

        ServletContext servletContext=request.getServletContext();
        String title = servletContext.getAttribute("username").toString();
        Integer userid=userRepository.findByUsername(title).getUserid();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createdate = sdf.format(date);
        cart.setPaid(1);
        order.setPaid(1);
        order.setUserid(userid);
        order.setOrdertime(createdate);
        orderRepository.save(order);


        orderItem.setBookid(cart.bookid);
        orderItem.setNumber(cart.number);
        orderItem.setOrderid(order.orderid);
        orderItem.setUserid(userid);
        orderItemRepository.save(orderItem);


        return"结账成功！！！";

    }

    @Override
    public String checkall (HttpServletRequest request)
    {
        ServletContext servletContext=request.getServletContext();
        String username = servletContext.getAttribute("username").toString();
        Integer userid =userRepository.findByUsername(username).getUserid();
        List<Cart> cart=cartRepository.findByUserid(userid);

        Order order =new Order();


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createdate = sdf.format(date);

        order.setPaid(1);
        order.setUserid(userid);
        order.setOrdertime(createdate);
        orderRepository.save(order);
        for(int i=0;i<cart.size();i++)
        {
            if(cart.get(i).paid==0&&cart.get(i).userid==userid)
            {
                Books book =bookRepository.findByBookid(cart.get(i).bookid);
                Integer number=cart.get(i).number;
                Integer repertory=book.getRepertory()-number;
                book.setRepertory(repertory);
                bookRepository.save(book);


                OrderItem orderItem=new OrderItem();


                cart.get(i).setPaid(1);
                orderItem.setBookid(cart.get(i).bookid);
                orderItem.setNumber(cart.get(i).number);
                orderItem.setOrderid(order.orderid);
                orderItem.setUserid(userid);
                orderItemRepository.save(orderItem);
            }
        }
        /*List <Order> user_cart=orderRepository.findByUserid(userid);
        for(Integer i=0;i<user_cart.size();i++)
        {
            List<OrderItem> orderItems =orderItemRepository.findByOrderid(user_cart.get(i).getOrderid());
            for(Integer j=0;j<orderItems.size();j++)
            {
                if(user_cart.get(i).getPaid()==0)
                {

                    Books book = bookRepository.findByBookid(orderItems.get(j).getBookid());

                    Integer number = orderItems.get(j).getNumber();

                    Integer repertory = book.getRepertory() - number;
                    book.setRepertory(repertory);
                    bookRepository.save(book);
                }
            }
            user_cart.get(i).setPaid(1);
            orderRepository.save(user_cart.get(i));
        }*/

       /* List<Cart> user_cart =cartRepository.findByUserid(userid);
        Order neworder =new Order();
        orderRepository.save(neworder);
        for(int i=0;i<user_cart.size();i++)
        {
            user_cart.get(i).setPaid(1);
            OrderItem orderItem=new OrderItem();
            orderItem.setUserid(user_cart.get(i).userid);
            orderItem.setOrderid(neworder.orderid);
            orderItem.setNumber(user_cart.get(i).number);
            orderItem.setBookid(user_cart.get(i).bookid);
            orderItemRepository.save(orderItem);
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createdate = sdf.format(date);
        neworder.setOrdertime(createdate);
        neworder.setUserid(userid);
        neworder.setPaid(1);
        orderRepository.save(neworder);*/



        return "已经为所有商品结账";
    }


    @Override
    public String bookdelete(HttpServletRequest request)
    {
        String bookname=request.getParameter("bookname");
        Books book=bookRepository.findByBookname(bookname);
        bookRepository.delete(book);
        return "已删除"+bookname;
    }


    @Override
    public String booknumber(HttpServletRequest request)
    {
        String bookname=request.getParameter("bookname");
        String repertory_toset=request.getParameter("booknumber");
        Integer number=Integer.valueOf(repertory_toset);
        Books book=bookRepository.findByBookname(bookname);
        book.setRepertory(number);
        bookRepository.save(book);
        return  "已设置"+bookname+"的库存为"+number+"!" ;
    }

    @Override
    public String bookdescription(HttpServletRequest request)
    {
        String bookname=request.getParameter("bookname");
        String description_toset=request.getParameter("bookdescription");

        Books book=bookRepository.findByBookname(bookname);
        book.setDescription(description_toset);
        bookRepository.save(book);
        return  "已修改"+bookname+"的description!" ;
    }

    @Override
    public String bookadd(HttpServletRequest request)
    {
        String isbn=request.getParameter("isbn");
        String name=request.getParameter("bookname");
        String price=request.getParameter("price");
        String author=request.getParameter("author");
        String repertory=request.getParameter("repertory");
        String description=request.getParameter("description");
        String picture=request.getParameter("picture");

        Integer price_number=Integer.valueOf(price);
        Integer repertory_number=Integer.valueOf(repertory);

        Books book =new Books();
        book.setIsbn(isbn);
        book.setName(name);
        book.setPrice(price_number);
        book.setAuthor(author);
        book.setRepertory(repertory_number);
        book.setDescription(description);
        book.setPicture(picture);

        bookRepository.save(book);
        return  "已新增图书"+name+"!" ;
    }


    @Override
    public void setbookdetail(HttpServletRequest request)
    {
        String bookname=request.getParameter("bookname");
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("bookname", bookname);
    }

    @Override
    public  void setbookdetailbyisbn(HttpServletRequest request)
    {
        String isbn=request.getParameter("isbn");
        String bookname=bookRepository.findByIsbn(isbn).getName();
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("bookname", bookname);
    }




    public List<Books> querydetail(HttpServletRequest request)
    {
        ServletContext servletContext=request.getServletContext();
        String bookname = servletContext.getAttribute("bookname").toString();
        List<Books> list = new ArrayList<Books>();
        Books book=new Books();
        book = bookRepository.findByBookname(bookname);
        list.add(book);
        return list;
    }

public void setpicture(Integer id,String picture)
{
    Books book =new Books();
    book=bookRepository.findByBookid(id);
    book.setPicture(picture);
    bookRepository.save(book);
}

public String querythebookpicture(HttpServletRequest request)
{
    ServletContext servletContext=request.getServletContext();
    String bookname = servletContext.getAttribute("bookname").toString();
    Books book=bookRepository.findByBookname(bookname);
    String picture_link=book.getPicture();

    return picture_link;
}

@Override
    public String rmiquery(String bookname)
{
    Books book = bookRepository.findByBookname(bookname);
    String description = book.getDescription();
    return  description;
}

@Override
    public Books redisquery(Integer bookid)
{
    Books books = null;
    System.out.println("Searching the book:"+ bookid+ "in Redis");
    Object p = redisUtil.get("book"+bookid);
    if(p==null)
    {
        System.out.println("Book:"+ bookid +"is not in Redis");
        System.out.println("Searching Book: " + bookid + " in DB");
        books = bookRepository.findByBookid(bookid);
        redisUtil.set("book"+bookid, JSONArray.toJSON(books));
    }
    else
    {
        books = JSONArray.parseObject(p.toString(), Books.class);
        System.out.println("Book:"+bookid+"is in Redis");
    }
    return books;

}

@Override
public void solradd(String isbn,String name ,Integer price,String author,
                    Integer repertory,String description)
{
    try{
        final SolrClient client = getSolrClient();
        final Books books = new Books();
        books.setIsbn(isbn);
        books.setName(name);
        books.setPrice(price);
        books.setAuthor(author);
        books.setRepertory(repertory);
        books.setDescription(description);


        final UpdateResponse response = client.addBean("book2",books);
        client.commit("book2");
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    System.out.println("save ok");
}
@Override
    public void solrquery(Integer bookisbn)
{
    try{
        final SolrClient client = getSolrClient();


        final Map<String, String> queryParamMap = new HashMap<String, String>();
        queryParamMap.put("q", "isbn:"+bookisbn);
        queryParamMap.put("fl", "name,description");
        queryParamMap.put("sort", "isbn asc");
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);

        final QueryResponse response = client.query("book2", queryParams);
        final SolrDocumentList documents = response.getResults();

        System.out.println("Found " + documents.getNumFound() + " documents");
        for (SolrDocument document : documents) {
            final String isbn = (String) document.getFirstValue("isbn");
            final String name = (String) document.getFirstValue("name");
            final String description = (String) document.getFirstValue("description");

            System.out.println("isbn: " + bookisbn + "; name: " +name+"; description: "+description);

        }
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }

}



    public static SolrClient getSolrClient() {
        final String solrUrl = "http://localhost:8983/solr";
        return new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }


}
