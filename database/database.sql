SET GLOBAL time_zone ='-3:00';
DROP SCHEMA IF EXISTS `proyecto-derivaciones`;
CREATE SCHEMA IF NOT EXISTS `proyecto-derivaciones`;
USE `proyecto-derivaciones`;



insert into paciente(id,nombreCompleto,documento,fechaNacimiento) values
(1,"Juan de la Torre",2121221,"2021-04-11"),
(2,"Facundo Campazzo",2124444,"2021-04-12"),
(3,"Rodrigo Bueno",2121333,"2021-04-13");

insert into cobertura(id,nombre) values
(1,"Osde"),
(2,"Galeno"),
(3,"Swiss Medical"),
(4,"Medicus"),
(5,"UOM"),
(6,"Medife"),
(7,"OMINT");
INSERT INTO `proyecto-derivaciones`.`centromedico`
(
`direccion`,
`guardia`,
`nombre`,
`salaComun`,
`terapia`)
VALUES
(
"Monte 6936",
1,
"Centro Medico Monte",
1,
0);
INSERT INTO `proyecto-derivaciones`.`centromedico`
(
`direccion`,
`guardia`,
`nombre`,
`salaComun`,
`terapia`)
VALUES
(
"Av. Emilio Castro 7469",
0,
"Centro Medico CME",
1,
1);


insert into derivacion(id,diagnostico,estado,fechaDerivacion,paraQueSector,urgente,cobertura_id,paciente_id)
values(2,"covid","Pendiente","2021-04-13","Terapia",false,1,1);

select * from derivacion;