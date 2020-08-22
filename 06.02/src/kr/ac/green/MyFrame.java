package kr.ac.green;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	private Properties prop;

	public MyFrame() {
		load();

		setTitle(prop.getProperty("title"));
		int width = Integer.parseInt(prop.getProperty("width"));
		int height = Integer.parseInt(prop.getProperty("height"));
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void load() {
		prop = new Properties();

		try (FileInputStream fis = new FileInputStream("myframe.properties")) {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MyFrame();
	}
}
