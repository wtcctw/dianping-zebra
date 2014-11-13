zebraWeb.controller('update', function ($scope, $http) {
    $http.get('/a/update/view').success(function (data, status, headers, config) {
        $scope.predicate = 'm_name';
        $scope.report = data;
    });

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
        if (confirm('确山添加？')) {
            $http.get('/a/blacklist?op=add&env=' + $scope.config.env + '&ip=' + ($scope.addIp ? $scope.addIp : '') + '&id=' + ($scope.addId ? $scope.addId : ''))
                .success(function (data, status, headers, config) {
                    $scope.load();
                    $scope.addId = '';
                    $scope.addIp = '';
                });
        }
    }

    $scope.$watch('config.env', $scope.load);
});

zebraWeb.controller('update-database', function ($scope, $stateParams, $http) {
    $http.get('/a/update?op=database&database=' + $stateParams.name).success(function (data, status, headers, config) {
        $scope.predicate = 'm_name';
        $scope.database = data;
    });
});

zebraWeb.controller('update-app', function ($scope, $stateParams, $http, $window) {
    $scope.load = function(){
        $http.get('/a/update?op=app&app=' + $stateParams.name).success(function (data, status, headers, config) {
            $scope.app = data;
        });
    }

    $scope.load();

    $scope.deleteInfo = function (ip, beanName) {
        $http.get('/a/update?op=delete_info&app=' + $stateParams.name + '&ip=' + ip + '&beanName=' + beanName).success(function (data) {
            if (!!data) {
                $scope.load();
            }
        });
    }
});

zebraWeb.controller('config-test', function ($scope, $http, name) {
    $scope.name = name;
    var url = '/a/config?op=test&key=' + name + '&env=' + $scope.config.env;

    $http.get(url).success(function (data, status, headers, config) {
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

    $scope.save = function (force) {
        force = !!force;
        $http.post('/a/config?op=updateDs&force=' + force, $.param({dsConfigs: encodeURIComponent(angular.toJson($scope.data))}),
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
            isCreate: true,
            newValue: value
        });
    }
});


zebraWeb.controller('header', function ($rootScope, $scope, $cookies, $http, loginService) {
    $scope.$watch('config.env', function () {
        if ($scope.config && $scope.config.env) {
            $cookies.env = $scope.config.env;
        }
    });

    $scope.load = function () {
        $http.get('/a/config?op=env').success(function (data, status, headers, config) {
            $rootScope.config = {
                envs: data,
                env: $cookies.env ? $cookies.env : data[0]
            }
        }).error(function (data, status, headers, config) {
            if (status == 401) {
                loginService.login();
            }
        });
    }
    $scope.load();
});

zebraWeb.controller('config', function ($scope, $stateParams, $http, configService) {
    var convertKey = function (key) {
        return key.substring(key.indexOf('.') + 1, key.lastIndexOf('.'));
    }

    $scope.edit = function (key) {
        configService.openEditModal(convertKey(key), $scope.load);
    };

    $scope.merge = function (key) {
        configService.openMergeModal(convertKey(key), $scope.load);
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
                $scope.addText = '';
                $scope.load();
                alert('添加成功！')
            });
        }
    }
});

zebraWeb.controller('login', function ($rootScope, $scope, $http) {
    $scope.login = function () {
        $http.post('/a/login?op=view', $.param({
                username: encodeURIComponent($scope.username),
                password: encodeURIComponent($scope.password)
            }),
            {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
            .success(function (data, status, headers, config) {
                alert('登陆成功!')
                location.reload();
            }).error(function () {
                alert('登陆失败!')
                $scope.username = '';
                $scope.password = '';
            });
    }
});

zebraWeb.controller('merge-edit', function ($scope, $http, $log, name, close) {
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

    $scope.close = function () {
        close();
    }

    $scope.onChange = function (selectedConfig) {
        if (selectedConfig.isMerged) {
            $scope.data.configs.forEach(function (config) {
                if (config.id != selectedConfig.id && config.isMerged) {
                    config.isMerged = !config.isMerged;
                }
            });

            $scope.data.configs.forEach(function (config) {
                config.isDelete = false;
                if (config.selected) {
                    if (!config.isMerged) {
                        config.isDelete = true;
                    }
                }
            });
        } else {
            $scope.data.configs.forEach(function (config) {
                config.isDelete = false;
            });
        }
    };

    $scope.merge = function () {
        var from = "";
        var to = "";
        if ($scope.config && $scope.config.env) {
            var first = true;
            $scope.data.configs.forEach(function (config) {
                if (config.selected) {
                    if (first) {
                        from += config.id;
                        first = false;
                    } else {
                        from += ",";
                        from += config.id;
                    }

                    if (config.isMerged) {
                        to = config.id;
                    }
                }
            });

            $http.get('/a/merge?op=merge&from=' + from + '&to=' + to + '&env=' + $scope.config.env).success(function (data, status, headers, config) {
                close();
            });
        }
    }
});