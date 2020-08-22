/*
	===================
	Book
	===================
	- title : String
	- author : String
	- price : int
	===================
	+ Book(title:String, author:String, price:int)
	+ getters / setters
	+ toString() : String
	====================

	======================
	Bookcase
	======================
	- category : String
	- books : Book[]
	======================
	+ Bookcase(category:String, books:Book[])
	+ getters / setters
	+ toString() : String
	======================

	======================
	Bookstore
	======================
	- name : String
	- tel : String
	- cases : Bookcase[]
	======================
	+ Bookstore(name:String, tel:String, cases:Bookcase[])
	+ getters / setters
	+ toString
	=======================


*/

class Book {
	private String title;
	private String author;
	private int price;

	public Book(String title, String author, int price){
		setTitle(title);
		setAuthor(author);
		setPrice(price);
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getAuthor(){
		return author;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public int getPrice(){
		return price;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public String toString(){
		String info ="title: " + title + "\n";
		info += "author: " + author + "\n";
		info += "price: " + price;
		return info;
	}
	
}

class Bookcase {
	private String category;
	private Book[] books;

	public Bookcase(String category, Book... books){
		setCategory(category);
		setBooks(books);
	}

	public String getCategory(){
		return category;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public Book[] getBooks(){
		return books;
	}

	public void setBooks(Book... books){
		this.books = books;
	}

	@Override
	public String toString(){
		String info = "-- category : " + category + "\n";
		for(Book book : books){
			info += book + "\n";
		}
		return info;
	}
}

class Bookstore {
	private String name;
	private String tel;
	private Bookcase[] cases;

	public Bookstore(String name, String tel, Bookcase... cases) {
		setName(name);
		setTel(tel);
		setCases(cases);
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getTel(){
		return tel;
	}

	public void setTel(String tel){
		this.tel = tel;
	}

	public Bookcase[] getCases(){
		return cases;
	}

	public void setCases(Bookcase... cases){
		this.cases = cases;
	}

	@Override 
	public String toString(){
		String info ="<<" + name + " information>>\n";
		info += "tel: " + tel + "\n";
		for(Bookcase temp : cases){
			info += temp + "\n";
		}
		return info;
	}
}

class BookstoreEx {
	public static void main(String[] args)	{
		// Book(String title, String author, int pirce)
		Book b1 = new Book("자바","제임스 고슬링",20000);
		Book b2 = new Book("C언어","제임스 씨슬링",25000);
		Book b3 = new Book("파이썬","제임스 파슬링",30000);

		//Bookcase(String category, Book... books)
		Bookcase case1 = new Bookcase("프로그래밍", b1,b2,b3);
		Bookcase case2 = new Bookcase(
			"과일",
			new Book("애플은 무엇인가", "사과", 12000),
			new Book("바나나는 무엇인가", "빙그레", 14000),
			new Book("레몬은 무엇인가", "레몬", 17000),	
			new Book("나는 무엇인가", "사람", 22000)
		);

		//배열은 길이가 변하지 않는다
		Bookstore store = new Bookstore
			("교보문고","051-1234-5678",case1,case2);
		System.out.println(store);

	}
}
