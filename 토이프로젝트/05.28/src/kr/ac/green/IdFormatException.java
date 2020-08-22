package kr.ac.green;

public class IdFormatException extends Exception {
	public IdFormatException() {
		super("아이디는 영어 또는 숫자만 들어갈 수 있음");
	}
}
