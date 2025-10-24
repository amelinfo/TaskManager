public record TaskResponse(
        UUID id, 
        String title, 
        String description, 
        Status status, 
        LocalDateTime createdAt) {
}