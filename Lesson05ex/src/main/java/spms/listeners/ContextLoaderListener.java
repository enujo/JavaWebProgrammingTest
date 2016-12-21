package spms.listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import spms.dao.MemberDao;

public class ContextLoaderListener implements ServletContextListener {
	private Connection conn;
	
    public ContextLoaderListener() {
        // »ý¼ºÀÚ
    }
    public void contextDestroyed(ServletContextEvent sce)  { 
         // ÅèÄ¹ÀÌ Á×±âÁ÷Àü¿¡
    	try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public void contextInitialized(ServletContextEvent event)  { 
         // ÅèÄ¹ÀÌ ½ÇÇàµÇ°í³ª¸é
    	try {
			ServletContext sc = event.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			Connection conn = DriverManager.getConnection(
						sc.getInitParameter("url"),
						sc.getInitParameter("username"),
						sc.getInitParameter("password"));
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			sc.setAttribute("memberDao", memberDao);
			
		} catch(Throwable e) {
			e.printStackTrace();
		}
    }
	
}
