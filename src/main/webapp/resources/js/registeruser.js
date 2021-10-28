let form = document.getElementById('form');

form.addEventListener('submit', register);

async function register(event) {
	event.preventDefault();
	//console.log('Send Register Data');
	
	let username = document.getElementById('username').value;
	let password = document.getElementById('password').value;
	let name = document.getElementById('fName').value;
	let surname = document.getElementById('lName').value;
	let email = document.getElementById('email').value;
	let roleOpts = document.getElementsByName('role');
	let role;
	
	for(let i = 0; i < roleOpts.length; i++) {
		if(roleOpts[i].checked) {
			role = roleOpts[i].value;
		}
	}
	
	let user = {
		username,
		password,
		name,
		surname,
		email,
		role
	};
	
	let req = await fetch('http://localhost:8080/Project001/api/register', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(user)
	});
	let res = await req.json();
	
	if(user.username == res.username) {
		console.log(user);
		location.href="login.html";
	} else {
		alert(res);
	}
}