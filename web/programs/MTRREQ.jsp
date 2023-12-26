<%-- 
    Document   : StorePage
    Created on : Dec 29, 2022, 4:33:21 PM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.css" />
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid-theme.min.css" />
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.js"></script>

<!DOCTYPE html>
<html>

    <style>



        #frm_sublot {
            position: relative;
        }

        .modal-dialog {
            position: fixed;
            width: 100%;
            margin: 0;
            padding: 10px;
        }




        .jsgrid-header-row>.jsgrid-header-cell {
            /*background-color: #0080ff;*/
            /*background-image: linear-gradient(#6f8aff, #19bfde);*/
            background-image: linear-gradient(#000000, #000000);

            /*font-family: "Roboto Slab";*/
            font-size: 1.2em;
            color: white;
            font-weight: normal;
            align-content: center;
            text-align: center;
            align-self: center;
        }



        .header {
            padding: 60px;
            text-align: center;
            /*        background: #070c13;*/
            background: #19bfde;
            color: white;
            font-size: 30px;
            height: 180px;

            /*            background-image: linear-gradient(#6f8aff, #19bfde);*/
            background-image: linear-gradient(#000000, #000000);
            /*        border-top-left-radius:10px;*/
            /*        border-top-right-radius:10px;*/
            border-bottom-right-radius:  20% 50%;




            /*        border-top-left-radius:10px;
                    border-top-right-radius:10px;*/

        }


        .spinnermodal {
            background-color: #FFFFFF;
            height: 100%;
            left: 0;
            opacity: 0.5;
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 100000;
        }

        /* Center the loader */
        .loader {
            position: absolute;
            left: 50%;
            top: 50%;
            z-index: 1;
            width: 120px;
            height: 120px;
            margin: -76px 0 0 -76px;
            border: 16px solid #f3f3f3;
            border-radius: 50%;
            border-top: 16px solid #000000;
            -webkit-animation: spin 2s linear infinite;
            animation: spin 2s linear infinite;
        }

        @-webkit-keyframes spin {
            0% {
                -webkit-transform: rotate(0deg);
            }
            100% {
                -webkit-transform: rotate(360deg);
            }
        }

        @keyframes spin {
            0% {
                transform: rotate(0deg);
            }
            100% {
                transform: rotate(360deg);
            }
        }

        /* Add animation to "page content" */
        .animate-bottom {
            position: relative;
            -webkit-animation-name: animatebottom;
            -webkit-animation-duration: 1s;
            animation-name: animatebottom;
            animation-duration: 1s
        }

        @-webkit-keyframes animatebottom {
            from {
                bottom:-100px;
                opacity:0
            }
            to {
                bottom:0px;
                opacity:1
            }
        }

        @keyframes animatebottom {
            from{
                bottom:-100px;
                opacity:0
            }
            to{
                bottom:0;
                opacity:1
            }
        }

        #myDiv {
            display: none;
            text-align: center;
        }

        .jsgrid-grid-body{

            color: #161637;
            text-align: center;
        }

        label{
            color: #FFFFFF;


        }


    </style>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <header class="header"  >

            <h1 align="center";  style=" color: #ffffff; font-weight: bold"> Material Request ROLLBACK  </h1>

            <h3 align="center";  style=" color: #ffffff; font-weight: bold"><%out.print(session.getAttribute("comp"));%> </h3>

            <br>



        </header>



        <br>
        <br>
        <form method="GET" action="./Report">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-offset-5  col-md-2 centerDiv">
                        <label>ORDER ID :</label><br>
                        <input  type="hidden" name="company" id="company" value="<%out.print(session.getAttribute("comp"));%>" />
                        <input  type="hidden" name="username" id="username" value="<%out.print(session.getAttribute("user"));%>" />
                        <input  type="hidden" name="cono" id="cono" value="<%out.print(session.getAttribute("cono"));%>" />
                        <input  type="hidden" name="PP" id="PP" value="xoxoxo" />

                        <input class="form-control"  autocomplete="off" placeholder="OrderID" id="orid" name="orid" value="">
                        <!--                        <datalist id="orderidlist"> </datalist>-->

                    </div> 
                </div>
            </div>
            <br><br><br><br>
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-offset-5  col-md-2 centerDiv">
                        <!--                        <button  type="submit" name="report"  id="report"  class="btn btn-danger text-center" value="MATERAIL_REQ" style="width: 157px;">PRINT REPORT</button> -->
                    </div> 

                    <br>
                    <br>
                    <HR>


                    <div class="col-md-offset-5  col-md-2 centerDiv">
                        <button  type="button" name="ROLLBACK"  id="ROLLBACK"  class="btn btn-danger text-center" value="ROLLBACK" style="width: 157px;">ROLLBACK</button> 
                    </div> 

                    <br>
                    <br>
                    <HR>

                    <div class="col-md-offset-5  col-md-2 centerDiv">
                        <button  type="button" name="SETITEM"  id="SETITEM"  class="btn btn-danger text-center" value="SETITEM" style="width: 157px;">SETITEM</button> 
                    </div> 


                    <br>
                    <br>
                    <HR>

                </div>
            </div>
        </form>




        <script>



            $("#ROLLBACK").click(function () {
                var cono = "<%out.print(session.getAttribute("cono"));%>";
                var divi = "<%out.print(session.getAttribute("divi"));%>";
                var ordid = document.getElementById("orid").value;


alert(cono +" :" + divi + ":" + ordid);
                $.ajax({
                    type: "GET",
                    url: "./Sync",
                    async: false,
                    data: {
                        page: "ROLLBACK",
                        cono: cono,
                        divi: divi,
                        ordid: ordid
                    },
                }).done(function () {


                    alert("RETURNED");


                });

            });

            $("#SETITEM").click(function () {

                var cono = "<%out.print(session.getAttribute("cono"));%>";
                var divi = "<%out.print(session.getAttribute("divi"));%>";
                var ordid = document.getElementById("orid").value;


                $.ajax({
                    type: "GET",
                    url: "./Sync",
                    async: false,
                    data: {
                        page: "SETITEM",
                        cono: cono,
                        divi: divi,
                        ordid: ordid
                    },
                }).done(function () {


                    alert("SET");


                });

            });



            $(document).ready(function () {


            });







        </script>