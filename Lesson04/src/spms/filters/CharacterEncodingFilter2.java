package spms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
	urlPatterns="/*",
	initParams={
		@WebInitParam(name="encoding",value="UTF-8")
	})
public class CharacterEncodingFilter2 implements Filter{
	FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
	@Override
	public void doFilter(
			ServletRequest request, ServletResponse response,
			FilterChain nextFilter) throws IOException, ServletException {
		
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		//request.setCharacterEncodin("UTF-8"); �̼Ҹ�
		System.out.println("doFilter�� request���͸�");
		
		nextFilter.doFilter(request, response);
		//response�� ���õ� ������ ���;� �Ѵ�
		System.out.println("doFilter�� response���͸�");
	}

	@Override
	public void destroy() {}
}

//���ڵ��� �Ѳ����� �Ҽ� �ִ�.