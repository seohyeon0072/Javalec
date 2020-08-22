import java.util.*;
class Homework_RecursiveCall {
	public static void answer1(String str){
		int idx = str.lastIndexOf(" ");
		if(idx != -1){
			answer1(str.substring(0, idx));
		}
		System.out.println(str);
	}

	public static void answer2(String str){
		int idx = str.indexOf(" ");
		if(idx != -1){
			answer2(str.substring(idx+1));
		}
		System.out.println(str);
	}

	public static void answer3(String str){
		int idx = str.indexOf(" ");
		if(idx != -1){
			System.out.println(str.substring(0, idx));
			answer3(str.substring(idx+1, str.length()));
		}else {
			System.out.println(str);
		}
	}

	public static void answer4(String str){
		int idx = str.lastIndexOf(" ");
		if(idx != -1){
			System.out.println(str.substring(idx+1, str.length()));
			answer4(str.substring(0, idx));
		}else{
			System.out.println(str);
		}	
	}

	public static void main(String[] args)	{
		String str = "태산이 높다하되 하늘 아래 뫼이로다.";
		answer1(str);
		System.out.println();
		answer2(str);
		System.out.println();
		answer3(str);
		System.out.println();
		answer4(str);
		System.out.println();
	}
}
