<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
    
    <title>My JSP 'notice.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	 <script type="text/javascript" src="ckeditor/ckeditor.js"></script>

  </head>
  
  <body>
  
  <jsp:include page="/pages/header.jsp"/>
  
  <div class="container" style="margin-top: 80px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation" class="active"><a href="<%=path%>/manage/notice.jsp">发布公告</a></li>
                <li role="presentation" ><a href="<%=path%>/manage/change-admin.jsp">资料修改</a></li>
                <li role="presentation"><a href="<%=path%>/manage/newpost.jsp">查看新帖</a></li>
                <li role="presentation"><a href="<%=path%>/manage/bestpost.jsp">精华帖请求</a></li>
                <li role="presentation"><a href="<%=path%>/manage/limit.jsp">封锁用户</a></li>
                <li role="presentation"><a href="<%=path%>/manage/create_discuss.jsp">创建讨论区</a></li>
                <!--<li role="presentation"><a href="#">Messages</a></li>-->
            </ul>
        </div>

  <div class="col-md-9">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        发布公告
                    </h3>
                </div>
                <div class="panel-body">


                    <div class="column">

					<form action="<%=path%>/notice.action" method="post">
                        <div class="form-group">
                            <label for="name">公告标题</label>
                            <input required type="text" class="form-control" name="title" id="name" width="200px" height="80px"
                                   placeholder="请输入标题">
                        </div>




                        <dl class="form-group">
                            <dt><label >公告内容</label></dt>
                            <dd> <textarea id="TextArea1" cols="20" rows="5" name="content" class="ckeditor"></textarea></dd>
                        </dl>


                        <p><button type="submit" class="btn btn-primary">发布公告</button></p>
                        <s:fielderror fieldName="notice_result"></s:fielderror>
                    
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
   
  </body>
  <script type="text/javascript" src="js/jquery.validate.min.js"></script>
  <script type="text/javascript">
  	$(function(){
  	 	$("#signupForm").validate();
  	});
  </script>
</html>
