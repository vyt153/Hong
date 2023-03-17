const todoform = document.querySelector("#todo-form");
const todoList = document.querySelector("#Todo-list");
const todoInput = document.querySelector("#todo-form input");

const TODOS_KEY = "toDos";

let todos = [];

function saveTodos(){
    localStorage.setItem(TODOS_KEY, JSON.stringify(todos));
}

function deleteTodo(event){
    const li = event.target.parentElement;
    li.remove();
    todos = todos.filter(todo => todo.id !== parseInt(li.id));
    saveTodos();
}

function paintToDo(newTodo){
    const li = document.createElement("li");
    li.id = newTodo.id;
    const span = document.createElement("span");
    span.innerText = newTodo.Text;
    const button = document.createElement("button");
    button.innerText = "X";
    button.addEventListener("click", deleteTodo);
    li.appendChild(span);
    li.appendChild(button);
    todoList.appendChild(li);
}

    function todoSubmit(event){
        event.preventDefault();
        const newTodo = todoInput.value;
        todoInput.value = "";
        const newTodoObj = {
            Text: newTodo,
            id: Date.now()
        };
        todos.push(newTodoObj);
        paintToDo(newTodoObj);
        saveTodos();
    }

    todoform.addEventListener("submit", todoSubmit);

    const savedTodos = localStorage.getItem(TODOS_KEY);

    if(savedTodos !== null){
        const parsedTodos = JSON.parse(savedTodos);
        todos = parsedTodos;
        parsedTodos.forEach(paintToDo);
    }