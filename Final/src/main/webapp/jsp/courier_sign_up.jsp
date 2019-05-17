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
<%--    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">--%>


    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.header.language" var="language"/>
    <fmt:message bundle="${loc}" key="local.header.language.english" var="english"/>
    <fmt:message bundle="${loc}" key="local.header.language.russian" var="russian"/>

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
    <fmt:message bundle="${loc}" key="local.registration_form.registrate" var="registrate"/>


    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_login" var="empty_login"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_password" var="empty_password"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_repeated_password" var="empty_repeated_password"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.not_equal" var="not_equal"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_transport" var="empty_transport"/>
    <fmt:message bundle="${loc}" key="local.registration_form.warning.empty_goods" var="empty_goods"/>

    <fmt:message bundle="${loc}" key="local.back" var="back"/>

    <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>
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
            background: url(../img/ground.png);
            -moz-background-size: 100%;
            -webkit-background-size: 100%;
            -o-background-size: 100%;
            background-size: 100%;
        }
    </style>

    <script>
        function validateForm() {
            var x1 = document.forms["courier-registration-form"]["login"].value;
            if (x1 == "") {
                alert("${empty_login}");
                return false;
            }
            var x2 = document.forms["courier-registration-form"]["password"].value;
            if (x2 == "") {
                alert("${empty_password}");
                return false;
            }
            var x3 = document.forms["courier-registration-form"]["repeated_password"].value;
            if (x2 == "") {
                alert("${empty_repeated_password}");
                return false;
            }
            if (x2 != x3) {
                alert("${not_equal}");
                return false;
            }
            var t1 = document.forms["courier-registration-form"]["cycle"].checked;
            var t2 = document.forms["courier-registration-form"]["motorcycle"].checked;
            var t3 = document.forms["courier-registration-form"]["cycle"].checked;
            var t4 = document.forms["courier-registration-form"]["truck"].checked;
            if (t1 == false && t2 == false && t3 == false && t4 == false) {
                alert ("${empty_transport}");
                return false;
            }
            var g1 = document.forms["courier-registration-form"]["food"].checked;
            var g2 = document.forms["courier-registration-form"]["tech"].checked;
            var g3 = document.forms["courier-registration-form"]["furniture"].checked;
            var g4 = document.forms["courier-registration-form"]["easy-to-beat"].checked;
            if (g1 == false && g2 == false && g3 == false && g4 == false) {
                alert ("${empty_goods}");
                return false;
            }
        }
    </script>

    <title>
        ${"header_registration"}
    </title>
</head>


<body>

<div class="w3-container w3-teal main-panel-header ">
    <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-left"><img src="../img/logo.jpg" style="height:30px;"></a>
    <a href="<c:url value="/"/>" class="w3-bar-item w3-button w3-padding-large w3-hide-small">
        Good-Couriers.com
    </a>

    <my:headLanguage language="${language}"/>
</div>

<div class="w3-container col-xs-12 w3-display-topmiddle" style="margin-top:100px;">
    <h2 class="title w3-text-black" style="text-shadow:1px 1px 0 #444">
        <span>${header_registration}</span></h2>
</div>

<div class="w3-container w3-round w3-border w3-border-black w3-teal" style="margin-left:32%; margin-right:32%; margin-top:140px;">
    <form name="courier-registration-form" onsubmit="return validateForm()" method="post" action="Controller">
        <input type="hidden" name="command" value="courier-registration"/>
        <div class="w3-cell-row">
            <div class="w3-container w3-cell" style="width:45%">
                <br>
                <div>${login}</div>
                <input class="w3-input w3-border" id ="login" type="text" name="login"> <br>
                <div>${password}</div>
                <input class="w3-input w3-border" id="password" type="password"  name="password"> <br>
                <div>${repeated_password}</div>
                <input class="w3-input w3-border" id="repeated_password" type="password"  name="repeated_password">
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
</div>
<footer class="main-footer">
    ${main_footer}
</footer>
</body>
