<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <p><a class="brand" href="<%=request.getContextPath()%>/login">Spotitube</a></p>
            <div class="nav-collapse">
                <ul class="nav">
                    <li class="active"><a href="<%=request.getContextPath()%>/home">Playlists</a></li>
                </ul>
                <ul class="nav pull-right">
                    <li class="active">
                        <form action="${pageContext.request.contextPath}/logout" method="post">
                            <input class="active" type="submit" value="Logout"/>
                        </form>
                    </li>
                </ul>
            </div><!-- /.nav-collapse -->
        </div>
    </div><!-- /navbar-inner -->
</div>
