require('./../bookhub.app');

angular.module('bookhub').factory('HttpInterceptor', HttpInterceptor);

HttpInterceptor.$inject = ['$q', '$injector', '$rootScope'];
function HttpInterceptor($q, $injector, $rootScope) {

    return {

        request: function (request) {
            $rootScope.$emit('AJAX_CALL_STARTED');
            return request;
        },

        response: function (response) {
            $rootScope.$emit('AJAX_CALL_FINISHED');

            if (angular.isDefined(response.data.type)
                && response.data.result === 'ERROR') {
                return $q.reject(response);
            }
            return response;

        },

        responseError: function (response) {
            $rootScope.$emit('AJAX_CALL_FINISHED');
            return $q.reject(response);
        }

    };
}