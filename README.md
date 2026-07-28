# Spring Boot + MySQL API (Dockerized)

Este é um projeto base utilizando **Spring Boot (Java 21)** e **MySQL 8**, totalmente conteinerizado com Docker. Ninguém precisa instalar Java, Maven ou MySQL localmente na máquina para rodar ou desenvolver; o Docker cuida de todo o ciclo de build e execução.

---

## Pré-requisitos

Você só precisa ter instalado em sua máquina:
* **Docker**
* **Docker Compose**

---

## Como Rodar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/Otavio-Ferreira/Sistema-Site-CMS.git](https://github.com/Otavio-Ferreira/Sistema-Site-CMS.git)
   ```

2. **Acesse a pasta do projeto:**
   ```bash
   cd Sistema-Site-CMS
   ```

3. **Suba os contêineres:**
   Execute o comando abaixo na raiz do projeto para baixar as imagens, compilar o código Java e iniciar os serviços:
   ```bash
   docker compose up --build
   ```
   *(Remova o `-d` se quiser ver os logs rodando direto no terminal, ou adicione `-d` para rodar em segundo plano).*

---

## Portas e Acessos

Assim que a inicialização for concluída, os serviços estarão disponíveis em:

* **API (Spring Boot):** [http://localhost:8080](http://localhost:8080)
* **Banco de Dados (MySQL Local):** `localhost:3306`

### Acesso Direto ao Banco via Terminal
Se precisar entrar no terminal do MySQL direto pelo contêiner Docker, utilize o comando:
```bash
docker exec -it springboot_mysql mysql -u usuario -psenha meubanco
```

### Credenciais para Conexão Externa (DBeaver / Workbench)
* **Host:** `localhost`
* **Porta:** `3306`
* **Database:** `meubanco`
* **Usuário:** `usuario`
* **Senha:** `senha`