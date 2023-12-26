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
                            <h4 class="text"> <font color="#ebd4f5" style="font-weight: 1000;" >Delete E-BANKING LIST</font></h4>
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
                                                <label style=" color: #ffffff;">PAYEE NO. : </label>
                                                <input type="text" name="vPYNO" id="vPYNO"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;" >VOUCHER NO. : </label>
                                                <input type="text" name="vVCNO" id="vVCNO"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;">TRANSFER DATE: (YYYYMMDD) : </label>
                                                <input type="text" name="vDATE" id="vDATE"  style=" border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>

                                            <hr>

                                            <label style=" color: #ffffff;">ALL INVOICE AMOUNT : </label>
                                            <input type="text" name="vAMT" id="vAMT"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            &nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-primary " type="button" style=" background-color: #78c3d9; color: #b356b5; width: 20%" id="vSearch" name="vSearch" >SEARCH</button>


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
                                                <button class="btn btn-danger " style=" background: #red; border-radius: 5px; border: thin; color: #ffffff; width: 25%" id="vDEL" type="button" name="vDEL" >DELETE</button>
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





    $("#vSearch").click(function (e) {

        var vPYNO = document.getElementById('vPYNO').value;
        var vVCNO = document.getElementById('vVCNO').value;
        var vDATE = document.getElementById('vDATE').value;
        const getCompany = document.getElementById("vCompany").value;
        const myArray = getCompany.split(":");
        var cono = myArray[0];
        var divi = myArray[1];
        var comp = myArray[2];
        
        
        $.ajax({
            type: "GET",
            url: "./Sync",
            dataType: 'json',
            async: false,
            data: {
                page: "getEBK",
                cono: cono,
                divi: divi,
                pyno: vPYNO,
                vcno: vVCNO,
                date: vDATE


            },
            success: function (result) {

                alert(result);
                document.getElementById('vAMT').value = result;
            }

        });
        $("#vDEL").click(function (e) {
            var code = document.getElementById("vPYNO").value;
            if (window.confirm(`DELETE ` + code + " ?")) {

                var vPYNO = document.getElementById("vPYNO").value;
                var vVCNO = document.getElementById("vVCNO").value;
                 var vDATE = document.getElementById('vDATE').value;

                const getCompany = document.getElementById("vCompany").value;
                const myArray = getCompany.split(":");
                var cono = myArray[0];
                var divi = myArray[1];
                var comp = myArray[2];
                $.ajax({
                    type: "GET",
                    url: "./Sync",
                    async: false,
                    data: {
                        page: "DELEBK",
                        cono: cono,
                        divi: divi,
                        pyno: vPYNO,
                        vcno: vVCNO,
                        date: vDATE
                    },
                    success: function (result) {

                        alert("DELETED");
                    }
                });
            }

        });
    });


    $("#vSave").click(function (e) {


        var code = document.getElementById("vCode").value;
        var date = document.getElementById("vDate").value;
        var amt = document.getElementById("vAMT").value;
        var fee = document.getElementById("vFee").value;
        var user = document.getElementById("vUser").value;
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
                fee: fee
            },
            success: function (result) {

                alert("Saved");
            }
        });
    });


</script>
