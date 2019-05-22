
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="my" uri="mine" %>
<html>
<head>

    <meta charset="utf-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-flat.css">
    <link rel="shortcut icon" href="../../img/greenlogo.png" type="image/png">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.header.sign_out" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.header.settings" var="settings"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.not_equal" var="not_equal"/>
    <fmt:message bundle="${loc}" key="local.settings.change_password" var="change_password"/>
    <fmt:message bundle="${loc}" key="local.settings.new_password" var="new_password"/>
    <fmt:message bundle="${loc}" key="local.settings.repeat_new_password" var="repeat_new_password"/>
    <fmt:message bundle="${loc}" key="local.settings.current_password" var="current_password"/>
    <fmt:message bundle="${loc}" key="local.back" var="back"/>


    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>


    <title>
        ${change_password}
    </title>

</head>
<body>
<div class="w3-container w3-teal main-panel-header ">
    <a class="w3-bar-item w3-button w3-padding-large w3-hide-small">
        Good-Couriers.com
    </a>
    <my:headName role="${sessionScope.user.role}" login="${sessionScope.user.login}" settings="${settings}" sign_out="${sign_out}"/>
    <my:headLanguage/>
</div>


<div class="w3-container col-xs-12 w3-display-topmiddle" style="margin-top:100px;">
    <h2 class="title w3-text-black" style="text-shadow:1px 1px 0 #444">
        <span>${change_password}</span></h2>
</div>

<div class="w3-container w3-round w3-border w3-card-4 w3-border-black w3-teal"  style="margin-left:36%; margin-right:36%; margin-top:140px;">
    <form name="customer-registration-form" method="post" action="Controller">
        <input type="hidden" name="command" value="settings"/>
        <div class="w3-cell-row">
            <div class="w3-container w3-cell">
                <br>
                <br>
                <div>${current_password}</div>
                <input class="w3-input w3-border" id="password" type="password" required minlength="3" name="currentPassword"> <br>
                <div>${new_password}</div>
                <input class="w3-input w3-border" type="password" required minlength="3" name="newPassword"> <br>
                <div>${repeat_new_password}</div>
                <input class="w3-input w3-border" type="password" required minlength="3" name="repeatedNewPassword">
                <br><br>
            </div>
        </div>
        <div class="w3-cell-row ">
            <div class="w3-container  w3-cell" style="width:30%;"> </div>
            <div class="w3-container  w3-cell" style="width:40%;">
                <button class="w3-button w3-round btn-block w3-green"  type="submit">${change_password}</button>
                <br>
            </div>
            <div class="w3-container  w3-cell" style="width:30%;"> </div>
        </div>
    </form>

        <c:if test="${requestScope.message eq 'notEqualsPasswords'}">
            <h5 class="w3-text-flat-alizarin w3-center">${not_equal}</h5>
        </c:if>

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
