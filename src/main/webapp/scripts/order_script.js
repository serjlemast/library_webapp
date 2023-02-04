getAllOrdersFromServer();

function getAllOrdersFromServer() {
    fetch(baseUrl + '/orders', {method: 'GET'})
        .then(response => response.json())
        .then(jsonResponse => createOrderList(jsonResponse))
}

function createOrderList(orderInJsonFormat) {

    let divElement = document.getElementById("orders");

    if (document.getElementById("orders") != null) {
        document.getElementById("orders").innerHTML = ""
    }
    orderInJsonFormat.forEach(function (item) {

        let rootElement = document.createElement("tr");

        rootElement.setAttribute("id", "li_id_" + item.id)

        let thElement = document.createElement("th");
        let td1Element = document.createElement("td");
        let td2Element = document.createElement("td");
        let td3Element = document.createElement("td");
        let td4Element = document.createElement("td");


        thElement.appendChild(document.createTextNode(item.id));
        thElement.setAttribute("id", "order_id_" + item.id)
        thElement.setAttribute("type", "button")
        thElement.onclick = function (ev) {
            eventSelectOrder(item)
        };

        td1Element.appendChild(document.createTextNode(item.userId));

        td2Element.appendChild(document.createTextNode(item.librarianId));

        td3Element.appendChild(document.createTextNode(item.bookIds));

        td4Element.appendChild(document.createTextNode(item.createDate));

        rootElement.appendChild(thElement)
        rootElement.appendChild(td1Element)
        rootElement.appendChild(td2Element)
        rootElement.appendChild(td3Element)
        rootElement.appendChild(td4Element)

        divElement.appendChild(rootElement);
    })
}

function eventSelectOrder(order) {
    document.getElementById("orderButton").value = order.id;
    document.getElementById("newOrderUserId").value = order.userId;
    document.getElementById("newOrderLibrarianId").value = order.librarianId;
}

function eventCreateNewOrderByUser() {
    let newOrderUserIdValue = document.getElementById("newOrderUserId").value;
    let newOrderBookIdValue = document.getElementById("newOrderBookId").value;
    let order = {
        userId: newOrderUserIdValue,
        bookIds: newOrderBookIdValue
    };
    if (newOrderUserIdValue === "") {
        alert("Please fill user id  !!!")
        return;
    }
    if (newOrderBookIdValue === "") {
        alert("Please fill book id !!!")
        return;
    }
    fetch(baseUrl + '/orders', {method: "POST", body: JSON.stringify(order)})
        .then(response => response.json())
        .then(response => {

            let id = response.id

            let divElement = document.getElementById("orders");

            let rootElement = document.createElement("tr");

            rootElement.setAttribute("id", "li_id_" + id)

            let thElement = document.createElement("th");
            let td1Element = document.createElement("td");
            let td2Element = document.createElement("td");
            let td3Element = document.createElement("td");
            let td4Element = document.createElement("td");


            thElement.appendChild(document.createTextNode("0"));
            thElement.setAttribute("id", "order_id_" + id)
            thElement.setAttribute("type", "button")

            td1Element.appendChild(document.createTextNode(order.userId));

            td3Element.appendChild(document.createTextNode(order.bookIds));

            rootElement.appendChild(thElement)
            rootElement.appendChild(td1Element)
            rootElement.appendChild(td2Element)
            rootElement.appendChild(td3Element)
            rootElement.appendChild(td4Element)

            divElement.appendChild(rootElement);
        })
}
