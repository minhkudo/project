<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../include/css.jsp" %>
<%@ include file="../pageAdmin/include/menu.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"><a href="<c:url value="/admin/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <c:if test="${result.code==0}">
                    <button class="close" data-dismiss="alert">×</button><strong>Error!</strong> ${result.messing}</div>
                </c:if>
            <div class="widget-box" style="margin: 20px" ng-app="listSubTeach" ng-controller="subTeachController" >{{message}}
                <div class="widget-content nopadding">
                    <form ng-submit="submitEmployee()">
                        <table class="table table-bordered table-striped">
                            <tr>
                                <td style="width: 20%"></td>
                                <td><input type="hidden" ng-model="subTeachForm.id"  /></td>
                            </tr>
                            <tr>
                                <td style="width: 20%">Mã Môn Học</td>
                                <td>
                                    <input type="text" ng-model="subTeachForm.code_sub" ng-required="true"  />
                                </td>
                            </tr>
                            <tr>
                                <td>Mã Giáo Viên</td>
                                <td>
                                    <select ng-model="subTeachForm.code_teach" >
                                        <option ng-repeat="option in select.availableOptions" ng-value="option.code">[{{option.code}}] {{option.name}}</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="Submit" class="blue-button" />
                                </td>
                            </tr>
                        </table>
                    </form>     
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../include/page.jsp" %>
<%@ include file="../include/footer.jsp" %>
<script src="<c:url value='/js/jquery.min.js'/>"></script>
<script src="<c:url value='/js/jquery.validate.js'/>"></script>
<script src="<c:url value='/js/jquery.ui.custom.js'/>"></script>
<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/js/maruti.popover.js'/>"></script>
<script>
                                            var app = angular.module('listSubTeach', []);
                                            app.controller('subTeachController', function ($scope, $http, $filter) {
                                                $scope.subTeachForm = {
                                                    id: "",
                                                    code_sub: "",
                                                    code_teach: ""
                                                };

                                                $scope.select = {
                                                    model: null,
                                                    availableOptions: []
                                                };

                                                $scope.init = function () {
                                                    // check if there is query in url
                                                    // and fire search in case its value is not empty
                                                    $http({
                                                        method: "PUT",
                                                        url: "/pageAdmin/subTeach/editRest",
                                                        params: {id: ${id}},
                                                        headers: {
                                                            Authorization: "${token}"
                                                        }
                                                    }).then(
                                                            function Succes(resp) {
                                                                console.log(resp.data);
                                                                $scope.subTeachForm = resp.data;
                                                            }, function Error(resp) {
                                                        console.log("Error: " + resp.status + " : " + resp.data);
                                                        console.log(resp.data);
                                                        $scope.message = "Thêm Mới Thất Bại";
                                                    });


                                                    $http({
                                                        method: "POST",
                                                        url: "/pageAdmin/teach/",
                                                        params: {crPage: 1, maxRow: 100, code: '', name: '', status: '1'},
                                                        headers: {
                                                            Authorization: "${token}"
                                                        }
                                                    }).then(
                                                            function Succes(resp) {
                                                                console.log(resp.data);
                                                                $scope.select.availableOptions = resp.data.listObject;
                                                            }, function Error(resp) {
                                                        console.log("Error: " + resp.status + " : " + resp.data);
                                                        console.log(resp.data);
                                                        $scope.message = "Thêm Mới Thất Bại";
                                                    });

                                                };

                                                $scope.init();

                                                $scope.findData = function () {
                                                    $http({
                                                        method: "PUT",
                                                        url: "/pageAdmin/subTeach/editRest",
                                                        params: {id: ${id}},
                                                        headers: {
                                                            Authorization: "${token}"
                                                        }
                                                    }).then(
                                                            function Succes(resp) {
                                                                console.log(resp.data);
                                                                $scope.subTeachForm = resp.data;
                                                            }, function Error(resp) {
                                                        console.log("Error: " + resp.status + " : " + resp.data);
                                                        console.log(resp.data);
                                                        $scope.message = "Thêm Mới Thất Bại";
                                                    });
                                                };

                                                $scope.submitEmployee = function () {
                                                    $http({
                                                        method: "POST",
                                                        url: "/pageAdmin/subTeach/editRest",
                                                        data: angular.toJson($scope.subTeachForm),
                                                        headers: {
                                                            Authorization: "${token}"
                                                        }
                                                    }).then(
                                                            function Succes(resp) {
                                                                $scope.message = resp.data.message;
                                                                $scope.code = resp.data.code;
                                                                if ($scope.code === 1)
                                                                {
                                                                    window.location = urlBase + '/pageAdmin/subTeach/view';
                                                                }
                                                            }, function Error(resp) {
                                                        console.log("Error: " + resp.status + " : " + resp.data);
                                                        console.log(resp.data);
                                                        $scope.message = "Cập Nhật Thất Bại";
                                                    });
                                                };
                                            });
</script>
