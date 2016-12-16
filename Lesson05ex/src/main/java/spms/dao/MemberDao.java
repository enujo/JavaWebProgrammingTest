package spms.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import spms.vo.Member;

public class MemberDao {
	Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
		
	}
	/*>>> selectList() <<<
	 setConnection을 호출하지 않고 이 메소드를 호출 하게 되면 NullPointException이 난다
	 하지만 setConnection을 호출하고 이메소드를 호출 하면 connection이 null이 아니다.
	 이러한것을  의존성 주입 defendency injection 이라 하며 
	 이렇게 만들면 클래스 안에 new 연산자가 없다 -> 그친구 생성자가 없다는 소리
	 완벽한 Dao 를 만들기 위해
	 하지만 Connection 이라는 클래스에게 의존을 안하려면 interface ㅇㅅㅇ
	*/
	public List<Member> selectList() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		try{
			/*
			ServletContext sc = this.getServletContext(); dao 가 서블릿이 된다는 말도안되는 
			connection = (Connection) sc.getAttribute("conn"); 
			*/
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
					" FROM MEMBERS" +
					" ORDER BY MNO ASC");
			
			/*response.setContentType("text/html; charset=UTF-8");*/
			ArrayList<Member> members = new ArrayList<Member>();
			
			// 데이터베이스에서 회원 정보를 가져와 Member에 담는다.
			// 그리고 Member객체를 ArrayList에 추가한다.
			while(rs.next()) {
				members.add(new Member()
							.setNo(rs.getInt("MNO"))
							.setName(rs.getString("MNAME"))
							.setEmail(rs.getString("EMAIL"))
							.setCreatedDate(rs.getDate("CRE_DATE"))	);
			}
			
		}catch(Exception e){
			throw e;		//있으나 마나 하지만 finally절이 필요해 
		}finally{
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			//try {if (connection != null) connection.close();} catch(Exception e) {}
		}
		return null;
	}

}
