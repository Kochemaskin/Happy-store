(function ($localStorage) {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'about/about.html',
                controller: 'aboutController'
            })
            .when('/products', {
                templateUrl: 'products/products.html',
                controller: 'productsController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/order_confirmation', {
                templateUrl: 'order_confirmation/order_confirmation.html',
                controller: 'orderConfirmationController'
            })
            .when('/order_result/:orderId', {
                templateUrl: 'order_result/order_result.html',
                controller: 'orderResultController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .otherwise({
                redirectTo: '/'
            });

    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }
            $http({
                url: 'http://localhost:8189/market/api/v1/cart',
                method: 'POST',

            }).then(function (response) {
                $localStorage.cartUUID = response.data;
            })
    }
})();

angular.module('app').controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';
    const rootPath = 'http://localhost:8189/market';

    $scope.tryToAuth = function () {

        $scope.user.cartUuid = $localStorage.cartUUID;

        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.currentUser = {username: $scope.user.username, token: response.data.token};

                    $scope.currentUserName = $scope.user.username;
                    $scope.getUserAlias();

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $rootScope.$broadcast('logInOut', {
                        upUUID: $localStorage.cartUUID
                    });
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();

        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }

        $http({
            url: 'http://localhost:8189/market/api/v1/cart',
            method: 'POST',

        }).then(function (response) {
            $localStorage.cartUUID = response.data;
            $rootScope.$broadcast('logInOut', {
                upUUID: $localStorage.cartUUID
            });
        })


    };

    $scope.clearUser = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.getUserAlias = function () {
        $http.get(rootPath + '/auth/alias')
            .then(function (response) {
                $scope.userInfo = response.data;
            });

    }
});