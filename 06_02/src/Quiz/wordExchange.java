package Quiz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;

public class wordExchange extends JFrame {
	private JLabel lbl;
	private JMenuBar mBar;
	private JMenu mFile;
	private JMenuItem save;
	private JMenuItem open;
	private JPopupMenu pm;
	private JRadioButton kor;
	private JRadioButton eng;
	private Properties prop;

	public wordExchange() {
		load();
		init();
		setDisplay();
		addListener();
		showFrame();
	}

	private void init() {
		mBar = new JMenuBar();
		mFile = new JMenu("File");
		save = new JMenuItem("Save");
		open = new JMenuItem("Open");
		mFile.add(save);
		mFile.add(open);
		mBar.add(mFile);
		lbl = new JLabel("Label", JLabel.CENTER);
		// 팝업 메뉴
		pm = new JPopupMenu();
		kor = new JRadioButton("한글");
		eng = new JRadioButton("영어");
		pm.add(kor);
		pm.add(eng);
		ButtonGroup group = new ButtonGroup();
		group.add(kor);
		group.add(eng);
		Set<?> keys = prop.keySet();
		for(Object key : keys) {
			System.out.println(key.toString());
		}
	}

	private void setDisplay() {
		setJMenuBar(mBar);
		add(lbl, BorderLayout.CENTER);
	}

	private void addListener() {
		// TODO Auto-generated method stub
		lbl.addMouseListener(new MouseAdapter() {
			// os마다 팝업 신호가 다름
			@Override
			public void mousePressed(MouseEvent me) {
				showPopup(me);
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				showPopup(me);
			}

		});
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="한글"){
					lbl.setText(prop.getProperty("Label"));
					mFile.setText(prop.getProperty("File"));
					save.setText(prop.getProperty("Save"));
					open.setText(prop.getProperty("Open"));
				}else{
					lbl.setText(prop.getProperty("레이블"));
					mFile.setText(prop.getProperty("파일"));
					save.setText(prop.getProperty("저장"));
					open.setText(prop.getProperty("열기"));
				}
			
			}

		};
		kor.addActionListener(listener);
		eng.addActionListener(listener);
	}

	private void showPopup(MouseEvent me) {

		if (me.isPopupTrigger()) {
			pm.show(lbl, me.getX(), me.getY());
		}
	}

	private void showFrame() {
		// TODO Auto-generated method stub
		setSize(500, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void load() {
		prop = new Properties();
		try (FileInputStream fis = new FileInputStream("lang.properties")) {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	 	PropertiesEx a = new PropertiesEx();
		a.save();
		a.load(); 
		new wordExchange();
	}

}
