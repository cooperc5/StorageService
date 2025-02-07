# Stop any existing containers to avoid conflicts
Write-Host "Stopping any running containers..." -ForegroundColor Yellow
docker-compose down

# Start the database container separately to ensure it is ready
Write-Host "Starting PostgreSQL database..." -ForegroundColor Green
docker-compose up -d db  # Use the correct service name for PostgreSQL

# Wait for the database to be ready
Write-Host "Waiting for PostgreSQL to initialize..." -ForegroundColor Yellow
Start-Sleep -Seconds 10  # Adjust as needed

# Ensure database 'competitoreventresult' exists
Write-Host "Ensuring database 'competitoreventresult' exists..." -ForegroundColor Cyan
docker exec -i db psql -U postgres -tc "SELECT 1 FROM pg_database WHERE datname = 'competitoreventresult'" | FindStr 1
if ($LASTEXITCODE -ne 0) {
    docker exec -i db psql -U postgres -c "CREATE DATABASE competitoreventresult;"
    Write-Host "Database 'competitoreventresult' created." -ForegroundColor Green
} else {
    Write-Host "Database already exists, skipping creation." -ForegroundColor Cyan
}

# Start the StorageService
Write-Host "Starting StorageService..." -ForegroundColor Green
docker-compose up -d storage-service  # Corrected service name

Write-Host "Service started successfully!" -ForegroundColor Green
