<%@page import="com.bbs.model.BestPost"%>
<%@page import="com.bbs.service.BestPostBiz"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.bbs.model.Post"%>
<%@page import="com.bbs.service.PostBiz"%>
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
    
    <title>My JSP 'bestpost.jsp' starting page</title>
    
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
                <li role="presentation" class="active"><a href="<%=path%>/manage/bestpost.jsp">精华帖请求</a></li>
                 <li role="presentation"><a href="<%=path%>/manage/limit.jsp">封锁用户</a></li>
                  <li role="presentation"><a href="<%=path%>/manage/create_discuss.jsp">创建讨论区</a></li>
            </ul>
        </div>

        <div class="col-md-9">

             <ul class="list-group">
                <a class="list-group-item active">
                    精华帖申请列表
                </a>

               <% ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                  	   PostBiz postBiz = (PostBiz)context.getBean("postBiz");
                  	   String pageNumStr = request.getParameter("page");
                  	   int pageNum = 1;
                  	   if (pageNumStr != null)
                  	   pageNum = Integer.parseInt(pageNumStr);
                  	   
                  	   BestPostBiz bestPostBiz = (BestPostBiz)context.getBean("bestPostBiz");
                  	   System.out.println("page:"+pageNum);
                  	   List<BestPost>bestPosts = bestPostBiz.getBestPosts(pageNum, 10);
                  	   System.out.println("size:"+bestPosts.size());
                  	   for (BestPost bestPost:bestPosts){
                  	   if (bestPost.getState() != 1)
                  	   continue;
                  	   Post post = bestPost.getPost();
                  	   
                %>
               
                <div class="list-group-item">
                    <a href="<%=path%>/pages/post.jsp?postId=<%=post.getId()%>&&page=1" style="color:grey">
                        <h4 class="list-group-item-heading" style="color:black">[<%=post.getSubForum().getMainForum().getTitle()%>]</h4>
                        <%=post.getTitle() %>
                    </a>
                    <a href="<%=path%>/replyapply.action?postId=<%=post.getId()%>&&state=3" style="float: right">&nbsp;拒绝</a>&nbsp;
                    <a href="<%=path%>/replyapply.action?postId=<%=post.getId()%>&&state=2" style="float: right">&nbsp;同意</a>
                    <p style="float: right;margin-right: 50px">评论量:<%=post.getReplyNum()%>&nbsp;发表日期:<%=post.getTime()%></p>
                </div>
 				<%} %>
 				
 				 <ul class="pagination pagination-lg" style="float:right">
<% if (pageNum>1) { int pageIndex = pageNum -1;%>
    <li><a href="<%=path+"/manage/bestpost.jsp?page="+pageIndex%>">&laquo;</a></li>
    <%}
    	if (pageNum<=5){
    		for (int i=1; i<=5; i++){
    		if (pageNum == i){
     %>
    <li class="active"><a href="<%=path+"/manage/bestpost.jsp?page="+i%>"><%=i%></a></li>
    <%}else {
     %>
    <li><a href="<%=path+"/manage/bestpost.jsp?page="+i%>"><%=i%></a></li>
    <%}
    if (i ==5){
    %>
     <li><a href="<%=path+"/manage/bestpost.jsp?page="+6%>">&raquo;</a></li>
   <%}}}
    if (pageNum >5){
    int maxPage = pageNum+1;
    for (int i=4; i>=0; i--){
    	int pageIndex = pageNum - i;
    	if (i==0){
     %>
     <li class="active"><a href="<%=path+"/manage/bestpost.jsp?page="+pageIndex%>"><%=pageIndex%></a></li>
   
    <%}else {%>
     <li class=""><a href="<%=path+"/manage/bestpost.jsp?page="+pageIndex%>"><%=pageIndex%></a></li>
     <%}}%>
      <li><a href="<%=path+"/manage/bestpost.jsp?page="+maxPage%>">&raquo;</a></li>
    <%}%>
    
   
</ul><br>
 				

        </div>
    </div>
</div>
  </body>
</html>
