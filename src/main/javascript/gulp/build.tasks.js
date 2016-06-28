var gulp = require('gulp');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var clean = require('gulp-clean');





gulp.task('copy-views', function () {
    gulp.src('index.html')
        .pipe(gulp.dest('../webapp/'));

    gulp.src('./app/**/*.html')
        .pipe(gulp.dest('../webapp/assets/'));
});


gulp.task('build-js',  function () {

    var b = browserify({
        entries: './app/main.js',
        debug: true
    });

    return b.bundle()
        .pipe(source('bookhub.js'))
        .pipe(gulp.dest('../webapp/assets/js'));

});


gulp.task('build-styles', function () {
    var sass = require('gulp-sass');

    gulp.src('./sass/**/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest('../webapp/assets/css'))
});

gulp.task('build-dev', ['build-js', 'copy-views', 'build-styles']);