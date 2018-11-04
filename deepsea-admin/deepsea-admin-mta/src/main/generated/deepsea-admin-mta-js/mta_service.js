/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

/** @module deepsea-admin-mta-js/mta_service */
var utils = require('vertx-js/util/utils');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JMTAService = Java.type('io.ensure.deepsea.admin.mta.MTAService');
var MidTermAdjustment = Java.type('io.ensure.deepsea.admin.mta.MidTermAdjustment');

/**
 @class
*/
var MTAService = function(j_val) {

  var j_mTAService = j_val;
  var that = this;

  /**

   @public
   @param resultHandler {function} 
   @return {MTAService}
   */
  this.initializePersistence = function(resultHandler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_mTAService["initializePersistence(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        resultHandler(null, null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param mta {Object} 
   @param resultHandler {function} 
   @return {MTAService}
   */
  this.addMTA = function(mta, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_mTAService["addMTA(io.ensure.deepsea.admin.mta.MidTermAdjustment,io.vertx.core.Handler)"](mta != null ? new MidTermAdjustment(new JsonObject(Java.asJSONCompatible(mta))) : null, function(ar) {
      if (ar.succeeded()) {
        resultHandler(ar.result(), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param lastId {number} 
   @param resultHandler {function} 
   @return {MTAService}
   */
  this.replayMTAs = function(lastId, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] ==='number' && typeof __args[1] === 'function') {
      j_mTAService["replayMTAs(java.lang.Integer,io.vertx.core.Handler)"](utils.convParamInteger(lastId), function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnListSetDataObject(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_mTAService;
};

MTAService._jclass = utils.getJavaClass("io.ensure.deepsea.admin.mta.MTAService");
MTAService._jtype = {
  accept: function(obj) {
    return MTAService._jclass.isInstance(obj._jdel);
  },
  wrap: function(jdel) {
    var obj = Object.create(MTAService.prototype, {});
    MTAService.apply(obj, arguments);
    return obj;
  },
  unwrap: function(obj) {
    return obj._jdel;
  }
};
MTAService._create = function(jdel) {
  var obj = Object.create(MTAService.prototype, {});
  MTAService.apply(obj, arguments);
  return obj;
}
module.exports = MTAService;