<div class="container">
    <div class="row">
        <div class="col">
            <div>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">Is enable user</th>
                        <th scope="col">Delete user</th>
                    </tr>
                    </thead>
                    <tbody id="users"></tbody>
                </table>
                <div class="form-group">
                    <nav aria-label="Page navigation example" id="paginationButtons"></nav>
                </div>
                <select class="form-select-sm" id="newLimit" aria-label="Default select example">
                    <option selected value="2">2</option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                </select>
                <button onclick="{console.log('clicks')}">paginate</button>
            </div>
        </div>
        <div class="col-md">
            <div class="col">
                <select class="form-select" id="newUserRole" aria-label="Default select example">
                    <option selected>Choose role</option>
                    <option value="1">LIBRARIAN</option>
                    <option value="2">CLIENT</option>
                </select>
            </div>
            <br>
            <br>
            <div class="form-floating">
                <label for="newUserName" class="form-label">User name</label>
                <input type="text" class="form-control" id="newUserName" placeholder="username">
            </div>
            <br>
            <br>
            <div class="form-floating">
                <label for="newUserPassword" class="form-label">Password</label>
                <input type="text" class="form-control" id="newUserPassword" placeholder="password">
            </div>
            <br>
            <br>
            <div class="form-floating">
                <label for="newUserFirstName" class="form-label">First name</label>
                <input type="text" class="form-control" id="newUserFirstName" placeholder="my name">
            </div>
            <br>
            <br>
            <div class="form-floating">
                <label for="newUserSecondName" class="form-label">Second name</label>
                <input type="text" class="form-control" id="newUserSecondName" placeholder="not my name">
            </div>
            <br>
            <br>
            <div class="d-grid gap-3 d-md-block">
                <button type="button" onclick="eventCreateNewUser()" class="btn btn-primary">Create</button>
                <button type="button" onclick="eventUpdateUser()" id="updateButton" class="btn btn-primary">Update
                </button>
                <button type="button" onclick="eventFindUserByCredentials()" class="btn btn-primary">Search</button>
            </div>
        </div>
    </div>
</div>

