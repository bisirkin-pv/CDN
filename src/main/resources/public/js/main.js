;window.onload = function () {
    var sourceElement = document.querySelector("#js-save-cdn");
    sourceElement.addEventListener("click", function(obj){
        console.log(_load("http://localhost:8080/api/save"));
    }, true);

};

function _loadJSON(path, callback) {
    var xhr = new XMLHttpRequest();
    xhr.overrideMimeType("application/json");
    xhr.open('POST', path, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == "200") {
            callback(xhr.responseText);
        }
    };
    var github_name = document.querySelector("#github_name");
    var github_url = document.querySelector("#github_url");
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send("github_name="+github_name.value+"&github_url="+github_url.value);
}

/*
    Object: ColorCode, private, Filling out the JSON file
    In: String path - path to JSON file containing the syntax
    return: Object actual_JSON - Processed JSON object.
*/
function _load(path) {
    var actual_JSON = {};
    _loadJSON(path||'', function(response) {
        try{
            actual_JSON = JSON.parse(response);
        }catch(ex){
            console.log('ColorCode.load: Error parse JSON file.');
        }
    });
    return actual_JSON;
};