forceAuth();
getDashboard();

function get(resources, fn, roleString = "ALL") {
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

function showReimbursementsOfOneEmployee() {
    let searchId = document.getElementById('authorId').value;
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && (xhr.status >= 200 && xhr.status < 300)) {
            let response = xhr.response;
            response = JSON.parse(response);
            showReimbursements(response);
        }
    }
    xhr.open('GET', `http://localhost:8080/reimbursements?author-id=${searchId}`);
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.send();
}

let clearMainContent = () => {
    let container = document.getElementById("main-content");
    if(container) container.innerHTML = "";
}

function getEmployee(resources, id, fn) {
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
            document.getElementById("error-div").innerHTML = "Update success.";
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
            `<div id="main-content">
                <form id="register-form" onsubmit="return false">
                    Update your information
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" aria-describedby="userNameHelp"
                            placeholder="${username}">
                        <small id="userNameHelp" class="form-text text-muted">
                            Username must be 50 characters or less.
                        </small>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" placeholder="Please enter your password, or enter a new one">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                            placeholder="${email}">
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
                            else.</small>
                    </div>
                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <input type="text" class="form-control" id="firstName" aria-describedby="firstNameHelp"
                            placeholder="${firstName}">
                        <small id="firstNameHelp" class="form-text text-muted">
                            First name must be 100 characters or less.
                        </small>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" id="lastName" aria-describedby="lastNameHelp"
                            placeholder="${lastName}">
                        <small id="lastNameHelp" class="form-text text-muted">
                            Last name must be 100 characters or less.
                        </small>
                    </div>
                        <button class="btn btn-primary" onclick={updateEmployeeInfo()}>Update</button>
                        <button class="btn btn-secondary" onclick={showEmployeeInfo()}>Refresh</button>
                </form>
                <div id="error-div"></div>
            </div>`;
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

let showReimbursements = (res = [], str = "ALL") => {
    let container = document.getElementById("main-content");
    if (container) {
        container.innerHTML = "";
        for (let i = 0; i < res.length; i++) {
            let el = res[i];
            if (str === "ALL" || (str === "PENDING" && el.statusId === 0) || (str === "RESOLVED" && el.statusId > 0)) {
                let newEl = document.createElement("div");
                newEl.innerHTML = el.description;
                if(el.statusId === 0 && (sessionStorage.token.split(":")[1]) === 'MANAGER'){
                    newEl.innerHTML = 
                        `<div>
                            ${"Amount: " + el.amount + " Description: " + el.description + " Author: " + el.authorId + " Resolver: " + el.resolverId}
                            <button onclick="put('reimbursements', ${el.id}, 1)">
                                Approve
                            </button>
                            <button onclick="put('reimbursements', ${el.id}, 2)">
                                Deny
                            </button>
                        </div>`
                } else if(el.statusId === 1 || el.statusId === 2){
                    newEl.innerHTML = 
                        `<div>
                            ${"Amount: " + el.amount + " Description: " + el.description + " Author: " + el.authorId + " Resolver: " + el.resolverId}
                        </div>`
                }
                container.append(newEl);
            }
        }
        let errorDiv = document.createElement("div");
        errorDiv.id = "error-div";
        container.append(errorDiv);
    }
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
            document.getElementById("error-div").innerHTML = "Unable to login.";
        }
    }

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let requestBody = `username=${username}&password=${password}`;
    xhr.send(requestBody);
}

function forceAuth() {
    let token = sessionStorage.getItem("token");
    let ref = window.location.href;
    let ok = ["login", "register", "reimbursement", "index", "profile"];
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
    console.log('reimbursement added');
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
