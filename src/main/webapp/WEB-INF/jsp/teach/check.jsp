<%@page language="java" contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/css.jsp" %>
<%@ include file="../pageTeach/include/menu.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"><a href="<c:url value="/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid" ng-app="listTeach" ng-controller="teachController"  ng-init="maxRow = '20'; crPage = '1';">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
                        <h5>Danh sách Sinh Viên Trong Môn Học <span ng-model="message" style="color: fuchsia;margin-left: 15px">{{message}}{{result.message}}</span></h5>
                    </div>
                    <div class="widget-content nopadding">
                        <form ng-submit="submitEmployee(listData)">
                            <table class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Mã Sinh Viên</th>
                                        <th>Điểm Danh</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="teach in listData">
                                        <td style="width: 70px">{{$index + 1}}</td>
                                        <td>{{teach.code_student}}</td>
                                        <td><input type="checkbox" ng-model="teach.status" ng-true-value="1" ng-false-value="0"  /></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <input type="submit" value="Submit" class="blue-button" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../include/page.jsp" %>
<%@ include file="../include/footer.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script>
                                            var app = angular.module('listTeach', []);
                                            app.controller('teachController', function ($scope, $http, $filter) {
                                                $scope.reloadFilter = function (str) {
                                                    $http({
                                                        method: 'POST',
                                                        url: urlBase + '/pageTeach/check',
                                                        params: {codeSub: "${codeSub}", codeTeach: "ad"}
                                                    }).then(
                                                            function Succes(res) { // success
                                                                $scope.listData = res.data.listObject;
                                                                $scope.totalRow = res.data.totalRow;
                                                                $scope.message = str;
                                                                $scope.result = res.data.result;
                                                                console.log($scope.result.message);
                                                                if (!angular.isUndefined(str) && str !== '') {
                                                                    $scope.result.message = str;
                                                                }
                                                                for (let item of $scope.listData) {
                                                                    item.status = 0;
                                                                    console.log(item);
                                                                }
                                                            },
                                                            function Error(res) { // error
                                                                console.log("Error: " + res.status + " : " + res.data);
                                                            }
                                                    );
                                                };

                                                $scope.$watch('crPage + crPage', function () {
                                                    $scope.reloadFilter();
                                                });

                                                $scope.checkForm = {
                                                    id: "",
                                                    status: 0
                                                };

                                                $scope.submitEmployee = function (str) {
                                                    $http({
                                                        method: "PUT",
                                                        url: "/pageTeach/check",
                                                        data: str
                                                    }).then(
                                                            function Succes(resp) {
                                                                console.log(resp.data);
                                                                $scope.message = resp.data.message;
                                                                $scope.code = resp.data.code;
                                                                if ($scope.code === 1)
                                                                {
                                                                    $scope.message = "Điểm Danh Thành Công";
                                                                    window.location = urlBase + '/pageTeach/list';
                                                                }
                                                                else
                                                                {
                                                                    $scope.message = "Điểm Danh Thất Bại";
                                                                }
//                                                                if (resp.data == true)
//                                                                {
//                                                                    $scope.message = "Điểm Danh Thành Công";
//                                                                } else
//                                                                {
//                                                                    $scope.message = "Điểm Danh Thất Bại";
//                                                                }
                                                            }, function Error(resp) {
                                                        console.log("Error: " + resp.status + " : " + resp.data);
                                                        console.log(resp.data);
                                                        $scope.message = "Thêm Mới Thất Bại";
                                                    });
                                                };
                                            });
</script>
