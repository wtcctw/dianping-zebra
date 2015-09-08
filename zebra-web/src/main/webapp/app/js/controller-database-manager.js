zebraWeb.controller('database-manager', function ($rootScope, $scope,$location) {
	$scope.create = function(){
		$location.path("/database-manager-create");
	}
	
	$scope.show = function(jdbcRef){
		$location.path("/database-manager-datasource").search({"jdbcRef":jdbcRef});
	}
});

zebraWeb.controller('database-manager-create', function ($rootScope, $scope,$location) {
	$scope.accouts = ["root","test","test123"];
});

zebraWeb.controller('database-manager-datasource', function ($rootScope, $scope,$location,configService) {
	 $scope.edit = function (jdbcRef) {
	    configService.openEditModal(jdbcRef, $scope.load);
	 };
	 
	 $scope.load = function () {
        if ($scope.config && $scope.config.env) {
            $http.get('/a/config/?env=' + $scope.config.env).success(function (data, status, headers, config) {
                $scope.lionConfigs = data;
                
                $scope.getMonitored();
            });
        }
    }
});