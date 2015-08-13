<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <script src="js/jquery-1.11.3.min.js"></script>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript">
        $(function () {
            function verifySign() {
                // 定义私有属性
                //存储信息的map
                var errorMessage = new Object();
                //标志位
                var flag = true;
                // 定义私有方法
                //验证
                function verify() {
                    console.log("verify...");
                    clear();
                    console.log($("input[name=name]").val());
                    if (verifyName() & verifyIdCard() & verifyTel() & verifyPswConFirm() & verifyPsw() & verifyName()) {// && verifyPsw() && verifyPswConFirm() && verifyTel() && verifyIdCard()) {
                        return true;
                    } else {
                        showMessage();
                        return false;
                    }
                }

                //清除上次的验证结果
                function clear() {
                    console.log("clear...");
                    jQuery.each(errorMessage, function (n, value) {
                        $("#" + n).text("");
                        console.log(n + ' ' + value);
                        //alert(n + ' ' + value);
                    });
                    errorMessage = new Object();

                }

                // 特权函数声明，用于该对象其他公有方法能通过该特权方法访问到私有成员
                this.startVerify = function () {
                    console.log("startVerify...");
                    flag = true;
                    verify();
                    return false;
                };

                //展示错误信息
                function showMessage() {
                    console.log("showMessage....");
                    jQuery.each(errorMessage, function (n, value) {
                        $("#" + n).text(value);
                        console.log(n + ' ' + value);
                        //alert(n + ' ' + value);
                    });

                }

                function verifyIdCard() {
                    var idCardRegex = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                    var idCard = $("input[name=idCard]").val();
                    if (idCardRegex.test(idCard)) {
                        return true;
                    } else {
                        errorMessage["idCardVerify"] = "please confirm your idCard number";
                        return false;
                    }

                }

                function verifyTel() {
                    var telphone = $("input[name=tel]").val();
                    if (!checkTel(telphone)) {
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
                        errorMessage["telVerify"] = "please confirm your tel number";
                        return false;
                    }
                }

                function verifyPswConFirm() {
                    var psw = $("input[name=psw]").val();
                    var pswConfirm = $("input[name=pswconfirm]").val();
                    if (psw != pswConfirm) {
                        errorMessage["pswconfirmVerify"] = "please confirm your password";
                        return false;
                    } else {
                        return true;
                    }

                }

                function verifyPsw() {
                    var psw = $("input[name=psw]").val();
                    if (psw.length < 6) {
                        errorMessage["pswVerify"] = "passwrod must longer than 6";
                        return false;
                    } else {
                        return true;
                    }
                }

                function verifyName() {
                    var username = $("input[name=name]").val();
                    if (username.length < 4 || username.length > 8) {
                        errorMessage["nameVerify"] = "length of username must between 4 to 8 ";
                        return false;
                    } else {
                        return true;
                    }
                }

            }
            var verify = new verifySign();
            jQuery("#registerform").submit(function () {
                console.log("submit");
                verify.startVerify();

                //for test !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                return false;
            });
            console.log("loding...");
        });


    </script>
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
                    <input id="username" type="text" name="name" class="form-control" required value="12"/>
                    <span id="nameVerify" class="text-danger">${verifyParameter.name}</span>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input id="psw" type="password" name="psw" class="form-control" required value="123456789"/>
                    <span id="pswVerify" class="text-danger">${verifyParameter.pwd}</span>
                </div>
                <div class="form-group">
                    <label>Confirm Password</label>
                    <input id="pwsconfirm" type="password" name="pswconfirm" class="form-control" required
                           value="123456789"/>
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
                <%--<div class="form-group">--%>
                <%--<label>License</label>--%>
                <%--<input type="file" name="license" class="form-control" required/>--%>
                <%--</div>--%>
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


</body>
</html>