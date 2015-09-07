zebraWeb.controller('database-manager', function ($rootScope, $scope,$location) {
	$scope.create = function(){
		$location.path("/database-manager-create");
	}
});

zebraWeb.controller('database-manager-create', function ($rootScope, $scope,$location) {
	$scope.accouts = ["root","test","test123"];
});