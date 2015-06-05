zebraWeb.config(function ($httpProvider, loginServiceProvider, alertServiceProvider) {
    $httpProvider.interceptors.push(function ($q) {
        return {
            'response': function (response) {
                return response;
            },
            'responseError': function (rejection) {
                if (rejection.status === 401) {
                    loginServiceProvider.$get().login();
                }
                if (rejection.status === 500) {
                    alertServiceProvider.$get().addException(rejection.data.message);
                }
                return $q.reject(rejection);
            }
        };
    });
});


zebraWeb.controller('alert', function ($rootScope, $scope) {
    setInterval(function () {
        $rootScope.alertList.shift();
        $rootScope.$apply();
    }, 4000);
});

zebraWeb.controller('shard-migrate-dump', function ($scope, $http) {
    $scope.load = function () {
        $http.get('/a/shard/migrate/dump/' + $scope.name).success(function (data, status, headers, config) {
            $scope.dump = data;
        });
    }

    $http.get('/a/migratedb').success(function (data, status, headers, config) {
        $scope.dbs = data;
    });

    $scope.newTask = {};

    $scope.load();

    $scope.addNewTarget = function () {
        if (!$scope.newTask.targets) {
            $scope.newTask.targets = [];
        }
        $scope.newTask.targets.push({});
    }

    $scope.removeTarget = function (index) {
        $scope.newTask.targets.splice(index, 1);
    }

    $scope.removeTask = function (index) {
        $http.delete('/a/shard/migrate/dump/' + $scope.name + '/' + index).success(function (data, status, headers, config) {
            $scope.dump = data;
        });
    }

    $scope.commitTask = function () {
        $http.post('/a/shard/migrate/dump/' + $scope.name, $scope.newTask).success(function (data, status, headers, config) {
            alert("创建成功！");
            $scope.dump = data;
            $scope.newTask = {};
        });
    }
});


zebraWeb.controller('shard-migrate', function ($scope, $http, name, close) {
    $scope.name = name;

    $http.get('/a/shard/migrate/process/' + name).success(function (data, status, headers, config) {
        $scope.process = data;
        if ($scope.process) {
            if (!$scope.process.initFinish) {
                $scope.currentStop = 1;
            } else if (!$scope.process.dumpFinish) {
                $scope.currentStop = 2;
            } else if ($scope.process.needSync && !$scope.process.syncCreateFinish) {
                $scope.currentStop = 3;
            } else if (!$scope.process.catchUpFinish) {
                $scope.currentStop = 4;
            }
        }
    });

    $scope.close = close;

    $scope.next = function () {
        if ($scope.currentStop < 4) {
            $scope.currentStop++;
        }
    }

    $scope.prev = function () {
        if ($scope.currentStop > 1) {
            $scope.currentStop--;
        }
    }
});

zebraWeb.controller('validate', function ($scope, $http) {
	$scope.load = function () {
        $http.get('/a/shard/' + $scope.config.env + '/config').success(function (data, status, headers, config) {
            $scope.shardRules = data;
        });
        
        $http.get('/a/validate/dbs').success(function (data, status, headers, config) {
            $scope.databases = data;
        });
    }
	
	$scope.load();
});

zebraWeb.controller('dml', function ($scope, $http) {
    $scope.load = function () {
        $http.get('/a/shard/' + $scope.config.env + '/config').success(function (data, status, headers, config) {
            $scope.shardRules = data;
        });

        $scope.isDisabled = false;
    }

    $scope.analyze = function () {
        if (!$scope.shardRule) {
            alert("请选择正确的分表规则");
            return;
        }

        if (!$scope.shardSql) {
            alert("请输入sql语句");
            return;
        }

        $scope.isDisabled = true;

        $http.post('/a/dml/analyze', {
            ruleName: $scope.shardRule,
            sql: $scope.shardSql,
        }).success(
            function (data, status, headers, config) {
                $scope.data = data;
                $scope.isInQuery = true;
                $scope.isDisabled = false;
            });

    }

    $scope.analyzeSample = function () {
        $scope.shardRule = "unifiedorder";
        $scope.shardSql = "select UserID,OrderID,SkuID from UOD_OrderSKU where OrderID='1432878940034432510782'";

        $scope.analyze();
    }

    $scope.load();
});

zebraWeb.controller('migratedb', function ($scope, $http) {
    $scope.newDb = {};

    $http.get('/a/migratedb').success(function (data, status, headers, config) {
        $scope.data = data;
    });

    $scope.createDb = function () {
        $http.post('/a/migratedb', $scope.newDb).success(function (data, status, headers, config) {
            alert("创建成功！");
            $scope.data = data;
            $scope.newDb = {};
        });
    }

    $scope.delete = function (id) {
        $http.delete('/a/migratedb/' + id).success(function (data, status, headers, config) {
            alert("删除成功！");
            $scope.data = data;
        });
    }
});

zebraWeb.controller('doc', function ($scope, $location) {
    $scope.isActive = function (viewLocation) {
        return viewLocation === $location.path();
    };
});

zebraWeb.controller('monitor', function ($scope, $http) {
    $scope.load = function () {
        $http.get('/a/monitor/list').success(function (data, status, headers, config) {
            $scope.statusList = data;
        });

        $http.get('/a/mha/allMarkedDown').success(function (data, status, headers, config) {
            $scope.mhaList = data;
        });

        $http.get('/a/monitor/history').success(function (data, status, headers, config) {
            $scope.histories = data;
        });
    }

    $scope.load();

    $scope.addJdbcRef = function () {
        if ($scope.jdbcRef) {
            $http.get('/a/monitor/add?jdbcRef=' + $scope.jdbcRef + '&env=' + $scope.config.env).success(function (data, status, headers, config) {
                $scope.load();
            });
        }
    }

    $scope.removeJdbcRef = function () {
        if ($scope.jdbcRef) {
            $http.get('/a/monitor/remove?jdbcRef=' + $scope.jdbcRef + '&env=' + $scope.config.env).success(function (data, status, headers, config) {
                $scope.load();
            });
        }
    }

    $scope.markup = function (dsId) {
        if (dsId) {
            $http.get('/a/mha/markup?dsId=' + dsId).success(function (data, status, headers, config) {
                $scope.load();
            });
        }
    }
});

zebraWeb.controller('update', function ($scope, $http) {
    $http.get('/a/update/index').success(function (data, status, headers, config) {
        $scope.predicate = 'name';
        $scope.report = data;
    });
});

zebraWeb.controller('shard', function ($scope, $http, $modal, shardService) {
    $scope.load = function () {
        $http.get('/a/shard/' + $scope.config.env + '/config').success(function (data, status, headers, config) {
            $scope.data = data;
        });
    }

    $scope.edit = function (key) {
        shardService.openEditModal(key)
    }

    $scope.migrate = function (name) {
        var modal = $modal.open({
            templateUrl: 'app/template/shard-migrate.html',
            controller: 'shard-migrate',
            size: 'lg',
            resolve: {
                name: function () {
                    return name;
                },
                close: function () {
                    return function () {
                        if (modal) {
                            modal.close();
                        }
                    };
                }
            }
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

        if (item.index == 0) {
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
        })
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
        var readListHasWrite = false;
        var readListLength = 0;

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

                if (config.id.indexOf("write") >= 0) {
                    readListHasWrite = true;
                }

                readListLength++;
            }
        });
        $scope.data.config = '(' + readList + '),(' + writeList + ')';

        $scope.data.alert = "";

        if (readListHasWrite == true) {
            $scope.data.alert = "含有不合法的读role，只允许读账号作为读role。"
        }

        if (readListLength <= 1) {
            $scope.data.alert = $scope.data.alert + "配置的读role少于2个，将不具有读HA。"
        }
    }

    $scope.close = function () {
        close();
    }

    $scope.newDs = function () {
        if (!$scope.newDsName1) {
            return;
        }
        if ($scope.newDsName1.indexOf($scope.name) != 0) {
            alert('请以 ' + $scope.name + ' 开头！');
            return;
        }

        var propertiesTmp = [];
        propertiesTmp.push({
            key: 'ds.' + $scope.newDsName1 + '.jdbc.url',
            value: '',
            isCreate: true,
            newValue: ""
        });
        propertiesTmp.push({
            key: 'ds.' + $scope.newDsName1 + '.jdbc.uername',
            value: '',
            isCreate: true,
            newValue: ""
        });
        propertiesTmp.push({
            key: 'ds.' + $scope.newDsName1 + '.jdbc.password',
            value: '',
            isCreate: true,
            newValue: ""
        });
        propertiesTmp.push({
            key: 'ds.' + $scope.newDsName1 + '.jdbc.driverClass',
            value: '',
            isCreate: true,
            newValue: "com.mysql.jdbc.Driver"
        });
        propertiesTmp.push({
            key: 'ds.' + $scope.newDsName1 + '.jdbc.properties',
            value: '',
            isCreate: true,
            newValue: "${ds.datasource.properties}"
        });
        propertiesTmp.push({
            key: 'ds.' + $scope.newDsName1 + '.jdbc.active',
            value: '',
            isCreate: true,
            newValue: "true"
        });

        $scope.data.configs.push({
            id: $scope.newDsName1,
            role: {
                isWrite: false,
                isRead: false,
                weight: 0
            },
            properties: propertiesTmp
        });

        $scope.newDsName1 = '';
    }

    $scope.addDs = function (oldProperties, fromId, toId) {
        if (!toId) {
            return;
        }
        if (toId.indexOf($scope.name) != 0) {
            alert('请以 ' + $scope.name + ' 开头！');
            return;
        }

        if (toId == fromId) {
            return;
        }

        var configs = $scope.data.configs;
        var newProperties = [];

        angular.forEach(oldProperties, function (property) {
            var newKey = property['key'].replace(fromId, toId);
            var newProperty = {
                key: newKey,
                value: '',
                isCreate: true,
                newValue: property["value"]
            }
            newProperties.push(newProperty);
        });

        $scope.data.configs.push({
            id: toId,
            role: {
                isWrite: false,
                isRead: false,
                weight: 0
            },
            properties: newProperties
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
        });

        loginService.isLogin();
    }

    $scope.login = function () {
        loginService.login();
    }

    $scope.logout = function () {
        loginService.logout();
    }

    $scope.load();
});

zebraWeb.controller('config', function ($scope, $stateParams, $http, configService) {
    var convertKey = function (key) {
        return key;
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

    $scope.autoreplace = function (jdbcRef, isNew) {
        if (jdbcRef && $scope.config.env) {
            $http.post('/a/config/autoreplace?env=' + $scope.config.env + '&jdbcRef=' + jdbcRef + '&isNew=' + isNew).success(function (data, status, headers, config) {
                $scope.load();
            });
        }
    }

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