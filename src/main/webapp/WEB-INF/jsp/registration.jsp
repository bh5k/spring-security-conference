<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Registration</title>
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
            <h1>Registration</h1>
        </div>

        <form:form modelAttribute="registration">
            <form:errors path="*" cssClass="errorblock" element="div" />
            <label for="textinput1">
                Enter Registration:
            </label>
            <form:input path="name" cssErrorClass="error" />
            <form:errors path="name" cssClass="error" />
            <br/>
            <input type="submit" class="btn btn-lg btn-primary" role="button" value="Add Registration"/>
        </form:form>

        <div class="control-group">
        </div>
    </div>
</body>
</html>



