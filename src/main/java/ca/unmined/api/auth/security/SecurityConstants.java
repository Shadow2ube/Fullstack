package ca.unmined.api.auth.security;

public final class SecurityConstants {
    public static final String SECRET = "ChickenNuggiesAreC00l";
    public static final Long EXPIRATION_TIME = 864_000_000L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}
