<div class="container">
    <div class="row">
        <div class="col">
            <div>

                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Author</th>
                    </tr>
                    </thead>
                    <tbody id="books"></tbody>
                </table>
            </div>
        </div>

        <div class="col-md">
            <div class="form-floating">
                <label for="newBookName" class="form-label">Book name</label>
                <input type="text" class="form-control" id="newBookName" placeholder="book name">
            </div>
            <div class="input-group" id="datetimepicker1">
                <input type="text" class="form-control"/>
                <span class="input-group-addon">
                <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
            <br>
            <br>
            <div class="form-floating">
                <label for="newBookAuthor" class="form-label">author</label>
                <input type="text" class="form-control" id="newBookAuthor" placeholder="author name">
            </div>
            <br>
            <br>
            <div class="form-floating">
                <label for="newBookInfo" class="form-label">Book info</label>
                <input type="text" class="form-control" id="newBookInfo" placeholder="info">
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

