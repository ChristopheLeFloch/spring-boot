package com.laposte.pic.examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//comment
public class HelloServlet extends HttpServlet  {


	 public  void doGet(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
		 try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 


		
		PrintWriter out = response.getWriter() ;
		out.println("Hello Sam et Thomas") ;
	 }


	 public  void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
	
		doGet(request, response) ;
	}
}
