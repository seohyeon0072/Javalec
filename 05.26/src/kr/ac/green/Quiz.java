package kr.ac.green;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Quiz {
	public static final int EOF = -1;

	/*
	 * src = 원본 복사본 파일 이름 *_copy.*
	 */
	public static void copy(File src) {
		int data = -1;

		FileInputStream fis = null;
		FileOutputStream fos = null;

		String fileName = src.getName();

		int ext = fileName.lastIndexOf(".");

		String name = fileName.substring(0, ext);
		String ext2 = fileName.substring(ext, fileName.length());

		File copyImg = new File(name + "_copy" + ext2);

		// File Read
		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(copyImg, true);

			while ((data = fis.read()) != EOF) {
				fos.write(data);
			}

			//File이 자동으로 생김
//			copyImg.createNewFile();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			} try {
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		File img = new File("looto.png");
		copy(img);
	}
}