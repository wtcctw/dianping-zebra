zebraWeb.service('configService', function ($modal) {
    this.openTestModal = function (name) {
        $modal.open({
            templateUrl: 'app/template/config-test.html',
            controller: 'config-test',
            size: 'lg',
            resolve: {
                name: function () {
                    return name;
                }
            }
        });
    };
});
