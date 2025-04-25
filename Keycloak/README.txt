docker run -p 8180:8180 \
           --add-host=host.docker.internal:host-gateway \
           -e KEYCLOAK_SCRIPT_UPLOAD=true \
           -e KC_BOOTSTRAP_ADMIN_USERNAME=admin \
           -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin \
           -e DB_VENDOR=postgres \
           -e DB_ADDR=172.17.0.1 \
           -e DB_DATABASE=keycloak \
           -e DB_USER=keycloakclient \
           -e DB_PASSWORD=keyCloakClient1234 \
           -e KEYCLOAK_ADMIN=admin \
           -e KEYCLOAK_ADMIN_PASSWORD=admin \
           --name keycloakContainer \
           -it quay.io/keycloak/keycloak:26.1.0 start-dev

docker rm $(docker ps -aq)

//keycloak config in docker container laden
docker cp /home/silva/Documents/WII-Masterclass/Keycloak/keycloak.conf keycloakContainer:/opt/keycloak/conf/keycloak.conf

docker restart keycloakContainer

//1 min Warten (Docker container braucht etwas) ganz unten in den logs sollte dann etwas mit 'jdbc-postgresql' stehen, dann hats funktioniert :)
docker logs keycloakContainer 


//schauen, was für eine keycloak confing im docker läuft
docker cp keycloakContainer:/opt/keycloak/conf/keycloak.conf /home/silva/Documents/WII-Masterclass