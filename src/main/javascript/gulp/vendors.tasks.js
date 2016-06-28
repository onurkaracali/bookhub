var gulp = require('gulp');
var gulpFilter = require('gulp-filter');
var rename = require('gulp-rename');
var concat = require('gulp-concat');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var bundle = require('gulp-bundle-assets');


var vendors = [
    './node_modules/bootstrap/dist/css/bootstrap.css',
    './node_modules/angular-ui-bootstrap/dist/ui-bootstrap-csp.css'
];

gulp.task('vendor-js', function() {


    var b = browserify({
        entries: './vendors/vendor-loader.js',
        debug: true
    });

    return b.bundle()
        .pipe(source('vendors.js'))
        .pipe(gulp.dest('../webapp/js'));

});

gulp.task('vendor-css', function() {

    return gulp.src(vendors)
        .pipe(concat('vendor.css'))
        .pipe(gulp.dest('../webapp/assets/css'));

});



gulp.task('bundle-assets', function () {

    return gulp.src('./bundle.config.js')
        .pipe(bundle())
        .pipe(bundle.results({
                fileName: 'manifest'
            }))
        .pipe(gulp.dest('./../webapp/js'));
});

gulp.task('build-vendors', ['vendor-js', 'vendor-css']);