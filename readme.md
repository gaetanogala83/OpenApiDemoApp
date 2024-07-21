ITS - APULIA DIGITAL MAKER - 23-24/07/2024

Caso di studio di esempio da implementare assieme il 23/07:
Creare un microservizio di gestione di account bancari.
In particolar modo il servizio dovrà 
1. ritornare tutti gli account con i relativi balance
2. recuperare un singolo account
3. cancellare un account
4. creare un account con il relativo balance
5. modificare il balance di un dato account

Implementare un microservezio RESTFull ApiFirst che deve esporre le seguenti rotte:
6. rotta di liveness
7. recuperare tutti gli account
8. recuperare un solo account by accountId
9. cancellare un account by accountId
10. aggiungere un account comprensivo di balance
11. aggiornare il balance di account


V2: aggiungere una lista di transazioni:
id, data, importo
all'aggiunta di una transazione, il balance deve essere aggiornato


Caso di studio per l'esercitazione del 24/07 mattina:
La segreteria studenti ha necessità di un microservizio che consenta la gestione degli
studenti.Questi i dati da gestiore:
1. matricola
2. nome
3. cognome
4. indirizzo
5. codice fiscale