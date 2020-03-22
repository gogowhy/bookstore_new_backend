package book.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orderkafkaRepository",schema = "web2")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})

public class orderkafka implements Serializable {

    @Column(name = "userid")
    public Integer userid;
    @Column(name="bookname")
    private String bookname;
    @Column(name="booknumber")
    private Integer booknumber;
    @Id
    @Column(name = "orderkafkaid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer orderkafkaid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Integer getBooknumber() {
        return booknumber;
    }

    public void setBooknumber(Integer booknumber) {
        this.booknumber = booknumber;
    }

    public Integer getOrderkafkaid() {
        return orderkafkaid;
    }

    public void setOrderkafkaid(Integer orderkafkaid) {
        this.orderkafkaid = orderkafkaid;
    }
}


