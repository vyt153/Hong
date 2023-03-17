/*const todoform = document.querySelector("#todo-form");
const todoList = document.querySelector("#Todo-list");
const todoInput = document.querySelector("#todo-form input");

const todos = [];
const TODOS_KEY = "todos";

function saveTodos(){
    localStorage.setItem(TODOS_KEY, JSON.stringify(todos));
}

function deleteTodo(event){
    const li = event.target.parentElement;
    li.remove();
}

function paintToDo(newTodo){
    const li = document.createElement("li");
    const span = document.createElement("span");
    const button = document.createElement("button")
    button.innerText = "X";
    button.addEventListener("click", deleteTodo);
    span.innerText = newTodo;
    li.appendChild(span);
    li.appendChild(button);
    todoList.appendChild(li);
}
    function todoSubmit(event){
        event.preventDefault();
        const newTodo = todoInput.value;
        todoInput.value = ""
        todos.push(newTodo);
        paintToDo(newTodo);
        saveTodos();
    }*/


todoForm.addEventListener("submit", todoSubmit);

const savedTodos = localStorage.getItem("TODOS_KEY")
if (saveTodos !== null) {
    const parsedTodos = JSON.parse(saveTodos);
    parsedTodos.forEach((item) => console.log("this is the turn of ", item));
}

const todoForm = document.querySelector("#todo-form");
const todoList = document.querySelector("#Todo-list");
const todoInput = document.querySelector("#todo-form input");

const todos = [];
const todos_Key = "todos";

function saveTodos() {
    localStorage.setItem("todos_Key", JSON.stringify(todos));
}

function deleteTodo(event) {
    const li = event.target.parentNode;
    li.remove();
}

function paintToDo(newTodo) {
    const li = document.createElement("li");
    const span = document.createElement("span");
    span.innerText = newTodo;
    const button = document.createElement("button");
    button.innerText = "X";
    button.addEventListener("click", deleteTodo)
    li.appendChild(span);
    li.appendChild(button);
    todoList.appendChild(li);
}

function todoSubmit(event) {
    event.preventDefault();
    const newTodo = todoInput.value;
    todoInput.value = ""
    todos.push(newTodo);
    paintToDo(newTodo);
    saveTodos();
}

todoForm.addEventListener("submit", todoSubmit);

const savedTodos = localStorage.getItem(todos_Key);


function sayHello(item) {
    console.log("this is turn of ", item);
}
if (savedTodos !== null) {
    const parsedTodos = JSON.parse(saveTodos);
    parsedTodos.forEach(sayHello);
}