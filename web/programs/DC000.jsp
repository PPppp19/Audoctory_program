<%-- 
    Document   : DC000
    Created on : Apr 26, 2023, 10:19:15 AM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

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
            }
            ;
        }

    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container"  style=" background-color: #aaf0d1; border-radius: 15px">
            <div class="col-md-12">
                <div class="row">
                    <div class="panel panel-default container">
                        <div class="panel-heading" style="background-color: #000000">
                            <h4 class="text"> <font color="#ebd4f5" style="font-weight: 1000;" >Edit Daily Cash List</font></h4>
                        </div>
                        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 " style="background: linear-gradient(#be5fc1 30% ,#5e2f83  );">
                            <form  method="GET"  id="MyForm" action="./Report1" >
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <hr>
                                            <h1> IN </h1>
                                            <hr>
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;">Date : </label>
                                                <input type="text" name="vDate" id="vDate"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;" >Amount : </label>
                                                <input type="text" name="vAMT" id="vAMT"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;">Fee : </label>
                                                <input type="text" name="vFee" id="vFee"  style=" border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>

                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;">User Key : </label>
                                                <input  type="text" name="vUser" id="vUser"   style=" border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center;"> 
                                            </div>
                                            <br>
                                            <br>
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;">User HOLD : </label>
                                                <input  type="text" name="uhold" id="uhold"   style=" border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center;"> 
                                            </div>

                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>

                                            <label style=" color: #ffffff;">Running Code : </label>
                                            <input type="text" name="vCode" id="vCode"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            &nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-primary " type="button" style=" background-color: #78c3d9; color: #b356b5; width: 20%" id="vSearch" name="vSearch" >SEARCH</button>

                                            <br>
                                            <br>
                                            
                                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <button class="btn btn-success " style=" background: #aaf0d1; border-radius: 5px; border: thin; color: #b356b5; width: 25%" id="vSave" type="button" name="vSave" >SAVE</button>
                                            </div>

                                            <br> 
                                            <br> 

                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <button class="btn btn-danger " style=" background: #red; border-radius: 5px; border: thin; color: #ffffff; width: 25%" id="vDEL" type="button" name="vDEL" >DELETE</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <hr>
                                            <h1> OUT </h1>
                                            <hr>



                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;">Date : </label>
                                                <input type="text" name="vDateo" id="vDateo"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;" >Amount : </label>
                                                <input type="text" name="vAMTo" id="vAMTo"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;">Fee : </label>
                                                <input type="text" name="vFeeo" id="vFeeo"  style=" border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>

                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;">User Key : </label>
                                                <input  type="text" name="vUsero" id="vUsero"   style=" border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center;"> 
                                            </div>
                                            <br>
                                            <br>
                                           

                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>

                                            <label style=" color: #ffffff;">Running Code : </label>
                                            <input type="text" name="vCodeo" id="vCodeo"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            &nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-primary " type="button" style=" background-color: #78c3d9; color: #b356b5; width: 20%" id="vSearcho" name="vSearcho" >SEARCH</button>


                                        </div>
                                    </div>
                                </div>

                                <br>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <button class="btn btn-success " style=" background: #aaf0d1; border-radius: 5px; border: thin; color: #b356b5; width: 25%" id="vSaveo" type="button" name="vSaveo" >SAVE</button>
                                            </div>

                                            <br> 
                                            <br> 

                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <button class="btn btn-danger " style=" background: #red; border-radius: 5px; border: thin; color: #ffffff; width: 25%" id="vDELo" type="button" name="vDELo" >DELETE</button>
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



</html>

<script>

    $("#vDEL").click(function (e) {
        var code = document.getElementById("vCode").value;

        if (window.confirm(`DELETE ` + code + " ?")) {

            var date = document.getElementById("vDate").value;
            var amt = document.getElementById("vAMT").value;
            var fee = document.getElementById("vFee").value;
            var user = document.getElementById("vUser").value;
            $.ajax({
                type: "GET",
                url: "./Sync",
                dataType: 'json',
                async: false,
                data: {
                    page: "DELDL",
                    code: code,
                    user: user,
                    date: date,
                    amt: amt,
                    fee: fee
                },
                success: function (result) {

                    alert("DELETED");
                }
            });

        }

    });
    $("#vSearch").click(function (e) {


        var user = "<%out.print(session.getAttribute("username"));%>";
        var code = document.getElementById("vCode").value;
        $.ajax({
            type: "GET",
            url: "./Sync",
            dataType: 'json',
            async: false,
            data: {
                page: "getdailylist",
                code: code,
            },
            success: function (result) {

//                alert(JSON.stringify(result));
                $.each(result, function (i, obj) {
                    document.getElementById("vDate").value = obj.TRDT;
                    document.getElementById("vAMT").value = obj.AMOUT;
                    document.getElementById("vFee").value = obj.FEE;
                    document.getElementById("vUser").value = obj.USER;
                    document.getElementById("uhold").value = obj.UHOLD;

                });
            }
        });
        $("#vSave").click(function (e) {


            var code = document.getElementById("vCode").value;
            var date = document.getElementById("vDate").value;
            var amt = document.getElementById("vAMT").value;
            var fee = document.getElementById("vFee").value;
            var user = document.getElementById("vUser").value;
            var uhold = document.getElementById("uhold").value;
            console.log(code);
            $.ajax({
                type: "GET",
                url: "./Sync",
                dataType: 'json',
                async: false,
                data: {
                    page: "updatedailiylist",
                    code: code,
                    user: user,
                    date: date,
                    amt: amt,
                    fee: fee,
                    uhold: uhold
                },
                success: function (result) {

                    alert("Saved");
                }
            });
        })
                ;

    });
    
    
    
    
    
    
    
    
     $("#vDELo").click(function (e) {
        var code = document.getElementById("vCodeo").value;

        if (window.confirm(`DELETE ` + code + " ?")) {

            var date = document.getElementById("vDateo").value;
            var amt = document.getElementById("vAMTo").value;
            var fee = document.getElementById("vFeeo").value;
            var user = document.getElementById("vUsero").value;
            $.ajax({
                type: "GET",
                url: "./Sync",
                dataType: 'json',
                async: false,
                data: {
                    page: "DELDLo",
                    code: code,
                    user: user,
                    date: date,
                    amt: amt,
                    fee: fee
                },
                success: function (result) {

                    alert("DELETED");
                }
            });

        }

    });
    $("#vSearcho").click(function (e) {


        var user = "<%out.print(session.getAttribute("username"));%>";
        var code = document.getElementById("vCodeo").value;
        $.ajax({
            type: "GET",
            url: "./Sync",
            dataType: 'json',
            async: false,
            data: {
                page: "getdailylisto",
                code: code,
            },
            success: function (result) {

//                alert(JSON.stringify(result));
                $.each(result, function (i, obj) {
                    document.getElementById("vDateo").value = obj.TRDT;
                    document.getElementById("vAMTo").value = obj.AMOUT;
                    document.getElementById("vFeeo").value = obj.FEE;
                    document.getElementById("vUsero").value = obj.USER;

                });
            }
        });
        $("#vSaveo").click(function (e) {


            var code = document.getElementById("vCodeo").value;
            var date = document.getElementById("vDateo").value;
            var amt = document.getElementById("vAMTo").value;
            var fee = document.getElementById("vFeeo").value;
            var user = document.getElementById("vUsero").value;
            console.log(code);
            $.ajax({
                type: "GET",
                url: "./Sync",
                dataType: 'json',
                async: false,
                data: {
                    page: "updatedailiylisto",
                    code: code,
                    user: user,
                    date: date,
                    amt: amt,
                    fee: fee
                },
                success: function (result) {

                    alert("Saved");
                }
            });
        })
                ;

    });
</script>
