#!/bin/bash
HOST_NAME=SNACK-BE
REPOSITORY=/home/ec2-user/app/travis

echo "> $HOST_NAME 프로세스 ID 확인 🧐"
CURRENT_PID=$(pgrep -f news)

echo "$HOST_NAME 프로세스 ID : $CURRENT_PID"

if [ -z ${CURRENT_PID} ]; then
    echo "> 현재 구동중인 $HOST_NAME 이 없으므로 종료하지 않습니다. 🎃"
else
    echo "> $HOST_NAME 프로세스 종료 중입니다. : $CURRENT_PID 👹"
    kill -15 ${CURRENT_PID}
    sleep 5
fi

echo "> $HOST_NAME JAR 파일 복사합니다. 🚁"
cp ${REPOSITORY}/build/build/libs/*.jar ${REPOSITORY}/jar

echo "> 새로운 $HOST_NAME 배포합니다. 💍"
JAR_NAME=$(ls -tr ${REPOSITORY}/jar | grep 'news' | tail -n 1)

echo "> 배포 JAR 이름: $JAR_NAME 🦊"
nohup java -jar ${REPOSITORY}/jar/${JAR_NAME} &
