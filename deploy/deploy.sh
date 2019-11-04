#!/bin/bash
HOST_NAME=SNACK-BE
BASE_PATH=/home/ec2-user/snack-be
BUILD_PATH=$(ls ${BASE_PATH}/build/build/libs/*.jar)
DEPLOY_SCRIPT_PATH=$BASE_PATH/build/deploy

echo "> $HOST_NAME : 배포 시작"

JAR_NAME=$(basename ${BUILD_PATH})
echo "> build 파일명: $JAR_NAME"

# COPY APPLICATION JAR
echo "> build 파일 복사"
DEPLOY_PATH=${BASE_PATH}/jar/
cp ${BUILD_PATH} ${DEPLOY_PATH}

# NGINX CHECK AND RESTART
NGINX_CHECK=`ps -ef | grep -v "grep" | grep "nginx" | wc -l`
if [[ $NGINX_CHECK == "0"  ]];
then
  echo "> Nginx 확인할 수 없음. 재실행 시작"
  sudo service nginx start
else
  echo "> Nginx 실행 확인"
fi
  echo "> 완료"

# CHECK CURRENT PROFILE
CURRENT_PROFILE=$(curl -s http://localhost/meta/profile)
echo -n "> 현재 구동중인 Set 확인 : "

# IDLE PROFILE
if [[ $CURRENT_PROFILE == set1 ]]
then
  echo "$CURRENT_PROFILE"
  IDLE_PROFILE=set2
  IDLE_PORT=8082
elif [[ $CURRENT_PROFILE == set2 ]]
then
  echo "$CURRENT_PROFILE"
  IDLE_PROFILE=set1
  IDLE_PORT=8081
else
  echo "> 일치하는 Profile이 없습니다."
  echo "> set1을 할당합니다."
  echo "" | sudo tee /etc/nginx/conf.d/service-url.inc
  IDLE_PROFILE=set1
  IDLE_PORT=8081
fi

echo "> SNACK-NEWS.jar 교체"
IDLE_APPLICATION=${IDLE_PROFILE}-news.jar
IDLE_APPLICATION_PATH=${DEPLOY_PATH}${IDLE_APPLICATION}

ln -Tfs ${DEPLOY_PATH}${JAR_NAME} ${IDLE_APPLICATION_PATH}

echo "> $IDLE_PROFILE 에서 구동중인 애플리케이션 pid 확인"
IDLE_PID=$(pgrep -f ${IDLE_APPLICATION})

if [ -z $IDLE_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi

echo "> $IDLE_PROFILE 배포"
nohup java -jar -Dspring.profiles.active=${IDLE_PROFILE} ${IDLE_APPLICATION_PATH} &

echo "> $IDLE_PROFILE 10초 후 Health check 시작"
echo "> curl -s http://localhost:$IDLE_PORT/actuator/health"
sleep 10

for retry_count in {1..10}
do
  response=$(curl -s http://localhost:${IDLE_PORT}/actuator/health)
  up_count=$(echo ${response} | grep 'UP' | wc -l)

  if [[ ${up_count} -ge 1 ]]
  then # $up_count >= 1 ("UP" 문자열이 있는지 검증)
      echo "> Health check 성공"
      break
  else
      echo "> Health check의 응답을 알 수 없거나 혹은 status가 UP이 아닙니다."
      echo "> Health check: ${response}"
  fi

  if [[ ${retry_count} -eq 10 ]]
  then
    echo "> Health check 실패. "
    echo "> Nginx에 연결하지 않고 배포를 종료합니다."
    exit 1
  fi

  echo "> Health check 연결 실패. 재시도..."
  sleep 10
done

# SWITCHING NGINX UPSTREAM
echo "> 스위칭"
sleep 10
${DEPLOY_SCRIPT_PATH}/switch.sh

# KILL LEGACY APPLICATION
NEW_PROFILE=$(curl -s http://localhost/meta/profile)
echo "> $NEW_PROFILE 에서 구동중인 애플리케이션 pid 확인"

if [[ $IDLE_PROFILE == $NEW_PROFILE ]]
then
    echo "> 어플리케이션 Switch 성공 "
    echo "> 기존 어플리케이션을 종료합니다."
    LEGACY_PID=$(pgrep -f ${CURRENT_PROFILE})
    if [ -z $LEGACY_PID ]
    then
        echo "> 기존 애플리케이션이 없으므로 종료하지 않습니다."
    else
      echo "> kill -15 $LEGACY_PID"
      kill -15 ${LEGACY_PID}
      sleep 5
    fi
fi

echo "배포 완료 🚀"