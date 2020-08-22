import java.util.*;

//이름뽑기
class MyUtils {
	//from~to~까지 난수뽑음
	public static int getRandomNumber(int from, int to){
		return (int)(Math.random() * (to - from + 1)) + from;
	}
	
	//이름 자동생성기
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
	//키를 사람이름, 벨류는 Human으로
	private Hashtable<String, Vector<Human>> book;
	
	//생성만 담당
	public PhoneBook(){
		book = new Hashtable<String, Vector<Human>>();
	}
	
	//추가
	public void add(Human h){
		String name = h.getName();//추가하는사람의 이름
		Vector<Human> list;//리스트 선언
		//처음엔 아무것도 없음
		if(book.containsKey(name)){//키값체크 : 있는경우
			list = book.get(name);//기존에 있는 Vector에서 가져와서 넣음
		} else {//없는경우
			//Vector를 만들어서 hashtable(list)에 넣음
			list = new Vector<Human>();
			book.put(name, list);
		}
		list.add(h);//list는 주소라 넣는 방법이나 순서는 상관없음
	}
	
	//검색
	public Human[] search(String name){
		Vector<Human> list = book.get(name);//이름으로 꺼내와서 Vector로 받음 //만약 없는 이름이면 null
		Human[] arr;
		// 존재하지 않는 경우
		if(list == null){
			arr = new Human[0];
		} else { //존재한다면 
			arr = list.toArray(new Human[0]);
		}
		return arr;
	}
	
	//삭제
	public boolean delete(String name){
		Vector<Human> list = book.get(name);//이름 들고와서
		//null(이름이없거나 등록된게 없거나)이거나 size가 0이면(사람이 등록되어있다가 삭제된 경우)
		if(list == null || list.size() == 0){
			return false;
		} else {//사람이름도 등록되어있고 Vector도 원소를 가지고 있을 경우
			for(int idx=0; idx<list.size(); idx++){ //사람목록 꺼내서 출력해줌
				System.out.println((idx + 1) + ". " + list.get(idx));
			}
			System.out.print("삭제할 번호를 선택하세요 : ");
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
