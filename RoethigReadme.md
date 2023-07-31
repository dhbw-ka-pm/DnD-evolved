# Table of Contents

1.  [Gruppen-Mitglieder](#orge0ceed0)
2.  [Website](#org2bc2af4)
    1.  [Source Code](#org5355a5a)
        1.  [XSLT](#org4f5da97)
        2.  [XML Datenstrukturen](#org71a1b26)
3.  [Benotung](#orge4e0eb2)

<a id="orge0ceed0"></a>

# Gruppen-Mitglieder

- Jakob Fassunge
- Nils Bohland
- Margot Geisbauer
- Jan Deger
- Nick Möhrmann
- Tanina Blucha

<a id="org2bc2af4"></a>

# Website

<http://193.196.36.44/>

**Aufgrund der geringen Serverleistung der bwcloud, kann es passieren dass nach dem Löschen oder Hinzufügen von Karten die Website neu geladen werden muss um die Änderungen anzuzeigen**

Mit der Website sollen User die Möglichkeit haben Maps für ein DnD-Spiel anzulegen.
Dies können sie auf der rechten Seite der Website. Nachdem sie eine Map angelegt haben, wird diese mit Namen im Reiter angezeigt. Wenn sie auf den Namen einer Karte klicken, öffnet sich eine Ansicht in der Mitte des Fensters, welche die Karte mitsamt Event-Markern anzeigt. Auf der rechten Seite werden sie ein Panel zum Hinzufügen, Editieren, sowie Entfernen von Events für die aktuelle Karte finden.

Sie finden zwei Bilder welche sie als Karten verwenden können im Root Verzeichnis.

1.  ![img](fantasy-map-1.jpeg)
2.  ![img](fantasy-map-2.jpeg)

<a id="org5355a5a"></a>

## Source Code

Wir haben uns dazu entschieden im Backend mit Spring Boot zu Arbeiten und im Frontend mit Angular. Im folgenden wollen wir ihnen unsere Projekt-Struktur erklären.

<a id="org4f5da97"></a>

### XSLT

Wir haben uns dafür entschieden ihre Anforderungen bezüglich XSLT zu erfüllen, indem wir XSLT zum rendern der Karte mit Events verwenden.
Dazu haben wir eine Komponente geschrieben, welche das XSLT als String-literal enthält und mit Javascript in HTML transformiert. Die Komponente finden sie wie folgt:

<./angular-frontend/src/app/interactive-map/interactive-map.component.ts>

<a id="org71a1b26"></a>

### XML Datenstrukturen

Unsere XML Datenstrukturen finden sie unter folgende Verzeichnissen:

- [Map.xsd](backend/persistence/xmlDocs/schemas/Map.xsd)
- [Event.xsd](backend/persistence/xmlDocs/schemas/Event.xsd)

<a id="orge4e0eb2"></a>

# Benotung

Tanina hat sich Webengineering bereits anrechnen lassen.
