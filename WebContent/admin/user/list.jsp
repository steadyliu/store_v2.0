<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
		<script src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
		
	</HEAD>
	<script type="text/javascript">
		$(function(){
			
			
		});
		function postLock( el){
			var uid = $(el).attr("id");
			var lock ;
			//alert($.trim($(el).text()) =='锁定');
			if($.trim($(el).text()) =='锁定'){
				lock = 1 ;
			}else{lock = 0;}
			$.post("${ pageContext.request.contextPath }/AdminUserServlet",{"method":"lockUser","uid":uid,"lock":lock},function(data){
			//	alert(data);
			});
			
		}
	</script>
	<body>
		<br>
		<form id="Form1" name="Form1" action="#" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>用户列表</strong>
						</TD>
					</tr>
					<tr>
						
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%">
										序号
									</td>
									<td align="center" width="17%">
										用户名称
									</td>
									<td align="center" width="17%">
										真实姓名
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										删除
									</td>
									<td width="7%" align="center">
										锁定与否
									</td>
								</tr>
									<c:forEach var="user" items="${ pageBean.list }"  varStatus="status">
								<!-- 	<s:iterator var="u" value="pageBean.list" status="status"> -->
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">
												${ status.count  }
												<!-- <s:property value="#status.count"/> -->
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${ user.username }
												<!-- <s:property value="#u.username"/> -->
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${ user.name }
												<!-- <s:property value="#u.name"/> -->
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${ pageContext.request.contextPath }/AdminUserServlet?method=edit&uid=${ user.uid}">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<a href="${ pageContext.request.contextPath }/AdminUserServlet?method=delUser&uid=${ user.uid}">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
											<td align="center" style="HEIGHT: 22px" >
											<c:if test="${user.lock ==1 }">
												<font style="color:green">已锁定</font>
												<button class="btn-primary"   id="${user.uid }" onclick="postLock(this)">
													解锁
												</button>
											</c:if>
											<c:if test="${user.lock ==0 }">
												<font style="color:green">未锁定</font>
												<button  class="btn-primary"  id="${user.uid }" onclick="postLock(this)">
													锁定
												</button>
											</c:if>
											</td>
										</tr>
										</c:forEach>
									<!-- </s:iterator> -->	
							</table>
						</td>
					</tr>
					<%-- <tr align="center">
						<td colspan="7">
							第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页 
							<s:if test="pageBean.page != 1">
								<a href="${ pageContext.request.contextPath }/userAdmin_findAll.action?page=1">首页</a>|
								<a href="${ pageContext.request.contextPath }/userAdmin_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
							</s:if>
							<s:if test="pageBean.page != pageBean.totalPage">
								<a href="${ pageContext.request.contextPath }/userAdmin_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
								<a href="${ pageContext.request.contextPath }/userAdmin_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>|
							</s:if>
						</td>
					</tr> --%>
			
					
				</TBODY>
			</table>
					<!-- 分页内容 -->
				<div style="text-align: center;">
				<ul class="pagination">
				
<%-- 					<li <c:if test="${ pageBean.currPage == 1 }">class="disabled"</c:if>><a href="${ pageContext.request.contextPath }/AdminUserServlet?method=findUserAllPageBean&currPage=${pageBean.currPage-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
 --%>
					<!-- 需要增加判断如果当前页面是第一页则不让跳转，否则进行向前 -->
					<c:if test="${ pageBean.currPage ==1 }">
						<li class="disabled"  aria-label="Previous"><span aria-hidden="true">&laquo;</span></li>
					</c:if>
					<c:if test="${ pageBean.currPage !=1 }">
						<li ><a href="${ pageContext.request.contextPath }/AdminUserServlet?method=findUserAllPageBean&currPage=${pageBean.currPage-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:if>
					
					<c:forEach var="i" begin="1" end="${ pageBean.totalPage }">
						<li <c:if test="${ pageBean.currPage == i }">class="active"</c:if>><a href="${ pageContext.request.contextPath }/AdminUserServlet?method=findUserAllPageBean&currPage=${ i}">${ i }</a></li>
					</c:forEach>
					<c:if test="${ pageBean.currPage == pageBean.totalPage }">
						<li class="disabled">
							<a href="#" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
					<c:if test="${ pageBean.currPage != pageBean.totalPage }">
						<li>
							<a href="${ pageContext.request.contextPath }/AdminUserServlet?method=findUserAllPageBean&currPage=${pageBean.currPage+1}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
				</ul>
			</div>
					
		</form>
	</body>
</HTML>

