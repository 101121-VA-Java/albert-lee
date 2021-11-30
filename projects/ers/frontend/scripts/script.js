forceAuth();
getDashboard();
addLogoutButton();

let customizableFetch = (url, callback, optionalData=null) => {
    fetch(url, {
        headers: {
            Authorization: sessionStorage.token
        }
    })
    .then(res => res.json())
    .then(res => {
        callback(res, optionalData);
    })
    .catch(err => console.log(err.message))
}

let get = (resources, fn) => {
    customizableFetch(`http://localhost:8080/${resources}`, fn);
}

let showReimbursementsOfOneEmployee = () => {
    let searchId = document.getElementById('authorId').value;
    if(!searchId) return;
    customizableFetch(`http://localhost:8080/reimbursements?author-id=${searchId}`, showReimbursements)
}

function getEmployee(resources, fn) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && (xhr.status >= 200 && xhr.status < 300)) {
            let response = xhr.response;
            response = JSON.parse(response);
            fn(response, roleString);
        }
    }
    xhr.open('GET', `http://localhost:8080/${resources}`);
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.send();
}

let put = (resources, resourceId, data) => {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && (xhr.status >= 200 && xhr.status < 300)) {
            let table = document.getElementById(`table`);
            let outdated = document.getElementById(resourceId);
            let index = null;
            if(outdated) index = outdated.rowIndex;
            if(table && index) table.deleteRow(index);
            document.getElementById("error-div").innerHTML = `Request #${resourceId} successfully resolved`;
        } else {
            document.getElementById("error-div").innerHTML = "Update failed.";
        }
    }
    xhr.open('PUT', `http://localhost:8080/${resources}/${resourceId}`);
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.send(JSON.stringify(data));
}

let showUsers = (res = [], str = "ALL") => {
    let container = document.getElementById("main-content");
    if (container) {
        container.innerHTML = "";
        for (let i = 0; i < res.length; i++) {
            let el = res[i];
            if (el.role === str || (str === "ALL" && el.role !== "ADMIN")) {
                let newEl = document.createElement("div");
                newEl.innerHTML = el.email;
                container.append(newEl);
            }
        }
    }
}

let getUsers = (typeOfUserToShow) => {
    let url = `http://localhost:8080/users`
    customizableFetch(url, showUsers, typeOfUserToShow);
}

let showEmployeeInfo = () => {
    let container = document.getElementById("main-content")
    if(!container) return;
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && (xhr.status >= 200 && xhr.status < 300)) {
            let response = xhr.response;
            response = JSON.parse(response);
            let {username, email, firstName, lastName} = response;
            container.innerHTML = 
                `<form id="register-form" onsubmit="return false">
                    <h4>Update your profile</h4>
                    <div class="form-group">
                        <input type="text" class="form-control" id="username" aria-describedby="userNameHelp"
                            value=${username}>
                            Username must be 50 characters or less.
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" placeholder="Please enter your password, or enter a new one">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                            value=${email}>
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
                            else.</small>
                    </div>
                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <input type="text" class="form-control" id="firstName" aria-describedby="firstNameHelp"
                            value=${firstName}>
                        <small id="firstNameHelp" class="form-text text-muted">
                            First name must be 100 characters or less.
                        </small>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" id="lastName" aria-describedby="lastNameHelp"
                            value=${lastName}>
                        <small id="lastNameHelp" class="form-text text-muted">
                            Last name must be 100 characters or less.
                        </small>
                    </div>
                        <button class="btn btn-primary" onclick={updateEmployeeInfo()}>Update</button>
                        <button class="btn btn-secondary" onclick={showEmployeeInfo()}>Refresh</button>
                </form>
                <div id="error-div"></div>`;
        } 
        else if (xhr.readyState === 4) {
            document.getElementById("error-div").innerHTML = "came back at least.";
        }
    }
    xhr.open('GET', `http://localhost:8080/users?id=${sessionStorage.token.split(":")[0]}`);
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.send();
}

let updateEmployeeInfo = () => {
    let arr = sessionStorage.token.split(":");
    let id = arr[0];
    let role = arr[1];
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let data = { id, role, username, password, email, firstName, lastName };
    for (const key in data) if (!data[key]) return;
    put("users", sessionStorage.token.split(":")[0], data);
}

let getReimbursements = (typeOfReimbursementToShow) => {
    let url = `http://localhost:8080/reimbursements`;
    customizableFetch(url, showReimbursements, typeOfReimbursementToShow)
}

let showReimbursements = (res = [], stringOfTypeToDisplay) => {

    let container = document.getElementById("main-content")
    if(!container) return; 
    else if(container) container.innerHTML = '';
    if(res.length <= 0) container.innerHTML = `No ${stringOfTypeToDisplay.toLowerCase()} reimbursements found`;
    let result = '';
    switch (stringOfTypeToDisplay) {
        case 'PENDING':
            result += `<h4 class="header-padding-fix">Pending reimbursements</h4>`
            break;
        case 'RESOLVED':
            result += `<h4 class="header-padding-fix">Resolved reimbursements</h4>`
            break;
        default:
            result += `<h4 class="header-padding-fix">All reimbursements</h4>`
            break;
    }
    result +=
        `<table id="table" class="table table-hover">` +
            `<thead>` +
                `<tr>` +
                    `<th scope="col">ID</th>` +
                    `<th scope="col">Amount</th>` +
                    `<th scope="col">Description</th>` +
                    `<th scope="col">Author ID</th>` +
                    `<th scope="col">Resolver ID</th>` +
                    `<th scope="col"></th>` +
                    `<th scope="col"></th>` +
                    `<th scope="col"></th>` +
                `</tr>` +
            `</thead>` +
            `<tbody>`;

    let role = sessionStorage.token.split(':')[1];
    for (let i = 0; i < res.length; i++) {
        let reimb = res[i];
        let {statusId} = reimb;
        if(role === 'BASIC') {
            if(stringOfTypeToDisplay === "ALL") 
                result += reimbursementRow(reimb);
            else if(stringOfTypeToDisplay === "PENDING" && statusId === 0)
                result += reimbursementRow(reimb);
            else if(stringOfTypeToDisplay === "RESOLVED" &&  statusId > 0)
                result += reimbursementRow(reimb);
        } 
        else if(role === 'MANAGER') {
            if(!stringOfTypeToDisplay || stringOfTypeToDisplay === "ALL")
                result += reimbursementRowWithReceiptImage(reimb);
            else if(stringOfTypeToDisplay === "PENDING" && statusId === 0) 
                result += reimbursementRowWithApproveOrDenyButtons(reimb);
            
            else if(stringOfTypeToDisplay === "RESOLVED" && statusId > 0) 
                result += reimbursementRowWithReceiptImage(reimb);
            
        }
    }
    result += `</tbody></table>`
    result += `<div id="error-div"></div>`;
    container.innerHTML = result;
}

let reimbursementRow = (reimbursement) => {
    let {amount, description, authorId, resolverId, id, statusId} = reimbursement;
    let result = '';
    return result +=
    `<tr id=${id}>` +
        `<th scope="row">${id}</th>` +
        `<th scope="row">${amount}</th>` +
        `<th scope="row">${description}</th>` +
        `<th scope="row">${authorId}</th>` +
        `<th scope="row">${resolverId}</th>` +
    `</tr>`;
}

let reimbursementRowWithReceiptImage = (reimbursement) => {
    let {amount, description, authorId, resolverId, id, statusId} = reimbursement;
    return (
        `<tr id=${id}>` +
            `<th scope="row">${id}</th>` +
            `<th scope="row">${amount}</th>` +
            `<th scope="row">${description}</th>` +
            `<th scope="row">${authorId}</th>` +
            `<th scope="row">${resolverId}</th>` +
            `<th scope="row"><img class="card-img-bottom-custom" src="" alt="reimbursement_receipt"/></th>` +
        `</tr>`);
}

let reimbursementRowWithApproveOrDenyButtons = (reimbursement) => {
    let {amount, description, authorId, resolverId, id} = reimbursement;
    return (
        `<tr id=${id}>` +
            `<th scope="row">${id}</th>` +
            `<th scope="row">${amount}</th>` +
            `<th scope="row">${description}</th>` +
            `<th scope="row">${authorId}</th>` +
            `<th scope="row">${resolverId}</th>` +
            `<th scope="row"><img class="card-img-bottom-custom" src="" alt="reimbursement_receipt"></th>` +
            `<th scope="row">
                <button class="btn btn-primary" onclick="put('reimbursements', ${id}, 1)">
                    Approve
                </button>
            </th>` +
            `<th scope="row">
                <button class="btn btn-red" onclick="put('reimbursements', ${id}, 2)">
                    Deny
                </button>
            </th>` +
        `<tr/>`
    );
}

function register() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let newUser = { username, password, email, firstName, lastName };
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 201) {
                console.log('Employee was successfully added!');
                login();
            } else {
                console.log('Employee was not added...');
            }
        }
    }
    xhr.open("POST", "http://localhost:8080/users");
    let requestBody = JSON.stringify(newUser);
    xhr.send(requestBody);
}

function login() {
    document.getElementById("error-div").innerHTML = "";
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/auth");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let authToken = xhr.getResponseHeader("Authorization");
            sessionStorage.setItem("token", authToken);
            getDashboard();
        } else if (xhr.readyState === 4) {
            document.getElementById("error-div").innerHTML = `
            <div class="alert alert-danger" role="alert">
            Wrong username and password combination. Please double check and again.
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            </div>`;
        }
    }

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let requestBody = `username=${username}&password=${password}`;
    xhr.send(requestBody);
}

function forceAuth() {
    let token = sessionStorage.getItem("token");
    let ref = window.location.href;
    let ok = ["login", "register", "index"];
    let safe = ok.some(url => ref.includes(url));
    if (!token && !safe)
        window.location.href = "/frontend/html/index.html";
}

function getDashboard() {
    let token = sessionStorage.getItem("token");
    let ref = window.location.href;
    let ok = ["dashboard", "index", "reimbursement", "profile"];
    let safe = ok.some(url => ref.includes(url));
    if (token && !safe) {
        let d = "";
        if (token.split(":")[1] === "MANAGER") {
            d = "manager_dashboard";
        } else if (token.split(":")[1] === "BASIC") {
            d = "employee_dashboard";
        } else if (token.split(":")[1] === "ADMIN") {
            d = "admin_dashboard";
        }
        if (d.length > 0) window.location.href = `/frontend/html/${d}.html`;
    }
}

let setUpReimbursementForm = (container = document.getElementById("employee-dash")) => {
    if (container) window.location.href = "reimbursement_form.html";
}

let addReimbursement = () => {
    if(!sessionStorage.token) return;
    let amount = document.getElementById("reimb-amount").value;
    let description = document.getElementById("reimb-description").value;
    let arr = document.getElementsByName("flexRadioDefault");
    let checkedElement = null;
    for (let i = 0; i < arr.length; i++) if(arr[i].checked) checkedElement = arr[i];
    let typeId = checkedElement.id.split("-")[1];
    switch (typeId) {
        case "lodging":
            typeId = "1";
            break;
        case "travel":
            typeId = "2";
            break;
        case "food":
            typeId = "3";
            break;
        case "other":
            typeId = "4";
            break;
        default:
            break;
    }
    let authorId = sessionStorage.token.split(":")[0];
    let newReimbursement = { amount, description, authorId, typeId };
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 201) {
                console.log('Reimbursement was successfully added!');
            } else {
                console.log('Reimbursement was not added...');
            }
        }
    }
    xhr.open("POST", "http://localhost:8080/reimbursements");
    let requestBody = JSON.stringify(newReimbursement);
    xhr.send(requestBody);
}

let logout = () => {
    sessionStorage.clear();
    window.location.href = "/frontend/html/index.html";
}

function addLogoutButton() {
    if(!sessionStorage.token) return; 
    let navList = document.getElementById("navbar-unordered-elements");
    if(!navList) return;
    let logoutButton = document.createElement("li");
    logoutButton.className = "nav-item";
    logoutButton.innerHTML = `<a class="nav-link" onclick="logout()" href="/frontend/html/index.html">Logout</a>
    `;
    navList.appendChild(logoutButton);
}