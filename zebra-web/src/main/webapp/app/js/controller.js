zebraWeb.controller('update', function ($scope, $http) {
    $http.get('/a/update/index').success(function (data, status, headers, config) {
        $scope.predicate = 'name';
        $scope.report = data;
    });

});

zebraWeb.controller('shard', function ($scope, $http, shardService) {
    $scope.load = function () {
        $http.get('/a/shard/' + $scope.config.env + '/config').success(function (data, status, headers, config) {
            $scope.data = data;
        });
    }

    $scope.edit = function (key, config) {
        shardService.openEditModal(key)
    }

    $scope.test = function (key) {
        $http.get('/a/shard/' + $scope.config.env + '/test?key=' + key).success(function (data, status, headers, config) {
            alert(data.message);
        });
    }

    $scope.load();
});

zebraWeb.controller('shard-edit', function ($scope, $http, name, close) {
    $scope.name = name;

    $scope.close = function () {
        close();
    }

    $scope.load = function () {
        $http.get('/a/shard/' + $scope.config.env + '/config/' + name + '/').success(function (data, status, headers, config) {
            $scope.configs = data;

            if (!$scope.configs) {
                $scope.configs = {};
            }

            if (!$scope.configs.tableShardConfigs) {
                $scope.configs.tableShardConfigs = [];
            }

            if ($scope.configs.tableShardConfigs && $scope.configs.tableShardConfigs.length > 0) {
                $scope.configs.tableShardConfigs[0].active = true;
            }
        });
    }
    $scope.load();

    $scope.addException = function (dimension) {
        if (!dimension.exceptions) {
            dimension.exceptions = [];
        }
        dimension.exceptions.push({});
    }

    $scope.removeException = function (dimension, index) {
        dimension.exceptions.splice(index, 1);
    }

    $scope.addDimension = function (shard) {
        if (!shard.dimensionConfigs) {
            shard.dimensionConfigs = [];
        }
        shard.dimensionConfigs.push({});
    }

    $scope.removeDimension = function (shard, index) {
        shard.dimensionConfigs.splice(index, 1);
    }

    $scope.tableNameChange = function (shard) {
        while ($scope.configs.tableShardConfigs.filter(function (item) {
                return item.tableName == shard.tableName
            }).length > 1) {
            alert('表名重复！请修改表名');
            shard.tableName += '_new';
        }
    }

    $scope.removeTable = function (configs, index) {
        configs.tableShardConfigs.splice(index, 1);
        if (configs.tableShardConfigs.length > 0) {
            configs.tableShardConfigs[0].active = true;
        }
    }

    $scope.addTable = function () {
        $scope.configs.tableShardConfigs.forEach(function (item) {
            item.active = false;
        });

        var newTable = 'table_new';

        while ($scope.configs.tableShardConfigs.filter(function (item) {
                return item.tableName == newTable
            }).length >= 1) {
            newTable += '_new';
        }

        $scope.configs.tableShardConfigs.push({
            active: true,
            tableName: newTable
        })
    }

    $scope.changeActive = function (tableName) {
        $scope.configs.tableShardConfigs.forEach(function (item) {
            if (item.tableName == tableName) {
                item.active = true;
            } else {
                item.active = false;
            }
        });
    }

    $scope.test = function () {
        $http.post('/a/shard/' + $scope.config.env + '/test/', $scope.configs).success(function (data, status, headers, config) {
            alert(data.message);
        });
    }

    $scope.save = function () {
        $http.post('/a/shard/' + $scope.config.env + '/update/' + $scope.name + '/', $scope.configs).success(function (data, status, headers, config) {
            alert('保存成功！');
            close();
        });
    }
});

zebraWeb.controller('flow', function ($scope, $http, $filter) {
    $scope.load = function () {
        $http.get('/a/flowcontrol?env=' + $scope.config.env).success(function (data, status, headers, config) {
            $scope.blackList = data;
        });

        $http.get('/a/flowcontrol/history?env=' + $scope.config.env).success(function (data, status, headers, config) {
            $scope.history = data;
        });
    }
    $scope.load();

    $scope.percents = [{
        index: 0,
        text: "0"
  }, {
        index: 1,
        text: "10"
  }, {
        index: 2,
        text: "20"
  }, {
        index: 3,
        text: "30"
  }, {
        index: 4,
        text: "40"
  }, {
        index: 5,
        text: "50"
  }, {
        index: 6,
        text: "60"
  }, {
        index: 7,
        text: "70"
  }, {
        index: 8,
        text: "80"
  }, {
        index: 9,
        text: "90"
  }, {
        index: 10,
        text: "100"
  }];

    $scope.showPercent = function (item) {
        var selected = [];

        if (item.index) {
            selected = $filter('filter')($scope.percents, {
                index: item.index
            });
        }

        if(item.index == 0){
        	return "0";
        }
        
        return selected.length ? selected[0].text : 'Not set';
    };

    $scope.save = function ($data) {
        var selected = [];

        selected = $filter('filter')($scope.percents, {
            index: $data.percent
        });

        if (selected.length) {
            $http.post('/a/flowcontrol/save?env=' + $scope.config.env, {
                m_sqlId: $data.m_sqlId,
                ip: $data.m_app,
                m_allowPercent: selected[0].text,
                sql: $data.sql
            }).success(
                function (data, status, headers, config) {
                    $scope.load();
                });
        }
    };

    $scope.remove = function (key) {
        $http.post('/a/flowcontrol/delete?env=' + $scope.config.env + '&key=' + key).success(
            function (data, status, headers, config) {
                $scope.load();
            });
    }

    $scope.addFlowControl = function () {
        $scope.inserted = {
            m_sqlId: '',
            m_app: '_global_',
            percent: 0,
            sql: ''
        };

        $scope.blackList[' '] = $scope.inserted;
    };

    $scope.checkSqlId = function (data) {
        if (!data || data.length != 8) {
            return "请输入8位正确的sqlId！";
        }
    };

    $scope.$watch('config.env', $scope.load);
});

zebraWeb.controller('update-database', function ($scope, $stateParams, $http) {
    $http.get('/a/update/database?database=' + $stateParams.name).success(function (data, status, headers, config) {
        $scope.predicate = 'name';
        $scope.database = data;
    });
});

zebraWeb.controller('update-app', function ($scope, $stateParams, $http, $window) {
    $scope.load = function () {
        $http.get('/a/update/app?app=' + $stateParams.name).success(function (data, status, headers, config) {
            $scope.app = data;
        });
    }

    $scope.load();

    $scope.deleteInfo = function (ip, beanName) {
        $http.get('/a/update/delete-info?app=' + $stateParams.name + '&ip=' + ip + '&beanName=' + beanName).success(function (data) {
            if (!!data) {
                $scope.load();
            }
        });
    }
});

zebraWeb.controller('config-test', function ($scope, $http, name, configs) {
    $scope.name = name;
    var url = '/a/config/test?key=' + name + '&env=' + $scope.config.env;

    if (configs) {
        $scope.connectionStatus = configs;
    } else {
        $http.get(url).success(function (data, status, headers, config) {
            $scope.connectionStatus = data;
        });
    }
});

zebraWeb.controller('config-edit', function ($scope, $http, name, close, configService) {
    $scope.name = name;

    $scope.load = function () {
        if ($scope.config && $scope.config.env) {
            $http.get('/a/config/ds?key=' + name + '&env=' + $scope.config.env).success(function (data, status, headers, config) {
                $scope.data = data;
            });
        }
    }

    $scope.changeName = function () {
        if ($scope.config && $scope.config.env) {
            $http.get('/a/config/ds?key=' + name + '&env=' + $scope.config.env + '&otherkey=' + $scope.changedName).success(function (data, status, headers, config) {
                $scope.data = data;
            });
        }
    }

    $scope.$watch('config.env', $scope.load);

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
        if ($scope.newDsName.indexOf($scope.name) != 0) {
            alert('请以 ' + $scope.name + ' 开头！');
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
        $http.post('/a/config/updateds?force=' + force, angular.toJson($scope.data))
            .success(function (data, status, headers, config) {
                alert('保存成功！');
                close();
                configService.openTestModal($scope.name);
            });
    }

    $scope.test = function () {
        $http.post('/a/config/test?env=' + $scope.config.env, angular.toJson($scope.data))
            .success(function (data, status, headers, config) {
                configService.openTestModal("", data);
            });
    }

    $scope.$watch(function () {
        calGroupPrevoew();
    })

    $scope.newKeys = ['url', 'username', 'password', 'active', 'properties', 'warmupTime', 'driverClass', 'other'];

    $scope.addProperty = function (config) {
        if (!config.properties.newKey) {
            alert('请选择 key ！');
            return;
        }

        if (config.properties.newKey == 'other' && !config.properties.newOtherKey) {
            alert('请输入新的 key ！');
            return;
        }

        var newKey = config.properties.newKey == 'other' ? config.properties.newOtherKey : config.properties.newKey

        config.properties.push({
            key: 'ds.' + config.id + '.jdbc.' + newKey,
            value: '',
            isCreate: true,
            newValue: config.properties.newValue
        });

        config.properties.newValue = '';
        config.properties.newOtherValue = '';
        config.properties.newKey = '';
    }
});


zebraWeb.controller('header', function ($rootScope, $scope, $cookies, $http, loginService) {
    $scope.$watch('config.env', function () {
        if ($scope.config && $scope.config.env) {
            $cookies.env = $scope.config.env;
        }
    });

    $scope.load = function () {
        $http.get('/a/config/env').success(function (data, status, headers, config) {
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
            $http.get('/a/config/?env=' + $scope.config.env).success(function (data, status, headers, config) {
                $scope.lionConfigs = data;
            });
        }
    }
    $scope.$watch('config.env', $scope.load);

    $scope.createGroupDs = function () {
        if ($scope.addText) {
            $http.post('/a/config/create?project=groupds&key=' + $scope.addText).success(function (data, status, headers, config) {
                $scope.addText = '';
                $scope.load();
                alert('添加成功！')
            });
        }
    }
});

zebraWeb.controller('login', function ($rootScope, $scope, $http) {
    $scope.login = function () {
        $http.post('/a/login', {
                username: $scope.username,
                password: $scope.password
            })
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
            $http.get('/a/config/ds?key=' + name + '&env=' + $scope.config.env).success(function (data, status, headers, config) {
                $scope.data = data;
            });
        }
    }

    $scope.$watch('config.env', $scope.load);

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