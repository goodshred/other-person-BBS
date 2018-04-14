<%@page import="java.net.URLEncoder"%>
<%@page import="com.bbs.service.BlackListBiz"%>
<%@page import="com.bbs.model.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if (session.getAttribute("adminname") == null){
response.sendRedirect(path+"/manage/admin.jsp");
return ;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'limit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <jsp:include page="/pages/header.jsp"/>
    
     <div class="container" style="margin-top: 80px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation" ><a href="<%=path%>/manage/notice.jsp">发布公告</a></li>
                <li role="presentation" ><a href="<%=path%>/manage/change-admin.jsp">资料修改</a></li>
                <li role="presentation"><a href="<%=path%>/manage/newpost.jsp">查看新帖</a></li>
                <li role="presentation" ><a href="<%=path%>/manage/bestpost.jsp">精华帖请求</a></li>
                 <li role="presentation" class="active"><a href="<%=path%>/manage/limit.jsp">封锁用户</a></li>
                  <li role="presentation"><a href="<%=path%>/manage/create_discuss.jsp">创建讨论区</a></li>
            </ul>
        </div>

        <div class="col-md-9">

            
              
              

            <form style="float: right;" class="navbar-form navbar-right" role="search" action="<%=request.getContextPath()%>/searchuser.action" method="get">
                <div class="input-group" style="width: 300px">
                    <input type="text"  class="form-control" name="keywords" placeholder="搜索用户(用户名/邮箱)">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-search"></span> </span>
                </div>
            </form>
            <div style="margin-top: 50px;">
             <ul class="list-group">
            <%
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            BlackListBiz blackListBiz = (BlackListBiz)context.getBean("blackListBiz");
            String keywords = (String)request.getAttribute("keywords");
            System.out.println("jsp keywords:"+keywords);
            	List<User> uses = (List<User>)request.getAttribute("uses");
            	if (uses == null||uses.size()<1)
            	return ;
            	for (User user:uses){
            	
            	
             %>


           

                <div class="list-group-item" style="height: 60px;padding-top: 20px;">
                    <a href="" style="color:grey">
                        用户名:<%=user.getUsername() %>&nbsp;邮箱:<%=user.getEmail() %>
                    </a>



                    <div class="btn-group" style="float: right;margin-right: 20px;">

                        <button  type="button" class="btn btn-default dropdown-toggle btn-xs"
                                data-toggle="dropdown">
                            封锁用户 <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                        	<li><a href="<%=path%>/limit.action?userId=<%=user.getId()%>&&level=-1&&keywords=<%=keywords%>">解除限制</a></li>
                            <li><a href="<%=path%>/limit.action?userId=<%=user.getId()%>&&level=4&&keywords=<%=keywords%>">限制回复</a></li>
                            <li><a href="<%=path%>/limit.action?userId=<%=user.getId()%>&&level=3&&keywords=<%=keywords%>">限制发帖</a></li>
                            <li><a href="<%=path%>/limit.action?userId=<%=user.getId()%>&&level=2&&keywords=<%=keywords%>">限制发帖与回复</a></li>
                            <li><a href="<%=path%>/limit.action?userId=<%=user.getId()%>&&level=1&&keywords=<%=keywords%>">限制登陆</a></li>
                        </ul>
                    </div>
                    <div style="float: right;margin-right: 10px;">
                        限制状态：
                        <%
                        int level = blackListBiz.getLevel(user.getId());
                        if (level <0){
                         %>
                        无限制
                        <%}else if (level == 1) {%>
                        禁止登陆
                        <%}else if (level == 2) {%>
                        禁止发帖和回复
                        <%}else if (level == 3) {%>
                         禁止发帖
                        <%}else if (level == 4) {%>
                        禁止回复
                        <%}%>
                    </div>
                </div>
  <%} %>
            </ul>
          
</div>
       
 		
 				

        </div>
    </div>
</div>
   
  </body>
</html>
