<%-- 
    Document   : paydetail1
    Created on : Nov 2, 2014, 12:15:14 AM
    Author     : Fenil Admin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pay partial fine</title>
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

                                <h2>Fine details</h2>
                                <label>Details about due fines and previously paid fines is shown below.</label>
                                <div class="style1" style="font-size: 12; color: red"></div>
                                <div class="clr"></div>
                                <div class="err"></div>

                                <form action="PayFineCheckServlet1" method="post">
                                    <h3>Current due fine details:</h3>
                                    <c:if test="${empty dueFineList}" >
                                        <lable>No fine is due.</lable>
                                        </c:if>

                                    <c:if test="${dueFineList.size() > 0}" >
                                        <table width="100%"  border="1">
                                            <tr>

                                                <th scope="col">Select</th>
                                                <th scope="col">Title</th>
                                                <th scope="col">Branch ID</th>
                                                <th scope="col">Checked Out</th>
                                                <th scope="col">Checked In</th>
                                                <th scope="col">Due date</th>
                                                <th scope="col">Fine(in $)</th>

                                            </tr>
                                            <c:forEach items="${dueFineList}" var = "b">
                                                <tr>
                                                    <td><input type="radio" name="loanid" value="${b.loanid}" /> </td>
                                                    <td>${b.tile}</td>
                                                    <td>${b.branch_id}</td>
                                                    <td>${b.date_out}</td>
                                                    <td>${b.date_in}</td>
                                                    <td>${b.date_due}</td>
                                                    <td>${b.fine}</td>

                                                </tr>
                                            </c:forEach>

                                        </table>

                                        <p>
                                            <input type="submit" value="Pay" />
                                            <input type="reset" value="Reset" />
                                        </p>

                                    </c:if>
                                </form>
                                <c:remove var="dueFineList" />
                                <form>
                                    <h3>Previously paid fine details:</h3>
                                    <c:if test="${finelist.size() == 0}" >
                                        <label>No previous history found.</label>
                                    </c:if>

                                    <c:if test="${finelist.size() > 0}">
                                        <table width="100%"  border="1">
                                            <tr>

                                                <th scope="col">Title</th>
                                                <th scope="col">Branch ID</th>
                                                <th scope="col">Checked Out</th>
                                                <th scope="col">Checked In</th>
                                                <th scope="col">Due date</th>
                                                <th scope="col">Fine(in $)</th>

                                            </tr>
                                            <c:forEach items="${finelist}" var = "b">
                                                <tr>
                                                    <td>${b.tile}</td>
                                                    <td>${b.branch_id}</td>
                                                    <td>${b.date_out}</td>
                                                    <td>${b.date_in}</td>
                                                    <td>${b.date_due}</td>
                                                    <td>${b.fine}</td>

                                                </tr>
                                            </c:forEach>

                                        </table>
                                        <p>&nbsp;</p>

                                    </c:if>
                                    <c:remove var="finelist" />
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
                                    <li><a href="searchborrower.jsp">Pay Total</a></li>
                                    <li class="active"><a href="searchborrower2.jsp">Pay Partial</a> </li>

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
