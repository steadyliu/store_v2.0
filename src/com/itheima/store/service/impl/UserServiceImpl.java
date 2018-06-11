package com.itheima.store.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.itheima.store.dao.UserDao;
import com.itheima.store.dao.impl.UserDaoImpl;
import com.itheima.store.domain.PageBean;
import com.itheima.store.domain.User;
import com.itheima.store.service.UserService;
import com.itheima.store.utils.BeanFactory;
import com.itheima.store.utils.MailUtils;
import com.itheima.store.utils.UUIDUtils;
/**
 * 用户模块的Service的实现类
 * @author admin
 *
 */
public class UserServiceImpl implements UserService {

	@Override
	public User findByUsername(String username)  throws SQLException{
		UserDao userDao = (UserDao) BeanFactory.getBean("userDao");
		return userDao.findByUsername(username);
	}

	@Override
	public void save(User user) throws SQLException {
		UserDao userDao = (UserDao) BeanFactory.getBean("userDao");
		user.setUid(UUIDUtils.getUUID());
		user.setState(1);// 1代表未激活, 2:代表已经激活.
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		
		// 发送激活邮件:
		MailUtils.sendMail(user.getEmail(), code);
	}

	@Override
	public User findByCode(String code) throws SQLException {
		UserDao userDao = (UserDao) BeanFactory.getBean("userDao");
		return userDao.findByCode(code);
	}

	@Override
	public void update(User existUser) throws SQLException {
		UserDao userDao = (UserDao) BeanFactory.getBean("userDao");
		userDao.update(existUser);
	}

	@Override
	public User login(User user) throws SQLException {
		UserDao userDao = (UserDao) BeanFactory.getBean("userDao");
		return userDao.login(user);
	}

	@Override
	/*
	 * 获取所有的用户
	 * @see com.itheima.store.service.UserService#findUserAll()
	 */
	public List<User> findUserAll() throws SQLException {
		// TODO Auto-generated method stub
		//获得UserDao实例对象进行底层操作
		UserDao userDao = (UserDao)BeanFactory.getBean("userDao");
		
		return userDao.findAll();
	}

	@Override
	public PageBean<User> findUserAllPageBean(int currPage) throws SQLException {
		// TODO Auto-generated method stub
		PageBean<User> pageBean = new PageBean<User>();
		pageBean.setCurrPage(currPage);
		//1.设置每页的大小
	   int pageSize = 10;
	   UserDao userDao = (UserDao)BeanFactory.getBean("userDao");
	   //2.获取总条数 totalCount
	    Integer totalCount = userDao.findAllByPage();
		double tc  = totalCount;
		Double num =Math.ceil(tc /pageSize);
		pageBean.setTotalPage(num.intValue());
		//设置每页显示的数据的集合
		int begin = (currPage - 1)*pageSize;
	    List<User> list  = userDao.findAllByPage(begin,pageSize);
	    pageBean.setList(list);
	    
		return pageBean;
	}

	@Override
	public User findUserByUid(String uid) throws SQLException {
		// TODO Auto-generated method stub
		return ((UserDao) BeanFactory.getBean("userDao")).findUserByUid(uid);
	}

	@Override
	public void updateEdit(User user) throws SQLException {
		// TODO Auto-generated method stub
		((UserDao) BeanFactory.getBean("userDao")).updateEdit(user);
	}

	@Override
	public void lockUser(String uid, String lock) throws SQLException {
		// TODO Auto-generated method stub
		((UserDao) BeanFactory.getBean("userDao")).lockUser(uid,lock);
	}

	@Override
	public void delUser(String uid) throws SQLException {
		// TODO Auto-generated method stub
		((UserDao) BeanFactory.getBean("userDao")).delUser(uid);
		
	}

}
