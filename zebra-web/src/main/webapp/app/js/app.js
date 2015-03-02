var zebraWeb = angular.module('ZebraWeb', ['ui.bootstrap', 'ui.router', 'ui.checkbox', 'ngCookies']);

zebraWeb.filter('objectCount', function () {
    return function (input) {
        var size = 0, key;
        for (key in input) {
            if (input.hasOwnProperty(key)) size++;
        }
        return size;
    }
});

zebraWeb.filter('toArray', function () {
    'use strict';

    return function (obj) {
        if (!(obj instanceof Object)) {
            return obj;
        }

        return Object.keys(obj).filter(function (key) {
            if (key.charAt(0) !== "$") {
                return key;
            }
        }).map(function (key) {
            if (!(obj[key] instanceof Object)) {
                obj[key] = {value: obj[key]};
            }

            return Object.defineProperty(obj[key], '$key', {__proto__: null, value: key});
        });
    };
});

zebraWeb.directive('snippet', function () {
    return {
        restrict: 'E',
        template: '<pre><div class="hidden code" ng-transclude></div><code></code></pre>',
        replace: true,
        transclude: true,
        link: function (scope, elm, attrs) {
            scope.$watch(function () {
                return elm.find('.code').text();
            }, function (newValue, oldValue) {
                if (newValue != oldValue) {
                    elm.find('code').html(hljs.highlightAuto(newValue).value);
                }
            });
        }
    };
});

zebraWeb.directive('activeLink', ['$location', function (location) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs, controller) {
            var clazz = attrs.activeLink;
            var path = element.children().attr('href');
            path = path.substring(1);
            scope.location = location;
            scope.$watch('location.path()', function (newPath) {
                if (newPath.indexOf(path) > -1) {
                    element.addClass(clazz);
                } else {
                    element.removeClass(clazz);
                }
            });
        }
    };
}]);

zebraWeb.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/config");
    $stateProvider.state('config', {
        url: '/config',
        templateUrl: 'app/template/config.html',
        controller: 'config'
    }).state('shard', {
        url: '/shard',
        controller: 'shard',
        templateUrl: 'app/template/shard.html'
    }).state('flow-control', {
              url: '/flow',
              controller: 'flow',
              templateUrl: 'app/template/flow-control.html'
    }).state('update', {
        url: '/update',
        templateUrl: 'app/template/update.html',
        controller: 'update'
    }).state('update-app', {
        url: '/update/app/{name}',
        templateUrl: 'app/template/update-app.html',
        controller: 'update-app'
    }).state('update-database', {
        url: '/update/database/{name}',
        templateUrl: 'app/template/update-database.html',
        controller: 'update-database'
    });
});


