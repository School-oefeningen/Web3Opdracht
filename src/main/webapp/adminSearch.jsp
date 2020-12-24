<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Admin search</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/app.js" defer></script>
</head>
<body>
<div id="container">
    <div id="content">
        <jsp:include page="header.jsp">
            <jsp:param name="page" value="Admin search"/>
        </jsp:include>

        <main class="main">
            <c:choose>
                <c:when test="${empty testResultsContactsMap.size()}">
                    <p id="error">No positive test results to show.</p>
                </c:when>
                <c:otherwise>
                    <c:if test="${not empty error}">
                        <div id="alert-danger">
                            <ul>
                                <li><c:out value="${error}"/></li>
                            </ul>
                        </div>
                    </c:if>
                    <form id="filterForm" action="Controller?command=AdminSearch" method="POST">
                        <p><label for="userId">User id </label><input type="text" id="userId" name="userId" required></p>
                        <p><input type="submit" id="filter" value="Filter"></p>
                        <a href="Controller?command=AdminSearch">Clear filter</a>
                    </form>
                    <br>

                    <c:forEach var="el" items="${testResultsContactsMap}">
                        <p>The user <c:out value="${el.key.userId}"/> has been tested positive on: <fmt:formatDate pattern="dd/MM/yyyy" value="${el.key.date}"/></p>

                        <c:choose>
                            <c:when test="${empty el.value}">
                                <p>No contacts to show.</p>
                                <br>
                            </c:when>
                            <c:otherwise>
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
                                            <td class="userId"><c:out value="${contact.userId}"/></td>
                                            <td><c:out value="${contact.firstName}"/></td>
                                            <td><c:out value="${contact.lastName}"/></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${contact.timestamp}"/></td>
                                            <td><fmt:formatDate pattern="HH:mm" value="${contact.timestamp}"/></td>
                                            <td><c:out value="${contact.phoneNumber}"/></td>
                                            <td><c:out value="${contact.email}"/></td>
                                        </tr>
                                    </c:forEach>
                                    <caption>Contacts of a positive user</caption>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </main>
    </div>
    <footer> &copy; Lorenzo Catalano, r0790963</footer>
</div>
</body>
</html>