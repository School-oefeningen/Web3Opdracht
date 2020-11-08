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
            <jsp:param name="page" value="Users overview"/>
        </jsp:include>

        <main>
            <c:if test="${not empty succes}">
                <div class="alert-succes">
                    <ul>
                        <li><c:out value="${succes}"/></li>
                    </ul>
                </div>
            </c:if>

            <c:choose>
                <c:when test="${people.size() <= 0}">
                    <p id="error">No users in the database.</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <tr>
                            <th>User id</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>E-mail</th>
                        </tr>
                        <c:forEach var="person" items="${people}">
                            <tr>
                                <td id="<c:out value="${person.userid}"/>"><c:out value="${person.userid}"/></td>
                                <td><c:out value="${person.firstName}"/></td>
                                <td><c:out value="${person.lastName}"/></td>
                                <td><c:out value="${person.email}"/></td>

                                <c:if test="${user.userid eq 'admin'}">
                                    <td>
                                        <form method="POST" action="Controller?command=DeleteAccountConfirmation&userId=<c:out value="${person.userid}"/>">
                                            <input type="image" id="deleteAccountButton" src="images/delete-button.svg" alt="Delete user submit">
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        <caption>Users Overview</caption>
                    </table>
                </c:otherwise>
            </c:choose>

            <c:if test="${user.userid ne 'admin'}">
                <form method="POST" action="Controller?command=Register">
                    <p><input type="submit" id="register" value="Register new user"></p>
                </form>
            </c:if>
        </main>
    </div>
    <footer> &copy; Lorenzo Catalano, r0790963</footer>
</div>
</body>
</html>