angular.module('app').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {
    const apiPath = 'http://localhost:8855/happy/api/v1';

    $scope.cartContentRequest = function () {
        $http({
            url: apiPath + '/cart/' + $localStorage.cartUUID,
            method: 'GET'
        }).then(function (response) {
            $scope.cartList = response.data;
        });
    };

    $scope.submitOrder = function () {
        $http({
            url: apiPath + '/orders/' + $localStorage.cartUUID,
            method: 'POST',
            params: {
                address: $scope.order_info.address
            }
        }).then(function (response) {
            $location.path('/order_result/' + response.data.id);
        });
    }

    $scope.cartContentRequest();
});