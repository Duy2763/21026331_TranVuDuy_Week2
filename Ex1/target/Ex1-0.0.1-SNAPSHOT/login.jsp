<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<style type="text/css">
	.red{
		color: red;
	}
</style>
</head>
<body>
	<%
		String error = request.getAttribute("error") + "";
		error = error.equals("null") ? "" : error;
	%>
	<!-- Login 13 - Bootstrap Brain Component -->
	<section class="bg-light py-3 py-md-5">
	  <div class="container">
	    <div class="row justify-content-center">
	      <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5 col-xxl-4">
	        <div class="card border border-light-subtle rounded-3 shadow-sm">
	          <div class="card-body p-3 p-md-4 p-xl-5">
	            <div class="text-center mb-3">
	            </div>
	            <h2 class="fw-normal text-center text-secondary mb-4">Sign in to your account with </h2>
	            <i class="red"><%= error %></i>
            	<i>UserName: <b>admin</b></i>
            	<span> | </span>
            	<i>Password: <b>123</b></i>
	            <form action="login" method="post">
	              <div class="row gy-2 overflow-hidden">
	                <div class="col-12">
	                  <div class="form-floating mb-3">
	                    <input type="text" class="form-control" name="username" id="username">
	                    <label for="username" class="form-label">UserName</label>
	                  </div>
	                </div>
	                <div class="col-12">
	                  <div class="form-floating mb-3">
	                    <input type="password" class="form-control" name="password" id="password" value="" placeholder="Password">
	                    <label for="password" class="form-label">Password</label>
	                  </div>
	                </div>
	                <div class="col-12">
	                </div>
	                <div class="col-12">
	                  <div class="d-grid my-3">
	                    <button class="btn btn-primary btn-lg" type="submit">Log in</button>
	                  </div>
	                </div>
	              </div>
	            </form>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
</body>
</html>