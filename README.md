# Spring Boot + MySQL API (Dockerized)

Este é um projeto base utilizando **Spring Boot (Java 21)** e **MySQL 8**, totalmente conteinerizado com Docker. Ninguém precisa instalar Java, Maven ou MySQL localmente na máquina para rodar ou desenvolver; o Docker cuida de todo o ciclo de build e execução.

---

## Pré-requisitos

Você só precisa ter instalado em sua máquina:
* **Docker**
* **Docker Compose**

---

## Como Rodar o Projeto

**1. Clone o repositório:**
   ```bash
   git clone [https://github.com/Otavio-Ferreira/Sistema-Site-CMS.git](https://github.com/Otavio-Ferreira/Sistema-Site-CMS.git)
   ```
   
   ```bash
   cd seu-repositorio
   ```

**2. Subir Contêineres**

Execute o comando abaixo na raiz do projeto. Ele vai baixar as imagens necessárias, compilar o código Java dentro do ambiente Docker e iniciar os serviços.

    ```bash
    docker compose up --build
    ```

**3. Portas e Acessos**

Assim que a inicialização for concluída, os serviços estarão disponíveis em:

API (Spring Boot)
    ```bash
    http://localhost:8080
    ```

Banco de Dados (MySQL)
    ```bash
    docker exec -it springboot_mysql mysql -u usuario -psenha meubanco
    ```