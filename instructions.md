# Instruções para Refatoração do Projeto ChefOnline

Você, como um desenvolvedor de software experiente em Clean Architecture, deverá realizar uma análise crítica e refatoração do projeto ChefOnline, que é um sistema de cadastro de clientes e proprietários. O objetivo é garantir que o projeto esteja alinhado aos princípios do Clean Architecture. Siga rigorosamente as instruções abaixo:

## Critérios a serem considerados:

1. **Manutenção dos Atributos Existentes**
   Não crie novos atributos nos DTOs. Todos os atributos necessários já foram definidos e devem ser utilizados como estão.

2. **Respeito à Inversão de Dependências**
   As camadas externas devem se comunicar com as camadas internas, mas as camadas internas **não podem** depender das camadas externas. Certifique-se de que as dependências respeitem este princípio.

3. **Não crie Ports, crie gateways**
   Crie gateways para comunicação com a base de dados

4. **Criação de Pacotes Adicionais**
   Crie os seguintes pacotes apenas se necessário:
    - **Gateway**: Para abstrações de comunicação com fontes de dados.
    - **Interfaces**: Para definir contratos entre camadas.
    - **Presenters**: Para transformar dados de saída em formatos adequados para a camada de apresentação.
    - **Use Cases**: Para encapsular a lógica de negócios.

   Evite criar pacotes ou classes desnecessárias que não agreguem valor ou alterem o escopo do projeto.

## Passos para a Refatoração:

1. **Análise Crítica do Projeto**
   Analise cada classe e pacote existente no projeto ChefOnline. Verifique se:
    - As responsabilidades estão bem definidas.
    - Não há violações dos princípios do Clean Architecture.
    - As dependências entre camadas estão corretas.

2. **Refatoração das Classes e Pacotes**
    - Reorganize as classes em pacotes adequados, respeitando os princípios do Clean Architecture.
    - Substitua implementações diretas de persistência ou comunicação externa por Ports.
    - Certifique-se de que os casos de uso (Use Cases) sejam independentes de detalhes de infraestrutura.

3. **Resumo das Alterações**
   Após concluir a refatoração, documente:
    - Quais pacotes e classes foram criados.
    - Quais alterações foram realizadas em classes existentes.
    - Como o projeto foi ajustado para seguir o padrão Clean Architecture.

## Observações Finais:

- Certifique-se de que o projeto esteja funcional após a refatoração.
- Garanta que os testes existentes sejam atualizados e que novos testes sejam criados, se necessário, para validar as alterações.
- Mantenha o escopo original do projeto, evitando adicionar funcionalidades ou alterar comportamentos que não sejam necessários para a refatoração.

Boa sorte na refatoração do ChefOnline!