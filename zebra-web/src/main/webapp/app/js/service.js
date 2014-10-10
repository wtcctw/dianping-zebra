zebraWeb.service('configService', function ($http, $modal) {
    this.test = function (name) {
        $http.get('/a/update?op=connection&database=' + name).success(function (data, status, headers, config) {
            $modal.open({
                templateUrl: 'app/template/config-test.html',
                controller: 'config-test',
                size: 'lg',
                resolve: {
                    name: function () {
                        return name;
                    },
                    connectionStatus: function () {
                        return data;
                    }
                }
            });
        });
    };
});
