package spms.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.controls.Controller;
import spms.controls.LogInController;
import spms.controls.LogOutController;
import spms.controls.MemberAddController;
import spms.controls.MemberDeleteController;
import spms.controls.MemberListController;
import spms.controls.MemberUpdateController;
import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
  @Override
  	protected void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    String servletPath = request.getServletPath();
    
    try {
    	Controller controller = null;
    	Map<String, Object> model = new HashMap<String, Object>();
    	//model 안의 memberDao 주입
    	ServletContext sc = this.getServletContext();
    	model.put("memberDao", sc.getAttribute("memberDao"));
    	
      if ("/member/list.do".equals(servletPath)) {
    	  System.out.println("DispatcherServlet : list.do");
        controller = new MemberListController();
      } else if ("/member/add.do".equals(servletPath)) {
    	  System.out.println("DispatcherServlet : add.do");
        controller = new MemberAddController();
        if (request.getParameter("email") != null) {
          model.put("member", new Member()
            .setEmail(request.getParameter("email"))
            .setPassword(request.getParameter("password"))
            .setName(request.getParameter("name")));
        }
      } else if ("/member/update.do".equals(servletPath)) {
    	  System.out.println("DispatcherServlet : update.do");
    	  model.put("no", Integer.parseInt(request.getParameter("no")));
    	  controller = new MemberUpdateController();
        if (request.getParameter("email") != null) {
        	System.out.println("DispatcherServlet : update.do != null");
			model.put("member", new Member()
				.setNo(Integer.parseInt(request.getParameter("no")))
				.setEmail(request.getParameter("email"))
				.setName(request.getParameter("name")));
        }
      } else if ("/member/delete.do".equals(servletPath)) {
    	  System.out.println("DispatcherServlet : delete.do");
    	  model.put("no", Integer.parseInt(request.getParameter("no")));
    	  controller = new MemberDeleteController();
      } else if ("/auth/login.do".equals(servletPath)) {
    	  System.out.println("DispatcherServlet : login.do");
			
    	  if(request.getParameter("email")!= null && request.getParameter("password") != null){
    		  model.put("loginInfo", new Member()
  					.setEmail(request.getParameter("email"))
  					.setPassword(request.getParameter("password")));
    		  model.put("session", request.getSession(true));
    		  
    		  controller = new LogInController();
			}else{
				controller = new LogInController();
			}
    	  
      } else if ("/auth/logout.do".equals(servletPath)) {
    	  System.out.println("DispatcherServlet : logout.do");
    	  model.put("session", request.getSession(false));
    	  controller = new LogOutController();
      }
      
      
    // 컨트롤러 호출을 통해 view 이름을 리턴
      String viewUrl = controller.execute(model);
      //Map안의 내용을 request.attribute 안으로 옮겨야한다.
      for(String key : model.keySet())//모델의 key수 만큼, key도 중복이 안된다
      {
    	  request.setAttribute(key, model.get(key));
      }
      
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
        return;
        
      } else {
       RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
        rd.include(request, response);
      }
      
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
      rd.forward(request, response);
    }
  }
}
