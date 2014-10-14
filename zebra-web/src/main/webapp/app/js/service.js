zebraWeb.service('configService', function ($modal) {
    this.openTestModal = function (name, env) {
        $modal.open({
            templateUrl: 'app/template/config-test.html',
            controller: 'config-test',
            size: 'lg',
            resolve: {
                name: function () {
                    return name;
                },
                env: function () {
                    if (!env) {
                        env = '';
                    }
                    return env;
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
});
