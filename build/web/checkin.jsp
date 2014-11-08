<%-- 
    Document   : checkin
    Created on : Oct 27, 2014, 12:59:21 PM
    Author     : Fenil Admin
--%>




<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Return Book</title>
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
                    <div class="hbg">
                    </div>
                </div>
                <div class="content" style="min-height: 500px">
                    <div class="content_bg">
                        <div class="mainbar" style="background-color:#FFFFFF; color:#000000">
                            <div class="article">

                                <h2><span>Return Book</span> </h2>
                                <div class="style1" style="font-size: 12; color: red"></div>
                                <div class="clr">

                                </div>
                                <form action="CheckInSearchServlet" >
                                    <h3 style="color: red">${error}</style> </h3>
                                    <c:remove var="error" />
                                    <label style="color: black">Search the book by Book ID, Card No. and/or Borrower name and then select the book that is to be returned.</style> </lable>
                                        <table width="100%" class="style5" style="color: black">
                                            <tr>
                                                <th width="25%" scope="row"><div align="left" class="style5">Book ID</div></th>
                                            <td width="75%" class="style5"><input type="text" name="bookid"  autofocus="true"/> </td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><div align="left" class="style5">Card No.</div></th>
                                            <td class="style5"><input type="text" name="card"  /></td>
                                            </tr>

                                            <tr>
                                                <th scope="row"><div align="left" class="style5">First Name </div></th>
                                            <td><input type="text" name="fname"  /></td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><div align="left" class="style5">Last Name </div></th>
                                            <td><input type="text" name="lname" /></td>
                                            </tr>
                                            <tr>
                                            </tr>
                                        </table>
                                        <input type="submit" name="search" value="Search" />
                                        <input type="reset" name="reset" value="Reset" />

                                </form>   
                                <br><p>
                                    <b>${msg}</b>
                                    <b>${check}</b>
                                </p>
                                <c:remove var="check" />
                                <c:remove var="msg" />
                                <form action="CheckInBookSevlet" method="post">
                                    <c:if test="${list.size() > 0}">
                                        <table width="100%" border="1" >
                                            <tr>
                                                <td colspan="6"><b>Select one of the book and click on 'Check In'.</b></td> 
                                            </tr>
                                            <tr>
                                                <th scope="col">Select</th>
                                                <th scope="col">Book ID</th>
                                                <th scope="col">Card No.</th>
                                                <th scope="col">Name</th>
                                                <th scope="col">Check Out Date</th>
                                                <th scope="col">Due Date</th>
                                            </tr>
                                            <c:forEach items="${list}" var="l">
                                                <tr>
                                                    <td><input type="radio" name="loanid" value="${l.loanid}" /></td>
                                                    <td>${l.bookid}</td>
                                                    <td>${l.cardno}</td>
                                                    <td>${l.fname} ${l.lname}</td>
                                                    <td>${l.dateout}</td>
                                                    <td>${l.datedue}</td>
                                                </tr>
                                            </c:forEach>
                                            <tr></tr>
                                        </table>
                                        <br>
                                        <table>
                                            <tr>
                                                <td>
                                                    <input type="submit" value="Check In" name="checkin" />
                                                </td>
                                                <td>
                                                    <input type="reset" value="Reset" />
                                                </td>
                                            </tr>
                                        </table>

                                    </c:if>
                                </form>

                            </div>
                        </div>
                        <div class="sidebar">
                            <div class="gadget">
                                <h2 class="star"><span>Menu</span></h2>
                                <div class="clr"></div>
                                <ul class="sb_menu">
                                    <li class="active"><a href="checkin.jsp">Return Book</a></li>
                                    <li><a href="checkout.jsp">Issue Book</a></li>

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

