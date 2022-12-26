package org.vicnor.financial.calculator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.vicnor.financial.calculator.model.IncomeDto;
import org.vicnor.financial.calculator.model.IncomeListDto;
import org.vicnor.financial.calculator.model.UpdateIncomeDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class IncomeControllerTests {

   private RestTemplate restTemplate;

   @LocalServerPort
   private int randomServerPort;

   private static final String BASE_URL = "http://127.0.0.1";

   public IncomeControllerTests() {
      this.restTemplate = new RestTemplate();
   }

   @Test
   public void testCrudOperations() {
      // Create
      IncomeDto request = new IncomeDto();
      request.setAmount(BigDecimal.valueOf(1000));
      request.setDueDate(LocalDate.now().plusDays(1));
      request.setDescription("Job");

      IncomeDto response = restTemplate.postForObject(buildUrl("/cashflow/income"), request, IncomeDto.class);
      assertNotNull(response);
      assertEquals(request.getAmount(), response.getAmount());
      assertEquals(request.getDueDate(), response.getDueDate());
      assertEquals(request.getDescription(), response.getDescription());

      // Get
      response = restTemplate.getForObject(buildUrl("/cashflow/income/" + response.getId()), IncomeDto.class);
      assertNotNull(response);
      assertEquals(request.getAmount(), response.getAmount());
      assertEquals(request.getDueDate(), response.getDueDate());
      assertEquals(request.getDescription(), response.getDescription());

      // List
      IncomeListDto incomeListDto = restTemplate.getForObject(buildUrl("/cashflow/incomes"), IncomeListDto.class);
      assertTrue(incomeListDto.getCount() >= 1);

      // Update
      UpdateIncomeDto updateIncomeDto = new UpdateIncomeDto();
      updateIncomeDto.setAmount(BigDecimal.valueOf(1000));
      updateIncomeDto.setDueDate(LocalDate.now().plusDays(2));
      updateIncomeDto.setDescription("Updated");
      restTemplate.put(buildUrl("/cashflow/income/" + response.getId()), updateIncomeDto);

      // Get after update
      response = restTemplate.getForObject(buildUrl("/cashflow/income/" + response.getId()), IncomeDto.class);
      assertNotNull(response);
      assertEquals(updateIncomeDto.getAmount(), response.getAmount());
      assertEquals(updateIncomeDto.getDueDate(), response.getDueDate());
      assertEquals(updateIncomeDto.getDescription(), response.getDescription());
   }

   @Test
   public void testException() {
      // Get not found
      assertThrows(RuntimeException.class, () -> {
         restTemplate.getForObject(buildUrl("/cashflow/income/test"), IncomeDto.class);
      });
   }

   private String buildUrl(String endpoint) {
      return new StringBuilder()
              .append(BASE_URL)
              .append(":")
              .append(randomServerPort)
              .append(endpoint).toString();
   }

}
