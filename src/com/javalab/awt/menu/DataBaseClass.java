package com.javalab.awt.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 오직 데이터를 보관하는 데이터베이스 역할
 */
public class DataBaseClass {
	public List<Bank> bankList; // 계좌 ArrayList

	public DataBaseClass() {
		bankList = new ArrayList<Bank>(); // 계좌 ArrayList
	}

	public List<Bank> getBankList() {
		return bankList;
	}

	public void setBankList(List<Bank> bankList) {
		this.bankList = bankList;
	}

	// 계좌 하나 추가 메소드
	public void addBank(Bank bank) {
		this.bankList.add(bank);
	}

	// 현재 등록된 계좌 수[계좌 list를 보여줄 2차원 배열의 크기에 사용]
	public int getBankCount() {
		return bankList.size();
	}


	public Bank findBank(String acc) {
		for(Bank bank : bankList) {  
			if(bank.getAcc().equals(acc)) {
				return bank;
			}
		}
		return null; // 해당 계좌번호를 가진 계좌가 없을 경우 null 반환
	}
}
