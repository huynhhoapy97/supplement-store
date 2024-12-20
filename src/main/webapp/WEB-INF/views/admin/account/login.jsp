<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="login row position-absolute top-50 start-50 translate-middle" style="width: 500px">
	<div class="col-md-6 offset-md-3">
        <form:form method="POST" action="admin/account/login" modelAttribute="account">
            <div>
                <form:input type="hidden" class="form-control" path="id" />
            </div>
            <div>
                <form:input type="text" class="form-control"
                placeholder="Tên tài khoản"
                autocomplete="off"
                path="userName" />
            </div>
            <div>
                <c:if test="${not empty accountStatus}">
                    <span>${accountStatus}</span>
                </c:if>
            </div>
            <div style="margin-top: 15px">
                <div class="continue">
                    <button class="btn btn-primary">Tiếp theo</button>
                </div>
                <div class="forget-password">
                    <a href="#">Quên mật khẩu</a>
                </div>
            </div>
        </form:form>
    </div>
</div>