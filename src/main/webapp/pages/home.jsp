<div class="container">
    <div class="row">

        <div class="col">
            <div>
                <div class="bd-example bd-example-tabs ">

                    <nav class="navbar navbar-light bg-light">
                        <span class="navbar-brand mb-0 h1">Info about user :</span>
                        <span class="navbar-brand mb-0 h1">UserName:  <%= request.getSession() != null ? request.getSession().getAttribute("sessionUserName") : "Session is null" %></span>
                        <span class="navbar-brand mb-0 h1">UserRole: <%= request.getSession() != null ? request.getSession().getAttribute("sessionUserRole") : "UserRole is null" %></span>
                    </nav>


                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <input type="text" class="form-control" id="bookId" placeholder="Book id" aria-label="id"
                                   aria-describedby="basic-addon1">
                            <input type="text" class="form-control" id="bookName" placeholder="Book name"
                                   aria-label="name"
                                   aria-describedby="basic-addon1">
                            <input type="text" class="form-control" id="bookAuthor" placeholder="Book author"
                                   aria-label="author"
                                   aria-describedby="basic-addon1">
                            <button class="btn btn-outline-secondary" id="searchButton"
                                    onclick="eventFindBookByCredentials(this.id)"
                                    type="button">
                                Search
                            </button>
                        </div>
                    </div>

                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col" id="b.id" value="ASC"
                                onclick="eventFindBookByCredentials(this.id)">
                                Id
                            </th>
                            <th scope="col" id="b.name" value="ASC"
                                onclick="eventFindBookByCredentials(this.id)">Name
                            </th>
                            <th scope="col" id="b.author" value="ASC"
                                onclick="eventFindBookByCredentials(this.id)">Author
                            </th>
                        </tr>
                        </thead>
                        <tbody id="books">
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

    </div>
</div>