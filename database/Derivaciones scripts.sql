USE `proyecto-derivaciones`;

-- Cobeturas

INSERT INTO `proyecto-derivaciones`.`cobertura` (`nombre`) VALUES ('OSDE');
INSERT INTO `proyecto-derivaciones`.`cobertura` (`nombre`) VALUES ('Swiss Medical');
INSERT INTO `proyecto-derivaciones`.`cobertura` (`nombre`) VALUES ('Galeno');
INSERT INTO `proyecto-derivaciones`.`cobertura` (`nombre`) VALUES ('Pami');
INSERT INTO `proyecto-derivaciones`.`cobertura` (`nombre`) VALUES ('IOMA');
INSERT INTO `proyecto-derivaciones`.`cobertura` (`nombre`) VALUES ('OSECAC');
INSERT INTO `proyecto-derivaciones`.`cobertura` (`nombre`) VALUES ('LUIS PASTEUR');

-- Centros Medicos

INSERT INTO `proyecto-derivaciones`.`centromedico` (`direccion`, `guardia`, `nombre`, `salaComun`, `terapia`) VALUES ('Av. Medrano 350', true,'Almagro (CeSAC Nº 38)',true,false);
INSERT INTO `proyecto-derivaciones`.`centromedico` (`direccion`, `guardia`, `nombre`, `salaComun`, `terapia`) VALUES ('Agüero 940',true,'Balvanera (CeSAC Nº 11)',false,false);
INSERT INTO `proyecto-derivaciones`.`centromedico` (`direccion`, `guardia`, `nombre`, `salaComun`, `terapia`) VALUES ('Av. Velez Sarsfield 1271',false,'Barracas 1 (CeSAC Nº 1)',true,false);
INSERT INTO `proyecto-derivaciones`.`centromedico` (`direccion`, `guardia`, `nombre`, `salaComun`, `terapia`) VALUES ('	Osvaldo Cruz 3485',true,'Barracas 2 (CeSAC Nº 8',true,true);
INSERT INTO `proyecto-derivaciones`.`centromedico` (`direccion`, `guardia`, `nombre`, `salaComun`, `terapia`) VALUES ('Osvaldo Cruz 2055',false,'Barracas 3 (CeSAC Nº 16)',true,true);
INSERT INTO `proyecto-derivaciones`.`centromedico` (`direccion`, `guardia`, `nombre`, `salaComun`, `terapia`) VALUES ('Amancio Alcorta e Iguazú', false,'Barracas 4 (CeSAC Nº 30)',true,true);
INSERT INTO `proyecto-derivaciones`.`centromedico` (`direccion`, `guardia`, `nombre`, `salaComun`, `terapia`) VALUES ('Osvaldo Cruz y Zavaleta',true,'Barracas 6 (CeSAC Nº 35)',false,true);
INSERT INTO `proyecto-derivaciones`.`centromedico` (`direccion`, `guardia`, `nombre`, `salaComun`, `terapia`) VALUES ('Curapaligüe 1905', true,'Flores 1 (CeSAC Nº 19)',true,true);
INSERT INTO `proyecto-derivaciones`.`centromedico` (`direccion`, `guardia`, `nombre`, `salaComun`, `terapia`) VALUES ('Ana María Janer y Charrúa 2330 (Barrio 1 11 14)', true,'Flores 2 (CeSAC Nº 20)',true,false);
INSERT INTO `proyecto-derivaciones`.`centromedico` (`direccion`, `guardia`, `nombre`, `salaComun`, `terapia`) VALUES ('Ana Maria Janer y Agustin De Vedia',true,'Flores 3 (CeSAC Nº 31)',true,false);
INSERT INTO `proyecto-derivaciones`.`centromedico` (`direccion`, `guardia`, `nombre`, `salaComun`, `terapia`) VALUES ('Esteban Bonorino 1729',false,'Flores 4 (CeSAC Nº 40)',true,true);

-- Planes

INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('1');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('1');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('1');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('2');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('2');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('2');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('3');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('3');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('3');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('4');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('4');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('5');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('6');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('6');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('6');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('7');
INSERT INTO `proyecto-derivaciones`.`plan` (`cobertura_id`) VALUES ('7');

-- PlanCentroMedico

INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('1', '1');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('2', '1');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('3', '1');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('4', '1');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('5', '1');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('6', '1');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('7', '1');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('8', '1');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('1', '2');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('3', '2');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('5', '2');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('8', '2');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('9', '2');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('11', '2');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('2', '3');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('4', '3');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('6', '3');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('7', '3');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('10', '3');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('1', '4');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('5', '4');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('8', '4');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('9', '4');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('1', '5');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('2', '5');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('6', '5');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('8', '5');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('9', '5');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('11', '5');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('6', '6');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('8', '6');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('10', '6');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('11', '6');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('3', '7');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('5', '7');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('1', '8');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('2', '8');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('6', '8');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('8', '8');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('10', '8');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('1', '9');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('2', '9');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('3', '9');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('4', '9');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('7', '9');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('10', '9');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('3', '10');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('8', '10');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('9', '11');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('5', '11');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('2', '11');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('9', '12');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('11', '12');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('7', '12');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('4', '12');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('3', '13');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('4', '13');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('8', '13');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('11', '13');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('1', '14');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('2', '14');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('7', '14');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('9', '14');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('10', '14');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('11', '14');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('1', '15');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('3', '15');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('6', '15');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('7', '15');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('10', '15');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('1', '16');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('3', '16');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('5', '16');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('8', '16');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('9', '16');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('10', '16');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('1', '17');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('2', '17');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('4', '17');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('6', '17');
INSERT INTO `proyecto-derivaciones`.`plancentromedico` (`idCentroMedico_id`, `idPlan_id`) VALUES ('7', '17');

-- Pacientes

INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('33694911', '1994/02/21', 'AGÜERO LUCAS DANIEL');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('34328642', '1996/03/11', 'AGÜERO PARRA ESTEFANÍA SOLEDAD');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('31643135', '1992/07/23', 'AGUILERA  MORENO ALFREDO ALEJANDRO');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('35241269', '1998/11/30', 'ÁLAMO DAIANA BELEN');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('35542153', '1997/12/03', 'ÁVILA ELIZABETH GISELLE');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('17037523', '1976/04/17', 'BAZÁN JUANA ESTÉR');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('32238035', '1993/02/14', 'BLANQUER ROMERO JUAN MANUEL');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('21355847', '1988/03/30', 'BRAC RAMOS SILVIA KARINA');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('11496916', '1972/05/05', 'CUENCA PETRONA NICOLAZA');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('28619955', '1990/01/22', 'DÍAZ RAÚL ');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('31374172', '1992/05/10', 'FLORES RAMONA MARTHA');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('13694348', '1987/03/12', 'FUENTES SONIA KARINA');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('32163776', '1993/12/24', 'HERRERA JULIA JESICA');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('39700861', '2000/07/08', 'LEDESMA EVELIN YOHANA ');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('33820158', '1995/10/23', 'LUCER FLORENCIA SILVANA');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('38731825', '2000/01/08', 'MIRANDAY CANDELA MARÍA ROSA ');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('34633758', '1996/07/18', 'NARBAEZ LORENZO DANIEL');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('29996758', '1989/09/27', 'OVIEDO PUEYO NORMA BEATRÍZ');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('27451433', '1986/04/29', 'PEREYRA MARÍA CRISTINA ');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('17973562', '1974/09/18', 'RÍOS NICOLASA DEL VALLE ');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('27041529', '1988/07/17', 'ROMERO MARÍA BELÉN ');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('23184773', '1982/02/23', 'SÁNCHEZ DELIA ');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('22703107', '1980/08/19', 'TELLO GRISELDA DEL CARMEN ');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('29449209', '1990/05/30', 'URRICHE FERNANDA MARGARIATA JUANA ');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('29226865', '1990/04/28', 'VERA ALEJANDRA KARINA ');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('28619954', '1989/11/22', 'VILCHES ANTONIA SATURNINA');
INSERT INTO `proyecto-derivaciones`.`paciente` (`documento`, `fechaNacimiento`, `nombreCompleto`) VALUES ('29226865', '1990/10/19', 'ZALAZAR RODRÍGUEZ MARÍA JOSÉ');

-- PlanPaciente

INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('1', '1');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('1', '5');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('2', '2');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('3', '7');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('4', '11');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('5', '15');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('6', '17');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('6', '3');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('6', '8');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('7', '17');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('7', '1');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('8', '7');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('9', '16');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('10', '14');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('11', '11');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('11', '3');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('12', '2');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('12', '4');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('12', '7');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('13', '12');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('14', '14');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('15', '17');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('16', '6');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('16', '5');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('17', '8');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('18', '4');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('19', '11');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('20', '15');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('21', '9');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('21', '17');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('22', '16');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('22', '1');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('22', '10');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('22', '13');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('23', '13');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('24', '4');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('25', '3');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('26', '8');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('26', '15');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('27', '4');
INSERT INTO `proyecto-derivaciones`.`planpaciente` (`idPaciente_id`, `idPlan_id`) VALUES ('27', '10');

-- Requerimientos Medicos
INSERT INTO `proyecto-derivaciones`.`requerimientosmedicos` (`cardiologoSeGuardia`, `cirujanoDeGuardia`, `tomografo`, `traumatologoDeguardia`) VALUES (true, true, true, true);
INSERT INTO `proyecto-derivaciones`.`requerimientosmedicos` (`cardiologoSeGuardia`, `cirujanoDeGuardia`, `tomografo`, `traumatologoDeguardia`) VALUES (true, true, true, true);
INSERT INTO `proyecto-derivaciones`.`requerimientosmedicos` (`cardiologoSeGuardia`, `cirujanoDeGuardia`, `tomografo`, `traumatologoDeguardia`) VALUES (true, true, true, true);
INSERT INTO `proyecto-derivaciones`.`requerimientosmedicos` (`cardiologoSeGuardia`, `cirujanoDeGuardia`, `tomografo`, `traumatologoDeguardia`) VALUES (true, true, true, true);
INSERT INTO `proyecto-derivaciones`.`requerimientosmedicos` (`cardiologoSeGuardia`, `cirujanoDeGuardia`, `tomografo`, `traumatologoDeguardia`) VALUES (true, true, true, true);
INSERT INTO `proyecto-derivaciones`.`requerimientosmedicos` (`cardiologoSeGuardia`, `cirujanoDeGuardia`, `tomografo`, `traumatologoDeguardia`) VALUES (true, true, true, true);
INSERT INTO `proyecto-derivaciones`.`requerimientosmedicos` (`cardiologoSeGuardia`, `cirujanoDeGuardia`, `tomografo`, `traumatologoDeguardia`) VALUES (true, true, true, true);
INSERT INTO `proyecto-derivaciones`.`requerimientosmedicos` (`cardiologoSeGuardia`, `cirujanoDeGuardia`, `tomografo`, `traumatologoDeguardia`) VALUES (true, true, true, true);
INSERT INTO `proyecto-derivaciones`.`requerimientosmedicos` (`cardiologoSeGuardia`, `cirujanoDeGuardia`, `tomografo`, `traumatologoDeguardia`) VALUES (true, true, true, true);
INSERT INTO `proyecto-derivaciones`.`requerimientosmedicos` (`cardiologoSeGuardia`, `cirujanoDeGuardia`, `tomografo`, `traumatologoDeguardia`) VALUES (true, true, true, true);
INSERT INTO `proyecto-derivaciones`.`requerimientosmedicos` (`cardiologoSeGuardia`, `cirujanoDeGuardia`, `tomografo`, `traumatologoDeguardia`) VALUES (true, true, true, true);

-- Requerimientos medicos Centro Medico
UPDATE `proyecto-derivaciones`.`centromedico` SET `requerimientosMedicos_id` = '1' WHERE (`id` = '1');
UPDATE `proyecto-derivaciones`.`centromedico` SET `requerimientosMedicos_id` = '2' WHERE (`id` = '2');
UPDATE `proyecto-derivaciones`.`centromedico` SET `requerimientosMedicos_id` = '3' WHERE (`id` = '3');
UPDATE `proyecto-derivaciones`.`centromedico` SET `requerimientosMedicos_id` = '4' WHERE (`id` = '4');
UPDATE `proyecto-derivaciones`.`centromedico` SET `requerimientosMedicos_id` = '5' WHERE (`id` = '5');
UPDATE `proyecto-derivaciones`.`centromedico` SET `requerimientosMedicos_id` = '6' WHERE (`id` = '6');
UPDATE `proyecto-derivaciones`.`centromedico` SET `requerimientosMedicos_id` = '7' WHERE (`id` = '7');
UPDATE `proyecto-derivaciones`.`centromedico` SET `requerimientosMedicos_id` = '8' WHERE (`id` = '8');
UPDATE `proyecto-derivaciones`.`centromedico` SET `requerimientosMedicos_id` = '9' WHERE (`id` = '9');
UPDATE `proyecto-derivaciones`.`centromedico` SET `requerimientosMedicos_id` = '10' WHERE (`id` = '10');
UPDATE `proyecto-derivaciones`.`centromedico` SET `requerimientosMedicos_id` = '11' WHERE (`id` = '11');




