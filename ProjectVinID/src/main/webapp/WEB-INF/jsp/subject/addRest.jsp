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
                                <td style="width: 20%"></td>
                                <td><input type="hidden" ng-model="subjectForm.id"  /></td>
                            </tr>
                            <tr>
                                <td style="width: 20%">Mã Môn Học</td>
                                <td>
                                    <input type="text" ng-model="subjectForm.code" ng-required="true"  />
                                </td>
                            </tr>
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
                                            year: ""
                                        };

                                        $scope.submitEmployee = function () {
                                            $http({
                                                method: "POST",
                                                url: "/pageAdmin/subject/addRest",
                                                data: angular.toJson($scope.subjectForm)
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
                                                $scope.message = "Thêm Mới Thất Bại";
                                            });
                                        };
                                    });
</script>
