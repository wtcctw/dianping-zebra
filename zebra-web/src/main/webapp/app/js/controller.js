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
        if ($rootScope.alertList) {
            $rootScope.alertList.shift();
            $rootScope.$apply();
        }
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

    $scope.finish = function () {
        if (confirm('确认该步骤已经完成？')) {
            $http.post('/a/shard/migrate/dump/' + $scope.name + '/finish').success(function (data, status, headers, config) {
                $scope.$parent.process = data;
            });
        }
    }

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
        if (confirm('确认删除？')) {
            $http.delete('/a/shard/migrate/dump/' + $scope.name + '/' + index).success(function (data, status, headers, config) {
                $scope.dump = data;
            });
        }
    }

    $scope.commitTask = function () {
        $http.post('/a/shard/migrate/dump/' + $scope.name, $scope.newTask).success(function (data, status, headers, config) {
            alert("创建成功！");
            $scope.dump = data;
            $scope.newTask.targets = [];
        });
    }
});

zebraWeb.controller('syncServers', function ($scope, $http){
	
});

zebraWeb.controller('syncTask', function ($scope, $http, $modal,syncService){
	$scope.load = function () {
        $http.get('/a/shard/' + $scope.config.env + '/config').success(function (data, status, headers, config) {
            $scope.shardRules = data;
        });
    }
    
    $scope.loadSyncTaskPlan = function (){
    	if($scope.shardRule){
    		$http.get('/a/syncTask?ruleName=' + $scope.shardRule).success(function (data, status, headers, config) {
    			$scope.syncTaskPlans = data;
    		});
    	}
    }
    
    $scope.schedule = function (pumaClientName){
    	if(pumaClientName){
    		$http.get('/a/syncTask/schedule?pumaClientName=' + pumaClientName).success(function (data, status, headers, config) {
    			$scope.syncTaskPlans.forEach(function (item){
    				if(item.pumaClientName == pumaClientName){
    					item.status = 2;
    				}
    			});
    		});
    	}
    }
    
    $scope.edit = function (pumaTask) {
    	syncService.openEditModal(pumaTask);
    }

    $scope.$watch('shardRule', $scope.loadSyncTaskPlan);

    $scope.load();
});

zebraWeb.controller('syncTask-edit', function ($scope, $http, pumaTask, close){
	$scope.name = pumaTask.pumaClientName;
	
	$scope.load = function() {
		$http.get('/a/sync-server').success(function (data, status, headers, config) {
			$scope.syncServers = data;
			
			$scope.executor = pumaTask.executor;
			$scope.executor1 = pumaTask.executor1;
			$scope.executor2 = pumaTask.executor2;
		});
	}
	
	$scope.load();
	
	$scope.save = function(){
		pumaTask.executor = $scope.executor;
		pumaTask.executor1 = $scope.executor1;
		pumaTask.executor2 = $scope.executor2;
		
		$http.get('/a/syncTask/updateSyncServer?pumaClientName=' + pumaTask.pumaClientName
				+ '&executor=' + $scope.executor + '&executor1=' + $scope.executor1 + "&executor2=" + $scope.executor2).success(function (data, status, headers, config) {
			close();
		});
	}
	
	$scope.close = function(){
		close();
	}
});

zebraWeb.controller('shard-migrate', function ($scope, $http, name, close) {
    $scope.name = name;

    $scope.init = function () {
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
    }

    $http.get('/a/shard/migrate/process/' + name).success(function (data, status, headers, config) {
        $scope.process = data;
        $scope.init();
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
        $http.get('/a/validate/dbs').success(function (data, status, headers, config) {
            $scope.databases = data;
        });
    }

    $scope.analyze = function () {
        if (!$scope.db) {
            alert("请选择数据库");
            return;
        }

        if (!$scope.table) {
            alert("请选择表");
            return;
        }

        $http.get("/a/validate?database=" + $scope.db + "&table=" + $scope.table).success(function (data, status, headers, config) {
            $scope.data = data;
        });
    }

    $scope.loadTable = function () {
        $http.get("/a/validate/tables?database=" + $scope.db).success(function (data, status, headers, config) {
            $scope.tables = data;
        });
    }

    $scope.$watch('db', $scope.loadTable);

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

zebraWeb.controller('monitor-history', function($scope, $http){
	$scope.load = function () {
        $http.get('/a/monitor/history').success(function (data, status, headers, config) {
            $scope.histories = data;
        });
    }
	
	$scope.markup = function (dsId) {
        if (dsId) {
            $http.get('/a/mha/markup?dsId=' + dsId).success(function (data, status, headers, config) {
                $scope.load();
            });
        }
    }
	
	$scope.load();
});

zebraWeb.controller('monitor-manager', function($scope, $http){
	$scope.load = function () {
        $http.get('/a/monitor/servers').success(function (data, status, headers, config) {
            $scope.servers = data;
        });
    }
	
	$scope.jdbcRefsList = [];

	$scope.tickedList = [];
	
	$scope.getTickedList = function() {
		$http.get('/a/monitor/getTickedList?ip=' + $scope.monitorServer).success(function (data, status, headers, config) {
			if(data != null) {
				var ticked = [];
				
				angular.forEach(data, function(value) {
					ticked.push(value);
				});
				
				$scope.tickedList = ticked;
			}
		});
	}
	
	$scope.myFilter = function(value) {
		$scope.flag = false;
		
		angular.forEach($scope.tickedList, function(data){
			if(value == data) {
				$scope.flag = true;
			}
		});
	}
	
	$scope.switchSource = function() {
		$scope.getTickedList();
		$http.get('/a/monitor/getJdbcRefList').success(function (data, status, headers, config) {
			var list = [];
			if(data != null) {
				angular.forEach(data, function(value){
					$scope.myFilter(value);
					var jsonValue = {
							"jdbcRef": value,
							"ticked" : $scope.flag
					};
					
					list.push(jsonValue);
				});
			}
			
			$scope.jdbcRefsList = list;
		});
	}
	
	$scope.loadMultiSelects = function(){
        $scope.jdbcRefs = null;
		angular.forEach($scope.jdbcRefChosed, function(data) {
			if(data.ticked) {
				if(!$scope.jdbcRefs) {
					$scope.jdbcRefs = data.jdbcRef;
				} else {
					$scope.jdbcRefs += (',' + data.jdbcRef);
				}
			}
		});
	}
	
	$scope.loadMonitorDs = function() {
		if($scope.monitorServer){
			$http.get('/a/monitor/getStatus?ip=' + $scope.monitorServer).success(function (data, status, headers, config) {
				$scope.statusList = data;
			});
			$scope.switchSource();
		}
	}

	$scope.submitJdbcRef = function() {
		if(!$scope.monitorServer){
			alert("请选择监控服务器");
			return;
		}
		
		$scope.loadMultiSelects();
		
        $http.get('/a/monitor/submit?jdbcRefs=' + $scope.jdbcRefs + "&ip=" + $scope.monitorServer).success(function (data, status, headers, config) {
        	if(data.errorCode == 1) {
        		alert(data.errorMessage);
        	}
        	$scope.loadMonitorDs();
        });
	}
   
	$scope.$watch('monitorServer', $scope.loadMonitorDs);
	
	$scope.load();
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
                m_allowPercent: selected[0].text == '0' ? -1:selected[0].text,
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
                
                $scope.initRWList();
            });
        }
    }

    $scope.changeName = function () {
        if ($scope.config && $scope.config.env) {
            $http.get('/a/config/ds?key=' + name + '&env=' + $scope.config.env + '&otherkey=' + $scope.changedName).success(function (data, status, headers, config) {
                $scope.data = data;
                
                $scope.initRWList();
           });
        }
    }

    $scope.$watch('config.env', $scope.load);

    $scope.writeList = [];
    
    $scope.readList  = [];
    
    $scope.initRWList = function() {
    	var start = 0;
    	var end   = 0;
    	
    	start = $scope.data.config.indexOf('(') +1;
    	end   = $scope.data.config.indexOf(')');
    	
    	if(start <= end) {
        	var readStrings = $scope.data.config.substring(start,end);
        	var readStrs    = readStrings.split(',');
        	
        	angular.forEach(readStrs,function(key) {
        		$scope.readList.push(key);
        	});
        	
    	}

    	start = $scope.data.config.lastIndexOf('(') +1;
    	end   = $scope.data.config.lastIndexOf(')');
    	
    	if(start <= end) {
        	var writeString = $scope.data.config.substring(start,end);

        	$scope.writeList.push(writeString);
    	}
    }
    
    $scope.makeDataConfig = function() {
    	$scope.data.config = '(' + $scope.readList.join() + '),(' + $scope.writeList.join() + ')';
    }
    
    $scope.removeWriteId = function(key) {
		$scope.writeList.splice(0,1);
		
		$scope.makeDataConfig();
    }
    
    $scope.removeReadId = function(key) {
    	var pos = 0;
    	angular.forEach($scope.readList,function(id,index) {
    		if(key ==id) {
    			pos = index;
    		}
    	});
    	
    	$scope.readList.splice(pos,1);
    	
		$scope.makeDataConfig();
    }
    
    $scope.isWriteChange = function(config) {
    	if(config.role != null) {
    		if(config.role.isWrite) {
        		config.role.isWrite = false;
        		
        		$scope.removeWriteId(config.id);
        		
        		return;
        	}
    	} else {
    		config.role = {
					isWrite : true,
					isRead  : false,
					weight  : 1
			};
    	}

		if(config.id.indexOf('-write') <= 0) {
			config.role.isWrite = false;
			alert("只有以-write结尾的库能够成为写库!");
			
			return;
		}
		
		if($scope.writeList.length > 0) {
			config.role.isWrite = false;
			alert("一个jdbcRef只能有1个写库!");
			
			return;
		}
		
		if(config.role.isRead) {
			config.role.isRead = false;
			
			$scope.removeWriteId(config.id);
		}
	
		config.role.isWrite = true;
		config.role.weight  = 1;
		
		$scope.writeList.push(config.id);
		
		$scope.makeDataConfig();
	}
    
    $scope.isReadChange = function(config) {
    	if(config.role != null) {
    		if(config.role.isRead) {
        		config.role.isRead = false;
        		
        		var readConf = config.id + ':' + config.role.weight;
        		$scope.removeReadId(readConf);
        		
        		return;
        	}
    	} else {
    		config.role = {
					isWrite : false,
					isRead  : true,
					weight  : 1
			};
    	}
    	
		if(config.id.indexOf('-read') <= 0) {
			config.role.isRead = false;
			alert("只有以-read结尾的库能够成为读库!");
			
			return;
		}
		
		config.role.isRead = true;
		config.role.weight = 1;
		
		var readConf = config.id + ':' + config.role.weight;
		$scope.readList.push(readConf);
    	
		$scope.makeDataConfig();
    }
    
    $scope.dbNumTest = function() {
    	var readNum  = $scope.readList.length;
    	var writeNum = $scope.writeList.length;
    	
    	var writeIp  = "";
    	var isVIPRight = false;
    	
    	if(readNum < 1 || writeNum < 1) {
    		alert("请设置至少1个读库和1个写库!");
    		
    		return false;
    	}
    	
    	angular.forEach($scope.data.configs,function(config) {
    		if(config.id == $scope.writeList[0]) {
        		angular.forEach(config.properties,function(property) {
    				if(property.key.indexOf('active') > 0) {
    					if(property.newValue != 'true') {
    						writeNum -= 1;
    					}
    				}
    				
    				if(property.key.indexOf('url') > 0 || property.key.indexOf('Url') > 0) {
    					writeIp = property.newValue.match("[\\d+\\.]+");
    					if(!writeIp) {
    						alert("写RUL格式错误!");
    						
    						return false;
    					}
    				}
        		});
    		}
    	});
    	
    	if(writeNum <1) {
    		alert('至少要有1个写库可用,并且active为true!');
    		
    		return false;
    	}
    	
    	angular.forEach($scope.data.configs,function(config) {
    		angular.forEach($scope.readList,function(readId) {
        		var readIdWithoutWeight = readId.substring(0,readId.indexOf(':'));
        		var readIdWeight = readId.substring(readId.indexOf(':')+1,readId.length);
        		
    			if(config.id == readIdWithoutWeight) {
    				if(readIdWeight == '0') {
						readNum -= 1;
					} else {
        				angular.forEach(config.properties,function(property) {
            				if(property.key.indexOf('active') > 0) {
            					if(property.newValue != 'true') {
            						readNum -= 1;
            					}
            				}
            				
            				if(property.key.indexOf('url') > 0 || property.key.indexOf('Url') > 0) {
            					var readIp = property.newValue.match("[\\d+\\.]+");
            					
            					if(!readIp) {
            						alert("读库RUL格式错误!");
            						
            						return false;
            					}
            					
            					if(readIp[0] == writeIp[0]) {
            						isVIPRight = true;
            					}
            				}
            			});
        			}
    			} 
    		});
    	});
    	
    	if(readNum < 1) {
    		alert('至少要有1个读库可用,并且active为true!');
    		
    		return false;
    	}
    	
    	if(!isVIPRight) {
    		alert("需要配置写库 的读vip!");
    		
    		return false;
    	}
    	
    	return true;
    }
    
    var watchWeightChange = function() {
    	angular.forEach($scope.data.configs,function(config) {
    		angular.forEach($scope.readList,function(readId,index) {
        		var readIdWithoutWeight = readId.substring(0,readId.indexOf(':'));
        		var readIdWeight = readId.substring(readId.indexOf(':')+1,readId.length);
        		
    			if(config.id == readIdWithoutWeight && !!config.role) {
    				if(!config.role.weight) {
    					config.role.weight = 0;
    				}
    				
    				if(readIdWeight != config.role.weight.toString()) {
    					readId = readIdWithoutWeight + ':' + config.role.weight;
    					$scope.readList.splice(index,1,readId);
    			    	$scope.makeDataConfig();
    				}   
    			}
    		});
    	});
    }
    
    $scope.$watch(function () {
    	if($scope.data) {
        	watchWeightChange();
    	}
    })
    
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
            key: 'ds.' + $scope.newDsName1 + '.jdbc.username',
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
        if($scope.dbNumTest()) {
            $http.post('/a/config/updateds?force=' + force, angular.toJson($scope.data))
            .success(function (data, status, headers, config) {
                alert('保存成功！');
                close();
                configService.openTestModal($scope.name);
            });
        }
    }

    $scope.test = function () {
        $http.post('/a/config/test?env=' + $scope.config.env + "&key=" + $scope.name, angular.toJson($scope.data))
            .success(function (data, status, headers, config) {
                configService.openTestModal("", data);
            });
    }

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
        configService.openTestModal(key);
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
    
    $scope.addMonitor = function(group) {
         $http.get('/a/monitor/addJdbcRef?env=' + $scope.config.env +'&jdbcRef=' + group.jdbcRef).success(function (data, status, headers, config) {
            if(!data.errorCode) {
            	group.isMonitored = true; 
            } else {
               	alert(data.errorMessage);
           	}
         });
    }
    
    $scope.removeMonitor = function(group) {
    	$http.get('/a/monitor/removeJdbcRef?env=' + $scope.config.env +'&jdbcRef=' + group.jdbcRef).success(function (data, status, headers, config) {
    		if(!data.errorCode) {
            	group.isMonitored = false; 
            } else {
               	alert(data.errorMessage);
           	}
    	});
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