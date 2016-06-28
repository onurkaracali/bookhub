require('./../bookhub.app');

angular.module('bookhub').controller('UpdateBookController', UpdateBookController);

UpdateBookController.$inject = ['$uibModalInstance', 'BookService'];
function UpdateBookController(modalInstance, bookService) {
    var vm = this;

    vm.selectedBook = {};
    vm.errorMessage = null;

    activate();
    function activate() {
        vm.selectedBook = {id : bookService.updatingBookObj.id,
            name : bookService.updatingBookObj.name,
            author : bookService.updatingBookObj.author};
    }


    vm.cancelAction = function () {
        modalInstance.dismiss();
    };

    vm.updateBook = function () {
        bookService.updateBook(vm.selectedBook).then(function (response) {
            modalInstance.close();
        }, function (response) {
            console.log(response);
            vm.errorMessage = 'İşleminizi şuanda gerçekleştiremiyoruz';
        });
    };

    return vm;
};