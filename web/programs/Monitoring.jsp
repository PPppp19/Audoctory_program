<%-- 
    Document   : Monitoring
    Created on : Sep 5, 2023, 11:50:21 AM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="jsGrid"></div>

        <br>

        <div id="jsGridselect"></div>

        <div id="expand-box">     
            <div id="expand-box-header" class="clearfix">
                <span style="float:left;">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label style=" color: #000000;">    SQL QUERY : </label>
                    <br>
                    <!--        <input type="text" name="vQUERY" id="vQUERY"  style=" color: #b356b5;"/>-->
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="vQUERY" name="vQUERY" rows="10" cols="50"></textarea>
                    <br>
                    <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button style="text-align:right;" class="btn btn-primary"  id="vEXE" name="vEXE" type="button">SELECT</button>
                    <br>
                    <br>           
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button style="text-align:right;" class="btn btn-primary"  id="vEXE2" name="vEXE2" type="button">EXECUTE</button>
                </span>



                <!--<span style="float:right;">-->

                <label style=" color: #000000;">    SQL QUERY EXE : </label>
                <br>
                <!--        <input type="text" name="vQUERY" id="vQUERY"  style=" color: #b356b5;"/>-->
                <textarea id="vQUERY2" name="vQUERY2" rows="10" cols="50"></textarea>
                <br>
                <br>
                <br>
                <br>   <br>
                <br>   
                <br>
                <br>
                <br>
                <br>   <br>
                <br>   
                <!--<span style="float:right;">-->
                    <label style=" color: #000000;">TABLE_SCHEMA : </label>
                    <input type="text" name="TABLE_SCHEMA" id="TABLE_SCHEMA"  style=" color: #b356b5;"/>
                    <label style=" color: #000000;">TABLE_NAME : </label>
                    <input type="text" name="TABLE_NAME" id="TABLE_NAME"  style=" color: #b356b5;"/>
                    <button class="btn btn-primary"  id="vGET" name="vGET" type="button">Get</button> &nbsp;
                    <div style="float:right;" id="jsGridtable"></div>
                    <br>
                <!--</span>-->
            </div>
        </div>

        <br><br><!-- comment -->
        <br><br><!-- comment -->
        <br><br>  <br><br>


    </body>
</html>


<script>


    var clients = [
        {"DB_NAME": "BRLDTA0100.BANK_MAPPING", "DB_DESC": "BANK_MAPPING Program", "DB_REMARK": '-'},
        {"DB_NAME": "", "DB_DESC": "", "DB_REMARK": ''},
        {"DB_NAME": "", "DB_DESC": "", "DB_REMARK": ''},
        {"DB_NAME": "BRLDTA0100.HR_RECEIPT", "DB_DESC": "RECEIPT Program", "DB_REMARK": 'storring details of receipt'},
        {"DB_NAME": "BRLDTA0100.HEAD_RECIPT", "DB_DESC": "RECEIPT Program", "DB_REMARK": 'storring head of receipt'},
        {"DB_NAME": "BRLDTA0100.LR_LINERECEIPT", "DB_DESC": "RECEIPT Program", "DB_REMARK": 'storring invoice of receipt'},
        {"DB_NAME": "BRLDTA0100.HR_RECEIPTEXPENSE", "DB_DESC": "RECEIPT Program", "DB_REMARK": 'Table of clear EXPENSE (partial)'},
        {"DB_NAME": "BRLDTA0100.RECEIPT_EXPENSE", "DB_DESC": "RECEIPT Program", "DB_REMARK": 'storring EXPENSE List'},
        {"DB_NAME": "BRLDTA0100.LR_LINECLEAREXP", "DB_DESC": "RECEIPT Program", "DB_REMARK": 'storring expense invoice of receipt'},
        {"DB_NAME": "", "DB_DESC": "", "DB_REMARK": ''},
        {"DB_NAME": "", "DB_DESC": "", "DB_REMARK": ''},
        {"DB_NAME": "BRLDTA0100.FAR_MTRREQ04 ", "DB_DESC": "Material Request  Program", "DB_REMARK": 'storring Head of Material order'},
        {"DB_NAME": "BRLDTA0100.FAR_ITMTLB04", "DB_DESC": "Material Request  Program", "DB_REMARK": 'storring details of Material order'},
        {"DB_NAME": "", "DB_DESC": "", "DB_REMARK": ''},
        {"DB_NAME": "", "DB_DESC": "", "DB_REMARK": ''},
        {"DB_NAME": "BRLDTA0100.EBK_PAYMAL", "DB_DESC": "E-Banking Program", "DB_REMARK": 'E-banking List  for Delete'},
    ];
    $(document).ready(function () {

        Setfields();


    });


    var clients2 = [

        {title: "DB_NAME", name: "DB_NAME", type: "text", align: "center"},
        {title: "DB_DESC", name: "DB_DESC", type: "text", align: "center"},
        {title: "DB_REMARK", name: "DB_REMARK", type: "text", align: "center"}
    ];

    var clientsfields = [

        {title: "DD1", name: "DD1", type: "text", align: "center"},
        {title: "DD2", name: "DD2", type: "text", align: "center"},
        {title: "DD3", name: "DD3", type: "text", align: "center"},
        {title: "DD4", name: "DD4", type: "text", align: "center"},
        {title: "DD5", name: "DD5", type: "text", align: "center"},
        {title: "DD6", name: "DD6", type: "text", align: "center"},
        {title: "DD7", name: "DD7", type: "text", align: "center"},
        {title: "DD8", name: "DD8", type: "text", align: "center"},
        {title: "DD9", name: "DD9", type: "text", align: "center"},
        {title: "DD10", name: "DD10", type: "text", align: "center"},
        {title: "DD11", name: "DD11", type: "text", align: "center"},
        {title: "DD12", name: "DD12", type: "text", align: "center"},
        {title: "DD13", name: "DD13", type: "text", align: "center"},
        {title: "DD14", name: "DD14", type: "text", align: "center"},
        {title: "DD15", name: "DD15", type: "text", align: "center"},
        {title: "DD16", name: "DD16", type: "text", align: "center"},
        {title: "DD17", name: "DD17", type: "text", align: "center"},
        {title: "DD18", name: "DD18", type: "text", align: "center"},
        {title: "DD19", name: "DD19", type: "text", align: "center"},
        {title: "DD20", name: "DD20", type: "text", align: "center"},
        {title: "DD21", name: "DD21", type: "text", align: "center"},
        {title: "DD22", name: "DD22", type: "text", align: "center"},
        {title: "DD23", name: "DD23", type: "text", align: "center"},
        {title: "DD24", name: "DD24", type: "text", align: "center"},
        {title: "DD25", name: "DD25", type: "text", align: "center"},
        {title: "DD26", name: "DD26", type: "text", align: "center"},
        {title: "DD27", name: "DD27", type: "text", align: "center"},
        {title: "DD28", name: "DD28", type: "text", align: "center"},
        {title: "DD29", name: "DD29", type: "text", align: "center"},
        {title: "DD30", name: "DD30", type: "text", align: "center"},
        {title: "DD31", name: "DD31", type: "text", align: "center"},
        {title: "DD32", name: "DD32", type: "text", align: "center"},
        {title: "DD33", name: "DD33", type: "text", align: "center"},
        {title: "DD34", name: "DD34", type: "text", align: "center"},
        {title: "DD35", name: "DD35", type: "text", align: "center"},
        {title: "DD36", name: "DD36", type: "text", align: "center"},
        {title: "DD37", name: "DD37", type: "text", align: "center"},
        {title: "DD38", name: "DD38", type: "text", align: "center"},
        {title: "DD39", name: "DD39", type: "text", align: "center"},
        {title: "DD40", name: "DD40", type: "text", align: "center"},
    ];



    $("#vEXE").click(function (e) {

        $("#jsGridselect").jsGrid({
            width: "100%",
            height: "auto",
            filtering: false,
            inserting: false,
            editing: false,
            sorting: true,
            pageSize: 10,
            paging: true,
            autoload: true,
            controller: {
                loadData: function (filter) {
                    var data = $.Deferred();


                    var vQUERY = document.getElementById('vQUERY').value;
                    var TYPE = 'SELECT';

                    $.ajax({
                        type: "GET",
                        url: "./Sync",
                        dataType: 'json',
                        async: false,
                        data: {
                            page: "CALLQUERY",
                            vQUERY: vQUERY,
                            //                TYPE:TYPE

                        },
                        error: function (result) {

                            alert("Error");
                            S
                        },
                        success: function (result) {


                            data.resolve(result);
                            alert("Success");

                            console.log(result);

                        }
                    });

                    return data.promise();
                },
            }
            , fields: clientsfields


        });



    });


    $("#vEXE2").click(function (e) {

        var vQUERY = document.getElementById('vQUERY2').value;
        $.ajax({
            type: "GET",
            url: "./Sync",
            async: false,
            data: {
                page: "XQUERY",
                vQUERY: vQUERY,

            },
            error: function (result) {

                alert("Error");

            },
            success: function (result) {

                alert("Success");

            }
        });


    });

    $("#vGET").click(function (e) {


        Settable();




    });



    function Setfields() {


        $("#jsGrid").jsGrid({
            width: "100%",
            height: "auto",
            filtering: false,
            inserting: false,
            editing: false,
            sorting: true,
            paging: false,
            autoload: true,
            data: clients
            , fields: clients2


        });


    }

    function Settable() {


        $("#jsGridtable").jsGrid({
            width: "50%",
            height: "auto",
            filtering: false,
            inserting: false,
            editing: false,
            sorting: true,
            pageSize: 10,
            paging: true,
            autoload: true,
            controller: {
                loadData: function (filter) {
                    var data = $.Deferred();


                    var TABLE_SCHEMA = document.getElementById('TABLE_SCHEMA').value;
                    var TABLE_NAME = document.getElementById('TABLE_NAME').value;


                    $.ajax({
                        type: "GET",
                        url: "./Sync",
                        dataType: 'json',
                        async: false,
                        data: {
                            page: "CALLTABLE",
                            TABLE_SCHEMA: TABLE_SCHEMA,
                            TABLE_NAME: TABLE_NAME
                                    //                TYPE:TYPE

                        },
                        error: function (result) {

                            alert("Error");


                        },
                        success: function (result) {


                            data.resolve(result);
                            alert("Success");



                        }
                    });

                    return data.promise();
                },
            }
            , fields: [
                {title: "ORDINAL_POSITION", name: "ORDINAL_POSITION", type: "text", align: "center"},
                {title: "COLUMN_NAME", name: "COLUMN_NAME", type: "text", align: "center"},
                {title: "TABLE_NAME", name: "TABLE_NAME", type: "text", align: "center"},
                {title: "DATA_TYPE", name: "DATA_TYPE", type: "text", align: "center"},
                {title: "LENGTH", name: "LENGTH", type: "text", align: "center"}]


        });



    }





</script>
