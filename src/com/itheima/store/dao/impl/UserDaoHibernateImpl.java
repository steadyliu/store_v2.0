package com.itheima.store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.itheima.store.dao.UserDao;
import com.itheima.store.domain.User;

public class UserDaoHibernateImpl implements UserDao {

	@Override
	public User findByUsername(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User user) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByCode(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User existUser) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public User login(User user) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findAllByPage() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllByPage(int currPage, int pageSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUid(String uid) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEdit(User user) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lockUser(String uid, String lock) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delUser(String uid) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
