/*
 * Copyright (c) 2014 Red Hat, Inc. and others
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

package io.ensure.deepsea.client;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link io.ensure.deepsea.client.Client}.
 *
 * NOTE: This class has been automatically generated from the {@link io.ensure.deepsea.client.Client} original class using Vert.x codegen.
 */
public class ClientConverter {

  public static void fromJson(JsonObject json, Client obj) {
    if (json.getValue("clientId") instanceof String) {
      obj.setClientId((String)json.getValue("clientId"));
    }
    if (json.getValue("clientName") instanceof String) {
      obj.setClientName((String)json.getValue("clientName"));
    }
  }

  public static void toJson(Client obj, JsonObject json) {
    if (obj.getClientId() != null) {
      json.put("clientId", obj.getClientId());
    }
    if (obj.getClientName() != null) {
      json.put("clientName", obj.getClientName());
    }
  }
}