<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="login row position-absolute top-50 start-50 translate-middle" style="width: 500px">
	<div class="col-md-4 offset-md-4">
        <form:form method="POST" action="admin/login" modelAttribute="account">
            <div>
                <form:input type="text" class="form-control"
                placeholder="Tên tài khoản"
                autocomplete="off"
                path="userName" />
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