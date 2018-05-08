<#import "index.ftl" as u>

<@u.page>

<div class="container main">
    <div class="row">
        <div class="col-lg-12 col-md-12">
            <h1>${page_main_header}</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h2>List url</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Short name</th>
                    <th scope="col">Type</th>
                    <th scope="col">Url</th>
                </tr>
                </thead>
                <tbody>
                    <#list cdnlist as cdn>
                        <tr>
                            <th scope="row">${cdn?counter}</th>
                            <#list cdn as elem>
                            <td>${elem}</td>
                            </#list>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</@u.page>