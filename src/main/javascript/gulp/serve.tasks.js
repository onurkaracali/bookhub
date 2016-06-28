var gulp = require('gulp');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var clean = require('gulp-clean');
var debowerify = require('debowerify');
var browserSync = require('browser-sync').create();
var connect = require('gulp-connect');


gulp.task('reload', ['build-dev'], function () {
    console.log('Reloading');
    browserSync.reload();
});


gulp.task('watch', ['start-server'], function () {

    // watch for changes livereload browser
    gulp.watch([
        '**/*.html',
        'app/**/*.*',
        'sass/**/*.*'
    ], ['reload', 'copy-views']);

});

gulp.task('start-server',
    ['build-dev'],
    function () {

        var url = require('url');
        var proxy = require('proxy-middleware');

        var options = url.parse('http://localhost:8080/bookhub/api/');
        options.route = '/api';

        browserSync.init({
            server: {
                baseDir: "../webapp/",
                middleware: [proxy(options)]
            },
            port: 3000,
            open: false
        });


    });

gulp.task('clean', function () {

    gulp.src(['app/tmp', '../webapp/'], {read: false})
        .pipe(clean());
});

gulp.task('serve', ['start-server', 'watch']);