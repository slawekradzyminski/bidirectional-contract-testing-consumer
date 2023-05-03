package com.awesome.testing.consumer;

import com.atlassian.ta.wiremockpactgenerator.WireMockPactGenerator;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ConsumerApplicationTests {

    public static WireMockServer wiremock = new WireMockServer(8080);

    @BeforeAll
    static void setupClass() {
        wiremock.addMockServiceRequestListener(
                WireMockPactGenerator
                        .builder("the-consumer", "the-provider")
                        .build()
        );
        wiremock.start();
    }

    @AfterEach
    void after() {
        wiremock.resetAll();
    }

    @AfterAll
    static void clean() {
        wiremock.shutdown();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGeneratePactInTargetPactsFolder() throws Exception {
        wiremock.stubFor(WireMock.get(urlEqualTo("/api/tutorials")).willReturn(aResponse()
                .withHeader("Content-Type", "application/json").withBody("""
                        [
                          {
                            "id": 1,
                            "title": "ChatGPT",
                            "description": "blablabla",
                            "published": true
                          }
                        ]
                        """)));

        this.mockMvc.perform(get("/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("ChatGPT")))
                .andExpect(jsonPath("$[0].description", is("blablabla")))
                .andExpect(jsonPath("$[0].published", is(true)));
    }

}
