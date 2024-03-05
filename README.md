# Trabalho acadêmico, Engenharia da computação (Instituto Infnet - 3 semestre)
## Teste de Performance 2 - Desenvolvimento de Serviços Web e Testes com Java


### O que é? 

Sistema de monitoramento de  aeroporto, relaciona cliente e voos de avião, o sistema cadastra clientes e estes podem comprar passagens disponíveis, o sistema possui um menu separado em dois campos, o primeiro sendo do cliente e o segundo das passagens, cada campo consta dados e ações relacionadas a si e o sistema utiliza de arquivos CSV para persistência de dados.

### Importante
O projeto só irá funciona caso o caminho da pasta "dados" se mantenha no seguinte padrão, C:/Caminho até o atual diretório/nome da pasta do projeto/src/main/java/data  
Exemplo: C:/Github_Clone/Java_TP2_DR3_BL3/src/main/java/data  
Obs: O nome da pasta do projeto, no caso Java_TP2_DR3_BL3, não é relevante para o funcionamento do projeto

### Ferramenta utizadas 

Itellij (IDE) 
Jdk 17   (java development kit)
Maven (ferramenta de testes)
JUnit 5.10.2 (Framework para testes)

### Regras 

Cliente 

- Um cliente só pode realizar a compra de uma passagem caso esteja cadastrado no sistema.
- Um cliente só pode estar associado a um único voo, mas um cliente pode comprar várias passagens de um único voo, caso deseje viajar com alguém
- O Cliente pode cancelar sua passagem ou trocar por outra, ele também pode apagar, atualizar ou criar um cadastro.

“Banco de dados”
- Os arquivos CSV simularão um banco de dados, mas não existe nenhuma restrição nos arquivos canto a limite de caracteres por dado.
- Há dois arquivos, um deles é o de Clientes (Clientes.csv) e o segundo é de Passagens (Passagens.csv)

- Clientes tem os campos, 
id (número identificador), 
nome (nome do cliente), 
Cpf  (cpf do cliente, apenas 14 dígitos) 
idPassagem (número que se relaciona com a tabela passagens, caso o número seja 0 o cliente não tem passagens compradas)

- Passagens tem os campos
Id (Número identificador)
LclPartida (Local de Partida)
LclChegada (Local de Chegada)
Duracao (Duração da viagem)
Preco (Preço da passagem em real)

### Testes 
Este projeto utiliza o framwork JUnit para criação e cobertura de testes, estes podem ser encontrados na pasta test, no momento apenas a pasta model possui cobertura 100% de testes.
