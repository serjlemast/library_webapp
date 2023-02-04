<div class="container">
    <div class="row">
        <div class="col" >
            <div>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <input type="text" class="form-control" id="userId" placeholder="User Id"
                               aria-label="name"
                               aria-describedby="basic-addon1">
                        <input type="text" class="form-control" id="orderStatus" placeholder="Status"
                               aria-label="author"
                               aria-describedby="basic-addon1">
                        <button class="btn btn-outline-secondary" id="searchButton"
                                onclick="alert('TODO')"
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
                        <label for="newBookIds" class="form-label">Book ids</label>
                        <input type="text" class="form-control" id="newBookIds" placeholder="book ids">
                    </div>
                    <br>
                    <br>
                    <div class="form-floating">
                        <label for="date" class="form-label">Return date</label>
                        <input type="date" id="date">
                    </div>
                    <br>
                    <br>
                    <div class="d-grid gap-2 d-md-block">
                        <button type="button" id="orderButton" onclick="alert('TODO')" class="btn btn-primary">Create</button>
                    </div>
                </div>

    </div>
</div>

