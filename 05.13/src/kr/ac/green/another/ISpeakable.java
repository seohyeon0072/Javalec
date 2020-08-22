package kr.ac.green.another;

// 람다식 지원을 위해 JKD 1.8 부터 사용
// 추상 메소드가 한 개만 있는거 ..
// 람다식의 대상 -> FunctionalInterface만 사용 가능
@FunctionalInterface
public interface ISpeakable {
	void speak();
}
