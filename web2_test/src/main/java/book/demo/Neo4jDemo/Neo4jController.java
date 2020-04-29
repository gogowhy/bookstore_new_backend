package book.demo.Neo4jDemo;


import book.demo.entity.Books;
import book.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.RemoteException;
import java.util.List;

@EnableNeo4jRepositories
@RestController
@Scope("singleton")
@RequestMapping("/person")
public class Neo4jController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("newperson/{name}")
    @ResponseBody
    public String newperson(@PathVariable("name") String name) {

        /*Person person = personRepository.findByName(name);
        if(person==null)
        {
            person.setName(name);
            personRepository.save(person);
            return "New person inserted successfully";
        }
        else
        {
            return "Sorry, the person already exists!";
        }*/
        Person person = new Person(name);
        personRepository.save(person);
        return "New person inserted successfully";

    }

    @RequestMapping("newperson/test")
    @ResponseBody
    public String persontest() {

        return "good test";
    }

    @RequestMapping("newrelation/{nameA}/{nameB}")
    @ResponseBody
    public String newrelation(@PathVariable("nameA") String nameA,@PathVariable("nameB") String nameB) {


        Person personA = personRepository.findByName(nameA);
        Person personB = personRepository.findByName(nameB);
        personA.isfriendwith(personB);
        personB.isfriendwith(personA);
        personRepository.save(personA);
        personRepository.save(personB);
        return "New relation inserted successfully";

    }

    @RequestMapping("deleterelation/{nameA}/{nameB}")
    @ResponseBody
    public String deleterelation(@PathVariable("nameA") String nameA,@PathVariable("nameB") String nameB) {


        Person personA = personRepository.findByName(nameA);
        Person personB = personRepository.findByName(nameB);
        personA.deletefriend(personB);
        personB.deletefriend(personA);
        personRepository.save(personA);
        personRepository.save(personB);
        return "Old relation deleted successfully";

    }

}
