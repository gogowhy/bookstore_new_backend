package book.demo.controller;
import book.demo.entity.*;
import book.demo.service.UserService;
import book.demo.repository.*;
import book.demo.controller.*;



import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.print.DocFlavor;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Scope("singleton")
@RequestMapping("/user")

public class UserController{
    //@Autowired
   // public UserService userService;

    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping("queryAll")
    @ResponseBody
    public List<User> queryAll(){
        UserService userService = applicationContext.getBean(UserService.class);
        System.out.println(userService);
        System.out.println(this);
        return userService.queryAll();
    }



    @RequestMapping("add/{username}/{passw}/{tell}/{state}")
    @ResponseBody
    public void adduser(@PathVariable("username")String username,@PathVariable("passw") String passw,
                        @PathVariable("tell")String tell,@PathVariable("state") Integer state)
    {
        UserService userService = applicationContext.getBean(UserService.class);
        userService.adduser(username,passw,tell,state);
    }

    @RequestMapping("setadmin/{userid}")
    @ResponseBody
    public  void update(@PathVariable("userid")Integer userid){
        UserService userService = applicationContext.getBean(UserService.class);
        userService.update(userid);
    }

    @RequestMapping("delete/{userid}")
    @ResponseBody
    public  void delete(@PathVariable("userid")Integer userid){
        UserService userService = applicationContext.getBean(UserService.class);
       userService.delete(userid);
    }

    @RequestMapping("setforbid/{userid}")
    @ResponseBody
    public  void forbiduser(@PathVariable("userid")Integer userid){
        UserService userService = applicationContext.getBean(UserService.class);
        userService.forbiduser(userid);
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.POST)
    @ResponseBody
    public String Login(HttpServletRequest request,HttpServletResponse response) {
        UserService userService = applicationContext.getBean(UserService.class);
       return userService.Login(request,response);
    }

    @RequestMapping("checkuser")
    @ResponseBody
    public  Integer checkuser(HttpServletRequest request){
        UserService userService = applicationContext.getBean(UserService.class);
return userService.checkuser(request);
    }


   
    @RequestMapping("test3")
    @ResponseBody
    public List<Order> tryit3(HttpServletRequest request){
        UserService userService = applicationContext.getBean(UserService.class);
return userService.tryit3(request);
    }


    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    @ResponseBody
    public String Register(HttpServletRequest request) {
        UserService userService = applicationContext.getBean(UserService.class);
        return userService.Register(request);
    }

    @RequestMapping("userforbid")
    @ResponseBody
    public String userdorbid(HttpServletRequest request)
    {
        UserService userService = applicationContext.getBean(UserService.class);
        return userService.userdorbid(request);
    }
    @RequestMapping("userbanlifting")
    @ResponseBody
    public String userbanlifting(HttpServletRequest request)
    {
        UserService userService = applicationContext.getBean(UserService.class);
       return userService.userbanlifting(request);
    }
}