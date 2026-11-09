/**
 * Autor: Henry Wong
 * GitHub: hwongu
 *
 * Esta clase configura y ejecuta pruebas de integracion utilizando Cucumber.
 * Se incluyen las configuraciones para generar reportes en formatos HTML y JSON.
 */
package com.hwongu.integration.service;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/cucumber-reports/cucumber.html, json:target/cucumber-reports/cucumber.json"
)
public class CucumberTestRunner {
}

