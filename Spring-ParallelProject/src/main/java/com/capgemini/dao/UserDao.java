package com.capgemini.dao;

import java.util.List;

import com.capgemini.exception.UserException;
import com.capgemini.model.Transaction;
import com.capgemini.model.User;

/**
 * <h2>User Dao Interface</h2>
 * This interface is used to include all user data related methods.
 * 
 * @author Snehal Chaudhari
 *
 */
public interface UserDao {
	/**
	 * This is abstract method for creating user.
	 * @param user
	 * @return boolean value
	 */
	public void createUserAccount(User user);

	/**
	 * This is abstract method for finding balance of defined userId.
	 * @param userId
	 * @return balance
	 * @throws UserException
	 */
	public double readBalance(String userId) throws UserException;

		/**
	 * This is abstract method for depositing money.
	 * @param userId
	 * @param amount
	 * @return boolean value
	 * @throws UserException
	 */
	public boolean updateBalanceDeposit(String userId, double amount) throws UserException;

	/**
	 * This is abstract method for withdrawing money.
	 * @param userId
	 * @param amount
	 * @return boolean value
	 * @throws UserException
	 */
	public boolean updateBalanceWithdraw(String userId, double amount) throws UserException;

	/**
	 * This is abstract method for fund transfer.
	 * @param userId1
	 * @param userId2
	 * @param amount
	 * @return boolean value
	 * @throws UserException
	 */
	public boolean updateFundTransfer(String userId1, String userId2, double amount) throws UserException;

	/**
	 * This is abstract method is used to show transactions of particular user.
	 * @param userId
	 * @return list
	 * @throws UserException
	 */
	public List<Transaction> readTransaction(String userId) throws UserException;


	/**
	 * This is abstract method is used to validate user account
	 * @param acc_no
	 * @return boolean value
	 */
	public boolean validateAccNo(String acc_no,String userId) throws UserException;
	
	/**
	 * This is abstract method is used to validate userId and Password
	 * @param userId
	 * @param password
	 * @return boolean value
	 */
	public boolean validateCredentials(String userId,String password) throws UserException;
	
	/**
	 * This is abstract method is used to validate userId
	 * @param userId
	 * @return boolean value
	 */
	public List<User> readAllUsers();
	
	public boolean validateId(String userId) throws UserException;
	
	public void commitTransaction();

	public void beginTransaction();
}
