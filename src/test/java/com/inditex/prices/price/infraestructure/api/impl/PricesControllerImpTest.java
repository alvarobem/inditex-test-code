package com.inditex.prices.price.infraestructure.api.impl;


import com.inditex.prices.price.application.PricesService;
import com.inditex.prices.price.application.dto.PriceQueryIDTO;
import com.inditex.prices.price.application.dto.ProductPriceODTO;
import com.inditex.prices.price.infraestructure.api.dto.PriceQueryRQDTO;
import com.inditex.prices.price.infraestructure.api.dto.ProductPriceRSDTO;
import com.inditex.prices.price.shared.mapper.PriceMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PricesControllerImpTest  {

    @InjectMocks
    private PricesControllerImp controller;

    @Mock
    private PriceMapper mapper;

    @Mock
    private PricesService service;

    @Test
    public void shouldReturn200WhenThePriceIsFound(){
        //given
        final var inOrder = inOrder(mapper, service);
        final var requestRQDTO = Instancio.create(PriceQueryRQDTO.class);
        final var requestIDTO = Instancio.create(PriceQueryIDTO.class);
        final var priceProductODTO = Instancio.create(ProductPriceODTO.class);
        final var priceProductRSDTO = Instancio.create(ProductPriceRSDTO.class);
        when(service.findPriceByFilters(requestIDTO)).thenReturn(priceProductODTO);
        when(mapper.toIDTO(requestRQDTO)).thenReturn(requestIDTO);
        when(mapper.toRSDTO(priceProductODTO)).thenReturn(priceProductRSDTO);
        //then
        final var result = controller.getPrices(requestRQDTO);

        //then
        inOrder.verify(mapper).toIDTO(requestRQDTO);
        inOrder.verify(service).findPriceByFilters(requestIDTO);
        inOrder.verify(mapper).toRSDTO(priceProductODTO);
        verifyNoMoreInteractions(mapper, service);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(priceProductRSDTO);
    }

}
