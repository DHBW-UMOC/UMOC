#  CI/CD-Pipeline – Überblick

Unser Projekt ist in **Frontend** und **Backend** unterteilt, daher gibt es für beide Bereiche separate Pipelines.

---

## Frontend

Das `Dockerfile` baut das Frontend und optimiert es in einem Docker-Container.  
Die einzige Aufgabe der Pipeline ist es, diesen Container auf  
[umoc-frontend (GitHub Packages)](https://github.com/users/max-rohrhirsch/packages/container/package/umoc-frontend)  
hochzuladen, sobald Code in den `main`-Branch gepusht wird.

---

## Backend

Im Backend erfolgen mehrere Schritte in der Pipeline:

![Backend-Pipeline-Übersicht](https://github.com/user-attachments/assets/ab9110e6-cf52-4e02-bee6-f31c3d7eb4ad)

### Code-Qualität

Zuerst wird die Code-Qualität mit **Pylint** überprüft.  
Pylint vergibt einen Score zwischen **0.0 und 10.0**.  
Die Pipeline wird abgebrochen, wenn der Score **unter 8.0** liegt.

### Tests

Wenn die Code-Qualität ausreichend ist, werden das Backend und die Tests ausgeführt.  
Hierfür verwenden wir:

- `pytest`
- `pytest-cov`
- `pytest-flask`

Die Pipeline schlägt fehl, wenn **mindestens ein Test nicht erfolgreich ist**.

### Veröffentlichen

Wenn Code-Qualität und Tests erfolgreich sind, wird das Docker-Image des Backends auf  
[umoc-backend (GitHub Packages)](https://github.com/users/max-rohrhirsch/packages/container/package/umoc-backend)  
hochgeladen.

### Metriken

Zusätzlich zum Veröffentlichen werden folgende Metriken erfasst:

- **Cyclomatic Complexity**
- **Response for Class (RFC)**

Verwendete Tools:

- `radon`
- `lizard`
- `locust`

### Manueller Publish (Workaround)

Da der Upload nur funktioniert, wenn Code-Qualität und Tests erfolgreich sind, haben wir eine separate Pipeline namens `manual-publish` eingerichtet.  
Damit können wir Images manuell veröffentlichen – auch zu Testzwecken, wenn die eigentliche Pipeline fehlschlägt.

---

## Server

Auf unserem Server läuft ein `docker-compose`-Setup mit **Watchtower**.  
Dieses prüft regelmäßig, ob ein neues Image für Frontend oder Backend veröffentlicht wurde.

Wenn ein neues Image erkannt wird:

1. Wird es automatisch heruntergeladen.
2. Der entsprechende Container wird neu gestartet.

Dadurch genügt ein Push in den `main`-Branch – der funktionierende Code wird automatisch auf **https://app.umoc.chat** übernommen, ohne manuelle Schritte.
