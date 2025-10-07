package com.company.payroll.security.util;

import com.company.payroll.user.dto.JwkKey;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Locator;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import java.util.List;

@Slf4j
public class JwkKeyLocator implements Locator<Key> {
    private final List<JwkKey> cachedKeys;

    public JwkKeyLocator(List<JwkKey> cachedKeys) {
        this.cachedKeys = cachedKeys;
    }

    private String getKidValue(Header header) throws IllegalArgumentException {
        String kid = header.get("kid").toString();

        if (kid == null) {
            throw new IllegalArgumentException("Token is missing the 'kid' header parameter.");
        }

        return kid;
    }

    private PublicKey generateRSAPublicKey(JwkKey jwkKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] modulusBytes = Decoders.BASE64URL.decode(jwkKey.n());
        byte[] exponentBytes = Decoders.BASE64URL.decode(jwkKey.e());

        BigInteger modulus = new BigInteger(1, modulusBytes);
        BigInteger exponent = new BigInteger(1, exponentBytes);

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);

        KeyFactory kf = null;
        kf = KeyFactory.getInstance(jwkKey.kty());
        return kf.generatePublic(keySpec);
    }

    private PublicKey generateECPublicKey(JwkKey jwkKey)
            throws NoSuchAlgorithmException, InvalidParameterSpecException, InvalidKeySpecException {
        String x = jwkKey.x();
        String y = jwkKey.y();
        String crv = jwkKey.crv();
        String jcaCurveName = null;

        switch (crv) {
            case "P-256" -> jcaCurveName = "secp256r1";
            case "P-384" -> jcaCurveName = "secp384r1";
            case "P-521" -> jcaCurveName = "secp521r1";
            default -> throw new IllegalArgumentException("Unsupported EC curve: " + crv);
        }

        BigInteger xCoord = new BigInteger(1, Base64.getUrlDecoder().decode(x));
        BigInteger yCoord = new BigInteger(1, Base64.getUrlDecoder().decode(y));
        ECPoint ecPoint = new ECPoint(xCoord, yCoord);

        AlgorithmParameters parameters = AlgorithmParameters.getInstance(jwkKey.kty());
        parameters.init(new ECGenParameterSpec(jcaCurveName));
        ECParameterSpec ecParameters = parameters.getParameterSpec(ECParameterSpec.class);

        ECPublicKeySpec ecSpec = new ECPublicKeySpec(ecPoint, ecParameters);
        return KeyFactory.getInstance(jwkKey.kty()).generatePublic(ecSpec);
    }

    @Override
    public Key locate(Header header) {
        PublicKey publicKey = null;

        String kid = getKidValue(header);

        try {
            if (cachedKeys != null && !cachedKeys.isEmpty()) {
                for (JwkKey jwkKey : cachedKeys) {
                    if (kid.equalsIgnoreCase(jwkKey.kid())) {
                        if ("RSA".equalsIgnoreCase(jwkKey.kty())) {
                            publicKey = generateRSAPublicKey(jwkKey);
                        } else {
                            publicKey = generateECPublicKey(jwkKey);
                        }
                    }
                }
            } else {
                throw new SignatureException("No matching public key found for kid: " + kid);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Error when accessing the public key: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (InvalidParameterSpecException e) {
            log.error("Error when initializing key algorithm: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            log.error("Error when validating the signature: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        return publicKey;
    }
}
