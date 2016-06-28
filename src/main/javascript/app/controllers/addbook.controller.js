require('./../bookhub.app');

angular.module('bookhub').controller('AddBookController', AddBookController);

AddBookController.$inject = ['$uibModalInstance', 'BookService'];
function AddBookController(modalInstance, bookService) {
    var vm = this;

    vm.newBookObj = {};
    vm.errorMessage = null;


    vm.cancelAction = function () {
        modalInstance.dismiss();
    };

    vm.addBookAction = function () {

        bookService.addNewBook(vm.newBookObj).then(function (response) {
            modalInstance.close();
        }, function (response) {
            console.log(response);

            var errObj = response.data || {};
            var errorKey = errObj.data || '';

            if (errorKey === "INCORRECT_CAPTCHA") {
                vm.errorMessage = 'Captcha değeri hatalı';
            }
            else {
                vm.errorMessage = 'İşleminizi şuanda gerçekleştiremiyoruz';
            }
        });

    };

    return vm;
};