import {display} from "./module/module.js";

function start() {
    document.getElementById("login").addEventListener('click', post, false);
}

function post() {
    let user = {
        userId : document.getElementById("id").innerText,
        userPassword : document.getElementById("password").innerText
    }

    fetch('http://localhost:57/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include',
        redirect: "follow",
        body: JSON.stringify(user)
    })
    .then((response)=>{
        reaction(response);
    });
}

function reaction(response) {
    switch (response.status) {
        case 403 : {
            alert("아이디가 존재하지 않거나, 비밀번호가 틀렸습니다.");
            break;
        }
        case 404 : {
            alert("서버가 동작하지 않습니다.");
            break;
        }
        case 200 : {
            alert("반갑습니다!")
        }
    }
}

window.addEventListener("load", start, false);