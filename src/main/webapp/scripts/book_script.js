getAllBooksFromServer();

function getAllBooksFromServer() {
    fetch(baseUrl + '/books', {method: 'GET'})
        .then(response => response.json())
        .then(jsonResponse => createBookList(jsonResponse))
}

function createBookList(bookInJsonFormat) {

    let divElement = document.getElementById("books");

    if (document.getElementById("books") != null) {
        document.getElementById("books").innerHTML = ""
    }

    bookInJsonFormat.forEach(function (item) {

        let rootElement = document.createElement("tr");

        rootElement.setAttribute("id", "li_id_" + item.id)

        let thElement = document.createElement("th");
        let td1Element = document.createElement("td");
        let td2Element = document.createElement("td");


        thElement.appendChild(document.createTextNode(item.id));
        thElement.setAttribute("id", "book_id_" + item.id)
        thElement.setAttribute("type", "button")

        td1Element.appendChild(document.createTextNode(item.name));

        td2Element.appendChild(document.createTextNode(item.author));

        rootElement.appendChild(thElement)
        rootElement.appendChild(td1Element)
        rootElement.appendChild(td2Element)

        divElement.appendChild(rootElement);
    })
}
