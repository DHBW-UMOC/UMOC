# Einführung und Ziele

## Aufgabenstellung

UMOC ("Unsafe Method of Commuunication") ist ein webbasierter Messenger, der sichere Ende-zu-Ende-Verschlüsselung (E2EE) mit absichtlich "unsicheren" Interaktionsfeatures kombiniert.
**Kernfunktionen**:
- Echtzeit-Nachrichtenaustausch mit Websockets.
- "Unsafe"-Features: Nachrichten andere sehen während sie getippt werden, TikTok-ADHS-Modus, Last Message von blockierten benutzern [und vieles mehr.](https://github.com/DHBW-UMOC/UMOC/blob/master/README.md)
- Web-UI nach Vorbild moderner Messenger (WhatsApp/Discord).
  
**Referenzen**: [Software Requirement Specification](https://github.com/DHBW-UMOC/UMOC/blob/master/docs/Software%20Requirements%20Specification.md)

## Qualitätsziele

| Priorität | Qualitätsziel                | Konkretes Szenario                                                                 |
|-----------|------------------------------|-----------------------------------------------------------------------------------|
| 1         | Sicherheit                   | Alle Nachrichten sind standardmäßig E2EE-verschlüsselt (ISO 25010: Security).   |
| 2         | Leistungseffizienz                     | Nachrichtenbearbeitung in Echtzeit (<500 ms Latenz, ISO 25010: Performance Efficiency).|
| 3         | Benutzungsfreundlichkeit       | UI-Ladezeit <2 Sekunden, intuitive Navigation (ISO 25010: Interaction Capability).|

## Stakeholder

| Rolle        | Kontakt        | Erwartungshaltung |
|--------------|----------------|-------------------|
| Stakeholder/Dozent | Ichters, Harald | Funktionierender Webbasierter Messenger  |
| Product Owner/Backend Developer | [Rohrhirsch, Max](https://github.com/Max-Rohrhirsch) | Klare Vision und Priorisierung der Produktanforderungen. |
| Scrum Master/Designer | [Schön, Adrian](https://github.com/AAAAAAAdrian) | Sicherstellung eines reibungslosen Scrum-Prozesses, Design der UI. |
| Backend Developer | [Lingenfelser, Pascal](https://github.com/pplayer1712) | Stabile und skalierbare Backend-Lösungen, klare technische Anforderungen und Unterstützung bei der Problemlösung |
| Frontend Developer | [Mohr, Lucas](https://github.com/Max-Rohrhirsch) | Benutzerfreundliche und ansprechende Benutzeroberflächen, Zusammenarbeit mit dem Designer. |
| Seelischer Beistand | [Schmidt, Jan Mathis](https://github.com/schmid-di) | Bereitstellung von emotionaler Unterstützung und Teamzusammenhalt, Förderung eines positiven Arbeitsumfelds. |
---
# Randbedingungen

| Kategorie         | Randbedingung                                                                 |
|-------------------|-------------------------------------------------------------------------------|
| Technisch         | Verwendung von **Angular (Frontend)**, **Flask (Backend)**, **SQLite**, Websockets. |
| Organisatorisch   | Projektabgabe bis [30.06.2025], Entwicklung im 5-Personen-Team mit Scrum.          |
| Rechtlich         | Open-Source-Lizenz (GPL 3.0), keine kommerzielle Nutzung.                     |
| Konventionen      | Clean Code, Testing, Git-Branching (Feature-Branches).                |

---
# Kontextabgrenzung

## Fachlicher Kontext

**Kommunikationsbeziehungen**:
- **Benutzer**: Senden/Empfangen von Nachrichten über Web-UI.
- **Standorddienst**: Anreichern von Nachrichten mit Geolokationsdaten (z.B. Google Maps API).
- **Admin-Tool**: Monitoring der Serverlast.

## Technischer Kontext

**Kommunikation**:
- **Frontend ↔ Backend**: REST-APIs (Nutzerverwaltung), Websockets (Nachrichten).
- **Backend ↔ MySQL**: sqlalchemy für Persistenz.
- **Externe Dienste**: SSL/TLS für Standort-APIs.

---
# Lösungsstrategie
- **Websockets**: Bidirektionale Echtzeitkommunikation für Nachrichten und Bearbeitungsereignisse.
- **Tech-Stack**: 
  - Angular für dynamische UI-Komponenten (z.B. Live-Textvorschau).
  - Flask als leichtgewichtiges Backend mit SocketIO für Websockets.
- **Sicherheit**: 
  - E2EE mit AES-256, Schlüsselaustausch via Diffie-Hellman.
  - JWT-Tokens für Authentifizierung.
- **Architekturmuster**: Layered Architecture (Präsentationsschicht, Applikationsschicht, Datenschicht).

---
# Bausteinsicht
## Komponentendiagramm

![Component View.png](https://github.com/DHBW-UMOC/UMOC/blob/master/docs/Component%20View.png)
*Da UMOC Angular benutzt, besitzt das Frontend zu viele Components, um sie in einem Komponentendiagramm darzustellen.*
### Erklärung

1. **SQLite**: Daten sind in der Datenbank persistiert.
2. **Backend Service Layer**: Services rufen Daten aus Datenbank ab.
3. **API**: API stellt Daten über Port 5000 bereit.
4. **Frontend Service Layer**: Konsumiert API über HTTPS auf Port 443.
5. **Chat Window Component**: Wird mit Daten populiert.
6. **Message Component**: Nach Volumen werden Message Components erstellt und mit einzelnen Datensets populiert.

---
# Laufzeitsicht
## Login
![Sequence Diagram Login](https://github.com/DHBW-UMOC/UMOC/blob/master/docs/Sequence%20Diagram%20Login.png)

### Erklärung
1. **Login**: Der User initiiert den Anmeldeprozess über den Client, indem er seinen Benutzernamen und sein Passwort eingibt.
2. **Send Password & Username**: Der Client sendet die Anmeldedaten an den Server.
3. **Check Input**: Der Server empfängt die Anmeldedaten und leitet eine Validierung ein. Hierbei wird eine Anfrage an die Datenbank gesendet, um die Eingaben zu prüfen.
4. **return User on None**: Die Datenbank sucht nach dem Benutzer und vergleicht das eingegebene Passwort. Wenn ein Benutzer existiert, wird dieser zurückgegeben, andernfalls nicht.
5. **return Error**: Wenn die Datenbank keinen Benutzer zurückgibt, bedeutet das, dass die Anmeldedaten ungültig sind. Der Server sendet eine Fehlermeldung zurück an den Client, um den Benutzer über die falschen Anmeldedaten zu informieren.
6. **Set Session Key**: Gibt die Datenbank einen Benutzer zurück, wird ein Sitzungsschlüssel generiert und der Datenbank mitgeteilt.
7. **Send Session Key**: Der Server sendet den Sitzungsschlüssel zurück an den Client, der nun für die Dauer der Sitzung verwendet wird.

## Create User/Register
![Sequence Diagram Create User](https://github.com/DHBW-UMOC/UMOC/blob/master/docs/Sequence%20Diagram%20Create%20User.png)

### Erklärung
1. **Create User**: Der Benutzer initiiert den Prozess zur Erstellung eines neuen Benutzers über den Client, durch eine Registrierung.
2. **Send Data**: Der Client sendet die enigegebenen Benutzerdaten an den Server.
3. **Store User**: Der Server empfängt die Benutzerdate und leitet den Speichervorgang ein. Hierbei wird eine Anfrage an die Datenbank gesendet, um die neuen Benutzerdaten zu speichern.
4. **Return**: Die Datenbank gibt dem Server eine Bestätigung, dass der Benutzer erfolgreich erstellt wurde. Der Server sendet anschließend eine Bestätigung dem Client zu, um den Benutzer zu informieren, dass der Benutzer erfolgreich erstellt wurde.

---
# Verteilungssicht
<!-- Kapitel 7 (Irgendwann in Zukunft machen)-->
---
# Querschnittliche Konzepte
<!-- Kapitel 8 (Irgendwann in Zukunft machen)-->
---
# Architekturentscheidungen

| Entscheidung               | Alternativen       | Begründung                                                                 |
|----------------------------|--------------------|----------------------------------------------------------------------------|
| Websockets statt Polling   | HTTP-Long-Polling  | Geringere Latenz, reduzierte Serverlast durch bidirektionale Kommunikation.|
| Flask statt Django         | Node.js, Spring    | Einfache Integration von Websockets (SocketIO), Python-Expertise im Team.  |
---
# Qualitätsanforderungen

## Qualitätsbaum
![Quality Requirements.png](https://github.com/DHBW-UMOC/UMOC/blob/master/docs/Quality%20Requirements.png)
## Qualitätsszenarien
### Performance
#### [A1] Latenz
- Antwortzeit für Nachrichten
- Verbindungsaufbauzeit
#### [A2] Durchsatz
- Anzahl der Nachrichten pro Sekunde
- Maximale gleichzeitige Verbindungen
#### [A3] Ressourcennutzung
- CPU-Auslastung
- Speicherverbrauch

### Sicherheit
#### [B1] Authentifizierung
- Methoden der Benutzeranmeldung (z.B. OAuth, Zwei-Faktor)
#### [B2] Vertraulichkeit
- Verschlüsselung (z.B. AES für gespeicherte Nachrichten)
#### [B3] Zugriffskontrolle
- Rollenbasierte Zugriffskontrolle
- Berechtigungen für verschiedene Benutzer

### Usability
#### [C1] Benutzeroberfläche
- Klarheit der Navigation
- Konsistenz der Gestaltung
#### [C2] Reaktionsfähigkeit
- Ladezeiten der Benutzeroberfläche
- Zeit bis zur ersten Aktion (z.B. Nachrichten senden)
#### [C3] Hilfestellungen
- Tooltips
- FAQs und Onboarding-Prozesse

### Zuverlässigkeit
#### [D1] Verfügbarkeit
- Uptime: Prozentuale Verfügbarkeit des Systems über einen definierten Zeitraum (z.B. 99,9%).
- Fehlerbehandlung: Mechanismen zur automatischen Wiederherstellung nach Serverausfällen.
#### [D2] Fehlertoleranz
- Backup-Mechanismen: Maßnahmen zur Sicherung von Daten und Kontinuität bei Systemausfällen.
- Redundanz: Einsatz von redundanten Komponenten (z.B. Server, Datenbanken) zur Sicherstellung der Betriebskontinuität.
#### [D3] Wiederherstellbarkeit
- Backup-Frequenz: Häufigkeit der Datensicherungen zur Minimierung von Datenverlust.
- Wiederherstellungszeit: Zeit, die benötigt wird, um nach einem Ausfall den Normalbetrieb wiederherzustellen (RTO – Recovery Time Objective).

---
# Risiken und technische Schulden
<!--
<div class="formalpara-title">

**Inhalt**

</div>

Eine nach Prioritäten geordnete Liste der erkannten Architekturrisiken
und/oder technischen Schulden.

> Risikomanagement ist Projektmanagement für Erwachsene.
>
> —  Tim Lister Atlantic Systems Guild

Unter diesem Motto sollten Sie Architekturrisiken und/oder technische
Schulden gezielt ermitteln, bewerten und Ihren Management-Stakeholdern
(z.B. Projektleitung, Product-Owner) transparent machen.

<div class="formalpara-title">

**Form**

</div>

Liste oder Tabelle von Risiken und/oder technischen Schulden, eventuell
mit vorgeschlagenen Maßnahmen zur Risikovermeidung, Risikominimierung
oder dem Abbau der technischen Schulden.

Siehe [Risiken und technische
Schulden](https://docs.arc42.org/section-11/) in der
online-Dokumentation (auf Englisch!).
-->
---
# Glossar
<!--
<div class="formalpara-title">

**Inhalt**

</div>

Die wesentlichen fachlichen und technischen Begriffe, die Stakeholder im
Zusammenhang mit dem System verwenden.

Nutzen Sie das Glossar ebenfalls als Übersetzungsreferenz, falls Sie in
mehrsprachigen Teams arbeiten.

<div class="formalpara-title">

**Motivation**

</div>

Sie sollten relevante Begriffe klar definieren, so dass alle Beteiligten

-   diese Begriffe identisch verstehen, und

-   vermeiden, mehrere Begriffe für die gleiche Sache zu haben.

Zweispaltige Tabelle mit \<Begriff> und \<Definition>.

Eventuell weitere Spalten mit Übersetzungen, falls notwendig.

Siehe [Glossar](https://docs.arc42.org/section-12/) in der
online-Dokumentation (auf Englisch!).

| Begriff        | Definition        |
|----------------|-------------------|
| *\<Begriff-1>* | *\<Definition-1>* |
| *\<Begriff-2*  | *\<Definition-2>* |
-->
