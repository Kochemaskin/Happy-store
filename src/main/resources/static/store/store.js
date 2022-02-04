angular.module('market-front').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8855/happy/';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.name_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };

    $scope.addToCart = function (productId) {
        $http.get(contextPath + 'api/v1/cart/add/' + productId)
            .then(function (response) {
            });
    }

    $scope.loadProducts();
});