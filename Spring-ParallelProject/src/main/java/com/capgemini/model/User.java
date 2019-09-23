package com.capgemini.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <h2>User Model Class</h2> This class defines all properties(details) of user
 * along with constructor, getters,setters and toString Methods.
 * 
 * @author SnehalChaudhari
 *
 */

@Entity
@Table(name="usertable")
public class User 
{
	@Id
	private String userId;
	private String acc_no, user_name, password;
	private long mob_no;
	private double balance;

	/**
	 * Default Constructor
	 */
	public User() {

	}

	/**
	 * Parameterized Constructor
	 * 
	 * @param acc_no
	 * @param user_name
	 * @param userId
	 * @param password
	 * @param mob_no
	 * @param balance
	 */
	public User(String acc_no, String user_name, String userId, String password, long mob_no, double balance) {
		super();
		this.acc_no = acc_no;
		this.user_name = user_name;
		this.userId = userId;
		this.password = password;
		this.mob_no = mob_no;
		this.balance = balance;
	}

	/**
	 * @return acc_no of user
	 */
	public String getAcc_no() {
		return acc_no;
	}

	/**
	 * @return acc_no of user
	 */
	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}

	/**
	 * @return user_name of user
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @return user_name of user
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return userId of user
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return userId of user
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return password of user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return password of user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return mob_no of user
	 */
	public long getMob_no() {
		return mob_no;
	}

	/**
	 * @return mob_no of user
	 */
	public void setMob_no(long mob_no) {
		this.mob_no = mob_no;
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
		return "User [acc_no=" + acc_no + ", user_name=" + user_name + ", userId=" + userId + ", password=" + password
				+ ", mob_no=" + mob_no + ", balance=" + balance + "]";
	}

}
