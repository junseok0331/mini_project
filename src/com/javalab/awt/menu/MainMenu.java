package com.javalab.awt.menu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu extends JFrame implements ActionListener {
	
	public DataBaseClass db;
	
	// GUI 구성요소
	private JLabel titleLabel;
	private JButton bankButton;
	private JButton bankListButton;
	private JButton withdrawButton;
	private JButton depositButton;
	private JButton exitButton;

	public MainMenu(DataBaseClass db) {
		
		this.db = db;
		
		// Set up the GUI components
		setTitle("계좌 관리 프로그램");
		setLayout(new GridLayout(6,1));
		setSize(300, 400);

		// 타이틀 레이블
		titleLabel = new JLabel("원하시는 메뉴를 선택하세요.");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel);

		// 계좌 등록 버튼
		bankButton = new JButton("1. 계좌 등록");
		bankButton.addActionListener(this);
		add(bankButton);

		// 계좌 리스트 버튼
		bankListButton = new JButton("2. 계좌 조회");
		bankListButton.addActionListener(this);
		add(bankListButton);

		// 예금 버튼
		depositButton = new JButton("3. 예금");
		depositButton.addActionListener(this);
		add(depositButton);
		
		// 출금 버튼
		withdrawButton = new JButton("4. 출금");
		withdrawButton.addActionListener(this);
		add(withdrawButton);
		
		// 종료 버튼
		exitButton = new JButton("5. 종 료");
		exitButton.addActionListener(this);
		add(exitButton);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// Handle button clicks

		if (e.getSource() == bankButton) {
            System.out.println("계좌 생성");
            
            // 현재 화면 닫기                
            this.dispose();
            
            // 이동해갈 화면 객체 생성
            BankForm bankForm = new BankForm(db);
            
            // 이동해갈 화면 보이기
            bankForm.setVisible(true);
            
        } else if (e.getSource() == bankListButton) {
            System.out.println("계좌 리스트");            
            // 현재 화면 닫기                
            this.dispose();            
            // 이동해갈 화면 객체 생성
            BankList bankList = new BankList(db);            
            // 이동해갈 화면 보이기
            bankList.setVisible(true);            
        }
		else if (e.getSource() == withdrawButton) {
			System.out.println("출금");
			// 현재 화면 닫기
			this.dispose();
			// 이동해갈 화면 객체 생성
			BankWithdraw bankWithdraw = new BankWithdraw(db);
			// 이동해갈 화면 보이기
			bankWithdraw.setVisible(true);
		}
		else if (e.getSource() == depositButton) {
			System.out.println("예금");
			// 현재 화면 닫기
			this.dispose();
			// 이동해갈 화면 객체 생성
			BankDeposit bankDeposit = new BankDeposit(db);
			// 이동해갈 화면 보이기
			bankDeposit.setVisible(true);
		}
		else if (e.getSource() == exitButton) {
        System.exit(0);
		}

	}

	public static void main(String[] args) {
		new MainMenu(new DataBaseClass());
	}
}