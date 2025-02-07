# Restarting the services
Write-Host "Restarting StorageService..." -ForegroundColor Yellow
docker-compose down

# Ensure database is running before restarting the service
Write-Host "Starting PostgreSQL database..." -ForegroundColor Green
docker-compose up -d db  # Use correct service name
Start-Sleep -Seconds 10  # Wait for DB initialization

# Restart the storage service
Write-Host "Restarting StorageService..." -ForegroundColor Green
docker-compose up -d storage-service  # Use correct service name

Write-Host "StorageService restarted successfully!" -ForegroundColor Green