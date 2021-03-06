<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../include/css.jsp" %>
<%@ include file="../pageAdmin/include/menu.jsp" %>
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
            <div class="widget-box" style="margin: 20px">${messing}
                <div class="widget-content nopadding">
                    <form:form  id="addSubjectfrm" action="" modelAttribute="subject" method="post">
                        <table class="table table-bordered table-striped">
                            <tbody>
                                <tr>
                                    <td style="width: 20%">Mã Môn Học</td>
                                    <td><form:input type="text" path="code" id="code" /></td>
                                </tr>
                                <tr>
                                    <td>Tên Môn Học</td>
                                    <td><form:input type="text" path="name" id="name" /></td>
                                </tr>
                                <tr>
                                    <td>Số Tiết</td>
                                    <td><form:input type="text" path="number"  /></td>
                                </tr>
                                <tr>
                                    <td>Năm Học</td>
                                    <td><form:input type="text" path="year" /></td>
                                </tr>
                                <tr>
                                    <td class="" colspan="6" align="left" style="padding: 10px 7%">
                                        <input id="btnSubmit" class="btn btn-success" name="submit" value="Thêm" type="submit"/>
                                        <input id="comback" onclick="location.href = '<c:url value="/subject/view"/>'" class="btn btn-danger" name="back" value="Quay lại" type="button"
                                               data-content="Quay lại trang quản lý Subject." data-placement="top" data-toggle="popover"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form:form>
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
$(document).ready(function () {
    $("#addSubjectfrm").validate({
            rules: {
                code: "required",
                name: "required",
                password: {
                    required: true,
                    minlength: 2
                }
            },
            messages: {
                code: "Vui lòng nhập mã",
                name: "Vui lòng nhập tên",
                password: {
                    required: "Vui lòng nhập password",
                    minlength: "password ngắn vậy, chém gió ah?"
                }
            }
        });
});
</script>
