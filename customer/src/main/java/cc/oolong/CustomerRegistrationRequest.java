package cc.oolong;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
