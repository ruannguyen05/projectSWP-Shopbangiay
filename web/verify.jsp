<%-- 
    Document   : verify
    Created on : Sep 15, 2023, 11:22:04 AM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <style>

            div#notes {
                margin-top: 30px;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <!-- Instructions -->
            <div class="row d-flex justify-content-center align-items-center">
                <div class="alert alert-success col-md-12" role="alert" id="notes">
                    <h4>Xác nhận</h4>
                    <ul>
                        <li>Vui lòng kiểm tra email của bạn.Chúng tôi đã gửi mã xác nhận về mail của bạn!!</li>
                        <li>If somehow, you did not recieve the verification email then <a href="#">resend the verification email</a></li>
                    </ul>
                </div>
            </div>
            <!-- Verification Entry Jumbotron -->
            <div class="row d-flex justify-content-center">
                <div class="col-md-12">
                    <div class="jumbotron text-center">
                        <h2>Nhập mã xác nhận bên dưới !!!</h2>
                        <c:if test="${err !=null}">
                            <font color ="red">  ${err}</font>
                        </c:if>
                        <form method="post" action="${authenticationfor}">
                            <div class="form-group d-flex justify-content-center align-items-center">
                                <input type="text" class="form-control col-md-6 " name="verify" required>
                                <input class="btn btn-primary" type="submit" value="Verify" style="margin-left: 20px">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
