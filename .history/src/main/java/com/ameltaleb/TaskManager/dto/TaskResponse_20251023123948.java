public record TaskResponse(
        UUID id, 
        String title, 
        String description, 
        Task
        Status status, 
        LocalDateTime createdAt) {
}