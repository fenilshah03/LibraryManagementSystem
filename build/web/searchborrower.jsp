<%-- 
    Document   : searchborrower
    Created on : Nov 1, 2014, 1:04:23 AM
    Author     : Fenil Admin
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Borrower</title>
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
                            <li><a href="checkin.jsp">Loan</a></li>
                            <li class="active"><a href="fine.jsp">Fine</a></li>
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

                                <h2>Search Borrower </h2>
                                <label>Search the borrower by entering his/her Card no., first name and/or last name. Then select one of them to see fine details.</label>
                                <div class="style1" style="font-size: 12; color: red">  ${Status}${Update}</div>
                                <div class="clr"></div>
                                <c:remove var="Status" />
                                <c:remove var="Update" />

                                <form action="SearchBorrowerServlet" method="post">
                                    <ol>
                                        <li>
                                            <table width="80%">
                                                <tr>
                                                    <th scope="row" width="25%">Card No</th>
                                                    <td><input type="text"   name="card" autofocus="true"/></td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">First Name</th>
                                                    <td><input type="text"   name="fname"/></td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">Last Name</th>
                                                    <td><input type="text"  name="lname"/></td>
                                                </tr>
                                            </table>

                                            <p>
                                                <input type="text" name="check" value="1" hidden="true"/>
                                                <input type="submit" name="search"  value="Search" />
                                                <input type="reset" name="reset" value="Reset" />
                                            </p>
                                            </form>

                                            </div>
                                        </li>
                                        <li>
                                            <h3> ${msg} </h3>
                                            <c:remove var="msg" />
                                        </li>
                                        <form action="GetFinesServlet" method="post">

                                            <c:if test="${list.size() > 0}">
                                                <table width="100%"  border="1">
                                                    <tr>

                                                        <th scope="col">Select</th>
                                                        <th scope="col">Card No</th>
                                                        <th scope="col">First Name</th>
                                                        <th scope="col">Last Name</th>

                                                    </tr>
                                                    <c:forEach items="${list}" var = "b">
                                                        <tr>
                                                            <td><input type="radio" name="cardno" value="${b.card}" /></td>
                                                            <td>${b.card}</td>
                                                            <td>${b.fname}</td>
                                                            <td>${b.lname}</td>

                                                        </tr>
                                                    </c:forEach>

                                                </table>
                                                <p>
                                                    <input type="submit" value="Select" />
                                                    <input type="reset" value="Reset" />
                                                </p>

                                            </c:if>
                                            <c:remove var="list" />
                                            </li>

                                            <p></p>
                                            <div class="clr"></div>


                                        </form>
                                        </div>
                                        </div>

                                        <div class="sidebar">
                                            <div class="gadget">
                                                <h2 class="star"><span>Menu</span></h2>
                                                <div class="clr"></div>
                                                <ul class="sb_menu">
                                                    <li><a href="fine.jsp">Update</a></li>
                                                    <li class="active"><a href="searchborrower.jsp">Pay Total</a></li>
                                                    <li><a href="searchborrower2.jsp">Pay Partial</a> </li>

                                                </ul>
                                            </div>
                                            <div class="gadget"></div>
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
