package com.inditex.prices.price.domain.service.impl;

import com.inditex.prices.price.domain.model.PriceQueryModel;
import com.inditex.prices.price.domain.model.ProductPriceModel;
import com.inditex.prices.price.domain.repository.PricesRepository;
import com.inditex.prices.shared.exceptions.InditexCustomException;
import jakarta.validation.*;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PricesDomainServiceImplTest {

    @Mock
    private PricesRepository pricesRepository;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private PricesDomainServiceImpl pricesDomainService;

    @BeforeEach
    public void init(){
        pricesDomainService = new PricesDomainServiceImpl(pricesRepository, validator);

    }

    @Test
    public void shouldReturnPriceDomainWhenIsPresent(){
        //given
        final var priceQueryModel = Instancio.create(PriceQueryModel.class);
        final var productPriceModel = Instancio.create(ProductPriceModel.class);

        when(pricesRepository.findPricesByFilters(priceQueryModel)).thenReturn(List.of(productPriceModel));

        //when
        final var result = pricesDomainService.findProductPriceByFilters(priceQueryModel);

        //then
        verify(pricesRepository).findPricesByFilters(priceQueryModel);
        verifyNoMoreInteractions(pricesRepository);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(productPriceModel);
    }

    @Test
    public void shouldThrowExceptionWhenQueryIsNorValid(){
        //given
        final var priceQueryModel = Instancio.of(PriceQueryModel.class).ignore(Select.field("brandId")).create();


        //when&&then
        assertThatExceptionOfType(InditexCustomException.class)
                .isThrownBy(() -> pricesDomainService.findProductPriceByFilters(priceQueryModel));

        verifyNoInteractions(pricesRepository);
    }
}
