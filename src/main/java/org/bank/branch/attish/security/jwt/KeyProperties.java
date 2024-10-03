package org.bank.branch.attish.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa", ignoreUnknownFields = true)
public record KeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey){}
