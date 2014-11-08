<%-- 
    Document   : checkout
    Created on : Oct 28, 2014, 12:23:24 AM
    Author     : Fenil Admin
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Issue Book</title>
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

                                <h2><span>Issue Book</span> </h2>
                                <div class="style1" style="font-size: 12; color: red"></div>
                                <div class="clr">

                                </div>
                                <form action="CheckOutServlet" method="post">
                                    <h3 style="color: red">${msg}</style> </h3>
                                    <c:remove var="msg" />
                                    <label style="color: black">Enter Book ID and Card No., then click Issue.</style> </lable>
                                        <table width="100%" class="style5" style="color: black">
                                            <tr>
                                                <th width="25%" scope="row"><div align="left" class="style5">Book ID</div></th>
                                            <td width="75%" class="style5"><input type="text" name="bookid" value="${bookid}" autofocus="true"/> </td>
                                            </tr>
                                            <c:remove var="bookid" />
                                            <tr>
                                                <th scope="row"><div align="left" class="style5">Card No.</div></th>
                                            <td class="style5"><input type="text" name="card"  /></td>
                                            </tr>
                                            <tr>
                                            </tr>
                                        </table>
                                        <input type="submit" name="issue" value="Issue" />
                                        <input type="reset" name="reset" value="Reset" />

                                </form>   
                                <br><p>
                                    <b>${msg}</b>
                                    <b>${check}</b>
                                </p>
                                <c:remove var="check" />
                                <c:remove var="msg" />
                                <c:remove var="error" />
                            </div>
                        </div>
                        <div class="sidebar">
                            <div class="gadget">
                                <h2 class="star"><span>Menu</span></h2>
                                <div class="clr"></div>
                                <ul class="sb_menu">
                                    <li><a href="checkin.jsp">Return Book</a></li>
                                    <li class="active"><a href="checkout.jsp">Issue Book</a></li>

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

