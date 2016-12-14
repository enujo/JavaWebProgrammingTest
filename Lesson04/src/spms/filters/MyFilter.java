package spms.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/MyFilter")		//filter랑 lisener는 web.xml에 어떤 필터와 어떤 리슨어를 통해 어떻게 동작되는지 알기 쉽게
public class MyFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MyFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {		//끝날때
		System.out.println("end log test");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
										// 	ㄴ 서블릿보다 먼저 받는다(1,2)  ㄴ4,5,6 단계	ㄴ 다음 필터가 있으면 다음 필터에 연결 시켜주는 매개변수
		//2,3실행 request
		
		
		chain.doFilter(request, response);
		// 4,5,6 실행 response
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {		//시작할때
		System.out.println("start log test");
	}

}
