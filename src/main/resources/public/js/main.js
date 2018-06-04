;window.onload = function () {
    var sourceElement = document.querySelector("#js-save-cdn");
    sourceElement.addEventListener("click", function(obj){
        _load("api/save");
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

    _loadJSON(path||'', saveResult);
};

function saveResult(response) {
    var actual_JSON = {};
    try{
        actual_JSON = JSON.parse(response);
        document.querySelector("#js-result-save").innerHTML = actual_JSON.message;
    }catch(ex){
        console.log('Error parse JSON file.');
    }
}