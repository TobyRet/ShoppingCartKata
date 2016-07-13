package com.codurance.feature;

import java.math.BigDecimal;
import java.util.HashMap;
import com.codurance.Basket;
import com.codurance.BasketItem;
import com.codurance.BasketRepository;
import com.codurance.Clock;
import com.codurance.PriceService;
import com.codurance.ProductId;
import com.codurance.ShoppingCartService;
import com.codurance.UserId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartFeature {

    @Mock
    Clock clock;

    @Test
    public void shouldAddItemsToCart() {
        BasketRepository basketRepository = new BasketRepository(clock, new HashMap<>());
        PriceService priceService = new PriceService();
        ShoppingCartService shoppingCartService = new ShoppingCartService(basketRepository, priceService);
        UserId userId = new UserId(1);

        String basketCreationDate = "2016-06-06";
        ProductId theHobbitProductId = new ProductId(1);
        int quantityForTheHobbit = 2;
        ProductId breakingBadProductId = new ProductId(2);
        int quantityForBreakingBad = 5;
        BigDecimal priceForTheHobbit = new BigDecimal("5.00");
        BigDecimal priceForBreakingBad = new BigDecimal("7.00");
        BasketItem expectedFirstBasketItem = new BasketItem(theHobbitProductId, quantityForTheHobbit, priceForTheHobbit);
        BasketItem expectedSecondBasketItem = new BasketItem(breakingBadProductId, quantityForBreakingBad, priceForBreakingBad);
        Basket expectedBasket = new Basket(basketCreationDate);

        expectedBasket.add(expectedFirstBasketItem);
        expectedBasket.add(expectedSecondBasketItem);

        given(clock.dateNow()).willReturn(basketCreationDate);

        shoppingCartService.addItem(userId, theHobbitProductId, quantityForTheHobbit);
        shoppingCartService.addItem(userId, breakingBadProductId, quantityForBreakingBad);

        assertThat(shoppingCartService.basketFor(userId), is(expectedBasket));
    }
}
