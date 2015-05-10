(function() {
  var app = angular.module('gemStore', ['store-directives']);
  
  app.controller('StoreController',[ '$http', function($http){
	  var store = this;
	  store.products = [];
	  store.currentGem = {};
	  store.review = {};
	  $http.get('/gems').success(function(data){
		  store.products = data;
	  });
	  
	  store.refresh = function(){
		  $http.get('/gems').success(function(data){
			  store.products = data;
		  });
		  store.currentGem = {};
		  store.review = {};
	  };
	  
	  store.addGem = function(newGem){
		  $http.post('/gem', newGem).success(function(data){
			  store.refresh();
		  });
	  };
	  
	  store.updateGem = function(selectedGem){
		  $http.put('/gem/' + selectedGem.id, selectedGem).success(function(data){
			  store.refresh();
		  });
	  };
	  
	  store.deleteGem = function(id){
		  $http.delete('/gem/' + id).success(function(data){
			  store.refresh();
		  });
	  };
	  
	  store.addReview = function(product) {
		  store.review.createdOn = Date.now();
		  product.reviews.push(store.review);
	      store.review = {};
	      store.updateGem(product);
	  };
  }]);
  
  app.controller('ButtonController', function(){
	  var button = this;
	  button.pressed = false;
	  button.isPressed = function(){
		  return button.pressed;
	  };
	  button.press = function(){
		  button.pressed = !(button.pressed);
	  };
  });
  
})();