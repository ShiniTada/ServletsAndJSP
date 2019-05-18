<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <html>
    <head>
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="local" var="loc"/>
        <fmt:message bundle="${loc}" key="local.error_page.title" var="title"/>
        <fmt:message bundle="${loc}" key="local.error_page.oops" var="oops"/>
        <fmt:message bundle="${loc}" key="local.error_page.five_title" var="five_title"/>
        <fmt:message bundle="${loc}" key="local.error_page.five_message" var="five_message"/>
        <fmt:message bundle="${loc}" key="local.error_page.button.takeMeHome" var="takeMeHome"/>
        <fmt:message bundle="${loc}" key="local.main_footer" var="main_footer"/>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="shortcut icon" href="../img/greenlogo.png" type="image/png">

        <title>${title}</title>
    </head>
    <body>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="w3-container w3-center col-xs-12 w3-display-topmiddle" style="margin-top:150px;">
                    <h1 class="title w3-text-black" style="text-shadow:1px 1px 0 #444">${oops}</h1>
                    <h2>${five_title}</h2>
                    <div class="error-details"> ${five_message}
                        <p>
                    </div>
                    <div class="error-actions">
                        <c:choose>
                            <c:when test="${sessionScope.user.role eq 'admin'}}">
                                <a class="w3-button w3-round w3-teal" href="<c:url value="/jsp/admin.jsp"/>">${takeMeHome}</a>
                            </c:when>
                            <c:when test="${sessionScope.user.role eq 'courier'}}">
                                <a class="w3-button w3-round w3-teal" href="<c:url value="/jsp/courier.jsp"/>">${takeMeHome}</a>
                            </c:when>
                            <c:when test="${sessionScope.user.role eq 'customer'}}">
                                <a class="w3-button w3-round w3-teal" href="<c:url value="/jsp/customer.jsp"/>">${takeMeHome}</a>
                            </c:when>
                            <c:otherwise>
                                <a class="w3-button w3-round w3-teal" href="<c:url value="/"/>">${takeMeHome}</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="main-footer">
        ${main_footer}
    </footer>
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
            /*background: url(img/ground.png);*/
            background: url(http://fondopantalla.com.es/file/935/2560x1600/crop/carretera-hacia-un-nuevo-planeta.jpg);
            -moz-background-size: 100%;
            -webkit-background-size: 100%;
            -o-background-size: 100%;
            background-size: 100%;
        }
    </style>
    </body>
    </html>
