package com.capgemini.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.capgemini.exception.UserException;
import com.capgemini.model.Transaction;
import com.capgemini.model.User;
import com.capgemini.util.JPAUtil;

/**
 * <h2>User DAO Implementation Class</h2>
 * It includes implementation of all methods present in userdao Interface.
 * @author Snehal Chaudhari
 */
public class UserDaoImpl implements UserDao 
{
	private EntityManager entityManager;
	private int transactionId;
	
	/**
	 * This is default constructor creating object of DatabaseUtils class for database connections
	 */
	public UserDaoImpl() 
	{
		entityManager = JPAUtil.getEntityManager();
		transactionId = 101;
	}
	
	
	static List<Transaction> tran = new ArrayList<Transaction>();
	
	/**
	 * This method is used to add new user in database.
	 * @param user
	 * @return res which is of boolean type.
	 * @exception UserException
	 * It will return true if user is added otherwise it will return false.
	 * @see com.capgemini.dao.UserDao#createUserAccount(com.capgemini.model.User)
	 */
	@Override
	public void createUserAccount(User user) throws UserException
	{
			entityManager.getTransaction().begin();
		//	entityManager.clear();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
	}

	/**
	 * This method is used to read balance of given userId from database
	 * @param userId
	 * @return balance of user if userId is valid.
	 * @exception UserException
	 * @see com.capgemini.dao.UserDao#readBalance(java.lang.String)
	 */
	@Override
	public double readBalance(String userId) throws UserException{
			//entityManager.clear();
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class,userId);
			entityManager.getTransaction().commit();
			return user.getBalance();
	}

	/**
	 * This method is used to deposit amount in given userId account
	 * @param userId,amount
	 * @return true if amount is deposited
	 * @exception UserException
	 * @see com.capgemini.dao.UserDao#updateBalanceDeposit(java.lang.String, double)
	 */
	@Override
	public boolean updateBalanceDeposit(String userId, double amount) throws UserException {
			boolean flag = false;
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class,userId);
			//System.out.println(user);
			double bal = user.getBalance() + amount;
			user.setBalance(bal);
			User user1 = entityManager.merge(user);
			entityManager.getTransaction().commit();
			if(user1.getBalance() == bal)
			{
				flag=true;
				Transaction transaction = new Transaction(transactionId, userId, userId, "Deposit",amount);
				entityManager.persist(transaction);
				transactionId++;
			}
			
			return flag;
	}

	/**
	 * This method is used to withdraw amount from given userId account
	 * @param userId,amount
	 * @return true if amount is withdrawn
	 * @exception UserException
	 * @see com.capgemini.dao.UserDao#updateBalanceWithdraw(java.lang.String, double)
	 */
	@Override
	public boolean updateBalanceWithdraw(String userId, double amount) throws UserException {
		boolean flag = false;
		entityManager.getTransaction().begin();
		User user = entityManager.find(User.class,userId);
		
		double bal = user.getBalance() - amount;
		user.setBalance(bal);
		User user1 = entityManager.merge(user);
		entityManager.getTransaction().commit();
		if(user1.getBalance() == bal)
		{
			flag=true;
			Transaction transaction = new Transaction(transactionId, userId, userId, "Withdraw",amount);
			entityManager.persist(transaction);
			//entityManager.getTransaction().commit();
			transactionId++;
		}
		
		return flag;
	}

	/**
	 * This method is used to transfer funds from first user account to second user account
	 * @param userId1,userId1,amount
	 * @return true if fund transfered successfully. Otherwise it will return false.
	 * @exception UserException
	 * @see com.capgemini.dao.UserDao#updateFundTransfer(java.lang.String, java.lang.String, double)
	 */
	@Override
	public boolean updateFundTransfer(String userId1, String userId2, double amount) throws UserException {
		/*boolean res = validateId(userId2);
		if(res) 
		{
			//boolean res1 = updateBalanceWithdraw(userId1, amount);
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class,userId1);
			System.out.println(user);
			double bal = user.getBalance() - amount;
			user.setBalance(bal);
			System.out.println(user);

			User user1 = entityManager.merge(user);
			//entityManager.getTransaction().commit();
			
			User user2 = entityManager.find(User.class,userId2);
			double bal1 = user2.getBalance() + amount;
			user.setBalance(bal1);
			User user3 = entityManager.merge(user2);
			entityManager.getTransaction().commit();
			
			if(bal > 0)
			{
				if(user1!=null )
				{
					if(user3!=null)
					{
						entityManager.getTransaction().begin();
						Transaction transaction = new Transaction(transactionId, userId1, userId2, "Fund Transfer",amount);
						entityManager.persist(transaction);
						entityManager.getTransaction().commit();
						transactionId++;
						return true;
					}
					else
					{
						throw new UserException("User not found with id "+userId2);
					}
				}
				else
				{
					throw new UserException("User not found with id "+userId1);
				}
			}
			else
			{
				throw new UserException("Not enough balance.");
			}
			
		}
		return false;
		*/
		
		boolean res = false;
		entityManager.getTransaction().begin();
		User user1 = entityManager.find(User.class, userId1);
		if (user1 != null) {
			double bal = user1.getBalance() - amount;
			if (bal > 0) {
				user1.setBalance(bal);
				entityManager.merge(user1);
				User user2 = entityManager.find(User.class, userId2);
				if (user2 != null) {
					bal = user2.getBalance() + amount;
					res = true;
					user2.setBalance(bal);
					entityManager.merge(user2);
					Transaction transaction = new Transaction(transactionId, userId1, userId2,"Fund Transfer", amount);
					transactionId++;
					entityManager.persist(transaction);
				} else
					throw new UserException("No such user.");
			} else
				throw new UserException("Not enough money");
		} else {
			throw new UserException("No such user.");
		}
		entityManager.getTransaction().commit();
		return res;
	}

	/**
	 * This method is used to read transactions of particular user
	 * @param userId
	 * @return trans_list list
	 * @see com.capgemini.dao.UserDao#readTransaction(java.lang.String)
	 */
	@Override
	public List<Transaction> readTransaction(String userId) throws UserException {
			tran.clear();
			boolean flag = false;
			entityManager.getTransaction().begin();

			TypedQuery<Transaction> query = entityManager.createQuery("from Transaction tr where tr.userId1=:userId or tr.userId2=:userId",Transaction.class);
			
			query.setParameter("userId", userId);
			tran = query.getResultList();
			entityManager.getTransaction().commit();
			return tran;
	}

	/**
	 * This method is used to validate Account no
	 * @param acc_no
	 * @return true if account no is valid else it will return false.
	 * @see com.capgemini.dao.UserDao#validateAccNo(java.lang.String)
	 */
	@Override
	public boolean validateAccNo(String acc_no,String userId) throws UserException{
		entityManager.getTransaction().begin();
		boolean res = false;
		User user = entityManager.find(User.class, userId);
		if(user!=null)
		{
			if (user.getAcc_no().equals(acc_no)) {
				res = true;
			}
		}
		entityManager.getTransaction().commit();		
		return res;
	}

	/**
	 * This method is used to validate userId and Password
	 * @param userId,password
	 * @return true if userId and password is valid else it will return false.
	 * @see com.capgemini.dao.UserDao#validateCredentials(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validateCredentials(String userId, String password) throws UserException {
	
		entityManager.getTransaction().begin();
		boolean res = false;
		User user = entityManager.find(User.class, userId);
		
		if(user!=null)
		{
			if (user.getUserId().equals(userId) && user.getPassword().equals(password)) 
			{
				res = true;
			}
		}
		entityManager.getTransaction().commit();		
		return res;
	}

	/**
	 * This method is used to validate userId
	 * @param userId
	 * @return true if userId is valid else it will return false.
	 * @see com.capgemini.dao.UserDao#validateId(java.lang.String)
	 */
	@Override
	public boolean validateId(String userId) throws UserException {

		entityManager.getTransaction().begin();
		boolean res = false;
		User user = entityManager.find(User.class, userId);
		
		if(user!=null)
		{
			if (user.getUserId().equals(userId)) 
			{
				res = true;
			}
		}
		entityManager.getTransaction().commit();		
		return res;	
	}
	
	@Override
	public List<User> readAllUsers()
	{
		TypedQuery<User> query = entityManager.createQuery("from User",User.class);
		List<User> list = query.getResultList();
		return list;
	}
	
	@Override
	public void beginTransaction() {
		entityManager.getTransaction().begin();		
	}

	@Override
	public void commitTransaction() {
		entityManager.getTransaction().commit();		
	}
}
