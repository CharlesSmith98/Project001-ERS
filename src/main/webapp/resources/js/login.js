let form = document.getElementById("login");

form.addEventListener("submit", login);

async function login(e) {
	e.preventDefault();
	
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	
	let user = {
		username,
		password
	}
	
	try {
		let req = await fetch('http://localhost:8080/Project001/api/login', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		console.log(user);
		let res = await req.json();
		console.log(res);
		if(res.roleId == 1) {
			location.href = '../html/emp/home.html';
		}else if(res.roleId == 2) {
			location.href = '../html/man/home.html';
		}
	}catch(e) {
		alert("Username or Password is incorrect");
	}
	
}