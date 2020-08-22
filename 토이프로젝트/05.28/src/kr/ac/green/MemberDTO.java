package kr.ac.green;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String nickName;
	private String gender;
	private boolean userSession;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean getSession() {
		return userSession;
	}

	public void setSession(boolean session) {
		this.userSession = session;
	}

	@Override
	public String toString() {
		String info = id + "\n";
		info += pw + "\n";
		info += name + "\n";
		info += nickName + "\n";
		info += gender;

		return info;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberDTO other = (MemberDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public MemberDTO(String id) {
		setId(id);
	}
	public MemberDTO() {
	}

}
