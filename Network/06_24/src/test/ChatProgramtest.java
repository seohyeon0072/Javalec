package test;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.Border;

class ChatProgramtest extends JFrame implements ActionListener {
	JLabel userID;
	JPanel mainPanel,userIDPanel;
	JTextField chat;
	JButton sendButton,exitButton,creatRoomButton,insertRoomButton;
	JScrollPane waitingRoomChatScroll,userListScroll ,roomListScroll; // 대기실 스크롤 ,사용자 스크롤
	JTextArea waitingRoomChat;
	JList<String> userList,roomList;
	DefaultListModel<String> userListModel,roomListModel;
	HashMap<String, PrintWriter> map = new HashMap<String, PrintWriter>();	
	HashMap<String,String> roomMap = new HashMap<String , String >();
	BufferedReader bufferedReader;
	PrintWriter printWriter;
	String id,roomcord;
	Socket socket;
	String ip;
	ChatProgramtest(String id,String ip){
		this.id = id;
		this.ip = ip;
		GUI();
		try {
			socket = new Socket(ip,10001);
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			printWriter.println(id);
			printWriter.flush();
			(new ChatClientThread(bufferedReader, printWriter, socket , this)).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addWindowListener(new WindowAdapter() {//종료
			@Override
			public void windowClosing(WindowEvent e){
				// 창위에 x 눌렀을 때
				printWriter.println("/exit");
				printWriter.flush();
				try {
					bufferedReader.close();
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				printWriter.close();
				System.exit(0);
			}
		});
	}
	void GUI() {
		setLayout(null);
//		mainPanel = new JPanel();
//		mainPanel.setLayout(null);
//		mainPanel.setSize(500,500);
		userIDPanel = new JPanel(new GridLayout(3,1));//유저정보패널
		userIDPanel.setBackground(Color.white);
		userID = new JLabel(id);
		userIDPanel.add(userID);
		Border border2 = BorderFactory.createTitledBorder("내정보");
		userIDPanel.setBorder(border2);
		
		chat = new JTextField("");
		chat.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					chat.selectAll();
					printWriter.println(chat.getText());
					printWriter.flush();
				}
			}
		});
		
		sendButton = new JButton("보내기");
		exitButton = new JButton("나가기");
		creatRoomButton = new JButton("방 만들기");
		creatRoomButton.addActionListener(this);
		insertRoomButton = new JButton("방 들어가기");
		insertRoomButton.addActionListener(this);
		waitingRoomChat = new JTextArea();
		Border border1 = BorderFactory.createTitledBorder("대기실");
		waitingRoomChat.setBorder(border1);
		waitingRoomChat.setEditable(false);
		userListModel = new DefaultListModel<String>();
		roomListModel = new DefaultListModel<String>();
		userList = new JList<String>(userListModel);
		roomList = new JList<String>(roomListModel);
		
		userListScroll = new JScrollPane(userList);
		roomListScroll = new JScrollPane(roomList);
		Border border3 = BorderFactory.createTitledBorder("채팅방");
		roomListScroll.setBorder(border3);
		Border border4 = BorderFactory.createTitledBorder("참가자");
		userListScroll.setBorder(border4);
		waitingRoomChatScroll = new JScrollPane(waitingRoomChat);
		userIDPanel.setBounds(300, 300, 100, 100);
		chat.setBounds(10, 440, 250, 21);
		sendButton.setBounds(280, 440, 80, 20);
		exitButton.setBounds(370,440,80,20);
		creatRoomButton.setBounds(310, 210, 100, 20);
		insertRoomButton.setBounds(310, 240, 110, 20);
		userListScroll.setBounds(310, 10, 170, 200);
		roomListScroll.setBounds(10, 10, 250, 200);
		waitingRoomChatScroll.setBounds(10, 235, 250, 200);
		add(userIDPanel);
		add(insertRoomButton);
		add(creatRoomButton);
		add(roomListScroll);
		add(userListScroll);
        add(waitingRoomChatScroll);
		add(chat);
		add(sendButton);
		add(exitButton);
		setSize(505,510);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == creatRoomButton){
			String roomName = JOptionPane.showInputDialog("방제목 입력 ");
			printWriter.println("creatroom_ " + id + " " + roomName);
			printWriter.flush();
		}
		if(e.getSource() == insertRoomButton){
			roomcord = (String)roomList.getSelectedValue();
			new RoomClient(this,roomMap.get(roomcord),Integer.parseInt(roomMap.get(roomcord)),id,roomcord);
		}
		if(chat.getText().equals("/quit")){
			printWriter.println("/quit");
			printWriter.flush();
			System.exit(0);
		} else {
			printWriter.println(chat.getText());
			printWriter.flush();			
		}
	}
}
class ChatClientThread extends Thread{
	ChatProgramtest mainFrame;
	Socket socket;
	BufferedReader bufferedReader;
	PrintWriter printWriter;
	ChatClientThread(BufferedReader bufferedReader, PrintWriter printWriter , Socket socket , ChatProgramtest mainFrame){
		this.mainFrame = mainFrame;
		this.socket = socket;
		this.bufferedReader = bufferedReader;
		this.printWriter = printWriter;
	}
	@Override
	public void run(){
		String line = null;
		try {
			while((line = bufferedReader.readLine()) != null){
				String[] sum = line.split("/");
				String[] sumroom = line.split(" ");
				System.out.println(line);
				if(line.indexOf("roomin") != -1){//방 리스트 갱신
					updateRoomList(sum);
				} else if(line.indexOf("/invate") != -1) {//초대받음
					be_Invited(sumroom);
				}else if (line.indexOf("roomout") != -1) {//방 리스트 수정
					editRoomList(sum);
				} else if(line.indexOf("/exit " + mainFrame.id) != -1) {//퇴장당함
					be_Sent_Off();
				} else if(line.indexOf("/createnumber") != -1) {//방 클리이언트 생성
					new RoomClient(mainFrame,sumroom[1],Integer.parseInt(sumroom[1]),mainFrame.id,sumroom[2]);
				} else if(line.indexOf("강퇴되었습니다.") != -1) {//강퇴당함
					be_Forced_Out();
				} else if(line.indexOf("입장하였습니다.") != -1) {//유저리스트 갱신
					updateUserList(sumroom);
				} else if(line.indexOf("userdelete") != -1) {//유저리스트 수정
					editUserList(sumroom);
				} else {//대기방 채팅
				mainFrame.waitingRoomChat.append(line + "\n");
				mainFrame.waitingRoomChat.setCaretPosition(mainFrame.waitingRoomChat.getDocument().getLength());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void updateRoomList(String[] sum) {
		mainFrame.roomMap.put(sum[3], sum[1]);// 방제목값 , 포트번호값
		mainFrame.roomcord = sum[3];
		mainFrame.roomListModel.addElement(sum[3]);
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	void be_Invited(String[] sumroom) {
		int invated = JOptionPane.showConfirmDialog(mainFrame, sumroom[1] + " 님께서 초대하셨습니다.\n" + sumroom[3],"초대장",JOptionPane.YES_NO_OPTION);
		if(invated == JOptionPane.YES_OPTION){
			new RoomClient(mainFrame,mainFrame.roomMap.get(sumroom[4]),Integer.parseInt(mainFrame.roomMap.get(sumroom[4])),mainFrame.id,sumroom[4]);
		}
	}
	void be_Sent_Off() {
		printWriter.println("/exit");
		printWriter.flush();
		try {
			bufferedReader.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		printWriter.close();
		System.exit(0);
	}
	void be_Forced_Out() {
		try {
			printWriter.println("/quit");
			printWriter.flush();
			printWriter.println("/exit");
			printWriter.flush();
		} finally {			
			try {
				if(bufferedReader != null){
					bufferedReader.close();
				}
				if(printWriter != null){
					printWriter.close();
				}
				if(socket != null){
					socket.close();					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("exit");
		System.exit(0);
	}
	void updateUserList(String[] sumroom) {
		if(sumroom[2].equals("입장하였습니다.")){
			synchronized (mainFrame.map) {
				mainFrame.map.put(sumroom[0],printWriter);
			}
			mainFrame.userListModel.addElement(sumroom[0]);
			mainFrame.revalidate();
			mainFrame.repaint();
		}
	}
	void editUserList(String[] sumroom) {
		mainFrame.map.remove(sumroom[1]);
		for(int i = 0 ; i < mainFrame.userListModel.getSize();i++){
			if(mainFrame.userListModel.getElementAt(i).equals(sumroom[1])){
				mainFrame.userListModel.remove(i);
				mainFrame.revalidate();
				mainFrame.repaint();
			}
		}
	}
	void editRoomList(String[] sum) {
		mainFrame.roomMap.remove(sum[1]);
		mainFrame.roomListModel.removeElement(sum[1]);
		mainFrame.revalidate();
		mainFrame.repaint();
	}
}
