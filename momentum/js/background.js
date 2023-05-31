const images = [
    "0.jpg","1.jpg","2.jpg","3.jpg","4.jpg"
]
const image = images[Math.floor(Math.random()*images.length)];

const realImage = document.createElement("img");
console.log(realImage);
const backgroundImage = document.getElementById("wrap");

realImage.src=`img/${image}`;

document.body.appendChild(realImage);