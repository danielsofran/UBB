
$(document).ready(function(){
    $.getJSON("php/getFilters.php", function(data){
        console.log(data);
        let filters = data;
        for (const filtername in filters) {
            console.warn(filtername);
            let filterdata = filters[filtername];
            filterdata.forEach(filter => {
                let filterElement = $("<li></li>");
                let checkbox = $("<input>");
                checkbox.attr('type', "checkbox");
                checkbox.attr('name', filtername);
                checkbox.attr('value', filter[0]);
                filterElement.append(checkbox);
                filterElement.append("<span>" + filter[0] + "</span>");

                $("#"+filtername).append(filterElement);
            });

        }
    })
})


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
    let formData = $("#form").serializeArray();
    console.log(formData);
    let data = {
        'producatori': [],
        'procesoare': [],
        'memorii': []
    };
    Object.keys(formData).forEach(key => {
        data[formData[key].name].push(formData[key].value);
    });

    let url = "php/getContent.php?"+createQueryParams(data);
    console.log(url);
    $.getJSON(url, function(data){
        console.log(data);
        $("#content").empty();
        data.forEach(product => {
            let row = $("<tr></tr>");
            row.append("<td>" + product[0] + "</td>");
            row.append("<td>" + product[1] + "</td>");
            row.append("<td>" + product[2] + "</td>");
            row.append("<td>" + product[3] + "</td>");
            $("#content").append(row);
        });
    });
    //
    // alert("Content loaded")
}