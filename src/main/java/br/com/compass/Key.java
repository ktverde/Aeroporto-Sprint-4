package br.com.compass;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.KeyPair;

public class Key
{
    public static final KeyPair KEY = Keys.keyPairFor(SignatureAlgorithm.RS512);
}
