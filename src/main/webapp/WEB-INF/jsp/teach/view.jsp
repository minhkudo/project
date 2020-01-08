<%@page language="java" contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/css.jsp" %>
<%@ include file="../pageAdmin/include/menu.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"><a href="<c:url value="/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid" ng-app="listTeach" ng-controller="teachController"  ng-init="maxRow = '20'; crPage = '1'; name = ''; code = ''; status = '-1';">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"><i class="icon-align-justify"></i> </span>
                        <h5>Tìm kiếm</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <form class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">Tên Giáo Viên</label>
                                <div class="controls">
                                    <input class="text-input" ng-model="name" type="text">
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    Mã Giáo Viên
                                    <input class="text-input" ng-model="code" type="text">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Trạng Thái</label>
                                <div class="controls">
                                    <select style="width: 150px;vertical-align: middle" ng-model="status">
                                        <option value="-1">---Tất Cả---</option>
                                        <option value="0">Nghỉ Dạy</option>
                                        <option value="1">Đang Dạy</option>
                                    </select>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <div  ng-click="reloadFilter()" class="btn btn-success">Tìm kiếm</div>
                                    &nbsp;&nbsp;&nbsp;
                                    <input type="reset" ng-click="reset()" class="btn btn-warning" name="Reset Form" value="reset"/>
                                    &nbsp;&nbsp;&nbsp;
                                    <input class="btn btn-primary" onclick="location.href = '<c:url value="/pageAdmin/teach/addRest" />'" value="Thêm mới" type="button">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
                        <h5>Danh sách Giáo Viên <span ng-model="message" style="color: fuchsia;margin-left: 15px">{{message}}{{result.message}}</span></h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Mã Giáo Viên</th>
                                    <th>Tên Giáo Viên</th>
                                    <th>Ghi Chú</th>
                                    <th>Trạng Thái</th>
                                    <th>Sửa/Xóa</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="teach in listData">
                                    <td>{{$index + 1}}</td>
                                    <td>{{teach.code}}</td>
                                    <td>{{teach.name}}</td>
                                    <td>{{teach.noti}}</td>
                                    <td>{{teach.status == 1 ? "Đang Dạy" : "Nghỉ Dạy" }} </td>
                                    <td style="width: 70px">
                                        <a href="${pageContext.request.contextPath}/pageAdmin/teach/editRest?id={{teach.id}}">
                                            Sửa
                                        </a>
                                        &nbsp;&nbsp;&nbsp;
                                        <a ng-click="delete(teach.id)" href="">
                                            Xóa
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
var app = angular.module('listTeach', []);
app.controller('teachController', function ($scope, $http, $filter) {
    $scope.reloadFilter = function (str) {
        $http({
            method: 'POST',
            url: urlBase + '/pageAdmin/teach/view',
            params: {crPage: $scope.crPage, maxRow: $scope.maxRow, code: $scope.code, name: $scope.name, status: $scope.status}
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
        $scope.name = '';
        $scope.code = '';
        $scope.status = '-1';
        $scope.reloadFilter();
    };

    $scope.delete = function (id) {
        if (confirm("Are you sure you want to delete this?")) {
            $http({
                method: "DELETE",
                url: urlBase + "/pageAdmin/teach/delete",
                params: {id: id}
            }).then(function Succes(resp) {
                console.log(resp.data.messing);
                $scope.reloadFilter(resp.data.messing);
            }, function Error(response) {
                console.log("Error: " + res.status + " : " + res.data);
            });
        }
    };
});
</script>
