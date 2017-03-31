<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Yarince
  Date: 22/03/2017
  Time: 15:26
--%>
<jsp:include page="Imports/header.jsp"/>
<body>
<jsp:include page="Imports/navbar.jsp"/>
<%--<jsp:useBean id="TRACKS" scope="page" type="java.util.List"/>--%>
<c:choose>
    <%--@elvariable id="PLAYLIST" type="nl.han.oose.yarince.domain.Playlist"--%>
    <c:when test="${sessionScope.USER != null && sessionScope.USER.username == PLAYLIST.owner}">
        <!-- Page Content -->
        <div id="fullpage_container">
            <div class="container" id="page_container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h3> Playlist name: <c:out value="${PLAYLIST.name}"/></h3>
                        <br>
                        Owner: <c:out value="${PLAYLIST.owner}"/>
                    </div>
                </div>
                <form method="post" action="<%=request.getContextPath()%>/playlist/details/addTrack"> TrackName
                    <button type="submit" name="playlistId"
                            value="<c:out value="${PLAYLIST.playlistId}"/>"
                            class="btn btn-success btm-sm">Add tracks
                    </button>
                </form>
                <div class="row">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <c:choose>
                                <%--@elvariable id="PLAYLIST" type="nl.han.oose.yarince.domain.Playlist"--%>
                                <c:when test="${not empty PLAYLIST}">
                                    <table class="table table-striped table-hover">
                                            <%--@elvariable id="TRACKS" type="java.util.List"--%>
                                            <%--@elvariable id="track" type="nl.han.oose.yarince.domain.Track"--%>
                                        <c:forEach items="${TRACKS}" var="track">
                                            <tr>
                                                <td>
                                                    Title: <c:out value="${track.title}"/> <br>
                                                </td>
                                                <td>
                                                    Performer: <c:out value="${track.performer}"/> <br>
                                                </td>
                                                <%--<c:choose>--%>
                                                    <%--<c:when test="${track.trackType.toString() == 'Song'}">--%>
                                                        <%--<td><%//todo check if song then print info %>--%>
                                                            <%--tracktype: <c:out value="${track.trackType.toString()}"/>--%>
                                                                <%--&lt;%&ndash;<c:out value="${track.}"/>&ndash;%&gt;--%>
                                                            <%--song info--%>
                                                        <%--</td>--%>
                                                    <%--</c:when>--%>
                                                    <%--<c:otherwise>--%>
                                                        <%--<td>--%>
                                                            <%--tracktype: <c:out value="${track.trackType.toString()}"/>--%>
                                                                <%--&lt;%&ndash;<c:out value="${track.}"&ndash;%&gt;--%>
                                                            <%--video info--%>
                                                        <%--</td>--%>
                                                    <%--</c:otherwise>--%>
                                                <%--</c:choose>--%>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <br>
                                    <div class="alert alert-warning text-center">
                                        No playlist loaded
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <meta http-equiv="refresh" content="0; url='<%=request.getContextPath()%>/index.html'"/>
        <div class="alert alert-warning text-center">
            <p><a href="<%=request.getContextPath()%>/index.html">Please login!</a></p>
        </div>
    </c:otherwise>
</c:choose>
<jsp:include page="Imports/footer.jsp"></jsp:include>
</body>
</html>
