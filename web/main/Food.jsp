<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>SMS - main course</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/DataTables/datatables.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/DataTables/datatables.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script type="text/javascript" src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


    <script type="text/javascript" src="/js/databuffer.js"></script>


    <script type="text/javascript">
        $(function(){
            $(".dropdown.active").toggleClass("open",true);
            $(".dropdown").on("click",function(){
                $(".dropdown").toggleClass("open",true);
            });
            $(".dropdown.active").on("hide.bs.dropdown",function(e) {
                $(".dropdown").toggleClass("active",false);
                return true;
            });
        });
        $(document).ready(function () {
            //init datatable
            $('.dataTable').DataTable({
                "columns": [
                    null, null, null, null, null, {"orderable": false}
                ]
            });
        });


    </script>
    <style>
        nav.navbar i {
            width: 30px;
        }

    </style>
</head>

<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-nav" data-hover="dropdown">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>main
            </button>
            <a class="navbar-brand" href="index.html">SMS</a>
        </div>
        <div class="collapse navbar-collapse" id="main-nav">
            <ul class="nav navbar-nav">
                <li class="dropdown active">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Food <span
                            class="caret"></span></a>
                    <ul id="foodList" class="dropdown-menu">
                        <li role="separator" class="divider"></li>
                        <li><a href="main/newFood.jsp"><i class="glyphicon glyphicon-plus"></i>New</a></li>
                    </ul>
                </li>
                <li><a href="PackageServlet">Package</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                       role="button"> <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="../login.html"><i class="glyphicon glyphicon-log-out"></i>&emsp;Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <ol class="breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li class="active">${currentFoodTypeName}</li>
            </ol>
            <span class="text-danger">${ErrorMessage}</span>
            <span class="text-success">${SuccessMessage}</span>
            <table id="foodTable" class="table table-hover dataTable">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Picture</th>
                    <th>Status</th>
                    <th style="width:150px">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${resultList}" var="foodTypeItem">
                    <tr>
                        <td>${foodTypeItem.foodID}</td>
                        <td>${foodTypeItem.foodName}</td>
                        <td>${foodTypeItem.price}</td>
                        <td><div class=""><img src="../File?filename=${foodTypeItem.pictureURL}" style="width:60%" class="img-rounded img-responsive"></div></td>
                        <%--<td>${foodTypeItem.pictureURL}</td>--%>
                        <td>
                            <c:forEach items="${statusEntityList}" var="statusItem">
                                <c:choose>
                                    <c:when test="${statusItem.statusID eq foodTypeItem.statusID}">
                                        ${statusItem.statusName}
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </td>
                        <td>
                            <a href="../UpdataFoodServlet?foodId=${foodTypeItem.foodID}&price=${foodTypeItem.price}&update=false">update</a>&emsp;
                            <a href="../DeleteFoodSerlvet?foodId=${foodTypeItem.foodID}"
                               onclick="return confirm('Delete this item?')">delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>

</html>
