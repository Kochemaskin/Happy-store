angular.module('app').controller('productsController', function ($scope, $http, $localStorage) {
    const apiPath = 'http://localhost:8855/happy/api/v1';
    // const rootPath = 'http://localhost:8855/happy';
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

    $scope.addToCartById = function (id) {
        $http({
            // url: apiPath + '/cart/add/' + id,
            url: apiPath + '/cart/' + $localStorage.cartUUID + '/add/' + id,
            method: 'GET',

        }).then(function (response) {
            $scope.fillTable();
        })
    };

    $scope.fillTable();
});