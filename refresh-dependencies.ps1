[CmdletBinding()]
param(
    [switch]$FullStackTrace,
    [switch]$FullDebug
)

Write-Host "Starting dependency refresh..."

# Put each Maven command item in an array
$mvnCmd = @("clean", "install")

$mvnOptions = @()
if ($FullStackTrace) {
    $mvnOptions += "-e"
}
if ($FullDebug) {
    $mvnOptions += "-X"
}

Write-Host "Running: mvn $($mvnCmd + $mvnOptions -join ' ')"

# Now we can call 'mvn' passing the array for arguments
mvn $mvnCmd $mvnOptions

Write-Host "Dependency refresh complete."
