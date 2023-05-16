# BDD relacionals amb JDBC
  
## ACLARACIÓNS:
 Tens el SQL de una BD buida dintre del projecte en la Carpeta de SQL
 
 -Les **importacións** es fan dins del **codi**.
  
  -Hauras de descarregar la carpeta anomenada **"zips"** per poder **extreure** les dades.
  
  -**Degut al gran nombre de dades dins de les importacións**, si el que vols fer és un **INSERT**, et recomen per no haver d'estar buscant una fila que no estigui **duplicada**, que abans de fer les importacións primer fagis les sentencies **CRUD-INSERT** de les dades que vulguis.
  
  -Si vols crear la **BDD** de nou només hauras de executar l'script de mysql sencer. Això el que fara es **eliminar totes les dades i crear l'estructura de nou**.
  
  
  -Per posar la teva **connexió** hauras de modificar la següent **linea de codi** a la clase **Main**:
  
  ```java
  con = DriverManager.getConnection("jdbc:mysql://IP:3306/BDD", "usuari", "contrasenya");
  ```
  -**IP**: serà la IP de la teva maquina virtual.
  
  -**BDD**: serà la base de dades que vols utilitzar.
  
  -**usuari**: el teu usuari de mysql.
  
  -**contrasenya**: la contrasenya del teu mysql pel usuari.
  
  ## Pràctica realitzada per:
  -Sergi Sanahuja
  
  -Elyas El Jerari
