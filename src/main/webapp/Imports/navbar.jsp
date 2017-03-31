<nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="navbar_top">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/home">SpotiTube</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="<%=request.getContextPath()%>/home"><i class="fa fa-list" aria-hidden="true"></i> Playlists</a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-search" aria-hidden="true"></i> Search <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <div id="dropdown_zoeken">
                                <div class="input-group">
                                    <input type="text" name="searchTb" class="form-control" placeholder="Search term">
                                    <span class="input-group-btn">
                                <button class="btn btn-secondary" type="button">Go!</button>
                              </span>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
            <p class="navbar-text navbar-right">
                <a href="#" class="navbar-link" data-toggle="modal"
                   data-target="#login-modal">Logout</a>
            </p>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
