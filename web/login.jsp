

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Log In</title>
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
                          
                            <li class="active"><a href="LoginLoadServlet">Log In</a></li>
                           
                        </ul>
                        <div class="clr"></div>
                    </div>
                    <div class="hbg"></div>
                </div>
                <div class="content" style="min-height: 500px">
                    <div class="content_bg">
                        <div class="mainbar" style="background-color:#FFFFFF; color:#000000">
                            <div class="article">

                                <h2><span>Log In</span> </h2>
                                <div class="style1" style="font-size: 12; color: red">  ${Status}${Update}</div>
                                <div class="clr"></div>
                                <c:remove var="Status" />
                                <c:remove var="Update" />
                                
                                <form action="LoginProcessServlet" method="post">
                                    <ol>
                                        <li>
                                            <h3 style="color: red"> ${msg} </h3>
                                            <c:remove var="msg" />
                                            <label for="username">
                                                
                                                <span class="style1">Select your branch (ID - Name)*</span>                  
                                                <p>
                                            </label>
                                            </p>

                                            <div>
                                                <select name="branch" size="1" >

                                                    <c:forEach items="${list}" var="t">
                                                        <option value="${t.branchid}">${t.branchid} - ${t.name}</option>
                                                    </c:forEach>
                                                </select>
                                            
                                            </div>
                                            <label>
                                                Password:
                                            </label>
                                            <br>
                                            <input type="password" name="pwd" autofocus="true"/>
                                        </li>
                                        <li>
                                            <p></p> <input type="Submit" name="submit" id="submit" value="Log in" /></li>
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
