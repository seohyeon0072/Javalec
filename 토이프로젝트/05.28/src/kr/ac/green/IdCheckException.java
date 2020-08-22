package kr.ac.green;

public class IdCheckException extends Exception{
	public IdCheckException() {
		super("아이디는 6~12자로 입력하세요");
	}
	
	public IdCheckException(String id) {
		super(id+"는 이미 사용 중 입니다.");
	}
	
	public IdCheckException(String id, String pw) {
		super("아이디와 비밀번호를 다시 확인 하세요.");
	}
}
