package TestObjectStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * 객체 직렬화
 *     객체의 내용을 바이트 단위로 변환해서 파일 또는 네트워크를 통해
 *     송수신이 가능하게 만들어주는것
 * JVM 힙 영역에 존재하는 객체를 한 줄로 늘어선 바이트의 형태로 만드는 것
 * 
 * 객체 직렬화의 장점
 *     객체 자체의 내용을 입출력 형식에 구애받지 않고 객체를 파일에 저장
 * -> 데이터의 영속성 제공
 */
class Car implements Serializable {
	private String color;
	private transient int speed;// transient 키워드를 통해서 객체 직렬화에서 제외시킨다
	private int mileage;

	// 객체 직렬화시 serialVersionUID가 선언되어 있어야 한다
	// 명시적으로 선언되지 않았다면 JVM에서 자동생성

	// 필요한 이유
	// 직렬화 과정에서 serialVersionUID의 버전이 포함되게 되고,
	// 역직렬화 과정에서 class에 선언되어 있는 serialVersionUID의 버전과 서로 동일한지 체크한다

	public Car(String color, int speed, int mileage) {
		this.color = color;
		this.speed = speed;
		this.mileage = mileage;
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		// TODO Auto-generated method stub
		return "색은" + this.color + "속도는" + this.color + "마일리지는" + this.mileage;
	}
}

public class Car1 {
	public static void main(String[] args) throws Exception {

		Car[] c = new Car[3];
		c[0] = new Car("블랙", 100, 1000);
		c[1] = new Car("화이트", 200, 2000);
		c[2] = new Car("그린", 300, 3000);
		FileOutputStream fsout = new FileOutputStream("C:\\DDD\\data.bin");
		ObjectOutputStream objout = new ObjectOutputStream(fsout);

		objout.writeObject(c[0]);// writeObject 객체를 적는다 //에러가 발생한다 , Student 클래스는 변환이 안되기 때문이다
		// Serializable 을 클래스에 적는다
		// class implements Serializable
		objout.writeObject(c[1]);// writeObject 객체를 적는다 //에러가 발생한다 , Student 클래스는 변환이 안되기 때문이다
		objout.writeObject(c[2]);// writeObject 객체를 적는다 //에러가 발생한다 , Student 클래스는 변환이 안되기 때문이다

		objout.close();

		ObjectInputStream objin = new ObjectInputStream(new FileInputStream("C:\\Temp\\data.bin"));

		for (int i = 0; i < args.length; i++) {
			c[i] = (Car) objin.readObject();
		}
		for (int i = 0; i < c.length; i++) {
			System.out.println(c[i]);
		}

		if (objin != null) {
			objin.close();
		}
		if (objout != null) {
			objout.close();
		}

	}
}
