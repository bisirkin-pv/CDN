<#import "index.ftl" as u>

<@u.page>

<div class="container main">
    <div class="row">
        <div class="col-lg-12 col-md-12">
            <h1>${page_main_header}</h1>
        </div>
    </div>
    <div class="row">
        <form role="form">
            <div class="form-group">
                <label for="github_url">Enter the url from GitHub</label>
                <input type="text" class="form-control" id="github_url"
                       name="github_url"
                       placeholder="https://raw.githubusercontent.com"
                       pattern="(?=https:\/\/raw\.githubusercontent\.com\/).*"
                       title="URL must start with 'https://raw.githubusercontent.com'">
            </div>
            <div class="form-group">
                <label for="github_name">Small title for link</label>
                <input type="text" class="form-control" id="github_name" name="github_name" placeholder="title">
            </div>
            <button type="button"  class="btn btn-primary" id="js-save-cdn">Get URL</button>
        </form>
    </div>
</div>
<script src="/js/main.js"></script>
</@u.page>