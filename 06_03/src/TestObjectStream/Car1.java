package TestObjectStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * ��ü ����ȭ
 *     ��ü�� ������ ����Ʈ ������ ��ȯ�ؼ� ���� �Ǵ� ��Ʈ��ũ�� ����
 *     �ۼ����� �����ϰ� ������ִ°�
 * JVM �� ������ �����ϴ� ��ü�� �� �ٷ� �þ ����Ʈ�� ���·� ����� ��
 * 
 * ��ü ����ȭ�� ����
 *     ��ü ��ü�� ������ ����� ���Ŀ� ���ֹ��� �ʰ� ��ü�� ���Ͽ� ����
 * -> �������� ���Ӽ� ����
 */
class Car implements Serializable {
	private String color;
	private transient int speed;// transient Ű���带 ���ؼ� ��ü ����ȭ���� ���ܽ�Ų��
	private int mileage;

	// ��ü ����ȭ�� serialVersionUID�� ����Ǿ� �־�� �Ѵ�
	// ��������� ������� �ʾҴٸ� JVM���� �ڵ�����

	// �ʿ��� ����
	// ����ȭ �������� serialVersionUID�� ������ ���Եǰ� �ǰ�,
	// ������ȭ �������� class�� ����Ǿ� �ִ� serialVersionUID�� ������ ���� �������� üũ�Ѵ�

	public Car(String color, int speed, int mileage) {
		this.color = color;
		this.speed = speed;
		this.mileage = mileage;
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		// TODO Auto-generated method stub
		return "����" + this.color + "�ӵ���" + this.color + "���ϸ�����" + this.mileage;
	}
}

public class Car1 {
	public static void main(String[] args) throws Exception {

		Car[] c = new Car[3];
		c[0] = new Car("��", 100, 1000);
		c[1] = new Car("ȭ��Ʈ", 200, 2000);
		c[2] = new Car("�׸�", 300, 3000);
		FileOutputStream fsout = new FileOutputStream("C:\\DDD\\data.bin");
		ObjectOutputStream objout = new ObjectOutputStream(fsout);

		objout.writeObject(c[0]);// writeObject ��ü�� ���´� //������ �߻��Ѵ� , Student Ŭ������ ��ȯ�� �ȵǱ� �����̴�
		// Serializable �� Ŭ������ ���´�
		// class implements Serializable
		objout.writeObject(c[1]);// writeObject ��ü�� ���´� //������ �߻��Ѵ� , Student Ŭ������ ��ȯ�� �ȵǱ� �����̴�
		objout.writeObject(c[2]);// writeObject ��ü�� ���´� //������ �߻��Ѵ� , Student Ŭ������ ��ȯ�� �ȵǱ� �����̴�

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
