# GeneratorQRCode
Generatore di QR Code tramite API REST.

#BackEnd 
Linguaggio Java con implementazione Spring e Spring Boot realizzato tramite tecnologia API REST con la realizzazione di un Controller ricevente la chiamata, un Service a cui viene inoltrata la chiamate ed elaborata la risposta.

#FrontEnd
Linguaggio JavaScript con implementazione Angular 16, tramite un service.ts viene creata la richiesta (inoltrata al BackEnd) con un header contenente un file in formato JSON e riceve in risposta un file text perché riceverà il QR code come stringa e successivamente verra convertita in immagine .png, visualizzata a schermo ed eventualmente scaricabile tramite apposito pulsate.
