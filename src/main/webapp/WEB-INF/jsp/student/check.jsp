<%@page language="java" contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session = "false" %>
<%@ include file="../include/css.jsp" %>
<%@ include file="../pageStudent/include/menu.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"><a href="<c:url value="/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> </div>
    </div>
    <div class="container-fluid">${token}
        <div class="row-fluid" ng-app="listStudent" ng-controller="studentController">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
                        <h5>Danh sách Sinh Viên <span ng-model="message" style="color: fuchsia;margin-left: 15px">{{message}}{{result.message}}</span></h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Ngày</th>
                                    <th>Trạng Thái</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="std in listData">
                                    <td>{{$index + 1}}</td>
                                    <td>{{formatDateTime(std.time)}}</td>
                                    <td>{{std.status == 1 ? "Đi" : "Nghỉ" }} </td>
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
                                                            url: urlBase + '/pageStudent/check',
                                                            params: {id_sts: "${id_sts}"},
                                                            headers: {
                                                                Authorization: "${token}"
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
                                                        $scope.code = '';
                                                        $scope.name = '';
                                                        $scope.status = '-1';
                                                        $scope.reloadFilter();
                                                    };

                                                    $scope.formatDateTime = function (date) {
                                                        if (!angular.isUndefined(date) && date !== null)
                                                            return $filter('date')(new Date(date), 'dd/MM/yyyy HH:mm:ss');
                                                        else
                                                            return "";
                                                    };

                                                    $scope.delete = function (id) {
                                                        if (confirm("Are you sure you want to delete this?")) {
                                                            $http({
                                                                method: "POST",
                                                                url: urlBase + "/pageStudent/delete",
                                                                params: {id: id}
                                                            }).then(function Succes(resp) {
                                                                console.log(resp.data.messing);
                                                                $scope.reloadFilter(resp.data.messing);
                                                            }, function Error(resp) {
                                                                console.log("Error: " + resp.status + " : " + resp.data);
                                                            });
                                                        }
                                                    };
                                                });
</script>
