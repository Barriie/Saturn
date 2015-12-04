::
:: Batch bestand voor het starten van het RMI HelloWorld voorbeeld.
::
:: Zorg ervoor dat de registry en de server gestart zijn.
::

java -cp .\HelloClient\target\HelloClient.jar;.\HelloClient\target\dependencies\HelloIF-1.0.jar;.\HelloClient\target\dependencies\log4j-1.2.17.jar example.hello.Client localhost

:: Wanneer je securityproblemen wilt oplossen, voeg dan onderstaande optie aan het command toe.
:: Hiermee krijg je inzicht in alle security instellingen.
::
:: 		-Djava.security.debug=access,failure

@pause