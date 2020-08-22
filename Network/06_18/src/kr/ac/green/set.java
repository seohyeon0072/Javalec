package kr.ac.green;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class set {

	public static void main(String[] args) {

		Set<Integer> set2 = new HashSet<Integer>();

		Scanner sc = new Scanner(System.in);

		set2.add(41);
		set2.add(1);
		set2.add(5);
		set2.add(3);
		set2.add(5);
		set2.add(8);

		List<Integer> list = new ArrayList<Integer>(set2);
		Collections.sort(list);
		System.out.println(list);

	}
}
