require('./../bookhub.app');


angular.module('bookhub').directive('ajaxSpinner', AjaxSpinnerDirective);

//-------------------------------------------------------------------------------------------------------
AjaxSpinnerDirective.$inject = [
    '$rootScope',
    '$timeout'
];

//-------------------------------------------------------------------------------------------------------

function AjaxSpinnerDirective($rootScope, $timeout) {
    var bricks = [];

    return {
        restrict :'E',
        template : '<div class="ajax-spinner-lay"><div class="ajax-spinner-foreground"></div><div>',
        link: function(scope, elem, attrs) {

            elem.css('display', 'none');

            $rootScope.$on('AJAX_CALL_STARTED', function() {
                bricks.push('*');

                elem.css('display', 'block');
            });

            $rootScope.$on('AJAX_CALL_FINISHED', function(){
                bricks.pop();

                if (bricks.length == 0)
                    elem.css('display', 'none');
            });


        }
    }
}