<%--
  Created by IntelliJ IDEA.
  User: ysoh1205
  Date: 12/28/21
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Language" content="en"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Title</title>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no"
    />
    <meta
            name="description"
            content="This is an example dashboard created using build-in elements and components."
    />
    <meta name="msapplication-tap-highlight" content="no"/>

    <link href="css/main.css", rel="stylesheet"/>
</head>
<body>
<div class="app-container app-theme-white
    body-tabs-shadow
    fixed-sidebar fixed-header">
    <div class="app-header header-shadow">
        <div class="app-header__logo">
            <div class="header__pane ml-auto">
                <div>
                    <img src="image/logo.png">
                </div>
            </div>
        </div>
        <div class="app-header__content">
            <div class="app-header-left">
                <div class="widget-content p-0">
                    <div class="widget-content-wrapper">
                        <a href="export"> | export </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="app-main">
        <div style="margin: auto; width: 1000px">
            <div class="app-main__inner">
                <div class="app-page-title">
                    <div class="page-title-wrapper">
                        <div class="page-title-heading">
                            <div>
                                <h2><b>설정 화면</b></h2>
                                <button id="GROUP_UPDATE">그룹 리스트 업데이트</button>
                                <div class="page-title-subheading">
                                    1. 그룹에 알림 이메일 설정 (추가, 제거)
                                    <p></p>
                                    2. 유저 설정 (추가, 제거)
                                    <p></p>
                                    3. 유저에 이메일 설정 (추가, 제거)
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

<%--                ---------------------- 그룹 to 이메일 변경 ----------------------------------%>
                <div class="row">
                    <div class="col-md col-xl">
                        <div class="main-card mb-3 card">
                            <div class="card-body">
                                <div class="mb-3 card">
                                    <div class="
										card-header-tab
										card-header-tab-animation
										card-header">
                                        <div class="card-header-title">그룹에 알림 이메일 설정 (추가, 제거)</div>
                                    </div>
                                    <div class="card-body">
                                        <div class="input-group">
                                            <div class="input-group-mb3">
                                                <select class="form-control" id="G2EADD-SELECT-GROUP" style="width:200px;"></select>
                                            </div>
                                            <div class="input-group-mb3">
                                                <select class="form-control" id="G2EADD-SELECT-USER" style="width:200px;" onchange="setGS_G2EADD_SELECT_EMAIL(this.value)"></select>
                                            </div>
                                            <div class="input-group-mb3">
                                                <select class="form-control" id="G2EADD-SELECT-EMAIL" style="width:200px;"></select>
                                            </div>
                                            <button
                                                    class="btn btn-outline-secondary"
                                                    type=""
                                                    id="G2EADD"
                                            >
                                                G2E 추가
                                            </button>
                                        </div>
                                        <div class="input-group">
                                            <div class="input-group-mb3">
                                                <select class="form-control" id="G2EDEL-SELECT-GROUP" style="width:200px;" onchange="setGS_G2EDEL_SELECT_EMAIL(this.value)"></select>
                                            </div>
                                            <div class="input-group-mb3">
                                                <select class="form-control" id="G2EDEL-SELECT-EMAIL" style="width:200px;"></select>
                                            </div>
                                            <button
                                                    class="btn btn-outline-secondary"
                                                    type=""
                                                    id="G2EDEL"
                                            >
                                                G2E 제거
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

<%--                ---------------------- 유저 DB 변경 ----------------------------------%>

                <div class="row">
                    <div class="col-md col-xl">
                        <div class="main-card mb-3 card">
                            <div class="card-body">
                                <div class="mb-3 card">
                                    <div class="
										card-header-tab
										card-header-tab-animation
										card-header">
                                        <div class="card-header-title">유저 설정 (추가, 제거)</div>
                                    </div>
                                    <div class="input-group">
                                        <div class="input-group-mb3">
                                            <input type="text" id="USERADD-INPUT-USER" style="width:200px;">
                                        </div>
                                        <button
                                                class="btn btn-outline-secondary"
                                                type=""
                                                id="USERADD"
                                        >
                                            유저 추가
                                        </button>
                                    </div>
                                    <div class="input-group">
                                        <div class="input-group-mb3">
                                            <select class="form-control" id="USERDEL-SELECT-USER" style="width:200px;"></select>
                                        </div>
                                        <button
                                                class="btn btn-outline-secondary"
                                                type=""
                                                id="USERDEL"
                                        >
                                            유저 제거
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

<%--                ---------------------- 유저 2 이메일 변경 ----------------------------------%>

                <div class="row">
                    <div class="col-md col-xl">
                        <div class="main-card mb-3 card">
                            <div class="card-body">
                                <div class="mb-3 card">
                                    <div class="
										card-header-tab
										card-header-tab-animation
										card-header">
                                        <div class="card-header-title">유저 이메일 설정 (추가, 제거)</div>
                                    </div>
                                    <div class="input-group">
                                        <div class="input-group-mb3">
                                            <select class="form-control" id="U2EADD-SELECT-USER" style="width:200px;"></select>
                                        </div>
                                        <div class="input-group-mb3">
                                            <input type="text" id="U2EADD-INPUT-EMAIL" style="width:200px;">
                                        </div>
                                        <button
                                                class="btn btn-outline-secondary"
                                                type=""
                                                id="U2EADD"
                                        >
                                            U2E 추가
                                        </button>
                                    </div>
                                    <div class="input-group">
                                        <div class="input-group-mb3">
                                            <select class="form-control" id="U2EDEL-SELECT-USER" style="width:200px;" onchange="setGS_U2EDEL_SELECT_EMAIL(this.value)"></select>
                                        </div>
                                        <div class="input-group-mb3">
                                            <select class="form-control" id="U2EDEL-SELECT-EMAIL" style="width:200px;"></select>
                                        </div>
                                        <button
                                                class="btn btn-outline-secondary"
                                                type=""
                                                id="U2EDEL"
                                        >
                                            U2E 제거
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
<%--                ---------------------- 끝 ----------------------------------%>
            </div>
        </div>
    </div>
</div>
</body>

<%--ajax--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<%--select box option script--%>
<script>
    // JSON array of tuple [String,String] : [ group_name, group_id ]
    let groupUUID = <%=request.getAttribute("groupUUID")%>;

    // JSON array of tuple [String,int] : [ user_name, user_code ]
    let user = <%=request.getAttribute("users")%>;

    // JSON array of tuple [int, String] : [ user_code, email_addr ]
    let email = <%=request.getAttribute("emails")%>;

    // JSON array of tuple [String, String] : [ group_id, email_addr ]
    let G2E = <%=request.getAttribute("group2Email")%>;


    // User
    function setGS_USERDEL_SELECT_USER() {
        let gs = document.getElementById('USERDEL-SELECT-USER');
        for(i=0; i<user.length; i++) {
            let option = document.createElement("option");
            option.setAttribute("value", user[i].user_code);
            option.append(user[i].user_name);
            gs.appendChild(option);
        }
    }




    // U2E
    function setGS_U2EADD_SELECT_USER() {
        let gs = document.getElementById('U2EADD-SELECT-USER');
        for(i=0; i<user.length; i++) {
            let option = document.createElement("option");
            option.setAttribute("value", user[i].user_code);
            option.append(user[i].user_name);
            gs.appendChild(option);
        }
    }


    function setGS_U2EDEL_SELECT_USER() {
        let gs = document.getElementById('U2EDEL-SELECT-USER');
        for(i=0; i<user.length; i++) {
            let option = document.createElement("option");
            option.setAttribute("value", user[i].user_code);
            option.append(user[i].user_name);
            gs.appendChild(option);
        }
    }
    function setGS_U2EDEL_SELECT_EMAIL(userVal) {

        let gs = document.getElementById('U2EDEL-SELECT-EMAIL');
        gs.options.length=0;
        // let userVal = $("#U2EDEL-SELECT-USER").val();
        let temp = 0;
        for(i=0; i<email.length; i++) {
            if(email[i].user_code == userVal) {
                let option = document.createElement("option");
                option.setAttribute("value", email[i].email_addr);
                option.append(email[i].email_addr);
                gs.appendChild(option);
            }
        }
    }



    // G2E
    function setGS_G2EADD_SELECT_GROUP() {
        let gs = document.getElementById('G2EADD-SELECT-GROUP');
        for(i=0; i<groupUUID.length; i++) {
            let option = document.createElement("option");
            option.setAttribute("value", groupUUID[i].group_guid);
            option.append(groupUUID[i].group_name);
            gs.appendChild(option);
        }
    }
    function setGS_G2EADD_SELECT_USER() {
        let gs = document.getElementById('G2EADD-SELECT-USER');
        for(i=0; i<user.length; i++) {
            let option = document.createElement("option");
            option.setAttribute("value", user[i].user_code);
            option.append(user[i].user_name);
            gs.appendChild(option);
        }
    }
    function setGS_G2EADD_SELECT_EMAIL(userVal) {
        let gs = document.getElementById('G2EADD-SELECT-EMAIL');
        gs.options.length=0;
        // let userVal = $("#U2EDEL-SELECT-USER").val();
        let temp = 0;
        for(i=0; i<email.length; i++) {
            if(email[i].user_code == userVal) {
                let option = document.createElement("option");
                option.setAttribute("value", email[i].email_addr);
                option.append(email[i].email_addr);
                gs.appendChild(option);
            }
        }
    }



    function setGS_G2EDEL_SELECT_GROUP() {
        let gs = document.getElementById('G2EDEL-SELECT-GROUP');
        for(i=0; i<groupUUID.length; i++) {
            let option = document.createElement("option");
            option.setAttribute("value", groupUUID[i].group_guid);
            option.append(groupUUID[i].group_name);
            gs.appendChild(option);
        }
    }
    function setGS_G2EDEL_SELECT_EMAIL(group_guid) {
        let gs = document.getElementById('G2EDEL-SELECT-EMAIL')
        gs.options.length=0
        for(i=0; i<G2E.length; i++) {
            if(G2E[i].group_guid == group_guid) {
                let option = document.createElement("option")
                option.setAttribute("value", G2E[i].email_addr)
                option.append(G2E[i].email_addr)
                gs.append(option)
            }
        }
    }

    // G2E

    setGS_USERDEL_SELECT_USER()
    setGS_U2EADD_SELECT_USER()
    setGS_U2EDEL_SELECT_USER()
    setGS_U2EDEL_SELECT_EMAIL($("#U2EDEL-SELECT-USER").val())
    setGS_G2EADD_SELECT_GROUP()
    setGS_G2EADD_SELECT_USER()
    setGS_G2EADD_SELECT_EMAIL($("#G2EADD-SELECT-USER").val())
    setGS_G2EDEL_SELECT_GROUP()
    setGS_G2EDEL_SELECT_EMAIL($("#G2EDEL-SELECT-GROUP").val())

</script>




<%--button script--%>
<script>
    // USER ADD
    $('#USERADD').click(function() {
        let id = $("#USERADD-INPUT-USER").val();
        $.ajax({
            type: "POST",
            url: "config/user-add",
            data: {
                id: id,
            },
            success: function(result) {
                switch (result)
                {
                    case 0:
                        alert("success")
                        location.href = "config"
                        break
                    case 1:
                        alert("Duplicate ID...")
                        break
                    default:
                        alert("SOMETHING WRONG")
                        location.href = "error"
                }
            },
            error:function(request,status,error) {
                alert(request)
                alert(status)
                alert(error)
                location.replace("/error")
            }
        });
    })

    // USER DELETE
    $('#USERDEL').click(function() {
        let id = $("#USERDEL-SELECT-USER").val();
        $.ajax({
            type: "POST",
            url: "config/user-delete",
            data: {
                id: id,
            },
            success: function(result) {
                switch (result)
                {
                    case 0:
                        alert("success")
                        location.href = "config"
                        break
                    case 1:
                        alert("NO SUCH ID....")
                        break
                    default:
                        alert("SOMETHING WRONG")
                        location.href = "error"
                }
            },
            error:function(request,status,error) {
                location.replace("/error")
            }
        });
    })


    // USER 2 EMAIL ADD
    $('#U2EADD').click(function() {
        let id = $("#U2EADD-SELECT-USER").val();
        let email = $("#U2EADD-INPUT-EMAIL").val();
        $.ajax({
            type: "POST",
            url: "config/u2e-add",
            data: {
                id: id,
                email: email,
            },
            success: function(result) {
                switch (result)
                {
                    case 0:
                        alert("success")
                        location.href = "config"
                        break
                    case 1:
                        alert("NO SUCH ID")
                        break
                    case 2:
                        alert("DUPLICATE EMAIL")
                        break
                    default:
                        alert("SOMETHING WRONG")
                        location.href = "error"
                }
            },
            error:function(request,status,error) {
                location.replace("/error")
            }
        });
    })

    // USER 2 EMAIL DELETE
    $('#U2EDEL').click(function() {
        let id = $("#U2EDEL-SELECT-USER").val();
        let email = $("#U2EDEL-SELECT-EMAIL").val();
        $.ajax({
            type: "POST",
            url: "config/u2e-delete",
            data: {
                id: id,
                email: email,
            },
            success: function(result) {
                switch (result)
                {
                    case 0:
                        alert("success")
                        location.href = "config"
                        break
                    case 1:
                        alert("NO SUCH ID")
                        break
                    case 2:
                        alert("NO SUCH EMAIL FOR THE USER")
                        break
                    default:
                        alert("SOMETHING WRONG")
                        location.href = "error"
                }
            },
            error:function(request,status,error) {
                location.replace("/error")
            }
        });
    })

    // GROUP 2 EMAIL ADD
    $('#G2EADD').click(function() {
        let group = $("#G2EADD-SELECT-GROUP").val();
        let email = $("#G2EADD-SELECT-EMAIL").val();
        $.ajax({
            type: "POST",
            url: "config/g2e-add",
            data: {
                group: group,
                email: email,
            },
            success: function(result) {
                switch (result)
                {
                    case 0:
                        alert("success")
                        location.href = "config"
                        break
                    case 1:
                        alert("NO SUCH GROUP")
                        break
                    case 2:
                        alert("NO SUCH EMAIL")
                        break
                    case 3:
                        alert("ALREADY EXIST EMAIL FOR THE GROUP")
                        break
                    default:
                        alert("SOMETHING WRONG")
                        location.href = "error"
                }
            },
            error:function(request,status,error) {
                location.replace("/error")
            }
        });
    })

    // GROUP 2 EMAIL DELETE
    $('#G2EDEL').click(function() {
        let group = $("#G2EDEL-SELECT-GROUP").val();
        let email = $("#G2EDEL-SELECT-EMAIL").val();
        $.ajax({
            type: "POST",
            url: "config/g2e-delete",
            data: {
                group: group,
                email: email,
            },
            success: function(result) {
                switch (result)
                {
                    case 0:
                        alert("success")
                        location.href = "config"
                        break
                    case 1:
                        alert("NO SUCH GROUP")
                        break
                    case 2:
                        alert("NO SUCH EMAIL")
                        break
                    case 3:
                        alert("NO SUCH G2E")
                        break
                    default:
                        alert("SOMETHING WRONG")
                        location.href = "error"
                }
            },
            error:function(request,status,error) {
                location.replace("/error")
            }
        });
    })

    // GROUP 2 EMAIL DELETE
    $('#GROUP_UPDATE').click(function() {
        $.ajax({
            type: "POST",
            url: "config/group-update",
            success: function(result) {
                switch (result)
                {
                    case 0:
                        alert("success")
                        location.href = "config"
                        break
                    case 1:
                        alert("Server Exception")
                        location.href = "error"
                        break
                    default:
                        alert("SOMETHING WRONG")
                        location.href = "error"
                }
            },
            error:function(request,status,error) {
                location.replace("/error")
            }
        });
    })
</script>
</html>
