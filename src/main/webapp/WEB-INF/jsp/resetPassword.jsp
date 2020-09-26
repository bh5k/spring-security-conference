<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Reset Password</title>
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <meta name="theme-color" content="#563d7c">


    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="navbar-top.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="#">Get Started</a>
</nav>

<div class="container">
    <div>
        <h1>Reset Password</h1>
    </div>

    <c:if test="${not empty param.reset}" >
        <div class="alert alert-success" role="alert">
            Your password has been reset!
        </div>
    </c:if>

    <form:form modelAttribute="password" method="post">
        <form:errors path="*" cssClass="errorblock" element="div" />
        <div><label> Password : <input type="password" name="password"/> </label></div>
        <div><label> Confirm Password : <input type="password" name="matchingPassword"/> </label></div>
        <input type="submit" class="btn btn-lg btn-primary" role="button" value="Reset Password"/>
    </form:form>


    <div class="control-group">
    </div>
</div>
</body>
</html>