package com.itheima.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.store.domain.Order;
import com.itheima.store.domain.OrderItem;
import com.itheima.store.service.OrderService;
import com.itheima.store.utils.BaseServlet;
import com.itheima.store.utils.BeanFactory;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class AdminOrderServlet
 */
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public String findAll(HttpServletRequest req ,HttpServletResponse resp) {
		try {
		//获取参数state
		String state = req.getParameter("state");
		OrderService orderService =(OrderService)BeanFactory.getBean("orderService");
		List<Order> list =null;
		if(state == null) {
			//表示查询所有的订单
			
				list = orderService.findAll();
			
		}else {
			//表示查询按照状态来得到数据，
			 list = orderService.findByState(state);
		}
		//2.数据存放到request域 
		req.setAttribute("list", list);
		//3.转发到显示的页面 admin/order/list.jsp
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "admin/order/list.jsp";
		
	}
	
	//通过OID查询对应的OrderItem
	public String findOrderItem(HttpServletRequest req,HttpServletResponse resp) {
		try {
		//获取参数
		String oid = req.getParameter("oid");
		OrderService orderService  = (OrderService) BeanFactory.getBean("orderService");
		Order order = orderService.findByOid(oid);
		List<OrderItem> orderItems = order.getOrderItems();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"order"});
		JSONArray jsonArray = JSONArray.fromObject(orderItems,jsonConfig);
		resp.getWriter().println(jsonArray.toString());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//发货 设置状态为3 
	public String send(HttpServletRequest req ,HttpServletResponse resp) {
		try {
		String oid = req.getParameter("oid");
		OrderService orderService =  (OrderService) BeanFactory.getBean("orderService");
		Order order = orderService.findByOid(oid);
		order.setState(3);
		orderService.update(order);
		resp.sendRedirect(req.getContextPath()+"/AdminOrderServlet?method=findAll");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	

}
