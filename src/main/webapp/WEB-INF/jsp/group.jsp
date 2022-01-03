<%--
  Created by IntelliJ IDEA.
  User: ysoh1205
  Date: 12/28/21
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                                <h2><b>Temp</b></h2>
                                <div class="page-title-subheading">
                                    temp
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md col-xl">
                        <div class="main-card mb-3 card">
                            <div class="card-body">
                                <ul class="nav nav-tabs nav-justified" role="tablist" id="select_list"></ul>
                                <div class="tab-content" id="select_panel"></div>
                                <div class="mb-3 card">
                                    <div class="
										card-header-tab
										card-header-tab-animation
										card-header">
                                        <div class="card-header-title">temp</div>
                                    </div>
                                    <div class="card-body">
                                        <div class="input-group">
                                            <div class="input-group-mb3">
                                                <select class="form-control" id="groupuuid-select" style="width:200px;"></select>
                                            </div>
                                            <div class="input-group-mb3">
                                                <select class="form-control" id="user-select" style="width:200px;"></select>
                                            </div>
                                            <div class="input-group-mb3">
                                                <select class="form-control" id="email-select" style="width:200px;"></select>
                                            </div>
                                            <button
                                                    class="btn btn-outline-secondary"
                                                    type=""
                                                    id="invoke"
                                            >
                                                Invoke
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    let groupUUID = <%=request.getAttribute("groupUUID")%>;
    let user = <%=request.getAttribute("users")%>;
    let email = <%=request.getAttribute("emails")%>;

    function setGroupsSelect() {
        let groupSelect = document.getElementById('groupuuid-select');
        for(let i=0; i<groupUUID.length; i++){
            let option = document.createElement("option");
            option.setAttribute("value", groupUUID[i]);
            option.append(groupUUID[i]);
            groupSelect.appendChild(option);
        }
    }

    function setUsersSelect() {
        let userSelect = document.getElementById('user-select');
        for(let i=0; i<user.length; i++){
            let option = document.createElement("option");
            option.setAttribute("value", user[i]);
            option.append(user[i]);
            userSelect.appendChild(option);
        }
    }

    function setEmailsSelect() {
        let emailSelect = document.getElementById('email-select');
        for(let i=0; i<email.length; i++){
            let option = document.createElement("option");
            option.setAttribute("value", email[i]);
            option.append(email[i]);
            emailSelect.appendChild(option);
        }
    }

    setGroupsSelect();
    setUsersSelect();
    setEmailsSelect();
</script>
</html>
