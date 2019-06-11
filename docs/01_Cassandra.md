# Set up cassandra locally

## Pull the Docker Images
### Docker Login
```bash
$> docker login
```

### Pull the DataStax Image
```bash
$> docker pull datastax/dse-server:latest
```

### Pull DataStax Studio Image (Notebook)
```bash
$> docker pull datastax/dse-studio:latest
```

### Start the DataStax Server Container
1. `-name` parameter provides a human readable reference for the container operation.
1. `-g` flag starts a Node with Graph Model enabled
1. `-s` flag starts a Node with Search Engine enabled
1. `-k` flag starts a Node with Spark Analytics enabled
```bash
$> docker run -e DS_LICENSE=accept --memory 4g --name polymesh-dse -d datastax/dse-server -g -s -k
```

### Start the DataStax Server Container locally with Port
```bash
docker run -e DS_LICENSE=accept --memory 4g --name polymesh-dse -p 127.0.0.1:9042:9042 -d datastax/dse-server
```

### Start DataStax Studio Container
1. `-link` parameter provides a way to map a hostname to a container IP address. In this example, 
we map the database container to Studio container by providing its name, "polymesh-dse"
1. `-p` flag maps ports between container and host.
```bash
$> docker run -e DS_LICENSE=accept --link polymesh-dse -p 9091:9091 --memory 1g --name polymesh-studio -d datastax/dse-studio
```

### Connecting Studio
Visit the Studio page by entering http://localhost:9091 in your browser.

**When opening the notebook, you will see a connection exception. This is because the default connection in studio uses localhost. You will need to change localhost to the DataStax Server Container name ‘polymesh-dse’.**

## Cassandra Docker Cheat Sheet
Common commands used when working with Cassandra and Docker
```bash
========= Status =========
#Active containers
$> docker ps
#Container Utilization
$> docker stats
#Container Details
$> docker inspect polymesh-dse
#NodeTool Status
$> docker exec -it polymesh-dse nodetool status

========== Logs ==========
#Server Logs
$> docker logs polymesh-dse
#System Out
$> docker exec -it polymesh-dse cat /var/log/cassandra/system.log
#Studio Logs
$> docker logs polymesh-studio

==== Start/Stop/Remove ====
#Start Container
$> docker start polymesh-dse
#Stop Container
$> docker stop polymesh-dse
#Remove Container
$> docker remove polymesh-dse

======= Additional =======
#Contaier IPAddress
&> docker inspect polymesh-dse | grep IPAddress
#CQL (Requires IPAddress from above)
$> docker exec -it polymesh-dse cqlsh [IPAddress]
#Bash
$> docker exec -it polymesh-dse bash
```