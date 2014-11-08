<%-- 
    Document   : loan
    Created on : Oct 27, 2014, 11:59:33 AM
    Author     : Fenil Admin
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Return book</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link href="style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/arial.js"></script>
        <script type="text/javascript" src="js/cuf_run.js"></script>



        <style type="text/css">
            <!--
            .style1 {font-size: 16px}
            -->
        </style>
    </head>
    <body>
        <div class="main">
            <div class="main_resize">
                <div class="header">
                    <div class="logo">
                        <h1><em>Library Management</em></h1>
                    </div>
                    <div class="search">
                        <div class="clr"></div>
                    </div>
                    <div class="clr"></div>
                    <div class="menu_nav">
                        <ul>

                            <li><a href="search.jsp">Search</a></li>
                            <li class="active"><a href="checkin.jsp">Loan</a></li>
                            <li><a href="fine.jsp">Fine</a></li>
                            <li><a href="borrower.jsp">Borrower</a></li>
                            <li><a href="LogOutServlet">Log Out</a></li>

                        </ul>
                        <div class="clr"></div>
                    </div>
                    <div class="hbg"></div>
                </div>
                <div class="content" style="min-height: 500px">
                    <div class="content_bg">
                        <div class="mainbar" style="background-color:#FFFFFF; color:#000000">
                            <div class="article">

                                <h2>Search Book </h2>
                                <div class="style1" style="font-size: 12; color: red">  ${Status}${Update}</div>
                                <c:remove var="Status" />
                                <c:remove var="Update" />
                                <div class="clr"></div>
                                <div class="err"></div>

                                <form action="SearchServlet" method="post" >
                                    <ol>
                                        <li>
                                            <table width="80%">
                                                <tr>
                                                    <th scope="row" width="25%">Book ID </th>
                                                    <td><input type="text" name="bookid" autofocus="true"/></td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Title</th>
                                                    <td><input type="text"   name="title"/></td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Author Name </th>
                                                    <td><input type="text"  name="aname"/></td>
                                                </tr>
                                            </table>

                                            <p>
                                                <input type="submit" name="search" value="Search" />
                                            </p>


                                        </li>
                                        <li>
                                            <h3> ${msg} </h3>
                                            <c:if test="${BookList.size() > 0}">
                                                <table width="100%"  border="1">
                                                    <tr>

                                                        <th scope="col">Book ID</th>
                                                        <th scope="col">Title</th>
                                                        <th scope="col">Author Name</th>
                                                        <th scope="col">Branch ID</th>
                                                        <th scope="col">Total Copy</th>
                                                        <th scope="col">Available</th>
                                                    </tr>
                                                    <c:forEach items="${BookList}" var = "b">
                                                        <tr>
                                                            <td>${b.bookid} </td>
                                                            <td>${b.tile}</td>
                                                            <td>${b.aname}</td>
                                                            <td>${b.branchid}</td>
                                                            <td>${b.total}</td>

                                                            <td>${b.available}</td>
                                                        </tr>
                                                    </c:forEach>

                                                </table>

                                            </c:if>
                                            <c:remove var="b" />
                                        </li>
                                        <li><a href="forgotpwd.jsp"></a>
                                            <p></p>
                                            <div class="clr"></div>
                                        </li>
                                    </ol>
                                </form>
                            </div>
                        </div>
                        <div class="clr"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="footer_resize">
                <div class="clr"></div>
            </div>
        </div>

</html>

