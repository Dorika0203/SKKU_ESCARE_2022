<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8" isELIgnored="false"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <link
      href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
      rel="stylesheet"
      id="bootstrap-css"
    />
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <title>Logout</title>
  </head>
  <body>
    <div
      class="container"
      style="width: auto; max-width: 680px; padding: 0 15px; margin: auto"
    >
      <div class="row">
        <div class="col-md-12">
          <div class="error-template">
            <div class="text-center">
              <img
                class="is-square"
                src="image/logout.png"
                width="400"
                alt="logout_img"
              />
              <h1>Session Expired!! Please Sign In Again.</h1>
              <a href="home" title="Return to home page" target="_self"
                >| Home |</a
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
