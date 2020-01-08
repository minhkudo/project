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
            <div class="widget-box" style="margin: 20px" ng-app="listSubject" ng-controller="subjectController">{{message}}
                <div class="widget-content nopadding">
                    <form ng-submit="submitEmployee()">
                        <table class="table table-bordered table-striped">
                            <tr>
                                <td>Tên Môn Học</td>
                                <td><input type="text" ng-model="subjectForm.name" ng-required="true" /></td>
                            </tr>
                            <tr>
                                <td>Số Tiết</td>
                                <td><input type="text" ng-model="subjectForm.number" /></td>
                            </tr>
                            <tr>
                                <td>Năm Học</td>
                                <td><input type="text" ng-model="subjectForm.year" /></td>
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
                                    var app = angular.module('listSubject', []);
                                    app.controller('subjectController', function ($scope, $http, $filter) {
                                        $scope.subjectForm = {
                                            id: "",
                                            code: "",
                                            name: "",
                                            number: "",
                                            text: ""
                                        };

                                        $scope.init = function () {
                                            // check if there is query in url
                                            // and fire search in case its value is not empty
                                            $http({
                                                method: "POST",
                                                url: "/pageAdmin/subject/editRest",
                                                params: {id: ${id}}
                                            }).then(
                                                    function Succes(resp) {
                                                        console.log(resp.data);
                                                        $scope.subjectForm = resp.data;
                                                    }, function Error(resp) {
                                                console.log("Error: " + resp.status + " : " + resp.data);
                                                console.log(resp.data);
                                                $scope.message = "Thêm Mới Thất Bại";
                                            });
                                        };

                                        $scope.init();

//                                        $scope.findData = function () {
//                                            $http({
//                                                method: "PUT",
//                                                url: "/subject/editRest",
//                                                params: {id: ${id}}
//                                            }).then(
//                                                    function Succes(resp) {
//                                                        console.log(resp.data);
//                                                        $scope.subjectForm = resp.data;
//                                                    }, function Error(resp) {
//                                                console.log("Error: " + resp.status + " : " + resp.data);
//                                                console.log(resp.data);
//                                                $scope.message = "Thêm Mới Thất Bại";
//                                            });
//                                        };

                                        $scope.submitEmployee = function () {
                                            $http({
                                                method: "PUT",
                                                url: "/pageAdmin/subject/editRest",
                                                data: angular.toJson($scope.subjectForm),
                                                headers: {
                                                    'Content-Type': 'application/json'
                                                }
                                            }).then(
                                                    function Succes(resp) {
                                                        $scope.message = resp.data.message;
                                                        $scope.code = resp.data.code;
                                                        if ($scope.code === 1)
                                                        {
                                                            window.location = urlBase + '/pageAdmin/subject/view';
                                                        }
                                                    }, function Error(resp) {
                                                console.log("Error: " + resp.status + " : " + resp.data);
                                                console.log(resp.data);
                                                $scope.message = "Cập Nhật Thất Bại";
                                            });
                                        };
                                    });
</script>
