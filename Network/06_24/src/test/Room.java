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
		
		ta_list = new JTextArea("====������====\n");
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
			ta_chat.append("\n"+"����:"+tf_chat.getText());
			broadcast("����:"+tf_chat.getText()+"\n");
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
			ta_chat.append("������ ��ٸ��ϴ�.\n");
			
			while(true){
				Socket sock2 = server2.accept();// ���� ���
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
		
		//3��
		try {
			pw 
			  = new PrintWriter(
				 new OutputStreamWriter(
					sock.getOutputStream()));
			br
			  = new BufferedReader(
				 new InputStreamReader(
					sock.getInputStream()));
			id = br.readLine();// ó�� ���� �ϸ� ������ �޾ƿ�
			synchronized(map){
				Collection<PrintWriter> collection = map.values();	
				Iterator<PrintWriter> iter = collection.iterator();	
				while(iter.hasNext()){				
					PrintWriter pw = iter.next();
					pw.println(id +" ���� �����Ͽ����ϴ�.");
					pw.flush();
				}	
			}		
		 // broadcast		
			
			ta_user.append(id+"\n");
			synchronized (map) {// �̾��� ������ ������ �����带 �����ϰ� ����
				map.put(id, pw);
			}
			String sum ="";
			for(Map.Entry<String, PrintWriter>s:map.entrySet()){
				sum += s.getKey()+" ���� �����Ͽ����ϴ�.\n";
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
					System.out.println("â�߿���");
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
							 map.get(eid).println("(�ӼӸ�)"+msgP[1]+":"+eidsum);
							 map.get(eid).flush();

							}
										
						}	
					}		
					
					
				}
				else if(msg.equals("/exit")){
					System.out.println("��ϰ��Ž�ȣ");
					br.close();
					map.get(id).close();
					map.remove(id);
					Iterator<String> mapIter = map.keySet().iterator();
					ta_user.setText("===������===\n");         
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
							 mainS.map.get(eid).println("/invate"+" "+id+" "+eidsum+" "+main.roomname); // 1��°�� ���̵� 2��°�� �޼��� 3��°�� ����� (key��)
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
					
					System.out.println("�߹����");
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

