require('./../bookhub.app');

angular.module('bookhub').controller('HomeController', HomeController);

HomeController.$inject = ['BookService', '$uibModal'];
function HomeController(bookService, $uibModal) {
    var vm = this;
    vm.allBooks = [];

    vm.errorStatus = false;

    function activate() {
        loadBookList();
    };
    activate();


    function loadBookList() {
        vm.allBooks = bookService.getAllBooks()
            .then(function (response) {
                var rdata = response.data || {}
                vm.allBooks = rdata.data || [];
            })
            .catch(function (response) {
                vm.errorStatus = true;
            });
    };

    vm.openDialogForAddNewBook = function () {
        var modalInstance = $uibModal.open({
            templateUrl: 'assets/templates/addbook.modal.html',
            controller: 'AddBookController',
            controllerAs: 'addBookCtl',
            backdrop: 'static'
        });


        modalInstance.result.then(function () {
            loadBookList();
        });
    };

    vm.updateBook = function (bookObj) {

        bookService.updatingBookObj = bookObj;

        var modalInstance = $uibModal.open({
            templateUrl: 'assets/templates/updatebook.modal.html',
            controller: 'UpdateBookController',
            controllerAs: 'uptBookCtl',
            backdrop: 'static'
        });


        modalInstance.result.then(function () {
            loadBookList();
        });
    };

    vm.deleteBook = function (book) {
        bookService.deleteBook(book.id).then(function (response) {
                loadBookList();
            })
            .catch(function (response) {
                vm.errorStatus = true;
            })
    };

    return vm;
}