package com.itheima.store.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.store.domain.Order;
import com.itheima.store.domain.PageBean;

/**
 * 订单模块的Service的接口
 * @author admin
 *
 */
public interface OrderService {

	void save(Order order);

	PageBean<Order> findByUid(String uid, int currPage)throws SQLException, Exception;

	Order findByOid(String oid) throws Exception;

	void update(Order order)throws Exception;

	List<Order> findAll()throws Exception;

	List<Order> findByState(String state)throws Exception;

}
