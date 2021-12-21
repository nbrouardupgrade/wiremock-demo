package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import feign.Headers;
import feign.Param;
import shaw.spectrum.contact.resource.ContactResource;
import shaw.spectrum.customer.AddressDTO;
import shaw.spectrum.customer.BankruptcyCaseDTO;
import shaw.spectrum.customer.ContactDTO;
import shaw.spectrum.customer.CustomerFinancialAccountDTO;
import shaw.spectrum.customer.EmailDTO;
import shaw.spectrum.customer.IndividualCustomerDTO;
import shaw.spectrum.customer.PhoneNumberDTO;
import shaw.spectrum.customer.resource.CustomerAccountsListResource;
import shaw.spectrum.customer.resource.CustomerAddressListResource;
import shaw.spectrum.customer.resource.CustomerAddressResource;
import shaw.spectrum.customer.resource.CustomerBankruptcyResource;
import shaw.spectrum.customer.resource.CustomerEmailAddressResource;
import shaw.spectrum.customer.resource.CustomerFinancialAccountListResource;
import shaw.spectrum.customer.resource.CustomerFinancialAccountResource;
import shaw.spectrum.customer.resource.CustomerPhoneNumberResource;
import shaw.spectrum.customer.resource.IndividualCustomerResource;

@FeignClient(name = "spectrumCustomer", url = "${spectrum.server.scheme:http}://${spectrum.server.host}:${spectrum.server.port}")
public interface SpectrumCustomerApi {
    @PostMapping("/spectrum/rws/sc/customers/individualcustomers?withExist=true")
    @Headers("Content-Type: application/json")
    IndividualCustomerResource addIndividualCustomer(IndividualCustomerDTO customerDTO);

    @PostMapping("/spectrum/rws/sc/customers/updateindividualcustomers")
    @Headers("Content-Type: application/json")
    IndividualCustomerResource updateIndividualCustomer(IndividualCustomerDTO customerDTO);

    @GetMapping("/spectrum/rws/sc/customers/individualcustomers/{customerId}")
    @Headers("Content-Type: application/json")
    IndividualCustomerResource getIndividualCustomer(@Param("customerId") Long customerId);

    @GetMapping("/spectrum/rws/sc/customers/individualcustomers/?customerNumber={customerNumber}")
    @Headers("Content-Type: application/json")
    IndividualCustomerResource getIndividualCustomerByNumber(@Param("customerNumber") Long customerNumber);

    @PostMapping("/spectrum/rws/sc/customers/individualcustomerbyssntin")
    @Headers("Content-Type: application/json")
    IndividualCustomerResource getIndividualCustomerBySsn(IndividualCustomerDTO customerDTO);

    @PostMapping("/spectrum/rws/sc/contacts/contact")
    @Headers("Content-Type: application/json")
    ContactResource addCustomerContact(ContactDTO contactDTO);

    @PostMapping("/spectrum/rws/sc/customers/bankruptcies")
    @Headers("Content-Type: application/json")
    CustomerBankruptcyResource addBankruptcy(BankruptcyCaseDTO bankruptcyCaseDTO);

    @PostMapping("/spectrum/rws/sc/customers/updatebankruptcies")
    @Headers("Content-Type: application/json")
    CustomerBankruptcyResource updateBankruptcy(BankruptcyCaseDTO bankruptcyCaseDTO);

    @PostMapping("/spectrum/rws/sc/customers/emails")
    @Headers("Content-Type: application/json")
    CustomerEmailAddressResource addEmailAddress(EmailDTO emailDTO);

    @PostMapping("/spectrum/rws/sc/customers/updateemails")
    @Headers("Content-Type: application/json")
    CustomerEmailAddressResource updateEmailAddress(EmailDTO emailDTO);

    @PostMapping("/spectrum/rws/sc/customers/phonenumbers")
    @Headers("Content-Type: application/json")
    CustomerPhoneNumberResource addPhoneNumber(PhoneNumberDTO phoneDTO);

    @PostMapping("/spectrum/rws/sc/customers/updatephonenumbers")
    @Headers("Content-Type: application/json")
    CustomerPhoneNumberResource updatePhoneNumber(PhoneNumberDTO phoneDTO);

    @GetMapping("/spectrum/rws/sc/customers/addresses/{customerId}")
    @Headers("Content-Type: application/json")
    CustomerAddressListResource getCustomerAddresses(@Param("customerId") Long customerId);

    @PostMapping("/spectrum/rws/sc/customers/addresses")
    @Headers("Content-Type: application/json")
    CustomerAddressResource addAddress(AddressDTO addressDTO);

    @PostMapping("/spectrum/rws/sc/customers/updateaddresses")
    @Headers("Content-Type: application/json")
    CustomerAddressResource updateAddress(AddressDTO addressDTO);

    @GetMapping("/spectrum/rws/sc/customers/customerfinancialaccounts/{accountNumber}")
    @Headers("Content-Type: application/json")
    CustomerFinancialAccountListResource getFinancialAccounts(@Param("accountNumber") Long accountNumber);

    @PostMapping("/spectrum/rws/sc/customers/updatecustomerfinancialaccounts")
    @Headers("Content-Type: application/json")
    CustomerFinancialAccountResource updateFinancialAccount(CustomerFinancialAccountDTO customerFinancialAccountDTO);

    @PostMapping("/spectrum/rws/sc/customers/customerfinancialaccounts")
    @Headers("Content-Type: application/json")
    CustomerFinancialAccountResource addFinancialAccount(CustomerFinancialAccountDTO customerFinancialAccountDTO);

    @GetMapping("/spectrum/rws/sc/customers/customeraccounts/{customerId}")
    @Headers("Content-Type: application/json")
    CustomerAccountsListResource getCustomerAccounts(@Param("customerId") Long customerId);
}
