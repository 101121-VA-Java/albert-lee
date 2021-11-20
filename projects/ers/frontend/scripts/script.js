function getUsersExcept(string = "") {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status >= 200 && xhr.status < 300) {
                let response = xhr.response;
                response = JSON.parse(response);
                console.log(response);
                appendUsersToPage(string, response);
            }
        }
    }
    xhr.open("GET", "http://localhost:8080/users/");
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.send();
}

let appendUsersToPage = (string = "", arr = []) => {
    let container = document.getElementById("manager-dashboard");
    if(container){
        container.innerHTML = "";
        for (let i = 0; i < arr.length; i++) {
            let el = arr[i];
            if(el.role !== string && el.role !== "ADMIN"){
                let employeeObj = document.createElement("div");
                employeeObj.innerHTML = el.email;
                container.append(employeeObj);
            }
        }
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

let forceAuth = () => {
    let token = sessionStorage.getItem("token");
    let ref = window.location.href;
    let ok = ["login_form", "register_form", "index"];
    let safe = ok.some(url => ref.includes(url));
    if (!token && !safe)
        window.location.href = "/frontend/html/index.html";
}

let getDashboard = () => {
    let token = sessionStorage.getItem("token");
    let ref = window.location.href;
    let ok = ["dashboard", "index"];
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
    //amount, description

    //java will add the values below after the http req is sent
    //submissionTime, 

    //receipt image is optional after mvp
    if (container) container.innerHTML = 
    `<form id="register-form" onsubmit="return false">
        New Reimbursement Claim
        <div class="form-group">
            <label for="amount">Amount</label>
            <input type="number" class="form-control" id="reimb-amount" aria-describedby="amountHelp"
                placeholder="Amount here as a number">
        </div>
        <div class="form-group">
            <label for="text">Description</label>
            <input type="text" class="form-control" id="reimb-description" placeholder="Description in 250 characters or less">
        </div>
        <button onclick={addReimbursement()} class="btn btn-primary">Submit</button>
    </form>
    <div id="error-div"></div>`;
}

let addReimbursement = () => {
    console.log('reimbursement added');
}

let getReimbursements = () => {
    container = document.getElementById("employee-dash");
    if(container){
        container.innerHTML = 
        `<ul>
            <div>hardcoded reimbursement 1</div>
            <div>hardcoded reimbursement 2</div>
            <div>hardcoded reimbursement 3</div>
        </ul>`
    }
}

let logout = () => {
    sessionStorage.clear();
    window.location.href = "/frontend/html/index.html";
}

forceAuth();
getDashboard();