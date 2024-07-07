
fetch("php/getFilters.php").then(response => response.json()).then(data => {
    console.log(data);
    let filters = data;
    for (const filtername in filters) {
        console.warn(filtername);
        let filterdata = filters[filtername];
        filterdata.forEach(filter => {
            let filterElement = document.createElement("li");
            let checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.name = filtername;
            checkbox.value = filter[0];
            filterElement.appendChild(checkbox);
            filterElement.appendChild(document.createTextNode(filter[0]));

            document.getElementById(filtername).appendChild(filterElement);
        });

    }
});

function createQueryParams(data)
{
    let queryParams = [];
    for(let key in data)
    {
        let value = data[key];
        if(Array.isArray(value))
        {
            if(value.length === 0)
                queryParams.push(key + "=");
            value.forEach(value => {
                queryParams.push(key + "[]=" + encodeURIComponent(value));
            });
        }
        else
        {
            queryParams.push(key + "=" + encodeURIComponent(value));
        }
    }
    return queryParams.join("&");
}

function loadContent(){
    let form = document.forms["form"];
    let formData = new FormData(form);
    let data = {};
    data['producatori'] = [];
    data['procesoare'] = [];
    data['memorii'] = [];
    for (const [key, value] of formData.entries())
        data[key].push(value);

    let url = "php/getContent.php?"+createQueryParams(data);
    // console.log(url);
    fetch(url, {
        method: "GET",
    }).then(response => response.json()).then(data => {
        console.log(data);
        let content = data;
        let contentElement = document.getElementById("content");
        contentElement.innerHTML = "";
        content.forEach(element => {
            let row = document.createElement("tr");

            let name = document.createElement("td");
            name.appendChild(document.createTextNode(element[0]));
            row.appendChild(name);

            let poducator = document.createElement("td");
            poducator.appendChild(document.createTextNode(element[1]));
            row.appendChild(poducator);

            let procesor = document.createElement("td");
            procesor.appendChild(document.createTextNode(element[2]));
            row.appendChild(procesor);

            let memorie = document.createElement("td");
            memorie.appendChild(document.createTextNode(element[3]));
            row.appendChild(memorie);

            contentElement.appendChild(row);
        });
    });
    //
    // alert("Content loaded")
}