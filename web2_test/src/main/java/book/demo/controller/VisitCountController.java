package book.demo.controller;


import book.demo.dao.VisitCountDao;
import book.demo.entity.User;
import book.demo.entity.VisitCount;
import book.demo.repository.BookRepository;
import book.demo.repository.VisitCountRepository;
import book.demo.service.UserService;
import book.demo.service.VisitCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RestController
@RequestMapping("/visitcount")
public class VisitCountController {

    @Autowired
    WebApplicationContext applicationContext;
    @Autowired
    private VisitCountRepository visitCountRepository;

    @Autowired
    private VisitCountDao visitCountDao;


    @RequestMapping("reset")
    @ResponseBody
    public void reset(){
        VisitCountService visitCountService = applicationContext.getBean(VisitCountService.class);
        visitCountService.reset();
    }

   @RequestMapping("queryAll")
    @ResponseBody
    public List<VisitCount> queryAll(){
        List<VisitCount> visitCounts = visitCountRepository.findAll();
        return visitCounts;
    }

    @RequestMapping("addcount")
    @ResponseBody
    public void addcount(){
        VisitCountService visitCountService = applicationContext.getBean(VisitCountService.class);
        visitCountService.addcount();
    }





}
