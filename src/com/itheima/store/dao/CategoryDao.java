package com.itheima.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.store.domain.Category;

/**
 * 分类的DAO的接口
 * @author admin
 *
 */
public interface CategoryDao {

	List<Category> findAll()throws SQLException;

	void save(Category category)throws SQLException;

	Category findByCid(String cid)throws SQLException;

	void update(Category category)throws SQLException;

	void deleteCategoryByCid(String cid)throws SQLException;
	


}
