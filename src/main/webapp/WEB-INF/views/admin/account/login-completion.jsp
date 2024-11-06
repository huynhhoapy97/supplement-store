<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="login row position-absolute top-50 start-50 translate-middle" style="width: 500px">
    <div class="col-md-6 offset-md-3" style="margin-bottom: 15px; text-align: center">
        <c:if test="${not empty account.userName}">
            <span>Xin chào, ${account.userName}</span>
        </c:if>
    </div>
	<div class="col-md-6 offset-md-3">
        <form:form method="POST" action="admin/account/login-completion" modelAttribute="account">
            <div>
                <form:input type="hidden" class="form-control" path="id" />
                <form:input type="hidden" class="form-control" path="userName" />
            </div>
            <div>
                <form:input type="password" class="form-control"
                placeholder="Mật khẩu"
                autocomplete="off"
                path="password" />
            </div>
            <div>
                <c:if test="${not empty accountStatus}">
                    <span>${accountStatus}</span>
                </c:if>
            </div>
            <div style="margin-top: 15px">
                <div class="sign-in">
                    <button class="btn btn-primary">Đăng nhập</button>
                </div>
            </div>
        </form:form>
    </div>
</div>