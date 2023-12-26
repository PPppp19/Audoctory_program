<%-- 
    Document   : MONITOR_RECEIPT
    Created on : Aug 23, 2023, 1:18:33 PM
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
        <hr>
        <hr>
        <div class="col-md-12">
            <label style=" color: #000000;">LR_RECEIPT : </label>

            <label style=" color: #000000;">RCNO : </label>


            <label style=" color: #000000;">RCNO : </label>
            <input type="text" name="vRCNOLR" id="vRCNOLR"  style=" color: #b356b5;"/>
            <hr>
            <hr>
            <label style=" color: #000000;">CONO : </label>
            <input type="text" name="vCONOLRNO" id="vCONOLRNO"  style=" color: #b356b5;"/>
            <hr>
            <hr>
            <label style=" color: #000000;">DIVI : </label>
            <input type="text" name="vDIVILRNO" id="vDIVILRNO"  style=" color: #b356b5;"/>
            <hr>
            <hr>
            <button class="btn btn-success"  id="sRCNOLR" name="sRCNOLR" type="button">SEARCH LRRCNO</button>

            <br>
            <br> <br>
            <div id="jsGridLRNO"></div>


            <hr>
        </div>
        <hr>
        <div class="col-md-12">
            <label style=" color: #000000;">LR_EXPENSE : </label>



            <label style=" color: #000000;">RCNO : </label>
            <input type="text" name="vRCNOLREXP" id="vRCNOLREXP"  style=" color: #b356b5;"/>
            <hr>
            <hr>
            <label style=" color: #000000;">CONO : </label>
            <input type="text" name="vCONOLRNOEXP" id="vCONOLRNOEXP"  style=" color: #b356b5;"/>
            <hr>
            <hr>
            <label style=" color: #000000;">DIVI : </label>
            <input type="text" name="vDIVILRNOEXP" id="vDIVILRNOEXP"  style=" color: #b356b5;"/>
            <hr>
            <hr>
            <button class="btn btn-success"  id="sRCNOLREXP" name="sRCNOLREXP" type="button">SEARCH EXPRCNO</button>

            <br>
            <br> <br>
            <div id="jsGridLRNOEXP"></div>


            <hr>
        </div>

        <hr>


        <hr>
    </body>
</html>


<script>

    $("#xQUERY").click(function (e) {

        var ID = document.getElementById('vID').value;
        var CONO = document.getElementById('vCONO').value;
        var DIVI = document.getElementById('vDIVI').value;

        $.ajax({
            type: "GET",
            url: "./Sync",
            dataType: 'json',
            async: false,
            data: {
                page: "CALLID",
                ID: ID,
                CONO: CONO,
                DIVI: DIVI
            },
            success: function (result) {

                var jj = 1;
                $.each(result, function (i, obj) {




                });
            }
        });

    });





    $("#sID").click(function (e) {

        var ID = document.getElementById('vID').value;
        var CONO = document.getElementById('vCONO').value;
        var DIVI = document.getElementById('vDIVI').value;


        $("#jsGridID").jsGrid({
            width: "100%",
            height: "auto",
            filtering: true,
            inserting: true,
            editing: true,
            sorting: true,
            paging: true,
            autoload: true,
            controller: {
                loadData: function (filter) {
                    var data = $.Deferred();


                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'json',
                        data: {

                            page: "CALLID",
                            ID: ID,
                            CONO: CONO,
                            DIVI: DIVI
                        },
                        async: false
                    }).done(function (response) {
                        data.resolve(response);




                    });
                    return data.promise();
                },
                updateItem: function (item) {


                    var CONO = item.H_CONO;
                    var DIVI = item.H_DIVI;
                    var RCNO = item.H_RCNO;
                    var CUNO = item.H_CUNO;
                    var PYNO = item.H_PYNO;
                    var STS = item.H_STS;
                    var VCNO = item.H_VCNO;
                    var LOCATION = item.H_LOCATION;
                    var TYPE = item.H_TYPE;
                    var RNNO = item.H_RNNO;

//                    alert("CONO :" + CONO);
//                    alert("DIVI :" + DIVI);
//                    alert("RCNO :" + RCNO);
//                    alert("CUNO :" + CUNO);
//                      alert("PYNO :" + PYNO);
//                    alert("STS :" + STS);
//                    alert("VCNO :" + VCNO);
//                    alert("LOCATION :" + LOCATION);
//                      alert("TYPE :" + TYPE);
//                    alert("RNNO :" + RNNO);



                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'JSON',
                        data: {
                            page: "UPDATEID",
                            CONO: CONO,
                            DIVI: DIVI,
                            RCNO: RCNO,
                            CUNO: CUNO,
                            PYNO: PYNO,
                            STS: STS,
                            VCNO: VCNO,
                            LOCATION: LOCATION,
                            TYPE: TYPE,
                            RNNO: RNNO

                        },
                        async: false
                    }).done(function (response) {

                        alert("Updated");


                    });



                }, deleteItem: function (item) {

                    var user = "<%out.print(session.getAttribute("username"));%>";
                    var CONO = item.H_CONO;
                    var DIVI = item.H_DIVI;
                    var RCNO = item.H_RCNO;
                    var CUNO = item.H_CUNO;
                    var PYNO = item.H_PYNO;
                    var STS = item.H_STS;
                    var VCNO = item.H_VCNO;
                    var LOCATION = item.H_LOCATION;
                    var TYPE = item.H_TYPE;
                    var RNNO = item.H_RNNO;

                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        data: {
                            page: "DELETEID",
                            CONO: CONO,
                            DIVI: DIVI,
                            RCNO: RCNO,
                            CUNO: CUNO,
                            PYNO: PYNO,
                            STS: STS,
                            VCNO: VCNO,
                            LOCATION: LOCATION,
                            TYPE: TYPE,
                            RNNO: RNNO,
                            user: user

                        },
                        async: false
                    }).done(function (response) {

                        alert("Deleted");


                    });




                },
                insertItem: function (item) {



                    var CONO = item.H_CONO;
                    var DIVI = item.H_DIVI;
                    var RCNO = item.H_RCNO;
                    var CUNO = item.H_CUNO;
                    var PYNO = item.H_PYNO;
                    var STS = item.H_STS;
                    var VCNO = item.H_VCNO;
                    var LOCATION = item.H_LOCATION;
                    var TYPE = item.H_TYPE;
                    var RNNO = document.getElementById('vID').value;



//                    alert("CONO :" + CONO);
//                    alert("DIVI :" + DIVI);
//                    alert("RCNO :" + RCNO);
//                    alert("CUNO :" + CUNO);
//                      alert("PYNO :" + PYNO);
//                    alert("STS :" + STS);
//                    alert("VCNO :" + VCNO);
//                    alert("LOCATION :" + LOCATION);
//                      alert("TYPE :" + TYPE);
//                    alert("RNNO :" + RNNO);

                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        data: {
                            page: "INSERTID",
                            CONO: CONO,
                            DIVI: DIVI,
                            RCNO: RCNO,
                            CUNO: CUNO,
                            PYNO: PYNO,
                            STS: STS,
                            VCNO: VCNO,
                            LOCATION: LOCATION,
                            TYPE: TYPE,
                            RNNO: RNNO

                        },
                        async: false
                    }).done(function (response) {

                        alert("Inserted");


                    });

                }
            },

            fields: [

                {title: "H_CONO", name: "H_CONO", type: "text", align: "center", editing: true},
                {title: "H_DIVI", name: "H_DIVI", type: "text", align: "center", editing: true},
                {title: "H_RCNO", name: "H_RCNO", type: "text", align: "center", editing: true, },
                {title: "H_RNNO", name: "H_RNNO", type: "text", align: "center", editing: true, },
                {title: "H_CUNO", name: "H_CUNO", type: "text", align: "center", editing: true, },
                {title: "H_PYNO", name: "H_PYNO", type: "text", align: "center", editing: true, },
                {title: "H_STS", name: "H_STS", type: "text", align: "center", editing: true, },
                {title: "H_VCNO", name: "H_VCNO", type: "text", align: "center", editing: true, },
                {title: "H_LOCATION", name: "H_LOCATION", type: "text", align: "center", editing: true, },
                {title: "H_TYPE", name: "H_TYPE", type: "text", align: "center", editing: true, },

                {type: "control", deleteButton: true}
            ],

        });


    });



    $("#sRCNO").click(function (e) {

        var RCNO = document.getElementById('vRCNO').value;
        var CONO = document.getElementById('vCONORCNO').value;
        var DIVI = document.getElementById('vDIVIRCNO').value;


        $("#jsGridRCNO").jsGrid({
            width: "100%",
            height: "auto",
            filtering: true,
            inserting: true,
            editing: true,
            sorting: true,
            paging: true,
            autoload: true,
            controller: {
                loadData: function (filter) {
                    var data = $.Deferred();


                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'json',
                        data: {

                            page: "CALLRCNO",
                            RCNO: RCNO,
                            CONO: CONO,
                            DIVI: DIVI
                        },
                        async: false
                    }).done(function (response) {
                        data.resolve(response);




                    });
                    return data.promise();
                },
                updateItem: function (item) {




                    var CONO = item.HR_CONO;
                    var DIVI = item.HR_DIVI;
                    var RCNO = item.HC_RCNO;
                    var TRDT = item.HC_TRDT;
                    var PYNO = item.HC_PYNO;
                    var REAMT = item.HC_REAMT;
                    var TYPE = item.HC_TYPE;
                    var BANK = item.HC_BANK;
                    var ACCODE = item.HC_ACCODE;
                    var PYDT = item.HC_PYDT;
                    var CHKNO = item.HC_CHKNO;
                    var USER = item.HC_USER;
                    var VCNO = item.HC_VCNO;
                    var STS = item.HC_STS;
                    var BKCHG = item.HR_BKCHG;
                    var LOCATION = item.HC_LOCATION;
                    var FIXNO = item.HC_FIXNO;
                    var RNNO = item.HC_FNNO;




                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'JSON',
                        data: {
                            page: "UPDATERCNO",
                            CONO: CONO,
                            DIVI: DIVI,
                            RCNO: RCNO,
                            TRDT: TRDT,
                            PYNO: PYNO,
                            REAMT: REAMT,
                            TYPE: TYPE,
                            BANK: BANK,
                            ACCODE: ACCODE,
                            PYDT: PYDT,
                            CHKNO: CHKNO,
                            USER: USER,
                            VCNO: VCNO,
                            STS: STS,
                            BKCHG: BKCHG,
                            LOCATION: LOCATION,
                            FIXNO: FIXNO,
                            RNNO: RNNO,

                        },
                        async: false
                    }).done(function (response) {

                        alert("Updated");


                    });



                }, deleteItem: function (item) {


                    var CONO = item.HR_CONO;
                    var DIVI = item.HR_DIVI;
                    var RCNO = item.HC_RCNO;
                    var TRDT = item.HC_TRDT;
                    var PYNO = item.HC_PYNO;
                    var REAMT = item.HC_REAMT;
                    var TYPE = item.HC_TYPE;
                    var BANK = item.HC_BANK;
                    var ACCODE = item.HC_ACCODE;
                    var PYDT = item.HC_PYDT;
                    var CHKNO = item.HC_CHKNO;
                    var USER = item.HC_USER;
                    var VCNO = item.HC_VCNO;
                    var STS = item.HC_STS;
                    var BKCHG = item.HR_BKCHG;
                    var LOCATION = item.HC_LOCATION;
                    var FIXNO = item.HC_FIXNO;
                    var RNNO = item.HC_FNNO;


                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        data: {
                            page: "DELETERCNO",
                            CONO: CONO,
                            DIVI: DIVI,
                            RCNO: RCNO,

                        },
                        async: false
                    }).done(function (response) {

                        alert("Deleted");


                    });




                },
                insertItem: function (item) {




                    var CONO = item.HR_CONO;
                    var DIVI = item.HR_DIVI;
                    var RCNO = item.HC_RCNO;
                    var TRDT = item.HC_TRDT;
                    var PYNO = item.HC_PYNO;
                    var REAMT = item.HC_REAMT;
                    var TYPE = item.HC_TYPE;
                    var BANK = item.HC_BANK;
                    var ACCODE = item.HC_ACCODE;
                    var PYDT = item.HC_PYDT;
                    var CHKNO = item.HC_CHKNO;
                    var USER = item.HC_USER;
                    var VCNO = item.HC_VCNO;
                    var STS = item.HC_STS;
                    var BKCHG = item.HR_BKCHG;
                    var LOCATION = item.HC_LOCATION;
                    var FIXNO = item.HC_FIXNO;
                    var RNNO = item.HC_FNNO;





                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        data: {
                            page: "INSERTRCNO",
                            CONO: CONO,
                            DIVI: DIVI,
                            RCNO: RCNO,
                            TRDT: TRDT,
                            PYNO: PYNO,
                            REAMT: REAMT,
                            TYPE: TYPE,
                            BANK: BANK,
                            ACCODE: ACCODE,
                            PYDT: PYDT,
                            CHKNO: CHKNO,
                            USER: USER,
                            VCNO: VCNO,
                            STS: STS,
                            BKCHG: BKCHG,
                            LOCATION: LOCATION,
                            FIXNO: FIXNO,
                            RNNO: RNNO,

                        },
                        async: false
                    }).done(function (response) {

                        alert("Inserted : " + response);

                        document.getElementById('vRCNO').value = response;


                    });

                }
            },

            fields: [

                {title: "HR_CONO", name: "HR_CONO", type: "text", align: "center", editing: true},
                {title: "HR_DIVI", name: "HR_DIVI", type: "text", align: "center", editing: true},
                {title: "HC_RCNO", name: "HC_RCNO", type: "text", align: "center", editing: true, },
                {title: "HC_TRDT", name: "HC_TRDT", type: "text", align: "center", editing: true, },
                {title: "HC_PYNO", name: "HC_PYNO", type: "text", align: "center", editing: true, },
                {title: "HC_REAMT", name: "HC_REAMT", type: "text", align: "center", editing: true, },
                {title: "HC_TYPE", name: "HC_TYPE", type: "text", align: "center", editing: true, },
                {title: "HC_BANK", name: "HC_BANK", type: "text", align: "center", editing: true, },
                {title: "HC_ACCODE", name: "HC_ACCODE", type: "text", align: "center", editing: true, },
                {title: "HC_PYDT", name: "HC_PYDT", type: "text", align: "center", editing: true, },

                {title: "HC_CHKNO", name: "HC_CHKNO", type: "text", align: "center", editing: true, },
                {title: "HC_USER", name: "HC_USER", type: "text", align: "center", editing: true, },
                {title: "HC_VCNO", name: "HC_VCNO", type: "text", align: "center", editing: true, },
                {title: "HC_STS", name: "HC_STS", type: "text", align: "center", editing: true, },
                {title: "HR_BKCHG", name: "HR_BKCHG", type: "text", align: "center", editing: true, },
                {title: "HC_LOCATION", name: "HC_LOCATION", type: "text", align: "center", editing: true, },
                {title: "HC_FIXNO", name: "HC_FIXNO", type: "text", align: "center", editing: true, },
                {title: "HC_FNNO", name: "HC_FNNO", type: "text", align: "center", editing: true, },

                {type: "control", deleteButton: true}
            ],

        });


    });



    $("#sRCNOLR").click(function (e) {

        var RCNO = document.getElementById('vRCNOLR').value;
        var CONO = document.getElementById('vCONOLRNO').value;
        var DIVI = document.getElementById('vDIVILRNO').value;


        $("#jsGridLRNO").jsGrid({
            width: "100%",
            height: "auto",
            filtering: true,
            inserting: true,
            editing: true,
            sorting: true,
            paging: true,
            autoload: true,
            controller: {
                loadData: function (filter) {
                    var data = $.Deferred();


                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'json',
                        data: {

                            page: "CALLLRNO",
                            RCNO: RCNO,
                            CONO: CONO,
                            DIVI: DIVI
                        },
                        async: false
                    }).done(function (response) {
                        data.resolve(response);




                    });
                    return data.promise();
                },
                updateItem: function (item) {




                    var LR_CONO = item.LR_CONO;
                    var LR_DIVI = item.LR_DIVI;
                    var LR_RCNO = item.LR_RCNO;
                    var LR_INVNO = item.LR_INVNO;
                    var LR_INVDT = item.LR_INVDT;
                    var LR_INVAMT = item.LR_INVAMT;
                    var LR_REAMT = item.LR_REAMT;
                    var LR_STS = item.LR_STS;





                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'JSON',
                        data: {
                            page: "UPDATELRNO",
                            LR_CONO: LR_CONO,
                            LR_DIVI: LR_DIVI,
                            LR_RCNO: LR_RCNO,
                            LR_INVNO: LR_INVNO,
                            LR_INVDT: LR_INVDT,
                            LR_INVAMT: LR_INVAMT,
                            LR_REAMT: LR_REAMT,
                            LR_STS: LR_STS,

                        },
                        async: false
                    }).done(function (response) {

                        alert("Updated");


                    });



                }, deleteItem: function (item) {


                    var LR_CONO = item.LR_CONO;
                    var LR_DIVI = item.LR_DIVI;
                    var LR_RCNO = item.LR_RCNO;
                    var LR_INVNO = item.LR_INVNO;
                    var LR_INVDT = item.LR_INVDT;
                    var LR_INVAMT = item.LR_INVAMT;
                    var LR_REAMT = item.LR_REAMT;
                    var LR_STS = item.LR_STS;

                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        data: {
                            page: "DELETELRNO",
                            LR_CONO: LR_CONO,
                            LR_DIVI: LR_DIVI,
                            LR_RCNO: LR_RCNO,
                            LR_INVNO: LR_INVNO,
                            LR_INVDT: LR_INVDT,
                            LR_INVAMT: LR_INVAMT,
                            LR_REAMT: LR_REAMT,
                            LR_STS: LR_STS


                        },
                        async: false
                    }).done(function (response) {

                        alert("Deleted");


                    });




                },
                insertItem: function (item) {




                    var LR_CONO = item.LR_CONO;
                    var LR_DIVI = item.LR_DIVI;
                    var LR_RCNO = item.LR_RCNO;
                    var LR_INVNO = item.LR_INVNO;
                    var LR_INVDT = item.LR_INVDT;
                    var LR_INVAMT = item.LR_INVAMT;
                    var LR_REAMT = item.LR_REAMT;
                    var LR_STS = item.LR_STS;





                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        data: {
                            page: "INSERTLRNO",
                            LR_CONO: LR_CONO,
                            LR_DIVI: LR_DIVI,
                            LR_RCNO: LR_RCNO,
                            LR_INVNO: LR_INVNO,
                            LR_INVDT: LR_INVDT,
                            LR_INVAMT: LR_INVAMT,
                            LR_REAMT: LR_REAMT,
                            LR_STS: LR_STS

                        },
                        async: false
                    }).done(function (response) {

                        alert("Inserted");



                    });

                }
            },

            fields: [

                {title: "LR_CONO", name: "LR_CONO", type: "text", align: "center", editing: true},
                {title: "LR_DIVI", name: "LR_DIVI", type: "text", align: "center", editing: true},
                {title: "LR_RCNO", name: "LR_RCNO", type: "text", align: "center", editing: true, },
                {title: "LR_INVNO", name: "LR_INVNO", type: "text", align: "center", editing: true, },
                {title: "LR_INVDT", name: "LR_INVDT", type: "text", align: "center", editing: true, },
                {title: "LR_INVAMT", name: "LR_INVAMT", type: "text", align: "center", editing: true, },
                {title: "LR_REAMT", name: "LR_REAMT", type: "text", align: "center", editing: true, },
                {title: "LR_STS", name: "LR_STS", type: "text", align: "center", editing: true, },

                {type: "control", deleteButton: true}
            ],

        });


    });

    $("#sRCNOLREXP").click(function (e) {

        var RCNO = document.getElementById('vRCNOLREXP').value;
        var CONO = document.getElementById('vCONOLRNOEXP').value;
        var DIVI = document.getElementById('vDIVILRNOEXP').value;


        $("#jsGridLRNOEXP").jsGrid({
            width: "100%",
            height: "auto",
            filtering: true,
            inserting: true,
            editing: true,
            sorting: true,
            paging: true,
            autoload: true,
            controller: {
                loadData: function (filter) {
                    var data = $.Deferred();


                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'json',
                        data: {

                            page: "CALLLRNOEXP",
                            RCNO: RCNO,
                            CONO: CONO,
                            DIVI: DIVI
                        },
                        async: false
                    }).done(function (response) {
                        data.resolve(response);




                    });
                    return data.promise();
                },
                updateItem: function (item) {




                    var LRE_CONO = item.LRE_CONO;
                    var LRE_DIVI = item.LRE_DIVI;
                    var LRE_RCNO = item.LRE_RCNO;
                    var LRE_CODE = item.LRE_CODE;
                    var LRE_ACTCODE = item.LRE_ACTCODE;
                    var LRE_AMT = item.LRE_AMT;
                    var LRE_REF1 = item.LRE_REF1;
                    var LRE_REF2 = item.LRE_REF2;
                    var LRE_REF3 = item.LRE_REF3;
                    var LRE_VCTXT = item.LRE_VCTXT;
                    var LRE_FNNO = item.LRE_FNNO;





                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'JSON',
                        data: {
                            page: "UPDATELRNOEXP",
                            LRE_CONO: LRE_CONO,
                            LRE_DIVI: LRE_DIVI,
                            LRE_RCNO: LRE_RCNO,
                            LRE_CODE: LRE_CODE,
                            LRE_ACTCODE: LRE_ACTCODE,
                            LRE_AMT: LRE_AMT,
                            LRE_REF1: LRE_REF1,
                            LRE_REF2: LRE_REF2,
                            LRE_REF3: LRE_REF3,
                            LRE_VCTXT: LRE_VCTXT,
                            LRE_FNNO: LRE_FNNO

                        },
                        async: false
                    }).done(function (response) {

                        alert("Updated");


                    });



                }, deleteItem: function (item) {


                    var LRE_CONO = item.LRE_CONO;
                    var LRE_DIVI = item.LRE_DIVI;
                    var LRE_RCNO = item.LRE_RCNO;
                    var LRE_CODE = item.LRE_CODE;
                    var LRE_ACTCODE = item.LRE_ACTCODE;
                    var LRE_AMT = item.LRE_AMT;
                    var LRE_REF1 = item.LRE_REF1;
                    var LRE_REF2 = item.LRE_REF2;
                    var LRE_REF3 = item.LRE_REF3;
                    var LRE_VCTXT = item.LRE_VCTXT;
                    var LRE_FNNO = item.LRE_FNNO;

                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        data: {
                            page: "DELETELRNOEXP",
                            LRE_CONO: LRE_CONO,
                            LRE_DIVI: LRE_DIVI,
                            LRE_RCNO: LRE_RCNO,
                            LRE_CODE: LRE_CODE,
                            LRE_ACTCODE: LRE_ACTCODE,
                            LRE_AMT: LRE_AMT,
                            LRE_REF1: LRE_REF1,
                            LRE_REF2: LRE_REF2,
                            LRE_REF3: LRE_REF3,
                            LRE_VCTXT: LRE_VCTXT,
                            LRE_FNNO: LRE_FNNO


                        },
                        async: false
                    }).done(function (response) {

                        alert("Deleted");


                    });




                },
                insertItem: function (item) {




                    var LRE_CONO = item.LRE_CONO;
                    var LRE_DIVI = item.LRE_DIVI;
                    var LRE_RCNO = item.LRE_RCNO;
                    var LRE_CODE = item.LRE_CODE;
                    var LRE_ACTCODE = item.LRE_ACTCODE;
                    var LRE_AMT = item.LRE_AMT;
                    var LRE_REF1 = item.LRE_REF1;
                    var LRE_REF2 = item.LRE_REF2;
                    var LRE_REF3 = item.LRE_REF3;
                    var LRE_VCTXT = item.LRE_VCTXT;
                    var LRE_FNNO = item.LRE_FNNO;





                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        data: {
                            page: "INSERTLRNOEXP",
                            LRE_CONO: LRE_CONO,
                            LRE_DIVI: LRE_DIVI,
                            LRE_RCNO: LRE_RCNO,
                            LRE_CODE: LRE_CODE,
                            LRE_ACTCODE: LRE_ACTCODE,
                            LRE_AMT: LRE_AMT,
                            LRE_REF1: LRE_REF1,
                            LRE_REF2: LRE_REF2,
                            LRE_REF3: LRE_REF3,
                            LRE_VCTXT: LRE_VCTXT,
                            LRE_FNNO: LRE_FNNO

                        },
                        async: false
                    }).done(function (response) {

                        alert("Inserted");



                    });

                }
            },

            fields: [

                {title: "LRE_CONO", name: "LRE_CONO", type: "text", align: "center", editing: true},
                {title: "LRE_DIVI", name: "LRE_DIVI", type: "text", align: "center", editing: true},
                {title: "LRE_RCNO", name: "LRE_RCNO", type: "text", align: "center", editing: true, },
                {title: "LRE_CODE", name: "LRE_CODE", type: "text", align: "center", editing: true, },
                {title: "LRE_ACTCODE", name: "LRE_ACTCODE", type: "text", align: "center", editing: true, },
                {title: "LRE_AMT", name: "LRE_AMT", type: "text", align: "center", editing: true, },
                {title: "LRE_REF1", name: "LRE_REF1", type: "text", align: "center", editing: true, },
                {title: "LRE_REF2", name: "LRE_REF2", type: "text", align: "center", editing: true, },
                {title: "LRE_REF3", name: "LRE_REF3", type: "text", align: "center", editing: true, },
                {title: "LRE_VCTXT", name: "LRE_VCTXT", type: "text", align: "center", editing: true, },
                {title: "LRE_FNNO", name: "LRE_FNNO", type: "text", align: "center", editing: true, },

                {type: "control", deleteButton: true}
            ],

        });


    });

    $(document).ready(function () {

        $(document).ready(function () {
            var auth = "<%out.print(session.getAttribute("username"));%>";
            if (auth !== "PHONGS_PHO") {
                alert("Your not auth this page");
                window.location.href = "./";
                return;
            }
        });


        $("#jsGridBM2").jsGrid({
            width: "30%",
            height: "auto",
            filtering: false,
            inserting: false,
            editing: false,
            sorting: true,
            paging: false,
            autoload: true,
            controller: {
                loadData: function (filter) {
                    var data = $.Deferred();
                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'json',
                        data: {

                            page: "CALLCODE",

                        },
                        async: false
                    }).done(function (response) {
                        data.resolve(response);
                    });
                    return data.promise();
                }
            }, fields: [

                {title: "BM_ACCODE", name: "BM_ACCODE", type: "text", align: "center", width: 10},
                {title: "BM_BANK", name: "BM_BANK", type: "text", align: "center", width: 10},
                {title: "BM_LCODE", name: "BM_LCODE", type: "text", align: "center", width: 10}

            ]


        });

    });
    $("#sBM").click(function (e) {

        var vCONOBM = document.getElementById('vCONOBM').value;
        var vDIVILBM = document.getElementById('vDIVILBM').value;
        var vBANKBM = document.getElementById('vBANKBM').value;
        var vDATEBM = document.getElementById('vDATEBM').value;
        var vTIMEBM = document.getElementById('vTIMEBM').value;
        var vAMOUNTBM = document.getElementById('vAMOUNTBM').value;
        $("#jsGridBM").jsGrid({
            width: "100%",
            height: "auto",
            filtering: true,
            inserting: true,
            editing: true,
            sorting: true,
            paging: true,
            autoload: true,
            controller: {
                loadData: function (filter) {
                    var data = $.Deferred();
                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'json',
                        data: {

                            page: "CALLBM",
                            vCONOBM: vCONOBM,
                            vDIVILBM: vDIVILBM,
                            vBANKBM: vBANKBM,
                            vDATEBM: vDATEBM,
                            vTIMEBM: vTIMEBM,
                            vAMOUNTBM: vAMOUNTBM

                        },
                        async: false
                    }).done(function (response) {
                        data.resolve(response);
                    });
                    return data.promise();
                },
                updateItem: function (item) {




                    var BM_ID = item.BM_ID;
                    var BM_CONO = item.BM_CONO;
                    var BM_DIVI = item.BM_DIVI;
                    var BM_BANK = item.BM_BANK;
                    var BM_ACCODE = item.BM_ACCODE;
                    var BM_DATE = item.BM_DATE;
                    var BM_TIME = item.BM_TIME;
                    var BM_CHQNO = item.BM_CHQNO;
                    var BM_TRANSAC = item.BM_TRANSAC;
                    var BM_CUNO = item.BM_CUNO;

                    var BM_CHANEL = item.BM_CHANEL;
                    var BM_BRANCH = item.BM_BRANCH;
                    var BM_AMOUNT = item.BM_AMOUNT;
                    var BM_DESC = item.BM_DESC;
                    var BM_RCNO = item.BM_RCNO;
                    var BM_TIME1 = item.BM_TIME1;
                    var BM_USER = item.BM_USER;
                    var BM_BKCHARGE = item.BM_BKCHARGE;
                    var BM_OVPAY = item.BM_OVPAY;
                    var BM_CNDN = item.BM_CNDN;

                    var BM_STATUS = item.BM_STATUS;
                    var REF2 = item.REF2;
                    var REF3 = item.REF3;
                    var SENDER = item.SENDER;
                    var BM_REFCU = item.BM_REFCU;
                    var REF4 = item.REF4;
                    var BM_REFCU1 = item.BM_REFCU1;
                    var BM_CUNA = item.BM_CUNA;
                    var BM_FNNO = item.BM_FNNO;
                    var BM_LCODE = item.BM_LCODE;



                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'JSON',
                        data: {
                            page: "UPDATEBM",
                            BM_ID: BM_ID,
                            BM_CONO: BM_CONO,
                            BM_DIVI: BM_DIVI,
                            BM_BANK: BM_BANK,
                            BM_ACCODE: BM_ACCODE,
                            BM_DATE: BM_DATE,
                            BM_TIME: BM_TIME,
                            BM_CHQNO: BM_CHQNO,
                            BM_TRANSAC: BM_TRANSAC,
                            BM_CUNO: BM_CUNO,

                            BM_CHANEL: BM_CHANEL,
                            BM_BRANCH: BM_BRANCH,
                            BM_AMOUNT: BM_AMOUNT,
                            BM_DESC: BM_DESC,
                            BM_RCNO: BM_RCNO,
                            BM_TIME1: BM_TIME1,
                            BM_USER: BM_USER,
                            BM_BKCHARGE: BM_BKCHARGE,
                            BM_OVPAY: BM_OVPAY,
                            BM_CNDN: BM_CNDN,

                            BM_STATUS: BM_STATUS,
                            REF2: REF2,
                            REF3: REF3,
                            SENDER: SENDER,
                            BM_REFCU: BM_REFCU,
                            REF4: REF4,
                            BM_REFCU1: BM_REFCU1,
                            BM_CUNA: BM_CUNA,
                            BM_FNNO: BM_FNNO,
                            BM_LCODE: BM_LCODE

                        },
                        async: false
                    }).done(function (response) {

                        alert("Updated");
                    });
                }, deleteItem: function (item) {

                    var BM_ID = item.BM_ID;
                    var BM_CONO = item.BM_CONO;
                    var BM_DIVI = item.BM_DIVI;
                    var BM_BANK = item.BM_BANK;
                    var BM_ACCODE = item.BM_ACCODE;
                    var BM_DATE = item.BM_DATE;
                    var BM_TIME = item.BM_TIME;
                    var BM_CHQNO = item.BM_CHQNO;
                    var BM_TRANSAC = item.BM_TRANSAC;
                    var BM_CUNO = item.BM_CUNO;

                    var BM_CHANEL = item.BM_CHANEL;
                    var BM_BRANCH = item.BM_BRANCH;
                    var BM_AMOUNT = item.BM_AMOUNT;
                    var BM_DESC = item.BM_DESC;
                    var BM_RCNO = item.BM_RCNO;
                    var BM_TIME1 = item.BM_TIME1;
                    var BM_USER = item.BM_USER;
                    var BM_BKCHARGE = item.BM_BKCHARGE;
                    var BM_OVPAY = item.BM_OVPAY;
                    var BM_CNDN = item.BM_CNDN;

                    var BM_STATUS = item.BM_STATUS;
                    var REF2 = item.REF2;
                    var REF3 = item.REF3;
                    var SENDER = item.SENDER;
                    var BM_REFCU = item.BM_REFCU;
                    var REF4 = item.REF4;
                    var BM_REFCU1 = item.BM_REFCU1;
                    var BM_CUNA = item.BM_CUNA;
                    var BM_FNNO = item.BM_FNNO;
                    var BM_LCODE = item.BM_LCODE;


                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        data: {
                            page: "DELETEBM",
                            BM_ID: BM_ID,
                            BM_CONO: BM_CONO,
                            BM_DIVI: BM_DIVI,
                            BM_BANK: BM_BANK,
                            BM_DATE: BM_DATE,
                            BM_TIME: BM_TIME






                        },
                        async: false
                    }).done(function (response) {

                        alert("Deleted");
                    });
                },
                insertItem: function (item) {


                    var BM_ID = item.BM_ID;
                    var BM_CONO = item.BM_CONO;
                    var BM_DIVI = item.BM_DIVI;
                    var BM_BANK = item.BM_BANK;
                    var BM_ACCODE = item.BM_ACCODE;
                    var BM_DATE = item.BM_DATE;
                    var BM_TIME = item.BM_TIME;
                    var BM_CHQNO = item.BM_CHQNO;
                    var BM_TRANSAC = item.BM_TRANSAC;
                    var BM_CUNO = item.BM_CUNO;

                    var BM_CHANEL = item.BM_CHANEL;
                    var BM_BRANCH = item.BM_BRANCH;
                    var BM_AMOUNT = item.BM_AMOUNT;
                    var BM_DESC = item.BM_DESC;
                    var BM_RCNO = item.BM_RCNO;
                    var BM_TIME1 = item.BM_TIME1;
                    var BM_USER = item.BM_USER;
                    var BM_BKCHARGE = item.BM_BKCHARGE;
                    var BM_OVPAY = item.BM_OVPAY;
                    var BM_CNDN = item.BM_CNDN;

                    var BM_STATUS = item.BM_STATUS;
                    var REF2 = item.REF2;
                    var REF3 = item.REF3;
                    var SENDER = item.SENDER;
                    var BM_REFCU = item.BM_REFCU;
                    var REF4 = item.REF4;
                    var BM_REFCU1 = item.BM_REFCU1;
                    var BM_CUNA = item.BM_CUNA;
                    var BM_FNNO = item.BM_FNNO;
                    var BM_LCODE = item.BM_LCODE;


                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        data: {
                            page: "INSERTBM",
                            BM_ID: BM_ID,
                            BM_CONO: BM_CONO,
                            BM_DIVI: BM_DIVI,
                            BM_BANK: BM_BANK,
                            BM_ACCODE: BM_ACCODE,
                            BM_DATE: BM_DATE,
                            BM_TIME: BM_TIME,
                            BM_CHQNO: BM_CHQNO,
                            BM_TRANSAC: BM_TRANSAC,
                            BM_CUNO: BM_CUNO,

                            BM_CHANEL: BM_CHANEL,
                            BM_BRANCH: BM_BRANCH,
                            BM_AMOUNT: BM_AMOUNT,
                            BM_DESC: BM_DESC,
                            BM_RCNO: BM_RCNO,
                            BM_TIME1: BM_TIME1,
                            BM_USER: BM_USER,
                            BM_BKCHARGE: BM_BKCHARGE,
                            BM_OVPAY: BM_OVPAY,
                            BM_CNDN: BM_CNDN,

                            BM_STATUS: BM_STATUS,
                            REF2: REF2,
                            REF3: REF3,
                            SENDER: SENDER,
                            BM_REFCU: BM_REFCU,
                            REF4: REF4,
                            BM_REFCU1: BM_REFCU1,
                            BM_CUNA: BM_CUNA,
                            BM_FNNO: BM_FNNO,
                            BM_LCODE: BM_LCODE

                        },
                        async: false
                    }).done(function (response) {

                        alert("Inserted");
                    });
                }
            },
            fields: [

                {title: "BM_ID", name: "BM_ID", type: "text", align: "center", editing: true},
                {title: "BM_CONO*****", name: "BM_CONO", type: "text", align: "center", editing: true},
                {title: "BM_DIVI*****", name: "BM_DIVI", type: "text", align: "center", editing: true, },
                {title: "BM_BANK*****", name: "BM_BANK", type: "text", align: "center", editing: true, },
                {title: "BM_ACCODE", name: "BM_ACCODE", type: "text", align: "center", editing: true, },
                {title: "BM_DATE*****", name: "BM_DATE", type: "text", align: "center", editing: true, },
                {title: "BM_TIME*****", name: "BM_TIME", type: "text", align: "center", editing: true, },
                {title: "BM_CHQNO", name: "BM_CHQNO", type: "text", align: "center", editing: true, },
                {title: "BM_TRANSAC", name: "BM_TRANSAC", type: "text", align: "center", editing: true, },
                {title: "BM_CUNO", name: "BM_CUNO", type: "text", align: "center", editing: true, },
                {title: "BM_CHANEL", name: "BM_CHANEL", type: "text", align: "center", editing: true},
                {title: "BM_BRANCH", name: "BM_BRANCH", type: "text", align: "center", editing: true},
                {title: "BM_AMOUNT*****", name: "BM_AMOUNT", type: "text", align: "center", editing: true, },
                {title: "BM_DESC", name: "BM_DESC", type: "text", align: "center", editing: true, },
                {title: "BM_RCNO", name: "BM_RCNO", type: "text", align: "center", editing: true, },
                {title: "BM_TIME1", name: "BM_TIME1", type: "text", align: "center", editing: true, },
                {title: "BM_USER*****", name: "BM_USER", type: "text", align: "center", editing: true, },
                {title: "BM_BKCHARGE", name: "BM_BKCHARGE", type: "text", align: "center", editing: true, },
                {title: "BM_OVPAY", name: "BM_OVPAY", type: "text", align: "center", editing: true, },
                {title: "BM_CNDN", name: "BM_CNDN", type: "text", align: "center", editing: true, },
                {title: "BM_STATUS*****", name: "BM_STATUS", type: "text", align: "center", editing: true},
                {title: "REF2", name: "REF2", type: "text", align: "center", editing: true},
                {title: "REF3", name: "REF3", type: "text", align: "center", editing: true, },
                {title: "SENDER", name: "SENDER", type: "text", align: "center", editing: true, },
                {title: "BM_REFCU", name: "BM_REFCU", type: "text", align: "center", editing: true, },
                {title: "REF4", name: "REF4", type: "text", align: "center", editing: true, },
                {title: "BM_REFCU1", name: "BM_REFCU1", type: "text", align: "center", editing: true, },
                {title: "BM_CUNA", name: "BM_CUNA", type: "text", align: "center", editing: true, },
                {title: "BM_FNNO", name: "BM_FNNO", type: "text", align: "center", editing: true, },
                {title: "BM_LCODE", name: "BM_LCODE", type: "text", align: "center", editing: true, },
                {type: "control", deleteButton: true}
            ],
        });
    });



</script>
