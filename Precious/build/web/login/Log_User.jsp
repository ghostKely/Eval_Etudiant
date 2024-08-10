<%-- 
    Document   : Log_User
    Created on : 25 juil. 2024, 19:14:09
    Author     : ETU1886-Fanirina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/js/adminLogin/css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-scroller">
          <div class="container-fluid page-body-wrapper full-page-wrapper">
            <div class="content-wrapper d-flex align-items-center auth px-0">
              <div class="row w-100 mx-0">
                <div class="col-lg-4 mx-auto">
                  <div class="auth-form-light text-left py-5 px-4 px-sm-5">
                    <h4>Login page</h4>
                    <form action="C_LoginUser_A" method="POST" class="pt-3">
                        <div class="form-group">
                            <input name="etudiant" type="text" class="form-control form-control-lg" placeholder="ETU 0000" value="001886" required>
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
    </body>
        <script src="./assets/js/adminLogin/js/vendor.bundle.base.js"></script>
        <script src="./assets/js/adminLogin/js/template.js"></script>
</html>
