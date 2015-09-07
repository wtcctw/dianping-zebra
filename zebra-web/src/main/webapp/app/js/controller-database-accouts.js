zebraWeb.controller('database-accouts', function ($rootScope, $scope,$location) {
	$scope.create = function(){
		$location.path("/database-accouts-create");
	}
});

zebraWeb.controller('database-accouts-create', function ($rootScope, $scope) {
});