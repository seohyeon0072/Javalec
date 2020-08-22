package kr.ac.green;

public class NickNameCheckException extends Exception {
	public NickNameCheckException() {
		super("닉네임은 2글자 이상 입력하세요");
	}
	

}
