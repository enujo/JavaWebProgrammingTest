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
	 setConnection�� ȣ������ �ʰ� �� �޼ҵ带 ȣ�� �ϰ� �Ǹ� NullPointException�� ����
	 ������ setConnection�� ȣ���ϰ� �̸޼ҵ带 ȣ�� �ϸ� connection�� null�� �ƴϴ�.
	 �̷��Ѱ���  ������ ���� defendency injection �̶� �ϸ� 
	 �̷��� ����� Ŭ���� �ȿ� new �����ڰ� ���� -> ��ģ�� �����ڰ� ���ٴ� �Ҹ�
	 �Ϻ��� Dao �� ����� ����
	 ������ Connection �̶�� Ŭ�������� ������ ���Ϸ��� interface ������
	*/
	public List<Member> selectList() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		try{
			/*
			ServletContext sc = this.getServletContext(); dao �� ������ �ȴٴ� �����ȵǴ� 
			connection = (Connection) sc.getAttribute("conn"); 
			*/
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
					" FROM MEMBERS" +
					" ORDER BY MNO ASC");
			
			/*response.setContentType("text/html; charset=UTF-8");*/
			ArrayList<Member> members = new ArrayList<Member>();
			
			// �����ͺ��̽����� ȸ�� ������ ������ Member�� ��´�.
			// �׸��� Member��ü�� ArrayList�� �߰��Ѵ�.
			while(rs.next()) {
				members.add(new Member()
							.setNo(rs.getInt("MNO"))
							.setName(rs.getString("MNAME"))
							.setEmail(rs.getString("EMAIL"))
							.setCreatedDate(rs.getDate("CRE_DATE"))	);
			}
			
		}catch(Exception e){
			throw e;		//������ ���� ������ finally���� �ʿ��� 
		}finally{
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			//try {if (connection != null) connection.close();} catch(Exception e) {}
		}
		return null;
	}

}
