#!/bin/bash

# BioDashboard 启动脚本
# 适用于 Linux/Mac 系统

APP_NAME=biodashboard
JAR_NAME=biodashboard-1.0.0.jar
LOG_FILE=logs/biodashboard.log
PID_FILE=logs/biodashboard.pid

# 创建日志目录
mkdir -p logs

# 检查是否已运行
check_running() {
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        if ps -p "$PID" > /dev/null 2>&1; then
            echo "错误: $APP_NAME 已经在运行 (PID: $PID)"
            exit 1
        fi
    fi
}

# 启动应用
start() {
    check_running
    
    echo "正在启动 $APP_NAME ..."
    
    # 使用 nohup 后台运行
    nohup java -jar $JAR_NAME \
        --spring.profiles.active=prod \
        > $LOG_FILE 2>&1 &
    
    # 保存 PID
    echo $! > $PID_FILE
    
    echo "$APP_NAME 启动成功!"
    echo "PID: $(cat $PID_FILE)"
    echo "日志文件: $LOG_FILE"
    echo "访问地址: http://localhost:8088/biodashboard"
}

# 停止应用
stop() {
    if [ ! -f "$PID_FILE" ]; then
        echo "错误: $APP_NAME 没有在运行"
        exit 1
    fi
    
    PID=$(cat "$PID_FILE")
    echo "正在停止 $APP_NAME (PID: $PID) ..."
    
    # 尝试优雅关闭
    kill "$PID"
    
    # 等待进程结束
    for i in {1..30}; do
        if ! ps -p "$PID" > /dev/null 2>&1; then
            rm -f "$PID_FILE"
            echo "$APP_NAME 已停止"
            exit 0
        fi
        sleep 1
    done
    
    # 强制关闭
    echo "强制停止进程..."
    kill -9 "$PID"
    rm -f "$PID_FILE"
    echo "$APP_NAME 已强制停止"
}

# 查看状态
status() {
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        if ps -p "$PID" > /dev/null 2>&1; then
            echo "$APP_NAME 正在运行 (PID: $PID)"
            echo "访问地址: http://localhost:8088/biodashboard"
        else
            echo "$APP_NAME 没有在运行，但存在 PID 文件"
        fi
    else
        echo "$APP_NAME 没有在运行"
    fi
}

# 查看日志
tail_log() {
    if [ -f "$LOG_FILE" ]; then
        tail -f $LOG_FILE
    else
        echo "日志文件不存在: $LOG_FILE"
    fi
}

# 使用说明
usage() {
    echo "用法: $0 {start|stop|restart|status|log}"
    echo ""
    echo "命令:"
    echo "  start    - 启动应用"
    echo "  stop     - 停止应用"
    echo "  restart  - 重启应用"
    echo "  status   - 查看运行状态"
    echo "  log      - 查看实时日志"
    echo ""
}

# 主逻辑
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        stop
        sleep 2
        start
        ;;
    status)
        status
        ;;
    log)
        tail_log
        ;;
    *)
        usage
        exit 1
        ;;
esac
