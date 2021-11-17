let apiUrl = 'https://pokeapi.co/api/v2/pokemon/';

let response = fetch(apiUrl)
    .then(res => res.json())
    .then(data => response = data)
    .catch(err => console.log(err));
