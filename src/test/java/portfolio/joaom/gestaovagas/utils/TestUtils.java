package portfolio.joaom.gestaovagas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class TestUtils {

    public static String objectToJSON(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID companyId) {
        Algorithm algorithm = Algorithm.HMAC256("meuPortfolio");
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        return JWT.create()
                .withIssuer("portfolio")
                .withSubject(companyId.toString())
                .withClaim("roles", List.of("COMPANY"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);
    }
}
