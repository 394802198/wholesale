<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
   	<meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>Total Mobile Solution</title>
    <link rel="shortcut icon" type="image/ico" href="${ctx}/public/bootstrap3/images/icon.png"/>
    <link href="${ctx}/public/bootstrap3/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="${ctx}/public/bootstrap3/css/datepicker3.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/public/bootstrap3/css/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/public/bootstrap3/css/skins/all.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/public/bootstrap3/css/ladda-themeless.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/public/bootstrap3/css/management.css?ver=2014822747" rel="stylesheet" type="text/css"  />
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<jsp:include page="nav.jsp" />