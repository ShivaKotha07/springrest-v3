package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exception.UserException;
import com.capgemini.model.Transaction;
import com.capgemini.model.User;
import com.capgemini.service.UserService;

@RestController
@RequestMapping(value="user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController {
	@Autowired
	private UserService service;
	
	// http://localhost:9090/user/
		@RequestMapping(value="/", method=RequestMethod.GET)
		public List<User> getallusers()
		{
			List<User> list = service.getAllUsers();
			return list;
		}
	
	// http://localhost:9090/user/findBalance/{userId}
	@RequestMapping(value="/findBalance/{userId}", method=RequestMethod.GET)
	public double findBalance(@PathVariable("userId")String userId){
		double bean = service.findBalance(userId);
		if(bean == 0)
		{
			throw new UserException("Userid not found with : "+userId);
		}
		return bean;
	}
	
	// http://localhost:9090/user/create
	@RequestMapping(value="/create", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public void createUser(@RequestBody User bean)
	{
		String result = service.validateUser(bean);
		boolean flg1 = service.validateUserId(bean.getUserId());

		if(result.equals("Correct"))
		{
			if (!flg1) 
			{
				service.addUser(bean);
			} 
			else
			{
				throw new UserException("User Id already exists.");
			}
		}
	}
	
		// http://localhost:9090/user/login/{userId}/{password}
		@RequestMapping(value="/login/{userId}/{password}", method=RequestMethod.GET)
		public boolean login(@PathVariable("userId")String userId,@PathVariable("password")String password)
		{
			boolean flag1 = service.validateLoginCredentials(userId, password);
//			if(flag1)
//			{
//				return "true";
//			}
//			else
//			{
//				return "false";
//			}
			return flag1;
		}
		
		// http://localhost:9090/user/deposit/{userid}/{amount}
		@RequestMapping(value="/deposit/{userId}/{amount}", method=RequestMethod.GET)
		public double deposit(@PathVariable("userId")String userId,@PathVariable("amount")double amount)
		{
			if(amount>0)
			{
				boolean flag = service.deposit(userId, amount);
				if(flag && (service.validateUserId(userId)))
				{
					//return "deposited successfully";
					return service.findBalance(userId);
					//return "true";
				}
				else
				{
					throw new UserException("Technical error.");
				}
			}
			else
			{
				throw new UserException("Amount must be greater than 0.");
			}
		}
	
		// http://localhost:9090/user/withdraw/{userid}/{amount}
		@RequestMapping(value="/withdraw/{userId}/{amount}", method=RequestMethod.GET)
		public double withdraw(@PathVariable("userId")String userId,@PathVariable("amount")double amount)
		{
			if(amount>0)
			{
				boolean flag = service.withdraw(userId, amount);
				if(flag && (service.validateUserId(userId)))
				{
					//return "Withdrawn successfully";
					return service.findBalance(userId);
				}
				else
				{
					throw new UserException("Technical error.");
				}
			}
			else
			{
				throw new UserException("Amount must be greater than 0.");
			}
		}
		
		// http://localhost:9090/user/fundtransfer/{userid}/{amount}
		@RequestMapping(value = "/fundtransfer/{userid1}/{userid2}/{amount}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public double fundTransfer(@PathVariable("userid1") String userid1,@PathVariable("userid2") String userid2, @PathVariable("amount") double amount) 
		{
			if(service.validateUserId(userid1) && service.validateUserId(userid2))
			{
				boolean res = service.fundTransfer(userid1, userid2, amount);
				if(res)
				{
					//return "Fund Transfer Successfully";
					//System.out.println(service.findBalance(userid1));
					return service.findBalance(userid1);
				}
				else
					throw new UserException("Technical error.");
			}
			else
			{
				throw new UserException("No such User");
			}
		}
		
		// http://localhost:9090/user/showtransaction/{userid}/{amount}
		@RequestMapping(value = "/showtransaction/{userid}", method = RequestMethod.GET)
		public List<Transaction> showTransaction(@PathVariable("userid") String userid) 
		{
			if(service.validateUserId(userid))
			{
				List<Transaction> list = service.showTransaction(userid);
				return list;
			}
			else
			{
				throw new UserException("No such User");
			}
		}
		
		// http://localhost:9090/user/validateUserId
		@RequestMapping(value="/validateUserId/{userid}", method=RequestMethod.GET)
		public boolean validateUserId(@PathVariable("userid")String userid)
		{
			if(service.validateUserId(userid))
				return true;
			return false;
		}
}
