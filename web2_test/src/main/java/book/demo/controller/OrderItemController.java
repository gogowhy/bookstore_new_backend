package book.demo.controller;
import book.demo.repository.*;
import book.demo.entity.*;
import book.demo.controller.*;
import book.demo.service.OrderItemService;
import book.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@RestController
@Scope("singleton")
@RequestMapping("/orderitem")
public class OrderItemController
{

    //@Autowired
    //private OrderItemService orderItemService;
    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping("queryAll")
    @ResponseBody
    public List<OrderItem> queryAll(){
        OrderItemService orderItemService = applicationContext.getBean(OrderItemService.class);
        return orderItemService.queryAll();
    }

    @RequestMapping("add/{userid}/{orderid}/{bookid}/{number}")
    @ResponseBody
    public void addorderitem(@PathVariable("userid") Integer userid,@PathVariable("orderid") Integer orderid,
                             @PathVariable("bookid") Integer bookid,@PathVariable("number") Integer number)
    {
        OrderItemService orderItemService = applicationContext.getBean(OrderItemService.class);
        orderItemService.addorderitem(userid,orderid,bookid,number);
    }



}
