## COMANDOS DE GIT (CONSEJOS):

# Lo normal es que tengamos una secuencia de trabajo muy mecánica para trabajar
# lo típico es :

# Consejos:
  1) Si pones las primeras letras de un comando y pressionas TAB se autocompleta
  2) Para cambiar de directorio usa el comando: $ cd [nombre de directorio]
  3) Para ver un listado de los ficheros que hay en el Directorio actual usa: $ ls
  4) Para usar comandos de git (excepto clone); debes estar Dentro de la Carpeta del Proyecto descargado
  
# Sobre comandos:
# Si no Tenemos el reposiorio en local, Lo clonamos:
  $ git clone [url del repositorio]

# Nos aseguramos de estar, en la rama correcta NUNCA EN MASTER:
# si no lo estamos cambiamos de rama:
  $ git checkout [nombre de la rama]
  
# Si ya tengo el repo en local, Hacemos un pull para descargar los últimos cambios:
  $ git pull  
  
# Cierra la consola y Realiza tus cambios desde fuera, ya puedes tocarlo con RSA o abrir ficheros 
# de cualquier forma y cambiarlos, copiarlos, cortarlos pegarlos, etc.

# Una vez realizados tus cambios entra en la consola
# pasa tus Cambios a la Zona Verde:
  $ git add .

# Puedes asegurar el estado de tu repositorio usando:
  $ git status

# HAZ UN PULL PARA ASEGURARTE DE QUE TIENES TODOS LOS ULTIMOS CAMBIOS DESCARGADOS
# si no te deja, hazlo después del commit (provoca un merge); pero siempre antes del push
  $ git pull
  
# Realiza el commit para apuntar el Head a tus cambios:
  $ git commit -m "mensaje diciendo lo que has hecho"

# Finalmente cuando estés seguro de lo que vas a subir, haz un push
  $ git push

  
  
