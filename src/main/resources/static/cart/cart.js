angular.module('app').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const apiPath = 'http://localhost:8855/happy/api/v1';



    $scope.fillCart = function() {
        $http({
            url: apiPath + '/cart/' + $localStorage.cartUUID,
            method: 'GET',

        }).then(function (response) {
            $scope.cartList = response.data;
        })
    };

    $scope.$on('logInOut', function (event, data) {
        $scope.fillCart();
    });

    $scope.addToCartById = function (id) {
        $http({
            url: apiPath + '/cart/' + $localStorage.cartUUID + '/add/' + id,
            method: 'GET',

        }).then(function (response) {
             $scope.fillCart();
        })
    };

    $scope.removeFromCartById = function (id) {
        $http({
            url: apiPath + '/cart/' + $localStorage.cartUUID + '/delete/' + id,
            method: 'GET',

        }).then(function (response) {
            $scope.fillCart();
        })
    };

    $scope.removeAllFromCartById = function (id) {
        $http({
            url: apiPath + '/cart/' + $localStorage.cartUUID + '/delete/all/' + id,
            method: 'GET',

        }).then(function (response) {
            $scope.fillCart();
        })
    };

    $scope.clearCart = function () {
        $http({
            url: apiPath + '/cart/' + $localStorage.cartUUID + '/clear',
            method: 'GET',

        }).then(function (response) {
            $scope.fillCart();
        })
    };

    $scope.createOrder = function () {
        $http.post(apiPath + '/orders/' + $localStorage.cartUUID, this.orderDelivery)
            .then(function (response) {
                $scope.fillCart();
            });
    };

    $scope.goToOrderSubmit = function () {
        $location.path('/order_confirmation');
    }

    $scope.fillCart();
});