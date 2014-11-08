<%-- 
    Document   : borrower
    Created on : Oct 19, 2014, 12:50:53 PM
    Author     : Fenil Admin
--%>





<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add borrower</title>
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
                                    <h3 style="color: red">${error}</style> </h3>
                                    <c:remove var="error" />
                                    <label style="color: red">Fields with * are mandatory.</style> </lable>
                                        <table width="100%" height="229"  class="style5" style="color: black">
                                            <tr>
                                                <th width="25%" scope="row"><div align="left" class="style5">First Name <label style="color: red">*</style> </lable> </div></th>
                                            <td width="75%" class="style5"><input type="text" name="fname" autofocus="true"/> </td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><div align="left" class="style5">Last Name <label style="color: red">*</style> </lable></div></th>
                                            <td class="style5"><input type="text" name="lname"/></td>
                                            </tr>

                                            <tr>
                                                <th scope="row"><div align="left" class="style5">Address <label style="color: red">*</style> </lable></div></th>
                                            <td><input type="text" name="st" placeholder="Street"/></td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><div align="left"><span class="style5"><span class="style5"></span></span></div></th>
                                            <td><input type="text" name="ct"  placeholder="City"/> </td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><div align="left"><span class="style5"><span class="style5"></span></span></div></th>
                                            <td><input type="text" name="state"  placeholder="State"/></td>
                                            </tr>

                                            <tr>
                                                <th scope="row"><div align="left" class="style5">Phone</div></th>
                                            <td><input type="text" name="ph" /></td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><div align="left" class="style5">Card No.</div></th>
                                            <td><input type="text" name="card" disabled="true" placeholder="Will be generated..."/></td>
                                            </tr>
                                            <tr></tr>

                                        </table>
                                        <input type="submit" name="add" value="Add" />
                                        <input type="reset" name="reset" value="Reset" />

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
