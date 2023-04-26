package com.inditex.prices.price.infraestructure.persistence.repository;

import com.inditex.prices.price.domain.model.PriceQueryModel;
import com.inditex.prices.price.domain.model.ProductPriceModel;
import com.inditex.prices.price.infraestructure.persistence.PriceSpecs;
import com.inditex.prices.price.infraestructure.persistence.entity.ProductPriceEntity;
import com.inditex.prices.price.infraestructure.persistence.repository.jpa.PricesJpaRepository;
import com.inditex.prices.price.shared.mapper.PriceMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PricesRepositoryImplTest {

    @Spy
    private PricesJpaRepository jpaRepository;

    @Mock
    private PriceMapper mapper;

    @Spy
    private PriceSpecs priceSpecs;

    @InjectMocks
    private PricesRepositoryImpl pricesRepository;


    @Test
    public void shouldReturnOneProductWhenFound(){
        //given
        final var queryModel = Instancio.create(PriceQueryModel.class);
        final var productPriceEntity = Instancio.create(ProductPriceEntity.class);
        final var productPriceModel = Instancio.create(ProductPriceModel.class);

        when(jpaRepository.findOne(any(Specification.class))).thenReturn(Optional.of(productPriceEntity));
        when(mapper.toModel(productPriceEntity)).thenReturn(productPriceModel);


        //when
        final var result= pricesRepository.findPriceByFilters(queryModel);

        //then
        verify(jpaRepository).findOne(any(Specification.class));
        verify(mapper).toModel(productPriceEntity);
        verifyNoMoreInteractions(jpaRepository, mapper);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(productPriceModel);

    }

    @Test
    public void shouldReturnEmptyOptionalWhenNotFound(){
        //given
        final var queryModel = Instancio.create(PriceQueryModel.class);
        final var productPriceModel = Instancio.create(ProductPriceModel.class);

        when(jpaRepository.findOne(any(Specification.class))).thenReturn(Optional.empty());

        //when
        final var result= pricesRepository.findPriceByFilters(queryModel);

        //then
        verify(jpaRepository).findOne(any(Specification.class));
        verifyNoMoreInteractions(jpaRepository);
        verifyNoInteractions(mapper);

        assertThat(result.isPresent()).isFalse();

    }



}
