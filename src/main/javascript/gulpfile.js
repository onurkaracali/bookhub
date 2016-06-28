var gulp = require('gulp');

require('./gulp/build.tasks');
require('./gulp/serve.tasks');
require('./gulp/vendors.tasks');

gulp.task('default', ['build-dev', 'vendor-css','serve' ]);