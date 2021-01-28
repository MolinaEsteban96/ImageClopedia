const app = new Vue({
	
	el: "#app",
	
	data: {
		urlInput: null,
		disableButton: false,
		comments: null,
	},
	
	methods: {
		disable: function(){
			disableButton = true
		},
		
	},	
	
	created: function(){
			fetch("/comments")
                .then(response=> response.json())
                .then(data => {
                    this.comments = data
                    console.log(data)
                })
                .catch(e => console.log(e))
	}
})