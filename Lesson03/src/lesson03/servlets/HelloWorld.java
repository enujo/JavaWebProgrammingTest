package lesson03.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class HelloWorld implements Servlet {
	ServletConfig config;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() »£√‚µ ");
		this.config = config;
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() »£√‚µ ");
	}
	
	public void doGet(){
		System.out.println("doGet() »£√‚µ ");
	}
	public void doPost(){
		System.out.println("doPost() »£√‚µ ");
	}
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		System.out.println("service() »£√‚µ ");
		if(((HttpServletRequest) request).getMethod().equals("GET")){
			this.doGet();
		}else{
			this.doPost();
		}
		
	}

	
	
	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig() »£√‚µ ");
		return this.config;
	}

	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo() »£√‚µ ");
		return "version=1.0;author=eomjinyoung;copyright=eomjinyoung 2013";
	}
}


