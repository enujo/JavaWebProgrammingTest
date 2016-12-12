package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/JSONController")
public class JSONController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
       
        ArrayList<String> list = new ArrayList<String>();
        list.add(new String("ruffy"));		//dao를 통해 db에서 가져오는 내용 
        list.add(new String("zoro"));
        list.add(new String("nami"));
       
        Gson gson = new Gson();

        String jsonList = gson.toJson(list);
        System.out.println(jsonList);
        PrintWriter out = response.getWriter();
        out.write(jsonList);
        out.flush();
        out.close();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
 
}
