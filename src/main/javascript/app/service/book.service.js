require('./../bookhub.app');

angular.module('bookhub').service('BookService',BookService);

BookService.$inject = ['$http', 'api_endpoint_url'];
function BookService($http, api_endpoint_url) {
    this.updatingBookObj = null;

    this.getAllBooks = function () {
        var callUrl = api_endpoint_url + '/book/';
        return $http.get(callUrl);
    };
    
    this.addNewBook = function (requestObj) {
        var callUrl = api_endpoint_url + '/book/';
        return $http.post(callUrl, requestObj);
    };

    this.deleteBook = function (bookId) {
        var callUrl = api_endpoint_url + '/book/' + bookId;
        return $http.delete(callUrl);
    };

    this.updateBook = function (book) {
        var callUrl = api_endpoint_url + '/book/';
        return $http.put(callUrl, book);
    }
}