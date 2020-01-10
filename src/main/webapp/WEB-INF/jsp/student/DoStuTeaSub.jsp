<%@page language="java" contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session = "true" %>
<%@ include file="../pageStudent/include/css.jsp" %>
<%@ include file="../pageStudent/include/menu.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"><a href="<c:url value="/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid" ng-app="listStudent" ng-controller="studentController"  ng-init="maxRow = '20'; crPage = '1'; codeSub = ''; codeTeach = '';">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"><i class="icon-align-justify"></i> </span>
                        <h5>Tìm kiếm</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <form class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">Mã Môn Học</label>
                                <div class="controls">
                                    <input class="text-input" ng-model="codeSub" type="text">
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    Mã Giáo Viên
                                    <input class="text-input" ng-model="codeTeach" type="text">
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <div  ng-click="reloadFilter()" class="btn btn-success">Tìm kiếm</div>
                                    &nbsp;&nbsp;&nbsp;
                                    <input type="reset" ng-click="reset()" class="btn btn-warning" name="Reset Form" value="reset"/>
                                    <!--                                    &nbsp;&nbsp;&nbsp;
                                                                        <input class="btn btn-primary" onclick="location.href = '<c:url value="/student/add" />'" value="Thêm mới" type="button">-->
                                    <!--                                    &nbsp;&nbsp;&nbsp;
                                                                        <input class="btn btn-primary" onclick="location.href = '<c:url value="/student/addRest" />'" value="Thêm mới" type="button">-->
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
                        <h5>Danh sách Môn Học Đã Đăng Ký <span ng-model="message" style="color: fuchsia;margin-left: 15px">{{message}}{{result.message}}</span></h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Mã Môn Học</th>
                                    <th>Mã Giáo Viên</th>
                                    <th>Đăng Ký</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="std in listData">
                                    <td>{{$index + 1}}</td>
                                    <td>{{std.code_sub}}</td>
                                    <td>{{std.code_teach}}</td>
                                    <td style="width: 170px">
<!--                                        <a href="${pageContext.request.contextPath}/student/edit?id={{std.id}}">
                                            Sửa
                                        </a>-->
<!--                                        <a href="${pageContext.request.contextPath}/student/regis?code_sub={{std.code_sub}}">
                                            Sửa
                                        </a>-->
                                        &nbsp;&nbsp;&nbsp;
                                        <a ng-click="delete(std.code_sub, std.code_teach)" href="">
                                            Hủy Đăng Ký
                                        </a>
                                    </td>
                                </tr>
                                <tr ng-if="totalRow > maxRow">
                                    <td colspan="13">
                                        <div class="pagination aalternate fr">
                                            <ul uib-pagination total-items="totalRow" ng-change="pageChanged()" ng-model="crPage" max-size="maxRow" items-per-page="maxRow" class="" boundary-links="true" num-pages="numPages" ></ul>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
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
                                                var app = angular.module('listStudent', []);
                                                app.controller('studentController', function ($scope, $http, $filter) {
                                                    $scope.reloadFilter = function (str) {
                                                        $http({
                                                            method: 'POST',
                                                            url: urlBase + '/pageStudent/DoStuTeaSub',
                                                            params: {crPage: $scope.crPage, maxRow: $scope.maxRow, codeSub: $scope.codeSub, codeTeach: $scope.codeTeach},
                                                            headers: {
                                                                Authorization: "<%=(String) session.getAttribute(session.getId()) %>"
                                                            }
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
                                                                },
                                                                function Error(res) { // error
                                                                    console.log("Error: " + res.status + " : " + res.data);
                                                                }
                                                        );
                                                    };

                                                    $scope.$watch('crPage + crPage', function () {
                                                        $scope.reloadFilter();
                                                    });

                                                    $scope.reset = function () {
                                                        $scope.codeSub = '';
                                                        $scope.codeTeach = '';
                                                        $scope.reloadFilter();
                                                    };

                                                    $scope.delete = function (codeSub, codeTeach) {
                                                        if (confirm("Are you sure you want to delete this?")) {
                                                            $http({
                                                                method: "POST",
                                                                url: urlBase + "/pageStudent/registerNot",
                                                                params: {codeSub: codeSub, codeTeach: codeTeach},
                                                                headers: {
                                                                    Authorization: "<%=(String) session.getAttribute(session.getId()) %>"
                                                                }
                                                            }).then(function Succes(resp) {
                                                                console.log(resp.data.messing);
                                                                $scope.reloadFilter(resp.data.messing);
                                                            }, function Error(resp) {
                                                                console.log("Error: " + resp.status + " : " + resp.data);
                                                            });
                                                        }
                                                    };

//    $scope.regis = function (codeSub,codeTeach) {
//        if (confirm("Are you sure you want to register this?")) {
//            $http({
//                method: "POST",
//                url: urlBase + "/student/registerDo",
//                params: {codeSub: codeSub,codeTeach: codeTeach}
//            }).then(function Succes(resp) {
//                console.log(resp.data.messing);
//                $scope.reloadFilter(resp.data.messing);
//            }, function Error(resp) {
//                console.log("Error: " + resp.status + " : " + resp.data);
//            });
//        }
//    };
                                                });
</script>
