package book.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "testsession",schema = "web2")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Test implements Serializable {

  @Column(name = "testname")
    public String testname;
    @Column(name="descriptor")
    private String descriptor;
    @Id
    @Column(name = "testid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer testid;

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
}
