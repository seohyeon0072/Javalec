package org.doo;

public class Member {
	private String u_id; // 아이디
	private String u_pw; // 비밀번호
	private String u_name; // 이름
	private String u_nick; // 닉네임
	
	public Member() {
	}
	
	// 식별자
	public Member(String u_id) {
		setU_id(u_id);
	}

	public String getU_id() {
		return u_id;
	}
	

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getU_pw() {
		return u_pw;
	}

	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	
	public String getU_nick() {
		return u_nick;
	}

	public void setU_nick(String u_nick) {
		this.u_nick = u_nick;
	}

	// 식별자 
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Member)){
			return false;
		}
		Member other = (Member)o;
		return u_id.equals(other.getU_id());
	}
	
	// 객체 출력
	@Override
	public String toString(){
		return "Member [u_id=" + u_id + ", u_pw=" + u_pw +", u_name=" + u_name + ", u_nick=" + u_nick + "]"; 
	}
	
}

