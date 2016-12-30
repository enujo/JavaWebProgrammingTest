package spms.context;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import java.util.Map;

//프로퍼티 파일을 이용한 객체 준비
public class ApplicationContext {
	Hashtable<String, Object> objTable = new Hashtable<String, Object>();

	public Object getBean(String key) {
		return objTable.get(key);
	}

	public ApplicationContext(String propertiesPath) throws Exception {
		Properties props = new Properties();		//Map의 자식
		//Properties<String,String> 할필요가 없다 원래 자체적으로 되어 잇으니까 
		//문자열이기 때문에 file로 바꿔야 한다. inputstream중에도 글자로 되어있는 것을 읽는다 파일을 로드 시키라는 것은 이 내용을 읽어서 map으로 만들어라
		props.load(new FileReader(propertiesPath));	
		//1. properties -> Hashtable로 이동하는 것을 분리 시켜 놓음
		prepareObjects(props);
		//2. 열개 끼리 의존 성을 가진다. 그래서 setter를 가지고 의존성을 주입 
		injectDependency();
	}

	private void prepareObjects(Properties props) throws Exception {
		Context ctx = new InitialContext();
		String key = null;
		String value = null;

		for (Object item : props.keySet()) {
			key = (String) item;
			value = props.getProperty(key);
			if (key.startsWith("jndi.")) {
				objTable.put(key, ctx.lookup(value));
			} else {
				objTable.put(key, Class.forName(value).newInstance());
			}
		}
	}

	private void injectDependency() throws Exception {
		for (String key : objTable.keySet()) {
			if (!key.startsWith("jndi.")) {
				callSetter(objTable.get(key));
			}
		}
	}

	private void callSetter(Object obj) throws Exception {
		Object dependency = null;
		for (Method m : obj.getClass().getMethods()) {
			if (m.getName().startsWith("set")) {
				dependency = findObjectByType(m.getParameterTypes()[0]);
				if (dependency != null) {
					m.invoke(obj, dependency);
				}
			}
		}
	}

	private Object findObjectByType(Class<?> type) {
		for (Object obj : objTable.values()) {
			if (type.isInstance(obj)) {
				return obj;
			}
		}
		return null;
	}
}
