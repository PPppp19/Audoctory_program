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
                            <h4 class="text"> <font color="#ebd4f5" style="font-weight: 1000;" >ADD ACCODE EXPEND</font></h4>
                        </div>
                        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 " style="background: linear-gradient(#be5fc1 30% ,#5e2f83  );">
                            <form  method="GET"  id="MyForm" action="./Report1" >
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">

                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;">COMPANY : </label>
                                                <select class="form-control form-control-user" name="vCompany" id="vCompany">
                                                </select>                                            </div>

                                            <br>
                                            <br>
                                            <br>
                                            <br><!-- comment -->


                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;">Account Name : </label>
                                                <input type="text" name="vaccname" id="vaccname"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;" >Account Code. : </label>
                                                <input type="text" name="vacccode" id="vacccode"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;" >Account Code2. : </label>
                                                <input type="text" name="vacccode2" id="vacccode2"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;">OPTIONS : </label>
                                                <input type="text" name="voption" id="voption"  style=" border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>

                                            <hr>


                                        </div>
                                    </div>
                                </div>

                                <br>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <!--<button class="btn btn-success " style=" background: #aaf0d1; border-radius: 5px; border: thin; color: #b356b5; width: 25%" id="vSave" type="button" name="vSave" >SAVE</button>-->
                                            </div>

                                            <br> 
                                            <br> 

                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <button class="btn btn-success " style=" background: #red; border-radius: 5px; border: thin; color: #ffffff; width: 25%" id="vADD" type="button" name="vADD" >ADD</button>
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

    $(document).ready(function () {
        $.ajax({
            url: "./Sync",
            type: "GET",
            dataType: "json",
            data: {
                page: "Company"
            },
            success: function (getdata) {
                $.each(getdata.data, function (i, obj) {
                    var div_data = "<option value=" + obj.COMPANY + ">" + obj.CCCONO + " : " + obj.CCDIVI + " : " + obj.CCCONM + "</option>";
                    $(div_data).appendTo('#vCompany');
                });
            }
        });
    });






    $("#vADD").click(function (e) {
        var code = document.getElementById("vaccname").value;
        if (window.confirm(`ADD ` + code + " ?")) {

            var vaccname = document.getElementById("vaccname").value;
            var vacccode = document.getElementById("vacccode").value;
            var vacccode2 = document.getElementById("vacccode2").value;
            var voption = document.getElementById("voption").value;
            const getCompany = document.getElementById("vCompany").value;
            const myArray = getCompany.split(":");
            var cono = myArray[0];
            var divi = myArray[1];
            var comp = myArray[2];



//            alert(vaccname + vacccode + vacccode2 + voption + cono + divi)
            $.ajax({
                type: "GET",
                url: "./Sync",
                async: false,
                data: {
                    page: "ADDEXP",
                    cono: cono,
                    divi: divi,
                    RE_DESC: vaccname,
                    RE_ACCODE1: vacccode,
                    RE_ACCODE2: vacccode2,
                    RE_OPTION : voption
                },
                success: function (result) {

                    alert("ADDED");
                }
            });
        }

    });








</script>
