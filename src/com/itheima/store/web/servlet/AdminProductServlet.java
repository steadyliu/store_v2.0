package com.itheima.store.web.servlet;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.store.domain.Category;
import com.itheima.store.domain.PageBean;
import com.itheima.store.domain.Product;
import com.itheima.store.service.CategoryService;
import com.itheima.store.service.ProductService;
import com.itheima.store.utils.BaseServlet;
import com.itheima.store.utils.BeanFactory;

/**
 * Servlet implementation class AdminProductServlet
 */
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//通过分页查询商品信息
	public String findByPage(HttpServletRequest req,HttpServletResponse resp) {
	
		PageBean<Product> pageBean;
		try {
			//获取参数
			Integer currPage = Integer.parseInt(req.getParameter("currPage"));
			ProductService productService = (ProductService)BeanFactory.getBean("productService");
			pageBean = productService.findByPage(currPage);
			req.setAttribute("pageBean", pageBean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/admin/product/list.jsp";
	}
	
	//后台添加商品界面
	public String saveUI(HttpServletRequest req,HttpServletResponse resp) {
		// 查询所有分类:
				try{
				CategoryService categoryService = (CategoryService) BeanFactory.getBean("categoryService");
				List<Category> list = categoryService.findAll();
				req.setAttribute("list", list);
				}catch(Exception e){
					e.printStackTrace();
				}
		return "/admin/product/add.jsp";
	}
	
	public String pushDown(HttpServletRequest req,HttpServletResponse resp) {
		try {
			//接收参数
			String pid = req.getParameter("pid");
			ProductService productService =(ProductService)BeanFactory.getBean("productService");
			Product product = productService.findByPid(pid);
			product.setPflag(1);
			productService.update(product);
			resp.sendRedirect(req.getContextPath()+"/AdminProductServlet?method=findByPage&currPage=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//下架商品显示
	public String findByPushDown(HttpServletRequest req,HttpServletResponse resp) {
		/* 0.获得有关productService对象
		 * 1.获取所有的pflag为1的Product
		 * 2.存放到request域中，然后转发到admin/product/list.jsp进行显示。
		 */
		
		List<Product> list;
		try {
			ProductService productService = (ProductService)BeanFactory.getBean("productService");
			Integer pflag = 1;
			list = productService.findPushDown(pflag);
			req.setAttribute("list", list);
			return "/admin/product/pushDown_list.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/admin/product/pushDown_list.jsp";
		
		
		
	}
	
	
	public String pushUp(HttpServletRequest req,HttpServletResponse resp) {
		try {
			//接收参数
			String pid = req.getParameter("pid");
			ProductService productService =(ProductService)BeanFactory.getBean("productService");
			Product product = productService.findByPid(pid);
			product.setPflag(0);
			productService.update(product);
			resp.sendRedirect(req.getContextPath()+"/AdminProductServlet?method=findByPushDown");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	}

