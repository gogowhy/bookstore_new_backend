package book.demo;

import book.demo.service.BookService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

public class BookClient {

    public static void main(String[] args) throws NamingException, RemoteException
    {
        Context namingContext = new InitialContext();

        String url = "rmi://localhost/bookService";
        BookService bookService = (BookService) namingContext.lookup(url);

        System.out.println(bookService.rmiquery("helloPanda"));

    }


}
