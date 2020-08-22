package kr.ac.green;

public class NameCheckException extends Exception {
	public NameCheckException() {
		super("이름은 한글만 가능함");
	}

	public NameCheckException(int num) {
		super("이름은 " + num + "글자 이상 한글로 입력할 것");
	}
}
