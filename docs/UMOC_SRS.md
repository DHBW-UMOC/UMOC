# Software Requirements Specification (SRS) für UMOC

## 1. Einleitung

### 1.1 Übersicht
UMOC ist ein Messenger mit dem Konzept "Unsafe but secure". Die App bietet vollständige Ende-zu-Ende-Verschlüsselung und alle Standard-Sicherheitsmaßnahmen, um den Inhalt von Nachrichten zu schützen. Jedoch werden die Risiken durch menschliche Interaktionen betont: Benutzer können beispielsweise die Nachrichten anderer sehen und manipulieren, bevor sie gesendet werden, was zu chaotischen oder missverständlichen Gesprächen führen kann. Dieses Spannungsfeld zwischen Sicherheit und Unsicherheit macht UMOC einzigartig und stellt die Hauptinnovation des Messengers dar.

### 1.2 Geltungsbereich
Dieses Dokument beschreibt die funktionalen und nichtfunktionalen Anforderungen an den UMOC-Messenger. Es deckt die Hauptfunktionen, wie das Senden/Empfangen von Nachrichten, Verwaltung von Kontakten und Gruppen und spezielle UMOC-Funktionen wie das Manipulieren von Nachrichten ab. Es stellt einen groben ersten Entwurf dar. Dieser deckt vorerst nur di Hauptfunktionalität des Messengers ab. Funktionen die auf grundfunktionalitäten basieren (z.B. Gruppen) werden später im Entwicklungsprozess eingebaut. Die UMOC Features sollen nach der Entwicklung des eigentlichen Messengers implementiert werden.

### 1.3 Definitionen, Akronyme und Abkürzungen
- **UMOC**: Unsafe Method of Communication
- **E2EE**: Ende-zu-Ende-Verschlüsselung
- **UI**: User Interface (Benutzeroberfläche)
- **API**: Application Programming Interface

### 1.4 Referenzen
- UML-Anwendungsfalldiagramm (siehe Abschnitt 2.1)
- Mockup der Chat-Seite (siehe Abschnitt 2.3)



## 2. Funktionale Anforderungen

### 2.1 Übersicht
UMOC bietet grundlegende Funktionen eines Messengers, wie das Senden und Empfangen von Nachrichten, das Erstellen von Gruppen und das Verwalten von Kontakten. Gleichzeitig hebt sich UMOC durch Features hervor, die menschliche Unsicherheiten nutzen, um potenziell chaotische Kommunikationsszenarien zu schaffen.

**UML-Anwendungsfalldiagramm**:  
![UML Diagramm](https://github.com/user-attachments/assets/e32ffb47-1343-4cc7-aabb-b563d866874c)

### 2.2 Funktion: Nachrichten senden/empfangen
Nutzer müssen zuallererst in der Lage zu sein miteinander zu kommunizieren. In einem Messenger geschieht dieser Austausch über Textnachrichten die gesendet und empfangen werden. Diese Funktionalität wird über eine Python Flask API bereitgestellt. Nachrichten müssen verschlüsselt werden und werden in einer MySQL Datenbank persistiert.
#### Anforderungen:
- Textnachrichten können von der API angefragt werden.
- Textnachrichten können verschlüsselt gesendet werden.
- Textnachrichten werden auf dem Server persistiert.

#### Relevante Stories:
- SCRUM-1
- SCRUM-6
- SCRUM-9
- SCRUM-7
- SCRUM-11

#### Geschätzter Aufwand: hoch


### 2.3 Funktion: Web-basierte UI
Nutzer sollen die API über eine Webseit Erreichen können. Diese soll stilistisch an vorhandene Messenger wie WhatsApp oder Snapchat angelehnt sein.

#### Anforderungen:
- Am linken Bildschirmrand sind alle verfügbaren Kontakte abgebildet
- Wählt der Nutzer einen Kontakt aus wird der Chat mit diesem Kontakt geöffnet.
- Ein Knopf öfnet die Einstellungen

#### Relevante Stories:
- SCRUM-3
- SCRUM-12

#### Geschätzter Aufwand: hoch

**UI-Mockup**:
![Chat MockUp](https://github.com/user-attachments/assets/fad24d66-d7ed-45af-bee3-15e0039250e8)


### 2.x Funktion: ...
Beschreibung

#### Anforderungen:
- x
- x
- x

#### Relevante Stories:
- x

#### Geschätzter Aufwand: x



## 3. Nichtfunktionale Anforderungen

### 3.1 Benutzerfreundlichkeit
- Die Benutzeroberfläche sollte einfach und intuitiv sein, basierend auf modernen UI-Designs wie WhatsApp oder Snapchat. 
- Siehe **SCRUM-3**: Benutzer fordert ein modernes UI.

### 3.2 Sicherheit
- Alle Nachrichten sind standardmäßig Ende-zu-Ende-verschlüsselt.
- Sabotagefunktionen, die menschliche Unsicherheit fördern, dürfen die E2EE nicht gefährden.

### 3.3 Leistung
- Nachrichtenmanipulation sollte in Echtzeit durchgeführt werden, ohne Verzögerung in der Benutzeroberfläche.

### 3.4 Zuverlässigkeit
- Trotz der absichtlichen Unsicherheitsfaktoren sollte die Verfügbarkeit des Messengers und die Integrität von Daten gewährleistet sein.

### 3.5 Testbarkeit
- Es müssen spezifische Unit Tests und Integrationstests durchgeführt werden (siehe **SCRUM-7** und **SCRUM-11**).



## 4. Technische Einschränkungen
- Lizenz: GPL 3.0
