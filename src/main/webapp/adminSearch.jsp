<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Postive test results</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Admin search"></jsp:param>
        </jsp:include>

        <main class="main">
            <c:choose>
                <c:when test="${empty testResultsContactsMap.size()}">
                    <p id="error">No postive test results to show.</p>
                </c:when>
                <c:otherwise>

                    <c:forEach var="el" items="${testResultsContactsMap}">
                        <p>The user <c:out value="${el.key.userId}"/> has been tested positive on: <c:out value="${el.key.getDateAsString()}"/></p>

                        <table>
                            <tr>
                                <th>User id</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Date</th>
                                <th>Hour</th>
                                <th>Phone number</th>
                                <th>Email</th>
                            </tr>

                            <c:forEach var="contact" items="${el.value}">
                                <tr>
                                    <td id="userId"><c:out value="${contact.userId}"/></td>
                                    <td><c:out value="${contact.firstName}"/></td>
                                    <td><c:out value="${contact.lastName}"/></td>
                                    <td><c:out value="${contact.getDateAsString()}"/></td>
                                    <td><c:out value="${contact.getHour().toString()}"/></td>
                                    <td><c:out value="${contact.phoneNumber}"/></td>
                                    <td><c:out value="${contact.email}"/></td>
                                </tr>
                            </c:forEach>

                            <caption>Contacts of a positive user</caption>
                        </table>
                        <br>
                    </c:forEach>

                </c:otherwise>
            </c:choose>
        </main>
    </div>
    <footer> &copy; Lorenzo Catalano, r0790963</footer>
</div>
</body>
</html>