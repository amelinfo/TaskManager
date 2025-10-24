# Configuración de base de datos automática
$dbPassword = "admin123"

Write-Host "🧹 Limpiando configuración anterior..." -ForegroundColor Yellow
docker-compose down
docker volume prune -f

Write-Host "📝 Generando archivos de configuración..." -ForegroundColor Green

# Crear docker-compose.yml
@"
version: '3.8'

services:
  postgres:
    image: postgres:15
    container_name: task-postgres
    environment:
      - POSTGRES_DB=taskdb
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=$dbPassword
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
"@ | Out-File -FilePath "docker-compose.yml" -Encoding UTF8

# Crear application.properties
@"
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=postgres
spring.datasource.password=$dbPassword
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

# SQL initialization
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true

# Server
server.port=8080
server.error.include-message=always
"@ | Out-File -FilePath "src/main/resources/application.properties" -Encoding UTF8

Write-Host "🚀 Iniciando PostgreSQL..." -ForegroundColor Green
docker-compose up -d

Write-Host "⏳ Esperando inicialización..." -ForegroundColor Yellow
Start-Sleep -Seconds 10

Write-Host "🔍 Probando conexión..." -ForegroundColor Cyan
docker exec -i task-postgres psql -U postgres -d taskdb -c "SELECT version();"

Write-Host "✅ Configuración completada!" -ForegroundColor Green
Write-Host "📋 Contraseña configurada: $dbPassword" -ForegroundColor White
Write-Host "🎯 Ejecuta: ./mvnw spring-boot:run" -ForegroundColor Cyan