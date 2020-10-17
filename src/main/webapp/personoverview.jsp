<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="People overview"></jsp:param>
        </jsp:include>

        <main>
            <c:choose>
                <c:when test="${people.size() <= 0}">
                    <p id="error">De database is leeg.</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <tr>
                            <th>E-mail</th>
                            <th>User id</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                        </tr>
                        <c:forEach var="person" items="${people}">
                            <tr>
                                <td>${person.email}</td>
                                <td id="${person.userid}">${person.userid}</td>
                                <td>${person.firstName}</td>
                                <td>${person.lastName}</td>
                            </tr>
                        </c:forEach>
                        <caption>Users Overview</caption>
                    </table>
                </c:otherwise>
            </c:choose>
        </main>
    </div>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>