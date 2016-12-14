package tistory.luahius.spms.filters;

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
		@WebInitParam(name="encoding",value="euc-kr")
	})												//�� jsp�� euc-kr�� �ؾ��Ѵ�.
public class CharacterEncodingFilter implements Filter{
	FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		System.out.println("CharacterEncodingFilter.java init() ����...");
	}
	
	@Override
	public void doFilter(
			ServletRequest request, ServletResponse response,
			FilterChain nextFilter) throws IOException, ServletException {
		System.out.println("CharacterEncodingFilter.java request ����...");
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		nextFilter.doFilter(request, response);
		System.out.println("CharacterEncodingFilter.java response ����...");
	}

	@Override
	public void destroy() {
		System.out.println("CharacterEncodingFilter.java destroy() ����...");
	}
}