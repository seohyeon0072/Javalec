package Lec4;

import java.io.Closeable;
import java.io.IOException;

public class MyUtils{

	public static void closeAll(Closeable...c) {
		for(Closeable tmp : c) {
			try {
				tmp.close();
			} catch(Exception e) { }
		}
	}


}
