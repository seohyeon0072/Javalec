package Network_Lec3;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

 

public class Bab extends JFrame implements ActionListener {
 private JButton button; // 시작, 멈춤 버튼
 
 // "밥.dat"에서 불러온 문자열을 저장할 공간
 // 순서대로 돌면서 화면에 보여줘야 하기 때문에 ArrayList로 저장
 // "밥.dat"에 몇줄이 저장되어 있는지 모르기 때문에 String[]을 쓰지 않고 ArrayList 사용
 private ArrayList<String> bobList = new ArrayList<String>();
 
 private JTextArea textArea; // "밥.dat"에서 불러온 문자열을 보여줄 영역

 private boolean startCheck = false; // 버튼의 상태값, 시작(true) <-> 멈춤(false)

 // 마지막 문자열을 보여줬을때 다시 처음 문자열을 보여줘야 하기 때문에
 // "밥.dat"에서 불러온 문자열이 몇개인지 저장
 private int count = 0;
 
 public static void main(String[] args) {
  Bab bob = new Bab();
  bob.dataSet(); // "밥.dat" 내용 읽어옴
  bob.setWindow(); // 윈도우창의 기본적인 레이아웃 배치
  bob.setListener(); // 리스너 등록
  bob.setShow(); // 프로그램 창의 크기, 제목 등 설정
 }

 private void dataSet() {
  try {
   FileReader fr = new FileReader("밥.dat");
   
   // readLine을 사용하기 위해 BufferedReader 사용
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
  JPanel panelCenter = new JPanel(); // 글자 보여줄 영역
  textArea = new JTextArea();
  textArea.setFont(new Font("", Font.BOLD, 100));
  textArea.setForeground(Color.BLUE);
  textArea.setBackground(Color.WHITE);
  panelCenter.add(textArea);
  
  this.add(panelCenter, BorderLayout.CENTER);
  
  JPanel panelSouth = new JPanel(); // 버튼 보여줄 영역
  button = new JButton("눌러");
  panelSouth.add(button);
  
  this.add(panelSouth, BorderLayout.SOUTH);
 }
 
 private void setListener() {
  button.addActionListener(this);
 }
 
 private void setShow() {
  this.setBounds(300, 300, 300, 200);
  this.setResizable(false); // 크기 고정
  this.setVisible(true);
  this.setTitle("밥"); // 제목 설정
  this.setDefaultCloseOperation(EXIT_ON_CLOSE); // X 누르면 프로그램 종료할수 있도록 설정
  button.requestFocus(); // 프로그램 실행시 포커스가 버튼으로 되도록 지정
 }
 
 // 버튼을 누를때 마다 true <-> false 변할수 있도록 해주는 메소드
 private boolean toggle() {
  return startCheck = !startCheck; // true -> false
                                   // false -> true
 }

 @Override
 public void actionPerformed(ActionEvent e) {
  if(toggle()) { // 버튼이 시작상태이면.. startCheck == true
   Loop loop = new Loop();
   loop.start(); // Loop 스레드 시작
  }
  button.requestFocus(); // 버튼 누른후 포커스를 다시 버튼으로 지정
 }

 // textArea 영역에 순서대로 돌아가면서 문자열을 보여줄 스레드
 class Loop extends Thread {
  @Override
  public void run() {
   
   // 버튼이 시작상태인(startCheck == true) 동안만 동작
   // startCheck == false이면 루프가 멈추므로 스레드도 종료
   while(startCheck == true) {

    // "밥.dat" 내용이 저장되어 있는 bobList에서
    // 0번 부터 마지막까지 순서대로 출력
    textArea.setText(bobList.get(count));
    
    // 현재 출력된 문자열 순서 다음이 전체 사이즈 숫자와 같다면
    // 순서를 0번으로 돌려서(Size가 3 이면.. 저장된 숫자는 0, 1, 2 이므로..)
    // 처음부터 마지막까지 다시 출력
    if(++count == bobList.size()) {
     count = 0;
    }
    
    try {
     
     // sleep() 메소드를 이용해서 현재 문자열과 다음 문자열의 출력시간 설정
     // 너무 적게 지정하면 오히려 화면상에서 부자연스러우므로 어느정도 크기는 줘야됨
     Loop.sleep(18);
    } catch (InterruptedException e) {
     e.printStackTrace();
    }
   }
  }
 }
}