
let signOff = document.getElementById("logout");

signOff.addEventListener('click', logout);

function logout(e) {
	e.preventDefault();
	console.log('click');
	//let text = 'User logged out';
	
	try{
		let req = fetch('http://localhost:8080/Project001/api/logout');
		browser.history.deleteAll();
		window.location.replace("../../html/login.html");
		//location.href='../../html/login.html';
	} catch(e) {
		console.log('Error Logging Out');
	}
}
