zebraWeb.controller('update', function($scope, $http) {
    $http.get('/a/update/index').success(function(data, status, headers, config) {
        $scope.predicate = 'name';
        $scope.report = data;
    });

});

zebraWeb.controller('shard', function($scope, $http) {
    $scope.load = function() {
        $http.get('/a/shard/' + $scope.config.env).success(function(data, status, headers, config) {
            $scope.data = data;
        });
    }


    $scope.test = function(key){
        $http.get('/a/shard/' + $scope.config.env + '/test?key=' +  key).success(function(data, status, headers, config) {
            alert(data.message);
        });
    }

    $scope.load();
});

zebraWeb.controller('black', function($scope, $http) {
    $scope.load = function() {
        $http.get('/a/blacklist/?env=' + $scope.config.env).success(function(data, status, headers, config) {
            $scope.blackList = data;
        });
    }
    $scope.load();

    $scope.remove = function(key, id) {
        if (confirm('确山删除？')) {
            $http.post('/a/blacklist/delete?env=' + $scope.config.env + '&id=' + id + '&key=' + key)
                .success(function(data, status, headers, config) {
                    $scope.load();
                });
        }
    }

    $scope.add = function() {
        if (confirm('确山添加？')) {
            $http.post('/a/blacklist/add?env=' + $scope.config.env, {
                ip: ($scope.addIp ? $scope.addIp : ''),
                id: ($scope.addId ? $scope.addId : ''),
                comment: ($scope.addComment ? $scope.addComment : '')
            }).success(function(data, status, headers, config) {
                $scope.load();
                $scope.addId = '';
                $scope.addIp = '';
                $scope.addComment = '';
            });
        }
    }

    $scope.$watch('config.env', $scope.load);
});

zebraWeb.controller('update-database', function($scope, $stateParams, $http) {
    $http.get('/a/update/database?database=' + $stateParams.name).success(function(data, status, headers, config) {
        $scope.predicate = 'name';
        $scope.database = data;
    });
});

zebraWeb.controller('update-app', function($scope, $stateParams, $http, $window) {
    $scope.load = function() {
        $http.get('/a/update/app?app=' + $stateParams.name).success(function(data, status, headers, config) {
            $scope.app = data;
        });
    }

    $scope.load();

    $scope.deleteInfo = function(ip, beanName) {
        $http.get('/a/update/delete-info?app=' + $stateParams.name + '&ip=' + ip + '&beanName=' + beanName).success(function(data) {
            if (!!data) {
                $scope.load();
            }
        });
    }
});

zebraWeb.controller('config-test', function($scope, $http, name, configs) {
    $scope.name = name;
    var url = '/a/config/test?key=' + name + '&env=' + $scope.config.env;

    if (configs) {
        $scope.connectionStatus = configs;
    } else {
        $http.get(url).success(function(data, status, headers, config) {
            $scope.connectionStatus = data;
        });
    }
});

zebraWeb.controller('config-edit', function($scope, $http, name, close, configService) {
    $scope.name = name;

    $scope.load = function() {
        if ($scope.config && $scope.config.env) {
            $http.get('/a/config/ds?key=' + name + '&env=' + $scope.config.env).success(function(data, status, headers, config) {
                $scope.data = data;
            });
        }
    }

    $scope.changeName = function() {
        if ($scope.config && $scope.config.env) {
            $http.get('/a/config/ds?key=' + name + '&env=' + $scope.config.env + '&otherkey=' + $scope.changedName).success(function(data, status, headers, config) {
                $scope.data = data;
            });
        }
    }

    $scope.$watch('config.env', $scope.load);
    $scope.load();

    var calGroupPrevoew = function() {
        if (!$scope.data) {
            return;
        }
        var writeList = '';
        var readList = '';
        $scope.data.configs.forEach(function(config) {
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

    $scope.close = function() {
        close();
    }

    $scope.addDs = function() {
        if (!$scope.newDsName) {
            return;
        }
        if ($scope.newDsName.indexOf($scope.name) != 0) {
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

    $scope.save = function(force) {
        force = !!force;
        $http.post('/a/config/updateds?force=' + force, angular.toJson($scope.data))
            .success(function(data, status, headers, config) {
                close();
            });
    }

    $scope.test = function() {
        $http.post('/a/config/test?env=' + $scope.config.env, angular.toJson($scope.data))
            .success(function(data, status, headers, config) {
                configService.openTestModal("", data);
            });
    }

    $scope.$watch(function() {
        calGroupPrevoew();
    })

    $scope.newKeys = ['url', 'username', 'password', 'active', 'properties', 'warmupTime', 'driverClass'];

    $scope.addProperty = function(config) {
        if (!config.properties.newKey) {
            alert('请选择 key ！');
        } else {
            config.properties.push({
                key: 'ds.' + config.id + '.jdbc.' + config.properties.newKey,
                value: '',
                isCreate: true,
                newValue: config.properties.newValue
            });

            config.properties.newValue = '';
            config.properties.newKey = '';
        }
    }
});


zebraWeb.controller('header', function($rootScope, $scope, $cookies, $http, loginService) {
    $scope.$watch('config.env', function() {
        if ($scope.config && $scope.config.env) {
            $cookies.env = $scope.config.env;
        }
    });

    $scope.load = function() {
        $http.get('/a/config/env').success(function(data, status, headers, config) {
            $rootScope.config = {
                envs: data,
                env: $cookies.env ? $cookies.env : data[0]
            }
        }).error(function(data, status, headers, config) {
            if (status == 401) {
                loginService.login();
            }
        });
    }
    $scope.load();
});

zebraWeb.controller('config', function($scope, $stateParams, $http, configService) {
    var convertKey = function(key) {
        return key.substring(key.indexOf('.') + 1, key.lastIndexOf('.'));
    }

    $scope.edit = function(key) {
        configService.openEditModal(convertKey(key), $scope.load);
    };

    $scope.merge = function(key) {
        configService.openMergeModal(convertKey(key), $scope.load);
    };

    $scope.test = function(key) {
        configService.openTestModal(convertKey(key));
    };

    $scope.load = function() {
        if ($scope.config && $scope.config.env) {
            $http.get('/a/config/?env=' + $scope.config.env).success(function(data, status, headers, config) {
                $scope.lionConfigs = data;
            });
        }
    }
    $scope.$watch('config.env', $scope.load);
    $scope.load();

    $scope.createGroupDs = function() {
        if ($scope.addText) {
            $http.post('/a/config/create?project=groupds&key=' + $scope.addText).success(function(data, status, headers, config) {
                $scope.addText = '';
                $scope.load();
                alert('添加成功！')
            });
        }
    }
});

zebraWeb.controller('login', function($rootScope, $scope, $http) {
    $scope.login = function() {
        $http.post('/a/login', {
                username: $scope.username,
                password: $scope.password
            })
            .success(function(data, status, headers, config) {
                alert('登陆成功!')
                location.reload();
            }).error(function() {
                alert('登陆失败!')
                $scope.username = '';
                $scope.password = '';
            });
    }
});

zebraWeb.controller('merge-edit', function($scope, $http, $log, name, close) {
    $scope.name = name;
    $scope.load = function() {
        if ($scope.config && $scope.config.env) {
            $http.get('/a/config/ds?key=' + name + '&env=' + $scope.config.env).success(function(data, status, headers, config) {
                $scope.data = data;
            });
        }
    }

    $scope.$watch('config.env', $scope.load);
    $scope.load();

    $scope.close = function() {
        close();
    }

    $scope.onChange = function(selectedConfig) {
        if (selectedConfig.isMerged) {
            $scope.data.configs.forEach(function(config) {
                if (config.id != selectedConfig.id && config.isMerged) {
                    config.isMerged = !config.isMerged;
                }
            });

            $scope.data.configs.forEach(function(config) {
                config.isDelete = false;
                if (config.selected) {
                    if (!config.isMerged) {
                        config.isDelete = true;
                    }
                }
            });
        } else {
            $scope.data.configs.forEach(function(config) {
                config.isDelete = false;
            });
        }
    };

    $scope.merge = function() {
        var from = "";
        var to = "";
        if ($scope.config && $scope.config.env) {
            var first = true;
            $scope.data.configs.forEach(function(config) {
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

            $http.get('/a/merge?op=merge&from=' + from + '&to=' + to + '&env=' + $scope.config.env).success(function(data, status, headers, config) {
                close();
            });
        }
    }
});
