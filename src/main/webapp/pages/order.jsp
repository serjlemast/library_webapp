<div class="container">
    <div class="row">
        <div class="col">
            <div>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">User</th>
                        <th scope="col">Librarian</th>
                        <th scope="col">Book</th>
                        <th scope="col">Create date</th>
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
                <label for="newOrderBookId" class="form-label">Book ids</label>
                <input type="text" class="form-control" id="newOrderBookId" placeholder="book ids">
            </div>
            <br>
            <br>
            <div class="form-floating">
                <label for="newOrderLibrarianId" class="form-label">Librarian id</label>
                <input type="text" class="form-control" id="newOrderLibrarianId" placeholder="librarian id">
            </div>
            <br>
            <br>
            <div class="d-grid gap-2 d-md-block">
                <button type="button" onclick="eventCreateNewOrderByUser()" class="btn btn-primary">Create</button>
                <button type="button"  id="orderButton" class="btn btn-primary">Order</button>
            </div>
        </div>
    </div>
</div>

