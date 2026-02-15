@echo off
chcp 65001 >nul

:: BioDashboard 启动脚本
:: 适用于 Windows 系统

set APP_NAME=biodashboard
set JAR_NAME=biodashboard-1.0.0.jar
set LOG_FILE=logs\biodashboard.log
set PID_FILE=logs\biodashboard.pid

:: 创建日志目录
if not exist logs mkdir logs

:: 使用说明
goto :usage

:usage
    echo 用法: %0 {start^|stop^|restart^|status}
    echo.
    echo 命令:
    echo   start    - 启动应用
    echo   stop     - 停止应用
    echo   restart  - 重启应用
    echo   status   - 查看运行状态
    echo.
    goto :eof

:start
    :: 检查是否已运行
    if exist %PID_FILE% (
        for /f "tokens=*" %%a in (%PID_FILE%) do (
            tasklist /fi "pid eq %%a" 2>nul | find "%%a" >nul
            if !errorlevel! == 0 (
                echo 错误: %APP_NAME% 已经在运行 (PID: %%a)
                goto :eof
            )
        )
    )
    
    echo 正在启动 %APP_NAME% ...
    
    :: 启动应用
    start /b javaw -jar %JAR_NAME% --spring.profiles.active=prod > %LOG_FILE% 2>&1
    
    :: 保存 PID
    echo %! > %PID_FILE%
    
    echo %APP_NAME% 启动成功!
    echo 日志文件: %LOG_FILE%
    echo 访问地址: http://localhost:8088/biodashboard
    goto :eof

:stop
    if not exist %PID_FILE% (
        echo 错误: %APP_NAME% 没有在运行
        goto :eof
    )
    
    for /f "tokens=*" %%a in (%PID_FILE%) do (
        echo 正在停止 %APP_NAME% (PID: %%a) ...
        taskkill /pid %%a /f >nul 2>&1
        if !errorlevel! == 0 (
            del %PID_FILE%
            echo %APP_NAME% 已停止
        ) else (
            echo 停止失败，进程可能已结束
            del %PID_FILE%
        )
    )
    goto :eof

:status
    if exist %PID_FILE% (
        for /f "tokens=*" %%a in (%PID_FILE%) do (
            tasklist /fi "pid eq %%a" 2>nul | find "%%a" >nul
            if !errorlevel! == 0 (
                echo %APP_NAME% 正在运行 (PID: %%a)
                echo 访问地址: http://localhost:8088/biodashboard
            ) else (
                echo %APP_NAME% 没有在运行，但存在 PID 文件
            )
        )
    ) else (
        echo %APP_NAME% 没有在运行
    )
    goto :eof

:: 主逻辑
if "%~1"=="" goto :usage
if "%~1"=="start" goto :start
if "%~1"=="stop" goto :stop
if "%~1"=="restart" (
    call :stop
    timeout /t 2 /nobreak >nul
    goto :start
)
if "%~1"=="status" goto :status

goto :usage
