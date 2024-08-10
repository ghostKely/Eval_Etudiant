<%-- 
    Document   : Log_Admin
    Created on : 25 juil. 2024, 18:07:09
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/js/adminLogin/css/style.css">
        <link rel="stylesheet" href="./assets/js/adminLogin/css/materialdesignicon.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-scroller">
          <div class="container-fluid page-body-wrapper full-page-wrapper">
            <div class="content-wrapper d-flex align-items-center auth px-0">
              <div class="row w-100 mx-0">
                <div class="col-lg-4 mx-auto">
                <div>
                </div>
                  <div class="auth-form-light text-left py-5 px-4 px-sm-5">
                    <h4>Login page<i class="mdi mdi-youtube-play"></i></h4>
                    <form action="C_LoginAdmin_A" method="POST" class="pt-3">
                      <div class="form-group">
                          <input type="text" name="userName" class="form-control form-control-lg" id="exampleInputEmail1" placeholder="Username" value="admin">
                      </div>
                      <div class="form-group">
                          <input type="password" name="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Password" value="admin">
                      </div>
                      <div class="mt-3">
                        <button class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">LOG IN</button>
                      </div>
                      <div class="text-center mt-4 font-weight-light">
                        <a href="index.jsp" class="text-primary">Back to homepage</a>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <script src="./assets/js/adminLogin/js/vendor.bundle.base.js"></script>
        <script src="./assets/js/adminLogin/js/template.js"></script>
    </body>
</html>
