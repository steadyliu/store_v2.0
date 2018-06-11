package com.itheima.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.store.domain.PageBean;
import com.itheima.store.domain.User;
import com.itheima.store.service.UserService;
import com.itheima.store.utils.BaseServlet;
import com.itheima.store.utils.BeanFactory;

/**
 * Servlet implementation class AdminUserServlet
 */
public class AdminUserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    //查询所有用户的,返回跳转到list.jsp
	
	public String findUserAll(HttpServletRequest req , HttpServletResponse resp) {
		//获取UserService实例对象
		UserService userService = (UserService) BeanFactory.getBean("userService");
		List<User> list;
		try {
			list = userService.findUserAll();
			req.setAttribute("list", list);
			return "/admin/user/list.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询所有用户出现了错误！");
		}
		
		
	}
	
	 //查询所有用户的，通过分页的形式,返回跳转到list.jsp
	
		public String findUserAllPageBean(HttpServletRequest req , HttpServletResponse resp) {
			//获取UserService实例对象
			int currPage = Integer.parseInt(req.getParameter("currPage"));
			UserService userService = (UserService) BeanFactory.getBean("userService");
			try {
				PageBean<User> pageBean = userService.findUserAllPageBean(currPage);
				req.setAttribute("pageBean", pageBean);
				return "/admin/user/list.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("查询所有用户出现了错误！");
			}
			
			
			
		}
		
		//编辑用户,根据用户ID来编辑用户
		public String edit(HttpServletRequest req , HttpServletResponse resp) {
			String uid = req.getParameter("uid");
			//1.根据用户ID查询到用户实体
			UserService userService = (UserService) BeanFactory.getBean("userService");
			User existUser;
			try {
				existUser = userService.findUserByUid(uid);
				if(existUser != null) {
					req.setAttribute("user", existUser);
					return "/admin/user/edit.jsp";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
		
		//保存修改后后的用户
		public String saveUser(HttpServletRequest req ,HttpServletResponse resp) {
			Map<String, String[]> map = req.getParameterMap();
			User user = new User();
			try {
				BeanUtils.populate(user, map);
				System.out.println(user.toString());
				UserService userService =(UserService)BeanFactory.getBean("userService");
				try {
					userService.updateEdit(user);
					try {
						resp.sendRedirect(req.getContextPath()+"/AdminUserServlet?method=findUserAllPageBean&currPage=1");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		//修改用户锁定状态(ajax方式)
		public String lockUser(HttpServletRequest req ,HttpServletResponse resp) {
			//1.获取UID 及lock参数
			try {
			String uid = req.getParameter("uid");
			String lock = req.getParameter("lock");
			//2.获取userService
			UserService userService  = (UserService) BeanFactory.getBean("userService");
			userService.lockUser(uid,lock);
			User existUser = userService.findUserByUid(uid);
			lock = existUser.getLock();
			resp.getWriter().println(lock);
			System.out.println(lock);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new RuntimeException();
			}
			
			return null;
		}
		
		
		//删除用户
		public String delUser(HttpServletRequest req ,HttpServletResponse resp) {
			
			try {
				String uid = req.getParameter("uid");
				String lock = req.getParameter("lock");
				//2.获取userService
				UserService userService  = (UserService) BeanFactory.getBean("userService");
				userService.delUser(uid);
				resp.sendRedirect(req.getContextPath()+"/AdminUserServlet?method=findUserAllPageBean&currPage=1");
				
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					throw new RuntimeException();
				}
				
				return null;
			}
		}
		
		
		
		
		
		
		
		
		
		


