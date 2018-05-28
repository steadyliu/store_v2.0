package com.itheima.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.store.domain.Category;
import com.itheima.store.service.CategoryService;
import com.itheima.store.service.impl.CategoryServiceImpl;
import com.itheima.store.utils.BaseServlet;
import com.itheima.store.utils.BeanFactory;
import com.itheima.store.utils.UUIDUtils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * Servlet implementation class AdminCategoryServlet
 * 后台分类管理的
 */

public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String findAll(HttpServletRequest request,HttpServletResponse response) {
		CategoryService categoryService =(CategoryService)BeanFactory.getBean("categoryService");
		try {
			//清空缓存
			CacheManager cacheManager = CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
			// 从配置文件中获取名称为categoryCache缓存区
			Cache cache = cacheManager.getCache("categoryCache");
			cache.remove("list");
			List<Category> categoryList = categoryService.findAll();
			request.setAttribute("list", categoryList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/admin/category/list.jsp";
	}
	//跳转到添加分类的页面
	public String saveUI(HttpServletRequest request,HttpServletResponse response) {
		
		return "/admin/category/add.jsp";
		
	}
	//保存分类到数据库表
	public String save(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String cname  = request.getParameter("cname");
		CategoryService categoryService =(CategoryService)BeanFactory.getBean("categoryService");
		String cid = UUIDUtils.getUUID();
		Category category = new Category();
		category.setCid(cid);
		category.setCname(cname);
		try {
			categoryService.save(category);
			response.sendRedirect(request.getContextPath()+"/AdminCategoryServlet?method=findAll");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//编辑修改页面
	public String editUI(HttpServletRequest request,HttpServletResponse response) {
		
		Category category;
		try {
			//获取参数
			String cid = request.getParameter("cid");
			CategoryService categoryService = (CategoryService)BeanFactory.getBean("categoryService");
			category = categoryService.findByCid(cid);
			if(category !=null) {
				request.setAttribute("category", category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "/admin/category/edit.jsp";
	}
	
	//更新Category表
	public String update(HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Category category = new Category();
		BeanUtils.populate(category, parameterMap);
		CategoryService categoryService = (CategoryService)BeanFactory.getBean("categoryService");
		try {
			categoryService.update(category);
			response.sendRedirect(request.getContextPath()+"/AdminCategoryServlet?method=findAll");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//删除分类
	public String deleteCategory(HttpServletRequest request,HttpServletResponse response) {
		try {
		String cid  = request.getParameter("cid");
		CategoryService categoryService = (CategoryService)BeanFactory.getBean("categoryService");
		categoryService.deleteCategoryByCid(cid);
		response.sendRedirect(request.getContextPath()+"/AdminCategoryServlet?method=findAll");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
