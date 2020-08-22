import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
	private String name;
	private String regDate;
	private int price;
	private int rank;

	public Item(String name, String regDate, int price, int rank){
		setName(name);
		setRegDate(regDate);
		setPrice(price);
		setRank(rank);
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getRegDate(){
		return regDate;
	}
	public void setRegDate(String regDate){
		this.regDate = regDate;
	}
	public int getPrice(){
		return price;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public int getRank(){
		return rank;
	}
	public void setRank(int rank){
		this.rank = rank;
	}
	
	public String toString(){
		return name + "(" + regDate + ", " + price + "��, " + rank + "��)";
	}
}

class Store implements Comparator<Item>{
	public static final int ORDER_BY_DATE = 1;
	public static final int ORDER_BY_PRICE_DESC = 2;
	public static final int ORDER_BY_PRICE_ASC = 3;
	public static final int ORDER_BY_RANK = 4;

	public static final int EXIT = 5;

	private Item[] itemList;
	private int mode;

	public Store(Item... itemList){
		setItemList(itemList);
	}

	public Item[] getItemList(){
		return itemList;
	}

	public void setItemList(Item... itemList){
		this.itemList = itemList;
	}

	@Override
	public int compare(Item item1, Item item2){
		int result = 0;
		if(mode == ORDER_BY_DATE){
			result = item1.getRegDate().compareTo(item2.getRegDate());
		} else if(mode == ORDER_BY_PRICE_DESC){
			result = item2.getPrice() - item1.getPrice();
		} else if(mode == ORDER_BY_PRICE_ASC){
			result = item1.getPrice() - item2.getPrice();
		} else if(mode == ORDER_BY_RANK){
			result = item1.getRank() - item2.getRank();
		}
		return result;
	}

	public void showList(){
		Scanner scan = new Scanner(System.in);
		do{
			System.out.println("1. ����� ��");
		    System.out.println("2. ���� ���� ��");
			System.out.println("3. ���� ���� ��");
			System.out.println("4. ��ǰ ��ŷ ��");
			System.out.println("5. ���� ��");
			System.out.println("* �����Ͻÿ�(1~5�̿��� ��ȣ�Է� �� �ڵ� ����˴ϴ�.:");
			mode = scan.nextInt();

			if(mode < 1 || mode > 4){
				mode = EXIT;
			}
			if(mode != EXIT){
				Arrays.sort(itemList, this);
				for(Item item : itemList){
					System.out.println(item);
				}
			}
		} while(mode != EXIT);
		System.out.println("Good bye~");
	}
}


class useStore {
	public static void main(String[] args)	{
		Store store = new Store(
				new Item("���Ÿ�����","2019-09-01", 300000, 5),
				new Item("�α�������","2019-10-06", 20000, 1),
				new Item("�����ѱ���","2019-07-05", 10000, 3),
				new Item("�ٶ���������","2019-04-09", 5000, 2),
				new Item("���뱸��","2018-12-16", 100000, 4)
			);
			store.showList();
	}
}
