#!/bin/sh
gnome-terminal  --tab -- /bin/bash -c "docker-compose up;exec /bin/bash;"
# gnome-terminal  --tab -- /bin/bash -c "cd gadgetshop-spring;./mvnw spring-boot:run -Dspring-boot.run.profiles=dev;exec /bin/bash;"
gnome-terminal  --tab -- /bin/bash -c "cd seedling-angular;ng s ;exec /bin/bash;"
