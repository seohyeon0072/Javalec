package test;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener{
	JPanel pnl;
	JLabel lbl;
	JTextArea ta;
	JTextField tf;
	JScrollPane sp;
	PrintWriter pw;
	BufferedReader br;
	JButton btn;
	public Client() {
		
		pnl = new JPanel();
		lbl = new JLabel("Ŭ���̾�Ʈ");
		ta = new JTextArea();
		sp = new JScrollPane(ta);
		tf = new JTextField(45);
		btn = new JButton("���� !");
		tf.addActionListener(this);
		btn.addActionListener(this);
		this.add(lbl,"North");
		this.add(sp,"Center");
		this.add(pnl,"South");
		pnl.add(tf, new GridLayout(0,1));
		pnl.add(btn, new GridLayout(0,1));
		
		this.setSize(600, 500);
		this.setVisible(true);
		
		//1��
		
		String id = JOptionPane.showInputDialog("����� ID�� �Է��ϼ���");
		
		Socket socket;		
		try {
			socket = new Socket("127.0.0.1",10001);// accept�� �����
			//3��
			pw 
			  = new PrintWriter(
				 new OutputStreamWriter(
				  socket.getOutputStream()));
			br
			  = new BufferedReader(
				 new InputStreamReader(
					socket.getInputStream()));
			
			lbl.setText(id+ "�� �ݰ����ϴ� ");
			pw.println(id);
			pw.flush();
			(new ClientThread01(br, ta, pw, socket)).start();
			
			
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e){					
					try {					
						pw.println("/quit");
						pw.flush();
						br.close();
						pw.close();
						socket.close();
						System.exit(0);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
			});
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {		
		tf.selectAll();
		if(tf.getText().equals("/quit")){
			pw.println("/quit");
			pw.flush();					
			System.exit(0);
		}else{
			pw.println(tf.getText());
			pw.flush();			
		}
		
		
	}
	
	public static void main(String[] args) {
		new Client();
	}
}

class ClientThread01 extends Thread{
	BufferedReader br;
	JTextArea ta;
	PrintWriter pw;
	Socket sockt;
	
	ClientThread01(BufferedReader br, JTextArea ta, PrintWriter pw, Socket sock){
		this.br=br;
		this.ta=ta;
		this.pw=pw;
		this.sockt=sock;
	}
	
	@Override
	public void run(){
		String line = null;	
		try {
			while((line = br.readLine()) != null){

				if(line.equals("����Ǿ����ϴ�.")){
					pw.println("/quit");
					pw.flush();
					break;
				}
//				ta.append(line + "\n");				 
//				ta.setCaretPosition(ta.getDocument().getLength());
//				//���� �ö󰡴¤��κ�
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {			
			try {
				if(br != null){
					br.close();
				}
				if(pw != null){
					pw.close();
				}
				if(sockt != null){
					sockt.close();					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.exit(1);
	}
}






