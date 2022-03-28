<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="color: slategrey">
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-client_id" content="544694297223-4e2536s8h66lidhdgm30bq46kg9gg79o.apps.googleusercontent.com">
</head>
<body >
<div class = "container" style="text-align-all: center">
    <div class="row">
        <div >
            <form name= "formlogin" id="formlogin" action="<%=request.getContextPath()%>/api/user/login" method="POST" accept-charset="ISO-8859-1">
                <div class="form-group">
                    <hr color="silver">
                    <h2>Login</h2>
                </div>
                <div class="form-group">
                    <label>Username or email: </label>
                    <input id="username" type="text" class="form-control" name="username" placeholder="Username" required>
                </div>
                <div class="form-group">
                    <label>Password: </label>
                    <input id="password" type="password" class="form-control" name="password" placeholder="Password" required>
                </div>
                <div>
                    <input id="name" type="hidden" class="form-control" name="name">
                </div>
                <div class="form-group">
                    <hr color="silver">
                    <button type="submit" class="btn btn-success">Login</button>
                </div>
            </form>
            <form id="formregister"action="<%=request.getContextPath()%>/register.jsp" method="POST" accept-charset="ISO-8859-1">
                <button type="submit" class="btn btn-light">Register</button>
            </form>
            <hr color="silver">
            Or
            <hr color="silver">
            <div class="g-signin2" data-onsuccess="onSignIn" id="myP"></div>

            <img id="myImg"><br>
            <p id="showName"></p>
            <div id="status"></div>
            <div>
                <button class="btn btn-light" id="signOutBtn" onclick="myFunction()" style="visibility: hidden">Sign Out</button>
                <button class="btn btn-success" id ="continueBtn" type="submit" style="visibility: hidden" onclick="submitGoogle()">Continue with Google</button>
            </div>

            <form name= "formGoogle" id="formGoogle" action="<%=request.getContextPath()%>/api/user/register" method="POST" accept-charset="ISO-8859-1">
                <input id="Gusername" type="hidden" class="form-control" name="username" placeholder="Username" required>
                <input id="Gemail" type="hidden" class="form-control" name="email" placeholder="email" required>
                <input id="Gpassword" type="hidden" class="form-control" name="password" placeholder="Password" required>
                <input id="Gname" type="hidden" class="form-control" name="name">
            </form>

            <script type="text/javascript">
                function onSignIn(googleUser) {
                    var profile = googleUser.getBasicProfile();
                    var imagurl = profile.getImageUrl();
                    var name=profile.getName();
                    var email=profile.getEmail();
                    document.getElementById("myImg").src = imagurl;
                    document.getElementById("name").innerHTML = name;
                    document.getElementById("myP").style.visibility = "hidden";
                    document.getElementById("continueBtn").style.visibility = "visible";
                    document.getElementById("status").innerHTML = 'Welcome '+ name;
                    document.getElementById("signOutBtn").style.visibility = "visible"
                    document.getElementById("Gusername").value = email;
                    document.getElementById("Gemail").value = email;
                    document.getElementById("Gname").value = name;
                    document.getElementById("Gpassword").value = "12345";
                }
            </script>
            <script>
                function myFunction() {
                    gapi.auth2.getAuthInstance().disconnect();
                    location.reload();
                }
            </script>

            <script>
                function submitGoogle() {
                    document.getElementById("formGoogle").submit();
                }
            </script>
        </div>
    </div>
</div>
</body>
</html>
