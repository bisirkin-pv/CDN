window.onload = function () {
    var sourceElement = document.querySelector(".table-list");
    sourceElement.addEventListener("click", function(event){
        if(event.target.classList.contains("delete-link")){
            _send('/api/delete'||'', event.target, showResult);
        }
    }, true);
};

/*
    Send post request
 */
function _send(path, element, callback) {
    var xhr = new XMLHttpRequest();
    xhr.overrideMimeType("application/json");
    xhr.open('POST', path, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == "200") {
            var res = callback(xhr.responseText);
            if(res){
                var el = element.parentElement.parentElement;
                if (el != null){
                    el.remove();
                }
            }
        }
    };
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var link = element.parentElement.parentElement.children[1].innerHTML;
    xhr.send("link="+link);
}

function showResult(response) {
    var actual_JSON = {};
    try{
        actual_JSON = JSON.parse(response);
        return actual_JSON.status == 200;
    }catch(ex){
        console.log('Error parse JSON file.');
    }
    return false;
}