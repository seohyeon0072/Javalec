import java.util.*;
class Car {
	private String no;
	private String tel;
	
	public Car(String no) {
		setNo(no);
	}
	public Car(String no, String tel) {
		setNo(no);
		setTel(tel);
	}

	public String getNo() {
		return no;
	}
	public String getTel() {
		return tel;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return no + "(" + tel + ")";
	}
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Car)) {
			return false;
		}
		Car other = (Car)o;
		return no.equals(other.getNo());
	}
}
class ParkingLot implements Comparator<Car> {
	public static final int MAX_COUNT = 100;
	public static final int NOT_EXISTS = -1;
	private Vector<Car> carList;

	public ParkingLot() {
		carList = new Vector<Car>();
	}

	public boolean in(Car car) {
		if(carList.size() == MAX_COUNT) {
			System.out.println("만차입니다.");
			return false;
		} else {			
			carList.add(car);
			sort();
			return true;
		}
	}
	
	public void sort() {
		Collections.sort(carList, this);
		Collections.reverse(carList);
		// Collections.shuffle(carList);
	}

	@Override
	public int compare(Car c1, Car c2) {
		return c1.getNo().compareTo(c2.getNo());
	}

	public Car out(String no) {
		Car car = new Car(no);
		int idx = Collections.binarySearch(carList, car, this);
		if(idx <= NOT_EXISTS) {
			System.out.println("해당 차량이 존재하지 않습니다.");
			return null;
		} else {
			return carList.remove(idx);
		}

		/*
		Car car = new Car(no);
		
		int idx = carList.indexOf(car);
		if(idx == NOT_EXISTS) {
			System.out.println("해당 차량이 존재하지 않습니다.");
			return null;
		} else {
			return carList.remove(idx);
		}
		*/
	}

	public int getCurrentCount() {
		return carList.size();
	}
	@Override
	public String toString() {
		String info = "<< 주차 차량 정보>>\n";
		info += "현재 차량 수 : " + getCurrentCount() + "\n";
		info += "==========================================\n";
		Iterator<Car> itr = carList.iterator();
		while(itr.hasNext()) {
			info += itr.next() + "\n";
		}
		return info;
	}
}

class VectorEx {
	public static void main(String[] args)	{
		ParkingLot p = new ParkingLot();

		p.in(new Car("58조4244", "010-2361-4414"));
		p.in(new Car("51조4254", "010-1004-5858"));
		p.in(new Car("34조4249", "010-1234-1818"));
		p.in(new Car("55조4244", "010-4321-1234"));
		p.in(new Car("66조4224", "010-1577-1577"));
		p.in(new Car("97조4211", "010-2362-4424"));

		System.out.println(p);

		Car outedCar = p.out("58조4244");
		System.out.println(p);
	}
}

