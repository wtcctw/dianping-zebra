zebraWeb.controller('update', function ($scope, $http, configService) {
    $http.get('/a/update/view').success(function (data, status, headers, config) {
        $scope.predicate = 'm_name';
        $scope.report = data;
    });
    $scope.test = configService.openTestModal;
});

zebraWeb.controller('black', function ($scope, $http) {
    $scope.load = function () {
        $http.get('/a/blacklist?op=view&env=' + $scope.config.env).success(function (data, status, headers, config) {
            $scope.blackList = data;
        });
    }
    $scope.load();

    $scope.remove = function (key, id) {
        if (confirm('确山删除？')) {
            $http.get('/a/blacklist?op=delete&env=' + $scope.config.env + '&id=' + id + '&key=' + key)
                .success(function (data, status, headers, config) {
                    $scope.load();
                });
        }
    }

    $scope.add = function () {

    }
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
    $http.get('/a/config?op=test&key=' + name + '&env=' + $scope.config.env).success(function (data, status, headers, config) {
        $scope.connectionStatus = data;
    });
});

zebraWeb.controller('config-edit', function ($scope, $http, name, close) {
    $scope.name = name;
    $scope.load = function () {
        if ($scope.config && $scope.config.env) {
            $http.get('/a/config?op=viewDs&key=' + name + '&env=' + $scope.config.env).success(function (data, status, headers, config) {
                $scope.data = data;
            });
        }
    }

    $scope.$watch('config.env', $scope.load);
    $scope.load();

    var calGroupPrevoew = function () {
        if (!$scope.data) {
            return;
        }
        var writeList = '';
        var readList = '';
        $scope.data.configs.forEach(function (config) {
            if (!config.role) {
                return;
            }
            if (config.role.isWrite) {
                writeList += writeList ? ',' + config.id : config.id;
            }
            if (config.role.isRead) {
                if (!config.role.weight) {
                    config.role.weight = 1;
                }
                var temp = config.id + ':' + config.role.weight;
                readList += readList ? ',' + temp : temp;
            }
        });
        $scope.data.config = '(' + readList + '),(' + writeList + ')';
    }

    $scope.close = function () {
        close();
    }

    $scope.addDs = function () {
        if (!$scope.newDsName) {
            return;
        }
        if ($scope.newDsName.indexOf('ds.' + $scope.name) != 0) {
            alert('请以 ds.' + $scope.name + ' 开头！');
            return;
        }
        $scope.data.configs.push({
            id: $scope.newDsName,
            role: {
                isWrite: false,
                isRead: false,
                weight: 1
            },
            properties: []
        });
        $scope.newDsName = '';
    }

    $scope.save = function () {
        $http.post('/a/config?op=updateDs', $.param({dsConfigs: encodeURIComponent(angular.toJson($scope.data))}),
            {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
            .success(function (data, status, headers, config) {
                close();
            });
    }

    $scope.$watch(function () {
        calGroupPrevoew();
    })

    $scope.addProperty = function (list, id, key, value) {
        list.push({
            key: 'ds.' + id + '.jdbc.' + key,
            value: '',
            newValue: value
        });
    }
});


zebraWeb.controller('header', function ($rootScope, $scope) {
    $rootScope.config = {
        envs: [ "dev", "alpha", "qa", "prelease", "product", "performance", "product-hm" ],
        env: 'dev'
    }
});
zebraWeb.controller('config', function ($scope, $stateParams, $http, configService) {
    var convertKey = function (key) {
        return key.substring(key.indexOf('.') + 1, key.lastIndexOf('.'));
    }

    $scope.edit = function (key) {
        configService.openEditModal(convertKey(key), $scope.load);
    };

    $scope.test = function (key) {
        configService.openTestModal(convertKey(key));
    };

    $scope.load = function () {
        if ($scope.config && $scope.config.env) {
            $http.get('/a/config?op=view&env=' + $scope.config.env).success(function (data, status, headers, config) {
                $scope.lionConfigs = data;
            });
        }
    }
    $scope.$watch('config.env', $scope.load);
    $scope.load();

    $scope.createGroupDs = function () {
        if ($scope.addText) {
            $http.get('/a/config?op=create&project=groupds&key=' + $scope.addText).success(function (data, status, headers, config) {
                $scope.load();
            });
        }
    }
});