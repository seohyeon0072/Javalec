package test;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class ChatServer extends JFrame implements ActionListener{
	JLabel panelTitle;
	JTextArea chatWindow;
	JList<String> userList;
	DefaultListModel<String> userListModel;
	JScrollPane chatWindowScroll,userListScroll;
	JTextField chatField;
	PrintWriter printWriter;
	BufferedReader bufferedReader;
	JButton kickButton;
	JPanel chatFieldPanel;
	int port = 10011;
	HashMap<String, PrintWriter> map;
	HashMap<String, PrintWriter> INGmap;//?
	HashMap<Integer, String> roomMap;
	public ChatServer() {
		GUI();
		makeSocket();
}
	void makeSocket() {
		try{		
			ServerSocket server = new ServerSocket(10001);	
			chatWindow.append("������ ��ٸ��ϴ�.\n");
			while(true){	
				Socket sock = server.accept();// ���� ���
				ChatThread1 chatthread = new ChatThread1(sock, map , chatWindow ,chatField,this, userListModel, kickButton);
				chatthread.start();
			} // while			
		}catch(Exception e){	
			System.out.println("server main : " + e);
		}	
	}
	void GUI() {
		setLayout(null);
		
		panelTitle = new JLabel("����");
		chatWindow = new JTextArea("===ä�ó���===\n");
		chatField = new JTextField(28);
		kickButton = new JButton("����");
		chatFieldPanel = new JPanel(null);
		
		userListModel = new DefaultListModel<String>();
		userList = new JList<String>(userListModel);
		
		chatWindowScroll = new JScrollPane(chatWindow);
		userListScroll = new JScrollPane(userList);
		
		map = new HashMap<String, PrintWriter>();
		INGmap = new HashMap<String, PrintWriter>();
		roomMap = new HashMap<Integer,String>();
		
		kickButton.addActionListener(this);
		chatField.addActionListener(this);
		
		chatFieldPanel.add(chatField);
		chatFieldPanel.add(kickButton);
		chatField.setBounds(0,0,300,30);
		kickButton.setBounds(310,0,100,30);
		
		panelTitle.setBounds(10,10,100,10);
		chatWindowScroll.setBounds(10,30,300,400);
		userListScroll.setBounds(320,30,100,400);
		chatFieldPanel.setBounds(10,435,420,30);
		
		add(panelTitle);
		add(chatWindowScroll);
		add(userListScroll);
		add(chatFieldPanel);
		
		setSize(445, 510);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String[] textFild = chatField.getText().split(" ");
		if (arg0.getSource()==kickButton) {
			kick_From_User_List();
		} else if (textFild[0].equals("/kick")) {//����
			kick(textFild[1]);
		} else if(!(chatField.getText().equals(""))) {//ä��
			chatting();
		}
	}
	void kick(String id) {
		map.get(id).println("����Ǿ����ϴ�.");
		map.get(id).flush();
		chatField.setText("");
		chatWindow.append(id + "���� �����Ͽ����ϴ�.\n");
	}
	void kick_From_User_List() {
		int index = userList.getSelectedIndex();
		System.out.println(index);
		kick(userListModel.getElementAt(index));
		if(userListModel.getSize()==0){
			kickButton.setEnabled(false);
		}
	}
	void chatting() {
		chatWindow.append("���� : " + chatField.getText() + "\n");
		synchronized(map){
			Collection<PrintWriter> collection = map.values();	
			Iterator<PrintWriter> iter = collection.iterator();	//�ݺ���
			while(iter.hasNext()){				
				PrintWriter pw = iter.next();
				pw.print("���� : " + chatField.getText() + "\n");
				pw.flush();
			}	
		}
		chatField.setText("");
	}
	public static void main(String[] args) {
		new ChatServer();
	}
}
class ChatThread1 extends Thread{
	Socket socket;
	HashMap<String, PrintWriter> map;
	DefaultListModel<String> userListModel;
	JTextArea chatWindow;
	JTextField chatField;
	PrintWriter printWriter;
	BufferedReader bufferedReader;
	String id;
	ChatServer mainFrame;
	JButton kickButton;
	ChatThread1(Socket socket, HashMap<String, PrintWriter> map, JTextArea chatWindow , JTextField chatField, ChatServer mainFrame,DefaultListModel<String> userListModel,JButton kickButton){
		this.socket = socket;
		this.map = map;
		this.chatWindow = chatWindow;
		this.chatField = chatField;
		this.mainFrame = mainFrame;
		this.userListModel = userListModel;
		this.kickButton = kickButton;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			id = bufferedReader.readLine();// ó�� ���� �ϸ� ������ �޾ƿ�
			this.userListModel.addElement(id);
			this.kickButton.setEnabled(true);
			synchronized(map){//?
				Collection<PrintWriter> collection = map.values();	
				Iterator<PrintWriter> iter = collection.iterator();	
				while(iter.hasNext()){	
					PrintWriter pw = iter.next();
					pw.println(id +" ���� �����Ͽ����ϴ�.");
					pw.flush();
				}	
			}		
			synchronized (map) {// �̾��� ������ ������ �����带 �����ϰ� ����
				map.put(id, printWriter);
			}
			String attendLog = null;
			for(Map.Entry<String, PrintWriter> s : map.entrySet()){
				attendLog = s.getKey() + " ���� �����Ͽ����ϴ�.";
				printWriter.println(attendLog);
				printWriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run(){
		try {
			String message = null;
			while((message = bufferedReader.readLine()) != null){
				if(message.lastIndexOf("/") != -1){//��ɾ� �ν�
					String[] splitMessage = message.split(" ");
					if(splitMessage[0].equals("/r")){//�Ӹ�
						whisper(splitMessage);
					} else if(message.equals("/exit")){//�����
						exeunt();
						exit_From_User_List();
				        break;
					}
				} else if(message.lastIndexOf("creatroom_") != -1) {//�����
					creatRoom(message);
				} else {//ä�ý�
				chatWindow.append(id + " : " + message + "\n");
				broadcast(id + " : " + message);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void exit_From_User_List() {
		for (int i = 0; i < userListModel.getSize(); i++) {
			if (userListModel.getElementAt(i).equals(id)) {
				userListModel.remove(i);
				if(userListModel.getSize() == 0){
					kickButton.setEnabled(false);
				}
			}
		}
	}
	void whisper(String[] splitMessage) {
		synchronized(map){
			Collection<String> collection = map.keySet();	
			Iterator<String> iter = collection.iterator();	
			while(iter.hasNext()){		
				String keyID = iter.next();//?
				if(keyID.equals(splitMessage[1])){
					String chatContent = null;
					for(int i = 2; i < splitMessage.length;i++){
						chatContent += " " + splitMessage[i];
					}
				 map.get(keyID).println("(�ӼӸ�) " + splitMessage[1] + " : " + chatContent);
				 map.get(keyID).flush();
				}
			}	
		}
	}
	void exeunt() {
		try {
			bufferedReader.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.get(id).close();
		map.remove(id);
        broadcast("userdelete " + id);
	}
	void creatRoom(String message) {
		String[] result = message.split(" ");
		printWriter.println("/createnumber "+mainFrame.port+" "+result[2]);
		
		Thread roomThread = new Thread(new Room(mainFrame,mainFrame.port,result[1],result[2]));
		roomThread.start();
		mainFrame.roomMap.put(mainFrame.port, result[2]);
		broadcast("roomin"+"/"+mainFrame.port+"/"+result[1]+"/"+result[2]);				
		mainFrame.port += 10;
	}
	public void broadcast(String message){			
		synchronized(map){		
			Collection<PrintWriter> collection = map.values();	
			Iterator<PrintWriter> iter = collection.iterator();	
			while(iter.hasNext()){				
				PrintWriter pw = iter.next();
				pw.println(message);
				pw.flush();			
			}	
		}		
	}
}