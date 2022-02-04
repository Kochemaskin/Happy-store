angular.module('market-front').controller('orderController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8855/happy/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'api/v1/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    }

    $scope.loadOrders();
});