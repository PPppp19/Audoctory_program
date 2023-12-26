<%-- 
    Document   : main
    Created on : Apr 18, 2023, 9:11:30 AM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <style>


        input[type="date"]{

            .content {
                max-width: 500px;
                margin: auto;
            }
        </style>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body class="content">
            <!--
                        <div class="col-xs-3 col-sm-3 col-md-2 col-lg-3">
                        </div>
            
                        <div class="col-xs-3 col-sm-3 col-md-2 col-lg-1">
                            <label style=" color: #000000;
                                   font-weight: bold; ">CONO : 
                                <select class="form-control form-control-user" name="vCONO" id="vCONO" onchange="calldate()" style="background-color : #ffffff;
                                        color: #000000;
                                        font-weight: bold;  ">
                                    <option value="10" selected="selected">10</option>
                                    <option value="600">600</option>
                                </select>
                            </label>
                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-1 col-lg-1">
                            <label style="color: #000000"> Start Date : </label>
                                <input type="date" id="startdate" name="startdate"  style=" border-radius: 5px;
                                color: #000000;
                                font-size: 18px;
                                border: none;
                                outline: none;
                                "/>   
                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-1 col-lg-1"></div>
            
                        <div class="col-xs-2 col-sm-2 col-md-1 col-lg-1">
                            <label  style="color: #000000"> To Date : </label>
                                <input type="date" id="todate" name="todate" style=" border-radius: 5px;
                                color: #000000;
                                font-size: 18px;
                                border: none;
                                outline: none;
            
            
                                "/> 
            
                            <div class="col-xs-2 col-sm-2 col-md-1 col-lg-1"></div>
                            <label  style="visibility: hidden"> To Date : </label>
            
                                <button class="btn btn-danger" id="vUpdate" name="vUpdate" type="button">Update</button>
                            </div>-->
        </body>
    </html>

    <script>
        $("#vUpdate").click(function (e) {


            var cono = document.getElementById("vCONO").value;

            var start = GetdeDate(document.getElementById("startdate").value);
            var end = GetdeDate(document.getElementById("todate").value);
            var cono = cono;
            $.ajax({
                url: './Sync',
                type: 'GET',
                data: {
                    page: "updatedate",
                    cono: cono,
                    start: start,
                    end: end

                },
                async: false
            }).done(function () {

                alert("UPDATED");
            });

        });
        $(document).ready(function () {

            calldate("10");
        });
        function calldate() {
            var cono = document.getElementById("vCONO").value;

            var start = document.getElementById("startdate");
            var end = document.getElementById("todate");
            var cono = cono;
            $.ajax({
                url: './Sync',
                type: 'GET',
                dataType: 'json',
                data: {
                    page: "getdate",
                    cono: cono

                },
                async: false
            }).done(function (response) {

                console.log(response);
                $.each(response, function (i, obj) {
                    start.value = GetDate(obj.MSSDTE);
                    end.value = GetDate(obj.MSEDTE);
                });
            });
        }

        function GetDate(date) {

            let text = date;
            let year = text.substring(0, 4);
            let month = text.substring(4, 6);
            let day = text.substring(6, 8);
            return year + "-" + month + "-" + day;
        }


        function GetdeDate(date) {

            var datef = date.toString().split("-");
            return datef[0] + datef[1] + datef[2];
        }

    </script>
