<%-- 
    Document   : RC001
    Created on : Apr 21, 2023, 1:59:26 PM
    Author     : Phongsathon
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"  content="width=device-width, initial-scale=1.0">
        <title>Daily cash : Report DC001</title>
        <style>
            .ui-widget *, .ui-widget input, .ui-widget select, .ui-widget button {
                font-family: 'Helvetica Neue Light', 'Open Sans', Helvetica;
                font-size: 14px;
                font-weight: 300 !important;
            }
            .center {


                text-align: center;
            }


            .text {

                text-transform: uppercase;
                background: linear-gradient( #6f9dae 0%, #d583ad 100%);
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                font: {
                    size: 20vw;
                    family: 'Poppins', sans-serif;
                };
            }
            </style>
        </head>
        <body>
            <div class="container"  style=" background-color: #aaf0d1; border-radius: 15px" >
                <div class="col-md-12">
                    <div class="row">
                        <div class="panel panel-default container" >
                            <div class="panel-heading" style="background-color: #000000" >
<!--                                    <h1 class="text">Hello</h1>-->
                                    <h4 class="text"> <font color="#FFFFFF" style=" font-weight: 1000;  " >Daily cash : Report DC001</font></h4>
                            </div>
                            <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 " style="background: linear-gradient(#be5fc1 30% ,#5e2f83); ">
                                <form  method="GET"  id="MyForm" action="./Report1" >
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                            <div class="center">
                                                <br>
                                                <br>
                                                <!--                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                                                                <label>FROM : </label>
                                                                                                <input type="date" name="vFROM" id="vFROM"  style="width: 15%;text-align: center"> 
                                                                                            </div>
                                                                                          
                                                                                            <br>
                                                                                            <br>
                                                                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                                                                <label>TO : </label>
                                                                                                <input type="date" name="vTO" id="vTO"  style="width: 15%;text-align: center"> 
                                                                                            </div>-->
                                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                    <label style=" color: #ffffff">FROM : </label>
                                                        <input type="date" name="vFROM" id="vFROM"  style=" color: #b356b5;
                                                    border-radius: 5px;
                                                    border: thin;
                                                    width: 15%;
                                                    text-align: center"> 
                                                    </div>

                                                    <br>
                                                    <br>
                                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                        <label style=" color: #ffffff" >TO : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label>
                                                        <input type="date" name="vTO" id="vTO"  style=" color: #b356b5;
                                                    border: thin;
                                                    border-radius: 5px;
                                                    width: 15%;
                                                    text-align: center"> 
                                                    </div>


                                                </div>
                                            </div>
                                        </div>

                                        <br>
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="center">
                                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                        <button class="btn btn-success " style=" color: #b356b5;
                                                    background-color: #78c3d9;
                                                    width: 25%"  id="REPORT" name="REPORT" type="submit" value="RC001">GET REPORT</button>
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                        <button class="btn btn-success " style=" color: #b356b5;
                                                    background-color: #aaf0d1;
                                                    width: 25%" id="REPORT" name="REPORT" type="submit" value="RC001excel">GET Excel</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                    </form>
                                </div>
                            </div>
                        </div> 
                    </div> 
                </div>
            </body>

            <script type="text/javascript">




            </script>

        </html>
