const API_KEY = "566f82b581e12bd3782f2677ff9212fb"
function geoOk(position){
    const lat = position.coords.latitude;
    const lng = position.coords.longitude;
    const url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lng}&appid=${API_KEY}&units=metric`;
    console.log(lat, lng);
    fetch(url)
    .then((response) => response.json())
    .then((data) => {
        const weather = document.querySelector("#weather span:first-child");
        const city = document.querySelector("#weather span:last-child")
        city.innerText = data.name
        weather.innerText = `${data.weather[0].main} / ${data.main.temp}`;;
        
    });
}

function geoError(){
    alert("Can't find you, No weather for you.");
}

navigator.geolocation.getCurrentPosition(geoOk, geoError);