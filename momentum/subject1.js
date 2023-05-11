const generateInput = document.querySelector("#generateNumber");
const guessInput = document.querySelector("#guessNumber")
const Form = document.querySelector("#Form");
const gameInfo = document.querySelector("#gameInfo");
const win = document.querySelector("#won");
const lose = document.querySelector("#lose");

function numberSubmit(event){
  event.preventDefault();
  const yourNumber = guessInput.value;
  const randomNumber = Math.ceil(Math.random()*generateInput.value);
  gameInfo.innerText=`You chose: ${yourNumber} the machine chose: ${randomNumber}`
  gameInfo.classList.remove("hidden");
  localStorage.setItem("yourNumber", yourNumber);
  localStorage.setItem("randomNumber", randomNumber);

const savedYourNumber = localStorage.getItem("yourNumber");
const savedRandomNumber = localStorage.getItem("randomNumber");

if(savedYourNumber === savedRandomNumber){
  win.innerText = "You win!"
  win.classList.remove("hidden");
  lose.classList.add("hidden");
} else{
  lose.innerText = "You lost!"
  lose.classList.remove("hidden");
  win.classList.add("hidden");
}}

Form.addEventListener("submit", numberSubmit);
