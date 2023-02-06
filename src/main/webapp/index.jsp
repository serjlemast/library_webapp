<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 12/5/22
  Time: 12:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <script type="text/javascript">
        let baseUrl = '<%= request.getRequestURL() %>'
        let sessionUserName = '<%= request.getSession().getAttribute("sessionUserName") %>'
    </script>

    <script type="text/javascript" src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <script>
        getDefaultCountOfBooksFromServer();
        getAllOrdersFromServer();

        function getDefaultCountOfBooksFromServer() {
            fetch(baseUrl + '/books', {method: 'GET'})
                .then(response => response.json())
                .then(jsonResponse => createBookList(jsonResponse))
        }

        function createBookList(bookInJsonFormat) {
            let divElement = document.getElementById("books");

            if (document.getElementById("books") != null) {
                document.getElementById("books").innerHTML = "";
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
                thElement.onclick = function (ev) {
                    eventSelectBookId(item)
                };

                td1Element.appendChild(document.createTextNode(item.name));
                td1Element.setAttribute("id", "book_name_id_" + item.name)

                td2Element.appendChild(document.createTextNode(item.author));
                td2Element.setAttribute("id", "author_name_id_" + item.author)


                rootElement.appendChild(thElement)
                rootElement.appendChild(td1Element)
                rootElement.appendChild(td2Element)

                divElement.appendChild(rootElement);
            })
        }

        function getAllOrdersFromServer() {
            if (sessionUserName !== 'null') {
                fetch(baseUrl + '/orders', {method: 'GET'})
                    .then(response => response.json())
                    .then(jsonResponse => createOrderList(jsonResponse))
            } else {
                fetch(baseUrl + '/orders', {method: 'GET'})
            }
        }

        function eventSelectBookId(book) {
            let divElement = document.getElementById("bookIdButtons");
            let buttonElement = document.createElement("button");
            buttonElement.setAttribute("id", "book_button_id_" + book.id)
            buttonElement.setAttribute("type", "button")
            buttonElement.setAttribute("class", "btn btn-inf")
            buttonElement.appendChild(document.createTextNode(book.name));
            buttonElement.onclick = function (ev) {
                document.getElementById("book_button_id_" + book.id).remove()
            };
            divElement.appendChild(buttonElement);
        }

        function eventFindBookByCredentials(id) {
            let bookNameValue = document.getElementById("bookName").value;
            let bookAuthorValue = document.getElementById("bookAuthor").value;
            let bookIdValue = document.getElementById("bookId").value;
            let newCedingId = document.getElementById(id).value;


            if (newCedingId === 'ASC') {
                document.getElementById(id).value = 'DESC';
                newCedingId = 'DESC';
            } else {
                document.getElementById(id).value = 'ASC';
                newCedingId = 'ASC';
            }

            let book = {
                bookName: bookNameValue,
                bookAuthor: bookAuthorValue
            };

            let fullOrder = id + " " + newCedingId;

            if (id === 'searchButton') {
                fullOrder = ' ';
            }

            let fullUrl = '?book_name=' + book.bookName + '&book_author=' + book.bookAuthor + '&orderBy=' + fullOrder;

            if (bookIdValue != null && bookIdValue !== '') {
                fullUrl = "/" + bookIdValue;
            }

            fetch(baseUrl + '/books/find' + fullUrl, {method: 'GET'})
                .then(response => response.json())
                .then(jsonResponse => createBookList(jsonResponse))
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

                thElement.appendChild(document.createTextNode(item.id));
                thElement.setAttribute("id", "order_id_" + item.id)
                thElement.setAttribute("type", "button")

                td1Element.appendChild(document.createTextNode(item.status));

                td2Element.appendChild(document.createTextNode(item.bookNames));

                td3Element.appendChild(document.createTextNode(item.createDate));

                rootElement.appendChild(thElement)
                rootElement.appendChild(td1Element)
                rootElement.appendChild(td2Element)
                rootElement.appendChild(td3Element)

                divElement.appendChild(rootElement);
            })
        }

        function eventCreateNewOrderByUser() {

            if (sessionUserName === null || sessionUserName === 'null') {
                alert("Only log in user can order");
                return;
            }
            let collection = document.getElementById("bookIdButtons").children;
            let bookIds;
            for (var i = 0; i < collection.length; i++) {
                bookIds += collection[i].id;
            }
            let result = bookIds.split('book_button_id_');
            result.shift();

            let order = {
                bookIds: result.join()
            };

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

                    thElement.appendChild(document.createTextNode(id));
                    thElement.setAttribute("id", "order_id_" + id)
                    thElement.setAttribute("type", "button")

                    td1Element.appendChild(document.createTextNode('CREATED'));

                    td2Element.appendChild(document.createTextNode('in progress'));

                    const date = new Date();

                    let day = date.getDate();
                    let month = date.getMonth() + 1;
                    let year = date.getFullYear();

                    let currentDate = `${day}-${month}-${year}`;
                    td3Element.appendChild(document.createTextNode(currentDate));

                    rootElement.appendChild(thElement)
                    rootElement.appendChild(td1Element)
                    rootElement.appendChild(td2Element)
                    rootElement.appendChild(td3Element)

                    divElement.appendChild(rootElement);
                })
        }

        function eventFindOrderByStatus() {
            let newOrderStatus = orderStatus.value;

            let order = {
                status: newOrderStatus
            };

            fetch(baseUrl + '/orders/find' + '?status=' + order.status, {method: 'GET'})
                .then(response => response.json())
                .then(jsonResponse => createOrderList(jsonResponse))
        }
    </script>

    <link type="text/css" rel="stylesheet" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css">

</head>

<body>
<div class="container">
    <div class="row">
        <div class="col">
            <div>
                <div class="bd-example bd-example-tabs ">

                    <ul class="nav nav-tabs nav-fill" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active show" id="home-tab" data-toggle="tab" href="#homeTab" role="tab"
                               aria-controls="homeTab" aria-selected="false">USER</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="contact-tab" data-toggle="tab" href="#orderTab" role="tab"
                               aria-controls="orderTab" aria-selected="false">Orders</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%= request.getContextPath() %>/login"
                               aria-controls="categoryTab" aria-selected="false">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%= request.getContextPath() %>/logout"
                               aria-controls="categoryTab" aria-selected="false">Logout</a>
                        </li>
                    </ul>

                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade active show" id="homeTab" role="tabpanel" aria-labelledby="home-tab">
                            <jsp:include page="/pages/home.jsp"/>
                        </div>
                        <div class="tab-pane fade" id="orderTab" role="tabpanel" aria-labelledby="profile-tab">
                            <jsp:include page="pages/client_order.jsp"/>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="col-md">
            <div>
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group mr-2" role="group" aria-label="First group">
                        <button type="button" id="orderButton" class="btn btn-secondary"
                                onclick="eventCreateNewOrderByUser()">Order
                        </button>
                    </div>
                    <div class="btn-group mr-2" role="group" id="bookIdButtons" aria-label="Second group">
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>