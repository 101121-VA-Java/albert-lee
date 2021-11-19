function getEmployees(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if( xhr.readyState === 4){
            if(xhr.status >= 200 && xhr.status < 300){
                let response = xhr.response;
                response = JSON.parse(response);
                console.log(response);
            }
        }
    }
    xhr.open("GET", "http://localhost:8080/employees");
    xhr.send();
}


function register(){
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;

    let newUser = { username, password, email, firstName, lastName };

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        // check that request was successful
        if( xhr.readyState === 4){
            if(xhr.status === 201){
                console.log('Employee was successfully added!');
            } else{
                console.log('Employee was not added...');
                /*
                    provide user with notification that action was unable to be completed, ie:
                    document.getElementById("errorDiv").innerHtml = 'invalid info';
                */
            }
        }
    }

    // Parameterize/Open request object
    xhr.open("POST", "http://localhost:8080/users/register");
    
    // Convert JS object to JSON object
    let requestBody = JSON.stringify(newUser);
    // send
    xhr.send(requestBody);
}

function login(){
    document.getElementById("error-div").innerHTML = "";
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/auth");
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let authToken = xhr.getResponseHeader("Authorization");
            sessionStorage.setItem("token", authToken);
            getDashboard();
        } else if (xhr.readyState === 4){
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
    if(token && !safe){
        let d = "";
        if(token.split(":")[1] === "MANAGER"){
            d = "manager_dashboard";
        } else if(token.split(":")[1] === "BASIC"){
            d = "employee_dashboard";
        } else if(token.split(":")[1] === "ADMIN"){
            d = "admin_dashboard";
        }
        if(d.length > 0) window.location.href = `/frontend/html/${d}.html`;
    }
}

let logout = () => {
   sessionStorage.clear();
   window.location.href = "/frontend/html/index.html";
}

forceAuth();
getDashboard();