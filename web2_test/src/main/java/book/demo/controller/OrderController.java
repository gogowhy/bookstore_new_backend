package book.demo.controller;
import book.demo.repository.*;
import book.demo.entity.*;
import book.demo.controller.*;
import book.demo.service.OrderService;
import javafx.geometry.InsetsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@Scope("singleton")
@RequestMapping("/order")

public class OrderController
{

    //@Autowired
    //private OrderService orderService;
    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping("queryAll")
    @ResponseBody
    public List<Order> queryAll(){
        OrderService orderService = applicationContext.getBean(OrderService.class);
       return orderService.queryAll();


    }

    @RequestMapping("add/{userid}/{paid}/{ordertime}")
    @ResponseBody
    public  void add(@PathVariable("userid")Integer userid,@PathVariable("paid") Integer paid,
                     @PathVariable("ordertime" )String ordertime)
    {
        OrderService orderService = applicationContext.getBean(OrderService.class);
         orderService.add(userid,paid,ordertime);
    }



    @RequestMapping("paid/{orderid}")
    @ResponseBody
    public  void update(@PathVariable("orderid") Integer orderid){

        OrderService orderService = applicationContext.getBean(OrderService.class);
        orderService.update(orderid);
    }


    @RequestMapping(value = "/findorderid/{orderid}")
    public Order getStu( @PathVariable("orderid") Integer orderid)
    {
        OrderService orderService = applicationContext.getBean(OrderService.class);

        return orderService.getStu(orderid);
    }

    @RequestMapping(value = "/findorderuserid/{orderuserid}")
    public Order getOrd( @PathVariable("orderuserid") Integer orderuserid){
        OrderService orderService = applicationContext.getBean(OrderService.class);

        return orderService.getOrd(orderuserid);
    }



    @RequestMapping("/queryorder")
    @ResponseBody
    public  List<order_out_structure> queryorder(HttpServletRequest request)
    {
        OrderService orderService = applicationContext.getBean(OrderService.class);
        return orderService.queryorder(request);


    }

    @RequestMapping("/querycart")
    @ResponseBody
    public  List<order_out_structure> querycart(HttpServletRequest request)
    {
        OrderService orderService = applicationContext.getBean(OrderService.class);
       return orderService.querycart(request);

    }



    @RequestMapping("/query_all_order")
    @ResponseBody
    public  List<order_out_structure> queryallorder(HttpServletRequest request)
    {
        OrderService orderService = applicationContext.getBean(OrderService.class);
       return orderService.queryallorder(request);

    }

@RequestMapping("booksales")
@ResponseBody
    public String booksales(HttpServletRequest request) {

    OrderService orderService = applicationContext.getBean(OrderService.class);
        return orderService.booksales(request);
}


    @RequestMapping("timesalesall")
    @ResponseBody
    public String timesalesall(HttpServletRequest request) {
        OrderService orderService = applicationContext.getBean(OrderService.class);

        return orderService.timesalesall(request);
    }

    @RequestMapping("allbooksalestimes")
    @ResponseBody
    public String allbooksalestimes(HttpServletRequest request)
    {
        OrderService orderService = applicationContext.getBean(OrderService.class);

        return orderService.allbooksalestimes(request);
    }

    @RequestMapping("custimebuy")
    @ResponseBody
    public String custimebuy(HttpServletRequest request)
    {


        OrderService orderService = applicationContext.getBean(OrderService.class);
        return orderService.custimebuy(request);
    }

    @RequestMapping("orderkafka/{userid}/{bookname}/{booknumber}")
    public String orderkafka(@PathVariable("userid") Integer userid, @PathVariable("bookname") String bookname, @PathVariable("booknumber") Integer booknumber)throws Exception{
        OrderService orderService = applicationContext.getBean(OrderService.class);

        return orderService.orderkafka(userid,bookname,booknumber);
    }


}