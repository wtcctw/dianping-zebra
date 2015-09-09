zebraWeb.controller('database-manager', function ($rootScope, $scope,$location) {
	$scope.create = function(){
		$location.path("/database-manager-create");
	}
	
	$scope.show = function(jdbcRef){
		$location.path("/database-manager-datasource").search({"jdbcRef":jdbcRef});
	}
	
	$scope.status = function(){
		
	}
});

zebraWeb.controller('database-manager-create', function ($rootScope, $scope,$location) {
	$scope.bus = ["平台产品","交易后台","交易前台"];
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