package test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.MalformedInputException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Room extends JFrame implements ActionListener , Runnable{
	JTextField tf_chat;
	JTextArea ta_chat;
	JTextArea ta_list;
	JScrollPane sp, sp1;
	HashMap<String, PrintWriter> map = new HashMap<String, PrintWriter>();	
	int socketnumber;
	String id;
	ChatServer mainS;
	String roomname;
	Room(ChatServer mainS,int socketnumber ,String id, String roomname) {
		this.mainS = mainS;
		this.socketnumber = socketnumber;
		this.id = id;
		this.roomname = roomname;
		this.setTitle(this.roomname);
		tf_chat = new JTextField();
		tf_chat.addActionListener(this);
		
		ta_list = new JTextArea("====접속자====\n");
		sp = new JScrollPane(ta_list);
		
		ta_chat = new JTextArea();
		sp1 = new JScrollPane(ta_chat);
		
		add(sp,"East");
		add(sp1,"Center");
		add(tf_chat,"South");
		
		
		this.setSize(500,400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(tf_chat.getText() != null){
			ta_chat.append("\n"+"서버:"+tf_chat.getText());
			broadcast("서버:"+tf_chat.getText()+"\n");
			tf_chat.setText("");
		}
	}
	public void broadcast(String msg){			
		synchronized(map){		
			Collection<PrintWriter> collection = map.values();	
			Iterator<PrintWriter> iter = collection.iterator();	
			while(iter.hasNext()){				
				PrintWriter pw = iter.next();
				pw.println(msg);
				pw.flush();				
			}	
		}		
	} // broadcast		
	@Override
	public void run() {
			
		try{
			ServerSocket server2 = new ServerSocket(this.socketnumber);	
			ta_chat.append("접속을 기다립니다.\n");
			
			while(true){
				Socket sock2 = server2.accept();// 강제 대기
				ChatThread2 chatthread2 = new ChatThread2(mainS,this,sock2, map, ta_list , ta_chat ,tf_chat);
				chatthread2.start();
				
			} // while			
		}catch(Exception e){	
			System.out.println("server main : " + e);
		}
}
class ChatThread2 extends Thread{
	Socket sock;
	HashMap<String, PrintWriter> map;
	JTextArea ta_user;
	JTextArea ta_chat;
	JTextField tf;
	Room main;
	ChatServer mainS;
	
	PrintWriter pw;
	BufferedReader br, br_id;
	String id;
	
	int cnt;
	
	ChatThread2(ChatServer mainS,Room frame,Socket sock, HashMap<String, PrintWriter> map, JTextArea ta_user , JTextArea ta_chat , JTextField tf){
		this.mainS = mainS;
		this.main = frame;
		this.sock=sock;
		this.map = map;
		this.ta_user = ta_user;
		this.ta_chat = ta_chat;
		this.tf = tf;
		
		//3번
		try {
			pw 
			  = new PrintWriter(
				 new OutputStreamWriter(
					sock.getOutputStream()));
			br
			  = new BufferedReader(
				 new InputStreamReader(
					sock.getInputStream()));
			id = br.readLine();// 처음 접속 하면 무조건 받아옴
			synchronized(map){
				Collection<PrintWriter> collection = map.values();	
				Iterator<PrintWriter> iter = collection.iterator();	
				while(iter.hasNext()){				
					PrintWriter pw = iter.next();
					pw.println(id +" 님이 입장하였습니다.");
					pw.flush();
				}	
			}		
		 // broadcast		
			
			ta_user.append(id+"\n");
			synchronized (map) {// 이안의 내용은 무조건 쓰레드를 중지하고 실행
				map.put(id, pw);
			}
			String sum ="";
			for(Map.Entry<String, PrintWriter>s:map.entrySet()){
				sum += s.getKey()+" 님이 입장하였습니다.\n";
			}
			pw.println(sum);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run(){
		
		try {
			String msg=null;
			while((msg=br.readLine())!=null){
				if(msg.indexOf("creatroom_") != -1){
					System.out.println("창뜨워져");
					String[] msgP = msg.split("_");
				}
				if(msg.lastIndexOf("/") != -1){
				String[] msgP = msg.split(" ");
				if(msgP[0].equals("/r")){
					synchronized(map){		
						Collection<String> collection = map.keySet();	
						Iterator<String> iter = collection.iterator();	
						while(iter.hasNext()){				
							String eid = iter.next();
							if(eid.equals(msgP[1])){
								String eidsum ="";
								for(int i = 2; i < msgP.length;i++){
									eidsum += " "+msgP[i];

								}
							 map.get(eid).println("(귓속말)"+msgP[1]+":"+eidsum);
							 map.get(eid).flush();

							}
										
						}	
					}		
					
					
				}
				else if(msg.equals("/exit")){
					System.out.println("목록갱신신호");
					br.close();
					map.get(id).close();
					map.remove(id);
					Iterator<String> mapIter = map.keySet().iterator();
					ta_user.setText("===접속자===\n");         
			        while(mapIter.hasNext()){ 
			            String key = mapIter.next();
			            ta_user.append(key+"\n");

			       }
			        try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        if(map.size() == 0){
			        	synchronized(mainS.map){		
							Collection<PrintWriter> collection = mainS.map.values();	
							Iterator<PrintWriter> iter = collection.iterator();	
							while(iter.hasNext()){				
								PrintWriter pw = iter.next();
								
								pw.println("roomout"+"/"+main.roomname);
								pw.flush();			
							}	
						}
			        	dispose();
			        }
			        
				
				}
				else if(msgP[0].equals("/invate")){
					synchronized(mainS.map){		
						Collection<String> collection = mainS.map.keySet();	
						Iterator<String> iter = collection.iterator();	
						while(iter.hasNext()){				
							String eid = iter.next();
							if(eid.equals(msgP[1])){
								String eidsum ="";
								for(int i = 2; i < msgP.length;i++){
									eidsum += " "+msgP[i];

								}
							 mainS.map.get(eid).println("/invate"+" "+id+" "+eidsum+" "+main.roomname); // 1번째는 아이디 2번째는 메세지 3번째는 룸네임 (key값)
							 mainS.map.get(eid).flush();

							}
										
						}	
					}		
					
	
				}
				else if(msgP[0].equals("/kick") && (id.equals(main.id))){
					synchronized(map){		
						Collection<PrintWriter> collection = map.values();	
						Iterator<PrintWriter> iter = collection.iterator();	
						while(iter.hasNext()){				
							PrintWriter pw = iter.next();
							pw.println("/exit"+" "+msgP[1]);
							pw.flush();				
						}	
					}
					
					System.out.println("추방실행");
				}
				
				}
				else{
				ta_chat.append("\n"+id+":"+msg);
				broadcast(id+":"+msg);
		
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void broadcast(String msg){			
		synchronized(map){		
			Collection<PrintWriter> collection = map.values();	
			Iterator<PrintWriter> iter = collection.iterator();	
			while(iter.hasNext()){				
				PrintWriter pw = iter.next();
				pw.println(msg);
				pw.flush();				
			}	
		}		
	} // broadcast		
}
}

