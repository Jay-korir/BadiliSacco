'use strict'
var gulp = require('gulp');
var requireDir = require('require-dir');
requireDir('gulp-tasks');


gulp.paths = {
    dist: 'dist',
};

var paths = gulp.paths;

//gulp.task('default', gulp.series('serve'));
// const build = series(clean, imagemin, sass, js);

gulp.task('clean:dist', function (resolve) {
  del.sync('dist');
  resolve();
})