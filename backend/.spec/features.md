 Documentação do Sistema: Plataforma Multi-Tenant de Portfólios e Páginas de Conversão

Esta documentação consolida a descrição do sistema, bem como seus requisitos funcionais e não funcionais, com base nas entrevistas e ferramentas de mapeamento de clientes (Mapa de Empatia AEIOU, Cenários de Utilização e Requisitos do Utilizador) para os perfis de Desenvolvedor, Educador Físico e Profissional de Enfermagem.

---

## 1. Descrição do Sistema

O sistema consiste em uma plataforma SaaS (Software as a Service) baseada em uma arquitetura **multi-tenant**, desenvolvida para permitir que profissionais autônomos e liberais de diferentes segmentos criem, gerenciem e publiquem seus próprios portfólios digitais e páginas de captura/conversão. 

O principal objetivo da plataforma é profissionalizar a presença digital desses trabalhadores, migrando um fluxo de divulgação majoritariamente informal (redes sociais desorganizadas e indicações boca a boca) para uma estrutura centralizada que transmite alta confiança a potenciais clientes. Cada profissional cadastrado (tenant) terá seu espaço isolado para personalização, onde poderá expor seus trabalhos, depoimentos, cards de ação e contatos, gerando um link público exclusivo para ser compartilhado em canais como a biografia do Instagram ou mensagens de WhatsApp.

Como modelo de negócios, o sistema funcionará por meio de **planos de assinaturas mensais**, oferecendo uma solução de baixo custo e alta acessibilidade para os assinantes.

---

## 2. Requisitos Funcionais (RF)

Os requisitos funcionais detalham os comportamentos, recursos e dados que o sistema deve fornecer aos seus utilizadores. Eles estão divididos entre as ferramentas de customização da página e o núcleo estrutural (SaaS).

### 2.1. Painel do Cliente e Customização da Página

* **RF01 - Carrossel de Imagens (P0):** O sistema deve permitir que o usuário faça o upload de imagens para estruturar um carrossel dinâmico, possibilitando a inclusão de títulos, descrições e links externos vinculados a cada imagem.
* **RF02 - Descrições do Profissional (P0):** O sistema deve disponibilizar um campo de texto livre para que o usuário insira uma biografia, resumo profissional ou informações detalhadas sobre si mesmo.
* **RF03 - Seção de Accordions (P0):** O sistema deve possibilitar a criação de seções expansíveis (accordions) para organização de dúvidas frequentes (FAQ), detalhes específicos de planos ou informações compactas sobre os serviços prestados.
* **RF04 - Botões de Chamada para Ação - CTA (P0):** O sistema deve permitir a inserção de botões de clique customizados (com texto e link de destino) para direcionar o cliente para ações estratégicas (ex: abrir conversa no WhatsApp, preencher formulário).
* **RF05 - Área de Contatos (P0):** O sistema deve disponibilizar um módulo para que o usuário insira suas informações de contato (e-mail, redes sociais, telefone de atendimento) de maneira visível e sem restrições.
* **RF06 - Link Externo Público (P0):** O sistema deve gerar automaticamente um link (URL pública) exclusivo e otimizado para cada página criada, permitindo o acesso de qualquer visitante de forma externa.
* **RF07 - Área de Feedbacks e Depoimentos (P0):** O sistema deve permitir que o usuário cadastre e gerencie depoimentos, avaliações ou feedbacks recebidos de seus clientes para exibição pública como prova social.
* **RF08 - Painel Editável com Formatação (P0):** O sistema deve integrar um editor de texto enriquecido (*Rich Text Editor*) para que o usuário redija e formate textos complexos, atuando como um criador de "mini-matérias", artigos curtos ou dicas especializadas de sua área.
* **RF09 - Cards de Chamada para Ação - CTA (P1):** O sistema deve possibilitar a inserção de blocos visuais destacados (cards) contendo imagem, texto e link, com o propósito de capturar o interesse do cliente para conversão.

### 2.2. Core Multi-Tenant e Comercial

* **RF10 - Isolamento de Instâncias (Tenants):** O sistema deve isolar completamente o ambiente de configuração, o banco de dados e os recursos de mídia de cada cliente individual.
* **RF11 - Gestão de Assinaturas e Planos Mensais:** O sistema deve fornecer um módulo para o gerenciamento de assinaturas recorrentes, identificando o status financeiro de cada inquilino (*tenant*) e aplicando regras de bloqueio de edição/exibição em caso de inadimplência.
* **RF12 - Autenticação e Controle de Contas:** O sistema deve gerenciar o cadastro, login seguro, logout e recuperação de acesso de cada profissional de forma independente.

---

## 3. Requisitos Não Funcionais (RNF)

Os requisitos não funcionais especificam os critérios de qualidade, restrições técnicas, segurança e usabilidade necessários para a sustentação e confiabilidade do software.

* **RNF01 - Isolamento de Dados e Segurança (Segurança):** Devido à natureza multi-tenant, o sistema deve impor um isolamento rígido na camada de dados, garantindo que um inquilino jamais tenha acesso direto ou indireto aos registros de dados e mídias de outro.
* **RNF02 - Responsividade Fluida (Usabilidade):** O sistema deve ser totalmente responsivo. Tanto o painel administrativo de edição quanto a página pública do portfólio devem oferecer uma experiência fluida em computadores, tablets e, prioritariamente, dispositivos móveis.
* **RNF03 - Desempenho e Carregamento Rápido (Performance):** A página pública do usuário deve carregar seus elementos essenciais em menos de 2 segundos, mesmo em conexões de dados móveis, minimizando a taxa de rejeição de "pré-clientes".
* **RNF04 - Disponibilidade (Confiabilidade):** Sendo a principal vitrine e canal de captação dos assinantes, o sistema deve manter uma taxa de disponibilidade mínima (Uptime) de 99,9%.
* **RNF05 - Escalabilidade Arquitetural (Arquitetura):** O software deve ser projetado de forma escalável, suportando o incremento contínuo de novos inquilinos simultâneos sem perda de performance ou degradação dos serviços.
* **RNF06 - Otimização de Armazenamento de Mídia (Eficiência):** O upload de imagens para carrosséis, perfis ou cards deve passar por compressão automatizada no servidor ou armazenamento em nuvem dedicado (*Object Storage*), otimizando custos e a velocidade de entrega das imagens.