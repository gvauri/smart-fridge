# Lokale Entwicklung

Um Lokal zu entwickeln folgendes tun:

```bash
docker compose up --build
```
Dann wir via Docker ein MongoDB-Image gestartet mit Volume.
Im ``.env`` SPRING_PROFILES_ACTIVE auf "dev" setzen.
Backend über IntelliJ starten.

# Produktion
Die produktive DB wird auf MongoDB Atlas gehostet.
Die Verbindung zur DB wird über die ``application-prod.properties`` hergestellt.
Damit das Backend auf die produktive DB connected, muss im ``.env`` SPRING_PROFILES_ACTIVE auf "prod" gesetzt werden.

# ENV Variables im IntelliJ setzen
.env anlegen und Variablen eintragen, z.B. SPRING_PROFILES_ACTIVE=dev

Unter Run -> Edit Configurations -> Modify Options -> unter Operating System ist Environment Variables -> dann .env auswählen