package com.marlonvtomazin.carscrud;

import com.marlonvtomazin.carscrud.web.dto.CarCreateDto;
import com.marlonvtomazin.carscrud.web.dto.CarResponseDto;
import com.marlonvtomazin.carscrud.web.dto.CarUpdateDto;
import com.marlonvtomazin.carscrud.web.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/cars/cars-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/cars/cars-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CarIT  {
    @Autowired
    WebTestClient testClient;

    @Nested
    @DisplayName("Register car")
    class RegisterCar {
        @Test
        public void shouldRegisterCar_WhenFieldsAreValid_ReturnStatus201() {
            CarResponseDto responseBody = testClient
                    .post()
                    .uri("/api/v1/cars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(new CarCreateDto("BBB-1212", "Jaguar", "E-pace", "Blue", 2026, 5020))
                    .exchange()
                    .expectStatus().isCreated()
                    .expectBody(CarResponseDto.class)
                    .returnResult().getResponseBody();

            assertThat(responseBody).isNotNull();
            assertThat(responseBody.getId()).isNotNull();
            assertThat(responseBody.getPlate()).isEqualTo("BBB-1212");
        }

        @Test
        public void shouldRegisterCar_WhenCarAlreadyRegistered_ReturnStatus409() {
            ErrorMessage responseBody = testClient
                    .post()
                    .uri("/api/v1/cars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(new CarCreateDto("ABC-1234", "Jaguar", "E-pace", "Blue", 2026, 5020))
                    .exchange()
                    .expectStatus().isEqualTo(409)
                    .expectBody(ErrorMessage.class)
                    .returnResult().getResponseBody();

            assertThat(responseBody).isNotNull();
            assertThat(responseBody.getStatus()).isEqualTo(409);
        }

        @Test
        public void shouldRegisterCar_WhenFieldsAreInvalid_ReturnStatus422() {
            ErrorMessage responseBody = testClient
                    .post()
                    .uri("/api/v1/cars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(new CarCreateDto("", "Jaguar", "E-pace", "Blue", 2026, 5020))
                    .exchange()
                    .expectStatus().isEqualTo(422)
                    .expectBody(ErrorMessage.class)
                    .returnResult().getResponseBody();

            assertThat(responseBody).isNotNull();
            assertThat(responseBody.getStatus()).isEqualTo(422);
        }
    }

    @Nested
    @DisplayName("Find car")
    class FindCarById {
        @Test
        public void shouldFindCar_WhenFieldsAreValid_ReturnStatus200() {
            CarResponseDto responseBody = testClient
                    .get()
                    .uri("/api/v1/cars/10")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(CarResponseDto.class)
                    .returnResult().getResponseBody();

            org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
            org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isEqualTo("10");
            org.assertj.core.api.Assertions.assertThat(responseBody.getPlate()).isEqualTo("ABC-1234");
        }

        @Test
        public void shouldFindCar_WhenIdDoesntExist_ReturnStatus404() {
            ErrorMessage responseBody = testClient
                    .get()
                    .uri("/api/v1/cars/30")
                    .exchange()
                    .expectStatus().isNotFound()
                    .expectBody(ErrorMessage.class)
                    .returnResult().getResponseBody();

            org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
            org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
        }
    }

    @Nested
    @DisplayName("Update car")
    class UpdateCar {
        @Test
        public void shouldUpdateCar_WhenFieldsAreValid_ReturnStatus200() {
            CarResponseDto responseBody = testClient
                    .patch()
                    .uri("/api/v1/cars/10")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(new CarUpdateDto("AAA-1111", null, null, null, null, null))
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(CarResponseDto.class)
                    .returnResult().getResponseBody();

            assertThat(responseBody).isNotNull();
            assertThat(responseBody.getId()).isNotNull();
            assertThat(responseBody.getPlate()).isEqualTo("AAA-1111");
        }

        @Test
        public void shouldUpdateCar_WhenPlateAlreadyExist_ReturnStatus409() {
            ErrorMessage responseBody = testClient
                    .patch()
                    .uri("/api/v1/cars/10")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(new CarUpdateDto("ABC-1111", null, null, null, null, null))
                    .exchange()
                    .expectStatus().isEqualTo(409)
                    .expectBody(ErrorMessage.class)
                    .returnResult().getResponseBody();

            org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
            org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(409);
        }

    }

    @Nested
    @DisplayName("List all cars")
    class ListAllCars {
        @Test
        public void shouldListAllCars_ReturnStatus200() {
            List<CarResponseDto> responseBody = testClient
                    .get()
                    .uri("/api/v1/cars")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBodyList(CarResponseDto.class)
                    .returnResult().getResponseBody();

            org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
            org.assertj.core.api.Assertions.assertThat(responseBody.size()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("Delete a car")
    class DeleteCar {
        @Test
        public void shouldDeleteCar_WhenIdIsValid_ReturnStatus200() {
            testClient
                    .delete()
                    .uri("/api/v1/cars/12")
                    .exchange()
                    .expectStatus().isNoContent()
                    .expectBody().isEmpty();


        }

        @Test
        public void shouldDeleteCar_WhenIdIsInvalid_ReturnStatus404() {
            ErrorMessage responseBody = testClient
                    .delete()
                    .uri("/api/v1/cars/30")
                    .exchange()
                    .expectStatus().isNotFound()
                    .expectBody(ErrorMessage.class)
                    .returnResult().getResponseBody();

            org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
            org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
        }
    }
}