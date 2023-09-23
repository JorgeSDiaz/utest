package org.adaschool.Weather.controller;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class WeatherReportControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @Mock
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherReportController).build();
    }

    @Test
    public void WhenGetControllerGivenRequestThenCorrectTemperature() throws Exception {
        double latitude = 37.8267;
        double longitude = -122.4233;
        WeatherReport mockWeatherReport = new WeatherReport();
        mockWeatherReport.setHumidity(80.0);
        mockWeatherReport.setTemperature(0.0);
        Mockito.when(weatherReportService.getWeatherReport(latitude, longitude)).thenReturn(mockWeatherReport);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/weather-report")
                        .param("latitude", String.valueOf(latitude))
                        .param("longitude", String.valueOf(longitude)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(0.0));
    }
}
