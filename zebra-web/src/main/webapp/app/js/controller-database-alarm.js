zebraWeb.controller('database-alarm', function ($rootScope, $scope,$location) {
	$scope.create = function(){
		$location.path("/database-alarm-create");
	}
});

zebraWeb.controller('database-alarm-create', function ($rootScope, $scope,$location) {
});