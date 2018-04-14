<%@page import="com.bbs.model.Notice"%>
<%@page import="com.bbs.service.NoticeBiz"%>
<%@page import="com.bbs.service.UserBiz"%>
<%@page import="com.bbs.model.User"%>
<%@page import="com.bbs.model.Followcard"%>
<%@page import="com.bbs.model.Post"%>
<%@page import="com.bbs.service.PostBiz"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" autoFlush="false" buffer="1000kb"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>公告浏览</title>
    
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
  		NoticeBiz noticeBiz = (NoticeBiz)context.getBean("noticeBiz");
  		 int noticeId  = 1;
  		 String pid = request.getParameter("noticeId");
  		 if (pid == null)
  		 noticeId = (Integer)request.getAttribute("postId");
  		 else 
  		 noticeId = Integer.parseInt(pid);
  		 
  		 Notice notice = noticeBiz.getNoticeById(noticeId);
  		 if (notice == null)
  		 return ;
  		 
  		
  	 	
  	 %>
  	 
  
    <div class="row">
       <div class="col-md-1 post-border">
       </div>
        <div class="col-md-2 post-head">
                <!--<img src="img/tm-bg-slide-1.jpg" width="80px" height="80px" class="img-responsive img-circle">-->
            <img  alt="" class="img-responsive img-circle" src="<%=path+"/"+notice.getAdmin().getPhotoUrl() %>"
                  style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>

                <span class="user-info">
                            <span class="badge" style="background: #f1c40f;margin-top: 5px">公告发布者</span>
                            :<span class="badge" style="background: #f1c40f;margin-top: 5px"><%=notice.getAdmin().getUserName()%></span>
                        </span><br/>
                        <span class="user-info">
                            <span class="badge" style="background: #2ecc71;margin-top: 5px">性别</span>
                            :<span class="badge" style="background: #2ecc71;margin-top: 5px"><%=notice.getAdmin().getSex()%></span>
                        </span><br/>
                        
            <br>
        </div>
        <div class="col-md-8 post-content">

            <div class="post-title">
                <h2 style="margin-left:20px;color:black"><%=notice.getTitle()%></h2>
                
                <strong style=" float:right;margin-right:10px;margin-top: 10px">
                    <span class="badge" style="background: #ff6927;width: 50px;">管理员</span></strong>

            </div>
            <div style="margin: 20px">
               <%=notice.getContent()%>
            </div>

        </div>
         <div class="col-md-1 post-border">
       </div>

        


    </div>
    </div>
       
  	

  	
		</div>
</div>
    
  </body>
</html>
