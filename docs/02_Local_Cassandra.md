## Setup local cassandra server instance

### Reference
https://hub.docker.com/r/bitnami/cassandra/

### Bring up single instance
```bash
$ docker run -v ${PWD}/cassandra_data:/bitnami -p 127.0.0.1:9042:9042 -p 127.0.0.1:9160:9160 bitnami/cassandra:latest
```

### Docker Compose
```bash
$ curl -sSL https://raw.githubusercontent.com/bitnami/bitnami-docker-cassandra/master/docker-compose.yml > docker-compose.yml
$ docker-compose up -d
```

### Get this image
```bash
$ docker pull bitnami/cassandra:latest
```

### Launch Cassandra client instance

```bash
cqlsh --username cassandra --password cassandra 127.0.0.1
 
```