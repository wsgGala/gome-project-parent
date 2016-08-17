<%--
  Created by IntelliJ IDEA.
  User: qiaowentao
  Date: 2016/8/17
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
contextPath="${rc.contextPath}";
<script src="contextPath/js/jQuery-2.1.3.min.js" type="text/javascript">


</script>
<body>

    <form action="contextPath/login" class="loginClass" method="post">

        用户名:<input type="text" name="username"><br/><br/>
        密码:<input type="password" name="password"><br/><br/>
        <input type="submit" value="登录">

    </form>

</body>
</html>
