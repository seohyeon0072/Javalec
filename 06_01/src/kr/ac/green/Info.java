package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Info extends JFrame implements ActionListener {
	private JTextArea taInput1;
	private JTextArea taInput2;
	
	private JButton btnSave;
	private JButton btnLoad;
	
	private String fileName = "strings.dat";
	
	public Info() {
		taInput1 = new JTextArea(4, 30);
		taInput2 = new JTextArea(4, 30);
		
		btnSave = new JButton("Save");
		btnLoad = new JButton("Load");
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(new JScrollPane(taInput1));
		pnlNorth.setBorder(new TitledBorder(
			new LineBorder(Color.GRAY, 1), "Input1"
		));
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.add(new JScrollPane(taInput2));
		pnlCenter.setBorder(new TitledBorder(
			new LineBorder(Color.GRAY, 1), "Input2"
		));
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnSave);
		pnlSouth.add(btnLoad);
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
		btnSave.addActionListener(this);
		btnLoad.addActionListener(this);
		
		setTitle("DataIO Ex");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);		
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Save")) {
			save();
		} else {
			load();
		}
	}
	
	private void save() {
		try(
			FileOutputStream fos = new FileOutputStream(fileName); 
			DataOutputStream dos = new DataOutputStream(fos);
		) {
			dos.writeUTF(taInput1.getText());
			dos.writeUTF(taInput2.getText());
			dos.flush();			
		} catch(IOException e) {
			e.printStackTrace();
		}		
	}
	private void load() {
		try (
			FileInputStream fis = new FileInputStream(fileName);
			DataInputStream dis = new DataInputStream(fis);
		) {
			taInput1.setText(dis.readUTF());
			taInput2.setText(dis.readUTF());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Info();
	}
}
