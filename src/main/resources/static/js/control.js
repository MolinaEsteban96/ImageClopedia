const app = new Vue({
	
	el: "#app",
	
	data: {
		urlInput: null,
		disableButton: false,
		memes: null,
	},
	
	methods: {
		disable: function(){
			disableButton = true
		},
		
	},	
	
	created: function(){
			fetch("/api")
                .then(response=> response.json())
                .then(data => {
                    this.memes = data
                    console.log(data)
                })
                .catch(e => console.log(e))
	}
})
