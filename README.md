# WeFit Cadastro de Pessoas: Sistema Simplificado de Registro de Pessoa Física e Jurídica

A aplicação segue o padrão de arquitetura limpa, separando as responsabilidades em camadas distintas (controller, application, domain e infrastructure). Fornece endpoints REST para operações de cadastro com validação embutida, tratamento adequado de erros e capacidade de logging. O sistema suporta tanto o cadastro de pessoas físicas quanto jurídicas, mantendo uma estrutura comum para informações compartilhadas como endereços e contatos.

## Estrutura do Repositório

```
src/
└── main/
    └── java/com/wefit/cadastro/
        ├── application/
        │   ├── dto/                 # Objetos de transferência de dados (entrada e saída)
        │   ├── exception/           # Exceções específicas da aplicação
        │   ├── factories/           # Criação de objetos de domínio
        │   └── service/             # Casos de uso / lógica da aplicação
        │
        ├── domain/
        │   └── model/               # Entidades e regras de negócio
        │
        ├── infrastructure/
        │   ├── repository/          # Implementações de persistência        │                
        │
        └── controller/              # Adaptadores de entrada (REST controllers)

```

## Instruções de Uso com Docker
No terminal, execute o comando abaixo na raiz do projeto (onde está o Dockerfile):

```bash
docker build -t app .

Esse comando irá:

Baixar uma imagem com Java 21 slim

Compilar o projeto com Maven

Gerar um .jar

Empacotar tudo em uma imagem Docker chamada app

Executar a aplicação
Depois que a imagem estiver criada, inicie o container com:

docker run -p 8080:8080 app

Isso irá:

Rodar sua aplicação Spring Boot no container

Mapear a porta 8080 do container para a 8080 da sua máquina

Agora, sua aplicação estará acessível em:
📍 http://localhost:8080

### Pré-requisitos

* Java Development Kit (JDK) 21 ou superior
* Maven 3.9.x (já incluído no wrapper)
* Banco de dados H2 (configurado no `application.properties`)

### Instalação

1. Clone o repositório:

```bash
git clone <url-do-repositório>
cd wefit-cadastro
```

2. Compile a aplicação:

```bash
# Para sistemas Unix
./mvnw clean install

# Para Windows
mvnw.cmd clean install
```

3. Execute a aplicação:

```bash
# Para sistemas Unix
./mvnw spring-boot:run

# Para Windows
mvnw.cmd spring-boot:run
```

### Início Rápido

1. Cadastrar uma nova pessoa física:

```bash
curl -X POST http://localhost:8080/v1/cadastro \
-H "Content-Type: application/json" \
-d '{
    "tipo_pessoa": "FISICA",
    "cpf": "12345678901",
    "nome": "João Silva",
    "celular": "11999999999",
    "email": "joao@email.com",
    "confirmar_email": "joao@email.com",
    "endereco": {
        "cep": "12345678",
        "logradouro": "Rua Exemplo",
        "numero": "123",
        "cidade": "São Paulo",
        "bairro": "Centro",
        "estado": "SP"
    },
    "aceite_termos_uso": true
}'
```

### Exemplos Detalhados

1. Cadastrar uma nova pessoa jurídica:

```bash
curl -X POST http://localhost:8080/v1/cadastro \
-H "Content-Type: application/json" \
-d '{
    "tipo_pessoa": "JURIDICA",
    "cnpj": "12345678000190",
    "nome": "Empresa LTDA",
    "celular": "11999999999",
    "email": "empresa@email.com",
    "confirmar_email": "empresa@email.com",
    "endereco": {
        "cep": "12345678",
        "logradouro": "Av Comercial",
        "numero": "1000",
        "cidade": "São Paulo",
        "bairro": "Centro",
        "estado": "SP"
    },
    "aceite_termos_uso": true
}'
```

## Solução de Problemas

### Problemas Comuns

1. **Erro de Conexão com o Banco de Dados**

   * Erro: "Could not connect to database"
   * Solução: Verifique as credenciais no `application.properties`
   * Confirme se o serviço do banco está em execução

2. **Erros de Validação**

   * Erro: "400 Bad Request" com mensagens de validação
   * Solução: Verifique se todos os campos obrigatórios foram preenchidos corretamente
   * Confirme se o e-mail e a confirmação de e-mail são iguais
   * Verifique o formato do CPF/CNPJ

### Modo de Depuração

1. Ativar logs de depuração:

   * Adicione ao `application.properties`:

   ```properties
   logging.level.com.wefit=DEBUG
   ```

2. Verifique os logs em:

   ```
   ./logs/spring-boot-logger.log
   ```


### Interações entre os Componentes:

1. O controller recebe a requisição HTTP POST com os dados de cadastro
2. Os dados são validados com Bean Validation
3. A camada de service processa a requisição
4. A factory instancia o tipo de pessoa (Física/Jurídica) apropriado
5. O repositório persiste os dados
6. A resposta retorna o status de sucesso ou erro
