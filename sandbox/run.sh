# Criar as docker networks
docker network create adm_videos_services

# Criando pastas com permissoes
mkdir -m 777 .docker
mkdir -m 777 .docker/keycloak

docker compose -f services/docker-compose.yml up -d

echo "Inicializando os containers ..."
sleep 20