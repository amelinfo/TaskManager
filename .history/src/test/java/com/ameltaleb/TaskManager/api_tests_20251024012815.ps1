# Base URL
$BaseUrl = "http://localhost:8080/api/tasks"

# Colores para output
$Green = "✅"
$Red = "❌"

Write-Host "1. Crear tarea exitosa..." -ForegroundColor Cyan

$body = @{
    id = "12345678-1234-1234-1234-123456789012"
    title = "Nueva tarea desde PowerShell"
    description = "Esta es una tarea creada para testing con PowerShell"
    status = "PENDING"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri $BaseUrl -Method Post -Body $body -ContentType "application/json"
    Write-Host "$Green PASS - Tarea creada: $($response.title)" -ForegroundColor Green
} catch {
    Write-Host "$Red FAIL - Status: $($_.Exception.Response.StatusCode.value__)" -ForegroundColor Red
}

# ========> result  ❌ FAIL - Status: 500

#====================