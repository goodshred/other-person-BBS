<%@page import="com.bbs.model.Post"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>更多帖子</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link href="css/titlebar.css" rel="stylesheet">
    <link href="css/search-result.css" rel="stylesheet"/>

  </head>
  
  <body>
   <jsp:include page="/pages/header.jsp"/>
            
            
            <!-- 帖子列表 -->
            
             <div class="list-group search-list">
    <a href="#" class="list-group-item active">
    
    <%
    	List<Post> posts = (List)request.getAttribute("posts");
    	int pageNum = (Integer)request.getAttribute("page");
    	String typename = (String)request.getAttribute("typename");
    	int type = (Integer)request.getAttribute("type");
    	
    	if (posts.size()>0){
    %>
        <%=typename %>：
    <%}
    	else {
    %>
    抱歉！没有更多的结果
    <%} %>
     </a>
    <%
        	for (Post post:posts){
     %>
    <a href="<%=path%>/pages/post.jsp?postId=<%=post.getId()%>&&page=1" class="list-group-item">
            <h4 class="list-group-item-heading">
                [<%=post.getSubForum().getMainForum().getTitle() %>]
            </h4>
            <%=post.getTitle()%>&nbsp;[<%=post.getSubForum().getTitle() %>]
            <p class="text-right post-date">浏览量:<%=post.getViewNum()%>&nbsp;评论量:<%=post.getReplyNum()%>&nbsp;发表日期:<%=post.getTime()%></p>
    </a>
    <%} %>
</div>


<ul class="pagination pagination-lg page-float">
<% if (pageNum>1) { int pageIndex = pageNum -1;%>
    <li><a href="<%=path+"/more.action?type="+type+"&&page="+pageIndex%>">&laquo;</a></li>
    <%}
    	if (pageNum<=5){
    		for (int i=1; i<=5; i++){
    		if (pageNum == i){
     %>
    <li class="active"><a href="<%=path+"/more.action?type="+type+"&&page="+i%>"><%=i%></a></li>
    <%}else {
     %>
    <li><a href="<%=path+"/more.action?type="+type+"&&page="+i%>"><%=i%></a></li>
    <%}
    if (i ==5){
    %>
     <li><a href="<%=path+"/more.action?type="+type+"&&page="+6%>">&raquo;</a></li>
   <%}}}
    if (pageNum >5){
    int maxPage = pageNum+1;
    for (int i=4; i>=0; i--){
    	int pageIndex = pageNum - i;
    	if (i==0){
     %>
     <li class="active"><a href="<%=path+"/more.action?type="+type+"&&page="+pageIndex%>"><%=pageIndex%></a></li>
   
    <%}else {%>
     <li class=""><a href="<%=path+"/more.action?type="+type+"&&page="+pageIndex%>"><%=pageIndex%></a></li>
     <%}}%>
      <li><a href="<%=path+"/more.action?type="+type+"&&page="+maxPage%>">&raquo;</a></li>
    <%}%>
    
   
</ul><br>





  </body>
</html>
