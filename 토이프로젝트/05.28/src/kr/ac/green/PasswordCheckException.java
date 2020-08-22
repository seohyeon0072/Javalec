package kr.ac.green;

public class PasswordCheckException extends Exception {
	public PasswordCheckException() {
		super("비밀번호가 서로 달라요");
	}
	
	public PasswordCheckException(int num) {
		super("비밀번호 입력하세요");
	}
}
