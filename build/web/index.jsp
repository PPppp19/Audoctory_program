<%-- 
    Document   : index
    Created on : Jun 14, 2019, 9:32:52 AM
    Author     : PP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        if (session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
        }
    %>



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/jQuery-3.3.1/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="assets/Bootstrap-3.3.7/css/bootstrap.min.css"/>
        <script type="text/javascript" src="assets/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="assets/Bootstrap-3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="assets/jQuery-3.3.1/jquery-ui.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

        <link rel="stylesheet" type="text/css" href="assets/jsgrid-1.5.3/dist/jsgrid.min.css" />
        <link rel="stylesheet" type="text/css" href="assets/jsgrid-1.5.3/dist/jsgrid-theme.min.css">
        <script type="text/javascript" src="assets/jsgrid-1.5.3/dist/jsgrid.min.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.core.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.validation.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.load-indicator.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.load-strategies.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.sort-strategies.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.field.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.text.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.number.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.select.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.checkbox.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.control.js"></script>
        <link rel="icon" type="image/icon" href="images/duck.jpg" />

    </head>
    <body style=" background: linear-gradient(#ead4f3 50% ,#7583a9  );  background-repeat: no-repeat;   background-attachment: fixed;">
        <div>
            <nav class="navbar navbar-default navigation-clean" style=" background-color: #000000; border-color: #000000; border-radius: 0 0 0 0; "  >
                <div class="container" >
                    <div class="navbar-header"><a class="navbar-brand" href="./" style="color: #b356b5; font-weight: bold">BR GROUP </a><button data-toggle="collapse" class="navbar-toggle collapsed" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button></div>
                    <div class="collapse navbar-collapse" id="navcol-1">
                        <ul class="nav navbar-nav navbar-right">

                            <li role="presentation"></li>
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./"style="color: #ebd4f5">RECEIPT&nbsp;<span class="caret"></span></a><!--
                                -->                                <ul class="dropdown-menu" role="menu"><!--
                                    -->                                    <li role="presentation"><a class='dropdown-item'  href="?page=RC001">RC001 : ADD ACCODE EXPEND</a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=add_LINERC">ADD000 : LINE_RECEIPT </a></li>
                                    <li id= "BAKAHITOTACHI" role="presentation"><a class='dropdown-item'  href="?page=ADDCHECK">ADD001 : ADD CUSTOMER CHECK </a></li>

                                    <li id="storepart" role="presentation"><a class='dropdown-item'  href="?page=MONITOR_RECEIPT">RC002 : MONITOR_RECEIPT</a></li>
                                    <li id="DEPTHEAD" role="presentation"><a class='dropdown-item'  href="?page=UNLOCKINV">RC00- : UNLOCK INVOICE</a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=RETURNID">RT001 : RETURN ID</a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=RETURNEXP">RT001 : RETURN EXP ID</a></li>

                                    <li role="presentation"><a class='dropdown-item'  href="?page=Receipt_REPORT">RP- 04 : Receipt_REPORT</a></li>


                                </ul>
                            </li> 
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./"style="color: #ebd4f5">BANK MAPPING&nbsp;<span class="caret"></span></a><!--
                                -->                                <ul class="dropdown-menu" role="menu"><!--
                                    -->                                    <li role="presentation"><a class='dropdown-item'  href="?page=BMP001">BM001 : Bank Mapping</a></li><!--
                                    -->                                    <li role="presentation"><a class='dropdown-item'  href="?page=BMP002">BM002 : Bank Mapping OLD</a></li><!--

                                                                                                            <li role="presentation"><a class='dropdown-item'  href="?page=BN001">BN001 : Billing Note</a></li>
                                                                        <li role="presentation"><a class='dropdown-item'  href="?page=BN002">BN002 : Monitoring</a></li>
                                                                        <li role="presentation"><a class='dropdown-item'  href="?page=BN003">BN003 : Search Invoice</a></li>
                                                                        <li role="presentation"><a class='dropdown-item'  href="?page=BN004">BN004 : Edit Billing Note</a></li>
                                    
                                    -->                                </ul>
                            </li>  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./"style="color: #ebd4f5">DAILY_CASH&nbsp;<span class="caret"></span></a><!--
                                -->                                <ul class="dropdown-menu" role="menu"><!--
                                    -->                                 

                                    <li  role="presentation"><a class='dropdown-item' style="font-weight:bold; pointer-events:none;  "  href="?page=BN001">Program </a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=DC000">DC000 : Edit Daily Cash List</a></li>


                                    <li  role="presentation"><a class='dropdown-item' style="font-weight:bold; pointer-events:none;  "  href="?page=BN001"></a></li>
                                    <li role="presentation"><a class='dropdown-item' style="font-weight:bold; pointer-events:none; "  href="?page=BN001">Report </a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=DC001">DC001 : DAILY_CASH REPORT.</a></li>

                                </ul>
                            </li>



                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./"style="color: #ebd4f5">E-BANKING&nbsp;<span class="caret"></span></a><!--
                                -->                                <ul class="dropdown-menu" role="menu"><!--
                                    -->                                 

                                    <li  role="presentation"><a class='dropdown-item' style="font-weight:bold; pointer-events:none;  "  href="?page=BN001">Program </a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=EBK001">EBK001 : Delete EBK List</a></li>



                                </ul>
                            </li>


                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./"style="color: #ebd4f5">MATERIAL REQUEST&nbsp;<span class="caret"></span></a><!--
                                -->                                <ul class="dropdown-menu" role="menu"><!--
                                    -->                                 

                                    <li  role="presentation"><a class='dropdown-item' style="font-weight:bold; pointer-events:none;  "  href="?page=BN001">Program </a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=MTRREQ">MTRREQ01 : ROLL BACK</a></li>



                                </ul>
                            </li>

                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./"style="color: #ebd4f5">MONITORING&nbsp;<span class="caret"></span></a><!--
                                -->                                <ul class="dropdown-menu" role="menu"><!--
                                    -->                                 

                                    <li  role="presentation"><a class='dropdown-item' style="font-weight:bold; pointer-events:none;  "  href="?page=BN001">Program </a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=Monitoring">MONITORING</a></li>



                                </ul>
                            </li>
                            <!--                            <li role="presentation"><a class='dropdown-item' style="font-weight: bold;color: #ffffff;font-size: 16px" href="?page=BN000">Billing Note</a></li>
                                                        <li role="presentation"><a class='dropdown-item' style="font-weight: bold;color: #ffffff;font-size: 16px" href="?page=BN004">Edit Billing</a></li>
                                                        <li role="presentation"><a class='dropdown-item' style="font-weight: bold;color: #ffffff;font-size: 16px" href="?page=BN002">Monitoring</a></li>
                                                        <li role="presentation"><a class='dropdown-item' style="font-weight: bold;color: #ffffff;font-size: 16px" href="?page=BN003">Search Invoice</a></li>-->

                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <form action="Logout" method="POSTs">
                                        <input style=" border-radius: 5px; color: #ebd4f5; background-color: #000000; border-color: #ebd4f5;"  type="submit" value="Sign Out">

                                    </form>
                                </a>
                            </li>
                        </ul>

                    </div>
                    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
                </div>
            </nav>
        </div>
        <section id="programs">
            <div class='centerDiv' id="vSection"></div>
        </section>
    </body>
</html>

<script type="text/javascript">

    <% if (request.getParameter("page") != null) {%>
    $("#vSection").load("./programs/<%=request.getParameter("page").toString()%>.jsp");
    <%  } else {  %>
    $("#vSection").load("./programs/main.jsp");
//    $("#vSection").load("./programs/BN001.jsp");
    $("#vSection").load("./programs/main.jsp");
    <% }%>


    $('.dropdown-item').on('click', function () {
        console.log(this.name);
        $("#vSection").load("./programs/" + this.name + ".jsp");
    });


    $(document).ready(function () {

        var username = "<%out.print(session.getAttribute("username"));%>";
        var st = document.getElementById("storepart");
        var DH = document.getElementById("DEPTHEAD");
        var BB = document.getElementById("BAKAHITOTACHI");

        if (username == "NITTAYA_KOM" || username == "PRANEE_KHA" || username == "M3SRVICT"|| username == "TAWA_HOA" || username == "SANTIM_PUP" )
        {
            BB.style.visibility = "Visible";


        } else {

            BB.style.visibility = "Hidden";


        }
        if (username == "PHONGS_PHO")
        {
            st.style.visibility = "Visible";
            DH.style.visibility = "Visible";
            BB.style.visibility = "Visible";


        } else {
            st.style.visibility = "Hidden";
            DH.style.visibility = "Hidden";

        }


    });
</script>