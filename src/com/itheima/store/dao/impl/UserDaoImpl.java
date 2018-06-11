package com.itheima.store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.store.dao.UserDao;
import com.itheima.store.domain.User;
import com.itheima.store.utils.JDBCUtils;

/**
 * 用户模块的DAO的实现类
 * 
 * @author admin
 *
 */
public class UserDaoImpl implements UserDao {

	@Override
	public User findByUsername(String username) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where username = ?";
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class), username);
		return user;
	}

	@Override
	public void save(User user) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user values (?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode(),user.getLock() };
		queryRunner.update(sql, params);
	}


	@Override
	public User findByCode(String code) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where code = ?";
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class), code);
		return user;
	}

	@Override
	public void update(User user) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set username = ?,password = ?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? ,lock=? where uid = ?";
		Object[] params = {  user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode(),user.getUid(),user.getLock() };
		queryRunner.update(sql, params);
	}
	
	public void updateEdit(User user) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set username = ?,password = ?,name=?,email=?,state=?,code=? ,user.lock=? where uid = ?";
		Object[] params = {  user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),user.getState(), user.getCode(),user.getLock(),user.getUid() };
		queryRunner.update(sql, params);
	}

	@Override
	public User login(User user) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ? and state = ?";
		User existUser = queryRunner.query(sql, new BeanHandler<User>(User.class), user.getUsername(),user.getPassword(),2);
		return existUser;
	}

	@Override
	public List<User> findAll() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user ";
		return queryRunner.query(sql, new BeanListHandler<User>(User.class));
	}

	@Override
	public Integer findAllByPage() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from user ";
		Long count = (Long) queryRunner.query(sql, new ScalarHandler());
		return count.intValue();
	}

	@Override
	public List<User> findAllByPage(int begin, int pageSize) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user order by create_time limit ?,?";
		List<User> list = queryRunner.query(sql, new BeanListHandler<User>(User.class), begin,pageSize);
		return list;
	}

	@Override
	public User findUserByUid(String uid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="select * from user where uid =?";
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class), uid);
		return user;
	}

	@Override
	public void lockUser(String uid, String lock) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="update user set user.lock=? where uid=?";
		queryRunner.update(sql, lock,uid);
	}

	@Override
	public void delUser(String uid) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="delete from user where uid=?";
		queryRunner.update(sql, uid);
	}
	
	

}
