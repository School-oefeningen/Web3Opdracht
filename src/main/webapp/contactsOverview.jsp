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
            <jsp:param name="page" value="Contacts overview"></jsp:param>
        </jsp:include>

        <main>
            <c:choose>
                <c:when test="${contacts.size() <= 0}">
                    <p id="error">No contacts to show.</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <tr>
                            <th>First name</th>
                            <th>Last Name</th>
                            <th>Date</th>
                            <th>Hour</th>
                            <th>Phone number</th>
                            <th>Email</th>
                        </tr>
                        <c:forEach var="contact" items="${contacts}">
                            <tr>
                                <td><c:out value="${contact.firstName}"/></td>
                                <td><c:out value="${contact.lastName}"/></td>
                                <td><c:out value="${contact.getDate().toString()}"/></td>
                                <td><c:out value="${contact.getHour().toString()}"/></td>
                                <td><c:out value="${contact.phoneNumber}"/></td>
                                <td><c:out value="${contact.email}"/></td>
                            </tr>
                        </c:forEach>
                        <caption>Contacts Overview</caption>
                    </table>
                </c:otherwise>
            </c:choose>

            <c:if test="${user.userid ne 'admin'}">
                <form method="POST" action="Controller?command=AddContactForm">
                    <p><input type="submit" id="addContact" value="Add contact"></p>
                </form>
            </c:if>
        </main>
    </div>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>