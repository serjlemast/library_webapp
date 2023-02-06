<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <script type="text/javascript">
        let baseUrl = 'http://localhost:8082/library_webapp_war/admin'
    </script>

    <script type="text/javascript" src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        getDefaultCountOfUsersFromServer();
        createButtonsForPagination();

        function getDefaultCountOfUsersFromServer() {
            fetch(baseUrl + '/users', {method: 'GET'})
                .then(response => response.json())
                .then(jsonResponse => createUserList(jsonResponse))
        }
        function createButtonsForPagination() {
            fetch(baseUrl + "/users/count", {method: 'GET'})
                .then(response => response.json())
                .then(jsonResponse => createPaginationButtons(jsonResponse))
        }

        function createUserList(userInJsonFormat) {
            let divElement = document.getElementById("users");

            if (document.getElementById("users") != null) {
                document.getElementById("users").innerHTML = ""
            }

            userInJsonFormat.forEach(function (item) {

                let rootElement = document.createElement("tr");

                rootElement.setAttribute("id", "li_id_" + item.id)

                let thElement = document.createElement("th");
                let td1Element = document.createElement("td");
                let td2Element = document.createElement("td");
                let td3Element = document.createElement("td");
                let td4Element = document.createElement("td");
                let enableButton = document.createElement("button");
                let deleteButton = document.createElement("button");


                enableButton.innerHTML = "Enable";
                enableButton.type = "button";
                enableButton.setAttribute("id", "enable_id_" + item.id)
                enableButton.value = item.blocked;
                enableButton.onclick = function () {
                    eventBlockUser(item.id);
                }

                if (item.blocked === true) {
                    enableButton.setAttribute("class", "btn btn-info");
                } else {
                    enableButton.setAttribute("class", "btn btn-secondary");
                }

                deleteButton.innerHTML = "Delete";
                deleteButton.type = "button";
                deleteButton.setAttribute("class", "btn btn-danger");

                deleteButton.onclick = function (ev) {
                    eventDeleteUserById(item.id)
                };

                thElement.appendChild(document.createTextNode(item.id));
                thElement.setAttribute("id", "user_id_" + item.id)
                thElement.setAttribute("type", "button")
                thElement.onclick = function (ev) {
                    eventSelectUser(item)
                };

                td1Element.appendChild(document.createTextNode(item.firstName));
                td1Element.setAttribute("id", "first_name_id_" + item.id)

                td2Element.appendChild(document.createTextNode(item.secondName));
                td2Element.setAttribute("id", "second_name_id_" + item.id)

                td3Element.appendChild(enableButton);
                td4Element.appendChild(deleteButton);

                rootElement.appendChild(thElement)
                rootElement.appendChild(td1Element)
                rootElement.appendChild(td2Element)
                rootElement.appendChild(td3Element)
                rootElement.appendChild(td4Element)

                divElement.appendChild(rootElement);
            })
        }

        function getUsersFromServerByPageNumber(page_number) {
            fetch(baseUrl + "/users?page_number=" + page_number, {method: 'GET'})
                .then(response => response.json())
                .then(jsonResponse => createUserList(jsonResponse))
        }
        function createPaginationButtons(countOfUser) {
            let divElement2 = document.getElementById("paginationButtons");
            let rootElement2 = document.createElement("ui");

            if (document.getElementById("paginationButtons") != null) {
                document.getElementById("paginationButtons").innerHTML = ""
            }

            rootElement2.setAttribute("class", "pagination")
            for (let i = 1; i < countOfUser.total / countOfUser.limit + 1; i++) {
                let childElement2 = document.createElement("button");
                childElement2.setAttribute("type", "button")
                childElement2.setAttribute("class", "btn btn-secondary btn-sm btn-outline-dark")
                childElement2.setAttribute("id", "buttons_" + i)
                let text2 = document.createTextNode(i);
                childElement2.appendChild(text2);
                rootElement2.appendChild(childElement2)
            }
            divElement2.appendChild(rootElement2);
            divElement2.addEventListener('click', (event) => {
                const isButton = event.target.nodeName === 'BUTTON';
                if (!isButton) {
                    return;
                }
                getUsersFromServerByPageNumber(event.target.id.replace("buttons_", ""));
            })
        }

        function eventCreateNewUser() {
            let newUserNameValue = document.getElementById("newUserName").value;
            let newUserPasswordValue = document.getElementById("newUserPassword").value;
            let newUserFirstNameValue = document.getElementById("newUserFirstName").value;
            let newUserSecondNameValue = document.getElementById("newUserSecondName").value;
            //todo ROLE
            let newUserRoleValue = document.getElementById("newUserRole").value;
            let user = {
                username: newUserNameValue,
                password: newUserPasswordValue,
                firstName: newUserFirstNameValue,
                secondName: newUserSecondNameValue
            };
            if (newUserPasswordValue === "") {
                alert("Please fill password  !!!")
                return;
            }
            if (newUserNameValue === "") {
                alert("Please fill user name !!!")
                return;
            }
            fetch(baseUrl + '/users', {method: "POST", body: JSON.stringify(user)})
                .then(response => response.json())
                .then(response => {

                    let id = response.id

                    let divElement = document.getElementById("users");

                    let rootElement = document.createElement("tr");

                    rootElement.setAttribute("id", "li_id_" + id)

                    let thElement = document.createElement("th");
                    let td1Element = document.createElement("td");
                    let td2Element = document.createElement("td");
                    let td3Element = document.createElement("td");
                    let td4Element = document.createElement("td");
                    let enableButton = document.createElement("button");
                    let deleteButton = document.createElement("button");

                    enableButton.innerHTML = "Enable";
                    enableButton.type = "button";
                    enableButton.setAttribute("class", "btn btn-secondary");

                    deleteButton.innerHTML = "Delete";
                    deleteButton.type = "button";
                    deleteButton.setAttribute("class", "btn btn-danger");

                    thElement.appendChild(document.createTextNode(id));
                    td1Element.appendChild(document.createTextNode(user.firstName));
                    td2Element.appendChild(document.createTextNode(user.secondName));
                    td3Element.appendChild(enableButton);
                    td4Element.appendChild(deleteButton);

                    rootElement.appendChild(thElement)
                    rootElement.appendChild(td1Element)
                    rootElement.appendChild(td2Element)
                    rootElement.appendChild(td3Element)
                    rootElement.appendChild(td4Element)

                    divElement.appendChild(rootElement);
                })
        }

        function eventSelectUser(user) {
            document.getElementById("newUserName").value = user.username;
            document.getElementById("newUserPassword").value = user.password;
            document.getElementById("newUserFirstName").value = user.firstName;
            document.getElementById("newUserSecondName").value = user.secondName;
            document.getElementById("updateButton").value = user.id;
        }

        function eventUpdateUser() {
            let newUserNameValue = document.getElementById("newUserName").value;
            let newUserPasswordValue = document.getElementById("newUserPassword").value;
            let newUserFirstNameValue = document.getElementById("newUserFirstName").value;
            let newUserSecondNameValue = document.getElementById("newUserSecondName").value;
            let userIdValue = document.getElementById("updateButton").value;
            //todo ROLE
            let newUserRoleValue = document.getElementById("newUserRole").value;
            let user = {
                username: newUserNameValue,
                password: newUserPasswordValue,
                firstName: newUserFirstNameValue,
                secondName: newUserSecondNameValue,
                id: userIdValue
            };
            if (newUserPasswordValue === "") {
                alert("Please fill password  !!!")
                return;
            }
            if (newUserNameValue === "") {
                alert("Please fill user name !!!")
                return;
            }
            fetch(baseUrl + '/users', {method: "PUT", body: JSON.stringify(user)})
                .then(response => {
                    //todo smt wrong with value
                    document.getElementById("first_name_id_" + userIdValue).innerText = document.getElementById("newUserFirstName").value;
                    document.getElementById("first_name_id_" + userIdValue).value = document.getElementById("newUserFirstName").value;
                    document.getElementById("second_name_id_" + userIdValue).innerText = document.getElementById("newUserSecondName").value;
                    document.getElementById("second_name_id_" + userIdValue).value = document.getElementById("newUserSecondName").value;
                })
        }

        function eventBlockUser(id) {
            let userIdValue = id;
            let isBlocked = true;
            let enableButton = document.getElementById("enable_id_" + id)
            if (enableButton.value === 'false') {
                enableButton.value = true;
                enableButton.setAttribute("class", "btn  btn-info ");
            } else {
                enableButton.value = false;
                isBlocked = false;
                enableButton.setAttribute("class", "btn btn-secondary");
            }
            let user = {
                id: userIdValue,
                blocked: isBlocked
            };
            fetch(baseUrl + '/users/blocked',
                {
                    method: "PUT",
                    body: JSON.stringify(user)
                })
        }

        function eventDeleteUserById(id) {
            let userIdElement = id;
            fetch(baseUrl + '/users/' + userIdElement, {method: 'DELETE'})
                .then(response => {
                    console.log(response)
                    document.getElementById("li_id_" + userIdElement).remove()
                })
        }

        function eventFindUserByCredentials() {
            let newUserNameValue = document.getElementById("newUserName").value;
            let newUserPasswordValue = document.getElementById("newUserPassword").value;

            if (document.getElementById("users") != null) {
                document.getElementById("users").innerHTML = "";
            }

            let user = {
                username: newUserNameValue,
                password: newUserPasswordValue
            };
            if (newUserPasswordValue === "") {
                alert("Please fill password  !!!")
                return;
            }
            if (newUserNameValue === "") {
                alert("Please fill user name !!!")
                return;
            }
            fetch(baseUrl + '/users/find?' + 'username=' + user.username + '&password=' + user.password, {method: 'GET'})
                .then(response => response.json())
                .then(jsonResponse => {
                    createUserList(jsonResponse);
                })
        }
    </script>
    <script type="text/javascript">
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
    </script>
    <script type="text/javascript">
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

    </script>


    <link type="text/css" rel="stylesheet" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css">


    <title>Title</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="bd-example bd-example-tabs ">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active show" id="home-tab" data-toggle="tab" href="#userTab" role="tab"
                       aria-controls="userTab" aria-selected="false">USER</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#bookTab" role="tab"
                       aria-controls="roleTab" aria-selected="false">BOOK</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#orderTab" role="tab"
                       aria-controls="orderTab" aria-selected="false">CREATE ORDER</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>/logout"
                       aria-controls="categoryTab" aria-selected="false">Logout</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade active show" id="userTab" role="tabpanel" aria-labelledby="home-tab">
                    <jsp:include page="/pages/admin_user.jsp"/>
                </div>
                <div class="tab-pane fade" id="bookTab" role="tabpanel" aria-labelledby="profile-tab">
                    <jsp:include page="/pages/book.jsp"/>
                </div>
                <div class="tab-pane fade" id="orderTab" role="tabpanel" aria-labelledby="profile-tab">
                    <jsp:include page="/pages/order.jsp"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
