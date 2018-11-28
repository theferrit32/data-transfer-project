/*
 * Copyright 2018 The Data Transfer Project Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.datatransferproject.auth.helium;

import com.google.api.client.http.HttpTransport;
import com.google.common.collect.ImmutableList;
import org.datatransferproject.api.launcher.ExtensionContext;
import org.datatransferproject.spi.api.auth.AuthDataGenerator;
import org.datatransferproject.spi.api.auth.AuthServiceProviderRegistry;
import org.datatransferproject.spi.api.auth.extension.AuthServiceExtension;
import org.datatransferproject.spi.cloud.storage.AppCredentialStore;
import org.datatransferproject.types.transfer.auth.AppCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class HeliumAuthServiceExtension implements AuthServiceExtension {
  private static final Logger logger = LoggerFactory.getLogger(HeliumAuthServiceExtension.class);

  private static final ImmutableList<String> SUPPORTED_DATA_TYPES = ImmutableList.of("FILES");

  private boolean initialized = false;

  private HeliumAuthDataGenerator authDataGenerator;

  public HeliumAuthServiceExtension() {
    super();
    System.out.println("HeliumAuthServiceExtension()");
  }

  @Override
  public String getServiceId() {
    System.out.println("HeliumAuthServiceExtension.getServiceId");
    return "Helium";
  }

  @Override
  public AuthDataGenerator getAuthDataGenerator(
      String transferDataType, AuthServiceProviderRegistry.AuthMode mode) {
    System.out.println("HeliumAuthServiceExtension.getAuthDataGenerator");
    return authDataGenerator;
  }

  @Override
  public List<String> getImportTypes() {
    System.out.println("HeliumAuthServiceExtension.getImportTypes");
    return SUPPORTED_DATA_TYPES;
  }

  @Override
  public List<String> getExportTypes() {
    System.out.println("HeliumAuthServiceExtension.getExportTypes");
    return SUPPORTED_DATA_TYPES;
  }

  @Override
  public void initialize(ExtensionContext context) {
    System.out.println("HeliumAuthServiceExtension.initialize");
    if (initialized) return;

    AppCredentials appCredentials;
    try {
      appCredentials =
          context
              .getService(AppCredentialStore.class)
              .getAppCredentials("HELIUM_KEY", "HELIUM_SECRET");
    } catch (IOException e) {
      logger.warn(
          "Problem getting AppCredentials: {}. Did you set HELIUM_KEY and HELIUM_SECRET?", e);
      return;
    }

    authDataGenerator =
        new HeliumAuthDataGenerator(appCredentials, context.getService(HttpTransport.class));

    initialized = true;
  }
}
