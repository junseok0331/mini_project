package com.javalab.awt.menu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BankForm extends JFrame implements ActionListener {

	public DataBaseClass db;

	private JTextField accField;
	private JTextField nameField;
	private JTextField balanceField;

	public BankForm() {
	}

	public BankForm(DataBaseClass db) {
		// 데이터베이스 전담 클래스의 객체를 전달받아서 내가 선언한 변수에 저장함
		this.db = db;

		// 텍스트필드(입력칸)와 레이블 설정
		accField = new JTextField(10);
		nameField = new JTextField(10);
		balanceField = new JTextField(10);

		JLabel accLabel = new JLabel("계좌번호:");
		JLabel nameLabel = new JLabel("예금주:");
		JLabel balanceLabel = new JLabel("초기 금액:");

		// 계좌 생성 버튼 생성
		JButton addButton = new JButton("계좌 생성하기");

		// 버튼 클릭시 actionPerformed()가 호출됨.
		addButton.addActionListener(this); // 액션 리스터 부착  this는 BankForm

		// 닫기 버튼 생성
		JButton closeButton = new JButton("이전 메뉴");

		// 버튼 클릭시 actionPerformed()가 호출됨.
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();  //해당창만 닫힌다.

				// 이동해갈 메인 메뉴 생성
				MainMenu mainMenu = new MainMenu(db);

				// 메인 메뉴 보이기
				mainMenu.setVisible(true);
			}
		});

		// 버튼, 레이블, 텍스트 필드를 배치할 input 패널을 배치할 메인 패널 생성
		// 즉, 메인 패널 위에 inputPanel이 놓이게 됨.
		JPanel mainPanel = new JPanel();

		// 메인 패널을 BoarderLayout으로 만듦.
		mainPanel.setLayout(new BorderLayout());

		// 버튼, 레이블, 텍스트 필드를 배치할 input 패널
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(3, 2));
		inputPanel.add(accLabel);
		inputPanel.add(accField);
		inputPanel.add(nameLabel);
		inputPanel.add(nameField);
		inputPanel.add(balanceLabel);
		inputPanel.add(balanceField);

		// 메인 패널에 input패널 부착
		mainPanel.add(inputPanel, BorderLayout.SOUTH);

		// 메인 패널에 "추가"버튼 부착
		mainPanel.add(addButton, BorderLayout.EAST);
		mainPanel.add(closeButton, BorderLayout.NORTH);

		// 프레임(윈도우 창) 설정
		setTitle("계좌 정보 입력");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 메인 패널을 프레임의 루트 컨텐트에 저장
		setContentPane(mainPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		/**
		 * 컴포넌트(버튼, 텍스트필드, 레이블)가 프레임에 쌓이는 순서 - 프레임 > 루트 컨텐트 > 메인 패널 > input패널 >
		 * 버튼,텍스트필드,레이블
		 */
	}

	/**
	 * 액션 리스너 - 계좌 생성 버튼 클릭시 실행되는 메소드로 데이터베이스 클래스에 계좌를 추가시킨다. 즉, 계좌 ArrayList에 계좌 하나
	 * 추가함.
	 */
	public void actionPerformed(ActionEvent e) {
		String acc = accField.getText();			// 화면에서 입력된 값을 임시 변수에 저장
		String name = nameField.getText();
        int balance = 0;

        try {	// 입력한 값이 정수가 아닐 시 발생하는 예외 처리
			balance = Integer.parseInt(balanceField.getText());
		} catch (NumberFormatException ex) {	
			JOptionPane.showMessageDialog(this, "출금액은 정수로 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
			accField.setText("");
			nameField.setText("");
			balanceField.setText("");
			return;
		}

		Bank bank = new Bank(acc, name, balance); // 화면에서 입력받은 데이터를 계좌 객체 생성

		// 정보를 입력하지 않거나 0미만의 금액을 초기금액으로 설정 시 경고창 노출 후 다시 입력
        if (acc == "" || acc == null || bank.getAcc() == "" || bank.getName() == null || balance < 0 ) {  
			JOptionPane.showMessageDialog(this, "잘못된 정보를 입력했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
			accField.setText("");
			nameField.setText("");
			balanceField.setText("");
			return;
		} else {
			this.db.addBank(bank);
			JOptionPane.showMessageDialog(this, "등록완료");
			dispose();
		}
        
		// 현재까지 등록된 계좌 수 확인
		System.out.println("계좌 객체 등록 완료 계좌 수 : " + db.getBankCount());

		// 계좌 생성 끝나고 현재의 화면 닫기
		this.dispose();

		// 이동해갈 메인 메뉴 생성
		MainMenu mainMenu = new MainMenu(db);

		// 메인 메뉴 보이기
		mainMenu.setVisible(true);
	}
}
