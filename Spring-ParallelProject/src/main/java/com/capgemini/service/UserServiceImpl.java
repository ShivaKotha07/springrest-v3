package com.capgemini.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.dao.UserDao;
import com.capgemini.dao.UserDaoImpl;
import com.capgemini.exception.UserException;
import com.capgemini.model.Transaction;
import com.capgemini.model.User;

/**
 * <h2>User Service Implementation</h2>
 * It includes implementation of all methods present in userService Interface.
 * @author Snehal Chaudhari
 *
 */

@Service
public class UserServiceImpl implements UserService {
	
	/**
	 * Object of UserDao implementation is created and stored in UserDao interface reference. 
	 */
	private UserDao dao = new UserDaoImpl();

	/**
	 * This method is used to call createUserAccount of dao layer
	 * @param user
	 * @return boolean value
	 * @see com.capgemini.service.UserService#addUser(com.capgemini.model.User)
	 */
	@Override
	public void addUser(User user) throws UserException
	{
	//	dao.beginTransaction();
		dao.createUserAccount(user);
	//	dao.commitTransaction();
	}

	/** 
	 * This method is used to call readBalance of dao layer
	 * @param userId
	 * @return balance of user if userId is valid.
	 * @exception UserException
	 * @see com.capgemini.service.UserService#findBalance(java.lang.String)
	 */
	@Override
	public double findBalance(String userId) throws UserException {
	//	dao.beginTransaction();
		double res = dao.readBalance(userId);
	//	dao.commitTransaction();
		return res;
	}

	/** 
	 * This method is used to call updateBalanceDeposit of dao layer
	 * @param userId,amount
	 * @return true if amount is deposited
	 * @exception UserException
	 * @see com.capgemini.service.UserService#deposit(java.lang.String, double)
	 */
	@Override
	public boolean deposit(String userId, double amount) throws UserException {
	//	dao.beginTransaction();
		boolean flag = dao.updateBalanceDeposit(userId, amount);
	//	dao.commitTransaction();
		return flag;
	}

	/**
	 * This method is used to call updateBalanceWithdraw of dao layer
	 * @param userId,amount
	 * @return true if amount is withdrawn
	 * @exception UserException
	 * @see com.capgemini.service.UserService#withdraw(java.lang.String, double)
	 */
	@Override
	public boolean withdraw(String userId, double amount) throws UserException {
	//	dao.beginTransaction();
		boolean res = dao.updateBalanceWithdraw(userId, amount);
	//	dao.commitTransaction();
		return res;
	}

	/**
	 * This method is used to call updateFundTransfer of dao layer
	 * @param userId1,userId1,amount
	 * @return true if fund transfered successfully. Otherwise it will return false.
	 * @exception UserException
	 * @see com.capgemini.service.UserService#fundTransfer(java.lang.String, java.lang.String, double)
	 */
	@Override
	public boolean fundTransfer(String userId1, String userId2, double amount) throws UserException{
	//	dao.beginTransaction();
		boolean res = dao.updateFundTransfer(userId1, userId2, amount);
	//	dao.commitTransaction();
		return res;
	}

	/**
	 * This method is used to call readTransaction of dao layer
	 * @param userId
	 * @return trans_list list
	 * @see com.capgemini.service.UserService#showTransaction(java.lang.String)
	 */
	@Override
	public List<Transaction> showTransaction(String acc_no) throws UserException {
	//	dao.beginTransaction();
		List<Transaction> trans = dao.readTransaction(acc_no);
	//	dao.commitTransaction();
		return trans;
	}

	/**
	 * Validation of details entered by user : 
	 *  - Account no length must be 7 and must be unique
	 *  - Username must contain alphabets only
	 *  - UserId length must be 8
	 *  - Password should start with uppercase letter followed by lowercase letters and digits,
	 *    length must be between 8-12
	 *  - Mobile no must be of 10 digits
	 *  - balance should be greater than equal to zero
	 *  If above user has entered invalid details then respected User exception is thrown.
	 * @return appropriate message
	 * @param user
	 * @see com.capgemini.service.UserService#validateUser(com.capgemini.model.User)
	 */
	@Override
	public String validateUser(User user) throws UserException {
		boolean res = dao.validateAccNo(user.getAcc_no(),user.getUserId());

		if (user.getAcc_no().length() == 7) {
			if (res) 
			{
				UserException ue = new UserException("Account no is already exists.");
				throw ue;
			} 
			else 
			{
				if (user.getUser_name().matches("[A-Z]*[a-z]*")) 
				{
					if(user.getUserId().length() == 8)
					{
						if(user.getPassword().matches("([A-Z]+)([a-z]+)@[0-9]*") && user.getPassword().length()>7 && user.getPassword().length()<=12)
						{
							int len = String.valueOf(user.getMob_no()).length();
							if (len == 10 || len == 12) 
							{
								if (user.getBalance() > 0) 
								{
									return "Correct";
								} 
								else 
								{
									UserException ue = new UserException("Balance should not be 0.");
									throw ue;
								}
							} 
							else 
							{
								UserException ue = new UserException("Phone no should be 10/12 digits");
								throw ue;
							}
						}
						else
						{
							UserException ue = new UserException("Password must starting with capital letter."
									+ "\nIt should contain small letters and special characters with digits\n"
									+ "Length must be between 8-12");
							throw ue;
						}
					}
					else
					{
						UserException ue = new UserException("UserId length must be 8.");
						throw ue;
					}
				} 
				else 
				{
					UserException ue = new UserException("Name should be characters only.");
					throw ue;
				}
			}
		} 
		else 
		{
			UserException ue = new UserException("Account no must be of 7 digits.");
			throw ue;
		}
	}

	/**
	 * This method is used to call validateCredentials of dao layer
	 * @param userId,password
	 * @return true if userId and password is valid else it will return false.
	 * @see com.capgemini.service.UserService#validateLoginCredentials(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validateLoginCredentials(String userId, String password) throws UserException {
	//	dao.beginTransaction();
		boolean flag = dao.validateCredentials(userId, password);
	//	dao.commitTransaction();
		return flag;
		
	}

	/**
	 * This method is used to call validateUserId of dao layer
	 * @param userId
	 * @return true if userId is valid else it will return false.
	 * @see com.capgemini.service.UserService#validateUserId(java.lang.String)
	 */
	@Override
	public boolean validateUserId(String userId) throws UserException {
	//	dao.beginTransaction();
		boolean flag1 = dao.validateId(userId);
	//	dao.commitTransaction();
		return flag1;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list=dao.readAllUsers();
		return list;
	}

	@Override
	public boolean checkAccNo(String acc_no, String userId) throws UserException {
		boolean res = dao.validateAccNo(acc_no, userId);
		return res;
	}

}
