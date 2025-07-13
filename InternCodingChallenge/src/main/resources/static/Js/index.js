
const crossBtn = document.querySelector('.crossBtn');
const sidebar = document.querySelector('.sidebar');
const userContent = document.querySelector('.user-content');
const bars = document.querySelector('.bars')

sidebar.style.display = "block"


const toggleBtn = () => {

	if (sidebar.style.display === "block") {
		sidebar.style.display = "none";
		userContent.style.marginLeft = "0%";
		bars.style.display = "inline";
	} else {
		sidebar.style.display = "block";
		userContent.style.marginLeft = "20%";
		bars.style.display = "none";
	}
}


//rating logic


const ratings = ["Very Poor", "Poor", "Average", "Good", "Excellent"];

document.querySelectorAll('.rating-container').forEach((ratingForm)=>{


        const selectContainer = ratingForm.querySelector('.select-container');
		const select = selectContainer.querySelector('.select');
		const options = selectContainer.querySelector('.option');
		const span = select.querySelector('span');
		const ratingInput = selectContainer.querySelector('.ratingInput');




const addRating = () => {

	options.innerHTML = '';

	ratings.forEach((rating) => {
		const li = document.createElement('li');
		li.innerText = rating;
		options.appendChild(li);
		li.addEventListener('click', () => {
			updateRating(rating);
		});

	});


}

const updateRating = (rating) => {
	console.log(rating);
	span.innerText = rating;
	selectContainer.classList.remove('active');
	ratingInput.value = rating;

}


select.addEventListener('click', () => {
	selectContainer.classList.toggle('active');
	addRating();

});

});


function searchUser() {

	let query = $('#usersearch').val();

	if (query == '') {
		$('.search-user-data').hide();
	} else {

		let URL=`http://localhost:8080/searchuser/${query}`;
		
		fetch(URL).then((response)=>{
			return response.json();
		}).then((data)=>{
			
			let text=`<div class="list-group">`
			
			data.forEach((user)=>{

				text+=`<a href="#" class="list-group-item list-group-item-action">${user.uname}</a>`;
			})
			
			text += `</div>`
			
			$('.search-user-data').html(text);
			$('.search-user-data').show();
			
		});
		
		
		
		
	}

}



function searchStore() {

	let query = $('#storesearch').val();

	if (query == '') {
		$('.search-store-data').hide();
	} else {

		let URL=`http://localhost:8080/searchstore/${query}`;
		
		fetch(URL).then((response)=>{
			return response.json();
		}).then((data)=>{
			
			let text=`<div class="list-group">`
			
			data.forEach((store)=>{

				text+=`<a href="#" class="list-group-item list-group-item-action">${store.name}</a>`;
			})
			
			text += `</div>`
			
			$('.search-store-data').html(text);
			$('.search-store-data').show();
			
		});
		
		
		
		
	}

}








