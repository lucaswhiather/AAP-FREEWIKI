AAP-FREEWIKI
============

Este projeto é um trabalho de faculdade feito pelos alunos Lucas Whiather e Eduarado Máximo da 
matéria PABDI do curso de Banco de Dados da Fatec de Lins.


INSTALAÇÃO E CONFIGURAÇÃO

1 - Instalar e configurar o banco de dados MySql

2 - Criar um schema para a aplicação chamado aap

3 - Configurar o arquivo hibernate.cfg localizado no pacote deafult
    url: aqui você configura o ip do banco de dados seguido do nome do schema
    username: usuário do banco(geralmene é root)
    password: senha do banco
    
4 - Criar as tabelas no banco. Pode ser feito de 2 formas
  1 - Após configurar o hbiernate.cfg, executar a classe CriarBanco no pacote util
  2 - Executar o script do banco através de alguma ferramenta como o MySql Administrator
  
CONFIGURANDO ADMINISTRADOR DO WIKI

1 - Cadastre um usuário normal
2 - Edite no banco de dados na tabela "user" na coluna "type" para o valor 2
