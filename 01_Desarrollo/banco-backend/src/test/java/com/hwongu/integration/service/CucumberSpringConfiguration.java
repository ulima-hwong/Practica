/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase configura el contexto de Spring para las pruebas de integracion con Cucumber,
 * permitiendo que los escenarios de prueba puedan aprovechar el contexto de la aplicacion
 * Spring Boot y sus beans.
 */
package com.hwongu.integration.service;

import com.hwongu.BancoBackendApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = BancoBackendApplication.class)
public class CucumberSpringConfiguration {
}

