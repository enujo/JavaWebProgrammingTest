package spms.bind;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletRequest;
//컨트롤러가 원하는게 기본, 날짜, string타입이면 getparameter사용해서 채워 주고 기본타입의 종류가 아니면 그놈의 set을 찾아서 getparam의 값을 넣어 주겠다. 
public class ServletRequestDataBinder {
  public static Object bind(
      ServletRequest request, Class<?> dataType, String dataName) 
      throws Exception {
	  // 1. dataType의 빈 객체를 만든다 
    if (isPrimitiveType(dataType)) {		//dataTpye과 동일한 객체를 만들어야 한다, Member가 들어요면 Member, 1이 들어오면 Integer
      return createValueObject(dataType, request.getParameter(dataName));
    }
    
    Set<String> paramNames = request.getParameterMap().keySet();
    										//ㄴ memberForm안에 있는 것을 다 받는 것 Map타입으로 keySet()이 Member set이름과 동일 할테니까 
    Object dataObject = dataType.newInstance();
    						//ㄴ 클래스 이름으로 .newinstance이용해서 객체를 만들어라 
    Method m = null;
    
    for (String paramName : paramNames) {
      m = findSetter(dataType, paramName);
      		//ㄴ  어떻게 만들면 되나요 param의 이름에 set 첫글자를 대문자로 그게 setter 
      if (m != null) {
    	  //메소드 이름을 가지고 메소드를 호출할때 invoke라는 메소드를 씁니다. 
        m.invoke(dataObject, createValueObject(m.getParameterTypes()[0], 
            request.getParameter(paramName)));
      }
    }
    return dataObject;
  }
  
  private static boolean isPrimitiveType(Class<?> type) {
    if (type.getName().equals("int") || type == Integer.class ||
        type.getName().equals("long") || type == Long.class ||
        type.getName().equals("float") || type == Float.class ||
        type.getName().equals("double") || type == Double.class ||
        type.getName().equals("boolean") || type == Boolean.class ||
        type == Date.class || type == String.class) {
      return true;
    }
    return false;
  }
  // 1.에서 기본타입이면 니 입력 타입의 객체를 생성해라 
  private static Object createValueObject(Class<?> type, String value) {
    if (type.getName().equals("int") || type == Integer.class) {
      return new Integer(value);
    } else if (type.getName().equals("float") || type == Float.class) {
      return new Float(value);
    } else if (type.getName().equals("double") || type == Double.class) {
      return new Double(value);
    } else if (type.getName().equals("long") || type == Long.class) {
      return new Long(value);
    } else if (type.getName().equals("boolean") || type == Boolean.class) {
      return new Boolean(value);
    } else if (type == Date.class) {
      return java.sql.Date.valueOf(value);
    } else {
      return value;
    }
  }
  
  private static Method findSetter(Class<?> type, String name) {//이 타입의 메소드를 전부 찾는다 
    Method[] methods = type.getMethods();		//모든 메소드를 찾습니다
    
    String propName = null;
    for (Method m : methods) {	//메소드를 하나씩 꺼내어 
      if (!m.getName().startsWith("set")) continue;//set으로 시작하지 않는 것을 continue 다음 for문을 시작 결국은 set으로 시작하는건 for문을 안하겠다는거 결국 set으로 시작하는 것만 for문 
      
      propName = m.getName().substring(3);//3 이후를 자른다  
      if (propName.toLowerCase().equals(name.toLowerCase())) { //그리고 앞에 대문자를 소문자로 바꾸고 내용의 것과 일치하면 리턴  
        return m;
      }
    }
    return null;
  }
}