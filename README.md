# Aeroporto-Sprint-4
Sistema aéreo desenvolvido para a quarta avaliação do programa de bolsas

--AP AirLines--

--------------------------
Própositos
--------------------------

	O sistema AP Airlines foi desenvolvido com o propósito de fazer a venda de passagens
	aéreas. Destinado a qualquer usuário que deseje pesquisar por passagens disponíveis no mercado
	e adquirir a(s) tal(is), caso seja o desejado. A aplicação visa em fazer a busca de passagem
	por cidades, apenas por origem e destino e data, e se estiver cadastrado em nosso sistema, informaremos
	as opções de voos para aquele destino com aquela origem.

	Desenvolvido por:
	Paulo Roberto Queiroz de Sá Filho e
	André Monteiro Lima.

--------------------------
Como utilizar
--------------------------
	
	Utilizar o banco de dados mySQL com a database "aeroporto".
	usuario = airport;
	senha = root;
	
	O sistema irá criar as tabelas automaticamente.
	
--------------------------
Administrador
--------------------------

	Primeiramente, o administrador do sistema deve acessar o formulario:

		http://localhost:8080/Aeroporto_war_exploded/Flights.xhtml

	OBS: Data deve seguir o formato <"yyyy-MM-dd HH:mm:ss">
	
![Flights](https://raw.githubusercontent.com/ktverde/Aeroporto-Sprint-4/master/README_imgs/Flights.png)

	Após cadastrar alguns voos, o sistema está funcional.
	
	Outras Telas:
	
![FlightList](https://raw.githubusercontent.com/ktverde/Aeroporto-Sprint-4/master/README_imgs/Flighs%20List.png)

--------------------------
Usuario
--------------------------

	O usuario, após isso deve se cadastrar no sistema, ou utilizar o google para se logar na página principal:

	http://localhost:8080/Aeroporto_war_exploded
	
![Login](https://raw.githubusercontent.com/ktverde/Aeroporto-Sprint-4/master/README_imgs/Login.png)

	Logo após, seguirá os procedimentos da aplicação.
	Seqgue abaixo um fluxo de telas, para o usuário efetuar uma compra.
	
![BuyTicket](https://raw.githubusercontent.com/ktverde/Aeroporto-Sprint-4/master/README_imgs/Buy%20Ticket.png)

![FlightChoice](https://raw.githubusercontent.com/ktverde/Aeroporto-Sprint-4/master/README_imgs/FlightChoice.png)

![SeatSelection](https://raw.githubusercontent.com/ktverde/Aeroporto-Sprint-4/master/README_imgs/Escolha%20seu%20assento.png)

![Sucess](https://raw.githubusercontent.com/ktverde/Aeroporto-Sprint-4/master/README_imgs/Sucess.png)

