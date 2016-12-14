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
	})												//ㄴ jsp도 euc-kr로 해야한다.
public class CharacterEncodingFilter implements Filter{
	FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		System.out.println("CharacterEncodingFilter.java init() 실행...");
	}
	
	@Override
	public void doFilter(
			ServletRequest request, ServletResponse response,
			FilterChain nextFilter) throws IOException, ServletException {
		System.out.println("CharacterEncodingFilter.java request 실행...");
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		nextFilter.doFilter(request, response);
		System.out.println("CharacterEncodingFilter.java response 실행...");
	}

	@Override
	public void destroy() {
		System.out.println("CharacterEncodingFilter.java destroy() 종료...");
	}
}