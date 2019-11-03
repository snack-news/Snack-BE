#!/bin/bash
echo "> 현재 구동중인 Port 확인"
CURRENT_PROFILE=$(curl -s http://localhost/meta/profile)
BASE_PORT=8081

# 쉬고 있는 set 찾기: set1이 사용중이면 set2가 쉬고 있고, 반대면 set1이 쉬고 있음
if [[ ${CURRENT_PROFILE} == set1 ]]
then
  IDLE_PORT=8082
elif [[ ${CURRENT_PROFILE} == set2 ]]
then
  IDLE_PORT=8081
else
  echo "> 일치하는 Profile이 없습니다."
  IDLE_PORT=${BASE_PORT}
fi

# 포트 바인딩 변경
echo "> 전환할 Port: $IDLE_PORT"
echo "> Port 전환"
echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

# nginx 리부트
echo "> Nginx Reload"
sudo service nginx reload

echo "> 변경된 profile : $(curl -s http://localhost/meta/profile)"