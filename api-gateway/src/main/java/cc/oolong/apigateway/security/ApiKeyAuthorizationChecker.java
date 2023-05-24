package cc.oolong.apigateway.security;

public interface ApiKeyAuthorizationChecker {
    boolean isAuthorized(String apiKey, String application);
}
