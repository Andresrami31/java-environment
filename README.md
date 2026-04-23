# El Ahorcado en Java 17

# Integrantes

Niny Johana Parra Garcia
Elheim Oquendo 
Brayan Vargas 
Andres Ramirez 
Laura Merchan

## Descripción del proyecto

Este proyecto corresponde a una implementación del juego **El Ahorcado** (conocido también como **Hangman**) desarrollada en **Java 17** para ejecutarse por consola.

El sistema permite que un jugador intente adivinar una palabra secreta letra por letra, con un número limitado de intentos fallidos antes de perder. Incluye validaciones de entrada, detección de victoria, detección de derrota, visualización del ahorcado en ASCII, reinicio de partidas y un marcador acumulado.

La solución fue construida en una única clase `Main`, manteniendo buenas prácticas de desarrollo de software como:

- separación de responsabilidades mediante métodos pequeños,
- nombres descriptivos,
- validación de entradas,
- documentación con **Javadoc**,
- legibilidad y mantenibilidad del código.

---

## Objetivo del juego

El objetivo es **adivinar la palabra secreta** letra por letra antes de agotar los intentos disponibles.

El jugador gana si:
- descubre todas las letras de la palabra antes de cometer **6 errores**.

El jugador pierde si:
- acumula **6 letras incorrectas** y el ahorcado queda completo.

---

## Reglas del juego

Las reglas del juego son las siguientes:

1. El sistema selecciona aleatoriamente una **palabra secreta** de una lista predefinida.
2. La palabra se muestra como una secuencia de guiones bajos (`_`), uno por cada letra.
3. El jugador ingresa **una letra por turno**.
4. Si la letra pertenece a la palabra, se revela en su posición correspondiente.
5. Si la letra **no pertenece** a la palabra, se cuenta como un intento fallido.
6. No está permitido ingresar una letra ya utilizada anteriormente.
7. El jugador dispone de un máximo de **6 intentos fallidos**.
8. Gana el jugador que completa la palabra antes de agotar los intentos.
9. Si se alcanzan los 6 errores, la partida termina con derrota y se revela la palabra.
10. Al finalizar una partida, el sistema permite iniciar una nueva.
11. El sistema lleva un marcador acumulado de victorias y derrotas.

---

## Características implementadas

El programa incluye las siguientes funcionalidades:

- selección aleatoria de palabra secreta,
- visualización del ahorcado en ASCII según los errores cometidos,
- visualización de la palabra con letras descubiertas y guiones para las ocultas,
- visualización de letras ya utilizadas,
- ingreso de letras por parte del jugador,
- validación de entrada (solo letras, un carácter a la vez),
- validación de letra ya utilizada,
- detección de victoria,
- detección de derrota,
- revelación de la palabra al perder,
- reinicio del juego,
- marcador acumulado.

---

## Requisitos técnicos

Para ejecutar este proyecto se requiere:

- **Java Development Kit (JDK) 17** o superior,
- consola o terminal del sistema operativo,
- compilador `javac`,
- intérprete `java`.

Puedes verificar la instalación de Java con los siguientes comandos:

```bash
javac -version
java -version
```

---

## Compilación del proyecto

Ubícate en la carpeta donde se encuentra el archivo `Main.java` y ejecuta:

```bash
javac Main.java
```

Si la compilación es correcta, se generará el archivo:

```bash
Main.class
```

---

## Ejecución del proyecto

Para ejecutar el juego, usa el siguiente comando:

```bash
java Main
```

---

## Instrucciones de uso

Cuando el programa inicia, se mostrará un mensaje de bienvenida con las reglas del juego.

Durante cada turno:

1. El sistema mostrará el dibujo actual del ahorcado.
2. El sistema mostrará la palabra con las letras descubiertas y `_` en las posiciones ocultas.
3. El sistema mostrará las letras ya utilizadas.
4. El jugador deberá ingresar **una letra**.

### Ejemplo de uso

Si la palabra secreta es `JAVA` y el jugador ingresa `A`, el resultado será:

```text
_ A _ A
```

Si el jugador ingresa `Z` (letra incorrecta), el contador de errores aumenta en 1 y se dibuja la siguiente parte del ahorcado.

---

## Ejemplo visual del ahorcado

El ahorcado se dibuja progresivamente según los errores cometidos:

**0 errores:**
```text
  +---+
  |   |
      |
      |
      |
      |
=========
```

**1 error (cabeza):**
```text
  +---+
  |   |
  O   |
      |
      |
      |
=========
```

**2 errores (cabeza + cuerpo):**
```text
  +---+
  |   |
  O   |
  |   |
      |
      |
=========
```

**3 errores (brazo izquierdo):**
```text
  +---+
  |   |
  O   |
 /|   |
      |
      |
=========
```

**4 errores (ambos brazos):**
```text
  +---+
  |   |
  O   |
 /|\  |
      |
      |
=========
```

**5 errores (pierna izquierda):**
```text
  +---+
  |   |
  O   |
 /|\  |
 /    |
      |
=========
```

**6 errores (ahorcado completo — derrota):**
```text
  +---+
  |   |
  O   |
 /|\  |
 / \  |
      |
=========
```

---

## Validaciones realizadas por el sistema

El programa valida correctamente los siguientes casos:

### 1. Entrada vacía
Si el usuario no ingresa ningún carácter, el sistema muestra un mensaje de error y vuelve a solicitar la entrada.

### 2. Entrada con más de un carácter
Si el jugador ingresa más de una letra, el sistema rechaza la entrada y solicita un único carácter.

### 3. Entrada no alfabética
Si el jugador ingresa un número, símbolo o carácter especial, el sistema lo rechaza y solicita una letra válida.

### 4. Letra ya utilizada
Si el jugador intenta ingresar una letra que ya fue usada anteriormente, el sistema informa la situación y no cuenta como nuevo intento.

### 5. Continuidad de juego
Una vez que el jugador gana o acumula 6 errores, la partida termina y no se permiten más ingresos en esa ronda.

---

## Flujo general del programa

El flujo de ejecución del sistema es el siguiente:

1. Se muestra un mensaje de bienvenida.
2. Se selecciona aleatoriamente una palabra secreta.
3. Se inicializan el contador de errores, las letras usadas y la palabra oculta.
4. Se muestra el ahorcado inicial y la palabra con guiones.
5. El jugador ingresa una letra.
6. El sistema valida la entrada.
7. Si la letra es válida y no fue usada, se registra.
8. Si la letra pertenece a la palabra, se revela en la posición correspondiente.
9. Si la letra no pertenece a la palabra, se incrementa el contador de errores y se actualiza el dibujo.
10. El sistema verifica si el jugador ganó (todas las letras reveladas).
11. El sistema verifica si el jugador perdió (6 errores acumulados).
12. Si la partida no termina, se repite desde el paso 4.
13. Al finalizar, se actualiza el marcador y se revela la palabra si el jugador perdió.
14. El sistema pregunta si se desea jugar otra partida.
15. Si la respuesta es afirmativa, se reinicia el juego con una nueva palabra.
16. Si la respuesta es negativa, el programa finaliza.

---

## Documentación técnica

## Lenguaje y versión

- **Lenguaje:** Java
- **Versión utilizada:** Java 17

---

## Tipo de aplicación

- Aplicación de consola.
- Ejecución local.
- Interacción mediante entrada estándar (`Scanner`).

---

## Diseño de la solución

Aunque el requerimiento solicita que todo esté contenido en una única clase `Main`, el código fue organizado internamente siguiendo principios de diseño limpio mediante métodos específicos y responsabilidades separadas.

### Responsabilidades cubiertas por la clase `Main`

La clase principal se encarga de:

- iniciar la aplicación,
- seleccionar la palabra secreta,
- gestionar el estado del juego,
- controlar el flujo de la partida,
- validar entradas,
- registrar letras utilizadas,
- verificar victoria,
- verificar derrota,
- dibujar el ahorcado,
- mostrar mensajes en consola,
- mantener el marcador acumulado.

---

## Estructura lógica del código

La solución está dividida conceptualmente en los siguientes bloques:

### 1. Constantes
Se definen constantes para:

- número máximo de intentos fallidos (6),
- lista de palabras disponibles,
- configuraciones básicas del juego.

### 2. Variables globales controladas
Se utilizan atributos estáticos para almacenar:

- la palabra secreta,
- el estado visible de la palabra (letras reveladas y guiones),
- las letras ya utilizadas,
- el contador de errores,
- el marcador de victorias y derrotas,
- el lector de consola.

### 3. Métodos de inicialización
Estos métodos preparan una nueva partida:

- seleccionar palabra aleatoria,
- inicializar estado de la palabra (todos guiones),
- reiniciar contador de errores y letras usadas,
- mostrar bienvenida.

### 4. Métodos de interacción con el usuario
Encargados de:

- mostrar el ahorcado según los errores,
- mostrar la palabra con letras descubiertas,
- mostrar letras utilizadas,
- solicitar letra al jugador,
- imprimir mensajes,
- preguntar si se desea continuar.

### 5. Métodos de validación
Permiten verificar:

- si la entrada es un único carácter alfabético,
- si la letra ya fue utilizada anteriormente.

### 6. Métodos de lógica del juego
Implementan:

- registrar letra utilizada,
- verificar si la letra está en la palabra,
- actualizar el estado visible de la palabra,
- incrementar contador de errores,
- validar victoria (sin guiones restantes),
- validar derrota (6 errores acumulados).

### 7. Métodos de cierre y continuidad
Gestionan:

- actualización de marcador,
- revelación de la palabra al perder,
- decisión de reiniciar,
- finalización del programa.

---

## Buenas prácticas aplicadas

### 1. Métodos pequeños y con una responsabilidad clara
Cada método realiza una tarea específica, lo que facilita la lectura, comprensión y mantenimiento.

### 2. Nombres descriptivos
Los nombres de variables y métodos fueron definidos en inglés, de forma clara y coherente con su propósito.

### 3. Separación lógica de responsabilidades
Aunque existe una sola clase, la lógica está separada por funciones bien delimitadas.

### 4. Validación robusta de entradas
El sistema maneja entradas inválidas sin romper la ejecución.

### 5. Legibilidad del código
Se priorizó una estructura ordenada y fácil de entender.

### 6. Documentación Javadoc
El código incluye documentación técnica con Javadoc para la clase y para los métodos principales.

### 7. Mantenibilidad
La organización del código permite agregar futuras mejoras con bajo impacto.

---

## Métodos principales del sistema

A nivel conceptual, el programa incluye métodos para:

- iniciar el programa,
- mostrar mensaje de bienvenida,
- iniciar una nueva partida,
- seleccionar palabra aleatoria,
- inicializar estado visible de la palabra,
- imprimir el ahorcado en ASCII,
- imprimir la palabra con guiones,
- imprimir letras utilizadas,
- leer letra del jugador,
- validar entrada de letra,
- verificar si la letra ya fue usada,
- registrar letra utilizada,
- procesar letra ingresada,
- actualizar posiciones reveladas en la palabra,
- verificar victoria,
- verificar derrota,
- actualizar marcador,
- imprimir marcador,
- revelar palabra secreta,
- preguntar si se desea jugar otra vez,
- finalizar programa.

---

## Lógica para detectar victoria

La lógica de victoria evalúa si:

- el estado visible de la palabra **no contiene ningún guion bajo** (`_`),
- lo que significa que todas las letras han sido correctamente adivinadas.

Si esta condición se cumple, el sistema declara al jugador como ganador.

---

## Lógica para detectar derrota

El sistema declara derrota cuando:

- el contador de errores alcanza el valor **6**,
- independientemente de cuántas letras hayan sido descubiertas.

Al perder, el sistema revela la palabra secreta completa.

---

## Lógica de selección de palabra

El programa selecciona la palabra secreta de la siguiente forma:

1. Se define un arreglo de palabras predefinidas en el código.
2. Se genera un índice aleatorio usando `Random`.
3. Se selecciona la palabra correspondiente al índice generado.
4. La palabra se convierte a mayúsculas para evitar distinción entre minúsculas y mayúsculas.

---

## Consideraciones de mantenibilidad

Aunque la implementación está en una sola clase, en una versión más escalable del proyecto sería recomendable separar la solución en varias clases, por ejemplo:

- `Main`: punto de entrada,
- `Game`: control del flujo de partida,
- `WordSelector`: selección y gestión de la palabra secreta,
- `HangmanDrawer`: dibujo del ahorcado en ASCII,
- `InputValidator`: validación de entradas,
- `GameResult`: control del estado del juego.

Esta separación permitiría una arquitectura más limpia, reutilizable y fácil de probar.

---

## Posibles mejoras futuras

El sistema puede ampliarse con funcionalidades como:

- categorías de palabras (animales, países, frutas, etc.),
- niveles de dificultad (más o menos intentos permitidos),
- sistema de pistas,
- interfaz gráfica,
- persistencia de resultados,
- historial de partidas,
- temporizador por turno,
- carga de palabras desde un archivo externo,
- soporte para palabras en varios idiomas,
- pruebas unitarias automatizadas,
- separación en múltiples clases,
- aplicación de patrones de diseño.

---

## Limitaciones de la versión actual

Las limitaciones actuales del sistema son:

- solo permite un jugador,
- funciona únicamente por consola,
- las palabras están predefinidas en el código fuente,
- no guarda partidas en archivos o base de datos,
- no permite personalizar la lista de palabras en tiempo de ejecución,
- no tiene interfaz gráfica,
- toda la lógica está contenida en una única clase.

---

## Ejemplo de ejecución

```text
==========================================
      BIENVENIDO AL JUEGO DEL AHORCADO
==========================================

  +---+
  |   |
      |
      |
      |
      |
=========

Palabra: _ _ _ _
Letras usadas: []

Ingresa una letra: A

¡Correcto! La letra 'A' está en la palabra.

  +---+
  |   |
      |
      |
      |
      |
=========

Palabra: _ A _ A
Letras usadas: [A]

Ingresa una letra: Z

Incorrecto. La letra 'Z' no está en la palabra.

  +---+
  |   |
  O   |
      |
      |
      |
=========

Palabra: _ A _ A
Letras usadas: [A, Z]
```

---

## Conclusión

Este proyecto representa una implementación sólida y clara del juego del Ahorcado en Java 17, adecuada para fines académicos, de práctica o de aprendizaje de fundamentos de programación.

A pesar de haberse desarrollado en una sola clase, la solución mantiene principios importantes de calidad de software, como la modularidad interna, la validación de entradas, la claridad del flujo lógico y la documentación técnica.

Es una base adecuada para evolucionar hacia versiones más robustas y escalables.

---

## Autor

Proyecto desarrollado como ejercicio académico y práctico en Java 17.
