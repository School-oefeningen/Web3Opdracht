<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <h1>Contact Tracing Service</h1>
    <nav>
        <ul>
            <li ${param.page eq 'Home'?'id="actual"':""}><a href="Controller?command=Home">Home</a></li>
            <li ${param.page eq 'People overview'?'id="actual"':""}><a href="Controller?command=Overview">Overview</a>
            </li>
            <li ${param.page eq 'Register'?'id="actual"':""}><a href="Controller?command=Register">Register</a></li>

            <c:if test="${not empty user}">
                <li ${param.page eq 'Dashboard'?'id="actual"':""}><a href="Controller?command=Dashboard">Dashboard</a>
                </li>
            </c:if>
        </ul>
    </nav>
    <h2><c:out value="${param.page}"/> </h2>
</header>