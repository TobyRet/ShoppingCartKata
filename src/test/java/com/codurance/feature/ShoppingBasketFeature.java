package com.codurance.feature;

import java.math.BigDecimal;
import java.util.HashMap;
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
public class ShoppingBasketFeature {

    @Mock
    Clock clock;

    @Mock
    Console console;

    private BasketRepository basketRepository;
    private PriceService priceService;
    private ShoppingBasketService shoppingCartService;
    private static final ProductId THE_HOBBIT_PRODUCT_ID = new ProductId(1);
    private static final int QUANTITY_FOR_THE_HOBBIT = 2;
    private static final int QUANTITY_FOR_BREAKING_BAD = 5;
    private static final ProductId BREAKING_BAD_PRODUCT_ID = new ProductId(2);
    private static final String BASKET_CREATION_DATE = "2016-06-06";
    private static final BigDecimal PRICE_FOR_THE_HOBBIT = new BigDecimal("5.00");
    private static final BigDecimal PRICE_FOR_BREAKING_BAD = new BigDecimal("7.00");
    private static final UserId USER_ID = new UserId(1);

    @Before
    public void setUp() {
        basketRepository = new BasketRepository(clock, new HashMap<>());
        priceService = new PriceService();
        shoppingCartService = new ShoppingBasketService(basketRepository, priceService, console, clock);
    }

    @Test
    public void shouldAddItemsToCart() {
        BasketItem expectedFirstBasketItem = new BasketItem(THE_HOBBIT_PRODUCT_ID, QUANTITY_FOR_THE_HOBBIT, PRICE_FOR_THE_HOBBIT);
        BasketItem expectedSecondBasketItem = new BasketItem(BREAKING_BAD_PRODUCT_ID, QUANTITY_FOR_BREAKING_BAD, PRICE_FOR_BREAKING_BAD);
        Basket expectedBasket = new Basket(BASKET_CREATION_DATE);

        expectedBasket.add(expectedFirstBasketItem);
        expectedBasket.add(expectedSecondBasketItem);

        given(clock.dateNow()).willReturn(BASKET_CREATION_DATE);

        shoppingCartService.addItem(USER_ID, THE_HOBBIT_PRODUCT_ID, QUANTITY_FOR_THE_HOBBIT);
        verify(console).print("[ITEM ADDED TO SHOPPING CART]: " +
                "Added[2016-06-06], " +
                "User[1], " +
                "Product[1], " +
                "Quantity[2], " +
                "Price[£5.00]\n");

        shoppingCartService.addItem(USER_ID, BREAKING_BAD_PRODUCT_ID, QUANTITY_FOR_BREAKING_BAD);
        verify(console).print("[ITEM ADDED TO SHOPPING CART]: " +
                "Added[2016-06-06], " +
                "User[1], " +
                "Product[2], " +
                "Quantity[5], " +
                "Price[£7.00]\n");

        assertThat(shoppingCartService.basketFor(USER_ID), is(expectedBasket));
    }
}
