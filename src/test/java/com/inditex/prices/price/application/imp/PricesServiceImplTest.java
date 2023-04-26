package com.inditex.prices.price.application.imp;

import com.inditex.prices.price.application.dto.PriceQueryIDTO;
import com.inditex.prices.price.application.dto.ProductPriceODTO;
import com.inditex.prices.price.domain.model.PriceQueryModel;
import com.inditex.prices.price.domain.model.ProductPriceModel;
import com.inditex.prices.price.domain.service.PricesDomainService;
import com.inditex.prices.price.shared.mapper.PriceMapper;
import com.inditex.prices.shared.exceptions.InditexCustomException;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricesServiceImplTest {

    @Mock
    private PriceMapper mapper;

    @Mock
    private PricesDomainService pricesDomainService;

    @InjectMocks
    private PricesServiceImpl service;

    @Test
    public void shouldReturnAndProductPriceWhenItsFound(){
        //given
        final var inOrder = Mockito.inOrder(mapper, pricesDomainService);
        final var priceQueryIDTO = Instancio.create(PriceQueryIDTO.class);
        final var priceQueryModel = Instancio.create(PriceQueryModel.class);
        final var productPriceModel = Instancio.create(ProductPriceModel.class);
        final var productPriceODTO = Instancio.create(ProductPriceODTO.class);

        when(mapper.toModel(priceQueryIDTO)).thenReturn(priceQueryModel);
        when(pricesDomainService.findProductPriceByFilters(priceQueryModel))
                .thenReturn(Optional.of(productPriceModel));
        when(mapper.toODTO(productPriceModel)).thenReturn(productPriceODTO);

        //when
        final var result = service.findPriceByFilters(priceQueryIDTO);

        //then
        inOrder.verify(mapper).toModel(priceQueryIDTO);
        inOrder.verify(pricesDomainService).findProductPriceByFilters(priceQueryModel);
        inOrder.verify(mapper).toODTO(productPriceModel);
        inOrder.verifyNoMoreInteractions();

        assertThat(result).isEqualTo(productPriceODTO);

    }

    @Test
    public void shouldThrowExceptionWhenItsNotFound(){
        //given
        final var inOrder = Mockito.inOrder(mapper, pricesDomainService);
        final var priceQueryIDTO = Instancio.create(PriceQueryIDTO.class);
        final var priceQueryModel = Instancio.create(PriceQueryModel.class);

        when(mapper.toModel(priceQueryIDTO)).thenReturn(priceQueryModel);
        when(pricesDomainService.findProductPriceByFilters(priceQueryModel))
                .thenReturn(Optional.empty());

        //when&&then
        assertThatExceptionOfType(InditexCustomException.class)
                .isThrownBy(()-> service.findPriceByFilters(priceQueryIDTO));

        inOrder.verify(mapper).toModel(priceQueryIDTO);
        inOrder.verify(pricesDomainService).findProductPriceByFilters(priceQueryModel);
        inOrder.verifyNoMoreInteractions();
    }


}
