package com.itheima.store.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.itheima.store.domain.User;

/**
 * Servlet Filter implementation class PrivilegeFilter
 */
public class PrivilegeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PrivilegeFilter() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//request强转为httprequest
		HttpServletRequest req = (HttpServletRequest) request;
		User existUser = (User) req.getSession().getAttribute("existUser");
		if(existUser == null) {
			//不存在 不能让其过去
			req.setAttribute("msg", "您还没有登录，没有权限访问！");
			req.getRequestDispatcher("/jsp/msg.jsp").forward(req, response);
			return ;
		}
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
