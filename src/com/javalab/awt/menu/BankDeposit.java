package com.javalab.awt.menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BankDeposit extends JFrame implements ActionListener {

	private DataBaseClass db;
	private JTextField accField;
	private JTextField depositField;

	public BankDeposit(DataBaseClass db) {
		this.db = db;	// 데이터베이스 전담 클래스의 객체를 전달받아서 내가 선언한 변수에 저장함

		// UI 생성
		accField = new JTextField(10);
		depositField = new JTextField(10);
		
		JLabel accLabel = new JLabel("계좌번호:");
		JLabel balanceLabel = new JLabel("입금 금액:");
		
		JButton depositButton = new JButton("입금하기");
		depositButton.addActionListener(this);

		JButton closeButton = new JButton("이전 메뉴");	// 닫기 버튼 생성
		closeButton.addActionListener(new ActionListener() { // 버튼 클릭시 익명 구현 객체 actionPerformed()가 호출됨.
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
				MainMenu mainMenu = new MainMenu(db);	// 이동해갈 메인 메뉴 생성
				mainMenu.setVisible(true);	// 메인 메뉴 보이기
			}
		});

		JPanel mainPanel = new JPanel();			// 메인 패널 생성
		mainPanel.setLayout(new BorderLayout());	// 메인 패널 BorderLayout으로 세팅

		JPanel inputPanel = new JPanel();			// 입력 패널 생성 (메인 패널 위에 덮어짐)
		inputPanel.setLayout(new GridLayout(2, 2));	// 입력 패널 2x2 GridLayout으로 세팅
		inputPanel.add(accLabel);
		inputPanel.add(accField);
		inputPanel.add(balanceLabel);
		inputPanel.add(depositField);

		mainPanel.add(inputPanel, BorderLayout.CENTER);
		mainPanel.add(depositButton, BorderLayout.SOUTH);
		mainPanel.add(closeButton, BorderLayout.NORTH);

		setTitle("예금 메뉴");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(mainPanel);
		pack();
		setLocationRelativeTo(null);	// 창을 모니터 중앙에 띄우기
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acc = accField.getText();
		int amount = 0;

		try {
			amount = Integer.parseInt(depositField.getText()); //depositField.getText를 int타입으로 바꿔 amount에 넣음
		} catch (NumberFormatException ex) {  //실수나 소숫점이 들어왔을때 예외처리를 해주고 밑에 메시지가 출력되게 한다.
			JOptionPane.showMessageDialog(this, "입금액은 정수로 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Bank bank = db.findBank(acc);

		if (bank == null) {
			JOptionPane.showMessageDialog(this, "입력하신 계좌번호는 존재하지 않습니다.", "Error", JOptionPane.ERROR_MESSAGE);
			accField.setText("");
			depositField.setText("");
			return;
		} else if (amount < 0) {
			JOptionPane.showMessageDialog(this, "잘못된 정보를 입력했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
			accField.setText("");
			depositField.setText("");
			return;
		} else {
			bank.deposit(amount);
			JOptionPane.showMessageDialog(this, amount + "원이 예금되었습니다.", "Success", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}

		this.dispose();	// 계좌 생성 끝나고 현재의 화면 닫기
		MainMenu mainMenu = new MainMenu(db);	// 이동해갈 메인 메뉴 생성
		mainMenu.setVisible(true);	// 메인 메뉴 보이기
	}
}
