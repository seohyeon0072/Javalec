package test;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server extends JFrame implements ActionListener {
	JLabel lbl;
	JPanel pnl;
	JTextArea ta_chat,ta_user;
	JScrollPane sp_chat,sp_user;
	JTextField tf;
	JButton btn;
	PrintWriter pw;
	BufferedReader br;
	private HashMap<String, PrintWriter> map;
	
	
	Server() throws IOException {
	
		pnl = new JPanel();
		lbl = new JLabel("서버");
		ta_chat = new JTextArea("===채팅내용===\n");
		ta_user = new JTextArea("===접속자===\n");
		sp_chat = new JScrollPane(ta_chat);
		sp_user = new JScrollPane(ta_user);
		
		btn = new JButton("강퇴 !");
		tf = new JTextField(45);
		
		this.add(pnl,"South");
		this.add(lbl,"North");
		this.add(sp_chat,"Center");
		this.add(sp_user,"East");
		this.add(pnl,"South");
		pnl.add(tf, new GridLayout(0,1));
		pnl.add(btn, new GridLayout(0,1));
		btn.addActionListener(this);
		this.setSize(600, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		map = new HashMap<String, PrintWriter>();
		
		try{		
			ServerSocket server = new ServerSocket(10001);	
			ta_chat.append("접속을 기다립니다.\n");
			
			while(true){	
				Socket sock = server.accept();// 
			
				(new ChatServerThread01(sock, map, ta_user)).start();				
			} 
			}catch(Exception e){	
			System.out.println("server main : " + e);
		}	
}
	
	
	//퇴장
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		ta_chat.append(tf.getText()+"\n");
//			tf.selectAll();
	
			
			if (e.getSource() == btn) {
				String i = tf.getText();
				System.out.println(i);
				map.get(i).println("강퇴되었습니다.");
				map.get(i).flush();
				//map.get(i).close();
				//map.remove(i);
				tf.setText("");
				ta_user.setText("***접속자***\n");
				for (Entry<String, PrintWriter> l : map.entrySet()) {
					String ll = l.getKey();
					ta_user.append(ll + "\n");
				}
				ta_chat.append(i + "님을 강퇴하였습니다.\n");
				
			}
		}
			
			
			/*
			String s = tf.getText();
			tf.setText(null);
			byte[]buffer = new byte[512];
			if(s == null);
			}else if(s.equals("exit")) {
		*/
			
		
			
			
	

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		new Server();
	}
	
}


	class ChatServerThread01 extends Thread {
		Socket sock;
		HashMap<String, PrintWriter> map;
		JTextArea ta_user;
			 
		PrintWriter pw;
		BufferedReader br;	 
		String id;

		ChatServerThread01(Socket sock, HashMap<String, PrintWriter>map, JTextArea ta_user) {
	
			this.sock = sock;
			this.map = map;
			this.ta_user = ta_user;
	
			try {
				pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
		
				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		
				id = br.readLine();
		
		
		
		
		
			ta_user.append(id +"\n");
			
			synchronized (map) {
				map.put(id, pw);
			}
		} catch (IOException e) {
			e.printStackTrace();
			
			
			} 
		}
		@Override
		public void run(){
			
			
			try {
				String msg=null;
				while((msg=br.readLine())!=null){
					if(msg.equals("/quit")){
						try {
							br.close();						
							map.get(id).close();//
							map.remove(id);	// map에서 삭제
							sock.close();
							setConUsers();
							break;
						
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						broadcast(id+":"+msg);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {			
				try {
					if(br != null){
						br.close();
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
					
		}
		
		void setConUsers(){
			Iterator<String> mapIter = map.keySet().iterator();
			ta_user.setText("===접속자===\n");         
		    while(mapIter.hasNext()){ 
		        String key = mapIter.next();
		        ta_user.append(key+"\n");
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
		} 
		}
		
		
		
	
		
		
		
