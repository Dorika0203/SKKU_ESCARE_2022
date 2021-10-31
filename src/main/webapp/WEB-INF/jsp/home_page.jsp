<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Language" content="en"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Demo Bank</title>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no"
    />
    <meta
            name="description"
            content="This is an example dashboard created using build-in elements and components."
    />
    <meta name="msapplication-tap-highlight" content="no"/>

    <link href="\css\main.css" rel="stylesheet"/>
    <link rel="shortcut icon" type="image⁄x-icon" href="\image\logo-only.png"/>
</head>
<body>
<div
        class="
        app-container app-theme-white
        body-tabs-shadow
        fixed-sidebar fixed-header
      "
>
    <div class="app-header header-shadow">
        <div class="app-header__content">
            <div class="app-header-right">
                <div class="header-btn-lg pr-0">
                    <div class="widget-content p-0">
                        <div class="widget-content-wrapper">
                            Please login first

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="app-main">
        <div style="margin: auto; width: 1000px;">
            <div class="app-main__inner">
                <div class="row">
                    <div class="col-md-10 col-lg-5">
                        <div class="main-card mb-3 card">
                            <div class="card-body">
                                <h5 class="card-title">Home Page</h5>

                                <ul class="nav nav-tabs nav-justified" role="tablist">
                                    <li class="nav-item">
                                        <a
                                                data-toggle="tab"
                                                href="#tab-home-0"
                                                class="active nav-link"
                                        >Sign up</a
                                        >
                                    </li>
                                    <li class="nav-item">
                                        <a data-toggle="tab" href="#tab-home-1" class="nav-link"
                                        >Sign in</a
                                        >
                                    </li>
                                </ul>

                                <div class="tab-content">
                                    <div
                                            class="tab-pane fade active show"
                                            id="tab-home-0"
                                            role="tabpanel"
                                    >
                                        <div class="mb-3 card">
                                            <div
                                                    class="
                              card-header-tab
                              card-header-tab-animation
                              card-header
                            "
                                            >
                                                <div class="card-header-title">Pleas join us</div>
                                            </div>
                                            <form id="sign-up-form" class="box" action="signup" method="post">
                                                <div class="card-body">
                                                    <div class="input-group">
                                                        <label class="label">EMAIL</label>
                                                        <div class="input-group mb-3">
                                                            <input
                                                                    type="email"
                                                                    class="form-control"
                                                                    placeholder="e.g. alex@example.com"
                                                                    name="ID"
                                                            />
                                                        </div>

                                                        <label class="label">PASSWORD</label>
                                                        <div class="input-group mb-3">
                                                            <input
                                                                    type="password"
                                                                    class="form-control"
                                                                    placeholder="********"
                                                                    name="PW"
                                                            />
                                                        </div>

                                                        <label class="label">NAME</label>
                                                        <div class="input-group mb-3">
                                                            <input
                                                                    type="text"
                                                                    class="form-control"
                                                                    placeholder="Doyeol"
                                                                    name="firstName"
                                                            />
                                                            <input
                                                                    type="text"
                                                                    class="form-control"
                                                                    placeholder="Kim"
                                                                    name="lastName"
                                                            />
                                                        </div>

                                                        <label class="label">PHONE NUMBER</label>
                                                        <div class="input-group mb-3">
                                                            <input
                                                                    type="text"
                                                                    class="form-control"
                                                                    pattern="010-[0-9]{4}-[0-9]{4}"
                                                                    placeholder="010-1234-5678"
                                                                    name="phoneNumber"
                                                            />
                                                        </div>
                                                    </div>

                                                    <button
                                                            type="submit"
                                                            class="
                                  btn-shadow
                                  p-1
                                  btn btn-primary btn-sm
                                  show-toastr-example
                                "
                                                            id="sign-in"
                                                    >
                                                        Join
                                                    </button>
                                                </div>
                                        </div>

                                        </form>

                                    </div>

                                    <div
                                            class="tab-pane fade"
                                            id="tab-home-1"
                                            role="tabpanel"
                                    >
                                        <div class="mb-3 card">
                                            <div
                                                    class="
                            card-header-tab
                            card-header-tab-animation
                            card-header
                          "
                                            >
                                                <div class="card-header-title">Welcome!</div>
                                            </div>
                                            <form id="sign-in-form" class="box" action="signin" method="post">
                                                <div class="card-body">
                                                    <div class="input-group">
                                                        <label class="label">EMAIL</label>
                                                        <div class="input-group mb-3">
                                                            <input
                                                                    type="email"
                                                                    class="form-control"
                                                                    placeholder="e.g. alex@example.com"
                                                                    name="ID_IN"
                                                            />
                                                        </div>

                                                        <label class="label">PASSWORD</label>
                                                        <div class="input-group mb-3">
                                                            <input
                                                                    type="password"
                                                                    class="form-control"
                                                                    placeholder="********"
                                                                    name="PW_IN"
                                                            />
                                                        </div>

                                                    </div>

                                                    <button
                                                            type="submit"
                                                            class="
                                btn-shadow
                                p-1
                                btn btn-primary btn-sm
                                show-toastr-example
                              "
                                                            id="login"
                                                    >
                                                        Login
                                                    </button>
                                                </div>
                                        </div>

                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-10 col-lg-5">
                        <img
                                class="is-square"
                                src="image/logo-md.png"
                                alt="home_page_img"
                        />

                    </div>

                </div>

            </div>
        </div>
    </div>
    <script
            src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"
    ></script>

    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
            crossorigin="anonymous"
    ></script>

    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
            integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
            crossorigin="anonymous"
    ></script>
</body>
</html>
