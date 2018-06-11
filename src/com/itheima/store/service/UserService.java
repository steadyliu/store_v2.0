package com.itheima.store.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.store.domain.PageBean;
import com.itheima.store.domain.User;

/**
 * 用户模块的Service的接口
 * @author admin
 *
 */
public interface UserService {

	User findByUsername(String username) throws SQLException;

	void save(User user) throws SQLException;

	User findByCode(String code)throws SQLException;

	void update(User existUser)throws SQLException;

	User login(User user)throws SQLException;

	List<User> findUserAll()throws SQLException;

	PageBean<User> findUserAllPageBean(int currPage) throws SQLException;

	User findUserByUid(String uid) throws SQLException;

	void updateEdit(User user)throws SQLException;

	void lockUser(String uid, String lock)throws SQLException;

	void delUser(String uid)throws SQLException;

}
