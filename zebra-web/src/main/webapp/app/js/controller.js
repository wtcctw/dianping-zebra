zebraWeb.controller('update', function ($scope, $http, configService) {
    $http.get('/a/update/view').success(function (data, status, headers, config) {
        $scope.report = data;
    });
    $scope.test = configService.test;
});

zebraWeb.controller('update-database', function ($scope, $stateParams, $http, $location) {
    $http.get('/a/update?op=database&database=' + $stateParams.name).success(function (data, status, headers, config) {
        $scope.database = data;
    });
});

zebraWeb.controller('update-app', function ($scope, $stateParams, $http, $location) {
    $http.get('/a/update?op=app&app=' + $stateParams.name).success(function (data, status, headers, config) {
        $scope.app = data;
    });
});

zebraWeb.controller('config-test', function ($scope, name, connectionStatus) {
    $scope.name = name;
    $scope.connectionStatus = connectionStatus;
});