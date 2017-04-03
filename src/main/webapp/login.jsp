<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="Imports/header.jsp"/>

<body>

<!-- Menu -->
<jsp:include page="Imports/navbar.jsp"></jsp:include>
<c:choose>
    <c:when test="${sessionScope.USER != null}">
        <meta http-equiv="refresh" content="0; url='<%=request.getContextPath()%>/home'" property=""/>
    </c:when>
    <c:otherwise>
        <!-- Page Content -->
        <div class="container">
            <div class="wrapper">
                <form action="" method="post" name="Login_Form" class="form-signin">
                    <h3 class="form-signin-heading">Welcome Back! Please Sign In</h3>
                    <hr class="colorgraph">
                    <br>

                    <input type="text" class="form-control" name="Username" placeholder="Username" required=""
                           autofocus=""/>
                    <input type="password" class="form-control" name="Password" placeholder="Password" required=""/>

                    <button class="btn btn-lg btn-primary btn-block" name="Submit" value="Login" type="Submit">Login
                    </button>
                </form>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="Imports/footer.jsp"></jsp:include>
</body>

</html>
