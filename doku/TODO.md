# TODO

1. Nachdem die Möglichkeiten an den entsprechenden Spieler geschickt wurden,
 sollte diese Methode pausieren und auf die Eingabe des Spielers warten. (Fehlt noch)
Klickt man eine mögliche Figur auf der Oberfläche an, sucht die SpielfeldCanvas 
die passende Figur im Spielfeld und ruft im ClientNetworkService die Methode 
"sendeFigur(f)" auf.
 
2. Nun muss auf der Serverseite die Daten empfangen werden und die Methode 
"bewegeFigur(f)" aufgerufen werden.
In der Methode "ServerController.bewegeFigur(f) wird dann der NetworkService 
mit der Methode "sendeFigurAnClients(figur)" aufgerufen. Die Figur sollte 
anschließend an alle Clients verteilt werden?! Bei den Clients in der Methode 
"aktualisiereSpielfeld(String ausgewaehlteFigurId, int neuePosition)" wird die 
Figur auf die neue Position gesetzt. Sollte an dieser Stelle eine Figur stehen, 
wird diese auf das entsprechende Homefield gesetzt.
Zudem wurde eine Überprüfung des Spielendes hoffentlich richtig implementiert?!?

3. Es müssen noch die Regeln implementiert werden (ServerController.ueberpruefeMoeglichkeiten)
4. Der Gewinner (in der Methode ServerController.starteSpiel) sollte noch auf 
der Oberfläche angezeigt werden.

5. (optional) Es wird auf der Oberfläche die Würfelanzahl angezeigt, 
dies könnte verschönert werden


* Christian 1,2
* Dominik 3
* Max 4(,5)
