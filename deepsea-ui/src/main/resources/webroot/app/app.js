'use strict';

var vertxApp = angular.module('vertxApp', [ 'ngRoute', 'appControllers' ]).config(function($sceProvider) {
	  // Completely disable SCE.  For demonstration purposes only!
	  // Do not use in new projects or libraries.
	  $sceProvider.enabled(false);
	});



/**
 * Config routes
 */
vertxApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'app/view/home.html',
		controller : 'AppIndexCtrl'
	}).when('/p/:page', {
		templateUrl : 'app/view/home.html',
		controller : 'AppIndexCtrl'
	}).when('/products/:productId', {
		templateUrl : 'app/view/product-detail.html',
		controller : 'ProductDetailCtrl'
	}).when('/cart', {
		templateUrl : 'app/view/cart.html',
		controller : 'CartCtrl'
	}).when('/account', {
		templateUrl : 'app/view/account.html',
		controller : 'AccountCtrl'
	}).when('/bordereau', {
		templateUrl : 'app/view/bordereau_select.html',
		controller : 'UserBordereauCtrl'
	}).when('/bordereau/:clientId', {
		templateUrl : 'app/view/bordereau.html',
		controller : 'UserBordereauCtrl'
	}).when('/enrolment', {
		templateUrl : 'http://deepsea-admin-enrolment-deepsea.192.168.99.100.nip.io/app/view/enrolment.html',
		controller : 'UserEnrolmentCtrl'
	}).when('/404', {
		templateUrl : 'app/view/404.html'
	}).otherwise({
		redirectTo : '/404'
	})
} ]);
