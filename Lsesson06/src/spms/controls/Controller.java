package spms.controls;

import java.util.Map;

public interface Controller 
{
	String execute(Map<String, Object> model) throws Exception;
	//ㄴ 리턴값가지고 view이름을 이용하겠다. 매개변수 Map이란? 키값 형태로 담을 수 있는 것
	// 참조타입으로 보내주는 것
}
