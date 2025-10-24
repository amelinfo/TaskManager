Write-Host "🔧 Iniciando PostgreSQL..." -ForegroundColor Green
docker-compose down
docker volume prune -f
docker-compose up -d

Write-Host "⏳ Esperando a que PostgreSQL esté listo..." -ForegroundColor Yellow
Start-Sleep -Seconds 15

Write-Host "✅ Verificando conexión..." -ForegroundColor Green
docker exec -it task-postgres psql -U postgres -d taskdb -c "SELECT version();"

if ($LASTEXITCODE -eq 0) {
    Write-Host "🎉 PostgreSQL está listo!" -ForegroundColor Green
    Write-Host "🚀 Ahora ejecuta: ./mvnw spring-boot:run" -ForegroundColor Cyan
} else {
    Write-Host "❌ Error: PostgreSQL no está respondiendo" -ForegroundColor Red
}