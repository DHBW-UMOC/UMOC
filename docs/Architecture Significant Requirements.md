# Nachrichten mit Websockets versenden

## Kontext und Problemstellung

Die Momentanen Endpunkte sind mit REST-APIs erstellt. 
Diese sind jedoch nicht für Echtzeitkommunikation geeignet. 
Es soll eine Lösung gefunden werden, um Nachrichten in Echtzeit zu versenden.

## Betrachtete Varianten

* Clients fragen in regelmäßigen Abständen den Server nach neuen Nachrichten ab.
* Websockets verwenden.

## Entscheidung

Gewählte Variante: "Websockets", denn sie ermöglichen eine bidirektionale Kommunikation zwischen Client und Server und sind für Echtzeitkommunikation geeignet. Belastung des Servers wird reduziert, da keine unnötigen Anfragen gestellt werden.


## Konsequenzen

* Gut, weil das Netwerk, Server und Client entlastet werden.
* Schlecht, weil wir uns in ein neues Thema einarbeiten müssen.
