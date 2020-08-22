package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

public class MainForm extends JFrame{
	
	//�޴� ��
	private JMenuBar mBar;
	// �޴�
	private JMenu mFile;
	// �޴�������
	private JMenuItem miOpen;
	private JMenuItem miExit;
	// �˾��޴�
	private JPopupMenu pMenu;
	// �˾��޴� ������
	private JRadioButtonMenuItem rmiKorean;
	private JRadioButtonMenuItem rmiEnglish;
	// ����
	private JLabel lblMain;
	// ����
	private JLabel lblStatus;
	// ��� ����
	public static final int KOR = 0;
	public static final int ENG = 1;
	// properties ����
	private Properties[] propArr;
	// ��� ����
	private String[] languages ={"korean", "english"};
	// �⺻��
	private int currentLang = KOR;
	
	
	public MainForm() {
		// ��� ������ŭ properties ��ü�ʱ�ȭ
		propArr = new Properties[languages.length];
		// ��� ������ ��� ���� �ϰ� load
		// ����ڰ� �޴� ȣ���ҋ����� �θ��� ����
		for(int idx = 0; idx < languages.length; idx++){
			// �ε����� = �� ��� - > �Ѱ���
			loadLangPack(idx);
		}
		
		init();
		setDisplay();
		addListeners();
		showFrame();
	}
	
	/* properties���� load �޼ҵ�
	 * parameter : int lang
	 * return : void 
	 * summary : ��� �迭 �ε����� ���� ���� ��� ����
	*/
	
	private void loadLangPack(int lang){
		FileReader fr = null;
		
		try {
			// lang ���, e.g.) -> korean = 0 -> korean . properties  
			fr = new FileReader(languages[lang] + ".properties");
			// �ε����� ��ġ�ϴ� properties ���� ����
			propArr[lang] = new Properties();
			// �ε����� ��ġ�ϴ� properties ���� �б�
			propArr[lang].load(fr);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// properties���� �����
			if(fr != null){
				try{
					// ����
					fr.close();
				}catch(IOException e){}
			}
		}
	}
	
	private void init(){
		mBar = new JMenuBar();
		mFile = new JMenu();
		miOpen = new JMenuItem();
		miExit = new JMenuItem();
		
		mFile.add(miOpen);
		mFile.addSeparator();
		mFile.add(miExit);
		
		mBar.add(mFile);
		
		pMenu = new JPopupMenu();
		
		rmiKorean = new JRadioButtonMenuItem("�ѱ���", true);
		rmiEnglish = new JRadioButtonMenuItem("English");
		
		// ��ư �׷�ȭ
		ButtonGroup group = new ButtonGroup();
		group.add(rmiEnglish);
		group.add(rmiKorean);
		
		pMenu.add(rmiKorean);
		pMenu.add(rmiEnglish);
		
		lblMain = new JLabel();
		// ���� ����
		lblMain.setHorizontalAlignment(JLabel.CENTER);
		lblStatus = new JLabel();
		
		// �⺻ ��� �ѱ��� properties���� �о �ʱⰪ ����
		setLanguage(currentLang);
		
	}
	
	private void setDisplay(){
		// �޴��� ��ġ
		setJMenuBar(mBar);
		add(lblMain, BorderLayout.CENTER);
		add(lblStatus, BorderLayout.SOUTH);
	}
	
	private void addListeners(){
		lblMain.addMouseListener(new MouseAdapter() {
			// ���콺 �÷�����
			@Override
			public void mousePressed(MouseEvent e) {
				showPopup(e);
			}
			// ���콺 ������
			@Override
			public void mouseReleased(MouseEvent e) {
				showPopup(e);
			}
		});
		
		ItemListener iListener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				// ItemEvent.SELECTED 1����
				if(e.getStateChange() == ItemEvent.SELECTED){
					if(e.getSource() == rmiKorean){
						// 0
						currentLang = KOR;
					}else{
						// 1
						currentLang = ENG;
					}
				}
				// ��� int�� setLanguage �Ѱ���
				setLanguage(currentLang);
				
			}
		};
		rmiKorean.addItemListener(iListener);
		rmiEnglish.addItemListener(iListener);
	}
	// �˾� �޴�â ���� ��ġ, isPopupTrigger() : ������ ���콺
	private void showPopup(MouseEvent me){
		if(me.isPopupTrigger()){
			pMenu.show(lblMain, me.getX(), me.getY());
		}
	}
	
	private void showFrame(){
		setTitle("International System Demo");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	// �ش��ϴ� properties -> load -> key�� �����ͼ� ���� 
	private void setLanguage(int lang){
		Properties prop = propArr[lang];
		mFile.setText(prop.getProperty("MainForm.fileMenu"));
		lblMain.setText(prop.getProperty("MainForm.label"));
		miOpen.setText(prop.getProperty("MainForm.open"));
		miExit.setText(prop.getProperty("MainForm.exit"));
		lblStatus.setText(prop.getProperty("MainForm.status"));
		
	}
	
	public static void main(String[] args) {
		new MainForm();
	}
	
}
