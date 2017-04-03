<%@ page import="nl.han.oose.yarince.domain.Song" %>
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
                        <h3> Playlist: <c:out value="${PLAYLIST.name}"/></h3>
                        <br>
                        Owner: <c:out value="${PLAYLIST.owner}"/>
                    </div>
                </div>
                <div class="row">
                    <form action="" name="newPlaylist" method="post"
                          onsubmit="setTimeout(function () { window.location.reload(); }, 10)">
                        <label>
                            New Playlist Name: <input type="text" name="newPlaylistName" required="">
                        </label>
                        <input type="submit" value="Save" name="newPlaylistNameSave">
                    </form>

                    <form method="post" action="<%=request.getContextPath()%>/playlist/details/addTrack">
                        <button type="submit" name="playlistId"
                                value="<c:out value="${PLAYLIST.playlistId}"/>"
                                class="btn btn-info btm-sm">Add tracks
                        </button>
                    </form>
                </div>
                <br>
                <div class="row">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <c:choose>
                                <%--@elvariable id="PLAYLIST" type="nl.han.oose.yarince.domain.Playlist"--%>
                                <c:when test="${not empty PLAYLIST}">
                                    <table class="table table-striped table-hover">
                                            <%--@elvariable id="TRACKS" type="java.util.List"--%>
                                            <%--@elvariable id="playlistEntry" type="nl.han.oose.yarince.domain.PlaylistEntry"--%>
                                        <c:forEach items="${TRACKS}" var="playlistEntry">
                                            <tr>
                                                <td>
                                                    Title: <c:out value="${playlistEntry.track.title}"/> <br>
                                                    Performer: <c:out value="${playlistEntry.track.performer}"/> <br>
                                                </td>
                                                <td>
                                                    Offline available: <c:out
                                                        value="${playlistEntry.offlineAvailable}"/>
                                                </td>
                                                <td>
                                                    Track type: <c:out
                                                        value="${playlistEntry.track.trackType.toString()}"/>
                                                </td>
                                                <td>
                                                    <form method="post">
                                                        <input type="hidden" name="playlistId"
                                                               value="<c:out value="${PLAYLIST.playlistId}"/>">
                                                        <button type="submit" name="deleteTrackId"
                                                                value="<c:out value="${playlistEntry.track.trackId}"/>"
                                                                class="btn btn-success btm-sm">Delete track
                                                        </button>
                                                    </form>
                                                </td>
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
        <meta http-equiv="refresh" content="0; url='<%=request.getContextPath()%>/index.html'" property=""/>
        <div class="alert alert-warning text-center">
            <p><a href="<%=request.getContextPath()%>/index.html">Please login!</a></p>
        </div>
    </c:otherwise>
</c:choose>
<jsp:include page="Imports/footer.jsp"/>
</body>
</html>
