package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.bind.DataBinding;
import spms.bind.ServletRequestDataBinder;
import spms.controls.Controller;
import spms.vo.Member;

// ServletContext에 보관된 페이지 컨트롤러를 사용
@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
  @Override
  protected void service(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    String servletPath = request.getServletPath();
    try {
      ServletContext sc = this.getServletContext();
      
      // 페이지 컨트롤러에게 전달할 Map 객체를 준비한다. 
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("session", request.getSession());
      
      Controller pageController = (Controller) sc.getAttribute(servletPath);
      if(pageController instanceof DataBinding){
    	  Object[] dataBinders = ((DataBinding) pageController).getDataBinders();
    	  //만들어야 할 객체 타입 객체 이름
    	  String dataName = null;		//가져온 데이터 이름
    	  Class<?> dataType = null;	//클래스 타입
    	  //ㄴ?를 빼도 되고 모르니까 물음표!
    	  Object dataObject = null;	//객체 타입
    	  for(int i=0; i<dataBinders.length; i+=2){
    		  dataName = (String)dataBinders[i];
    		  dataType = (Class<?>)dataBinders[i+1];
    		  /*<4>
    		   * 어떤 메소드를 호출하면 이 메소드가 해주는 일 dataType+request 이게 바인딩 되어서 와야 한다;
    		   * dataObject = 메소드호출();
    		   * request에 넘기는 매개변수 이름이 Member에 선언되어 있는 이름과 같아야한다
    		   * */
    		  dataObject = ServletRequestDataBinder.bind(request, dataType);
    		  // name을 dataTpye의 메소드를 호출   dataType.setName(name값); 호출하려면 이름이 같아야한다 !!!! 이래서 통일성이 있어야 된다 
    		  model.put(dataName, dataObject);
    	  }
    	  
    			  
    	  
    	  
    	  /*((DataBinding) pageController).getDataBinders();    	 <3> 
    	  //pageController.getDataBinders(); 호출 필요한 모델타입을 풀어낸다. 
    	  //모델 객체를 자동으로 만들어 주는 메서드 호출*/
    	  } 
     
      /* <2>
      	this.test(); 내가 뭐라 요청하든 이게 실행이 되기 때문에 분기를 시켜야한다
      	add이거나 뭐이거나 뭐이거나 이럴때. 지금은 간단 하다 언제 실행하면되냐add일경우 니가
      	문제는 add뿐만 아니라 또 있어야하니까 결론적으론 똑같네 ㅡㅡ
      	 if(servletPath.equals("/member/add.do")){
    	  
      }
      	
      	그래서 한자로 줄일수 있다. if(pageController instanceof DataBinding){ this.test()} 
      	들어갈수 있으면 true, 아니면 false
      */
      
      /*모델객체를 만들어야 하기때문에 이코드가 남아 잇는 것
      if ("/member/add.do".equals(servletPath)) {
        if (request.getParameter("email") != null) {
          model.put("member", new Member()
            .setEmail(request.getParameter("email"))
            .setPassword(request.getParameter("password"))
            .setName(request.getParameter("name")));
        }
      } else if ("/member/update.do".equals(servletPath)) {
        if (request.getParameter("email") != null) {
          model.put("member", new Member()
            .setNo(Integer.parseInt(request.getParameter("no")))
            .setEmail(request.getParameter("email"))
            .setName(request.getParameter("name")));
        } else {
          model.put("no", new Integer(request.getParameter("no")));
        }
      } else if ("/member/delete.do".equals(servletPath)) {
        model.put("no", new Integer(request.getParameter("no")));
      } else if ("/auth/login.do".equals(servletPath)) {
        if (request.getParameter("email") != null) {
          model.put("loginInfo", new Member()
            .setEmail(request.getParameter("email"))
            .setPassword(request.getParameter("password")));
        }
      }
      **/
      // 페이지 컨트롤러를 실행한다.
      String viewUrl = pageController.execute(model);
      
      // Map 객체에 저장된 값을 ServletRequest에 복사한다.
      for (String key : model.keySet()) {
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
  
  public void test(){
	  //여기로 if문의 내용이 와야 한다. <1> 
  }
}
