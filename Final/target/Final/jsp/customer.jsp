
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="mine" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="shortcut icon" href="../img/greenlogo.png" type="image/png">
<%--    <link rel="stylesheet" href="../styles/main.css">--%>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.header.language" var="language"/>
    <fmt:message bundle="${loc}" key="local.header.language.english" var="english"/>
    <fmt:message bundle="${loc}" key="local.header.language.russian" var="russian"/>
    <fmt:message bundle="${loc}" key="local.header.sign_out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.header.settings" var="settings"/>
    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>
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
        background:  url(http://fondopantalla.com.es/file/935/2560x1600/crop/carretera-hacia-un-nuevo-planeta.jpg);
        -moz-background-size: 100%;
        -webkit-background-size: 100%;
        -o-background-size: 100%;
        background-size: 100%;
    }
</style>
</html>
