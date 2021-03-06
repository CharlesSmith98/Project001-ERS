let display = document.getElementById('display');
let selected = document.getElementById('selected');
let resolution = document.getElementById('resolution');

let reimbs = new Array();
let money = new Intl.NumberFormat(`en-US`, {
	currency: `USD`,
	style: 'currency'
});

(async function(){
	let table = document.createElement('table');
	let headers = document.createElement('tr');
	headers.innerHTML = `<th>ID</th><th>Amount</th><th>Type</th><th>Submitted By</th><th>Description</th><th>Submitted</th>
						 <th>Status</th><th>Receipt</th>`;
	table.appendChild(headers);
	display.appendChild(table);
	
	let req = await fetch('http://localhost:8080/Project001/api/allpending', {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json'
		}
	});
	let res = await req.json();
	reimbs = res;
	
	for(let i = 0; i < reimbs.length; i++) {
		let temp = await fetch('http://localhost:8080/Project001/api/author', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(reimbs[i])
		});
		let author = await temp.json();
		reimbs[i].authorId = author.username;
		
		let submitted = (new Date(reimbs[i].timeSubmitted)).toDateString();
		reimbs[i].timeSubmitted = submitted;
		
		if(reimbs[i].description == null) {
			reimbs[i].description = '';
		}
		
		if(reimbs[i].receipt == null) {
			reimbs[i].receipt = '';
		}
		
		if(reimbs[i].statusId == 1) {
			reimbs[i].statusId = 'Pending';
		} else if(reimbs[i].statusId == 2) {
			reimbs[i].statusId = 'Approved';
		} else {
			reimbs[i].statusId = 'Denied';
		}
		
		if(reimbs[i].typeId == 1) {
			reimbs[i].typeId = 'Food';
		} else if (reimbs[i].typeId == 2) {
			reimbs[i].typeId = 'Lodging';
		} else if (reimbs[i].typeId == 3) {
			reimbs[i].typeId = 'Travel';
		} else if (reimbs[i].typeId == 4) {
			reimbs[i].typeId = 'Other';
		}
		
		let dollars = money.format(reimbs[i].amount);
		let row = document.createElement('tr');
		row.innerHTML = `<td>${reimbs[i].id}</td><td>${dollars}</td><td>${reimbs[i].typeId}</td><td>${reimbs[i].authorId}</td><td>${reimbs[i].description}</td>
						<td>${reimbs[i].timeSubmitted}</td><td>${reimbs[i].statusId}</td><td>${reimbs[i].receipt}</td>`;
		table.appendChild(row);
	
		row.addEventListener('click', showSelected);
	}
})();


function showSelected(e) {
	selected.innerHTML = '';
	let clicked = (e.target).parentElement;
	console.log(clicked);
	let receipt = clicked.lastElementChild.innerText;
	let id = Number(clicked.firstElementChild.innerText);
	let amount = clicked.firstElementChild.nextElementSibling.innerText;
	let typeId = clicked.firstElementChild.nextElementSibling.nextElementSibling.innerText;
	let authorId = clicked.firstElementChild.nextElementSibling.nextElementSibling.nextElementSibling.innerText;
	let description = clicked.firstElementChild.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.innerText;
	let timeSubmitted = clicked.firstElementChild.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.innerText;
	let statusId = clicked.lastElementChild.previousElementSibling.innerText;
	console.log(statusId);
	
	let reimb = {
		id,
		amount,
		typeId,
		authorId,
		description,
		timeSubmitted
	};
	
	selected.innerHTML = `<p>You have selected Reimbursement ${reimb.id} with an amount of ${reimb.amount}
							for ${typeId}</p><p>This was submitted by ${reimb.authorId} on ${reimb.timeSubmitted}</p>`;
							
	resolution.innerHTML = `<button type="button" id="approve">Approve</button>&nbsp;&nbsp;&nbsp;<button type="button" id="deny">Deny</button>`;
	
	let approveBtn = document.getElementById('approve');
	let denyBtn = document.getElementById('deny');
	
	approveBtn.addEventListener('click', () => {
		console.log('approve');
		let obj = {
			id,
			statusId: 2
		};
		resolve(obj);
		location.href = 'home.html';
	});
	
	denyBtn.addEventListener('click', () => {
		console.log('deny');
		let obj = {
			id,
			statusId: 3
		};
		resolve(obj);
		location.href = 'home.html';
	});
}

async function resolve(obj) {
	let req = await fetch('http://localhost:8080/Project001/api/resolve', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(obj)
	});
	let res = await req.json();
	
}





