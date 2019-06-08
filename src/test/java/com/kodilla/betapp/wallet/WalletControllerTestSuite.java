package com.kodilla.betapp.wallet;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WalletController.class)
public class WalletControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletMapper walletMapper;

    @MockBean
    private WalletService walletService;

    @Test
    public void testInitWallet() throws Exception {
        //Given
        Wallet wallet = new Wallet(BigDecimal.ONE, Currency.GBP);
        WalletDto walletDto = new WalletDto(BigDecimal.ZERO, Currency.USD);

        when(walletService.initWallet(walletMapper.mapToWallet(walletDto))).thenReturn(wallet);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(walletDto);

        //When & Then
        mockMvc.perform(post("/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    public void testAddFunds() throws Exception {
        //Given
        long id = 1L;
        BigDecimal funds = BigDecimal.ONE;
        WalletDto walletDto = new WalletDto(1, BigDecimal.ZERO, Currency.USD);

        when(walletMapper.mapToWalletDto(walletService.addFunds(id, funds))).thenReturn(walletDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(walletDto);

        //When & Then
        mockMvc.perform(patch("/wallet/add/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.accountBallance", is(0)))
                .andExpect(jsonPath("$.currency", is("USD")));
    }

    @Test
    public void testDeductFunds() throws Exception {
        //Given
        long id = 1L;
        BigDecimal withdrawal = BigDecimal.ONE;
        WalletDto walletDto = new WalletDto(1, BigDecimal.ZERO, Currency.USD);

        when(walletMapper.mapToWalletDto(walletService.deductFunds(id, withdrawal))).thenReturn(walletDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(walletDto);

        //When & Then
        mockMvc.perform(patch("/wallet/sub/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.accountBallance", is(0)))
                .andExpect(jsonPath("$.currency", is("USD")));
    }

    @Test
    public void testChangeCurrency() throws Exception {
        //Given
        long id = 1L;
        Currency currency = Currency.PLN;
        WalletDto walletDto = new WalletDto(1, BigDecimal.ZERO, Currency.USD);

        when(walletMapper.mapToWalletDto(walletService.changeCurrency(id, currency))).thenReturn(walletDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(walletDto);

        //When & Then
        mockMvc.perform(patch("/wallet/USD/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.accountBallance", is(0)))
                .andExpect(jsonPath("$.currency", is("USD")));
    }
}