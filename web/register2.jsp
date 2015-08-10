<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">

            <form id="registerform" action="SignInServlet" method="post" class="center-block well"
                  style="max-width: 500px; margin-top: 50px" enctype="multipart/form-data">
                <h1 class="text-center">Seller Register</h1>
                <span class="text-danger">${ErrorMessage}</span>

                <div class="form-group">
                    <label>Name</label>
                    <input id="username" type="text" name="name" class="form-control" required value="james"/>
                    <span id="nameVerify" class="text-danger">${verifyParameter.name}</span>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input id="psw" type="password" name="psw" class="form-control" required value="123456789"/>
                    <span id="pswVerify" class="text-danger">${verifyParameter.pwd}</span>
                </div>
                <div class="form-group">
                    <label>Confirm Password</label>
                    <input id="pwsconfirm" type="password" name="psw" class="form-control" required value="123456789"/>
                    <span id="pswconfirmVerify" class="text-danger"></span>
                </div>
                <div class="form-group">
                    <label>Tel</label>
                    <input id="tel" type="tel" name="tel" class="form-control" required value="2239823"/>
                    <span id="telVerify" class="text-danger">${verifyParameter.tel}</span>
                </div>
                <div class="form-group">
                    <label>Id Card</label>
                    <input id="idCard" type="text" name="idCard" class="form-control" required
                           value="440402199205179137"/>
                    <span id="idCardVerify" class="text-danger"></span>
                </div>
                <div class="form-group">
                    <label>License</label>
                    <input type="file" name="license" class="form-control" required/>
                </div>
                <br/>
                <span class="text-danger">${ErrorMessage}</span>
                <button type="submit" class="btn btn-block btn-success">Register</button>
                <br/>

                <div class="text-right">
                    <a href="login.jsp" class="btn btn-link">Canel</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    var registerform = document.getElementById("registerform");
    registerform.onsubmit = function () {
        var username = document.getElementById("username").value;
        var password = document.getElementById("psw").value;
        var passwordConfirm = document.getElementById("pwsconfirm").value;
        var telphone = document.getElementById("tel").value;
        var idCard = document.getElementById("idCard").value;

        if (verifyName(username) && verifyPsw(password) && verifyPswConFirm(password, passwordConfirm) && verifyTel(telphone) && verifyIdCard(idCard)) {
            return true;
        } else {
            return false;
        }

    }

    function verifyIdCard(idCard) {
        var idCardRegex = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        var idVerifyMessage = document.getElementById("idCardVerify");
        if (idCardRegex.test(idCard)) {
            //salert(idCard + " pass...")
            return true;
        } else {
            idVerifyMessage.innerHTML = "please confirm your idCard number";
            return false;
        }

    }

    function verifyTel(telphone) {
        if (!checkTel(telphone)) {
            document.getElementById("telVerify").innerHTML = "please confirm your password";
            return false;
        } else {

            return true;
        }

    }

    function checkTel(telphone) {
        var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
        var isMob = /(^(13\d|14[57]|15[^4,\D]|17[678]|18\d)\d{8}|170[059]\d{7})$/;

        if (isPhone.test(telphone) || isMob.test(telphone)) {
            return true;
        }
        else {
            return false;
        }
    }

    function verifyPswConFirm(password, passwordConfirm) {
        if (password != passwordConfirm) {
            document.getElementById("pswconfirmVerify").innerHTML = "please confirm your password";
            return false;
        } else {

            return true;
        }

    }
    function verifyPsw(password) {
        if (password < 6) {
            document.getElementById("pswVerify").innerHTML = "passwrod must longer than 6 ";
            return false;
        } else {
            return true;
        }
    }
    function verifyName(username) {
        if (username.length < 4 || username > 8) {

            document.getElementById("nameVerify").innerHTML = "length of username must between 4 to 8 ";
            return false;
        } else {
            return true;
        }
    }
</script>
</body>

</html>
