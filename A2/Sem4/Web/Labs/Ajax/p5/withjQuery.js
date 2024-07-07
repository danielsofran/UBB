
$('p').text(baseDir)

function loadDir(container, path)
{
    let p = $(container).children()[0];
    $(container).empty()
    $(container).append(p)

    $.get("loadContent.php",{path: path}, function (data){
       $(container).append(data);
    });
}

function loadFile(path){
    $.get("loadContent.php", {path: path}, function (data) {
        $("#content").text(data)
    })
}