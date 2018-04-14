<%@page import="java.util.Set"%>
<%@page import="com.bbs.model.Notice"%>
<%@page import="com.bbs.service.NoticeBiz"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.bbs.model.Post"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bbs.serviceImpl.PostBizImpl"%>
<%@page import="com.bbs.service.PostBiz"%>
<%@page import="com.bbs.model.SubForum"%>
<%@page import="com.bbs.model.MainForum"%>
<%@page import="com.bbs.service.MainForumBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh" class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<title>技术论坛</title>
<link rel="stylesheet" type="text/css" href="css/zzsc-demo.css">

   
    <link href="css/index.css" rel="stylesheet">

</head>

<body>


 <jsp:include page="/pages/header.jsp"/>
<div style="width: 100%;">
		
		 
		 <div class="container user-info">
    <div class="row">
        <div class="col-xs-12">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="img/2.jpg" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item">
                        <img src="img/1.jpg" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>

                    <div class="item">
                        <img src="img/2.jpg" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>

   
<div class="container" style="margin-top: 30px;">
    <div class="row">
        <div class="col-md-9">

            <ul class="list-group">
                <div class="list-group-item active">
                    论坛新帖
                    <a href="<%=request.getContextPath() %>/more.action?type=-1&&page=1" style="float: right;color: white">更多>></a>
                    <!--<p style="float: right"></p>-->
                </div>
                
					  <%
					    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                  	   PostBiz postBiz = (PostBiz)context.getBean("postBiz");
					   List<Post> latestPosts = postBiz.getLatestPosts(1,6);
					     for (Post post:latestPosts){
					   %>
				
                
                <a href="<%=request.getContextPath()%>/pages/post.jsp?postId=<%=post.getId()%>&&page=1" class="list-group-item">
                    <h4 class="list-group-item-heading">[<%=post.getSubForum().getMainForum().getTitle()%>]</h4>
                    <%=post.getTitle() %><span class="badge">新</span>
                    <p class="text-right" style="float: right;margin-right: 20px">浏览量:<%=post.getViewNum()%>&nbsp;评论量:<%=post.getReplyNum()%>&nbsp;发表日期:<%=post.getTime()%></p>
                </a>
				<%}%>
            </ul>

        </div>

        <div class="col-md-3">
            <ul class="list-group">
                <div class="list-group-item active">
                    论坛公告
                </div>
                  <%
                  	    NoticeBiz noticeBiz = (NoticeBiz)context.getBean("noticeBiz");
                  	    List<Notice> notices = noticeBiz.getNotice(1,5);
                  	    for (Notice notice:notices){
                   %>
                         <a href="<%=request.getContextPath()%>/pages/notice.jsp?noticeId=<%=notice.getId() %>" class="list-group-item"><%=notice.getTitle()%></a>
                    <%} %>
            </ul>
               <a href="<%=request.getContextPath()%>/publish_post.jsp" ><button type="button" class="btn btn-primary" style="width: 200px;height:50px;margin-left: 30px">我要发帖</button></a>
        </div>

        <div class="row">
            <div class="col-md-9" style="margin-left: 15px">
                <ul class="list-group">
                    <div class="list-group-item active">
                        精华帖
                        <a href="<%=request.getContextPath() %>/more.action?type=-2&&page=1" style="float: right;color: white">更多>></a>
                    </div>
                     <%
				   
				  	List<Post> posts = postBiz.getBestPosts(1,6);
				  	for (Post post:posts){
				   %>
                    <a href="<%=request.getContextPath()%>/pages/post.jsp?postId=<%=post.getId()%>&&page=1" class="list-group-item">
                        <h4 class="list-group-item-heading">[<%=post.getSubForum().getMainForum().getTitle()%>]</h4>
                            <%=post.getTitle() %><span class="badge">热</span>
                        <p class="text-right" style="float: right;margin-right: 20px">浏览量:<%=post.getViewNum()%>&nbsp;评论量:<%=post.getReplyNum()%>&nbsp;发表日期:<%=post.getTime()%></p>
                    </a>
                    
                     <%} %>
                </ul>
            </div>

            
    </div>
     <h3 style="margin-top: 20px;margin-left: 15px">板块分类</h3>
    <hr/>
      <div  class="container">
      <%
                  	   MainForumBiz mainForumBiz = (MainForumBiz)context.getBean("mainForumBiz");
                  	   List<MainForum> mainForums = mainForumBiz.getAllMainForums();
                  	   int size = mainForums.size();
                  	   for (int i=1; i<=size; i++){
                  	   MainForum mainForum = mainForums.get(i-1);
                  	 
                  	  
                  	   if (i%4 == 1){
        %>
         <div class="row">
       <%} %>
      
       
            <div class="col-md-3 col-sm-12">
                <a href="<%=request.getContextPath() %>/more.action?type=<%=mainForum.getId() %>&&page=1">
                <div class="main-forum">
                    <h3><%=mainForum.getTitle() %></h3>
                    <% Set<SubForum> subForums = mainForum.getSubForums();
                      boolean switcher = true;
                       for (SubForum subForum:subForums){
                       if (switcher){
                       switcher = false;
                     %>
                    <%=subForum.getTitle()%>
                    <%}else{ switcher = true;%>
                    <%=subForum.getTitle()%>
                    <%}} %>
                </div>
                </a>

            </div>
            
              <% if (i%4 == 4){%>
              </div>
              <%} }%>
            
          
  

    </div>
    </div>
    
   </div></div></div></div>

 
 <div  style="margin-top: 80px;background-color: rgba(0,0,0,0.8);height: 100px;color: darkgray;width: 100%">
    <div style="width: 400px;padding-top: 35px;padding-left:40px;padding-right: 40px;margin:auto;">
        <div>
            友情链接：
            <a href="https://github.com/zhangjianhao" style="color: darkgray">&nbsp;github&nbsp;|&nbsp;</a>
            <a href="http://www.csdn.net/" style="color: darkgray">csdn&nbsp;|&nbsp;</a>
            <a href="http://www.oschina.net/" style="color: darkgray">开源中国&nbsp;|&nbsp;</a>
            <a href="http://stackoverflow.com/" style="color: darkgray">stackflow</a><br>
            小组成员：张建浩,卜凡,卢静,姚文娜,余莎
        </div>
    </div>
</div>


</body>
</html>