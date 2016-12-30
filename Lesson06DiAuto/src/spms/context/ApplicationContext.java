package spms.context;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

//프로퍼티 파일을 이용한 객체 준비
public class ApplicationContext {
	Hashtable<String, Object> objTable = new Hashtable<String, Object>();

	// key이름에 해당하는 path를 넘겨주면 리턴 해준다
	public Object getBean(String key) {
		return objTable.get(key);
	}

	public ApplicationContext(String propertiesPath) throws Exception {
		Properties props = new Properties(); // Map의 자식
		// Properties<String,String> 할필요가 없다 원래 자체적으로 되어 잇으니까
		// 문자열이기 때문에 file로 바꿔야 한다. inputstream중에도 글자로 되어있는 것을 읽는다 
		// 파일을 로드 시키라는 것은 이 내용을 읽어서 map으로 만들어라
		props.load(new FileReader(propertiesPath));
		// 1. properties -> Hashtable로 이동하는 것을 분리 시켜 놓음
		prepareObjects(props);
		// 2. 열개 끼리 의존 성을 가진다. 그래서 setter를 가지고 의존성을 주입
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
				// 객체를 만드는게 아니라 가져올 것이다 context.xml에 잇을테니까
			} else {
				objTable.put(key, Class.forName(value).newInstance());
				// /auth/login.do 이부분을 key에
			}
		}
	}

	private void injectDependency() throws Exception {
		for (String key : objTable.keySet()) { // properties에서 key를 가져온다 그런데
												// jndi.로 시작하는건 필요가 없다
			if (!key.startsWith("jndi.")) {
				callSetter(objTable.get(key));
			} // setter가 필요한 것은 주입이 필요하니까
		}
	}

	private void callSetter(Object obj) throws Exception {
		Object dependency = null;
		for (Method m : obj.getClass().getMethods()) { // 입력되는 object를 가져와
														// memberDao.모든 메소드를
														// 가져온다
			if (m.getName().startsWith("set")) { // set으로 시작하면
				dependency = findObjectByType(m.getParameterTypes()[0]); // 메소드의
																			// 첫번째
																			// 매개변수
																			// 타입을
																			// 달라
				if (dependency != null) {
					m.invoke(obj, dependency); // object타입이 존재하면(null이 아니면) 주입
				}
			}
		}
	}

	private Object findObjectByType(Class<?> type) {
		for (Object obj : objTable.values()) { // 리턴된게 있으면 주입 없으면 null
			if (type.isInstance(obj)) {
				return obj;
			}
		}
		return null;
	}
}
