<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Create Account</title>
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
        <h1>Create Account</h1>
    </div>

    <form:form modelAttribute="account" method="post">
        <form:errors path="*" cssClass="errorblock" element="div" />
        <div><label> Username : <input type="text" name="username"/> </label></div>
        <div><label> First Name : <input type="text" name="firstName"/> </label></div>
        <div><label> Last Name : <input type="text" name="lastName"/> </label></div>
        <div><label> Email : <input type="text" name="email"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><label> Confirm Password: <input type="password" name="matchingPassword"/> </label></div>
        <input type="submit" class="btn btn-lg btn-primary" role="button" value="Submit"/>
    </form:form>


    <div class="control-group">
    </div>
</div>
</body>
</html>