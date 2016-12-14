package spms.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/MyFilter")		//filter�� lisener�� web.xml�� � ���Ϳ� � ����� ���� ��� ���۵Ǵ��� �˱� ����
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
	public void destroy() {		//������
		System.out.println("end log test");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
										// 	�� �������� ���� �޴´�(1,2)  ��4,5,6 �ܰ�	�� ���� ���Ͱ� ������ ���� ���Ϳ� ���� �����ִ� �Ű�����
		//2,3���� request
		
		
		chain.doFilter(request, response);
		// 4,5,6 ���� response
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {		//�����Ҷ�
		System.out.println("start log test");
	}

}
