<#import "index.ftl" as u>

<@u.page>
<div class="login-block">
    <form class="form-horizontal login-form">
        <div class="form-group">
            <label for="inputLogin" class="col-sm-3 control-label">login:</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="inputLogin" id="inputLogin" placeholder="login">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword" class="col-sm-3 control-label">Password:</label>
            <div class="col-sm-9">
                <input type="password" class="form-control" name="inputPassword" id="inputPassword" placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9" id="error-text">

            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
                <div class="checkbox" style="display:none">
                    <label>
                        <input type="checkbox"> Remember me
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-9 col-sm-3">
                <button type="button" class="btn btn-primary" id="logIn_btn">
                    <span class="fa fa-sign-in" style="font-size:16px"></span>&nbsp;&nbsp;Sign in
                </button>
            </div>
        </div>
    </form>
</div>
<script src="/js/login.js"></script>
</@u.page>