### Escuela Colombiana de Ingeniería

### INTRODUCCIÓN A PROYECTOS WEB

### LABORATORIO 5 - MVC PRIMEFACES INTRODUCTION

## Juan David Martinez Cardozo - Jhon Sebastian Piñeros 

## PARTE I. - JUGANDO A SER UN CLIENTE HTTP
1. Abra una terminal Linux o consola de comandos Windows.
2. Realice una conexión síncrona TCP/IP a través de Telnet/Netcat al siguiente servidor
	Host: www.escuelaing.edu.co
	Puerto: 80
Teniendo en cuenta los parámetros del comando telnet:

telnet HOST PORT
3. Antes de que el servidor cierre la conexión por falta de comunicación:

- Revise la página 36 del RFC del protocolo HTTP, sobre cómo realizar una petición GET. Con esto, solicite al servidor el recurso ‘sssss/abc.html’, usando la versión 1.0 de HTTP.

- Asegúrese de presionar ENTER dos veces después de ingresar el comando.
[![lab51.jpg](https://i.postimg.cc/zvfbjvZB/lab51.jpg)](https://postimg.cc/Fd2HF9K5)

- Revise el resultado obtenido. ¿Qué codigo de error sale?, revise el significado del mismo en la lista de códigos de estado HTTP.
RTA: Salio el codigo de error 301:Moved Permanently y este hace referncia a que la solicitud enviada y todas las que se hagan en un futuro deben dirigirse al URI dado.

- ¿Qué otros códigos de error existen?, ¿En qué caso se manejarán?
RTA: Existen varios codigos de error como por ejemplo:
- 1xx Respuestas informativas: La solicitud fue recibida, proceso continuo
- 2xx Peticiones correctas: La solicitud fue recibida, comprendida y aceptada con éxito
- 3xx Redirecciones: Se deben tomar más medidas por parte del cliente para completar la solicitud.
- 4xx Errores del cliente: La solicitud contiene una sintaxis incorrecta o no se puede procesarse
- 5xx Errores de servidor: El servidor falló al completar una solicitud aparentemente válida


4. Realice una nueva conexión con telnet, esta vez a:
- Host: www.httpbin.org
- Puerto: 80
- Versión HTTP: 1.1

Ahora, solicite (GET) el recurso /html. ¿Qué se obtiene como resultado?

[![lab52.jpg](https://i.postimg.cc/wMzFThmr/lab52.jpg)](https://postimg.cc/njdq39TK)

¡Muy bien!, ¡Acaba de usar del protocolo HTTP sin un navegador Web!. Cada vez que se usa un navegador, éste se conecta a un servidor HTTP, envía peticiones (del protocolo HTTP), espera el resultado de las mismas, y -si se trata de contenido HTML- lo interpreta y dibuja.

5. Seleccione el contenido HTML de la respuesta y copielo al cortapapeles CTRL-SHIFT-C. Ejecute el comando wc (word count) para contar palabras con la opción -c para contar el número de caracteres:
wc -c 
Pegue el contenido del portapapeles con CTRL-SHIFT-V y presione CTRL-D (fin de archivo de Linux). Si no termina el comando wc presione CTRL-D de nuevo. No presione mas de dos veces CTRL-D indica que se termino la entrada y puede cerrarle la terminal. Debe salir el resultado de la cantidad de caracteres que tiene el contenido HTML que respondió el servidor.

Claro está, las peticiones GET son insuficientes en muchos casos. Investigue: ¿Cuál es la diferencia entre los verbos GET y POST? ¿Qué otros tipos de peticiones existen?

[![lab53.jpg](https://i.postimg.cc/TYVj3L28/lab53.jpg)](https://postimg.cc/SJKYZKzV)

Diferencia GET - POST: En el tipo de petición GET los parámetros URL se guardan junto al URL mientras que con POST esto no pasa, ya que este ofrece mayor discreción debido a que tampoco se guardan los parámetros URL en el caché ni en el registro del servidor caso contrario en GET que se guardan sin cifrar.
Otro tipo de peiticiones:

- DELETE elimina en la ubicación de destino

- HEAD solo revisa los headers

- PUT deposita en la ubicación de destino

6. En la practica no se utiliza telnet para hacer peticiones a sitios web sino el comando curl con ayuda de la linea de comandos:

curl www.httpbin.org
[![1000.png](https://i.postimg.cc/SNfZttMz/1000.png)](https://postimg.cc/MvXbn5tW)

El curl trae el html

Utilice ahora el parámetro -v y con el parámetro -i:

curl -v www.httpbin.org
[![1001.png](https://i.postimg.cc/rFjJ90B1/1001.png)](https://postimg.cc/9r4ZmMF0)
curl -i www.httpbin.org
[![1002.png](https://i.postimg.cc/7LGnDBf9/1002.png)](https://postimg.cc/nsxDGGK9)

¿Cuáles son las diferencias con los diferentes parámetros?
RTA: La diferencia esque con curl -v se establece la conexion con el servidor y arrastra el html, mientras que con curl -i arrastra el html y adicionalmente muestra las caracteristicas del mismo

## PARTE II. - HACIENDO UNA APLICACIÓN WEB DINÁMICA A BAJO NIVEL.

En este ejercicio, va a implementar una aplicación Web muy básica, haciendo uso de los elementos de más bajo nivel de Java-EE (Enterprise Edition), con el fin de revisar los conceptos del protocolo HTTP. En este caso, se trata de un módulo de consulta de clientes Web que hace uso de una librería de acceso a datos disponible en un repositorio Maven local.

1. Para esto, cree un proyecto maven nuevo usando el arquetipo de aplicación Web estándar maven-archetype-webapp y realice lo siguiente:

[![lab54.jpg](https://i.postimg.cc/tCvrjnBX/lab54.jpg)](https://postimg.cc/N5T88FhV)
[![lab55.jpg](https://i.postimg.cc/0jWTMkqD/lab55.jpg)](https://postimg.cc/fSd21hyR)

Revise la clase SampleServlet incluida a continuacion, e identifique qué hace:
RTA: La clase SampleServlet extiende de una clase que se va a encargar de el protocolo http

Revise qué valor tiene el parámetro ‘urlPatterns’ de la anotación @WebServlet, pues este indica qué URLs atiende las peticiones el servlet.
RTA: El parametro urlPatterns es la que va a almacenar la ruta URL para usar el protocolo http

2. En el pom.xml, modifique la propiedad "packaging" con el valor "war". Agregue la siguiente dependencia:

3. Revise en el pom.xml para qué puerto TCP/IP está configurado el servidor embebido de Tomcat (ver sección de plugins).
RTA: El puerto es el 8080

4. Compile y ejecute la aplicación en el servidor embebido Tomcat, a través de Maven con:

mvn package
mvn tomcat7:run

5. Abra un navegador, y en la barra de direcciones ponga la URL con la cual se le enviarán peticiones al ‘SampleServlet’. Tenga en cuenta que la URL tendrá como host ‘localhost’, como puerto, el configurado en el pom.xml y el path debe ser el del Servlet. Debería obtener un mensaje de saludo.

[![lab56.jpg](https://i.postimg.cc/QdW2CZYK/lab56.jpg)](https://postimg.cc/4YsL2qgX)

6. Observe que el Servlet ‘SampleServlet’ acepta peticiones GET, y opcionalmente, lee el parámetro ‘name’. Ingrese la misma URL, pero ahora agregando un parámetro GET (si no sabe como hacerlo, revise la documentación en http://www.w3schools.com/tags/ref_httpmethods.asp).

[![lab57.jpg](https://i.postimg.cc/LspbsYZG/lab57.jpg)](https://postimg.cc/tsB5Sgkz)

7. Busque el artefacto gson en el repositorio de maven y agregue la dependencia.

8. En el navegador revise la dirección https://jsonplaceholder.typicode.com/todos/1. Intente cambiando diferentes números al final del path de la url.

[![lab58.jpg](https://i.postimg.cc/WpFCXvjW/lab58.jpg)](https://postimg.cc/3Whtxzkm)

[![lab59.jpg](https://i.postimg.cc/nVBTXcnJ/lab59.jpg)](https://postimg.cc/QVNcPDB4)

[![lab60.jpg](https://i.postimg.cc/gcVqYSKc/lab60.jpg)](https://postimg.cc/8sCr4b3x)

9. Basado en la respuesta que le da el servicio del punto anterior, cree la clase edu.eci.cvds.servlet.model.Todo con un constructor vacío y los métodos getter y setter para las propiedades de los "To Dos" que se encuentran en la url indicada.

10. Utilice la siguiente clase para consumir el servicio que se encuentra en la dirección url del punto anterior:

11. Cree una clase que herede de la clase HttpServlet (similar a SampleServlet), y para la misma sobrescriba el método heredado doGet. Incluya la anotación @Override para verificar –en tiempo de compilación- que efectivamente se esté sobreescribiendo un método de las superclases.

12. Para indicar en qué URL el servlet interceptará las peticiones GET, agregue al método la anotación @WebServlet, y en dicha anotación, defina la propiedad urlPatterns, indicando la URL (que usted defina) a la cual se asociará el servlet.

13. Teniendo en cuenta las siguientes métodos disponibles en los objetos ServletRequest y ServletResponse recibidos por el método doGet:

14. Una vez hecho esto, verifique el funcionamiento de la aplicación, recompile y ejecute la aplicación.

15. Intente hacer diferentes consultas desde un navegador Web para probar las diferentes funcionalidades.

Funcionando 
[![lab61.jpg](https://i.postimg.cc/YqtVkmYV/lab61.jpg)](https://postimg.cc/MXrtmHsb)
[![lab62.jpg](https://i.postimg.cc/R0JpG1x3/lab62.jpg)](https://postimg.cc/PLhM5wxh)

Sin parametros
[![lab63.jpg](https://i.postimg.cc/q7mntS5y/lab63.jpg)](https://postimg.cc/2qZ3McmS)


No encontrado
[![lab64.jpg](https://i.postimg.cc/jSmZWWnR/lab64.jpg)](https://postimg.cc/v47WkZvK)


## PARTE III

16. En su servlet, sobreescriba el método doPost, y haga la misma implementación del doGet.

17. Cree el archivo index.html en el directorio src/main/webapp/index.html de la siguiente manera

18. En la página anterior, cree un formulario que tenga un campo para ingresar un número (si no ha manejado html antes, revise http://www.w3schools.com/html/ ) y un botón. El formulario debe usar como método ‘POST’, y como acción, la ruta relativa del último servlet creado (es decir la URL pero excluyendo ‘http://localhost:8080/’).

19. Revise este ejemplo de validación de formularios con javascript y agruéguelo a su formulario, de manera que -al momento de hacer ‘submit’- desde el browser se valide que el valor ingresado es un valor numérico.

20. Recompile y ejecute la aplicación. Abra en su navegador en la página del formulario, y rectifique que la página hecha anteriormente sea mostrada. Ingrese los datos y verifique los resultados. Cambie el formulario para que ahora en lugar de POST, use el método GET . Qué diferencia observa?

Post: 
[![lab65.jpg](https://i.postimg.cc/jS1ntPw4/lab65.jpg)](https://postimg.cc/nXvh1s1X)

[![lab66.jpg](https://i.postimg.cc/pTb9nvB9/lab66.jpg)](https://postimg.cc/Y4R2JJZk)

Get:
[![lab67.jpg](https://i.postimg.cc/XqnpQgm4/lab67.jpg)](https://postimg.cc/DJYvz17M)

[![lab68.jpg](https://i.postimg.cc/kMyVDkR4/lab68.jpg)](https://postimg.cc/w37xG4V8)

Diferencia entre GET - POST: Con POST no se permite visualizar los parámetros en el URL mientras que con GET si se pueden ver.

21. ¿Qué se está viendo? Revise cómo están implementados los métodos de la clase Service.java para entender el funcionamiento interno.

RTA: Se crea una tabla de manera automatica en formato html utilizando los parametros de los getter.

## PARTE IV. - FRAMEWORKS WEB MVC – JAVA SERVER FACES / PRIME FACES

En este ejercicio, usted va a desarrollar una aplicación Web basada en el marco JSF, y en una de sus implementaciones más usadas: PrimeFaces. 

Escriba una aplicación web que utilice PrimeFaces para calcular la media, la moda, la desviación estándar y varianza de un conjunto de N números reales. Este conjunto de N números reales deben ser ingresados por el usuario de manera que puedan ser utilizados para los cálculos.

1. Al proyecto Maven, debe agregarle las dependencias mas recientes de javax.javaee-api, com.sun.faces.jsf-api, com.sun.faces.jsf-impl, javax.servlet.jstl y Primefaces (en el archivo pom.xml).

2. Para que configure automáticamente el descriptor de despliegue de la aplicación (archivo web.xml), de manera que el framework JSF se active al inicio de la aplicación, en el archivo web.xml agregue la siguiente configuración:

3. Revise cada una de las configuraciones agregadas anteriormente para saber qué hacen y por qué se necesitan. Elimine las que no se necesiten.

4. Ahora, va a crear un Backing-Bean de sesión, el cual, para cada usuario, mantendrá de lado del servidor las siguientes propiedades:

- El conjunto de datos ingresados por el usuario.

- Los resultados de las operaciones.

- La cantidad de números ingresados por el usuario.


5. Cree una página XHTML, de nombre calculadora.xhtml (debe quedar en la ruta src/main/webapp). Revise en la página 13 del manual de PrimeFaces, qué espacios de nombres XML requiere una página de PrimeFaces y cuál es la estructura básica de la misma.

6. Con base en lo anterior, agregue un formulario con identificador calculadora_form con el siguiente contenido básico:
	<h:body>
 	<h:form id="calculadora_form">

 	</h:form>
	</h:body>

7. Al formulario, agregue:

- Un elemento de tipo <p:outputLabel> para el resultado de la moda, sin embargo, este elemento se debe ocultar. Para ocultarlo, se puede agregar el estilo display: none; al elemento. Una forma de hacerlo es por medio de la propiedad style.
En una aplicacion real, no se debería tener este elemento, solo se crea con el fin de simplificar una prueba futura.
- Un elemento <p:inputText>para que el usuario ingrese los números. (Tenga en cuenta que una opción para separar los números es con “;” aunque no necesariamente debe hacerlo así) 

 Por ejemplo:

 2; 3.5; 4.8; 5.1

- Un elemento de tipo <p:outputLabel> para mostrar cada una de las operaciones resultantes. Y asocie dichos elementos al BackingBean de sesión a través de su propiedad value, y usando como referencia el nombre asignado: value="#{calculadoraBean.nombrePropiedad}"

8. Al formulario, agregue dos botones de tipo <p:commandButton>, cuatro para enviar la lista de números ingresados y ver el calculo de cada valor, y otro para reiniciar el juego.

9. Para verificar el funcionamiento de la aplicación, agregue el plugin tomcat-runner dentro de los plugins de la fase de construcción (build). Tenga en cuenta que en la configuración del plugin se indica bajo que ruta quedará la aplicación:

10. Si todo funcionó correctamente, realice las siguientes pruebas:

a. Abra la aplicación en un explorador. Realice algunas pruebas de aceptación con la aplicación.

[![Whats-App-Image-2022-03-04-at-11-20-49-PM.jpg](https://i.postimg.cc/wxNCVpQq/Whats-App-Image-2022-03-04-at-11-20-49-PM.jpg)](https://postimg.cc/w364qnrZ)

[![Whats-App-Image-2022-03-04-at-11-21-19-PM.jpg](https://i.postimg.cc/T3tFwYf9/Whats-App-Image-2022-03-04-at-11-21-19-PM.jpg)](https://postimg.cc/CRfmr0ZB)

[![Whats-App-Image-2022-03-04-at-11-21-56-PM.jpg](https://i.postimg.cc/sxP0R3T5/Whats-App-Image-2022-03-04-at-11-21-56-PM.jpg)](https://postimg.cc/q6RGsHV7)

b. Abra la aplicación en dos computadores diferentes. Si no dispone de uno, hágalo en dos navegadores diferentes (por ejemplo Chrome y Firefox; incluso se puede en un único navegador usando una ventana normal y una ventana de incógnito / privada). Haga cinco intentos en uno, y luego un intento en el otro. ¿Qué valor tiene cada uno?

[![Whats-App-Image-2022-03-04-at-11-25-04-PM.jpg](https://i.postimg.cc/QtQqF0c3/Whats-App-Image-2022-03-04-at-11-25-04-PM.jpg)](https://postimg.cc/jwjf968g)

c. Aborte el proceso de Tomcat-runner haciendo Ctrl+C en la consola, y modifique el código del backing-bean de manera que use la anotación @SessionScoped en lugar de @ApplicationScoped. Reinicie la aplicación y repita el ejercicio anterior.

[![Whats-App-Image-2022-03-04-at-11-31-20-PM.jpg](https://i.postimg.cc/KvqD3K3R/Whats-App-Image-2022-03-04-at-11-31-20-PM.jpg)](https://postimg.cc/cr3nVLmS)

Dado la anterior, ¿Cuál es la diferencia entre los backing-beans de sesión y los de aplicación?

RTA: AL diferencia esque backing-bean es cualquier bean referenciado por un formulario mientas por otro lado un managed bean es un bean de respaldo que ha sido registrado con JSF y se crea automáticamente por el mismo cuando se necesita.

d. Por medio de las herramientas de desarrollador del explorador (Usando la tecla "F12" en la mayoría de exploradores):
- Ubique el código HTML generado por el servidor.
- Busque el elemento oculto, que contiene el número generado aleatoriamente.
- En la sección de estilos, deshabilite el estilo que oculta el elemento para que sea visible.
- Observe el cambio en la página, cada vez que se realiza un cambio en el estilo.
- Revise qué otros estilos se pueden agregar a los diferentes elementos y qué efecto tienen en la visualización de la página.
- Actualice la página. Los cambios de estilos realizados desaparecen, pues se realizaron únicamente en la visualización, la respuesta del servidor sigue siendo la misma, ya que el contenido de los archivos allí almacenados no se ha modificado.
- Revise qué otros cambios se pueden realizar y qué otra información se puede obtener de las herramientas de desarrollador.

11. Para facilitar los intentos del usuario, se agregará una lista de los últimos valores ingresados:

- Agregue en el Backing-Bean, una propiedad que contenga una lista de valores ingresados por el usuario.

- Cuando se reinicie la aplicación, limpie el contenido de la lista.

- Busque cómo agregar una tabla a la página, cuyo contenido sea la lista de listas de números.




