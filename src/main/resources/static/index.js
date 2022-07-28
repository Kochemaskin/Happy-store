angular.module('app', []).controller('indexController', function ($scope, $http) {
    const apiPath = 'http://localhost:8189/market/api/v1';
    const rootPath = 'http://localhost:8189/market';
    let $currentPage=0;
    let $viewCount;

    $scope.fillTable = function () {
        $http({
            url: apiPath + '/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                page: $currentPage ? $currentPage : 0,
                count: $viewCount ? $viewCount : 3

            }
        }).then(function (response) {
            $scope.productsList = response.data;

            let minPageIndex = $currentPage - 2;
            if (minPageIndex < 0) {
                minPageIndex = 0;
            }

            let maxPageIndex = $currentPage + 2;
            if (maxPageIndex > $scope.productsList.totalPages-1) {
                maxPageIndex = $scope.productsList.totalPages-1;
            }

            $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);

        });
    };

    $scope.generatePagesIndexes = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage+1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.submitCreateNewProduct = function () {
        $http.post(apiPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (id) {
        $http({
            url: apiPath + '/products',
            method: 'DELETE',
            params: {
                id: id

            }
        }).then(function (response) {
            $scope.fillTable();
        });
    };

    $scope.changePage = function (page) {
        $currentPage = page;
        $viewCount = 3;
        $scope.fillTable();
    };

    $scope.fillCart = function() {
        $http({
            url: apiPath + '/cart',
            method: 'GET',

        }).then(function (response) {
            $scope.cartList = response.data;
        })
    };

    $scope.addToCartById = function (id) {
        $http({
            url: apiPath + '/cart/add/' + id,
            method: 'GET',

        }).then(function (response) {
            $scope.fillCart();
        })
    };

    $scope.removeFromCartById = function (id) {
        $http({
            url: apiPath + '/cart/delete/' + id,
            method: 'GET',

        }).then(function (response) {
            $scope.fillCart();
        })
    };

    $scope.removeAllFromCartById = function (id) {
        $http({
            url: apiPath + '/cart/delete/all/' + id,
            method: 'GET',

        }).then(function (response) {
            $scope.fillCart();
        })
    };

    $scope.clearCart = function () {
        $http({
            url: apiPath + '/cart/clear',
            method: 'GET',

        }).then(function (response) {
            $scope.fillCart();
        })
    };

    $scope.createOrder = function () {
        $http.post(apiPath + '/orders')
            .then(function (response) {
//                $scope.newProduct = null;
                $scope.fillOrders();
                $scope.fillCart();
            });
    };

    $scope.fillOrders = function () {
        $http.get(apiPath + '/orders')
            .then(function (response) {
                $scope.orderList = response.data;

            });
    };

    $scope.tryToAuth = function () {
        $http.post(rootPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.authorized = true;
                    $scope.fillTable();
                    $scope.fillOrders();
                    $scope.getUserAlias();
                }
            }, function errorCallback(response) {
                window.alert("Error");
            });
    };

    $scope.getUserAlias = function () {
        $http.get(rootPath + '/auth/alias')
            .then(function (response) {
                $scope.userInfo = response.data;
//                $scope.fillTable();
            });

    }

});