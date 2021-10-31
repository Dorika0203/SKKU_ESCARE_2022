<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="naver-site-verification" content="a8f714bdbbf08acd830048b83dcdb1078a4aeb9b"/>
    <meta name="description" content="인터넷뱅킹, 계좌조회, 이체, 대출, 외화 환전, 공과금납부, 상품신규, 사고신고 안내">
    <meta name="keywords" content="우리은행, 우리은행 인터넷뱅킹, 계좌조회, 이체, 대출, 외화 환전, 공과금납부, 상품신규, 사고신고 안내">
    <meta name="robots" content="index,follow">
    <title>우리은행</title>
    <link rel="canonical" href="http://www.wooribank.com">
    <link rel="stylesheet" href="https://simg.wooribank.com/css/base.css?1547196841000" type="text/css"/>

    <link rel="stylesheet" href="https://simg.wooribank.com/css/intro_new.css?1616601957000" type="text/css"/>

    <!--[if gt IE 9]><!-->
    <link rel="stylesheet" href="https://simg.wooribank.com/css/intro.trans.css?1363868777000" type="text/css"/>


    <link rel="stylesheet" href="https://simg.wooribank.com/css/import.css?1572858903000" type="text/css"/>


    <!--<![endif]-->
    <!--[if lt IE 7]>
    <link rel="stylesheet" href="https://simg.wooribank.com/css/intro.ie6.css?1364207957000" type="text/css"/>

    <![endif]-->
    <script type="text/javascript" src="https://simg.wooribank.com/js/com/jquery-1.7.1.min.js?1363353990000"></script>

    <script type="text/javascript"
            src="https://simg.wooribank.com/js/com/jquery-ui-1.10.2.custom.min.js?1363945599000"></script>

    <script type="text/javascript" src="https://simg.wooribank.com/js/com/jquery.bgpos.min.js?1363353990000"></script>

    <script type="text/javascript" src="https://simg.wooribank.com/js/com/wbui.min.js?1427870245000"></script>

    <!--[if lt IE 7]>
    <![endif]-->
    <script type="text/javascript" src="https://simg.wooribank.com/js/com/wbui.intro.js?1481281137000"></script>

</head>
<!--[if lt IE 7]>
<body class="intro msie ie6 lt-ie9 lt-ie8 lt-ie7 lt-css3"><![endif]-->
<!--[if IE 7]>
<body class="intro msie ie7 lt-ie9 lt-ie8 lt-css3"><![endif]-->
<!--[if IE 8]>
<body class="intro msie ie8 lt-ie9 lt-css3"><![endif]-->
<!--[if IE 9]>
<body class="intro msie ie9 css3"><![endif]-->
<!--[if gt IE 9]><!-->
<body class="intro css3 transition">
<!--<![endif]-->
<div id="accNav">
    <p><a href="#container" class="skip">본문으로 바로가기</a></p>
    <p><a href="#introFoot" class="skip">하단 전체메뉴로 바로가기</a></p>
    <p><a>웹접근성 이용안내 바로가기</a></p>
</div>
<div id="wrap">

    <!-- container -->
    <div id="container">
        <!-- content -->
        <div id="content">


            <script type="text/javascript" src="https://simg.wooribank.com/js/com/common.js?1525668543000"></script>

            <link rel="stylesheet" href="https://simg.wooribank.com/css/onetouch/com/lib/swiper.min.css?1480604402000"
                  type="text/css"/>

            <script type="text/javascript"
                    src="https://simg.wooribank.com/js/onetouch/com/lib/swiper.jquery.min.js?1465297202000"></script>

            <script type="text/javascript" src="https://simg.wooribank.com/js/com/search_ui.js?1362313617000"></script>

            <script type="text/javascript"
                    src="https://simg.wooribank.com/js/pot/fx/w.jquery.slides.min.js?1491901202000"></script>

            <script type="text/javascript">

                function goBoardDetailLink(pageURL, boardId, articleId) {
                    document.boardViewFrm.action = pageURL;
                    document.boardViewFrm.BOARD_ID.value = boardId;
                    document.boardViewFrm.ARTICLE_ID.value = articleId;
                    document.boardViewFrm.submit();
                }

                function showLogoutWarning(dis) {
                    wbUI.openPopup('/pib/jcc?withyou=ps&__ID=c018195' + '&dis=' + dis);
                }

                function showLogoutWarningCd(dis) {
                    wbUI.openPopup('/pib/jcc?withyou=ps&__ID=c022255' + '&dis=' + dis);
                }


                function chkCardLinkPop(dis) {
                    window.open('https://spib.wooribank.com/pib/Dream?withyou=CMCOM0429' + '&dis=' + dis, "_blank")
                }

                var _moveA = false;

                $(function () {

                    $(".site.sitemap_icon .btn-popup.ui-set-btn-popup").click(function () {
                        _moveA = true;
                    });

                    $('#search_upper_btn').click(function () {
                        $('.search_2').stop().slideDown();

                        var maskHeight = $(document).height();
                        var maskWidth = $(window).width();
                        var scrollPosition = 0;

                        $('body').append('<div id="layermask"></div>');
                        $('#layermask').css({
                            'width': maskWidth,
                            'height': maskHeight,
                            'position': 'fixed',
                            'left': '0',
                            'top': '0',
                            'background': '#000',
                            'z-index': '99'
                        });
                        $('#layermask').fadeTo("", 0.5);
                        $('body').css('position', 'relative');

                        $(window).resize(function () {
                            var maskHeight = $(document).height();
                            var maskWidth = $(window).width();
                            $('#layermask').css({'width': maskWidth, 'height': maskHeight});
                        });
                    });

                    $('#search_close').click(function () {
                        $('.search_2').stop().slideUp();
                        $("#layermask").hide();

                    });

                    $("#slides01").slidesjs({
                        play: {
                            active: true, //슬라이드여부
                            auto: true, //자동슬라이드
                            interval: 10000, //슬라이드 속도
                            swap: true,
                            effect: "fade"
                            //		  pauseOnHover:true
                        },
                        pagination: {
                            effect: "fade"
                        },
                        effect: {
                            fade: {
                                speed: 800,
                                cressfade: true
                            }
                        },
                        callback: {
                            loaded: function () {
                            }
                        }
                    });
                    $("#slides01 .slidesjs-container").prepend($("#slides01 .slidesjs-navigation"));
                    $("#slides01 .slidesjs-container").prepend($("#slides01 .slidesjs-play, #slides01 .slidesjs-stop"));

                    //금융상품 마우스오버
                    var link;
                    $('.cont_menu_img > li > a,.cont_menu_card_img > li > a').hover(
                        function () {
                            link = $(this).find('img').prop('src');
                            link = link.replace('.png', '_over.png');
                            $(this).find('img').prop('src', link);
                            $(this).parents('li').addClass('over');
                            $(this).css({'background': '#3a8dfd', 'border-radius': '50%', 'color': '#fff'});

                        }, function () {
                            link = $(this).find('img').prop('src');
                            link = link.replace('_over.png', '.png');
                            $(this).find('img').prop('src', link);
                            $(this).parents('li').removeClass('over');
                            $(this).css({'background': 'transparent', 'color': '#000'});
                        }
                    );

                    //빠른메뉴 이벤트
                    $('li.q_btn > a').on('click', function () {
                        $(this).parents('li').toggleClass('show');
                    });
                    $('.q_menu_list > li > a').on('click', function () {
                        $('.q_menu_list > li > a').removeClass('font-bold');
                        $(this).addClass('font-bold');
                    });


                });
            </script>
            <style type="text/css">
                * {
                    font-family: 'NotoSans', 'Noto Sans KR', sans-serif;
                    -webkit-font-smoothing: antialiased;
                    -moz-osx-font-smoothing: antialiased;
                }
            </style>

            <div id="introHeader" class="re_new">
                <div class="content h66" name="crmArea" id="introCrm_01">
                    <h1 class="absolute"><img alt="우리은행" src="https://simg.wooribank.com/img/intro/header/h1_01.png"/>
                    </h1>


                    <div class="tit-0 absolute js-display-hover">

                        <a href="#none" class="gnb-member-bt js-display-hover-trigger renew_login"><span>로그인</span></a>
                        <div class="js-display-hover-area level2">
                            <div class="menu">
			<span class="list">
				<a class="left" href="signUpPage">개인</a>
				<a class="right">기업</a>
			</span>
                            </div>
                        </div>

                    </div>
                    <div class="tit-8 absolute js-display-hover">
                        <span class="js-display-hover-trigger renew_certification"><a href="#none" class="renew_cert">인증센터</a></span>
                        <div class="js-display-hover-area level2">
                            <div class="menu">
						<span class="list">
							<a class="left">개인</a>
							<a class="right">기업</a>
						</span>
                            </div>
                        </div>
                    </div>


                    <div class="tit-1 absolute js-display-hover">
                        <a class="js-display-hover-trigger"><img
                                alt="개인"
                                src="https://simg.wooribank.com/img/intro/header/btn_tit_1_intro_renew.png"/></a>
                        <div class="js-display-hover-area level2">
                            <span class="arrow"></span>
                            <ul class="menu">
                                <li class="title"><a>개인뱅킹</a>
                                </li>
                                <li><a>조회</a></li>
                                <li><a>이체</a></li>


                                <li><a>오픈뱅킹</a></li>


                                <li><a>공과금</a></li>
                                <li><a>예금/신탁</a></li>
                                <li><a>펀드</a></li>
                                <li><a>보험</a></li>
                                <li><a>대출</a></li>
                                <li><a>외환/골드</a></li>
                                <li><a>퇴직연금</a></li>
                                <li><a>뱅킹관리</a></li>
                                <li><a>ISA</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="tit-2 absolute js-display-hover">
                        <a class="js-display-hover-trigger"><img
                                alt="기업"
                                src="https://simg.wooribank.com/img/intro/header/btn_tit_2_intro_renew.png"/></a>
                        <div class="js-display-hover-area level2">
                            <span class="arrow"></span>
                            <ul class="menu">
                                <li class="title"><a>기업뱅킹</a></li>
                                <li><a>조회</a></li>
                                <li><a>이체</a></li>
                                <li><a>공과금</a></li>
                                <li><a>전자결제</a></li>
                                <li><a>수표어음</a></li>
                                <li><a>자금관리</a></li>
                                <li><a>예금/신탁</a></li>
                                <li><a>대출</a></li>
                                <li><a>펀드/보험</a></li>
                                <li><a>외환</a></li>
                                <li><a>퇴직연금</a></li>
                                <li><a>뱅킹관리</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="tit-4 absolute js-display-hover">
                        <a class="js-display-hover-trigger"><img
                                alt="자산관리" src="https://simg.wooribank.com/img/intro/header/btn_tit_3_intro_renew.png"/></a>
                        <div class="js-display-hover-area level2">
                            <span class="arrow"></span>
                            <ul class="menu">
                                <li class="title"><a href="https://spot.wooribank.com/pot/Dream?withyou=wa">자산관리</a>
                                </li>
                                <li><a>MY자산 진단</a></li>
                                <li><a>펀드 포트폴리오</a></li>
                                <li><a>퇴직연금 포트폴리오</a></li>
                                <li><a>WON챌린지</a></li>
                                <li><a>미래설계</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="tit-5 absolute js-display-hover">
                        <a class="js-display-hover-trigger"><img
                                alt="금융상품" src="https://simg.wooribank.com/img/intro/header/btn_tit_4_intro_renew.png"/></a>
                        <div class="js-display-hover-area level2">
                            <span class="arrow"></span>
                            <ul class="menu">
                                <li class="title"><a href="https://spot.wooribank.com/pot/Dream?withyou=po">금융상품</a>
                                </li>
                                <li><a>추천상품</a></li>
                                <li><a>예금</a></li>
                                <li><a>펀드</a></li>
                                <li><a>대출</a></li>
                                <li><a>외환</a></li>
                                <li><a>골드</a></li>
                                <li><a>신탁</a></li>
                                <li><a>보험</a></li>
                                <li><a>퇴직연금</a></li>
                                <li><a>시니어플러스 은퇴설계</a>
                                </li>
                                <li><a>ISA</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="tit-3 absolute js-display-hover">


                        <a href="javascript:chkCardLinkPop('cd')" class="js-display-hover-trigger"><img alt="카드"
                                                                                                        src="https://simg.wooribank.com/img/intro/header/btn_tit_5_intro_renew.png"/></a>

                        <div class="js-display-hover-area level3">
                            <span class="arrow"></span>
                            <ul class="menu">
                                <li class="title">


                                    <a href="javascript:chkCardLinkPop('cd')">카드</a>

                                </li>


                                <li>


                                    <a href="javascript:chkCardLinkPop('cd')">우리카드</a>

                                </li>


                                <li><a href="javascript:chkCardLinkPop('lt')">롯데카드</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="tit-7 absolute js-display-hover">
                        <a href="https://spib.wooribank.com/pib/jcc?withyou=CMCOM0408&__ID=c027277"
                           class="btn-popup"><img alt="전체서비스 메뉴보기"
                                                  src="https://simg.wooribank.com/img/intro/header/btn_tit_6_intro_renew.png"/></a>
                    </div>
                    <div class="tit-9 absolute js-display-hover">
                        <a href="#none" class="js-display-hover-trigger"><img alt="Language"
                                                                              src="https://simg.wooribank.com/img/intro/header/btn_tit_7_intro_renew.png"/><span
                                class="description">메뉴보기</span></a>
                        <div class="js-display-hover-area level2">
                            <span class="arrow"></span>
                            <ul class="menu2">
                                <li class="title">LANGUAGE</li>
                                <li class="flag"><a href="https://spot.wooribank.com/pot/Dream?withyou=en"
                                                    class="flag-us">English</a></li>
                                <li class="flag"><a href="https://svc.wooribank.com/svc/Dream?withyou=ml&amp;LCL=ZH-CHS"
                                                    class="flag-china">Chinese (中國語 )</a></li>
                                <li class="flag"><a href="https://svc.wooribank.com/svc/Dream?withyou=ml&amp;LCL=JA"
                                                    class="flag-japan">Japanese (日本語)</a></li>
                                <li class="flag"><a href="https://svc.wooribank.com/svc/Dream?withyou=ml&amp;LCL=TL"
                                                    class="flag-philippines">Filipino (Tagalog)</a></li>
                                <li class="flag"><a href="https://svc.wooribank.com/svc/Dream?withyou=ml&amp;LCL=VI"
                                                    class="flag-vietnam">Vietnamese (tiếng Việt)</a></li>
                                <li class="flag"><a href="https://svc.wooribank.com/svc/Dream?withyou=ml&amp;LCL=MN"
                                                    class="flag-mongolia">Mongolian (Монгол хэл)</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="tit-search absolute">
                        <div class="search2">
                            <form method="post" action="https://spib.wooribank.com/pib/Dream?withyou=CMCOM0007">
                                <a href="#none" id="search_upper_btn"><img alt="검색"
                                                                           src="https://simg.wooribank.com/img/intro/header/upper_search_icon.png"/></a>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="content notice">


                </div>
            </div>
            <div class="search_2" style="display:none;">
                <div class="search_inner_cont">
                    <form id="search" name="search" method="post"
                          action="https://spib.wooribank.com/pib/Dream?withyou=CMCOM0007">
                        <input class="text font-c-7 mt50 font-18" type="text"
                               style="line-height:50px; width:658px; height:50px; ime-mode:active;" name="query"
                               title="검색어 입력" value="   검색어를 입력하세요" onfocus="this.value=''"/>
                        <input class="submit" type="image" src="/img/intro/header/inner_search_icon.png" alt="검색"/>
                        <div class="search_word mt25 ml50">
                            <div class="search_tit ml3">추천검색어</div>
                            <ul>
                                <li><a href="javascript:recSearch('인증서');">인증서</a></li>
                                <li><a href="javascript:recSearch('OTP');">OTP</a></li>
                                <li><a href="javascript:recSearch('주거래');">주거래</a></li>
                                <li><a href="javascript:recSearch('이체한도');">이체한도</a></li>
                                <li><a href="javascript:recSearch('자동차대출');">자동차대출</a></li>
                            </ul>
                        </div>
                    </form>
                </div>
                <div class="search_inner_close" id="search_close"><a href="#none"></a></div>
            </div>


            <div id="introHome" class="renew_main">
                <div id="introQuick">
                    <div class="bin"></div>
                </div>
                <div class="mid_content renew_style">
                    <div class="mid_cont_key overflowh">

                        <div id="slides01">


                            <div class="slides nth2" name="crmArea" id="introCrm_02"
                                 style="background: transparent; background:#eefae4;">

                                <a href='https://www.google.com'>
                                    <div class="img_center">
                                        <img src="/image/new_home_page/logo/cardcorona.png"
                                             width="980" height="520" alt="이미지 안뜸">
                                    </div>
                                </a>

                            </div>


                            <div class="slides nth3" name="crmArea" id="introCrm_02"
                                 style="background: transparent; background:#FFF3C3;">

                                <a href='https://www.google.com'>
                                    <div class="img_center">
                                        <img src="/image/new_home_page/logo/1.jpg"
                                             width="980" height="520" alt="이미지 안뜸">
                                    </div>
                                </a>

                            </div>


                            <div class="slides nth6" name="crmArea" id="introCrm_02"
                                 style="background: transparent; background:#edf3fd;">

                                <a href='https://www.google.com'>
                                    <div class="img_center">
                                        <img src="/image/new_home_page/logo/enter.png"
                                             width="980" height="520" alt="이미지 안뜸">
                                    </div>
                                </a>

                            </div>

                        </div>

                    </div>
                </div>
            </div>


            <div class="mid_cont_menu" id="mid_cont_menu">
                <ul class="left_menu">
                    <li><a>다른기관<br/>정보조회</a></li>
                    <li><a>조회</a></li>
                    <li><a>이체</a></li>
                    <li><a>환율</a></li>
                    <li class="q_btn">
                        <a href="#none" id="q_btn_toggle">빠른메뉴<span class="q_btn_icon"></span></a>
                        <div class="q_menu position-r" id="q_menu_toggle">
                            <div name="crmArea" id="introCrm_02" class="q_menu_inner">
                                <ul class="q_menu_list w100p clearfix top">
                                    <li class=""><a href="#popup"
                                                    onclick="jsOpenWindow('https://spib.wooribank.com/spd/Dream?withyou=CMSPD0010', '스피드계좌조회','890','490','1,resizable=yes','1');return false;"
                                                    title="새창열림" class="font-bold">스피드계좌조회</a></li>
                                    <li><a href='https://svc.wooribank.com/svc/Dream?withyou=PSTAX0001'>공과금</a></li>
                                    <li><a href='https://spib.wooribank.com/pib/Dream?withyou=PSBKM0001'>뱅킹관리</a></li>
                                </ul>
                                <ul class="q_menu_list w100p clearfix bottom">
                                    <li><a href='https://spot.wooribank.com/pot/Dream?withyou=cq'>고객광장</a></li>
                                    <li><a href="#popup"
                                           onclick="jsOpenWindow('https://spib.wooribank.com/pib/Dream?withyou=CMCOM0153', '영업점안내','890','700','1','1');return false;"
                                           title="새창열림">영업점안내</a></li>
                                    <li><a href="#popup" onclick="chkCardLinkPop('ci');return false;" title="새창열림">우리카드신청</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="add_menu_list">
                <ul class="submenu">
                    <li><a href='https://spot.wooribank.com/pot/Dream?withyou=CQCSD0006'>금융 소비자 보호</a></li>
                    <li><a href="https://spot.wooribank.com/pot/Dream?withyou=CQSCT0116">보안뉴스</a></li>
                    <li><a href="https://spot.wooribank.com/pot/Dream?withyou=BPPBC0012">상품/약관 공시</a></li>
                </ul>
            </div>


            <div id="introNews">
                <div class="content mt10" name="crmArea" id="introCrm_03">
                    <div class="menu_left fleft">
                        <a href="https://spot.wooribank.com/pot/Dream?withyou=po"
                           class="js-display-hover-trigger mt30 mb20"><h2 class="font-22">금융상품</h2></a>
                        <div class="mid_cont_menu">
                            <span class="arrow"></span>
                            <ul class="cont_menu_img mt12">
                                <li class="tit_1"><a href="https://spot.wooribank.com/pot/Dream?withyou=PODEP0001">
                                    <div><img src="/image/new_home_page/common/icon_set_011.png" alt="예금">
                                        <p class="acenter"><em>예금</em></p></div>
                                </a></li>
                                <li class="tit_2"><a href="https://spot.wooribank.com/pot/Dream?withyou=ln">
                                    <div><img src="/image/new_home_page/common/icon_set_022.png" alt="대출">
                                        <p class="acenter"><em>대출</em></p></div>
                                </a></li>
                                <li class="tit_3"><a href="https://spot.wooribank.com/pot/Dream?withyou=OWFDM0003">
                                    <div><img src="/image/new_home_page/common/icon_set_033.png" alt="펀드">
                                        <p class="acenter"><em>펀드</em></p></div>
                                </a></li>
                                <li class="tit_4"><a href="https://spot.wooribank.com/pot/Dream?withyou=fx">
                                    <div><img src="/image/new_home_page/common/icon_set_044.png" alt="외환">
                                        <p class="acenter"><em>외환</em></p></div>
                                </a></li>
                                <li class="tit_5"><a href="https://spot.wooribank.com/pot/Dream?withyou=POTRT0001">
                                    <div><img src="/image/new_home_page/common/icon_set_055.png" alt="신탁">
                                        <p class="acenter font-16"><em>신탁</em></p></div>
                                </a></li>
                                <li class="tit_6"><a href="https://svc.wooribank.com/svc/Dream?withyou=rp">
                                    <div><img src="/image/new_home_page/common/icon_set_066.png" alt="퇴직연금">
                                        <p class="acenter"><em>퇴직연금</em></p></div>
                                </a></li>
                                <li class="tit_7"><a href="https://spot.wooribank.com/pot/Dream?withyou=POBAC0001">
                                    <div><img src="/image/new_home_page/common/icon_set_077.png" alt="보험">
                                        <p class="acenter"><em>보험</em></p></div>
                                </a></li>
                                <li class="tit_8"><a href="https://spot.wooribank.com/pot/Dream?withyou=IMISA0044">
                                    <div><img src="/image/new_home_page/common/icon_set_088.png" alt="ISA">
                                        <p class="acenter"><em>ISA</em></p></div>
                                </a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="menu_right fright">
                        <a href="https://spib.wooribank.com/pib/Dream?withyou=CMCOM0126"
                           class="js-display-hover-trigger mt30 mb20"><h2 class="font-22">카드</h2></a>
                        <div class="mid_cont_card_menu">
                            <ul class="cont_menu_card_img mt12">
                                <li class="tit_1"><a href="javascript:chkCardLinkPop('ca')">
                                    <div><img src="/image/new_home_page/common/icon_set_099.png" alt="예금">
                                        <p class="acenter font-bold"><em>카드신청</em></p></div>
                                </a></li>
                                <li class="tit_2"><a href="javascript:chkCardLinkPop('cm')" class="Mycard">
                                    <div><img src="/image/new_home_page/common/icon_set_01010.png" alt="대출">
                                        <p class="acenter font-bold"><em>나의카드</em></p></div>
                                </a></li>
                                <li class="tit_3"><a href="javascript:chkCardLinkPop('ce')">
                                    <div><img src="/image/new_home_page/common/icon_set_01111.png" alt="펀드">
                                        <p class="acenter font-bold"><em>이벤트</em></p></div>
                                </a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div id="introservice" class="clearfix">
                <h2 class="mt20 font-22">서비스</h2>
                <ul class="mt20">
                    <li>
                        <a href='https://svc.wooribank.com/svc/Dream?withyou=SFSBK0002' title="우리WON뱅킹 바로가기">
                            <div class="introservice_info_one">
                                <div class="img_cont mb10"></div>
                                <span class="font-c-5 font-19 mb10 font-bold">우리WON뱅킹</span>
                                <p class="font-14 font-c-6">새 시대의 금융을 켜다,<br/>우리은행 대표 모바일뱅킹 APP</p>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href='https://spot.wooribank.com/pot/Dream?withyou=wa' title="자산관리">
                            <div class="introservice_info_two">
                                <div class="img_cont mb10"></div>
                                <span class="font-c-5 font-19 mb10 font-bold">자산관리</span>
                                <p class="font-14 font-c-6">소액투자부터 미래설계까지<br/>효율적인 자산관리를 시작하세요</p>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href='https://spib.wooribank.com/pib/Dream?withyou=PSDEP0047' title="스마트간편신규 바로가기">
                            <div class="introservice_info_three">
                                <div class="img_cont mb10"></div>
                                <span class="font-c-5 font-19 mb10 font-bold">스마트간편신규</span>
                                <p class="font-14 font-c-6">직원에게 추천받으신 상품을<br/>간편하게 가입하세요</p>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>

            <div id="introinfomation" class="mt30 mb50 clearfix">
                <div class="introinfomation_cont">
                    <div class="intro_news">
                        <div class="intro_news_tit">
                            <h2 class="tit_news mt40 mb10">새소식</h2>
                            <a href="https://spot.wooribank.com/pot/Dream?withyou=BPPBC0009" class="tit_news_more"><span
                                    class="font-c-6">더보기</span></a>
                        </div>
                        <div class="intro_news_list">

                            <script type="text/javascript">
                                var bbsUrl = "bbs";
                            </script>

                            <script type="text/javascript"
                                    src="https://simg.wooribank.com/js/com/bbs.js?1370948139000"></script>


                            <form method='post' action='' id='bbsForm_B00070'>
                                <input type='hidden' name='BOARD_ID' value='B00070'/><input type='hidden' name='bbsMode'
                                                                                            value='list'/><input
                                    type='hidden' name='__PAGE_TITLE__'/><input type='hidden' name='BOARD_PAGE_NO'
                                                                                value='1'/><input type='hidden'
                                                                                                  name='ARTICLE_ID'/>
                                <input type='hidden' name='ORDER_TYPE' value='desc'/>

                                <dl class="font-c-5">

                                    <dt class="new_list"><a id='article_link_46391'
                                                            title='[채용] 2021년 우리은행 사무지원직군 신입행원 특별채용 1차면접 합격자 발표'
                                                            href='#'
                                                            onclick="javascript:bbs_gotoView(this,'B00070' , 46391 , 'https://spot.wooribank.com/pot/Dream?withyou=BPPBC0009');return false;">[채용]
                                        2021년 우리은행 사무지원직군 신...</a></dt>
                                    <dd>2021.09.01</dd>


                                    <dt class="new_list"><a id='article_link_46348' title='[공지] 가상자산 거래관련 유의사항' href='#'
                                                            onclick="javascript:bbs_gotoView(this,'B00070' , 46348 , 'https://spot.wooribank.com/pot/Dream?withyou=BPPBC0009');return false;">[공지]
                                        가상자산 거래관련 유의사항</a></dt>
                                    <dd>2021.08.30</dd>


                                    <dt class="new_list"><a id='article_link_46012'
                                                            title='[공지] 코로나19 확산 방지를 위한 영업시간 단축 안내 ' href='#'
                                                            onclick="javascript:bbs_gotoView(this,'B00070' , 46012 , 'https://spot.wooribank.com/pot/Dream?withyou=BPPBC0009');return false;">[공지]
                                        코로나19 확산 방지를 위한 영업시...</a></dt>
                                    <dd>2021.07.23</dd>


                                </dl>

                            </form>

                        </div>
                    </div>
                    <div class="intro_event">
                        <div class="intro_event_tit">
                            <h2 class="tit_event mt40 mb10">이벤트</h2>
                            <a href="https://spot.wooribank.com/pot/Dream?withyou=EVEVT0001"
                               class="tit_event_more"><span class="font-c-6 w100p">더보기</a>
                        </div>
                        <div class="intro_event_list">
                            <dl class="font-c-5">

                                <dt>
                                    <a href="https://spot.wooribank.com/pot/Dream?withyou=EVEVT0001&cc=c001308:c001386&NO=2931">갈아타면
                                        쏟아지는 환승 혜택!</a>

                                <dt>
                                    <a href="https://spot.wooribank.com/pot/Dream?withyou=EVEVT0001&cc=c001308:c001386&NO=2565">청년을
                                        위한 우리미래드림바우처Ⅱ</a>

                                <dt>
                                    <a href="https://spot.wooribank.com/pot/Dream?withyou=EVEVT0001&cc=c001308:c001386&NO=2588">우리
                                        아이 첫 통장 만들고 1만원 받으세요!</a>

                            </dl>
                        </div>
                    </div>
                </div>
            </div>

            <div id="introFoot">
                <h2 class="description">우리은행정보 및 우리은행관련사이트</h2>


                <style>
                    .footerBox {
                        position: relative;
                        width: 100%;
                        height: 37px;
                    }

                    .snsBox {
                        position: absolute;
                        top: 16px;
                        left: 0px;
                    }

                    .snsBox li {
                        display: table-cell;
                    }

                    .snsBox li a {
                        margin-right: 5px;
                    }

                    .iconBox {
                        position: absolute;
                        top: 16px;
                        left: 153px;
                    }

                    .iconBox li {
                        display: table-cell;
                    }

                    .iconBox li a {
                        margin-left: 5px;
                    }

                    .Iconsection {
                        position: absolute;
                        top: 16px;
                        left: 135px;
                        color: #7f7f7f;
                    }

                    .iconCopyright {
                        position: absolute;
                        top: 15px;
                        right: -37px;
                    }

                    .icon .icon-box {
                        position: absolute;
                        border: solid 1px #696969;
                        background: #fff;
                        color: #555;
                        padding: 6px 10px;
                        top: -52px;
                        text-align: center;
                        font-size: 12px;
                        letter-spacing: -1px;
                        height: 30px;
                        display: none
                    }

                    .icon .icon-box .arrow {
                        position: absolute;
                        width: 14px;
                        height: 15px;
                        padding: 0 6px;
                        background: url(/img/common/footer/bu_more.gif) no-repeat center 0;
                        top: 42px;
                        left: 0
                    }

                    .icon-0 .icon-box {
                        width: 80px;
                        left: 101px
                    }

                    .icon-0 .icon-box .arrow {
                        width: 80px
                    }

                    .icon-1 .icon-box {
                        width: 125px;
                        left: -56px
                    }

                    .icon-1 .icon-box .arrow {
                        width: 132px
                    }

                    .icon-2 .icon-box {
                        width: 131px;
                        left: -23px
                    }

                    .icon-2 .icon-box .arrow {
                        width: 131px
                    }

                    .icon-3 .icon-box {
                        width: 138px;
                        left: 27px
                    }

                    .icon-3 .icon-box .arrow {
                        width: 84px
                    }

                    .icon-4 .icon-box {
                        width: 170px;
                        left: -3px
                    }

                    .icon-4 .icon-box .arrow {
                        width: 120px;
                        background-position: 104px 0
                    }

                    .icon-5 .icon-box {
                        width: 114px;
                        height: 45px;
                        top: -67px;
                        left: 6px
                    }

                    .icon-5 .icon-box .arrow {
                        width: 119px;
                        top: 57px;
                        background-position: 115px 0
                    }

                    .notice-1.notice-open {
                        padding-top: 0 !important;
                    }

                    .height278 {
                        height: 278px !important;
                    }

                    body.intro #introFoot .footer-link .links .family-box ul {
                        padding-right: 15px;
                    }

                    body.intro #introFoot .footer-link .links .family-box ul li {
                        float: left;
                        width: 50%;
                    }

                    body.intro #introFoot .footer-link .links .family-box ul li:nth-child(odd) {
                        width: 38%;
                        margin-left: 10px;
                    }

                    body.intro #introFoot .footer-link .links .etc .etc_box {
                        height: 223px;
                    }

                    body.intro #introFoot .footer-link .links .family-box .family-arrow {
                        background-position: left 0;
                        left: 88px;
                    }
                </style>

                <div id="footer" class="clearfix">
                    <div id="introFoot">
                        <h2 class="description">우리은행정보 및 우리은행관련사이트</h2>
                        <div class="content">
                            <div class="footer-content">
                                <div class="footer-link">
                                    <div class="links">
                                        <div class="more">
                                            <dl class="js-display-hover ars" data-ui-animation="true">
                                                <dt><a href="#none" class="js-display-hover-trigger">대표 <strong>1588-5000</strong></a>
                                                </dt>
                                                <dd class="etc_box js-display-hover-area">
                                                    <ul>
                                                        <li class="title">ARS 이용안내</li>
                                                        <li class="ars-list clearfix">
                                                            <div class="left blue">대표전화</div>
                                                            <div class="right">
                                                                <p class="top">1588-5000 / 1599-5000</p>
                                                                <p class="bot">82-2-2006-5000<span
                                                                        class="gray">(해외)</span></p>
                                                            </div>
                                                        </li>
                                                        <li class="ars-list clearfix">
                                                            <div class="left gray">고객의 말씀</div>
                                                            <div class="right">
                                                                <p class="top">080-365-5000</p>
                                                                <p class="bot">(휴대폰 02-2008-5000)</p>
                                                            </div>
                                                        </li>
                                                        <li class="ars-list none clearfix">
                                                            <div class="left blue">신규상담</div>
                                                            <div class="right">
                                                                <p class="top"><span class="lltop">예적금</span> 1599-8100
                                                                </p>
                                                                <p class="bot"><span class="lltop">대출</span> 1599-8300
                                                                </p>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                    <span class="country-arrow"></span>
                                                </dd>
                                            </dl>
                                            <dl class="js-display-hover etc" data-ui-animation="true">
                                                <dt><a href="#none" class="js-display-hover-trigger">기타서비스</a></dt>
                                                <dd class="etc_box js-display-hover-area">
                                                    <ul>
                                                        <li class="title">기타서비스</li>
                                                        <li>

                                                            <a href="#popup" title="팝업새창"
                                                               onclick="jsOpenWindow('https://spib.wooribank.com/pib/Dream?withyou=CMBBS0092', '법원환급금','720','515','1','1');return false;">법원환급금조회</a>
                                                            <a href="#popup" title="팝업새창"
                                                               onclick="jsOpenWindow('https://spib.wooribank.com/pib/Dream?withyou=CMCOM0328', '미교부국민주조회','787','570','1','1');return false;">미교부국민주조회</a>
                                                            <a href="#popup" title="팝업새창"
                                                               onclick="jsOpenWindow('https://spib.wooribank.com/pib/Dream?withyou=CMSPD0042', '상조회사예치금조회','735','450','1','1');return false;">상조회사예치금조회</a>
                                                            <a href="#popup" title="팝업새창"
                                                               onclick="jsOpenWindow('https://spib.wooribank.com/pib/Dream?withyou=CMSPD0012', '예금잔액증명서발급조회','860','720','1','1');return false;">예금잔액증명서발급조회</a>
                                                            <a href="#popup" title="팝업새창"
                                                               onclick="jsOpenWindow('https://spib.wooribank.com/pib/Dream?withyou=CMBBS0091', '지급보증서발급조회','740','620','1','1');return false;">지급보증서발급조회</a>
                                                            <a href="#popup" title="팝업새창"
                                                               onclick="jsOpenWindow('https://spib.wooribank.com/pib/Dream?withyou=CMLGN0011', '서울시예치금내역조회','880','600','1','1');return false;">서울시예치금내역조회</a>
                                                        </li>


                                                    </ul>
                                                    <span class="country-arrow"></span>
                                                </dd>
                                            </dl>
                                            <dl class="js-display-hover language" data-ui-animation="true">
                                                <dt><a href="#none" class="js-display-hover-trigger">LANGUAGE</a></dt>
                                                <dd class="country-box js-display-hover-area">
                                                    <ul>
                                                        <li class="title">LANGUAGE</li>
                                                        <li>
                                                            <a href="https://spot.wooribank.com/pot/Dream?withyou=en&amp;LCL=EN"
                                                               class="flag-us">English</a></li>
                                                        <li>
                                                            <a href="https://svc.wooribank.com/svc/Dream?withyou=ml&amp;LCL=ZH-CHS"
                                                               class="flag-china">中國語</a></li>
                                                        <li>
                                                            <a href="https://svc.wooribank.com/svc/Dream?withyou=ml&amp;LCL=JA"
                                                               class="flag-japan">日本語</a></li>
                                                        <li>
                                                            <a href="https://svc.wooribank.com/svc/Dream?withyou=ml&amp;LCL=TL"
                                                               class="flag-philippines">Tagalog</a></li>
                                                        <li>
                                                            <a href="https://svc.wooribank.com/svc/Dream?withyou=ml&amp;LCL=VI"
                                                               class="flag-vietnam">tiếng Việt</a></li>
                                                        <li>
                                                            <a href="https://svc.wooribank.com/svc/Dream?withyou=ml&amp;LCL=MN"
                                                               class="flag-mongolia">Монгол хэл</a></li>
                                                    </ul>
                                                    <span class="country-arrow"></span>
                                                </dd>
                                            </dl>
                                            <dl class="js-display-hover family" data-ui-animation="true">
                                                <dt><a href="#none" class="js-display-hover-trigger">패밀리사이트</a></dt>
                                                <dd class="family-box js-display-hover-area height278 w320">
                                                    <ul>
                                                        <li class="title w100p ml0">패밀리사이트</li>
                                                        <li>

                                                            <a title="새창열림" href="https://www.woorifg.com/"
                                                               target="_blank">우리금융지주</a></li>
                                                        <li><a title="새창열림" href="http://www.woorifs.co.kr/"
                                                               target="_blank">우리펀드서비스</a></li>
                                                        <li><a title="새창열림" href="https://www.wooricard.com/"
                                                               target="_blank">우리카드</a></li>
                                                        <li><a title="새창열림" href="http://www.wooripe.com/"
                                                               target="_blank">우리PE</a></li>
                                                        <li><a title="새창열림" href="https://www.woorifcapital.com/"
                                                               target="_blank">우리금융캐피탈</a></li>
                                                        <li><a title="새창열림" href="http://www.wooriglobal.com/"
                                                               target="_blank">우리글로벌자산운용</a></li>
                                                        <li><a title="새창열림" href="https://www.wooriib.com/"
                                                               target="_blank">우리종합금융</a></li>
                                                        <li><a title="새창열림" href="http://www.woorifis.com/"
                                                               target="_blank">우리FIS</a></li>
                                                        <li><a title="새창열림" href="http://www.wooriat.com/"
                                                               target="_blank">우리자산신탁</a></li>
                                                        <li><a title="새창열림" href="http://www.wfri.re.kr/"
                                                               target="_blank">우리금융경영연구소</a></li>
                                                        <li><a title="새창열림" href="http://www.wooriam.kr/"
                                                               target="_blank">우리자산운용</a></li>
                                                        <li><a title="새창열림" href="https://www.woorifoundation.or.kr/"
                                                               target="_blank">우리다문화장학재단</a></li>
                                                        <li><a title="새창열림" href="https://www.woorisavingsbank.com/"
                                                               target="_blank">우리금융저축은행</a></li>
                                                        <li><a title="새창열림" href="http://www.woorimiso.or.kr/"
                                                               target="_blank">우리미소금융재단</a></li>
                                                        <li><a title="새창열림" href="https://www.wooricredit.com/"
                                                               target="_blank">우리신용정보</a></li>
                                                    </ul>
                                                    <span class="family-arrow"></span>
                                                </dd>
                                            </dl>
                                        </div>
                                    </div>
                                </div>
                                <div class="footer-bottom clearfix">

                                    <div class="logo">
                                        <a href="/"><img alt="우리은행"
                                                         src="https://simg.wooribank.com/img/common/footer/icon_woori.gif"/></a>
                                    </div>
                                    <div class="right-content">
                                        <div class="info">
                                            <p class="quick">
                                                <a href="https://spot.wooribank.com/pot/Dream?withyou=bp">은행소개</a>&nbsp;&nbsp;|&nbsp;
                                                <a href="#popup"
                                                   onclick="jsOpenWindow('https://spib.wooribank.com/pib/Dream?withyou=CMCOM0153', '영업점안내','890','700','1','1');return false;">영업점안내</a>&nbsp;&nbsp;|&nbsp;
                                                <a href="https://spot.wooribank.com/pot/Dream?withyou=cq">고객광장</a>&nbsp;&nbsp;|&nbsp;
                                                <a href="https://spot.wooribank.com/pot/Dream?withyou=CQSCT0048"
                                                   class="font-bold font-c-5">개인정보처리방침</a>&nbsp;&nbsp;|&nbsp;
                                                <a href="https://spot.wooribank.com/pot/Dream?withyou=CQSCT0132"
                                                   class="font-bold font-c-5">신용정보활용체제</a>&nbsp;&nbsp;|&nbsp;
                                                <a href="https://spot.wooribank.com/pot/Dream?withyou=CQSCT0049">개인신용정보관리보호</a><br/>
                                                <a href="https://spot.wooribank.com/pot/Dream?withyou=CQACR0001">사고신고</a>&nbsp;&nbsp;|&nbsp;
                                                <a href="https://spot.wooribank.com/pot/Dream?withyou=CQCSD0001">전자민원접수</a>&nbsp;&nbsp;|&nbsp;
                                                <a href="https://spot.wooribank.com/pot/Dream?withyou=POTRT0033">보호금융상품등록부</a>&nbsp;&nbsp;|&nbsp;
                                                <a href="https://spot.wooribank.com/pot/Dream?withyou=CQPNC0002">상품공시실</a>&nbsp;&nbsp;|&nbsp;
                                                <a href="https://spot.wooribank.com/pot/Dream?withyou=CQSCT0001">보안센터</a>&nbsp;&nbsp;|&nbsp;
                                                <a href="https://spot.wooribank.com/pot/Dream?withyou=CQIBG0050">웹접근성
                                                    이용안내</a>
                                            </p>
                                            <p class="copyright">COPYRIGHTS WOORI BANK. ALL RIGHTS RESERVED.</p>
                                        </div>
                                        <div class="inof2">


                                            <div class="js-display-hover icon icon-0 ml5" data-ui-animation="true">
                                                <a href="http://www.wa.or.kr/board/list.asp?BoardID=0006"
                                                   target="_blank" title="한국웹접근성평가센터 인증현황 바로가기"
                                                   class="js-display-hover-trigger">
                                                    <img height="21px" width="34px" alt="국가공인 웹 접근성 품질인증마크"
                                                         title="한국웹접근성인증평가원"
                                                         src="https://simg.wooribank.com/img/main/icon_kwacc_ib_2021.bmp"/></a>
                                                <div class="icon-box js-display-hover-area">
                                                    2021 웹 접근성<br/>우수사이트
                                                    <span class="arrow"></span>
                                                </div>
                                            </div>


                                            <div class="js-display-hover icon icon-1" data-ui-animation="true">
                                                <a href="#none" class="js-display-hover-trigger"><img
                                                        alt="2019 고객감동경영대상 종합대상 12년 연속 수상"
                                                        src="https://simg.wooribank.com/img/main/icon_1_new.gif"/></a>
                                                <div class="icon-box js-display-hover-area">
                                                    2019 고객감동경영대상<br/>종합대상 12년 연속 수상
                                                    <span class="arrow"></span>
                                                </div>
                                            </div>
                                            <div class="js-display-hover icon icon-2" data-ui-animation="true">
                                                <a href="#none" class="js-display-hover-trigger"><img
                                                        alt="The Banker Awards 2020 우리은행 3관왕 수상"
                                                        src="https://simg.wooribank.com/img/main/icon_20201215.png"/></a>
                                                <div class="icon-box js-display-hover-area">
                                                    The Banker Awards 2020<br/>우리은행 3관왕 수상
                                                    <span class="arrow"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="content">
                    </div>
                </div>


                <form name="boardViewFrm" action="" method="post">


                    <input type="hidden" name="BOARD_ID" value="">
                    <input type="hidden" name="ARTICLE_ID" value="">
                    <input type="hidden" name="bbsMode" value="view">
                    <input type="hidden" name="ORDER_TYPE" value="desc">
                </form>

                <script type="text/javascript">
                    //<![CDATA[

                    function RemoveDomainCookie(name) {

                        var value = "";
                        var today = new Date();
                        today.setDate(today.getDate() - 1);

                        document.cookie = name + "=" + escape(value) + "; domain=.wooribank.com; path=/; expires=" + today.toGMTString() + ";";

                    }

                    RemoveDomainCookie("SSO_JSESSION");
                    //]]>
                </script>

            </div>
            <!-- //content -->
        </div>
        <!-- //container -->
    </div>
</body>
</html>