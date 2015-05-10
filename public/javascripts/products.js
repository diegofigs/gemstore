(function(){
    var app = angular.module('store-directives', []);

    app.directive("productDescription", function() {
      return {
        restrict: "E",
        templateUrl: "assets/html/product-description.html"
      };
    });

    app.directive("productSpecs", function() {
      return {
        restrict:"E",
        templateUrl: "assets/html/product-specs.html"
      };
    });
    
    app.directive("productReviews", function() {
        return {
          restrict: 'E',
          templateUrl: "/assets/html/product-reviews.html"
        };
    });

    app.directive("productTabs", function() {
      return {
        restrict: "E",
        templateUrl: "assets/html/product-tabs.html",
        controller: function() {
          this.tab = 1;

          this.isSet = function(checkTab) {
            return this.tab === checkTab;
          };

          this.setTab = function(activeTab) {
            this.tab = activeTab;
          };
        },
        controllerAs: "tab"
      };
    });

    app.directive("productGallery", function() {
      return {
        restrict: "E",
        templateUrl: "assets/html/product-gallery.html",
        controller: function() {
          this.current = 0;
          this.setCurrent = function(imageNumber){
            this.current = imageNumber || 0;
          };
        },
        controllerAs: "gallery"
      };
    });
    
    app.directive("searchBar", function() {
    	return {
    		restrict: "E",
    		templateUrl: "assets/html/search-bar.html"
    	};
    });
  })();