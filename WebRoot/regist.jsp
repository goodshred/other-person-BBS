<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'registjsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	    <link href="css/regist.css" rel="stylesheet">
	

  </head>
  
  <body>
     <jsp:include page="/pages/header.jsp"/>
 <div class="regist">
    <form id="form1" role="form" action="<%=path%>/regist.action" method="post">
            <!--<label for="name">用户名:</label>-->
            <input id="username" type="text" class="form-control" name="username" style="height: 40px; margin-top: 20px;"
                   placeholder="请输入用户名"><s:fielderror fieldName="username"></s:fielderror>
            <!--<label for="name"></label>-->
        <input id="password" type="password" class="form-control" name="password" style="height: 40px;margin-top: 20px;"
               placeholder="请输入密码"><s:fielderror fieldName="password"></s:fielderror>
      

        <input id="confirm_password" type="password" class="form-control" name="password" style="height: 40px;margin-top: 20px;"
               placeholder="请重复密码">
               
                 <div style="margin-top: 10px;margin-left: 10px;">
            <div style="float: left;margin-bottom: 10px">性别:</div>
            <div style="float:left;margin-left: 100px;margin-bottom: 10px">
                男<input type="radio" name="sex" value="男">
             </div>
            <div style="float: right;margin-right: 100px;margin-bottom: 10px">
                女<input type="radio" name="sex" value="女">
            </div>
        </div>
        <input id="email" type="email" class="form-control" name="email" style="height: 40px;margin-top: 20px;"
               placeholder="请输入邮箱"><s:fielderror fieldName="email"></s:fielderror>
        <div style="height: 80px;width: 100%;margin-top: 20px;margin-left: 30px;">
            <!--<div style="float:left;width: 100%;padding: 20px;">-->
                <input type="submit" class="btn btn-primary" value="注册"
                   style="margin:auto;width: 80%;height: 40px;padding: 10px;"></input>
            <!--</div>-->


        </div>

    </form>


</div>
 
  <div class="bottom" style="position:absolute;bottom:0px;margin-top: 20px;background-color: rgba(0,0,0,0.8);width:100%;height: 100px;color: darkgray">
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
  <script type="text/javascript" src="js/jquery.validate.min.js"></script>
  <script type="text/javascript">
  	$(function(){
  	 	$("#form1").validate({
  	 		rules:{
                    username:{
                        required:true
                    },
                    email:{
                        required:true,
                        email:true
                    },
                    password:{
                        required:true,
                        rangelength:[6,20]
                    },
                    confirm_password:{
                        equalTo:"#password"
                    }                    
                },
                messages:{
                    username:{
                        required:"必填"
                    },
                    email:{
                        required:"必填",
                        email:"E-Mail格式不正确"
                    },
                    password:{
                        required: "不能为空",
                        rangelength: $.validator.format("密码最小长度:{0}, 最大长度:{1}。")
                    },
                    confirm_password:{
                        equalTo:"两次密码输入不一致"
                    }                                    
                }
  	 	});
  	});
  </script>
  <style type="text/css">
  .error{
    color:red;
  }
  </style>
</html>
