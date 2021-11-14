<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="/css/test.css" rel="stylesheet">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


<head>
</head>

<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
            <img src="/image/logo.png" id="icon" alt="User Icon" />
        </div>

        <!-- Login Form -->
        <form>
            <input type="text" id="id" class="fadeIn second" name="login" placeholder="id">
            <input type="password" id="pw" class="fadeIn third" name="login" placeholder="password">
            <input type="button" id="submit" class="fadeIn fourth" value="Log In">
        </form>

        <!-- To Sign Up -->
<%--        <a href="signup" class="float-middle btn btn-outline-primary">Sign Up</a>--%>

    </div>
</div>
</body>

<script>
    $('#submit').click(function() {
        let id = $("#id").val();
        let pw = $("#pw").val();
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}",
            data: {
                id: id,
                pw: pw,
            },
            success: function(result) {
                switch (result)
                {
                    case 0:
                        alert("success")
                        break
                    case 1:
                        alert("id too short")
                        break
                    case 2:
                        alert("pw too short")
                        break
                    case 3:
                        alert("ID does not exist")
                        break
                    case 4:
                        alert("PW is wrong")
                        break
                }
                location.reload();
            },
            error:function(request,status,error) {
                location.replace("/error")
            }
        });
    })
</script>
</html>
