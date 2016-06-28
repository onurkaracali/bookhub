var $ = require('jquery/dist/jquery');
require('angular');

require('./service');
require('./controllers');
require('./components');

function main() {

    var appWrap = $('body');
    angular.bootstrap(appWrap.get(0), ['bookhub']);
}


$(document).ready(main);