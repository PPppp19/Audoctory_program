<%-- 
    Document   : ADDCHECK
    Created on : Aug 29, 2023, 4:50:59 PM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <style>

        .center {
            margin: auto;
            width: 50%;
            padding: 10px;
        }


    </style>
    <body>
        <form  method="POST" enctype="multipart/form-data" acceptcharset="UTF-8" id="MyForm" >
            <div class="center">
                <div class="center">
                    <label style=" color: #000000;">เพิ่มลูกค้า เช็ค : </label>

                    <label style=" color: #000000;">CUSTOMER CODE : </label>
                    <input type="text" class="form-control text-center"  name="vCusno" id="vCusno"  style=" color: #b356b5;"/>
                    <hr>
                    <label style=" color: #000000;">BRANCH : </label>
                    <input type="text"class="form-control text-center"   name="vBranch" id="vBranch"  style=" color: #b356b5;"/>
                    <hr>
                    <label style=" color: #000000;">BANK : </label>
                    <input type="text"class="form-control text-center"   name="vBank" id="vBank"  style=" color: #b356b5;"/>
                    <hr>
                    <hr>
                    <label style=" color: #000000;">DATE : </label>
                    <input type="text"class="form-control text-center" readonly="true"   name="vDATE" id="vDATE"  style=" color: #b356b5;"/>
                    <hr>

                    <button class="btn btn-success"  id="sSend" name="sSend" type="text">Request CHECK</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-danger"  id="REPORT" name="REPORT" value="sPost" type="text">POST CHECK</button>
                    <br>
                    <br> <br>
                    <hr>
                </div>
            </div>
            <div id="jsGridREQCUSCHK"></div>
        </form>
    </body>
</html>

<script>


    $(document).ready(function () {

        var btnPost = document.getElementById('REPORT');
        btnPost.style.visibility = 'hidden';

        $("#jsGridREQCUSCHK").jsGrid({
            width: "100%",
            height: "auto",
            filtering: false,
            inserting: false,
            editing: false,
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
                            page: "GETREQCUSCHK"
                        },
                        async: false
                    }).done(function (response) {
                        data.resolve(response);
                    });
                    return data.promise();
                }

            },
            rowClick: function (args) {
                
                 var username = "<%out.print(session.getAttribute("username"));%>";

if(args.item.STATUS === "00"){
                document.getElementById("vCusno").value = args.item.CUSTOMER_NO;
                document.getElementById("vBranch").value = args.item.BRANCH_NAME;
                document.getElementById("vDATE").value = args.item.REQ_DATE;
                document.getElementById("vBank").value = args.item.BANK;

        if (username === "NITTAYA_KOM" || username === "PRANEE_KHA" || username === "M3SRVICT" || username === "PHONGS_PHO" )
        
                var btnPost = document.getElementById('REPORT');
                btnPost.style.visibility = 'visible';
                }
                else {
                    
                       var btnPost = document.getElementById('REPORT');
                btnPost.style.visibility = 'hidden';
                    
                }
            },
            fields: [

                {title: "CONO", name: "CONO", type: "text", align: "center", editing: false},
                {title: "DIVI", name: "DIVI", type: "text", align: "center", editing: false},
                {title: "CUSTOMER_NO", name: "CUSTOMER_NO", type: "text", align: "center", editing: false, },
                {title: "BRANCH", name: "BRANCH_NAME", type: "text", align: "center", editing: false, },
                {title: "BANK", name: "BANK", type: "text", align: "center", editing: false, },
                {title: "REQUESTER", name: "REQ_USER", type: "text", align: "center", editing: false, },
                {title: "REQUEST DATE", name: "REQ_DATE", type: "text", align: "center", editing: false, },
                {title: "STATUS", name: "STATUS", type: "text", align: "center", editing: false,
                    itemTemplate: function (value, item) {
                        if (value === "00") {
                            return "กำลังดำเนินการ...";
                        } else if (value === "22") {
                            return "Completed";
                        }
                    }},
                {title: "POSTED USER", name: "OTHER", type: "text", align: "center", editing: false, },
            ],
        });




    });


    $("#sSend").click(function (e) {

        var customer_code = document.getElementById("vCusno").value;
        var branch = document.getElementById("vBranch").value;
        var bank = document.getElementById("vBank").value;


        var cono = <%out.print(session.getAttribute("cono"));%>
        var divi = <%out.print(session.getAttribute("divi"));%>
        var username = "<%out.print(session.getAttribute("username"));%>"

        $.ajax({
            url: "./Sync",
            type: "GET",
            dataType: "text",
            data: {
                page: "ADDREQCUSCHK",
                cono: cono,
                divi: divi,
                cusno: customer_code,
                branch: branch,
                user: username,
                bank: bank

            },
            success: function () {
                alert("Requested : " + customer_code + "  :  " + branch);
                $("#jsGridREQCUSCHK").jsGrid("loadData");

            }
        });


    });



    $("#REPORT").click(function (e) {


        var customer_code = document.getElementById("vCusno").value;
        var branch = document.getElementById("vBranch").value;
        var date = document.getElementById("vDATE").value;

        var cono = <%out.print(session.getAttribute("cono"));%>
        var divi = <%out.print(session.getAttribute("divi"));%>
        var username = "<%out.print(session.getAttribute("username"));%>"

        var form = $('#MyForm')[0];
        // Create an FormData object 
        var data = new FormData(form);


        if (confirm("Confirm to Add Customer : '" + $("#vCusno").val() + "' : ' " + $("#vBranch").val() + "'") === true) {

            data.append("REPORT", "sPost");
            
         
        console.log(data);

            $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "./Report1",
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                async: true,
                success: function (data) {

                    alert("สำเร็จ");

                },
                error: function (e) {
                    alert("ล้มเหลว");
                }

            });

            
             $.ajax({
             url: "./Sync",
             type: "GET",
             data: {
             page: "POSTREQCUSCHK",
             cono: cono,
             divi: divi,
             cusno: customer_code,
             branch: branch,
             user: username,
             date: date
             
             },
             success: function () {
             alert("ADDED");
             $("#jsGridREQCUSCHK").jsGrid("loadData");
             
             
             }
             });
             
        } else
        {
        }


    });

</script>
