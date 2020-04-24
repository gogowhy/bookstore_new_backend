package book.demo.HBaseTest;

import book.demo.entity.Test;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import book.demo.HBaseTest.HBaseTest;

@RestController
@Scope("singleton")

@RequestMapping("/journal")
public class HBaseController {


    HBaseTest hBaseTest;


    @RequestMapping("createjournal")
    @ResponseBody
    public void createjournal(){
        hBaseTest.createTable("journal","info");
        System.out.println("create jornal ok");
    }

    @RequestMapping("write/{content}")
    @ResponseBody
    public void createjournal(@PathVariable("content")String content){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        hBaseTest.addRow("journal",date,"info","details",content);
    }

    @RequestMapping("createjournals")//创建三个表格
    @ResponseBody
    public void createjournals(){
        hBaseTest.createTable("journal1","info");
        hBaseTest.createTable("journal2","info");
        hBaseTest.createTable("journal3","info");

    }

    @RequestMapping("check/{content}")//检查一个日志中的行数
    @ResponseBody
    public void checkjournal(@PathVariable("content") String content) throws IOException {

       Integer rows_of_1 = hBaseTest.rowcount("journal1");
        Integer rows_of_2 = hBaseTest.rowcount("journal2");
        Integer rows_of_3 = hBaseTest.rowcount("journal3");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        if(rows_of_1>=0 && rows_of_1<1000)
        {
            if(rows_of_1==999)
            {
                hBaseTest.deleteTable("journal2");
                hBaseTest.addRow("journal2",date,"info","details",content);
            }
            else
            {
                hBaseTest.addRow("journal1",date,"info","details",content);
            }
        }
        if(rows_of_2>=0 && rows_of_2<1000)
        {
            if(rows_of_2==999)
            {
                hBaseTest.deleteTable("journal3");
                hBaseTest.addRow("journal3",date,"info","details",content);
            }
            else
            {
                hBaseTest.addRow("journal2",date,"info","details",content);
            }
        }
        if(rows_of_3>=0 && rows_of_3<1000)
        {
            if(rows_of_3==999)
            {
                hBaseTest.deleteTable("journal1");
                hBaseTest.addRow("journal1",date,"info","details",content);
            }
            else
            {
                hBaseTest.addRow("journal3",date,"info","details",content);
            }
        }

    }

}
