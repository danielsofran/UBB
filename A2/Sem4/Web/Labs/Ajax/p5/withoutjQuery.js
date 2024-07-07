
document.getElementsByTagName('p')[0].innerText = "Ajax";

function clearDir(container) {
    container.innerHTML = container.getElementsByTagName('p')[0].outerHTML;
}

function loadDir(container, path) {
    clearDir(container);
    fetch("loadContent.php?path=" + path).then(response => response.text()).then(data => {
        container.innerHTML += data;
    });
}

function loadFile(path){
    fetch("loadContent.php?path=" + path).then(response => response.text()).then(data => {
        document.getElementById('content').innerText = data;
    });
}