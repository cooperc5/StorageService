#powershell
#run when storageservice is running and then use restart-service.ps1 to finish refreshing db schema after any schema changes
Write-Host "Dropping 'competitoreventresult' database..." -ForegroundColor Yellow
docker exec -i storage_db psql -U postgres -c "DROP DATABASE IF EXISTS competitoreventresult;"
Write-Host "Database dropped successfully."