$(document).ready(function() {
    const addresbookId = window.location.href.split("=").pop();
    $.ajax({
        type: "GET",
        url: "showaddressbook/api/" + addresbookId,
        cache: false,
        async: false,
        dataType: "json"
    }).then(function(data) {
        let table = document.getElementById("buddy-table");
        for (let i = 0; i < data.buddies.length; i++) {
            let row = document.createElement('tr');
            let c1 = document.createElement("td")
            let c2 = document.createElement("td")
            let c3 = document.createElement("td")
            let c4 = document.createElement("td")
            c1.innerText = data.buddies[i].id;
            c2.innerText = data.buddies[i].name;
            c3.innerText = data.buddies[i].phoneNumber;
            c4.innerText = data.buddies[i].address;
            row.appendChild(c1);
            row.appendChild(c2);
            row.appendChild(c3);
            row.appendChild(c4);
            table.appendChild(row);
        }
    });
});