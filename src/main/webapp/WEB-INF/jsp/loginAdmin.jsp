<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Quản Trị Đăng Nhập</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="css/maruti-login.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>

    </head>
    <body>
        <div id="loginbox"  ng-app="listAdmin" ng-controller="adminController" >  <span style="color: red">{{message}}</span>            
            <form id="loginform" class="form-vertical" ng-submit="submitEmployee()">
                <div class="control-group normal_text"> <h3><img src="img/logo.png" alt="Logo" /></h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on"><i class="icon-user"></i></span><input type="text" placeholder="Username" ng-model="code" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on"><i class="icon-lock"></i></span><input type="password" placeholder="Password" ng-model="password" />
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-inverse" id="to-recover">Lost password?</a></span>
                    <span class="pull-right"><input type="submit" class="btn btn-success" value="Login" /></span>
                </div>
            </form>
            <form id="recoverform" action="#" class="form-vertical">
                <p class="normal_text">Enter your e-mail address below and we will send you instructions how to recover a password.</p>

                <div class="controls">
                    <div class="main_input_box">
                        <span class="add-on"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
                    </div>
                </div>

                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-inverse" id="to-login">&laquo; Back to login</a></span>
                    <span class="pull-right"><input type="submit" class="btn btn-info" value="Recover" /></span>
                </div>
            </form>
        </div>

        <script src="js/jquery.min.js"></script>  
        <script src="js/maruti.login.js"></script> 
        <script>var urlBase = window.location.protocol + "//" + window.location.host + "${pageContext.request.contextPath}";</script>
        <script>
            var app = angular.module('listAdmin', []);
            app.controller('adminController', function ($scope, $http, $filter) {

                $scope.code = "";
                $scope.password = "";

                $scope.submitEmployee = function () {
                    $http({
                        method: "POST",
                        url: "/loginAdmin",
                        params: {code: $scope.code, password: $scope.password}
                    }).then(
                            function Succes(resp) {
                                console.log(resp.data);
                                if (resp.data.code === 1)
                                {
                                    $scope.message = "Đăng Nhập Thành Công";
                                    window.location = urlBase + '/pageAdmin/index';
                                } else {
                                    $scope.message = "Đăng Nhập Thất Bại";
                                }
                            }, function Error(resp) {
                        console.log("Error: " + resp.status + " : " + resp.data);
                        console.log(resp.data);
                        $scope.message = "Đăng Nhập Thất Bại";
                    });
                };
            });
        </script>
    </body>

</html>
