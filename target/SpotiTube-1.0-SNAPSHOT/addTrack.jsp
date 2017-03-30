<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yarince
  Date: 28/03/2017
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="Imports/header.jsp"/>
<body>
<%--Navigation import--%>
<jsp:include page="Imports/navbar.jsp"/>
<c:choose>
    <c:when test="${sessionScope.USER != null && sessionScope.USER.username == PLAYLIST.owner}">

    </c:when>
    <c:otherwise>
        <div class="alert alert-warning text-center">
            Please login!
        </div>
    </c:otherwise>
</c:choose>


<%--Footer--%>
<jsp:include page="Imports/footer.jsp"/>
</body>
</html>
