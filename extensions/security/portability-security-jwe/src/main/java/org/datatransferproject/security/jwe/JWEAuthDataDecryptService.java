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
package org.datatransferproject.security.jwe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.RSADecrypter;
import org.datatransferproject.spi.transfer.security.AuthDataDecryptService;
import org.datatransferproject.types.transfer.auth.AuthDataPair;

import java.io.IOException;
import java.security.PrivateKey;
import java.text.ParseException;

/** */
public class JWEAuthDataDecryptService implements AuthDataDecryptService {
  private final ObjectMapper objectMapper;

  public JWEAuthDataDecryptService(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public boolean canHandle(String scheme) {
    return "jwe".equals(scheme);
  }

  @Override
  public AuthDataPair decrypt(String encrypted, PrivateKey privateKey) {
    try {
      RSADecrypter decrypter = new RSADecrypter(privateKey);
      JWEObject object = JWEObject.parse(encrypted);
      object.decrypt(decrypter);
      return objectMapper.readValue(object.getPayload().toString(), AuthDataPair.class);
    } catch (IOException | ParseException | JOSEException e) {
      throw new SecurityException("Error decrypting auth tokens", e);
    }
  }
}
