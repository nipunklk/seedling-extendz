
APP_VERSION="0.0.1"
DOCKER_API_SERVER="seedling-spring-boot"
DOCKER_NETWORK="seedling_seedling-network"

echo 'Building Spring boot: live';
docker build -f Dockerfile -t $DOCKER_API_SERVER:$APP_VERSION .

echo 'Removing old containers';
docker container rm -f $DOCKER_API_SERVER || true

docker container run --name $DOCKER_API_SERVER -p 8080:8080 -d --network=$DOCKER_NETWORK $DOCKER_API_SERVER:$APP_VERSION 

# cd ./seedling-angular
rm src.tar.xz