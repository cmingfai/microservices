package cc.oolong.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
