package no.jaflaten.dev.mvcwithconfigurationclass.example;

import no.jaflaten.dev.mvcwithconfigurationclass.example.entities.UtilityServlet;
import no.jaflaten.dev.mvcwithconfigurationclass.example.service.UtilityServletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@WebAppConfiguration
@WebMvcTest
public class UtilityServletControllerTest {

    @MockBean
    UtilityServletService utilityServletService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private UtilityServlet servletA, servletB;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        servletA = new UtilityServlet();
        servletB = new UtilityServlet();
        servletA.setUrl("www.foo-url.com");
        servletB.setUrl("www.bar-url.no");
        servletA.setId(UUID.randomUUID());
        servletB.setId(UUID.randomUUID());
    }

    @Test
    public void findAllShouldReturnMultipleServlets() throws Exception {
        Set<UtilityServlet> servlets = new HashSet<>();
        servlets.add(servletA);
        servlets.add(servletB);

        Mockito.when(utilityServletService.findAllUtilityServlets()).thenReturn(servlets);
        mockMvc.perform(get("/api/utility/servlet/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


        verify(utilityServletService, times(1)).findAllUtilityServlets();
    }

    @Test
    public void findAllShouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/api/utility/servlet/"))
                .andExpect(status().isNoContent());

        verify(utilityServletService, times(1)).findAllUtilityServlets();
    }

    @ComponentScan(basePackages = {"no.jaflaten.dev.mvcwithconfigurationclass.example"})
    @SpringBootConfiguration
    public static class UtilityServletTestApp {
    }
}
