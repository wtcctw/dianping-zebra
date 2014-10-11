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

zebraWeb.controller('config-edit', function ($scope, $http, name, env) {
    $scope.name = name;
    $http.get('/a/config?op=viewDs&key=' + name + '&env=' + env).success(function (data, status, headers, config) {
        $scope.data = data;
        console.log(data);
    });
});

zebraWeb.controller('config', function ($scope, $stateParams, $http, configService) {
    $scope.envs = ['dev', 'alpha', 'qa'];
    $scope.env = 'dev';

    var convertKey = function (key) {
        return key.substring(key.indexOf('.') + 1, key.lastIndexOf('.'));
    }

    $scope.edit = function (key) {
        configService.openEditModal(convertKey(key), $scope.env);
    };

    $scope.test = function (key) {
        configService.openTestModal(convertKey(key));
    };

    var load = function () {
        $http.get('/a/config?op=view&env=' + $scope.env).success(function (data, status, headers, config) {
            $scope.lionConfigs = data;
        });
    }
    load();

    $scope.$watch('env', function () {
        load();
    });

    $scope.createGroupDs = function () {
        if ($scope.addText) {
            $http.get('/a/config?op=create&project=groupds&key=' + $scope.addText).success(function (data, status, headers, config) {
                load();
            });
        }
    }
});