<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <div id="header-content">
        <h1>Contact Tracing Service</h1>
        <nav>
            <ul>
                <li ${param.page eq 'Home'?'id="actual"':""}><a href="Controller?command=Home">Home</a></li>
                <li ${param.page eq 'Users overview'?'id="actual"':""}><a href="Controller?command=UsersOverview">Users</a></li>

                <c:if test="${not empty user}">
                    <li ${param.page eq 'Contacts overview'?'id="actual"':""}><a href="Controller?command=ContactsOverview">Contacts</a></li>
                    <li ${param.page eq 'Register test result'?'id="actual"':""}><a href="Controller?command=RegisterTestResult">Register test result</a></li>
                    <li ${param.page eq 'Search'?'id="actual"':""}><a href="Controller?command=TestResultsSearch">Search</a></li>
                    <li ${param.page eq 'Dashboard'?'id="actual"':""}><a href="Controller?command=Dashboard">Dashboard</a></li>
                </c:if>
            </ul>
        </nav>
    </div>
</header>
<h2><c:out value="${param.page}"/></h2>