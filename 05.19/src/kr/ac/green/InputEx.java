package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InputEx extends JFrame implements ActionListener {

	private JTextField tfInput;
	private JTextArea taOutput;
	private JButton btnSend;

	public InputEx() {
		tfInput = new JTextField(30);
		btnSend = new JButton("Send");
		btnSend.setPreferredSize(new Dimension(55, 22));
		btnSend.setMargin(new Insets(2,2,2,2));
		taOutput = new JTextArea(10, 40);
		taOutput.setEditable(false);

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(tfInput);
		pnlSouth.add(btnSend);

		add(new JScrollPane(taOutput), BorderLayout.CENTER);

		add(pnlSouth, BorderLayout.SOUTH);

		tfInput.addActionListener(this);
		btnSend.addActionListener(this);

		setTitle("Fake Chat");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) {
		new InputEx();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		taOutput.append(tfInput.getText() + "\n");
		tfInput.requestFocus();
		tfInput.selectAll();
	}
}
