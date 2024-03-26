# GeneratorQRCode
Generatore di QR Code per l'accesso ad una rete WiFi domestica tramite API REST.

#BackEnd 
Linguaggio Java con implementazione Spring e Spring Boot realizzato tramite tecnologia API REST con la realizzazione di un Controller ricevente la chiamata attraverswo un oggetto CredenzRequest composto da network, password e type della rete WiFi del QR code che andrà creato, un Service a cui vengono inoltratati i dati ed elaborata la risposta creando il QR code e inviarlo in risposta alla reciesta fatta.

#FrontEnd
Linguaggio JavaScript con Angular 16, tramite un service.ts viene creata la richiesta (inoltrata al BackEnd) con un header contenente un file in formato JSON, riceve in risposta un file text perché il QR code viene trasmesso come stringa e successivamente verra convertita in immagine (.png), visualizzata a schermo ed eventualmente scaricabile tramite apposito pulsate.
