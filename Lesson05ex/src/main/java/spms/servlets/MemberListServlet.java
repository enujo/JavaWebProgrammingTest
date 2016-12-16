package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

// ServletContext�� ������ Connection ��ü ���  
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ServletContext sc = this.getServletContext(); 
			Connection servletConnection = (Connection) sc.getAttribute("conn"); 
			System.out.println("MemberListServlet.java servletConnection : "+servletConnection);
			
			MemberDao memberDao = new MemberDao();//memberDao.connection == null;
			memberDao.setConnection(servletConnection);
			response.setContentType("text/html; charset=UTF-8");	//memberDao.connection == servletConnection;
			List<Member> members = memberDao.selectList();
			
			// request�� ȸ�� ��� ������ �����Ѵ�.
			request.setAttribute("members", members);
			
			// JSP�� ����� �����Ѵ�.
			RequestDispatcher rd = request.getRequestDispatcher(
					"/member/MemberList.jsp");
			rd.include(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
			
		}
	}
}