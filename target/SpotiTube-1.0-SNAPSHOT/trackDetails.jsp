<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="TRACK" type="nl.han.oose.yarince.domain.Track"--%>
<%--@elvariable id="VIDEO" type="nl.han.oose.yarince.domain.Video"--%>
<%--@elvariable id="SONG" type="nl.han.oose.yarince.domain.Song"--%>
<%--
  Created by IntelliJ IDEA.
  User: Yarince
  Date: 03/04/2017
  Time: 13:53
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="Imports/header.jsp"/>
<body>
<%--Navigation import--%>
<jsp:include page="Imports/navbar.jsp"/>
<c:choose>
    <c:when test="${sessionScope.USER != null}">
        <div id="fullpage_container">
            <div class="container" id="page_container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h3> Track name:
                            <c:out value="${TRACK.title}"/></h3>
                    </div>
                </div>
                <div class="row">
                    <table class="table table-striped table-hover">
                        <tr>
                            <td>
                                Title: <c:out value="${TRACK.title}"/> <br><br><x></x>
                                Performer: <c:out value="${TRACK.performer}"/>
                            </td>
                            <td>
                                Duration: <c:out value="${TRACK.duration}"/> <br><br>
                                Track type:<c:out value="${TRACK.trackType}"/>
                            </td>
                            <c:choose>
                                <c:when test="${SONG != null}">
                                    <td>
                                        Album name: <c:out value="${SONG.album}"/>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        Publication date: <c:out value="${VIDEO.publicationDate}"/> <br>
                                        Play count: <c:out value="${VIDEO.playCount}"/><br>
                                        Description: <c:out value="${VIDEO.description}"/>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <meta http-equiv="refresh" content="0; url='<%=request.getContextPath()%>/index.html'" property=""/>
        <div class="alert alert-warning text-center">
            <p><a href="index.html">Please login!</a></p>
        </div>
    </c:otherwise>
</c:choose>

<%--Footer--%>
<jsp:include page="Imports/footer.jsp"/>
</body>
</html>
