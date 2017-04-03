<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yarince
  Date: 28/03/2017
  Time: 13:53
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="Imports/header.jsp"/>
<body>
<%--Navigation import--%>
<jsp:include page="Imports/navbar.jsp"/>
<c:choose>
    <%--@elvariable id="PLAYLIST" type="nl.han.oose.yarince.domain.Playlist"--%>
    <c:when test="${sessionScope.USER != null && sessionScope.USER.username == PLAYLIST.owner}">
        <div id="fullpage_container">
            <div class="container" id="page_container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h3> Playlist name: <c:out value="${PLAYLIST.name}"/></h3>
                        <br>
                        Owner: <c:out value="${PLAYLIST.owner}"/>
                    </div>
                </div>
                <div class="row">
                    <form action="" name="searchTrack" method="post">
                        <input type="hidden" value="${PLAYLIST.playlistId}" name="playlistId">
                        Search tracks
                        <label>
                            Track Name: <input type="text" name="title" required="">
                        </label>
                        <input type="submit">
                    </form>
                </div>
                <div class="row">
                    <table class="table table-striped table-hover">
                            <%--@elvariable id="TRACKS" type="java.util.List"--%>
                            <%--@elvariable id="track" type="nl.han.oose.yarince.domain.Track"--%>
                        <c:forEach items="${TRACKS}" var="track">
                            <tr> <%--class="thumbnail playlistId"--%>
                                <td>
                                    Title: <c:out value="${track.title}"/> <br>
                                    Performer: <c:out value="${track.performer}"/> <br>
                                    Duration: <c:out value="${track.duration}"/>
                                </td>
                                <td>
                                    Track type:<c:out value="${track.trackType}"/> <br>
                                </td>
                                <td>
                                    <a class="btn btn-success btm-sm"
                                       href="<%=request.getContextPath()%>/trackDetails?id=<c:out value="${track.trackId}"/>">
                                        Details</a>
                                </td>
                                <td>
                                    <form method="post">
                                        <input type="hidden" name="playlistId"
                                               value="<c:out value="${PLAYLIST.playlistId}"/>">
                                        <button type="submit" name="addTrackId"
                                                value="<c:out value="${track.trackId}"/>"
                                                class="btn btn-success btm-sm">Add track
                                        </button>
                                        <label>Offline Available:
                                            <input type="checkbox" name="offlineAvailable${track.trackId}" value="true">
                                        </label>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
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
