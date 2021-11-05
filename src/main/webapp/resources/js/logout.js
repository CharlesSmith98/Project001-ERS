
let signOff = document.getElementById("logout");

signOff.addEventListener('click', logout);

async function logout(e) {
	e.preventDefault();
	console.log('click');
	//let text = 'User logged out';
	
		let req = await fetch('http://localhost:8080/Project001/api/logout');
		let res = req.status;
		if(res == 200) {
			console.log('Should Redirect');
			window.location.replace('http://localhost:8080/Project001/resources/html/login.html');
		} 
		
}
