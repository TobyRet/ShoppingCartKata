package com.codurance.unit;

import java.math.BigDecimal;
import com.codurance.Basket;
import com.codurance.BasketItem;
import com.codurance.BasketRepository;
import com.codurance.Clock;
import com.codurance.Console;
import com.codurance.PriceService;
import com.codurance.ProductId;
import com.codurance.ShoppingBasketService;
import com.codurance.UserId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingBasketServiceShould {
    private static final UserId USER_ID = new UserId(1);
    private static final String CREATION_DATE = "2016-06-06";
    private static final int QUANTITY_FOR_THE_HOBBIT = 2;
    private static final ProductId PRODUCT_ID_FOR_THE_HOBBIT = new ProductId(1);
    private static final BigDecimal PRICE_FOR_THE_HOBBIT = new BigDecimal("5.00");
    private static final String DATE_NOW = "2016-06-10";
    private ShoppingBasketService shoppingCartService;

    @Mock BasketRepository basketRepository;
    @Mock Console console;
    @Mock PriceService priceService;
    @Mock Clock clock;

    @Before
    public void setUp() {
        shoppingCartService = new ShoppingBasketService(basketRepository, priceService, console, clock);
        given(priceService.priceFor(PRODUCT_ID_FOR_THE_HOBBIT)).willReturn(PRICE_FOR_THE_HOBBIT);
    }

    @Test
    public void addItemsToABasket() {
        BasketItem basketItem = new BasketItem(PRODUCT_ID_FOR_THE_HOBBIT, QUANTITY_FOR_THE_HOBBIT, PRICE_FOR_THE_HOBBIT);
        Basket expectedBasket = new Basket(CREATION_DATE);
        expectedBasket.add(basketItem);

        given(basketRepository.basketFor(USER_ID)).willReturn(expectedBasket);

        shoppingCartService.addItem(USER_ID, PRODUCT_ID_FOR_THE_HOBBIT, QUANTITY_FOR_THE_HOBBIT);
        verify(priceService).priceFor(PRODUCT_ID_FOR_THE_HOBBIT);
        verify(basketRepository).addBasketItem(USER_ID, basketItem);

        assertThat(shoppingCartService.basketFor(USER_ID), is(expectedBasket));
        verify(basketRepository).basketFor(USER_ID);
    }

    @Test
    public void shouldLogWhenItemIsAddedToTheBasket() {
        given(clock.dateNow()).willReturn(DATE_NOW);

        shoppingCartService.addItem(USER_ID, PRODUCT_ID_FOR_THE_HOBBIT, QUANTITY_FOR_THE_HOBBIT);
        verify(console).print("[ITEM ADDED TO SHOPPING CART]: " +
                "Added[2016-06-10], " +
                "User[1], " +
                "Product[1], " +
                "Quantity[2], " +
                "Price[£5.00]\n");
    }
}
