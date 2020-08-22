import java.util.*;

//�̸��̱�
class MyUtils {
	//from~to~���� ��������
	public static int getRandomNumber(int from, int to){
		return (int)(Math.random() * (to - from + 1)) + from;
	}
	
	//�̸� �ڵ�������
	public static char getRandomChar(){
		int num = getRandomNumber(0, 1);
		char c;
		if(num == 0){
			c = (char)getRandomNumber('A', 'Z');
		}else {
			c = (char)getRandomNumber('a','z');
		}
		return c;
	}

	public static String getRandomString(int length){
		String str = "";
		for(int i=0; i<length; i++){
			str += getRandomChar();
		}
		return str;
	}

}

class Human{
	private String name;
	private int age;
	private String mail;
	private String tel;

	public Human(){
		setName(MyUtils.getRandomString(3));
		setAge(MyUtils.getRandomNumber(10, 40));
		int length = MyUtils.getRandomNumber(3, 5);
		setMail(MyUtils.getRandomNumber(10, 40) + "@doo.com");
		setTel(MyUtils.getRandomNumber(100, 9999) + "-" + MyUtils.getRandomNumber(100, 9999));
	}
	public Human(String name){
		setName(name);
	}

	public Human(String name, int age, String mail, String tel){
		setName(name);
		setAge(age);
		setMail(mail);
		setTel(tel);
	}

	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	public String getMail(){
		return mail;
	}
	public String getTel(){
		return tel;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setAge(int age){
		this.age = age;
	}
	public void setMail(String mail){
		this.mail = mail;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	@Override
	public String toString(){
		return name + "(" + tel + ", " + mail + ", " + age + ")";
	}

	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof Human)){
			return false;
		}

		Human temp = (Human)o;
		return name.equals(temp.getName());
	}
}

interface HumanManager{
	void add(Human h);
	Human[] search(String name);
	boolean delete(String name);
}

class PhoneBook implements HumanManager{
	private Vector<Human> list; //��������� Human�޴� Vector

	public PhoneBook(){
		list = new Vector<Human>();
	}

	public void add(Human h){
		list.add(h);
	}

	public boolean delete(String name){
		//�̸����� �ٵ��ͼ�
		Integer[] indices = getIndices(name);
		//0�̾ƴϸ� �̸� ã��
		if(indices.length != 0){
			int num = 1;
			for(int idx : indices){
				Human temp = list.get(idx);
				System.out.println(num + ". " + temp.toString());
				num++;
			}
			System.out.print("������ ��ȣ�� �Է��Ͻÿ� : ");
			Scanner scan = new Scanner(System.in);
			int deleteIdx = scan.nextInt() - 1;
			list.remove(indices[deleteIdx].intValue());
			return true;
		} else { //������ false
			return false;
		}
	}
	
	//name�� �����ϴ� ��ġ���� �迭�� ����
	private Integer[] getIndices(String name){
		//�̸��޾Ƽ� Human��ü ����
		Human target = new Human(name);
		
		boolean flag = true;
		int idx = 0;//Vector���� �ε����� ã�¼ҽ�
		Vector<Integer> indices = new Vector<Integer>();//������ ���� length�� 0
		while(flag){
			idx = list.indexOf(target, idx);
			if(idx == -1){
				flag = false;
			} else {
				indices.add(idx);
				idx++;
			}
		}
		return indices.toArray(new Integer[0]);//Integer[]�迭�� ó��
	}

	public Human[] search(String name){
		Integer[] indices = getIndices(name);//�̸�ã�� �ε��� �� ����
		Human[] arr;
		if(indices.length == 0){ //������
			arr = null;
		} else { //������
			arr = new Human[indices.length];
			for(int idx = 0; idx < arr.length; idx++){
				arr[idx] = list.get(indices[idx]);
			}
		}
		return arr;
	}

	@Override
	public String toString(){
		String info = "";
		for(Human temp : list){
			info += temp.toString() + "\n";
		}
		return info;
	}
}

class PhoneBookTest_Vector_1 {
	public static void main(String[] args)	{
		
		PhoneBook pb = new PhoneBook();

		pb.add(new Human());
		pb.add(new Human());
		pb.add(new Human());
		pb.add(new Human("a",10,"abc@doo.org","123-4567"));
		pb.add(new Human("a",21,"abc@doo.org","223-4567"));
		pb.add(new Human("a",32,"abc@doo.org","323-4567"));
		pb.add(new Human("a",43,"abc@doo.org","423-4567"));
		
		pb.delete("a");

		Human[] arr = pb.search("a");

		for(Human h : arr){
			System.out.println(h);
		}

	}
}
