<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Contacts overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/app.js" defer></script>
    <script src="scripts/formValidation.js" defer></script>
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Contacts overview"/>
        </jsp:include>

        <main>
            <c:if test="${not empty success}">
                <div id="alert-success">
                    <ul>
                        <li><c:out value="${success}"/></li>
                    </ul>
                </div>
                <br>
            </c:if>

            <c:if test="${not empty testResult}">
                <p>Your last positive Covid-19 test has been registered on: <fmt:formatDate pattern="dd/MM/yyyy" value="${testResult.date}"/></p>
                <br><br>
            </c:if>

            <c:choose>
                <c:when test="${contacts.size() <= 0}">
                    <p id="error">No contacts to show.</p>
                    <br>
                </c:when>
                <c:otherwise>
                    <form id="filterForm" action="Controller?command=ContactsOverview" method="POST" novalidate>
                        <p><label for="fromDate">From </label><input type="date" id="fromDate" name="fromDate" value="<c:out value="${fromDatePrevious}"/>" required></p>
                        <p><label for="untilDate">Until </label><input type="date" id="untilDate" name="untilDate" value="<c:out value="${untilDatePrevious}"/>" required></p>
                        <p><input type="submit" id="filter" value="Filter"></p>
                        <a href="Controller?command=ContactsOverview">Clear filter</a>
                    </form>

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
                        <c:forEach var="contact" items="${contacts}">
                            <tr>
                                <td id="userId"><c:out value="${contact.userId}"/></td>
                                <td><c:out value="${contact.firstName}"/></td>
                                <td><c:out value="${contact.lastName}"/></td>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${contact.timestamp}"/></td>
                                <td><fmt:formatDate pattern="HH:mm" value="${contact.timestamp}"/></td>
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
    <footer> &copy; Lorenzo Catalano, r0790963</footer>
</div>
</body>
</html>