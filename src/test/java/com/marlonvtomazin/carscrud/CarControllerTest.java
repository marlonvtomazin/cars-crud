package com.marlonvtomazin.carscrud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marlonvtomazin.carscrud.entity.Car;
import com.marlonvtomazin.carscrud.service.CarService;
import com.marlonvtomazin.carscrud.web.controller.CarController;
import com.marlonvtomazin.carscrud.web.dto.CarCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarService carService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterCar_WhenFieldsAreValid_ReturnStatus201() throws Exception {
        CarCreateDto carRequest = new CarCreateDto("BBB-1212", "Jaguar", "E-pace", "Blue", 2026, 5020);

        Car carResponse = new Car();
        carResponse.setId(1L);
        carResponse.setCarYear(2000);
        carResponse.setModel("Ka");
        carResponse.setOdometer(743190);
        carResponse.setColor("Chrome");
        carResponse.setBrand("Ford");
        carResponse.setPlate("AAA-1111");

        when(carService.save(any())).thenReturn(carResponse);

        mockMvc.perform(post("/api/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.brand").value("Ford"))
                .andExpect(jsonPath("$.color").value("Chrome"))
                .andExpect(jsonPath("$.odometer").value(743190));
    }

}