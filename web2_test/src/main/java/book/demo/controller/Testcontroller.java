package book.demo.controller;


import book.demo.entity.Test;
import book.demo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@RestController
@Scope("prototype")
@RequestMapping("/testsession")

public class Testcontroller {


    @Autowired
    TestRepository testRepository;

    @RequestMapping("queryAll")
    @ResponseBody
    public List<Test> queryAll(){
        List<Test> list = new ArrayList<Test>();
        list = testRepository.findAll();
        return list;
    }


    @RequestMapping("add/{testname}/{descriptor}")
    @ResponseBody
    public void addtest(@PathVariable("testname")String testname, @PathVariable("descriptor") String descriptor)
    {
       Test test = new Test();
       test.setTestname(testname);
       test.setDescriptor(descriptor);
       testRepository.save(test);
    }

    @RequestMapping("firstlog/{testname}")
    @ResponseBody
    public void firstlog(@PathVariable("testname")String testname, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.setAttribute("testname",testname);
        System.out.println("firstlog is ok");
        System.out.println(session.getAttribute("testname"));

    }

    @RequestMapping("secondlog")
    @ResponseBody
    public void secondlog(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("testname"));
        /*if(session.getAttribute("testname")!=null)
        {

            System.out.println("We have session"+session.getAttribute("testname")+"haha");
        }
        else
        {
            System.out.println("no session");
        }*/
    }

}
