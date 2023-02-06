<div class="container">
    <div class="row">
        <div class="col">
            <div>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <input type="text" class="form-control" id="userId" placeholder="User Id"
                               aria-label="name"
                               aria-describedby="basic-addon1">
                        <select class="form-select" id="orderStatus" aria-label="Default select example">
                            <option value="CREATED">CREATED</option>
                            <option value="CANCELED">CANCELED</option>
                            <option value="ACCESS">ACCESS</option>
                        </select>
                        <button class="btn btn-outline-secondary" id="searchButton"
                                onclick="eventFindOrderByCredentials()"
                                type="button">
                            Search
                        </button>
                    </div>
                </div>

                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">User id</th>
                        <th scope="col">Status</th>
                        <th scope="col">Book</th>
                        <th scope="col">Subscription</th>
                        <th scope="col">Create date</th>
                        <th scope="col">Return date</th>
                    </tr>
                    </thead>
                    <tbody id="orders"></tbody>
                </table>
            </div>
        </div>

        <div class="col-md">
            <div class="form-floating">
                <label for="newOrderUserId" class="form-label">User id</label>
                <input type="text" class="form-control" id="newOrderUserId" placeholder="session id!!!">
            </div>
            <br>
            <br>
            <div class="form-floating">
                <label for="newOrderBookIds" class="form-label">Book ids</label>
                <input type="text" class="form-control" id="newOrderBookIds" placeholder="book ids">
            </div>
            <br>
            <br>
            <div class="form-group">
                <label for="orderStatus1" class="form-label">New subscription</label>
                <select class="form-select" id="orderSubscription" aria-label="Default select example">
                    <option value="true">TRUE</option>
                    <option value="false">FALSE</option>
                </select>
            </div>
            <br>
            <br>
            <div class="form-floating">
                <label for="date" class="form-label">Return date</label>
                <input type="date" id="date">
            </div>
            <br>
            <br>
            <label for="orderStatus1" class="form-label">New status</label>
            <select class="form-select" id="orderStatus1" aria-label="Default select example">
                <option value="CANCELED">CANCELED</option>
                <option value="ACCESS">ACCESS</option>
            </select>
            <br>
            <br>
            <div class="d-grid gap-2 d-md-block">
                <button type="button" id="orderButton" onclick="eventUpdateOrder()" class="btn btn-primary">Update</button>
            </div>
        </div>

    </div>
</div>

