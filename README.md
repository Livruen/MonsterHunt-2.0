# MonsterHunt 2.0

![MonsterHunt 2.0 – ASCII-Cover mit Krieger, Magier und Kraken](assets/monsterhunt-cover.png)

Ein Java-Konsolen-Rollenspiel von **Livruen Nati**. Wähle einen Krieger oder Magier, erkunde ein zufällig erzeugtes Labyrinth und stelle dich dem Kraken. Textgrafiken, Kämpfe, Gegenstände, Handel und Quests begleiten die Reise.

Das Projekt entstand vor rund 15 Jahren als Lernprojekt und wird heute wieder für die Vorführung im Unterricht aufbereitet. Das Cover orientiert sich an den ursprünglichen ASCII-Zeichnungen des Spiels. Das Spiel selbst zeigt Karte, Figuren und Menüs als Textgrafik in der Konsole.

[Zum ursprünglichen Quellcode auf GitHub](https://github.com/Livruen/MonsterHunt-2.0)

## Was das Projekt enthält

- Zwei spielbare Figuren mit eigenen Startwerten und Gegenständen.
- Ein zufällig erzeugtes Labyrinth mit Gegnern, Händler, Questgeber, Heilbrunnen und Endgegner.
- Rundenbasierte Kämpfe mit Angriff, Tränken und Flucht.
- Ein Inventar für Tränke, Bomben, Ausrüstung und weitere Gegenstände.
- Handel und Quests mit Gegenständen und Belohnungen.
- Speichern und Laden eines lokalen Spielstands.

## Voraussetzungen

Für dieses Demo-Paket brauchst du **Java 21 oder neuer**. Empfohlen ist das kostenlose **Temurin JDK 21**. Das JDK enthält die Werkzeuge zum Ausführen und Kompilieren.

1. Öffne [Temurin JDK 21 herunterladen](https://adoptium.net/temurin/releases?version=21).
2. Wähle dein Betriebssystem und die passende Prozessorarchitektur. Für einen üblichen Windows-PC sind das **Windows**, **x64**, **JDK** und **MSI**.
3. Installiere das JDK und aktiviere dabei das Hinzufügen zu **PATH**.
4. Öffne nach der Installation ein neues Terminal und prüfe die Installation mit den folgenden Befehlen.

```text
java -version
javac -version
```

Beide Ausgaben sollten die Version 21 oder höher nennen.

## Unter Windows starten

1. Entpacke `MonsterHunt-2.0-Demo.zip` vollständig.
2. Öffne den entpackten Projektordner.
3. Starte `Start-MonsterHunt.cmd` per Doppelklick.

Für die Vorführung genügt das installierte Java. Die beiliegende JAR lässt sich auch über ein Terminal im Projektordner starten.

```text
java -jar MonsterHunt.jar
```

Dieser Befehl funktioniert auch unter Linux und macOS. Führe ihn im Ordner aus, in dem `MonsterHunt.jar`, `Title.txt` und `questFile.txt` liegen. Das Spiel liest seine Textdateien aus dem aktuellen Arbeitsverzeichnis.

## Spielen

Jede Auswahl und jeden Bewegungsbefehl mit **Enter** bestätigen. Die Eingabe wird zeilenweise gelesen.

| Situation | Eingabe | Aktion |
| --- | --- | --- |
| Hauptmenü | `n` | Neues Spiel starten |
| Figurenauswahl | `0` | Krieger wählen |
| Figurenauswahl | `1` | Magier wählen |
| Figurenauswahl | `2` | Beide Figuren anzeigen |
| Karte | `w`, `a`, `s`, `d` | Nach oben, links, unten, rechts gehen |
| Karte | `i` | Inventar und Werte öffnen |
| Karte | `o` | Spielstand speichern |
| Kampf | `1` | Angreifen |
| Kampf | `2` | Tränkmenü öffnen |
| Kampf | `3` | Flucht wählen |
| Inventar | `u` | Gegenstand anhand seiner angezeigten Nummer auswählen |
| Menüs | `e` | Das aktuelle Menü verlassen |
| Karte | `e` | Zurück zum Hauptmenü, beim neuen Spiel folgt eine Speicherfrage |
| Hauptmenü | `l` | Gespeichertes Spiel laden |
| Hauptmenü | `e` | Programm beenden |

Händler und Questgeber öffnen eigene Menüs, wenn du das entsprechende Feld ansteuerst. Die Karte enthält eine Legende für die verwendeten Zeichen.

## Speichern und Laden

`o` speichert auf der Karte in `gameSave1.ser` im Projektordner. Das Paket verwendet einen Speicherplatz. Erneutes Speichern ersetzt den bisherigen Stand. Nach dem Verlassen eines neu gestarteten Spiels lässt sich die Speicherfrage mit `y` bestätigen.

Wähle im Hauptmenü `l`, um den gespeicherten Stand fortzusetzen. Das Demo-Paket startet ohne Spielstand. Beim ersten Start wählst du `n`. Ein fehlender oder inkompatibler Spielstand führt mit einer Meldung zurück ins Hauptmenü.

## Waffen und Rüstung

Der angezeigte Angriff und der Schaden eines erfolgreichen Angriffs enthalten den Waffenbonus und einen verfügbaren Magiebonus. Der Basisangriff bleibt beim Wechseln der Waffe erhalten.

| Figur | Basisangriff | Waffe | Waffenbonus | Magiebonus mit Mana | Gesamtangriff |
| --- | --- | --- | --- | --- | --- |
| Krieger | 9 | Knife | 2 | 0 | 11 |
| Krieger | 9 | Fist of Blod | 8 | 0 | 17 |
| Magier | 7 | Simple Stick | 2 | 10 | 19 |
| Magier | 7 | Fist of Blod | 8 | 10 | 25 |

Der Magier startet mit 43 Lebenspunkten einschließlich Startrüstung und 60 Mana. Der Krieger startet mit 63 Lebenspunkten. Als erste Balance-Anpassung erhält der Magier einen Bonus von 10 Schaden, sobald mindestens 10 Mana verfügbar sind. Ein erfolgreicher Zaubertreffer verbraucht 10 Mana. Ein verfehlter Angriff verbraucht kein Mana. Bei weniger als 10 Mana greift der Magier mit Basisangriff und Waffenbonus an. Mit dem Startstab sind das 9 Schaden. Das Kampfmenü zeigt diesen Übergang an.

Ein Manatrank stellt bis zu 20 Mana wieder her, höchstens bis zum Maximum von 60. Ein Heiltrank stellt bis zu 20 Lebenspunkte wieder her, höchstens bis zum aktuellen Lebenspunkte-Maximum. Die Tränke werden im Menü getrennt verbraucht. Die Anzeige nennt die tatsächlich aufgefüllten Punkte.

Im Inventar werden Waffen mit `ATK +…` angezeigt. Rüstungen erhöhen in diesem Spiel die maximalen Lebenspunkte und tragen die Anzeige `MAXHP +…`. Beim Ausrüsten wandert die bisherige Ausrüstung zurück ins Inventar. Das funktioniert auch bei einem vollen Inventar. Beim Wechsel auf eine schwächere Rüstung werden die aktuellen Lebenspunkte auf das neue Maximum begrenzt.

Beim ersten Laden eines Spielstands aus der vorherigen Vorführversion wird der durch Waffenwechsel beschädigte Basisangriff auf die ursprünglichen Spielwerte gesetzt. Diese betragen 9 beim Krieger und 7 beim Magier. Ausrüstung, Gold und andere gespeicherte Fortschritte werden übernommen.

## Quellcode in Eclipse öffnen

1. Installiere [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/packages/).
2. Wähle **File → Import → General → Existing Projects into Workspace**.
3. Wähle den entpackten Projektordner als **Root Directory** und bestätige mit **Finish**.
4. Verwende für das Projekt ein installiertes **JDK 21 oder neuer**. Bei Bedarf findest du es unter **Window → Preferences → Java → Installed JREs**.
5. Öffne `src/Start.java` und wähle **Run As → Java Application**.
6. Verwende den Projektordner als Arbeitsverzeichnis. Die Standardkonfiguration einer Java-Anwendung in Eclipse verwendet diesen Ordner. Bei Bedarf kannst du ihn in **Run Configurations → Arguments → Working directory** auswählen.

Die Java-Quellen liegen unter `src/`. Die Eclipse-Projekteinstellung verwendet diesen Ordner zum Kompilieren. `MonsterHunt.jar` ist die vorkompilierte Vorführversion. Änderungen am Quellcode lassen sich direkt über Eclipse starten.

## Orientierung im Quellcode

| Datei oder Verzeichnis | Aufgabe |
| --- | --- |
| `src/Start.java` | Einstiegspunkt und Hauptmenü |
| `src/GameBoard.java` | Spielablauf, Kämpfe und Spielstand |
| `src/Maze/` | Labyrinth, Karte und Bewegungsrichtungen |
| `src/Characters/` | Spielfiguren, Gegner und Verhalten |
| `src/Items/` und `src/Inventory/` | Gegenstände und Inventar |
| `src/Shop/` und `src/Quest/` | Händler und Quests |
| `src/gameIO/` | Konsoleneingaben und Einlesen von Textdateien |
| `*.txt` im Projektordner | Textgrafiken, Menüs und Questdaten |

## Stand dieser Vorführversion

Die Vorführversion wurde für Java 21 aufbereitet. Zu den Korrekturen gehören die Eclipse-Quellordner, die Konsoleneingabe, das Speichern der Verhaltensobjekte und die Behandlung inkompatibler Spielstände. Außerdem wurden Fehler bei aufeinanderfolgenden Kämpfen, Inventareingaben, Waffenboni und dem Ausrüstungswechsel korrigiert. Angriffsanzeige und Kampfschaden verwenden jetzt denselben Gesamtangriff. Die Magie wurde in die normale Schadensberechnung eingebunden. Der Magier verursacht mit Startausrüstung und ausreichend Mana jetzt 19 Schaden pro Treffer. Ein Fehler, der beim Heiltrank gleichzeitig einen Manatrank verbrauchte, ist korrigiert. Rüstungswechsel und die Übernahme älterer Spielstände wurden ebenfalls geprüft.

Unter Linux mit Java 21 wurden die Kompilierung, beide Figuren, das Inventar, aufeinanderfolgende Kämpfe und Speichern und Laden nach einem Programmneustart geprüft. Zusätzlich wurden der tatsächliche Schaden mit Messer und Fist of Blod, wiederholte Waffen- und Rüstungswechsel bei vollem Inventar sowie die Übernahme bestehender Spielstände getestet. Ein Test über das Konsolenmenü prüft den Ablauf vom Monster-Drop über das Ausrüsten bis zum erneuten Laden. Weitere Tests prüfen sechs Zaubertreffer aus 60 Mana, den Angriff bei zu wenig Mana, verfehlte Angriffe, getrennten Trankverbrauch und das Laden vorhandener Magier-Spielstände. Die Balance über einen vollständigen Spieldurchlauf ist noch zu prüfen. Der Windows-Starter ist beigefügt. Seine Ausführung unter Windows und ein vollständiger Spieldurchlauf bis zum Endgegner sind noch offen.

Die beiliegenden Quellen und die JAR gehören zur aufbereiteten Vorführversion. Der verlinkte GitHub-Stand ist das ursprüngliche Projekt.

## Autorin

Livruen Nati · Natasza Szczypien

[github.com/Livruen/MonsterHunt-2.0](https://github.com/Livruen/MonsterHunt-2.0)
