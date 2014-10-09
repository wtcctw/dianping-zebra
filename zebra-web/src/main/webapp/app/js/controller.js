zebraWeb.controller('update', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    $http.get('/a/update/view').success(function (data, status, headers, config) {
        $scope.report = data;
    });
}]);

zebraWeb.controller('update-database', ['$scope', '$stateParams', '$http', '$location', function ($scope, $stateParams, $http, $location) {
    $http.get('/a/update?op=database&database=' + $stateParams.name).success(function (data, status, headers, config) {
        $scope.database = data;
    });
}]);

zebraWeb.controller('update-app', ['$scope', '$stateParams', '$http', '$location', function ($scope, $stateParams, $http, $location) {
    $http.get('/a/update?op=app&app=' + $stateParams.name).success(function (data, status, headers, config) {
        $scope.app = data;
    });
}]);