<%--
  Created by IntelliJ IDEA.
  User: ysoh1205
  Date: 12/17/21
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
                        <a href="config"> | group </a>
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
                                <h2><b>Key Component Export</b></h2>
                                <div class="page-title-subheading">
                                    Fortanix SDKMS 내 추출 가능한 키를 분할하여 지정된 Custodian 에게 이메일을 전송합니다.
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
                                        <div class="card-header-title">분할 추출 하고자 하는 키를 선택하고, 추출 버튼을 클릭합니다. 그룹을 먼저 선택하면 해당 그룹 내 키 목록을 볼 수 있습니다.</div>
                                    </div>
                                    <div style="color:red;padding-left: 20px;"><br>"Export" Key Operation permission 이 부여되지 않은 키는 목록에 나타나지 않습니다.</div>
                                    <div style="color:red;padding-left: 20px;"><br>추출된 Key Component 는 새당 그룹에 설정된 Custodian 정책에 따라 이메일로 발송됩니다.</div>
                                    <form>
                                        <div class="card-body">
                                            <div class="input-group">
                                                <div class="input-group-mb3">
                                                    <select class="form-control" id="group-select" style="width:300px;" onchange="setSobjectSelect()"></select>
                                                </div>
                                                <div class="input-group-mb3">
                                                    <select class="form-control" id="sobjects-select" style="width:300px;"></select>
                                                </div>
                                                <input type="button" id="invoke" class="btn btn-outline-secondary" value="Invoke">
                                            </div>
                                        </div>
                                    </form>

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
    console.log(`<%=request.getAttribute("group-object_Pair")%>`);
    let jsonObject = <%=request.getAttribute("group-object_Pair")%>;
    let groupsName = <%=request.getAttribute("groups")%>;



    function setSobjectSelect() {
        let sobjectSelect = document.getElementById('sobjects-select');
        let groupSelectValue = document.getElementById('group-select').value;
        let sobjectArray = jsonObject[groupSelectValue];

        while(sobjectSelect.hasChildNodes()){
            sobjectSelect.removeChild(sobjectSelect.firstChild);
        }

        for(let i=0; i<sobjectArray.length; i++) {
            let option = document.createElement("option");
            option.setAttribute("value", sobjectArray[i]);
            option.append(sobjectArray[i]);
            sobjectSelect.appendChild(option);
        }
    }

    function setGroupsSelect() {
        let groupSelect = document.getElementById('group-select');
        for(let i=0; i<groupsName.length; i++){
            let option = document.createElement("option");
            option.setAttribute("value", groupsName[i]);
            option.append(groupsName[i]);
            groupSelect.appendChild(option);
        }
    }
    setGroupsSelect();
    setSobjectSelect();
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(function () {
        $('#invoke').click(function () {
            let group = $('#group-select').val()
            let sobject = $('#sobjects-select').val()

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}",
                data:{
                    groupName: group,
                    sobjectName: sobject
                },
                success: function (result) {
                    switch(result) {
                        case 0:
                            alert("received")
                            location.reload()
                            break
                        case 1:
                            alert("invalid group or plugin")
                            location.reload()
                            break
                        case 2:
                            alert("no custodians in this group")
                            location.reload()
                            break
                        default:
                            alert("default")
                            break
                    }
                },
                error: function (result){
                    alert("export failed.")
                }
            })
        })
    })

</script>
</html>
