package kr.ac.green;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ObjectIO extends JFrame implements ActionListener{

	private JButton btnSave;
	private JButton btnOpen;
	private JButton btnNew;
	
	private Counter counter;
	
	public ObjectIO() {
		btnNew = new JButton("New");
		btnOpen = new JButton("Open");
		btnSave = new JButton("Save");
		
		setLayout(new GridLayout(1, 0));
		
		add(btnNew);
		add(btnSave);
		add(btnOpen);
		
		btnNew.addActionListener(this);
		btnSave.addActionListener(this);
		btnOpen.addActionListener(this);
		
		setTitle("IO Ex");
		setSize(400, 100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String fileName = "counter.dat";
		Object src = ae.getSource();
		if(src == btnNew) {
			if(counter != null) {
				counter.dispose();
				counter = null;
			}
			counter = new Counter();
			counter.setVisible(true);
		} else if(src == btnOpen) {
			try (
				ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(fileName)
				);	
			) {
				CounterInfo info = (CounterInfo)ois.readObject();
				counter = new Counter();
				counter.setCounter(info);
				counter.setVisible(true);
			} catch(IOException e) {
				JOptionPane.showMessageDialog(
					this, 
					"저장된 데이터가 존재하지 않습니다.", 
					"에러", 
					JOptionPane.ERROR_MESSAGE
				);
			} catch (ClassNotFoundException e) {				
				JOptionPane.showMessageDialog(
					this, 
					"클래스파일이 변경되어 불러올수 없습니다.", 
					"에러", 
					JOptionPane.ERROR_MESSAGE
				);
			}
		} else {
			try(
				ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(fileName)
				);
			) {
				if(counter != null) {
					int num = counter.getNumber();
					String title = counter.getTitle();
					Point location = counter.getLocation();
					Dimension size = counter.getSize();
					
					oos.writeObject(new CounterInfo(num, title, location, size));
					oos.flush();
					oos.reset();
					counter.dispose();
					counter = null;
				} else {
					JOptionPane.showMessageDialog(
						this, 
						"저장할 대상이 존재하지 않습니다.", 
						"에러", 
						JOptionPane.ERROR_MESSAGE
					);
				}
			} catch(IOException e) {
				JOptionPane.showMessageDialog(
					this, 
					"저장하는 중 에러가 발생했습니다.", 
					"에러", 
					JOptionPane.ERROR_MESSAGE
				);				
			}
		}
	}

	public static void main(String[] args) {
		new ObjectIO();
	}
}
