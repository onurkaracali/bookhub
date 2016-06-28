var $ = require('jquery/dist/jquery');
require('angular');
require('angular-ui-router');
require('angular-ui-bootstrap');



angular.module('bookhub', ['ui.router', 'ui.bootstrap']);

angular.module('bookhub').constant('api_endpoint_url', 'http://localhost:8080/bookhub/api');

angular.module('bookhub').config(['$urlRouterProvider',
    '$stateProvider', '$httpProvider',
    function ($urlRouterProvider, $stateProvider, $httpProvider) {

        console.log('Configured');

        $httpProvider.interceptors.push('HttpInterceptor');


        $urlRouterProvider.otherwise('/');
        $stateProvider.state('home', {
            url: '/',
            controller: 'HomeController',
            controllerAs: 'hctrl',
            templateUrl: 'assets/templates/bookhub.home.html'
        });
}]);