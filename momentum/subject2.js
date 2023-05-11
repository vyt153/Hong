const loginInput = document.querySelector("#login-form input");
const loginForm = document.querySelector("#login-form");
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

const inputNumber = inputNumber[Math.ceil(Math.random)];
console.log(inputNumber);
