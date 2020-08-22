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

class Human {
	private String name;
	private int age;
	private String mail;
	private String tel;

	// for test
	public Human(){
		setName(MyUtils.getRandomString(3));
		setAge(MyUtils.getRandomNumber(10,40));
		int length = MyUtils.getRandomNumber(3,5);
		setMail(MyUtils.getRandomNumber(10,40) + "@doo.com");
		setTel(MyUtils.getRandomNumber(100, 9999) + "-" + MyUtils.getRandomNumber(100, 9999));
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
	
}

interface HumanManager{
	void add(Human h);
	Human[] search(String name);
	boolean delete(String name);
}

class PhoneBook implements HumanManager{
	//Ű�� ����̸�, ������ Human����
	private Hashtable<String, Vector<Human>> book;
	
	//������ ���
	public PhoneBook(){
		book = new Hashtable<String, Vector<Human>>();
	}
	
	//�߰�
	public void add(Human h){
		String name = h.getName();//�߰��ϴ»���� �̸�
		Vector<Human> list;//����Ʈ ����
		//ó���� �ƹ��͵� ����
		if(book.containsKey(name)){//Ű��üũ : �ִ°��
			list = book.get(name);//������ �ִ� Vector���� �����ͼ� ����
		} else {//���°��
			//Vector�� ���� hashtable(list)�� ����
			list = new Vector<Human>();
			book.put(name, list);
		}
		list.add(h);//list�� �ּҶ� �ִ� ����̳� ������ �������
	}
	
	//�˻�
	public Human[] search(String name){
		Vector<Human> list = book.get(name);//�̸����� �����ͼ� Vector�� ���� //���� ���� �̸��̸� null
		Human[] arr;
		// �������� �ʴ� ���
		if(list == null){
			arr = new Human[0];
		} else { //�����Ѵٸ� 
			arr = list.toArray(new Human[0]);
		}
		return arr;
	}
	
	//����
	public boolean delete(String name){
		Vector<Human> list = book.get(name);//�̸� ���ͼ�
		//null(�̸��̾��ų� ��ϵȰ� ���ų�)�̰ų� size�� 0�̸�(����� ��ϵǾ��ִٰ� ������ ���)
		if(list == null || list.size() == 0){
			return false;
		} else {//����̸��� ��ϵǾ��ְ� Vector�� ���Ҹ� ������ ���� ���
			for(int idx=0; idx<list.size(); idx++){ //������ ������ �������
				System.out.println((idx + 1) + ". " + list.get(idx));
			}
			System.out.print("������ ��ȣ�� �����ϼ��� : ");
			Scanner scan = new Scanner(System.in);
			int idx = Integer.parseInt(scan.nextLine()) - 1;
			list.remove(idx);
			return true;
		}
	}

	@Override
	public String toString(){
		Collection<Vector<Human>> values = book.values();
		String info = "";
		for(Vector<Human> list : values){
			for(Human temp : list){
				info += temp + "\n";
			}
		}
		return info;
	}

}

class PhoneBookTest_Hashtable_2 {
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
