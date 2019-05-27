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

    <fmt:message bundle="${loc}" key="local.registration_form.header" var="header_registration"/>
    <fmt:message bundle="${loc}" key="local.registration_form.login" var="login"/>
     <fmt:message bundle="${loc}" key="local.registration_form.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.registration_form.repeated_password" var="repeated_password"/>
    <fmt:message bundle="${loc}" key="local.registration_form.transport_types" var="transport_types"/>
    <fmt:message bundle="${loc}" key="local.registration_form.cycle" var="cycle"/>
     <fmt:message bundle="${loc}" key="local.registration_form.motorcycle" var="motorcycle" />
    <fmt:message bundle="${loc}" key="local.registration_form.car" var="car" />
     <fmt:message bundle="${loc}" key="local.registration_form.truck" var="truck"/>
    <fmt:message bundle="${loc}" key="local.registration_form.goods_types" var="goods_types"/>
     <fmt:message bundle="${loc}" key="local.registration_form.food" var="food" />
    <fmt:message bundle="${loc}" key="local.registration_form.tech" var="tech"/>
     <fmt:message bundle="${loc}" key="local.registration_form.furniture" var="furniture"/>
    <fmt:message bundle="${loc}" key="local.registration_form.easy_to_beat" var="easy_to_beat"/>
    <fmt:message bundle="${loc}" key="local.registration_form.register" var="registrate"/>


    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_login" var="empty_login"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_password" var="empty_password"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_repeated_password" var="empty_repeated_password"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.not_equal" var="not_equal"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_transport" var="empty_transport"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_goods" var="empty_goods"/>
    <fmt:message bundle="${loc}" key="local.courier.bad_login" var="bad_login"/>

    <fmt:message bundle="${loc}" key="local.back" var="back"/>

    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>

    <title>
        ${header_registration}
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

<div class="w3-container w3-round w3-card-4 w3-border w3-border-black w3-teal" style="margin-left:32%; margin-right:32%; margin-top:140px;">
    <form name="courier-registration-form"  method="post" action="Controller">
        <input type="hidden" name="command" value="courier-registration"/>
        <div class="w3-cell-row">
            <div class="w3-container w3-cell" style="width:45%">
                <br>
                    <div>${login}</div>
                    <input class="w3-input w3-border" id ="login" type="text" required minlength="3" name="login"> <br>
                    <div>${password}</div>
                    <input class="w3-input w3-border" id="password" type="password" required minlength="3"  name="password"> <br>
                    <div>${repeated_password}</div>
                    <input class="w3-input w3-border" id="repeatedPassword" type="password" required minlength="3"  name="repeatedPassword">
                <br><br>
            </div>
            <div class="w3-container w3-cell" style="width:10%;"> </div>
            <div class="w3-container w3-cell" style="width:45%">
                <br>
                <div>${transport_types}</div>
                <input type="checkbox" name="cycle" value="cycle"> ${cycle}
                <br>
                <input type="checkbox" name="motorcycle" value="motorcycle"> ${motorcycle}
                <br>
                <input type="checkbox" name="car" value="car"> ${car}
                <br>
                <input type="checkbox" name="truck" value="truck"> ${truck}
                <br><br>

                <div>${goods_types}</div>
                <input type="checkbox" name="food" value="food"> ${food}
                <br>
                <input type="checkbox" name="tech" value="tech"> ${tech}
                <br>
                <input type="checkbox" name="furniture" value="furniture"> ${furniture}
                <br>
                <input type="checkbox" name="easy-to-beat" value="easy-to-beat"> ${easy_to_beat}
                <br><br>
            </div>
        </div>
        <div class="w3-cell-row ">
            <div class="w3-container w3-cell" style="width:25%;"> </div>
            <div class="w3-container w3-cell" style="width:20%;">
                <button class="w3-button w3-round btn-block w3-green"  type="submit">${registrate}</button>
                <br>
            </div>
            <div class="w3-container  w3-cell" style="width:10%;"> </div>
            <div class="w3-container  w3-cell" style="width:20%;">
                <a href="<c:url value="/"/>" class="w3-button w3-round btn-block w3-flat-alizarin" style="width:120px; height:40px">
                    ${back}
                </a>
                <br>
            </div>
            <div class="w3-container  w3-cell" style="width:25%;"> </div>
        </div>
    </form>
    <c:choose>
        <c:when test="${requestScope.message eq 'loginBad'}">
            <h5 class="w3-text-flat-alizarin w3-center">${bad_login}</h5>
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
        <c:when test="${requestScope.message eq 'emptyTransport'}">
            <h5 class="w3-text-flat-alizarin w3-center">${empty_transport}</h5>
        </c:when>
        <c:when test="${requestScope.message eq 'emptyGoods'}">
            <h5 class="w3-text-flat-alizarin w3-center">${empty_goods}</h5>
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
        background: url(http://fondopantalla.com.es/file/935/2560x1600/crop/carretera-hacia-un-nuevo-planeta.jpg);
        -moz-background-size: 100%;
        -webkit-background-size: 100%;
        -o-background-size: 100%;
        background-size: 100%;
    }
</style>

</html>