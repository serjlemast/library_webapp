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
        let baseUrl = 'http://localhost:8082/liberary_war_exploded/librarian'
    </script>

    <script type="text/javascript" src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <script>
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
                let td5Element = document.createElement("td");


                thElement.appendChild(document.createTextNode(item.id));
                thElement.setAttribute("id", "order_id_" + item.id)
                thElement.setAttribute("type", "button")
                thElement.onclick = function (ev) {
                    eventSelectOrder(item)
                };

                td1Element.appendChild(document.createTextNode(item.userId));

                if (item.status === 'CREATED'){
                    td2Element.setAttribute("class", "text-primary")
                }
                if (item.status === 'CANCELED'){
                    td2Element.setAttribute("class", "text-danger")
                }
                td2Element.appendChild(document.createTextNode(item.status));

                if (item.bookIds === undefined){
                    td3Element.appendChild(document.createTextNode("no value"));
                }
                else {
                    td3Element.appendChild(document.createTextNode(item.bookIds));
                }

                td4Element.appendChild(document.createTextNode(item.createDate));

                td5Element.appendChild(document.createTextNode(item.returnDate));

                rootElement.appendChild(thElement)
                rootElement.appendChild(td1Element)
                rootElement.appendChild(td2Element)
                rootElement.appendChild(td3Element)
                rootElement.appendChild(td4Element)
                rootElement.appendChild(td5Element)

                divElement.appendChild(rootElement);
            })
        }

        function eventSelectOrder(order) {
            document.getElementById("orderButton").value = order.id;
            document.getElementById("newOrderUserId").value = order.userId;
            document.getElementById("newBookIds").value = order.bookIds;
        }
    </script>

    <link type="text/css" rel="stylesheet" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css">

</head>

<body>
<div class="container">
    <div class="row">

                <div class="bd-example bd-example-tabs ">

                    <ul class="nav nav-tabs nav-fill" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link" id="home-tab" data-toggle="tab" href="#homeTab" role="tab"
                               aria-controls="homeTab" aria-selected="false">Orders</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%= request.getContextPath() %>/logout"
                               aria-controls="categoryTab" aria-selected="false">Logout</a>
                        </li>
                    </ul>

                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade active show" id="homeTab" role="tabpanel" aria-labelledby="home-tab">
                            <jsp:include page="pages/librarian_order.jsp"/>
                        </div>
                    </div>
                </div>

    </div>
</div>
</body>
</html>