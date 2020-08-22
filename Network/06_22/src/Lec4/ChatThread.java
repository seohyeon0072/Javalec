package Lec4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ChatThread extends Thread{
	
	private Socket sock;
	private String id;
	private BufferedReader br;
	private HashMap<String,PrintWriter> hm;
	
	public ChatThread(Socket sock,HashMap<String,PrintWriter> hm) {
		this.sock=sock;
		this.hm=hm;
		try {
			PrintWriter pw= new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			br=new BufferedReader(new InputStreamReader(sock.getInputStream()));
			id=br.readLine(); 
			broadcast(id+"���� �����Ͽ����ϴ�");
			System.out.println("������ ������� ���̵��"+id+"�Դϴ�");
			
			synchronized(hm){
				hm.put(id,pw);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void run(){
		try {
			String line = null;
			while((line = br.readLine())!=null){ 
				if(line.equals("/quit"))
					break;
				if(line.indexOf("/to")==0){
					//   /to user
					sendmsg(line);
				}else{
					broadcast(id+":"+line);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally {
			synchronized(hm){
				PrintWriter pw = hm.remove(id);
				pw.println("/quit");
				pw.flush();
			}
			broadcast(id+"���� ���� ���� �Ͽ����ϴ�.");
			try {
				if(sock!=null){
					sock.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	} 
	private void sendmsg(String msg) {
		int start =msg.indexOf(" ")+1;
		int end = msg.indexOf(" ",start);
		if(end!=-1){
			String to = msg.substring(start,end);
			System.out.println(to);
			String msg2 = msg.substring(end+1);
			Object obj = hm.get(to);
			if(obj!=null){
				PrintWriter pw = (PrintWriter)obj; 
				pw.println(id + "���� ������ �ӼӸ��� �����̽��ϴ� : "+ msg2);
				pw.flush();
			}//if
		}
	}
	public void broadcast(String msg){
		synchronized(hm){
			Collection<PrintWriter> collection = hm.values(); //printwriter 
			Iterator<PrintWriter> iter = collection.iterator();
			while(iter.hasNext()){
				PrintWriter pw= iter.next();
				pw.println(msg);
				System.out.println(msg);
				pw.flush();
			}
		}
	}
}

