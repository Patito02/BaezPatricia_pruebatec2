README:

# SISTEMA DE GESTION DE TURNOS
***
Esta página es un sistema de gestión de turnos en el cual se permite realizar altas, bajas, edición y consultas de turnos para una dependencia gubernamental. También permite registrar y listar a los ciudadanos que utilicen el sistema. Adicionalmente se podrán filtrar los turnos por fecha y estado.  Desde la página principal se podrá acceder fácilmente a las distintas opciones que ofrece el sistema, agilizando la gestión y tratamiento de los datos de turno y ciudadanos almacenados en la base de datos.
***
## Tecnologías utilizadas:
- Java
- JPA
- Jsp
- Servlets
- Base de datos relacional MySQL.

## Como descargar el proyecto:
1. Clonar el repositorio del proyecto en la pc local. Ejecutar el siguiente comando: 
	git clone https://github.com/Patito02/BaezPatricia_pruebatec2.git 
2. Desde el IDE importar el proyecto.
3. Crear una base de datos y hacer la conexión desde el IDE para la persistencia de los datos.
4. Ejecuta la aplicación.

## Supuestos
Se supone que la persona que utilice la aplicación será un empleado del gobierno por lo que la asignación de turnos requerirá un registro previo de los ciudadanos que quisieran un turno. 
Al registrar un turno, solo se podrán elegir fechas en un rango de 30 días, empezando a contar desde el día que se haga la solicitud. Lo mismo sucede con los horarios, solo se podrán registrar turnos entre las 9:00 hs y las 18:00 hs, siendo el último turno a las 17.30 hs. Se supone que el ciudadano tomará turnos en el rango permitido, así como en los días hábiles del mes.
Al momento de atender los turnos, se supone que el usuario va a utilizar el botón de “Atender siguiente turno”, pero la existencia del botón “Atender” que se encuentra el listado de turnos pendientes para poder marcar como atendidos a los turnos que hayan quedado pendientes de otras fechas o para adelantar la atención de los turnos del mismo día o de días posteriores, queda a criterio de una situación particular, por ejemplo: si alguna persona por cuestiones de salud o situación excepcional se presenta en otra fecha o en otro horario. 
También se supone que si una persona no asistirá a su turno, pedirá la cancelación del mismo, por lo que no deberían quedar turnos pendientes de fechas pasadas.

## Utilización de la aplicación
En la pantalla principal se puede ver un resumen de los totales de los turnos del día. También se pueden ver las estadísticas de todos los turnos registrados en el sistema. Por último, hay una botonera de accesos rápidos a todas las funcionalidades del sistema.
Desde el menú principal se puede acceder a las siguientes opciones:

- Turnos:

    - Ver Turnos
    - Nuevo turno

- Turnos Pendientes

    - Ver turnos pendientes
    - Atender siguiente turno

- Ciudadanos

    - Ver ciudadanos
    - Registrar ciudadano

### Turnos:
#### Ver turnos: 
Permite ver la lista completa de turnos, en espera y ya atendidos. Además, permite realizar el filtrado de los turnos por fecha, y por fecha + estado. Luego del filtrado se podrá visualizar a todos los turnos haciendo clic en “Ver todos”. Desde esta pantalla se puede editar y eliminar únicamente los turnos en estado “En espera”, ya que los turnos con estado “Ya atendidos” quedan guardados en la base de datos. 
Al hacer clic en el botón de editar turno, redirige a una pantalla de edición donde se podrán modificar los datos del turno, pero no del ciudadano asignado. Al hacer clic en el botón “Modificar Turno” se guardan los cambios. Al hacer clic en el botón “Cancelar” lleva al listado de turnos.
Al hacer clic en el botón de cancelar turno, pregunta si está seguro de la cancelación. Y si confirma, se procede a la cancelación del turno.
#### Nuevo turno: 
Se debe ingresar el dni del ciudadano y los datos del turno: fecha, hora y trámite. La fecha del turno está restringida a 30 días a contar a partir del día de la solicitud y la hora permitida para la atención es de 9:00 a 18:00 hs, por lo que el último turno habilitado es el de las 17.30hs. El botón “Guardar Turno” crea el turno. El botón “Cancelar” lleva al listado de turnos.
### Turnos Pendientes:
#### Ver turnos pendientes: 
Permite ver la lista completa de turnos pendientes, con la opción de filtrarlos por fecha. Luego del filtrado se podrá visualizar nuevamente todos los turnos haciendo clic en “Ver todos”.  Se podrá atender un turno al hacer clic en el botón “Atender”, siempre pregunta si se está seguro de realizar la atención y si confirma, el estado del turno cambia a “Ya atendido”.
#### Atender siguiente turno: 
Permite visualizar los datos del siguiente turno pendiente del día. Los datos visualizados no se podrán modificar. Al hacer clic en el botón “Atender” el estado del turno cambia a “Ya atendido”. El botón “Cancelar” lleva al listado de turnos pendientes.
### Ciudadanos:
#### Ver ciudadanos: 
Permite visualizar los datos de los ciudadanos registrados: nombre, apellido y dni. El botón “Modificar datos” permite realizar actualizaciones en los datos guardados del ciudadano seleccionado, excepto el dni. El botón “Eliminar ciudadano” permite eliminar un ciudadano de la base de datos. 
#### Registrar ciudadano: 
Se deben ingresar los datos del ciudadano: nombre, apellido y dni, todos los datos son obligatorios. El botón “Registrar Ciudadano” crea el ciudadano. El botón “Cancelar” lleva al listado de ciudadanos.

## Explicación de métodos

#### Ver Turnos
Esta sección utiliza el servlet SvTurno - método GET. Lo que se hace es traer el listado completo de turnos a través del método traerTurnos(). Este método trae un listado de todos los turnos registrados y habilitados existentes en el sistema, los ordena por fecha ascendente y los muestra en verTurnos.jsp; si no hay turnos muestra un cartel y redirige a SvInicio para ver la pantalla principal.
Las opciones de filtrado de los turnos son: por fecha o por fecha y estado. Para este filtrado se utiliza el servlet SvBusquedaFecha – método GET. Se toman los datos de filtrado elegidos, se busca por fecha con el método buscarPorFecha() para traer la lista de turnos habilitados de ese fecha. En el caso de que no haya turnos le avisa al usuario con un cartel. Si el filtro es solo por fecha, muestra el listado por fecha. Si el filtro incluye la fecha y el estado, se utiliza adicionalmente el método buscarPorEstado() para filtrar por el estado seleccionado y mostrar la lista, si no hay turnos con el estado seleccionado le avisa al usuario con otro cartel. En todos los casos, luego del filtrado redirige a la pantalla verTurnos.jsp.
El botón Ver Todos muestra todos los turnos por pantalla utilizando el servlet SvTurno – método GET y se queda en la pantalla verTurnos.jsp
Para editar un turno se utiliza el servlet SvModifTurno - método GET. Se utiliza el método traerTurno() para buscar los datos del turno seleccionado y mostarlos en la pantalla editarTurno.jsp, en el caso que el turno tenga estado Ya atendido, muestra un cartel de aviso ya que no se podría editar y redirige a SvTurno para ver el listado de turnos. Cuando se presiona el botón Modificar Turno llama al servlet SvModifTurno - método POST. Lo que se hace es setear los datos modificados y llamar al método editarTurno() para guardar los cambios, en el caso que el turno tenga estado Ya atendido, muestra un cartel de aviso.  En ambos casos redirige a SvTurno para ver la lista completa de turnos con los cambios realizados.
Para cancelar un turno se utiliza el servlet SvElimTurno- método POST. Se utiliza el método traerTurno() para buscar los datos del turno seleccionado, en el caso que el turno tenga estado Ya atendido, muestra un cartel de aviso ya que no se podría cancelar. Si el turno se puede cancelar pregunta si está seguro de realizar la cancelación del turno, si la respuesta es SI se procede a la cancelación del turno a través del método eliminarTurno(), si la respuesta es NO se queda en la misma página. El método eliminarTurno() cambia el atributo Habilitado a false. En todos los casos redirige a SvTurno para visualizar el listado completo de turnos con los cambios realizados.

#### Nuevo Turno
Esta sección muestra en la pantalla solicitarTurno.jsp los campos a completar para la solicitud de un nuevo turno, todos los campos son obligatorios. Al presionar el botón Guardar Turno, se procede al registro del mismo, para lo cual se utiliza el servlet SvTurno - método POST. Lo que se hace es tomar los datos de pantalla y verificar si existe el ciudadano ingresado mediante el método buscarPorDni(). Este método verifica si existe el ciudadano en la base de datos, si existe retorna el ciudadano, si no existe pregunta si desea crearlo y si confirma, redirige a la pantalla de registrarCiudadano.jsp. Seguidamente si el ciudadano existe, se procede a la creación del turno con el método crearTurno() y por último se agrega a la lista del ciudadano el turno registrado. Una vez finalizado se muestra un cartel y redirige a la pantalla solicitarTurno.jsp.
Cada turno tendrá un Estado y un Trámite asignado, éstos son:
Estado: 
1=”En Espera”,
2=”Ya atendido”.
Tramites: 
1=” Consulta / Información”, 
2=” Atención Comercial”, 
3=” Atención Tesorería”, 
4=” Reclamos”.

#### Ver Turnos Pendientes 
Esta sección utiliza el servlet SvTurnosPendientes - método GET. Lo que se hace es traer el listado completo de turnos a través del método traerTurnos(). Este método trae un listado de todos los turnos registrados y habilitados en el sistema y los ordena por fecha ascendente para luego mostrarlos en la pantalla turnosPendientes.jsp; si no hay turnos pendientes muestra un aviso y redirige a SvInicio para ver la pantalla principal. Si hay turnos, realiza el filtro por estado con el método buscarPorEstado().
Está la opción de filtrar por fecha. Para este filtrado se utiliza el servlet SvBusquedaFecha – método POST. Se toman el dato de fecha elegida y se busca con el método buscarPorFecha(). En el caso de que no haya turnos le avisa al usuario con un cartel. Luego filtra por estado “En espera” con el método buscarPorEstado() y muestra el listado por pantalla. Al finalizar redirige a la pantalla turnosPendientes.jsp.
El botón Ver Todos muestra todos los turnos por pantalla utilizando el servlet SvTurnosPendientes – método GET y se queda en la pantalla turnosPendientes.jsp.
El botón Atender utiliza el servlet SvTurnosPendientes – método POST. Se busca por el id del turno seleccionado a través del método traerTurno(). Le pregunta al usuario si está seguro de querer atender ese turno, si la respuesta es SI, cambia el estado del turno a “Ya atendido”, con el método editarTurno(). Luego redirige al SvTurnosPendientes para mostrar por pantalla los turnos pendientes en turnosPendientes.jsp.

#### Atender Próximo Turno
Esta sección utiliza el servlet SvProximoTurno- método GET. Lo que se hace es traer los datos completos del próximo turno. Esto los hace con el método buscarPorFecha() utilizando la fecha de hoy y luego filtrando por estado con buscarPorEstado(). De esa lista buscar el primero turno y lo muestra por pantalla en proximoTurno.jsp. En el caso de que no haya turnos pendientes para hoy, muestra un cartel al usuario y redirige a la pantalla principal con SvInicio.    
El botón Atender utiliza el servlet SvTurnosPendientes – método POST. Se busca por el id del turno seleccionado a través del método traerTurno(). Le pregunta al usuario si está seguro de querer atender ese turno, si la respuesta es SI, cambia el estado del turno a “Ya atendido”, con el método editarTurno(). Luego redirige al SvTurnosPendientes para mostrar por pantalla los turnos pendientes en turnosPendientes.jsp.

#### Ver ciudadanos
Esta sección utiliza el servlet SvCiudadano - método GET. Lo que se hace es traer la lista completa de ciudadanos con el método traerCiudadanos() y los muestra por pantalla en verCiudadanos.jsp. En el caso de que no haya ciudadanos registrados muestra un aviso al usuario y redirige a la pantalla principal a través de SvInicio.
En el botón Modificar utiliza el servlet SvModifCiudadano - método GET. Se utiliza el método traerCiudadano() para encontrar el ciudadano del id que se seleccionó. Una vez que encuentra el ciudadano redirige los resultados y los muestra en la pantalla editarCiudadano.jsp. El botón Modificar Ciudadano utiliza el servlet SvModifCiudadano – método POST. Se toman los datos de pantalla y se setean esos datos y con el método editarCiudadano() se guardan en la base de datos las modificaciones. Al finalizar se redirige a SvCiudadano para ver la lista de ciudadanos.
Para botón Eliminar utiliza el servlet SvElimCiudadano - método POST. Se utiliza el método traerCiudadano () para buscar los datos del ciudadano seleccionado. Luego de verificar que no tenga turnos pendientes con los métodos traerTurnos() y buscarPorEstado(), pregunta si está seguro de realizar la eliminación del ciudadano, si la respuesta es SI se procede a la eliminación del ciudadano a través del método eliminarCiudadano(), si la respuesta es NO se queda en la misma página. El método eliminarCiudadano () cambia el atributo Habilitado a false. En todos los casos redirige a SvCiudadano para visualizar el listado completo de ciudadanos habilitados.

#### Registrar Ciudadanos
Esta opción lleva a la pantalla registrarCiudadano.jsp, donde se podrán ingresar los datos de un ciudadano. Al hacer clic en el botón Registrar Ciudadano se invoca al servlet SvCiudadano – método POST. Aquí se toman los datos de pantalla y se verifica si ya existe con el método buscarPorDni(). Luego si no existe en la base de datos se procede a la creación del ciudadano mediante el método crearCiudadano(). Tanto para el caso de si existe el ciudadano o si se crea el ciudadano se le informa al usuario con un cartel y redirige a la pantalla registrarCiudadano.jsp.

### Desarrolladores y creadores
La desarrolladora de este sistema no se hace responsable por el mal uso del software. Queda prohibida la alteración del código fuente y su venta sin autorización de la creadora.

### Consultas y Sugerencias
Ante alguna consulta o sugerencia por favor enviar un mensaje a la siguiente dirección de correo electrónico: patriciabaez1987@gmail.com
