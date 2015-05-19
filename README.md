Energy Detection
================

Compilazione
------------

	make

Per compilare tutto il programma

	make clean
	
Per eliminare tutti i file compilati

Configurazione
--------------

I parametri del sistema sono scritti all'interno del file **settings.conf**. È necessario specificare all'interno di questo file il percorso dei file contenenti i segnali da analizzare.

Esecuzione
----------

È possibile eseguire direttamente il main all'interno di *it.uniroma3.sdr.Main* oppure lanciare il comando

	make run
	
che eseguirà prima la compilazione e dopo il programma.