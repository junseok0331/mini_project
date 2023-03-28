package com.javalab.awt.menu;

/**
 * Bank 클래스
 */
public class Bank {

	private String acc;		// 계좌번호
	private String name; 	// 예금주
	private int balance;	// 금액
	
	//기본 생성자
	public Bank() {		
	}
	
	//오버로딩 생성자
	public Bank(String acc, String name, int balance) {
		this.acc = acc;
		this.name = name;
		this.balance = balance;
	}

	// getter/setter 생성자
	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// toString 오버로딩 생성자
	@Override
	public String toString() {
		return "Bank [acc=" + acc + ", name=" + name + ", balance=" + balance + "]";
	}

	// 예금 메소드
	public void deposit(int amount) {
		balance += amount;
	}

	// 출금 메소드
	public void withdraw(int amount) {
		balance -= amount;
	}
}