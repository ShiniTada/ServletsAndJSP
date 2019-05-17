
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="my" uri="mine" %>
<html>
<head>

    <meta charset="utf-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
    <link rel="shortcut icon" href="../img/greenlogo.png" type="image/png">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.header.language" var="language"/>
    <fmt:message bundle="${loc}" key="local.header.language.english" var="english"/>
    <fmt:message bundle="${loc}" key="local.header.language.russian" var="russian"/>

    <fmt:message bundle="${loc}" key="local.registration_form.header" var="header_registration"/>
    <fmt:message bundle="${loc}" key="local.sign_in_form.enter_login" var="enter_login"/>
    <fmt:message bundle="${loc}" key="local.sign_in_form.enter_password" var="enter_password"/>
    <fmt:message bundle="${loc}" key="local.registration_form.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.registration_form.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.registration_form.repeated_password" var="repeated_password"/>
    <fmt:message bundle="${loc}" key="local.sign_in_form.sign_in" var="sign_in"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.not_equal" var="not_equal"/>
    <fmt:message bundle="${loc}" key="local.registration_form.registrate" var="registrate"/>



    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_login" var="empty_login"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_password" var="empty_password"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_repeated_password" var="empty_repeated_password"/>
    <fmt:message bundle="${loc}" key="local.back" var="back"/>
    <fmt:message bundle="${loc}" key="local.courier.bad_login" var="bad_login"/>

    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>


    <title>
        ${header_sign_in}
    </title>

</head>
<body>
<div class="w3-container w3-teal main-panel-header ">
    <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-padding-large w3-hide-small">
        Good-Couriers.com
    </a>
    <my:headLanguage/>
</div>


<div class="w3-container col-xs-12 w3-display-topmiddle" style="margin-top:100px;">
    <h2 class="title w3-text-black" style="text-shadow:1px 1px 0 #444">
        <span>${header_registration}</span></h2>
</div>

<div class="w3-container w3-round w3-border w3-card-4 w3-border-black w3-teal"  style="margin-left:36%; margin-right:36%; margin-top:140px;">
    <form name="customer-registration-form" onsubmit="return validateForm()" method="post" action="Controller">
        <input type="hidden" name="command" value="customer-registration"/>
        <div class="w3-cell-row">
            <div class="w3-container w3-cell">
                <br>
                <br>
                <div>${login}</div>
                <input class="w3-input w3-border" id ="login" type="text" name="login"> <br>
                <div>${password}</div>
                <input class="w3-input w3-border" id="password" type="password"  name="password"> <br>
                <div>${repeated_password}</div>
                <input class="w3-input w3-border" id="repeatedPassword" type="password"  name="repeatedPassword">
                <br><br>
            </div>
        </div>
        <div class="w3-cell-row ">
            <div class="w3-container  w3-cell" style="width:10%;"> </div>
            <div class="w3-container  w3-cell" style="width:40%;">
                <button class="w3-button w3-round btn-block w3-green"  type="submit">${registrate}</button>
                <br>
            </div>
            <div class="w3-container  w3-cell" style="width:40%;">
                <a href="<c:url value="/"/>" class="w3-button w3-round btn-block w3-flat-alizarin" style="width:90px; height:40px">
                    ${back}
                </a>
                <br>
            </div>
            <div class="w3-container  w3-cell" style="width:10%;"> </div>
        </div>
    </form>
    <c:choose>
        <c:when test="${requestScope.message eq 'loginBad'}">
        <h5 class="w3-text-flat-alizarin  w3-center">${bad_login}</h5>
        </c:when>
        <c:when test="${requestScope.message eq 'notEqualsPasswords'}">
            <h5 class="w3-text-flat-alizarin w3-center">${not_equal}</h5>
        </c:when>
        <c:when test="${requestScope.message eq 'emptyLogin'}">
            <h5 class="w3-text-flat-alizarin w3-center">${empty_login}</h5>
        </c:when>
        <c:when test="${requestScope.message eq 'emptyPassword'}">
            <h5 class="w3-text-flat-alizarin w3-center">${empty_password}</h5>
        </c:when>
    </c:choose>
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
