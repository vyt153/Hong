const loginInput = document.querySelector("#Login-form input");
const loginForm = document.querySelector("#Login-form");
const greeting = document.querySelector("#greeting");

const USERNAME = "userName";


function onLoginSubmit(event){
    event.preventDefault();
    loginForm.classList.add("hidden");
    const userName = loginInput.value;
    localStorage.setItem("USERNAME", userName);
    greeting.innerText = `Hello ${userName}`;
    paintGreeting(userName);
}
function paintGreeting(userName){
    greeting.innerText=`Hello ${userName}`;
    greeting.classList.remove("hidden");
}

const savedUsername = localStorage.getItem("USERNAME");

if(savedUsername === null){
    loginForm.classList.remove("hidden");
    loginForm.addEventListener("submit", onLoginSubmit);
} else{
    paintGreeting(savedUsername);
}