module.exports = {
    bundle: {
        main: {
        },
        vendor: {
            scripts: [
                './vendors/jquery.min.js',
                './vendors/angular.min.js'
            ]
        }
    },
    copy: './content/**/*.{png,svg}'
};