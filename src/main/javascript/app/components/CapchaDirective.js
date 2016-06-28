require('./../bookhub.app');


angular.module('bookhub').directive('captcha', CaptchaDirective);
CaptchaDirective.$inject = ['api_endpoint_url'];

function CaptchaDirective(api_endpoint_url) {

    return {
        restrict: 'E',
        scope:true,
        template: '<img src="' + api_endpoint_url + '/capcha"/> ' +
                    '<button type="button" class="btn" ' +
                    'ng-click="reCaptcha();">Değiştir</button> ',
        link: function (scope, elem, attr) {

            scope.reCaptcha = function () {
                scope.$broadcast('CHANGE_CAPTCHA');
            };

            scope.$on('CHANGE_CAPTCHA', function () {
                elem.find('img').attr('src', api_endpoint_url + '/capcha?r='+new Date().getTime())
            });
        }
    };
}