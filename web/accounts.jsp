<<<<<<< OURS
<%-- 
    Document   : account.jsp
    Created on : Sep 18, 2023, 4:26:17 PM
    Author     : MSI GF
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Accounts - Product Admin Template</title>
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
            />
        <!-- Link icon boostrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="css/bootstrap1.min.css" />
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="css/templatemo-style.css">
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin
        -->
    </head>

    <body id="reportsPage">
        <div class="" id="home">
            <%@include file="menu-dashboard.jsp" %>
            <div class="container mt-5">
                <div class="row tm-content-row">
                    <div class="col-12 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                            <h2 class="tm-block-title">List of Accounts</h2>
                            <c:if test="${mess!=null }">
                                <div class="alert alert-success" role="alert">
                                    ${mess}
                                </div>
                            </c:if>
                            <p class="text-white">Accounts</p>
                            <select onclick="selectRole(this)" name="roleId" class="custom-select">
                                <option value="1">Default</option>
                                <option value="1">Admin</option>
                                <option value="2">Customer</option>
                                <option value="4">Product Manager</option>
                                <option value="5">Marketing Manager</option>
                            </select>
                        </div>
                    </div>
                </div>
                <!-- row -->
                <div class="row tm-content-row">
                    <div class="col-12 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block tm-block-products">
                            <div class="tm-product-table-container">
                                <table class="table table-hover tm-table-small tm-product-table">
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">Full Name</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Gender</th>
                                            <th scope="col">Phone Number</th>
                                            <th scope="col">Address</th>
                                            <th scope="col">Status</th>
                                            <th scope="col">Created Date</th>
                                            <th scope="col">Role</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="content">
                                        <c:forEach items="${listA}" var="a">
                                            <tr>
                                                <td>${a.id}</td>
                                                <td>${a.fullName}</td>
                                                <td>${a.email}</td>
                                                <c:if test="${a.gender == 1}">
                                                    <td>Male</td>
                                                </c:if>
                                                <c:if test="${a.gender == 0}">
                                                    <td>Female</td>
                                                </c:if>
                                                <td>${a.phoneNumber}</td>
                                                <td>${a.address}</td>
                                                <c:if test="${a.status == 1}">
                                                    <td>Active</td>
                                                </c:if>
                                                <c:if test="${a.status == 0}">
                                                    <td>Blocked</td>
                                                </c:if>
                                                <td>${a.createDate}</td>
                                                <c:if test="${a.roleId == 1}">
                                                    <td>Admin</td>
                                                </c:if>
                                                <c:if test="${a.roleId == 2}">
                                                    <td>Customer</td>
                                                </c:if>

                                                <c:if test="${a.roleId == 4}">
                                                    <td>Product Manager</td>
                                                </c:if>
                                                <c:if test="${a.roleId == 5}">
                                                    <td>Marketing Manager</td>
                                                </c:if>

                                                <td>
                                                    <a href="loadAccount?id=${a.id}" class="tm-product-delete-link">
                                                        <i class="bi bi-pencil-square tm-product-delete-icon"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- table container -->
                            <a
                                href="add-account.jsp"
                                class="btn btn-primary btn-block text-uppercase mb-3">Add new product</a>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="tm-footer row tm-mt-small">
                <div class="col-12 font-weight-light">
                    <p class="text-center text-white mb-0 px-4 small">
                        Copyright &copy; <b>2018</b> All rights reserved. 

                        Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
                    </p>
                </div>
            </footer>
        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <!-- https://jquery.com/download/ -->
        <script src="js/bootstrap1.min.js"></script>
        <!-- https://getbootstrap.com/ -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            function selectRole(param) {
                var roleId = param.value;
                $.ajax({
                    url: "/onlineshop/selectRole",
                    type: "get", //send it through get method
                    data: {
                        roleId: roleId
                    },
                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                });
            }
        </script>
    </body>
</html>

=======
<%-- 
    Document   : account.jsp
    Created on : Sep 18, 2023, 4:26:17 PM
    Author     : MSI GF
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Accounts - Product Admin Template</title>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Roboto:400,700"
    />
    <!-- Link icon boostrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <!-- https://fonts.google.com/specimen/Roboto -->
    <link rel="stylesheet" href="css/fontawesome.min.css" />
    <!-- https://fontawesome.com/ -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- https://getbootstrap.com/ -->
    <link rel="stylesheet" href="css/templatemo-style.css">
    <!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
  </head>

  <body id="reportsPage">
    <div class="" id="home">
      <%@include file="menu-dashboard.jsp" %>
      <div class="container mt-5">
        <div class="row tm-content-row">
          <div class="col-12 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
              <h2 class="tm-block-title">List of Accounts</h2>
              <c:if test="${mess!=null }">
                            <div class="alert alert-success" role="alert">
                                ${mess}
                            </div>
                        </c:if>
              <p class="text-white">Accounts</p>
              <select class="custom-select">
                <option value="0">Select account</option>
                <option value="1">Admin</option>
                <option value="2">Editor</option>
                <option value="3">Merchant</option>
                <option value="4">Customer</option>
              </select>
            </div>
          </div>
        </div>
        <!-- row -->
        <div class="row tm-content-row">
        <div class="col-12 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-products">
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table">
                <thead>
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Full Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Gender</th>
                    <th scope="col">Phone Number</th>
                    <th scope="col">Address</th>
                    <th scope="col">Status</th>
                    <th scope="col">Created Date</th>
                    <th scope="col">Role</th>
                    <th scope="col">Action</th>
                  </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listA}" var="a">
                  <tr>
                    <td>${a.id}</td>
                    <td>${a.fullName}</td>
                    <td>${a.email}</td>
                    <c:if test="${a.gender == 1}">
                        <td>Male</td>
                    </c:if>
                    <c:if test="${a.gender == 0}">
                        <td>Female</td>
                    </c:if>
                    <td>${a.phoneNumber}</td>
                    <td>${a.address}</td>
                    <c:if test="${a.status == 1}">
                        <td>Active</td>
                    </c:if>
                    <c:if test="${a.status == 0}">
                        <td>Blocked</td>
                    </c:if>
                    <td>${a.createDate}</td>
                    <c:if test="${a.roleId == 1}">
                        <td>Admin</td>
                    </c:if>
                    <c:if test="${a.roleId == 2}">
                        <td>Customer</td>
                    </c:if>
                    
                    <c:if test="${a.roleId == 4}">
                        <td>Product Manager</td>
                    </c:if>
                    <c:if test="${a.roleId == 5}">
                        <td>Marketing Manager</td>
                    </c:if>
                    
                    <td>
                      <a href="loadAccount?id=${a.id}" class="tm-product-delete-link">
                        <i class="bi bi-pencil-square tm-product-delete-icon"></i>
                      </a>
<!--                      <a href="" class="tm-product-delete-link">
                        <i class="bi bi-trash tm-product-delete-icon"></i>
                      </a>-->
                    </td>
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
            <!-- table container -->
            <a
              href="add-account.jsp"
              class="btn btn-primary btn-block text-uppercase mb-3">Add new product</a>
          </div>
        </div>
        
      </div>
      </div>
      <footer class="tm-footer row tm-mt-small">
        <div class="col-12 font-weight-light">
          <p class="text-center text-white mb-0 px-4 small">
            Copyright &copy; <b>2018</b> All rights reserved. 
            
            Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
          </p>
        </div>
      </footer>
    </div>

    <script src="js/jquery-3.3.1.min.js"></script>
    <!-- https://jquery.com/download/ -->
    <script src="js/bootstrap.min.js"></script>
    <!-- https://getbootstrap.com/ -->
  </body>
</html>

>>>>>>> THEIRS
