package com.itheima.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.store.domain.PageBean;
import com.itheima.store.domain.Product;

/**
 * 商品DAO的接口
 * @author admin
 *
 */
public interface ProductDao {

	List<Product> findByHot()throws SQLException ;

	List<Product> findByNew()throws SQLException ;

	Integer findCountByCid(String cid)throws SQLException ;

	List<Product> findPageByCid(String cid, int begin, Integer pageSize)throws SQLException ;

	Product findByPid(String pid)throws SQLException ;

	Integer findcountAll()throws SQLException ;

	List<Product> finByPage(int begin, Integer pageSize)throws SQLException ;

	void save(Product product)throws SQLException ;

	void update(Product product)throws SQLException ;

	List<Product> finPushDown(Integer pflag)throws SQLException ;

	
	
}
