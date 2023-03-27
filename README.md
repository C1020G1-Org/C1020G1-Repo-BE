# C1020G1-Repo-BE

```shell
cd app/src/main/resources/

cp application.properties.example application.properties
# edit application.properties as needed"

cd -
docker build -t social_network_23.03.01.1600 -f app.dockerfile .

docker compose up db -d
# wait for db fully init

docker compose up -d 
```
