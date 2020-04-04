package book.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "visitcount")

public class VisitCount implements Serializable {

    @Column(name="visitcount")
    private Integer visitcount;

    @Column(name = "countname")
    private String countname;

    @Id
    @Column(name = "vcid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer vcid;

    public Integer getVisitcount() {
        return visitcount;
    }

    public void setVisitcount(Integer visitcount) {
        this.visitcount = visitcount;
    }

    public String getCountname() {
        return countname;
    }

    public void setCountname(String countname) {
        this.countname = countname;
    }

    public Integer getVcid() {
        return vcid;
    }

    public void setVcid(Integer vcid) {
        this.vcid = vcid;
    }
}
