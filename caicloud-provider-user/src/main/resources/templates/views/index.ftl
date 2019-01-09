<!DOCTYPE html>
<html>
<head>
    <title>FreeMarker 用户表单提交</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div>用户登录表单</div>
<form name="frmLogin" action="/user.from" method="post">
    用户名: <input type="text" name="username"><br/>
    别名: <input type="text" name="name"><br/>
    年龄: <input type="text" name="age"><br/>
    分值: <input type="text" name="balance"><br/>
    <input type="submit">
</form>
</body>
</html>