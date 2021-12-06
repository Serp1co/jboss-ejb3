run jboss and postgre in the same network

docker run -d --network app-bridge -p 8080:8080 -p 9990:9990 -it jboss/wildfly /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0
docker run -d --network app-bridge --env POSTGRES_PASSWORD=mysecretpassword --publish 5432:5432 postgres
