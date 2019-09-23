package com.capgemini.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <h2>Transaction Model Class</h2> This class will hold transaction details of
 * user.
 * 
 * @author SnehalChaudhari
 * 
 */
@Entity
@Table(name = "transactiontable")
public class Transaction {
	@Id
	int transactonId;
	String userId1, userId2, trans_mode;
	double balance;

	public Transaction() {
		
	}
	/**
	 * Parameterized Constructor
	 * 
	 * @param userId1
	 * @param userId2
	 * @param trans_mode
	 * @param balance
	 */
	public Transaction(int transactonId,String userId1, String userId2, String trans_mode, double balance) {
		super();
		this.transactonId = transactonId;
		this.userId1 = userId1;
		this.userId2 = userId2;
		this.trans_mode = trans_mode;
		this.balance = balance;
	}

	public int getTransactonId() {
		return transactonId;
	}

	public int setTransactonId(int transactonId) {
		return this.transactonId = transactonId;
	}

	/**
	 * @return userId of first user
	 */
	public String getUserId1() {
		return userId1;
	}

	/**
	 * @return userId of first user
	 */
	public void setUserId1(String userId1) {
		this.userId1 = userId1;
	}

	/**
	 * @return userId of second user
	 */
	public String getUserId2() {
		return userId2;
	}

	/**
	 * @return userId of second user
	 */
	public void setUserId2(String userId2) {
		this.userId2 = userId2;
	}

	/**
	 * @return type of transaction
	 */
	public String getTrans_mode() {
		return trans_mode;
	}

	/**
	 * @return type of transaction
	 */
	public void setTrans_mode(String trans_mode) {
		this.trans_mode = trans_mode;
	}

	/**
	 * @return balance of user
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @return balance of user
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return A string representing the object.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [userId1=" + userId1 + ", userId2=" + userId2 + ", trans_mode=" + trans_mode + ", balance="
				+ balance + "]";
	}



}
