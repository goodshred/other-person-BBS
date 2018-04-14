<%@page import="com.bbs.service.UserBiz"%>
<%@page import="com.bbs.model.User"%>
<%@page import="com.bbs.model.Followcard"%>
<%@page import="com.bbs.model.Post"%>
<%@page import="com.bbs.service.PostBiz"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" autoFlush="false" buffer="1000kb"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>帖子浏览</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
     <link href="css/post-detail.css" rel="stylesheet">
     <script type="text/javascript" src="ckeditor/ckeditor.js"></script>

  </head>
  
  <body>
<div style="height:100%">
  
   <jsp:include page="/pages/header.jsp"/>
  	 <div class="container" style="margin-top: 50px">
  	<%
  		
  		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
  		 PostBiz postBiz = (PostBiz)context.getBean("postBiz");
  		 int postId  = 1;
  		 String pid = request.getParameter("postId");
  		 if (pid == null)
  		 postId = (Integer)request.getAttribute("postId");
  		 else 
  		 postId = Integer.parseInt(pid);
  		 Post post = postBiz.getPostById(postId);
  		 int pageNum  = 1;
  		 String num = request.getParameter("page");
  		 if (num != null)
  		    pageNum = Integer.parseInt(num);
  		 else 
  		 	pageNum = (Integer)request.getAttribute("page");
  		    System.out.println("postId"+postId+"pageNum:"+pageNum);
  		    postBiz.autoIncreaseViewNum(postId);
  	 	 List<Followcard> followcards = postBiz.getFollowCards(postId, pageNum, 5);
  	 %>
  	 
  
    <div class="row">
       <div class="col-md-1 post-border">
       </div>
        <div class="col-md-2 post-head">
                <!--<img src="img/tm-bg-slide-1.jpg" width="80px" height="80px" class="img-responsive img-circle">-->
            <img  alt="" class="img-responsive img-circle" src="<%=path+"/"+post.getUser().getPhotoUrl() %>"
                  style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>

                <span class="user-info">
                            <span class="badge" style="background: #f1c40f;margin-top: 5px">发帖者</span>
                            :<span class="badge" style="background: #f1c40f;margin-top: 5px"><%=post.getUser().getUsername()%></span>
                        </span><br/>
                        <span class="user-info">
                            <span class="badge" style="background: #2ecc71;margin-top: 5px">性别</span>
                            :<span class="badge" style="background: #2ecc71;margin-top: 5px"><%=post.getUser().getSex()%></span>
                        </span><br/>
                        <span class="user-info">
                             <span class="badge" style="background: #ff6927;margin-top: 5px">论坛等级</span>:
                            <span class="badge" style="background: #ff6927;margin-top: 5px">LV<%=post.getUser().getLevel()%></span>
                        </span>
            <br>
        </div>
        <div class="col-md-8 post-content">

            <div class="post-title">
                <h2 style="margin-left:20px;color:black">[<%=post.getSubForum().getTitle()%>]<%=post.getTitle()%></h2>
                <div style="margin-left:20px" >

                    <span class="glyphicon glyphicon-comment"></span>&nbsp;&nbsp;<%=post.getReplyNum() %> &nbsp;|&nbsp;<span>发表于:<%=post.getTime() %></span>
                    <%String username = (String)session.getAttribute("username");
                    if ((username != null && post.getUser().getUsername().equals(username))||(String)session.getAttribute("adminname")!=null){%>
					<a style="float:right;margin-right: 20px;" href="<%=path%>/editpost.action?postId=<%=post.getId()%>">编辑</a>
					<%}%>
                </div>
                <strong style=" float:right;margin-right:10px;margin-top: 10px">
                    <span class="badge" style="background: #ff6927;width: 50px;">楼主</span></strong>

            </div>
            <div style="margin: 20px">
               <%=post.getCardContent()%>
            </div>

        </div>
         <div class="col-md-1 post-border">
       </div>

        


    </div>
    </div>
       
  	 
  	 
  	  
  	 
    
   
    <!-- 回复内容 -->
     <%
   
  	 	 int size = followcards.size();
  	 	 System.out.println("回复数目："+size);
  	 	 UserBiz userBiz = (UserBiz)context.getBean("userBiz");
  	 	 int floor = 0;
		for (int i = 0;i<size;i++ ){
		Followcard followcard = followcards.get(i);
		floor = i+5*(pageNum-1)+1;
		User user = followcard.getUser();
		User repeatUser = null;
		if (user.getSex() == null){
		  repeatUser = userBiz.getUserById(user.getId());
		  System.out.println("sexi is null:"+user.getId());
		    user.setSex(repeatUser.getSex());
		}
		if (user.getLevel() == null&&repeatUser!=null){
		  user.setLevel(repeatUser.getLevel());		  
		}
		   
  	  %>
  	 <div class="container">
    <div class="row" style="margin-top: 5px">
        <div class="col-md-1 reply-border">


        </div>
        <div class="col-md-2 reply-head">
            <img  alt="" class="img-responsive img-circle" src="<%=path+"/"+post.getUser().getPhotoUrl() %>"
                  style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>

            <span class="user-info">
                        <span class="badge" style="background: #f1c40f;margin-top: 5px">姓名</span>
                        :<span class="badge" style="background: #f1c40f;margin-top: 5px"><%=user.getUsername()%></span>
                    </span><br/>
                    <span class="user-info">
                        <span class="badge" style="background: #2ecc71;margin-top: 5px">性别</span>
                        :<span class="badge" style="background: #2ecc71;margin-top: 5px"><%=user.getSex()%></span>
                    </span><br/>
                    <span class="user-info">
                         <span class="badge" style="background: #ff6927;margin-top: 5px">论坛等级</span>:
                        <span class="badge" style="background: #ff6927;margin-top: 5px">LV<%=user.getLevel()%></span>
                    </span>
            <br>


        </div>
        <div class="col-md-8 reply-content">

            <div class="reply-time">
                <span style="color: gray">回复于:<%=followcard.getTime() %></span>
                <% if (floor == 1) {%>
                <span class="badge" style="float:right;margin-right:10px;background: #ff6927;width: 50px;">沙发</span>
                <%}else if (floor == 2) {%>
                <span class="badge" style="float:right;margin-right:10px;background: #ff5959;width: 50px;">板凳</span>
                <%} else if (floor == 3) {%>
                <span class="badge" style="float:right;margin-right:10px;background: #4b9ded;width: 50px;">地板</span>
                <%} else if (floor > 3) {%>
                <span class="badge" style="float:right;margin-right:10px;background: #4b9ded;width: 50px;">第<%=floor%>楼</span>
                <%}%>
                
            </div>
            <div style="margin: 20px;">
                <%=followcard.getFollowContent()%>
            </div>


        </div>
        <div class="col-md-1 reply-border">

        </div>
 <%}%>

    </div>
     </div>
    
  

  	 
  	 <ul class="pagination pagination-lg" style="float: right;margin-right: 20px;">
<% if (pageNum>1) { int pageIndex = pageNum -1;%>
    <li><a href="<%=path+"/pages/post.jsp?postId="+postId+"&&page="+pageIndex%>">&laquo;</a></li>
    <%}
    	if (pageNum<=5){
    		for (int i=1; i<=5; i++){
    		if (pageNum == i){
     %>
    <li class="active"><a href="<%=path+"/pages/post.jsp?postId="+postId+"&&page="+i%>"><%=i%></a></li>
    <%}else {
     %>
    <li><a href="<%=path+"/pages/post.jsp?postId="+postId+"&&page="+i%>"><%=i%></a></li>
    <%}
    if (i ==5){
    %>
     <li><a href="<%=path+"/pages/post.jsp?postId="+postId+"&&page="+6%>">&raquo;</a></li>
   <%}}}
    if (pageNum >5){
    int maxPage = pageNum+1;
    for (int i=4; i>=0; i--){
    	int pageIndex = pageNum - i;
    	if (i==0){
     %>
     <li class="active"><a href="<%=path+"/pages/post.jsp?postId="+postId+"&&page="+pageIndex%>"><%=pageIndex%></a></li>
   
    <%}else {%>
     <li class=""><a href="<%=path+"/pages/post.jsp?postId="+postId+"&&page="+pageIndex%>"><%=pageIndex%></a></li>
     <%}}%>
      <li><a href="<%=path+"/pages/post.jsp?postId="+postId+"&&page="+maxPage%>">&raquo;</a></li>
    <%}%>
    
   
</ul><br>
  	
  	
  	<div style="height: 200px;margin: 70px auto; width: 800px;">
  	<form action="<%=path%>/reply.action" method="post">
  	<input type="hidden" name="postId" value="<%=postId%>">
  	<input type="hidden" name="page" value="<%=pageNum%>">
        <div style="margin: 5px auto;height: 100px; width: 800px">
            <textarea id="TextArea1" cols="20" rows="1" name="content" class="ckeditor"></textarea>

        </div>
        <s:fielderror fieldName="limit"></s:fielderror>
    <div style="float:right;margin: 60px auto">
        <input type="submit" class="btn btn-primary" style="width: 60px;" value="回复"></input>
    </div>
</form>
</div>



  	
</div>
    
  </body>
</html>
