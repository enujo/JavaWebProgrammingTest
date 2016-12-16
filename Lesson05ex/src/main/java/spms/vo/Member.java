package spms.vo;

import java.util.Date;

public class Member {
	public Member(){}	//디폴트 생성자 나중에 생성자 오버로딩을 대비해서
	
	protected int 		no;
	protected String 	name;
	protected String 	email;
	protected String 	password;
	protected Date		createdDate;
	protected Date		modifiedDate;
	
	public int getNo() {
		return no;
	}
	public Member setNo(int no) {
		System.out.println("Member.java setNo : "+no);
		this.no = no;
		return this;
	}
	public String getName() {
		return name;
	}
	public Member setName(String name) {
		System.out.println("Member.java name : "+name);
		this.name = name;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Member setEmail(String email) {
		System.out.println("Member.java email : "+email);
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		System.out.println("Member.java password : "+password);
		this.password = password;
		return this;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Member setCreatedDate(Date createdDate) {
		System.out.println("Member.java createdDate : "+createdDate);
		this.createdDate = createdDate;
		return this;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public Member setModifiedDate(Date modifiedDate) {
		System.out.println("Member.java modifiedDate : "+modifiedDate);
		this.modifiedDate = modifiedDate;
		return this;
	}
	
	@Override
	public String toString() {
		return "Member [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
	
}
