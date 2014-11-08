<%-- 
    Document   : returncardno
    Created on : Oct 19, 2014, 1:56:59 PM
    Author     : Fenil Admin
--%>




<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Borrower Card No.</title>
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
                            <li><a href="fine.jsp">Fine</a></li>
                            <li class="active"><a href="borrower.jsp">Borrower</a></li>
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

                                <h2><span>Add new borrower</span> </h2>
                                <div class="style1" style="font-size: 12; color: red"></div>
                                <div class="clr">

                                </div>
                                <form action="AddBorrowerServlet" name="borrower" method="post">
                                    <h3 style="color: red">${msg}</style> </h3>
                                    <c:remove var="msg" />
                                    <label> Your Card No. is: </label>
                                    <label>${msg2}</label>


                                </form>           

                                <c:remove var="msg2" />
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
