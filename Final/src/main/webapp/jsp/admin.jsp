
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="mine" %>

<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" href="../img/greenlogo.png" type="image/png">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-teal.css">
<%--    <link rel="stylesheet" href="styles/main.css">--%>
<%--    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">--%>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.header.language" var="language"/>
    <fmt:message bundle="${loc}" key="local.header.sign_out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.header.settings" var="settings"/>
    <fmt:message bundle="${loc}" key="local.header.language.english" var="english"/>
    <fmt:message bundle="${loc}" key="local.header.language.russian" var="russian"/>

    <fmt:message bundle="${loc}" key="local.admin.courier_applications" var="courier_applications"/>
    <fmt:message bundle="${loc}" key="local.admin.couriers" var="couriers"/>
    <fmt:message bundle="${loc}" key="local.admin.orders" var="orders"/>
    <fmt:message bundle="${loc}" key="local.admin.goods" var="goods"/>
    <fmt:message bundle="${loc}" key="local.admin.transport" var="transport"/>

    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>
<%--    <style>--%>
<%--        <%@include file='styles/main.css' %>--%>
<%--    </style>--%>
    <title>
        Admin
    </title>
</head>
<body>

<div class="w3-container w3-teal main-panel-header ">
    <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-left"><img src="../img/logo.jpg" style="height:30px;"></a>
    <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-padding-large w3-hide-small">
        Good-Couriers.com
    </a>

    <my:headName role="${sessionScope.user.role}" login="${sessionScope.user.login}" settings="${settings}" sign_out="${sign_out}"/>
    <my:headLanguage/>
</div>



<!-- Sidebar -->
<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:15%">
    <h5 class="w3-bar-item">Menu</h5>

    <!-- Command 1-->
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="get-new-couriers-records"/>
        <button class="w3-button w3-bar-item w3-padding-large btn-lg btn-block " type="submit">${courier_applications}</button>
    </form>

    <!-- Command 2-->
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="get-all-couriers"/>
        <button class="w3-bar-item w3-button w3-padding-large btn-lg btn-block">${couriers}</button>
    </form>

    <!-- Command 3-->
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="get-all-orders"/>
        <button class="w3-bar-item w3-button btn-lg btn-block w3-padding-large">${orders}</button>
    </form>

    <!-- Command 4-->
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="get-all-goods"/>
        <button class="w3-bar-item w3-button btn-lg btn-block w3-padding-large">${goods}</button>
    </form>

    <!-- Command 5-->
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="get-all-transport"/>
        <button class="w3-bar-item w3-button btn-lg btn-block w3-padding-large">${transport}</button>
    </form>
</div>


<%-- CENTER --%>
<div style="margin-left:200px">
    <div class="w3-container w3-center w3-display-topmiddle" style="margin-top:100px;">

        <h2 class="title w3-text-teal w3-padding-32" style="text-shadow:1px 1px 0 #444">SMTH</h2>
    </div>
</div>


<footer class="main-footer">
    ${main_footer}
</footer>
</body>
<style>
    .main-footer {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        background-color: teal;
        color: white;
        text-align: center;
    }
    body {
        background: url(http://fondopantalla.com.es/file/935/2560x1600/crop/carretera-hacia-un-nuevo-planeta.jpg);
        -moz-background-size: 100%;
        -webkit-background-size: 100%;
        -o-background-size: 100%;
        background-size: 100%;
    }
</style>
</html>
