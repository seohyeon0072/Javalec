package kr.ac.green;

public class GenderCheckException extends Exception {
	public GenderCheckException() {
		super("성별을 제대로 선택 하시오");
	}
}
