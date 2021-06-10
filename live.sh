#!/bin/bash
#https://stackoverflow.com/questions/22530886/ssh-copy-id-no-identities-found-error

APP_VERSION="0.0.1"
DOCKER_API_SERVER="seedling-spring-boot"
DOCKER_NETWORK="seedling_seedling-network"

echo 'Connecting Live docker demon';
export DOCKER_HOST=ssh://root@178.128.99.175

echo 'Building Angular: Live';
cd ./seedling-angular
npm run prod
# tar -cJf dist.tar.xz dist
cd ../

rm -rf seedling-spring/src/main/resources/static
cp -r seedling-angular/dist/seeding/browser/ seedling-spring/src/main/resources/static

cd ./seedling-spring
tar -cJf src.tar.xz src

echo 'Building Spring boot: live';
docker build -f Dockerfile -t $DOCKER_API_SERVER:$APP_VERSION .

echo 'Removing old containers';
docker container rm -f $DOCKER_API_SERVER || true

docker container run --name $DOCKER_API_SERVER -p 8080:8080 -d --network=$DOCKER_NETWORK $DOCKER_API_SERVER:$APP_VERSION 

# cd ./seedling-angular
rm src.tar.xz