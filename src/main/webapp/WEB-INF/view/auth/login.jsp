<!DOCTYPE html><head>	<!-- BEGIN GLOBAL MANDATORY STYLES -->	<link href="/frontend/lib/metronic/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>	<link href="/frontend/lib/metronic/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>	<link href="/frontend/lib/metronic/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>	<link href="/frontend/lib/metronic/css/style-metro.css" rel="stylesheet" type="text/css"/>	<link href="/frontend/lib/metronic/css/style.css" rel="stylesheet" type="text/css"/>	<link href="/frontend/lib/metronic/css/style-responsive.css" rel="stylesheet" type="text/css"/>	<link href="/frontend/lib/metronic/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>	<link href="/frontend/lib/metronic/css/uniform.default.css" rel="stylesheet" type="text/css"/>	<!-- END GLOBAL MANDATORY STYLES -->	<!-- BEGIN PAGE LEVEL STYLES -->	<link href="/frontend/lib/metronic/css/login.css" rel="stylesheet" type="text/css"/>	<!-- END PAGE LEVEL STYLES --></head><body class="login">	<div class="logo">		<img src="/image/logo.png"/>	</div>	<div class="content">        <!-- BEGIN LOGIN FORM -->		<form class="form-vertical login-form" action="/auth/login" method="post">			<h3 class="form-title">登陆</h3>			<div class="control-group">				<div class="controls">					<div class="input-icon left">						<i class="icon-user"></i>						<input class="m-wrap placeholder-no-fix" type="text" placeholder="用户名" name="username"/>					</div>				</div>			</div>			<div class="control-group">				<div class="controls">					<div class="input-icon left">						<i class="icon-lock"></i>						<input class="m-wrap placeholder-no-fix" type="password" placeholder="密码" name="password"/>					</div>				</div>			</div>			<div class="form-actions">				<label class="checkbox">				<input type="checkbox" name="remember" value="1"/>下次自动登录				</label>				<button type="submit" class="btn green pull-right">                    登陆<i class="m-icon-swapright m-icon-white"></i>				</button>			</div>            <div class="forget-password">                <h4>没有帐号？</h4>                <p>                    <a href="javascript:;" id="register-btn" class="">创建账号</a>                </p>            </div>            <div class="create-account">                忘记密码？                <p>                    <a href="javascript:;" class="" id="forget-password">重置密码</a>                </p>            </div>		</form>		<!-- END LOGIN FORM -->		<!-- BEGIN FORGOT PASSWORD FORM -->		<form class="form-vertical forget-form" method="post" action="index.html">			<h3 class="">重置密码</h3>			<p>输入你的电子邮箱</p>			<div class="control-group">				<div class="controls">					<div class="input-icon left">						<i class="icon-envelope"></i>						<input class="m-wrap placeholder-no-fix" type="text" placeholder="电子邮箱" name="email" />					</div>				</div>			</div>			<div class="form-actions">				<button type="button" id="back-btn" class="btn">				<i class="m-icon-swapleft"></i>后退				</button>				<button type="submit" class="btn green pull-right">				确定<i class="m-icon-swapright m-icon-white"></i>				</button>			</div>		</form>		<!-- END FORGOT PASSWORD FORM -->		<!-- BEGIN REGISTRATION FORM -->		<form class="form-vertical register-form" action="index.html">			<h3 class="">注册</h3>			<p>请在下方输入你的账户信息:</p>			<div class="control-group">				<div class="controls">					<div class="input-icon left">						<i class="icon-user"></i>						<input class="m-wrap placeholder-no-fix" type="text" placeholder="用户名" name="username"/>					</div>				</div>			</div>			<div class="control-group">				<div class="controls">					<div class="input-icon left">						<i class="icon-lock"></i>						<input class="m-wrap placeholder-no-fix" type="password" id="register_password" placeholder="密码" name="password"/>					</div>				</div>			</div>			<div class="control-group">				<div class="controls">					<div class="input-icon left">						<i class="icon-ok"></i>						<input class="m-wrap placeholder-no-fix" type="password" placeholder="请再次输入密码" name="rpassword"/>					</div>				</div>			</div>			<div class="control-group">				<div class="controls">					<div class="input-icon left">						<i class="icon-envelope"></i>						<input class="m-wrap placeholder-no-fix" type="text" placeholder="电子邮箱" name="email"/>					</div>				</div>			</div>            <div class="control-group">                <label class="control-label">类型</label>                <div class="controls">                    <label class="radio">                        <span><input type="radio" name="type" value="1"  checked="">学生</span>                    </label>                    <label class="radio">                        <span class="checked"><input type="radio" name="type" value="0">老师</span>                    </label>                    <%--<span class="help-inline">老师类型注册需要人工验证</span>--%>                </div>            </div>			<div class="form-actions">				<button id="register-back-btn" type="button" class="btn">				<i class="m-icon-swapleft"></i>后退				</button>				<button type="submit" id="register-submit-btn" class="btn green pull-right">				注册<i class="m-icon-swapright m-icon-white"></i>				</button>			</div>		</form>		<!-- END REGISTRATION FORM -->	</div>	<div class="copyright">        湖北省智能互联网技术重点实验室	</div>	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->	<!-- BEGIN CORE PLUGINS -->	<script src="/frontend/lib/metronic/js/jquery-1.10.1.min.js" type="text/javascript"></script>	<script src="/frontend/lib/metronic/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->	<script src="/frontend/lib/metronic/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>	<script src="/frontend/lib/metronic/js/bootstrap.min.js" type="text/javascript"></script>	<!--[if lt IE 9]>	<!--<script src="/frontend/lib/metronic/js/excanvas.min.js"></script>-->	<!--<script src="/frontend/lib/metronic/js/respond.min.js"></script>-->	<!--[endif]-->	<script src="/frontend/lib/metronic/js/jquery.slimscroll.min.js" type="text/javascript"></script>	<script src="/frontend/lib/metronic/js/jquery.blockui.min.js" type="text/javascript"></script>	<script src="/frontend/lib/metronic/js/jquery.cookie.min.js" type="text/javascript"></script>	<script src="/frontend/lib/metronic/js/jquery.uniform.min.js" type="text/javascript"></script>	<!-- END CORE PLUGINS -->	<!-- BEGIN PAGE LEVEL PLUGINS -->	<script src="/frontend/lib/metronic/js/jquery.validate.min.js" type="text/javascript"></script>	<!-- END PAGE LEVEL PLUGINS -->	<!-- BEGIN PAGE LEVEL SCRIPTS -->	<%--<script src="/frontend/lib/metronic/js/app.js" type="text/javascript"></script>--%>	<script src="/frontend/lib/metronic/js/login.js" type="text/javascript"></script>	<!-- END PAGE LEVEL SCRIPTS -->	<script>		jQuery(document).ready(function() {		  App.init();		  Login.init();		});	</script>	<!-- END JAVASCRIPTS --><%--<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>--%><!-- END BODY --></html>