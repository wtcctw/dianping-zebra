zebraWeb.controller('update', function ($scope, $http, configService) {
    $http.get('/a/update/view').success(function (data, status, headers, config) {
        $scope.predicate = 'm_name';
        $scope.report = data;
    });
    $scope.test = configService.openTestModal;
});

zebraWeb.controller('update-database', function ($scope, $stateParams, $http) {
    $http.get('/a/update?op=database&database=' + $stateParams.name).success(function (data, status, headers, config) {
        $scope.predicate = 'm_name';
        $scope.database = data;
    });
});

zebraWeb.controller('update-app', function ($scope, $stateParams, $http) {
    $http.get('/a/update?op=app&app=' + $stateParams.name).success(function (data, status, headers, config) {
        $scope.app = data;
    });
});

zebraWeb.controller('config-test', function ($scope, $http, name) {
    $scope.name = name;

    $http.get('/a/update?op=connection&database=' + name).success(function (data, status, headers, config) {
        $scope.connectionStatus = data;
    });
});

zebraWeb.controller('config', function ($scope, $stateParams, $http) {
    $scope.envs = ['dev', 'alpha', 'qa'];
    $scope.env = 'dev';

    var load = function () {
        $http.get('/a/config?op=view&env=' + $scope.env).success(function (data, status, headers, config) {
            $scope.lionConfigs = data;
        });
    }
    load();

    $scope.$watch('env', function () {
        load();
    });
});