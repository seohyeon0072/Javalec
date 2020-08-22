package kr.ac.green;

public class BadFileException extends RuntimeException {
	public BadFileException() {
		super("파일의 형식이 올바르지 않습니다.");
	}
}
