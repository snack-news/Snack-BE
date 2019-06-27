#!/bin/bash
HOSTNAME=SNACK-BE
REPOSITORY=/home/ec2-user
cd $REPOSITORY/Snack-BE/


#echo "> 1. $HOSTNAME 소스코드를 동기화합니다. 🚥"
#git pull


echo "> 2. $HOSTNAME Build 시작합니다. 🚀"
./gradlew clean build


echo "> 3. $HOSTNAME JAR 파일 복사합니다. 🚁"
cp ./build/libs/*.jar $REPOSITORY/


echo "> 4. $HOSTNAME 프로세스 ID 확인 🧐"
CURRENT_PID=$(pgrep -f news


echo "$HOSTNAME 프로세스 ID : $CURRENT_PID"
if [ -z $CURRENT_PID ]; then
    echo "> 5. 현재 구동중인 $HOSTNAME이 없으므로 종료하지 않습니다. 🎃"
else
    echo "> 5. $HOSTNAME 프로세스 종료 중입니다. : $CURRENT_PID 👹"
    kill -9 $CURRENT_PID
    sleep 5
fi


echo "> 6. 새로운 $HOSTNAME 배포합니다. 💍"
JAR_NAME=$(ls $REPOSITORY/ |grep 'news' | tail -n 1)


echo "> 배포 JAR 이름: $JAR_NAME "🦊
nohup java -jar $REPOSITORY/$JAR_NAME &