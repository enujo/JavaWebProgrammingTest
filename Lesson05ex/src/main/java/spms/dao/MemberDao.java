package spms.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;

import spms.vo.Member;

public class MemberDao {
	Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
		
	}
	public Member exist(String email, String password)throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Member member = null;
		
		try{
			stmt = connection.prepareStatement(
					"SELECT MNAME,EMAIL FROM MEMBERS"
					+ " WHERE EMAIL=? AND PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				member = new Member()
						.setEmail(rs.getString("EMAIL"))
						.setName(rs.getString("MNAME"));
			}
		}catch(Exception e){
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
		}
		
		return member;
	}
	public int update(Member member)throws Exception	{
		int result = 0;
		PreparedStatement stmt = null;
		try{
		stmt = connection.prepareStatement(
				"UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now()"
				+ " WHERE MNO=?");
		stmt.setString(1, member.getEmail());
		stmt.setString(2, member.getName());
		stmt.setInt(3, member.getNo());
		result = stmt.executeUpdate();
		}catch(Exception e){
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			//try {if (conn != null) conn.close();} catch(Exception e) {}
		}
		return result;
	}
	public Member selectOne(int no)throws Exception	{
		Member member = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
				"SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + 
				" WHERE MNO=" + (no));	
			if (rs.next()) {
				member =  
					new Member()
						.setNo(rs.getInt("MNO"))
						.setEmail(rs.getString("EMAIL"))
						.setName(rs.getString("MNAME"))
						.setCreatedDate(rs.getDate("CRE_DATE"));
			}
		}catch(Exception e){
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			//try {if (conn != null) conn.close();} catch(Exception e) {}
		}
		return member;		
	}
	public int delete(int no) throws Exception{
		Statement stmt = null;
		int result = 0;
		try{
			stmt = connection.createStatement();
			result = stmt.executeUpdate(
					"DELETE FROM MEMBERS WHERE MNO=" + (no));
			
			
		}catch(Exception e){
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			//try {if (conn != null) conn.close();} catch(Exception e) {}
		}
		System.out.println("delete result : "+no);
		return result;
		
	}
	public int addList(Member member) throws Exception {
		PreparedStatement stmt = null;
		int result = 0;
		try{
		stmt = connection.prepareStatement("INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE) VALUES (?,?,?,NOW(),NOW())");
		stmt.setString(1, member.getEmail());
		stmt.setString(2, member.getPassword());
		stmt.setString(3, member.getName());
		result = stmt.executeUpdate();
		}catch(Exception e){
			throw e;
		}finally{
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}
		return result;
		
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
		ArrayList<Member> members = new ArrayList<Member>();
		
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
		
			
			// �����ͺ��̽����� ȸ�� ������ ������ Member�� ��´�.
			// �׸��� Member��ü�� ArrayList�� �߰��Ѵ�.
			while(rs.next()) {
				members.add(new Member()
							.setNo(rs.getInt("MNO"))
							.setName(rs.getString("MNAME"))
							.setEmail(rs.getString("EMAIL"))
							.setCreatedDate(rs.getDate("CRE_DATE"))	);
				System.out.println("Member.java toString : "+members.toString());
			}
			
			
		}catch(Exception e){
			throw e;		//������ ���� ������ finally���� �ʿ��� 
		}finally{
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			//try {if (connection != null) connection.close();} catch(Exception e) {}
		}
		return members;
		
	}

}
