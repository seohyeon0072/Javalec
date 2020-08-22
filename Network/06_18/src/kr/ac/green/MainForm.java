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
	
	//메뉴 바
	private JMenuBar mBar;
	// 메뉴
	private JMenu mFile;
	// 메뉴아이템
	private JMenuItem miOpen;
	private JMenuItem miExit;
	// 팝업메뉴
	private JPopupMenu pMenu;
	// 팝업메뉴 아이템
	private JRadioButtonMenuItem rmiKorean;
	private JRadioButtonMenuItem rmiEnglish;
	// 메인
	private JLabel lblMain;
	// 상태
	private JLabel lblStatus;
	// 언어 구분
	public static final int KOR = 0;
	public static final int ENG = 1;
	// properties 모음
	private Properties[] propArr;
	// 언어 모음
	private String[] languages ={"korean", "english"};
	// 기본값
	private int currentLang = KOR;
	
	
	public MainForm() {
		// 언어 개수만큼 properties 객체초기화
		propArr = new Properties[languages.length];
		// 언어 모음에 담긴 언어들 일괄 load
		// 사용자가 메뉴 호출할떄마다 부르면 느림
		for(int idx = 0; idx < languages.length; idx++){
			// 인덱스값 = 각 언어 - > 넘겨줌
			loadLangPack(idx);
		}
		
		init();
		setDisplay();
		addListeners();
		showFrame();
	}
	
	/* properties파일 load 메소드
	 * parameter : int lang
	 * return : void 
	 * summary : 언어 배열 인덱스가 각국 언어로 담겨 있음
	*/
	
	private void loadLangPack(int lang){
		FileReader fr = null;
		
		try {
			// lang 언어, e.g.) -> korean = 0 -> korean . properties  
			fr = new FileReader(languages[lang] + ".properties");
			// 인덱스에 일치하는 properties 파일 생성
			propArr[lang] = new Properties();
			// 인덱스에 엘치하는 properties 파일 읽기
			propArr[lang].load(fr);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// properties파일 존재시
			if(fr != null){
				try{
					// 종료
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
		
		rmiKorean = new JRadioButtonMenuItem("한국어", true);
		rmiEnglish = new JRadioButtonMenuItem("English");
		
		// 버튼 그룹화
		ButtonGroup group = new ButtonGroup();
		group.add(rmiEnglish);
		group.add(rmiKorean);
		
		pMenu.add(rmiKorean);
		pMenu.add(rmiEnglish);
		
		lblMain = new JLabel();
		// 수평 정렬
		lblMain.setHorizontalAlignment(JLabel.CENTER);
		lblStatus = new JLabel();
		
		// 기본 언어 한국어 properties파일 읽어서 초기값 적용
		setLanguage(currentLang);
		
	}
	
	private void setDisplay(){
		// 메뉴바 배치
		setJMenuBar(mBar);
		add(lblMain, BorderLayout.CENTER);
		add(lblStatus, BorderLayout.SOUTH);
	}
	
	private void addListeners(){
		lblMain.addMouseListener(new MouseAdapter() {
			// 마우스 올렸을때
			@Override
			public void mousePressed(MouseEvent e) {
				showPopup(e);
			}
			// 마우스 땟을때
			@Override
			public void mouseReleased(MouseEvent e) {
				showPopup(e);
			}
		});
		
		ItemListener iListener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				// ItemEvent.SELECTED 1리턴
				if(e.getStateChange() == ItemEvent.SELECTED){
					if(e.getSource() == rmiKorean){
						// 0
						currentLang = KOR;
					}else{
						// 1
						currentLang = ENG;
					}
				}
				// 언어 int값 setLanguage 넘겨줌
				setLanguage(currentLang);
				
			}
		};
		rmiKorean.addItemListener(iListener);
		rmiEnglish.addItemListener(iListener);
	}
	// 팝업 메뉴창 출현 위치, isPopupTrigger() : 오른쪽 마우스
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
	
	// 해당하는 properties -> load -> key값 가져와서 설정 
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
