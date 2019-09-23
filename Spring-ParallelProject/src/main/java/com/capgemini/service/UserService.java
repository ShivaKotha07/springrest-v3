package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.UserException;
import com.capgemini.model.Transaction;
import com.capgemini.model.User;

/**
 * <h2>User Service Interface</h2>
 * This interface is used to include all user services.
 * 
 * @author Snehal Chaudhari
 *
 */
public interface UserService {
	
	/**
	 * This is abstract method for creating user.
	 * @param user
	 * @return boolean value
	 */
	public void addUser(User user);

	/**
	 * This is abstract method for finding balance of defined userId.
	 * @param userId
	 * @return balance
	 * @throws UserException
	 */
	public double findBalance(String userId) throws UserException;

	/**
	 * This is abstract method for depositing money.
	 * @param userId
	 * @param amount
	 * @return boolean value
	 * @throws UserException
	 */
	public boolean deposit(String userId, double amount) throws UserException;

	/**
	 * This is abstract method for withdrawing money.
	 * @param userId
	 * @param amount
	 * @return boolean value
	 * @throws UserException
	 */
	public boolean withdraw(String userId, double amount) throws UserException;

	/**
	 * This is abstract method for fund transfer.
	 * @param userId1
	 * @param userId2
	 * @param amount
	 * @return boolean value
	 * @throws UserException
	 */
	public boolean fundTransfer(String userId1, String userId2, double amount) throws UserException;

	/**
	 * This is abstract method is used to show transactions of particular user.
	 * @param acc_no
	 * @return list
	 * @throws UserException
	 */
	public List<Transaction> showTransaction(String acc_no) throws UserException;

	/**
	 * This is abstract method is used to validate user details
	 * @param user
	 * @return appropriate operation
	 * @throws UserException
	 */
	
	public List<User> getAllUsers();
	
	public String validateUser(User user) throws UserException;
	
	/**
	 * This is abstract method is used to validate userId and Password
	 * @param userId
	 * @param password
	 * @return boolean value
	 */
	public boolean validateLoginCredentials(String userId,String password) throws UserException;
	
	/**
	 * This is abstract method is used to validate userId
	 * @param userId
	 * @return boolean value
	 */
	public boolean validateUserId(String userId) throws UserException;
	
	public boolean checkAccNo(String acc_no,String userId) throws UserException;

}
