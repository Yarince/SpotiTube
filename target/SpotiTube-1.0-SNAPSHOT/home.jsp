<%@ page import="javax.jws.soap.SOAPBinding" %>
<%@ page import="nl.han.oose.yarince.domain.User" %>
<%@ page import="nl.han.oose.yarince.domain.Playlist" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--Import Header--%>
<jsp:include page="Imports/header.jsp"/>

<body>
<!-- Menu -->
<jsp:include page="Imports/navbar.jsp"/>
<c:choose>
    <c:when test="${sessionScope.USER != null}">
        <!-- Page Content -->
        <div id="fullpage_container">
            <div class="container" id="page_container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h3>Welcome <c:out value="${sessionScope.USER.username}"/></h3>
                    </div>
                </div>
                <div class="row productbox">
                    <form action="" name="newPlaylist" method="post"
                          onsubmit="setTimeout(function () { window.location.reload(); }, 10)">
                        New playlist
                        <label>
                            Playlist Name: <input type="text" name="playlistName" required="">
                        </label>
                        <input type="submit" value="Save" name="newPlaylist" href="/home">
                    </form>
                </div>
                <br>
                <table class="table table-hover table-striped">
                        <%--@elvariable id="PLAYLIST_BY_OWNER" type="java.util.List"--%>
                    <c:choose>
                        <c:when test="${not empty PLAYLIST_BY_OWNER}">
                            <c:forEach items="${PLAYLIST_BY_OWNER}" var="PLAYLIST">
                                <tr>
                                    <td class="productbox">
                                        <div class="producttitle">Name: <c:out value="${PLAYLIST.name}"/></div>
                                        <p class="text-justify">Owner: <c:out
                                                value="${PLAYLIST.owner}"/></p>
                                    <td>
                                        <p class="text-sm-center">Length:
                                            <c:out value="${PLAYLIST.totalLength}"/> min.</p>
                                        <p class="text-sm-left">
                                            <c:forEach begin="0" end="2" items="${PLAYLIST.playlistEntries}"
                                                       var="playlistEntry" varStatus="loop">
                                                ${loop.index+1}: <c:out value="${playlistEntry.track.title}"/> - <c:out
                                                    value="${playlistEntry.track.performer}"/> <br>
                                            </c:forEach>
                                        </p>
                                    </td>
                                    <td class="productprice">
                                        <div class="pull-right">
                                            <form action="<%=request.getContextPath()%>/playlist/details" method="post">
                                                <button type="submit" name="playlistId"
                                                        value="<c:out value="${PLAYLIST.playlistId}"/>"
                                                        class="btn btn-success btm-sm">Edit playlist
                                                </button>
                                            </form>
                                            <form action="" onclick="window.location.reload()" method="post">
                                                <button type="submit" name="deletePlaylist"
                                                        value="<c:out value="${PLAYLIST.playlistId}"/>"
                                                        class="btn btn-success btm-sm">Delete playlist
                                                </button>
                                            </form>
                                            <form method="post"
                                                  action="<%=request.getContextPath()%>/playlist/details/addTrack">
                                                <button type="submit" name="playlistId"
                                                        value="<c:out value="${PLAYLIST.playlistId}"/>"
                                                        class="btn btn-success btm-sm">Add tracks
                                                </button>
                                            </form>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr class="alert alert-warning text-center">
                                No playlists
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <meta http-equiv="refresh" content="0; url='index.html'"/>
        <div class="alert alert-warning text-center">
            <p><a href="index.html">Please login!</a></p>
        </div>
    </c:otherwise>
</c:choose>
<!-- /.container -->

<jsp:include page="Imports/footer.jsp"/>
</body>

</html>
