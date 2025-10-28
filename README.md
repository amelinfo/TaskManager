# TaskManger

TaskManger is a simple task management application to help you create, track, and complete tasks.

## Features

- Create, edit, and delete tasks
- Mark tasks as completed or pending
- Organize tasks by priority or due date
- Simple and clean user interface

## Installation

1. Clone the repository:
   ```
   git clone https://github.com/amelinfo/TaskManger.git
   ```
2. Change into the project directory:
   ```
   cd TaskManger
   ```
3. Set up database with Docker:
   ```
   # Start PostgreSQL
   docker-compose up -d
   ```
4. Run the application:
   ```
   # With Maven Wrapper
   ./mvnw spring-boot:run

   # Or with regular Maven
   mvn spring-boot:run
   ```

##  API Endpoints

1. Get all tasks:

   ```
   GET /api/tasks
   ```
2. Get task by ID :
   ```
   GET /api/tasks/{id}
   ```
3. Create a new Task :

   ```
   POST /api/tasks
   Content-Type: application/json
   {
    "id": "12345678-1234-1234-1234-123456789012",
    "title": "New task",
    "description": "Task description",
    "status": "PENDING"
   }
   ```
4. Update an existing Task :

   ```
   PUT /api/tasks/{id}
   Content-Type: application/json

   {
    "id": "5508e177-2d92-49e3-bd7b-7106d6447e38",
    "title": "Updated title",
    "description": "Updated description",
    "status": "COMPLETED"
   }
   ```

5. Delete a task :
   ```
   DELETE /api/tasks/{id}
   ```




The application will be available at: http://localhost:8080

## Contributing

Contributions are welcome. Please fork the repository, create a branch for your feature or fix, and open a pull request describing your changes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
