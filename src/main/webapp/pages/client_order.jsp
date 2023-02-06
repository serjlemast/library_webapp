<div class="container">
    <div class="row">
        <div class="col">
            <div>

                <div class="input-group mb-3">

                    <select class="form-select" id="orderStatus" aria-label="Default select example">
                        <option value="CREATED">CREATED</option>
                        <option value="CANCELED">CANCELED</option>
                        <option value="ACCESS">ACCESS</option>
                    </select>

                    <button class="btn btn-outline-secondary" id="searchButton"
                            onclick="eventFindOrderByStatus()"
                            type="button">
                        Search
                    </button>
                </div>

                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Status</th>
                        <th scope="col">Book</th>
                        <th scope="col">Create date</th>
                    </tr>
                    </thead>
                    <tbody id="orders"></tbody>
                </table>
            </div>
        </div>

        <%--        <div class="col-md">--%>
        <%--        </div>--%>

    </div>
</div>

