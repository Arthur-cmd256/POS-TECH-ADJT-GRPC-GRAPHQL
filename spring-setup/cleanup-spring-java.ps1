$ErrorActionPreference = 'Stop'

Write-Host "Procurando processos Java do projeto Spring..." -ForegroundColor Yellow

$projectMarker = 'POS-TECH-ADJT-GRPC-GRAPHQL'
$javaProcs = Get-CimInstance Win32_Process |
    Where-Object {
        $_.Name -eq 'java' -and
        $_.CommandLine -match $projectMarker
    }

if (-not $javaProcs) {
    Write-Host "Nenhum processo Java do projeto encontrado." -ForegroundColor Green
    $portCheck = Get-NetTCPConnection -LocalPort 6565,7575,8081,8082,9090 -ErrorAction SilentlyContinue
    if ($portCheck) {
        Write-Host "Algumas portas ainda estao em uso:" -ForegroundColor Yellow
        $portCheck | Format-Table -AutoSize
    }
    exit 0
}

foreach ($proc in $javaProcs) {
    Write-Host "Encerrando PID $($proc.ProcessId) -> $($proc.CommandLine)" -ForegroundColor Red
    Stop-Process -Id $proc.ProcessId -Force -ErrorAction SilentlyContinue
}

Start-Sleep -Seconds 2

$remaining = Get-CimInstance Win32_Process |
    Where-Object {
        $_.Name -eq 'java' -and
        $_.CommandLine -match $projectMarker
    }

if ($remaining) {
    Write-Host "Alguns processos ainda permanecem ativos:" -ForegroundColor Yellow
    $remaining | Select-Object ProcessId, CommandLine | Format-Table -AutoSize
    exit 1
}

Write-Host "Processos Java do projeto encerrados com sucesso." -ForegroundColor Green

$ports = Get-NetTCPConnection -LocalPort 6565,7575,8081,8082,9090 -ErrorAction SilentlyContinue
if ($ports) {
    Write-Host "Portas ainda em uso:" -ForegroundColor Yellow
    $ports | Format-Table -AutoSize
}
else {
    Write-Host "Todas as portas esperadas estao livres." -ForegroundColor Green
}
