require('./../bookhub.app');

angular.module('bookhub').factory('HttpInterceptor', HttpInterceptor);

HttpInterceptor.$inject = ['$q', '$injector', '$rootScope'];
function HttpInterceptor($q, $injector,  $rootScope) {

    return {

        /**
         * Catch all response of ajax calls, if response type is error
         * redirect to error
         */
        response: function (response) {
            if (angular.isDefined(response.data.type)
                && response.data.result === 'ERROR') {
                return $q.reject(response);
            }
            return response;
        },

    };
}