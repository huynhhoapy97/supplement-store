<%@ page language="java" pageEncoding="utf-8"%>

<div class="login">
	<div>
    		<form:form method="POST" action="admin/login" modelAttribute="adminAccount">
    			<div class="account">
    				<form:input type="text" placeholder="Tên tài khoản" autocomplete="off" path="username" />
    			</div>
    			<div class="manipulation">
    				<div class="forgetpassword">
    					<a href="#">Quên mật khẩu</a>
    				</div>
    				<div class="continue">
    					<button>Tiếp theo</button>
    				</div>
    			</div>
    		</form:form>
    	</div>
</div>