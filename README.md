Energy Detection
================

Il sistema fa uso di **Java 8** ed è stato testato con la versione 1.8.0_45

Compilazione
------------

È stato realizzato un makefile, per compilare il programma è sufficiente lanciare `make` e `make clean` per eliminare i file compilati.

Configurazione
--------------

I parametri del sistema sono nel file **settings.conf**. È necessario specificare il percorso dei file contenenti i segnali da analizzare prima di avviare il programma.

Esecuzione
----------

È possibile eseguire direttamente il main all'interno di *it.uniroma3.sdr.Main* oppure lanciare `make run` che eseguirà prima la compilazione e dopo il programma.
