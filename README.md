# Banco Java RYC

Para utlizar o sistema, execute o método main e então uma janela será exibida para selecionar as opções desejadas

O sistema conta com 6 contas "cadastradas", para verificar e alterar informações dessas contas basta acessar o arquivo [ListaClientes.csv](https://github.com/YanAssis/Banco_Java_RYC/blob/main/ListaClientes.csv)

Ao iniciar, o sistema irá solicitar que seja indicado se o usuário é pessoa física ou jurídica e então se possui uma conta. Caso possua, basta inserir Nome e Senha para logar, caso contrário basta inserir nome, senha e cpf ou cnpj para criar uma nova conta.

Após criar/logar, poderão ser selecionadar as opções de "Saque", "Depósito", "Transferência", "Limites" e "Extrato".

- **Saque:** O sistema solicitará o valor a ser sacado e, caso o saldo seja suficiente, o saque será realizado.
  
- **Depósito:** O sistema solicitará o valor a ser depositádo e adicionará ele ao saldo.
  
- **Tranferência:** O sistema perguntará se a trasnferência será realizada para um cliente físico ou jurídico então irá solicitar o nome do cliente, caso o cliente exista, o sistema irá solicitar o valor a ser transferido e, caso o valor a ser transferido esteja em conta e dentro do limite de trasnferência, a transferência será feita. 
  
- **Limites:** O sistema exibirá o limite atual de transferência e perguntará se o usuário deseja alterar o limite. Caso deseje, basta inserir o valor para alterar.
  
- **Extrato:** Ao executar as operações citadas anteriormente é criado um registro de transação e ao selecionar a opção extrato esses registros são exportados para um arquivo CSV na pasta do projeto.
 
