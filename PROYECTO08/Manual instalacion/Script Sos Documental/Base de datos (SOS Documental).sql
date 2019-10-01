
INSERT INTO `sos_documental`.`rol` (`codigoRol`, `nomRol`, `descripcionRol`, `estadoRol`)
VALUES ('ROL-01', 'Administrador', 'Contiene todos los permisos', 'Activo');

INSERT INTO `sos_documental`.`usuario` (`idUsuario`, `primNombre`, `primApellido`, `segApellido`, `correo`, `idRol`, `contrasena`, `estadoUsuario`)
VALUES ('123456789', 'Einer', 'Mendez', 'Palomino', 'eomendez6@misena.edu.co', '1', 'f789e225167c4a7dc187868c821166d4', 'Activo');

INSERT INTO `sos_documental`.`clasificacionmodulo` (`nomClasificacionModulo`, `descripcionClasificacionModulo`, `estadoClasificacionModulo`)
VALUES ('Usuario', 'Pertenecen todos los modulos relacionados con usuario', 'Activo'),
('Documento', 'Pertenecen todos los modulos relacionados con documento', 'Activo'),
('Reportes', 'Pertenecen todos los modulos relacionados con reportes', 'Activo'),
('Ayuda', 'Pertenecen todos los modulos relacionados con ayuda', 'Activo');

INSERT INTO `sos_documental`.`modulo` (`codigoModulo`, `nomModulo`, `descripcionModulo`, `linkAccesoModulo`, `estadoModulo`, `idClasificacionModulo`)
VALUES ('MOD-01', 'Administrar Rol', 'Permite administrar los roles', 'AdministrarRol.jsp', 'Activo', '1'),
('MOD-02', 'Administrar Perfil', 'Permite administrar los permisos del rol', 'AdministrarPerfil.jsp', 'Activo', '1'),
('MOD-03', 'Cambiar Contrasena', 'Permite cambiar la contraseña de usuario', 'CambioContrasena.jsp', 'Activo', '1'),
('MOD-04', 'Administrar Usuarios', 'Permite administrar los usuarios', 'AdministrarUsuario.jsp', 'Activo', '1'),
('MOD-05', 'Administrar Documentos', 'Permite administrar los documento', 'AdministrarDocumento.jsp', 'Activo', '2'),
('MOD-06', 'Reporte Especifico', 'Permite generar reportes de documentos', 'ReporteEspecifico.jsp', 'Activo', '3'),
('MOD-07', 'Reporte Global', 'Permite generar reportes globales', 'ReporteGlobal.jsp', 'Activo', '3'),
('MOD-08', 'Reporte Descargas', 'Permite generar reportes de las descargas', 'ReporteDescargas.jsp', 'Activo', '3'),
('MOD-09', 'Manual de Usuario', 'Permite descargar el manual', 'ManualUsuario.jsp', 'Activo', '4');




INSERT INTO `sos_documental`.`perfil` (`codigoPerfil`, `nomPerfil`, `descripcionPerfil`, `idModulo`, `estadoPerfil`) 
VALUES ('PER-LIS-01', 'Listar roles', 'Permite buscar y ver los roles', '1', 'Activo'),
('PER-CRE-01', 'Crear roles', 'Permite la creacion de roles', '1', 'Activo'),
('PER-MOD-01', 'Modificar roles', 'Permite modificar los roles', '1',  'Activo'),
('PER-LIS-02', 'Listar perfiles', 'Permite buscar y ver los perfiles', '2', 'Activo'),
('PER-CRE-02', 'Crear perfiles', 'Permite la creacion de perfiles', '2', 'Activo'),
('PER-MOD-02', 'Modificar perfiles', 'Permite modificar los perfiles', '2',  'Activo');

INSERT INTO `sos_documental`.`rolperfil` (`idRol`, `idPerfil`)
VALUES ('1', '1'),
('1', '2'),
('1', '3'),
('1', '4'),
('1', '5'),
('1', '6');



/*9.4.0.5125 version heidy-mariadb*/

/* f789e225167c4a7dc187868c821166d4 = AbCd12345*/


update Usuario set contrasena= "1030647666" where idUsuario = 1030647666;

/*Esta consulta sirve para que mysql guarde archivos blob mas pesados*/ 
SHOW VARIABLES LIKE 'max_allowed_packet';
SET GLOBAL max_allowed_packet=112000000;








