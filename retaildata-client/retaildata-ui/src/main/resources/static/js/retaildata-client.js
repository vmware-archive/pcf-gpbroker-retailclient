	angular.module('retaildata-client', ['info','ngResource','ui.bootstrap']).
	    factory('ProductSalesByStoreData', function ($resource) {
	        return $resource('product_sales_by_store');
	    }).
	    factory('ProductSalesByStoreDataSearch', function ($resource) {
	        return $resource('product_sales_by_store/search/productNameContains');
	    }).
	    controller('ReportController', function ($scope, ProductSalesByStoreData, ProductSalesByStoreDataSearch) {
	        function list() {
	        	ProductSalesByStoreData.get({page: $scope.pageNumber - 1, size: $scope.itemsPerPage}).$promise.then(function(pagedProductSalesByStoreData) {
	                $scope.pagedProductSalesByStoreData = pagedProductSalesByStoreData;
	            })
	        }

	        function search() {
	        	ProductSalesByStoreDataSearch.get({q: $scope.searchProductSalesByStoreData.productNameContains, page: $scope.pageNumber - 1, size: $scope.itemsPerPage}).$promise.then(function(pagedProductSalesByStoreData) {
	                $scope.pagedProductSalesByStoreData = pagedProductSalesByStoreData;
	            })
	        }

	        $scope.refresh = function() {
	            $scope.firstItemIndex = ($scope.itemsPerPage * ($scope.pageNumber - 1)) + 1;
	            $scope.lastItemIndex = Math.min(($scope.firstItemIndex + $scope.itemsPerPage) - 1, $scope.pagedProductSalesByStoreData.page.totalElements);
	            if($scope.firstTime===true)
	            	{
	            		$scope.lastItemIndex = 10;
	            		$scope.firstTime=false;
	            	}
	            
	            if ($scope.searchProductSalesByStoreData.productNameContains)
	                search();
	            else
	                list();
	        };

	        $scope.search = function() {
	            search();
	        };

	        $scope.clearSearch = function() {
	            $scope.searchProductSalesByStoreData.productNameContains = "";
	            list();
	        };

	        $scope.init = function() {
	        	$scope.firstTime=true;
	            $scope.pageNumber = 1;
	            $scope.itemsPerPage = 10;
	            $scope.maxPageLinks = 15;
	            $scope.itemsPerPageOptions = [10, 15, 25, 50];
	            $scope.searchProductSalesByStoreData= {
	                productNameContains: ""
	            };
	            $scope.pagedProductSalesByStoreData = {
	                "contents": [],
	                "page": {
	                    "number": 0,
	                    "size": 1,
	                    "totalElements": 1,
	                    "totalPages": 1
	                }
	            };

	            $scope.refresh();
	        };
	    });
