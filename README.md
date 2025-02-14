# Datenbanktechnologien - Versicherung

Dieses Repository beinhaltet den kompletten Satz an Übungsaufgaben für das Modul "Datenbanktechnologien" an der HTW
Berlin. Die Aufgaben basieren auf dem Anwendungsfall einer Versicherung.

Importieren sie das Repository in ihren eigenen Github-Account und setzen sie die Sichtbarkeit auf "private", damit
andere Gruppen nicht ihre Lösungen einsehen können. Klonen sie dann ihr Repository (nicht meines) und bearbeiten sie es
mit der IDE ihrer Wahl (VS Code, IntelliJ, Eclipse)

- Anleitung Import
  [(link)](https://ic-htw.github.io/home/lv/dbtech/p/github-import.html)
- Anleitung VS Code
  [(link)](https://ic-htw.github.io/home/lv/dbtech/p/ide-vscode-vers.html)
- Anleitung IntelliJ
  [(link)](https://ic-htw.github.io/home/lv/dbtech/p/ide-intellij-vers.html)
- Anleitung Eclipse
  [(link)](https://ic-htw.github.io/home/lv/dbtech/p/ide-eclipse.html)

## Datenmodell

Zur Bearbeitung der Aufgaben müssen sie das Datenmodell in ihrer eigenen Datenbank anlegen.

- Beschreibung des Datenmodells
  [(pdf)](https://github.com/ic-htw/dbtech-vers/blob/main/doc/vers-beschreibung.pdf)
- SQL-Code zur Erzeugung der Tabellen und zum Einfügen der Daten
  [(link)](https://github.com/ic-htw/dbtech-vers/tree/main/db/versicherung)

## Logging

Die Verwendung der Logging-Einstellungen wird in der Übung erläutert.

```
-Dorg.slf4j.simpleLogger.showThreadName="false"
-Dorg.slf4j.simpleLogger.logFile="System.out"
-Dorg.slf4j.simpleLogger.log.org.dbunit="error"
-Dorg.slf4j.simpleLogger.log.de.htwberlin="info"
```

Protokollstufen

- error
- warn
- info
- debug

## Übungen

Es gibt eine Übung zur Wiederholung von SQL, zwei Übungen zur Implementierung von datenbank-orientierten Diensten in
Java und eine Übung zur Implementierung eines datenbank-orientierten Dienstes in PL/SQL.

Für die Dienste werden Test bereitgestellt, mit denen sie die Funktionsfähigkeit ihrer Lösungen überprüfen können.
Bedenken Sie aber, dass eine erfolgreiche Ausführung aller Tests nicht automatisch die Korrektheit Ihrer Lösung
sicherstellt. Tests können immer nur die Anwesenheit von Fehlern zeigen, nicht aber deren Abwesenheit. Das liegt daran,
dass Tests im Allgemeinen nicht vollständig alle Fehlersituationen abdecken. In der Bewertung Ihrer Lösung ist daher der
erfolgreiche Durchlauf aller Tests eine notwendige Bedingung zum Erreichen der vollen Punktzahl. Es kann aber trotzdem
Punktabzug geben, falls Ihre Lösung Fehler enthält, die durch die Tests nicht aufgedeckt werden.

### Bonuspukte

Im Laufe des Semesters kann jede Gruppe einmalig 2 Bonuspunkte durch die Präsentation von Lösungsbestandteilen der Übung
2 oder 3 erhalten. Es gelten folgende Bedingungen

- Die Punkte können als Ausgleich für Punktabzüge bei den abgegeben Lösungen dienen
- Sie können trotzdem nur insgesamt 40 Punkte in den Übungen erreichen
- Sollte die Summe regulärer Punkte plus Bonuspunkte 40 übersteigen wird auf 40 gekappt

### Übung 1

Diese Übung dient zur Wiederholung von SQL und zur Einarbeitung in die Struktur der Daten.
Die Bearbeitung dieser Übung ist freiwillig, eine Abgabe der Lösungen ist nicht erforderlich.

- SQL-Aufgaben
  [(link)](https://github.com/ic-htw/dbtech-vers/blob/main/db/aufgaben/ue01/vers-sql.pdf)

### Übung 2

Diese Übung dient zur Implementierung einfacher Dienste in Java. Dabei geht es im Wesentlichen um die Verwaltung der
Daten.

```java
public interface IVersicherungJdbc {
  List<String> kurzBezProdukte();
  Kunde findKundeById(Integer id);
  void createVertrag(Integer id, Integer produktId, Integer kundenId, LocalDate versicherungsbeginn);
  BigDecimal calcMonatsrate(Integer vertragsId);
}
```

Details zu den Diensten finden sie hier
[(link)](https://github.com/ic-htw/dbtech-vers/blob/main/javasrc/de/htwberlin/dbtech/aufgaben/ue02/IVersicherungJdbc.java)

### Übung 3

Diese Übung dient zur Implementierung eines komplexen Dienstes in Java. Dabei geht es um die Festlegung von Versicherungsbedingungen, z.B. die Deckung bestimmter Risiken.

```java
public interface IVersicherungService {
  void createDeckung(Integer vertragsId, Integer deckungsartId, BigDecimal deckungsbetrag);
}
```

Details zu dem Dienst finden sie hier
[(link)](https://github.com/ic-htw/dbtech-vers/blob/main/javasrc/de/htwberlin/dbtech/aufgaben/ue03/IVersicherungService.java)

Hinweise zur Implementierung der Ablehnungsregeln finden sie hier [(pdf)](https://github.com/ic-htw/dbtech-vers/blob/main/doc/ablehnungsregeln.pdf)

Dieser Dienst soll in zwei Versionen implementiert werden:

1. Implementierung wie bei Übung 2 in einer Klasse ```VersicherungService``` 
2. Implementierung auf Grundlage eines Architekturmusters (Table-Data-Gateway, Row-Data-Gateway oder Data-Mapper) in
   einer Klasse ```VersicherungServiceDao``` und zusätzlichen Interfaces und Klassen zur Implementierung der DAOs

### Übung 4

Diese Übung dient zur Implementierung eines komplexen Dienstes innerhalb des Datenbanksystems mit PL/SQL. Dabei soll der
Dienst aus Übung 3 in diesem neuen Umfeld reimplementiert werden.

```sql
create or replace package versicherungsservice as
  exc_data exception;
  pragma exception_init(exc_data, -20001);
  exc_vertrag_existiert_nicht exception;
  pragma exception_init(exc_vertrag_existiert_nicht, -20002);
  exc_deckungsart_existiert_nicht exception;
  pragma exception_init(exc_deckungsart_existiert_nicht, -20003);
  exc_ungueltiger_deckungsbetrag exception;
  pragma exception_init(exc_ungueltiger_deckungsbetrag, -20004);
  exc_deckungsart_passt_nicht_zu_produkt exception;
  pragma exception_init(exc_deckungsart_passt_nicht_zu_produkt, -20005);
  exc_deckungsart_nicht_regelkonform exception;
  pragma exception_init(exc_deckungsart_nicht_regelkonform, -20006);
  exc_deckungspreis_nicht_vorhanden exception;
  pragma exception_init(exc_deckungspreis_nicht_vorhanden, -20007);

  procedure create_deckung (
    p_vertrags_id vertrag.id%type,
    p_deckungsart_id deckungsart.id%type,
    p_deckungsbetrag deckung.deckungsbetrag%type);
end versicherungsservice;
```

PL/SQL-Code zu dieser Übung finden sie hier
[(link)](https://github.com/ic-htw/dbtech-vers/tree/main/db/aufgaben/ue04)