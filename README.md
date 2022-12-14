# Implementazione gestione anomalie
## Segnalazione e risoluzione delle anomalie con registrazione eventi su file di Log
---
Il mio contributo consiste nell'implementazione delle funzioni che consentono di:
* Segnalare al server di Backend eventuali anomalie rilevate dalla SmartBox installata sul mezzo (Simulata).
* Segnalare la risoluzione delle anomalie da parte della SmartBox o dall'intervento di un operatore mediante interfaccia di Frontend o mobile App.
* Visualizzare in tempo reale lo stato di ogni mezzo potendo filtrare per tipologia di anomalia.
* Segnalare la manutenzione completa di un mezzo azzerando tutte le anomalie.
* Tenere traccia del susseguirsi delle anomalie in un file di Log filtrabile secondo necessità.
---
Le anomalie di cui si tiene traccia sono: Gps, temperatura motore, temperatura cassone isotermico, sensore del portellone posteriore. Sono stati inoltre predisposti 3 errori generici per sviluppi futuri. Le anomalie sono dei valori Booleani che risulteranno "true" nel caso di anomalie presente e "false" nel caso di anomalia assente.

I file di Log contengono: ID del mezzo, azione, tipologia ed istante in cui viene registrato l'evento. 

Si è cercato di catturare più eccezioni possibili e dai test effettuati il sistema blocca l'inserimento nel Database relazionale di qualisasi tipo di dato non consentito.
