
<%@ page contentType="text/html;charset=UTF-8"%>
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

    <fmt:message bundle="${loc}" key="local.sign_in_form.header.sign_in" var="header_sign_in"/>
    <fmt:message bundle="${loc}" key="local.sign_in_form.enter_login" var="enter_login"/>
    <fmt:message bundle="${loc}" key="local.sign_in_form.enter_password" var="enter_password"/>
    <fmt:message bundle="${loc}" key="local.sign_in_form.sign_in" var="sign_in"/>
    <fmt:message bundle="${loc}" key="local.message.login_or_pass_not_correct" var="error_login_or_passwors"/>


    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_login" var="empty_login"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_password" var="empty_password"/>
    <fmt:message bundle="${loc}" key="local.back" var="back"/>

    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>


    <title>
        ${header_sign_in}
    </title>

</head>
<body>
    <div class="w3-container w3-teal main-panel-header ">
        <a class="w3-bar-item w3-button w3-padding-large w3-hide-small">
            Good-Couriers.com
        </a>
        <my:headLanguage/>
    </div>


        <div class="w3-container col-xs-12 w3-display-topmiddle" style="margin-top:100px;">
            <h2 class="title w3-text-black" style="text-shadow:1px 1px 0 #444">
                <span>${header_sign_in}</span></h2>
        </div>

    <div class="w3-container w3-round w3-center  w3-border w3-border-black w3-teal"  style="margin-left:40%; margin-right:40%; margin-top:140px;">
    <form name="sign-form" method="post" action="Controller" >
    <input type="hidden" name="command" value="sign-in"/>
        <div class="w3-cell-row">
            <div class="w3-container w3-cell">
                        <br>
                            <input class="w3-input w3-border" id ="login" type="text"  placeholder="${enter_login}"  name="login">
                        <br>
                            <input class="w3-input w3-border" id="password" type="password"  placeholder="${enter_password}" name="password">
                        <br>
            </div>
        </div>
        <div class="w3-cell-row ">
            <div class="w3-container  w3-cell" style="width:10%;"> </div>
            <div class="w3-container  w3-cell" style="width:40%;">
                <button class="w3-button w3-round btn-block w3-green"  type="submit">${sign_in}</button>
                <br>
            </div>
<%--            <div class="w3-container  w3-cell" style="width:1%;"> </div>--%>
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
            <c:when test="${not empty requestScope.message}">
                    <h5 class="w3-text-flat-alizarin">${error_login_or_passwors}</h5>
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
