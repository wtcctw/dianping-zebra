zebraWeb.service('shardService', function ($modal) {
    this.openEditModal = function (name, onClose) {
        var modal = $modal.open({
            templateUrl: 'app/template/shard-edit.html',
            controller: 'shard-edit',
            size: 'lg',
            backdrop: false,
            resolve: {
                name: function () {
                    return name;
                },
                close: function () {
                    return function () {
                        if (modal) {
                            modal.close();
                        }
                        if (onClose) {
                            onClose();
                        }
                    };
                }
            }
        });
    };
});

zebraWeb.service('configService', function ($modal) {
    this.openTestModal = function (name, configs) {
        $modal.open({
            templateUrl: 'app/template/config-test.html',
            controller: 'config-test',
            size: 'lg',
            resolve: {
                name: function () {
                    return name;
                },
                configs: function () {
                    return configs;
                }
            }
        });
    };

    this.openEditModal = function (name, onClose) {
        var modal = $modal.open({
            templateUrl: 'app/template/config-edit.html',
            controller: 'config-edit',
            size: 'lg',
            backdrop: false,
            resolve: {
                name: function () {
                    return name;
                },
                close: function () {
                    return function () {
                        if (modal) {
                            modal.close();
                        }
                        if (onClose) {
                            onClose();
                        }
                    };
                }
            }
        });
    };

    this.openMergeModal = function (name, onClose) {
        var modal = $modal.open({
            templateUrl: 'app/template/merge-edit.html',
            controller: 'merge-edit',
            size: 'lg',
            backdrop: false,
            resolve: {
                name: function () {
                    return name;
                },
                close: function () {
                    return function () {
                        if (modal) {
                            modal.close();
                        }
                        if (onClose) {
                            onClose();
                        }
                    };
                }
            }
        });
    };
});

zebraWeb.service('alertService', function ($rootScope) {
    this.addException = function (msg) {
        if (!$rootScope.alertList) {
            $rootScope.alertList = [];
        }

        $rootScope.alertList.push({
            "msg": msg,
            "className": "alert-danger"
        });
    };
});

zebraWeb.service('loginService', function ($modal, $rootScope, $http) {
    this.login = function () {
        if ($rootScope.isLogin) {
            return;
        }

        $modal.open({
            templateUrl: 'app/template/login.html',
            controller: 'login'
        });
    };

    this.logout = function () {
        if ($rootScope.isLogin == false) {
            return;
        }

        $http.get('/a/logout').success(function (data) {
            $rootScope.isLogin = false;
        });
    };

    this.isLogin = function () {
        $http.get('/a/login').success(function (data) {
            $rootScope.isLogin = data;
        });
    }
});