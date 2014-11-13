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


zebraWeb.service('loginService', function ($modal, $rootScope) {
    this.login = function () {
        if ($rootScope.isLogin) {
            return;
        }

        $rootScope.isLogin = true;
        $modal.open({
            templateUrl: 'app/template/login.html',
            controller: 'login'
        });
    };
});