<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="menu" fragment="true" %>

<html>
<head>
    <title>${title} : Cinema Ticket</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/default.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/menu.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/footer.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/table.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/panel-content.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/buttons.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/forms.css" />">
</head>
<body class="body-flex">
    <jsp:invoke fragment="menu"/>