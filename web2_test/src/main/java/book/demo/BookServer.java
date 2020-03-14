package com.macoredroid.onlinebookstore;

import book.demo.serviceimpl.BookServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
public class BookServer {
    public static void main(String[] args) throws
            RemoteException, NamingException {
        System.out.println("Constructing server implementation...");
       BookServiceImpl bookServiceImpl = new BookServiceImpl();

        System.out.println("Binding server implementation to registry...");
        Context namingContext = new InitialContext();
        namingContext.bind("rmi:bookService", bookServiceImpl);

        System.out.println("Waiting for invocations from clients...");
    }

}