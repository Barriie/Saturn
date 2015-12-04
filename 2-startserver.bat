::
:: Batchbestand voor het starten van de IVH5 RMI voorbeelden.
:: Je start de server het handigst vanuit de directory waar de webserver
:: de classes kan vinden. Pas deze zo nodig hieronder aan.
::
cd C:\dev\workspace\worked-example\IVH5\target\fysio

:: Start java met het juiste classpath
java -cp fysio-api.jar;fysio-shared.jar;fysio-server.jar;.\dependencies\ProftaakAPI-0.1.jar;.\dependencies\log4j-1.2.17.jar edu.avans.ivh5.server.model.main.FysioServer -properties resources/fysio-1.properties

:: Wanneer je securityproblemen wilt oplossen, voeg dan onderstaande optie aan het command toe.
:: Hiermee krijg je inzicht in alle security instellingen.
::
:: 		-Djava.security.debug=access,failure

@pause