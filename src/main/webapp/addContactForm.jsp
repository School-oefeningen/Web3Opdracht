<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Add contact</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/app.js" defer></script>
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Add contact"/>
        </jsp:include>

        <main>
            <c:if test="${not empty errors}">
                <div id="alert-danger">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li><c:out value="${error}"/></li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <form method="POST"  action="Controller?command=AddContact" novalidate="novalidate">
                <!-- novalidate in order to be able to run tests correctly -->
                <p><label for="firstName">First name</label><input type="text" id="firstName" name="firstName" value="<c:out value="${firstNamePrevious}"/>" required></p>
                <p><label for="lastName">Last name</label><input type="text" id="lastName" name="lastName" value="<c:out value="${lastNamePrevious}"/>" required></p>
                <p><label for="date">Date</label><input type="date" id="date" name="date" value="<c:out value="${datePrevious}"/>" required></p>
                <p><label for="hour">Hour</label><input type="time" id="hour" name="hour" value="<c:out value="${hourPrevious}"/>" required></p>
                <p><label for="phoneNumber">Phone number</label><input type="tel" id="phoneNumber" name="phoneNumber" value="<c:out value="${phoneNumberPrevious}"/>" required></p>
                <p><label for="email">Email</label><input type="email" id="email" name="email" value="<c:out value="${emailPrevious}"/>" required></p>
                <p><input type="submit" id="addContact" value="Add contact"></p>
            </form>
        </main>
    </div>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>