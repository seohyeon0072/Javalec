package Network_Lec3;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

 

public class Bab extends JFrame implements ActionListener {
 private JButton button; // ����, ���� ��ư
 
 // "��.dat"���� �ҷ��� ���ڿ��� ������ ����
 // ������� ���鼭 ȭ�鿡 ������� �ϱ� ������ ArrayList�� ����
 // "��.dat"�� ������ ����Ǿ� �ִ��� �𸣱� ������ String[]�� ���� �ʰ� ArrayList ���
 private ArrayList<String> bobList = new ArrayList<String>();
 
 private JTextArea textArea; // "��.dat"���� �ҷ��� ���ڿ��� ������ ����

 private boolean startCheck = false; // ��ư�� ���°�, ����(true) <-> ����(false)

 // ������ ���ڿ��� ���������� �ٽ� ó�� ���ڿ��� ������� �ϱ� ������
 // "��.dat"���� �ҷ��� ���ڿ��� ����� ����
 private int count = 0;
 
 public static void main(String[] args) {
  Bab bob = new Bab();
  bob.dataSet(); // "��.dat" ���� �о��
  bob.setWindow(); // ������â�� �⺻���� ���̾ƿ� ��ġ
  bob.setListener(); // ������ ���
  bob.setShow(); // ���α׷� â�� ũ��, ���� �� ����
 }

 private void dataSet() {
  try {
   FileReader fr = new FileReader("��.dat");
   
   // readLine�� ����ϱ� ���� BufferedReader ���
   BufferedReader br = new BufferedReader(fr);
   
   String line = "";
   while((line = br.readLine()) != null) {
    bobList.add(line);
   }
   
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (IOException ioe) {
   ioe.printStackTrace();
  }
 }
 
 private void setWindow() {
  JPanel panelCenter = new JPanel(); // ���� ������ ����
  textArea = new JTextArea();
  textArea.setFont(new Font("", Font.BOLD, 100));
  textArea.setForeground(Color.BLUE);
  textArea.setBackground(Color.WHITE);
  panelCenter.add(textArea);
  
  this.add(panelCenter, BorderLayout.CENTER);
  
  JPanel panelSouth = new JPanel(); // ��ư ������ ����
  button = new JButton("����");
  panelSouth.add(button);
  
  this.add(panelSouth, BorderLayout.SOUTH);
 }
 
 private void setListener() {
  button.addActionListener(this);
 }
 
 private void setShow() {
  this.setBounds(300, 300, 300, 200);
  this.setResizable(false); // ũ�� ����
  this.setVisible(true);
  this.setTitle("��"); // ���� ����
  this.setDefaultCloseOperation(EXIT_ON_CLOSE); // X ������ ���α׷� �����Ҽ� �ֵ��� ����
  button.requestFocus(); // ���α׷� ����� ��Ŀ���� ��ư���� �ǵ��� ����
 }
 
 // ��ư�� ������ ���� true <-> false ���Ҽ� �ֵ��� ���ִ� �޼ҵ�
 private boolean toggle() {
  return startCheck = !startCheck; // true -> false
                                   // false -> true
 }

 @Override
 public void actionPerformed(ActionEvent e) {
  if(toggle()) { // ��ư�� ���ۻ����̸�.. startCheck == true
   Loop loop = new Loop();
   loop.start(); // Loop ������ ����
  }
  button.requestFocus(); // ��ư ������ ��Ŀ���� �ٽ� ��ư���� ����
 }

 // textArea ������ ������� ���ư��鼭 ���ڿ��� ������ ������
 class Loop extends Thread {
  @Override
  public void run() {
   
   // ��ư�� ���ۻ�����(startCheck == true) ���ȸ� ����
   // startCheck == false�̸� ������ ���߹Ƿ� �����嵵 ����
   while(startCheck == true) {

    // "��.dat" ������ ����Ǿ� �ִ� bobList����
    // 0�� ���� ���������� ������� ���
    textArea.setText(bobList.get(count));
    
    // ���� ��µ� ���ڿ� ���� ������ ��ü ������ ���ڿ� ���ٸ�
    // ������ 0������ ������(Size�� 3 �̸�.. ����� ���ڴ� 0, 1, 2 �̹Ƿ�..)
    // ó������ ���������� �ٽ� ���
    if(++count == bobList.size()) {
     count = 0;
    }
    
    try {
     
     // sleep() �޼ҵ带 �̿��ؼ� ���� ���ڿ��� ���� ���ڿ��� ��½ð� ����
     // �ʹ� ���� �����ϸ� ������ ȭ��󿡼� ���ڿ�������Ƿ� ������� ũ��� ��ߵ�
     Loop.sleep(18);
    } catch (InterruptedException e) {
     e.printStackTrace();
    }
   }
  }
 }
}