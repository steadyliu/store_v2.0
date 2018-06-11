package com.itheima.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.store.domain.User;

/**
 * 用户模块的DAO的接口
 * @author admin
 *
 */
public interface UserDao {

	User findByUsername(String username) throws SQLException;

	void save(User user)throws SQLException;

	User findByCode(String code)throws SQLException;

	void update(User existUser)throws SQLException;

	User login(User user)throws SQLException;

	List<User> findAll()throws SQLException;

	Integer findAllByPage()throws SQLException;

	List<User> findAllByPage(int begin, int pageSize)throws SQLException;

	User findUserByUid(String uid)throws SQLException;

	void updateEdit(User user)throws SQLException;

	void lockUser(String uid, String lock)throws SQLException;

	void delUser(String uid)throws SQLException;

}
