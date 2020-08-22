package test;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn extends JDialog implements ActionListener{
	JPanel pn, pn1, pn2, pn3;
	JLabel lb_id, lb_ip;
	JTextField tf_id, tf_ip;
	JPasswordField pf_pw;
	JButton bt_ok, bt_no, bt_signin;
	String str_id, str_ip;
	ArrayList<String> list = new ArrayList<String>();
	
	LogIn(){
		this.setTitle("�α��� â");
		this.setLayout(new BorderLayout());
		//�г� ����
		pn = new JPanel();
		pn.setLayout(new FlowLayout(FlowLayout.CENTER));
		pn1 = new JPanel();
		pn1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pn2 = new JPanel();
		pn2.setLayout(new FlowLayout(FlowLayout.CENTER));
		pn3 = new JPanel();
		pn3.setLayout(new BoxLayout(pn3, BoxLayout.Y_AXIS));
		//���̺�
		lb_id = new JLabel("���̵�");
		lb_ip = new JLabel("ip��ȣ");
		
		
		//�Է� ����
		tf_id = new JTextField(10);
		tf_ip = new JTextField(10);
		tf_ip.setText("localhost");
		
		//��ư
		bt_ok = new JButton("Ȯ��");
//		bt_no = new JButton("���");
		
		//Ű ������
		tf_id.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode() == 10){
					KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.getFocusOwner().transferFocus();
				}
			}
		});
			
		tf_ip.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode() == 10){
					String ip = tf_ip.getText();
					String id = tf_id.getText();
					if(id.length()>3 && id.length()<8 && ip.length() > 8){
						for(int i = 0; i <= list.size(); i++){
							if (list.size() == 0){
								list.add(id);
								setId(id);
								setIp(ip);
								dispose();
								new ChatProgramtest(id,ip);
								break;
							}
							else if(!(id.equals(list.get(i)))){
								list.add(id);
								setId(id);
								setIp(ip);
								dispose();
								new ChatProgramtest(id,ip);
							}
							else {
								JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�. �ٽ� ������ֽʽÿ�.");
								tf_id.selectAll();
							}
							System.out.println(list.size());
						}
					} else{
						if((!(id.length()>3 && id.length()<8))&&(ip.length() > 8)){ 
							JOptionPane.showMessageDialog(null, "3~8�� ������ ���̵� ����ÿ�.");
							tf_id.selectAll();
						}else if ((!(ip.length() > 8))&&(id.length()>3 && id.length()<8)){
							JOptionPane.showMessageDialog(null, "ip��ȣ�� Ȯ���Ͻÿ�.");
							tf_ip.selectAll();
						}else{
							JOptionPane.showMessageDialog(null, "id�� ip�� ��Ȯ���Ͻÿ�.");
							tf_id.setText("");
							tf_ip.setText("");
						}
					}
				}
			}
		});
		
		//�׼� ������
		bt_ok.addActionListener(this);
		
		//�гο� ���̵� ��� ���̱�
		pn.add(lb_id);
		pn.add(tf_id);
		pn2.add(lb_ip);
		pn2.add(tf_ip);
		
		pn1.add(bt_ok);

		pn3.add(pn);
		pn3.add(pn2);
		
		add(pn3, "Center");
		add(pn1,"South");
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(300,150);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt_ok){
			String ip = tf_ip.getText();
			String id = tf_id.getText();
			if(id.length()>3 && id.length()<8 && ip.length() > 8){
				for(int i = 0; i <= list.size(); i++){
					if (list.size() == 0){
						list.add(id);
						setId(id);
						setIp(ip);
						dispose();
						new ChatProgramtest(id,ip);
						break;
					}
					else if(!(id.equals(list.get(i)))){
						list.add(id);
						setId(id);
						setIp(ip);
						dispose();
						new ChatProgramtest(id,ip);
					}
					else {
						JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�. �ٽ� ������ֽʽÿ�.");
						tf_id.selectAll();
					}
					System.out.println(list.size());
				}
			} else{
				if((!(id.length()>3 && id.length()<8))&&(ip.length() > 8)){ 
					JOptionPane.showMessageDialog(null, "3~8�� ������ ���̵� ����ÿ�.");
					tf_id.selectAll();
				}else if ((!(ip.length() > 8))&&(id.length()>3 && id.length()<8)){
					JOptionPane.showMessageDialog(null, "ip��ȣ�� Ȯ���Ͻÿ�.");
					tf_ip.selectAll();
				}else{
					JOptionPane.showMessageDialog(null, "id�� ip�� ��Ȯ���Ͻÿ�.");
					tf_id.setText("");
					tf_ip.setText("");
				}
			}
		}
	}
	
	void setId(String id){	str_id = id;}
	String getId (){	return str_id;	}
	
	void setIp(String ip){	str_ip = ip;}
	String getIp(){		return str_ip;	}
	
	public static void main(String args[]){
		new LogIn();
	}
}
