<%-- 
    Document   : forgetPassword
    Created on : Sep 17, 2023, 4:29:04 PM
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
    </head>
    <body>
        <div class="container">
            <c:if test="${verified==null}">
                <div class="row d-flex justify-content-center align-items-center" style="margin-top: 50px">
                    <div class="alert alert-success col-md-12" role="alert" id="notes">
                        <h4>Bạn quên mật khẩu vào tài khoản?</h4>
                        <ul>
                            <li>Vui lòng nhập địa chỉ email đã đăng ký với chúng tôi để tạo mật khẩu mới</li>
                            <li>Chúng tôi sẽ gửi 1 email vào địa chỉ email cung cấp và yêu cầu xác minh trước khi có thể tạo mật khẩu mới</li>
                        </ul>
                    </div>
                </div>
                <div class="row d-flex justify-content-center">
                    <div class="col-md-12">
                        <div class="jumbotron text-center">
                            <h2>Nhập email của bạn</h2>
                            <c:if test="${err!=null}">
                                <font color ="red">  ${err}</font>
                            </c:if>
                            <form method="post" action="forget">
                                <div class="form-group d-flex justify-content-center align-items-center">
                                    <input type="email" class="form-control col-md-6 " name="email" required>
                                    <input class="btn btn-primary" type="submit" value="Send" style="margin-left: 20px">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${verified=='verified'}">
                <div class="row d-flex justify-content-center align-items-center" style="margin-top: 50px">
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
                            <h2>Nhập mã xác nhận!!!</h2>
                            <c:if test="${err!=null}">
                                <font color ="red">  ${err}</font>
                            </c:if>
                            <form method="post" action="forget">
                                <div class="form-group d-flex justify-content-center align-items-center">
                                    <input type="text" class="form-control col-md-6 " name="code" required>
                                    <input class="btn btn-primary" type="submit" value="Verify" style="margin-left: 20px">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${changepass!=null}">
                <div class="row d-flex justify-content-center align-items-center" style="margin-top: 50px">
                    <div class="alert alert-success col-md-12" role="alert" id="notes">
                        <h4>Tạo mật khẩu</h4>
                        <ul>
                            <li>Bạn cần tạo mật khẩu mới cho lần đăng nhập tiếp theo</li>
                            <li>Vui lòng làm theo các hướng dẫn bên dưới để tạo mậ khẩu</li>
                        </ul>
                    </div>
                </div>
                <!-- Verification Entry Jumbotron -->
                <div class="row d-flex justify-content-center align-items-center vh-100">
                    <div class="col-md-12">
                        <div class="jumbotron text-center">
                            <div id="errorMessage" style="color: red;"></div>
                            <form method="post" action="forget" onsubmit="return validateForm()">
                                <div class="form-group ">
                                    <div class="form-outline mb-4" style="width: 400px ; margin: auto" >
                                        <input name="password"  type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
                                    </div>
                                    <div class="form-outline mb-4" style="width: 400px ; margin: auto">
                                        <input name="repassword"  type="password" id="inputPassword" class="form-control" placeholder="Repeat Password" required="">
                                    </div>

                                    <button class="btn btn-primary btn-block" type="submit" style="width: 120px ; margin: auto">Submit</button>
                                </div>
                                <script>
                                    window.onload = function () {
                                        var err = "${err}";
                                        if (msg) {
                                            document.getElementById("errorMessage").innerHTML = err;
                                        }
                                    }
                                    function validateForm() {
                                        var password = document.getElementsByName("password")[0];
                                        var repassword = document.getElementsByName("repassword")[0];

                                        if (password.value == "" || password.value.trim().length == 0) {
                                            document.getElementById("errorMessage").innerHTML = "Mật khẩu không được để trống";
                                            password.value = "";
                                            repassword.value = "";
                                            return false;
                                        }

                                        if (password.value.length < 6) {
                                            document.getElementById("errorMessage").innerHTML = "Mật khẩu phải có ít nhất 6 ký tự";
                                            return false;
                                        }

                                        if (password.value != repassword.value) {
                                            document.getElementById("errorMessage").innerHTML = "Mật khẩu và mật khẩu lặp lại phải giống nhau";
                                            return false;
                                        }
                                    }
                                </script>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>
