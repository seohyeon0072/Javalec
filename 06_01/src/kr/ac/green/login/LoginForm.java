package kr.ac.green.login;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;


public class LoginForm extends JFrame implements ActionListener {
	private JTextComponent tfId;
	private JTextComponent pfPw;
	
	private JButton btnLogin;
	private JButton btnJoin;
	
	private Vector<User> list;
	private boolean isUpdated;
	
	public LoginForm() {
		loadData();
		init();
		setDisplay();
		addListener();
		showFrame();
	}

	private void init() {	
		tfId = LoginUtils.getTextComponent(LoginUtils.TEXT);
		pfPw = LoginUtils.getTextComponent(LoginUtils.PASSWORD);;		
		btnJoin = LoginUtils.getButton("Join");		
		btnLogin = LoginUtils.getButton("Login");
	}
	private void loadData() {
		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(
				new File(LoginUtils.DIR, LoginUtils.FILE)		
			);
			dis = new DataInputStream(fis);
			
			list = new Vector<User>();
			
			while( dis.available() > 0) {
				String uid = dis.readUTF();
				String upw = dis.readUTF();
				String uname = dis.readUTF();
				String unick = dis.readUTF();
				String ugender = dis.readUTF();
				list.add(new User(uid, upw, uname, unick, ugender));
			}
		} catch(FileNotFoundException e) {
			list = new Vector<User>();
			File dir = new File(LoginUtils.DIR).getAbsoluteFile();
			if(!dir.exists()) {
				dir.mkdir();
			}
		} catch(IOException e) {
			JOptionPane.showMessageDialog(
				this,
				"데이터 파일 로딩 실패"
			);
		} finally {
			closeAll(dis, fis);
		}
	}
	private void closeAll(Closeable... c) {
		for(Closeable temp : c) {
			try {
				temp.close();
			} catch(Exception e){}
		}
	}
	private void setDisplay() {		
		JPanel pnlText = new JPanel(new GridLayout(0,1));
		JPanel pnlInput = new JPanel(new GridLayout(0,1));
				
		pnlText.add(LoginUtils.getLabel("ID"));
		pnlText.add(LoginUtils.getLabel("Password"));
		
		JPanel pnlId = new JPanel();
		pnlId.add(tfId);
		JPanel pnlPw = new JPanel();
		pnlPw.add(pfPw);
		pnlInput.add(pnlId);
		pnlInput.add(pnlPw);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnLogin);
		pnlSouth.add(btnJoin);
				
		JPanel pnlMain = new JPanel(new BorderLayout());
		pnlMain.add(pnlText, BorderLayout.WEST);
		pnlMain.add(pnlInput, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		
		pnlMain.setBorder(new EmptyBorder(5, 10, 5, 10));
		
		add(pnlMain, BorderLayout.CENTER);
	}

	private void addListener() {
		btnLogin.addActionListener(this);
		btnJoin.addActionListener(this);
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we) {
				int result = JOptionPane.showConfirmDialog(
					LoginForm.this,
					"exit?",
					"question",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE
				);
				if(result == JOptionPane.YES_OPTION) {
					if(isUpdated) {
						result = save();
					}
					if(result == JOptionPane.YES_OPTION) {
						System.exit(0);
					}					
				}
			}
		});
	}
	private int save() {
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		int result = JOptionPane.YES_OPTION;
		try {
			fos = new FileOutputStream(
				new File(LoginUtils.DIR, LoginUtils.FILE)
			);
			dos = new DataOutputStream(fos);
			
			for(User user : list) {
				dos.writeUTF(user.getUid());
				dos.writeUTF(user.getUpw());
				dos.writeUTF(user.getUname());
				dos.writeUTF(user.getUnick());
				dos.writeUTF(user.getUgender());
			}
			dos.flush();
		} catch(IOException e) {
			result = JOptionPane.showConfirmDialog(
				LoginForm.this,
				"error occurred(during save user list). do you want to exit?",
				"question",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
		} finally {
			closeAll(dos, fos);
		}
		return result;
	}
	private void showFrame() {
		setTitle("Login");
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();		
		if(src == btnLogin) {
			JTextComponent input = null;
			String msg = "welcome!!";
			User user = null;
			if(LoginUtils.isEmpty(tfId)) {
				msg = "input your ID";
				input = tfId;
			} else {
				if(LoginUtils.isEmpty(pfPw)) {
					msg = "input your password";
					input = pfPw;
				} else {
					String uid = tfId.getText();
					String upw = pfPw.getText();
					user = findUser(uid);
					if( user == null ){
						msg = "check your ID";
						input = tfId;
					} else {
						if(!upw.equals(user.getUpw())) { 
							msg = "check your password";
							input = pfPw;
						}
					}
				}
			}
			JOptionPane.showMessageDialog(
				this,
				msg,
				"Information",
				JOptionPane.INFORMATION_MESSAGE	
			);
			if(input != null) {
				input.requestFocus();
			} else {
				clear();
				setVisible(false);
				new InformationForm(this, user);				
			}
		} else {			
			clear();
			setVisible(false);			
			new JoinForm(this);			
		}
	}

	private void clear() {
		tfId.setText("");
		pfPw.setText("");
	}
	
	public User findUser(String userId) {
		int idx = list.indexOf(new User(userId));
		if(idx>=0) {
			return list.get(idx);
		} else {
			return null;
		}		
	}

	public void addUser(User user) {
		if(findUser(user.getUid()) == null) {
			list.add(user);
			isUpdated = true;
		}
	}

	public void removeUser(User user) {
		list.remove(user);
		isUpdated = true;
	}
	public static void main(String[] args) {
		new LoginForm();
	}
}

































