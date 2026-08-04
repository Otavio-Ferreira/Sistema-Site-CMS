# PBI: Criação do diagrama ER (Entidade Relacional) com Mermaid

## História do Usuário
Como membro da equipe de desenvolvimento, quero elaborar o diagrama Entidade-Relacionamento (ER) do sistema utilizando a sintaxe Mermaid, para documentar a estrutura do banco de dados e garantir o correto mapeamento da arquitetura multi-tenant antes de iniciarmos a implementação do código e das *queries*.

## Casos de uso 

### Caso de uso 1: Consulta estrutural durante o desenvolvimento
Os desenvolvedores consultarão o diagrama renderizado para guiar a criação de *migrations*, entidades, tabelas e relacionamentos, garantindo a integridade referencial ao modelar os dados do painel do cliente.

### Caso de uso 2: Validação da segurança e isolamento de dados
O time utilizará o diagrama visual para auditar a arquitetura, assegurando que todas as informações (portfólios, mídias, feedbacks) estejam rigorosamente amarradas e restritas ao profissional correto, prevenindo vazamentos de dados entre contas.

## Regras de negócio

* **Isolamento Multi-Tenant (RNF01):** O diagrama deve refletir obrigatoriamente a arquitetura de isolamento de instâncias. Todas as tabelas que armazenam dados sensíveis ou de customização de página devem possuir uma chave estrangeira (ex: `tenant_id`) relacionando diretamente a informação ao usuário inquilino isolado.
* **Gestão Comercial (RF11):** A modelagem deve prever o vínculo entre as contas dos profissionais (tenants) e o módulo de gerenciamento de assinaturas e planos mensais recorrentes, permitindo o controle de status financeiro.

## Critérios de Aceite

* O código Mermaid (`erDiagram`) deve renderizar visualmente sem apresentar erros de sintaxe no repositório de documentação.
* O diagrama deve sinalizar claramente as chaves primárias (PK) e chaves estrangeiras (FK) de cada entidade mapeada.
* Todas as cardinalidades de relacionamento (1:1, 1:N, N:M) devem estar declaradas explicitamente e coerentes com as regras multi-tenant do sistema.
* O diagrama deve incluir a tipagem de dados (ex: `uuid`, `varchar`, `boolean`, `timestamp`).

## Definição de Pronto (DoD)

* O script em formato Mermaid foi integrado com sucesso ao arquivo oficial de documentação do projeto.
* O modelo proposto reflete todos os requisitos funcionais de persistência de dados.
* O diagrama foi revisado e aprovado pelos desenvolvedores da equipe técnica.

## Er

```mermaid
---
config:
  layout: elk
---
erDiagram
    TENANT ||--o{ USUÁRIO : inclui
    TENANT ||--o{ ASSINATURA : possui
    PLANO ||--o{ ASSINATURA : oferecida
    TENANT ||--o{ PAGINA : cria
    PAGINA ||--|| BIOGRAFIA : contém
    PAGINA ||--o{ CONTATO : inclui
    PAGINA ||--o{ ACCORDION : possui
    PAGINA ||--o{ BOTAO_CTA : exibe
    PAGINA ||--o{ CARD_CTA : expõe
    PAGINA ||--o{ FEEDBACK : mostra
    PAGINA ||--o{ ARTIGO_RICHTEXT : publica
    PAGINA ||--o{ CARROSSEL : contém
    CARROSSEL ||--o{ IMAGEM_CARROSSEL : composta

    USUÁRIO {
        int id PK
        string nome_completo
        string email
        string senha_hash
        boolean is_admin
    }

    TENANT {
        int id PK
        string nome
    }

    PLANO {
        int id PK
        string nome_plano
        decimal valor_mensal
        string descricao
    }

    ASSINATURA {
        int id PK
        int tenant_id FK
        int plano_id FK
        date data_inicio
        string status_pagamento
    }

    PAGINA {
        int id PK
        int tenant_id FK
        string url_publica
        string titulo_pagina
        date data_criacao
    }

    BIOGRAFIA {
        int id PK
        int pagina_id FK
        text conteudo_texto
    }

    CONTATO {
        int id PK
        int pagina_id FK
        string tipo_contato
        string valor_contato
    }

    ACCORDION {
        int id PK
        int pagina_id FK
        string pergunta_titulo
        text resposta_conteudo
    }

    BOTAO_CTA {
        int id PK
        int pagina_id FK
        string texto_exibicao
        string link_destino
    }

    CARD_CTA {
        int id PK
        int pagina_id FK
        string url_imagem
        string texto_destaque
        string link_destino
    }

    FEEDBACK {
        int id PK
        int pagina_id FK
        string nome_cliente
        text texto_avaliacao
        date data_feedback
    }

    ARTIGO_RICHTEXT {
        int id PK
        int pagina_id FK
        string titulo
        text conteudo_html
        date data_publicacao
    }

    CARROSSEL {
        int id PK
        int pagina_id FK
    }

    IMAGEM_CARROSSEL {
        int id PK
        int carrossel_id FK
        int ordem_exibicao
        string url_midia
        string titulo
        text descricao
        string link_externo
    }
```