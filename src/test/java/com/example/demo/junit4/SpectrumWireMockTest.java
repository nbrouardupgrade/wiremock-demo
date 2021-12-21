package com.example.demo.junit4;

import com.example.demo.SpectrumCustomerApi;
import com.example.demo.SpectrumLoginApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import shaw.spectrum.customer.EmailDTO;
import shaw.spectrum.customer.IndividualCustomerDTO;
import shaw.spectrum.system.LoginDTO;

public class SpectrumWireMockTest extends AbstractEmbeddedWireMockStarter {
    @Autowired
    private SpectrumWireMock spectrumWireMock;

    @Autowired
    private SpectrumLoginApi spectrumLoginApi;

    @Autowired
    private SpectrumCustomerApi spectrumCustomerApi;

    @Before
    public void setUp() {
        spectrumWireMock.init();
    }

    @Test
    public void testLogin() {
        final var loginDTO = new LoginDTO();
        loginDTO.setUserName("user");
        final var login = spectrumLoginApi.login(loginDTO);
        Assertions.assertNotNull(login);
    }

    @Test
    public void individualCustomer() {
        final IndividualCustomerDTO customerDTO = createIndividualCustomerDTO();
        final var individualCustomerResource = spectrumCustomerApi.addIndividualCustomer(customerDTO);
        Assertions.assertNotNull(individualCustomerResource);
        final var id = individualCustomerResource.getIndividualCustomerDTO().getId();
        Assertions.assertTrue(id > 0);

//        final var individualCustomer = spectrumCustomerApi.getIndividualCustomer(id);
//        Assertions.assertEquals(individualCustomer, individualCustomerResource);
    }

    private IndividualCustomerDTO createIndividualCustomerDTO() {
        final var customerDTO = new IndividualCustomerDTO();
        customerDTO.setFirstName("Nicolas");
        customerDTO.setLastName("Brouard");
        final var email = new EmailDTO();
        email.setEmailAddress("nbrouard@upgrade.com");
        customerDTO.addEmail(email);
        return customerDTO;
    }
}
