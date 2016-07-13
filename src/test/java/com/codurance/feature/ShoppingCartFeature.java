package com.codurance.feature;

import java.util.HashMap;
import com.codurance.Basket;
import com.codurance.BasketItem;
import com.codurance.BasketRepository;
import com.codurance.Clock;
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
        ShoppingCartService shoppingCartService = new ShoppingCartService(basketRepository);
        UserId userId = new UserId(1);
        String basketCreationDate = "2016-06-06";
        ProductId theHobbitProductId = new ProductId(1);
        int quantityForTheHobbit = 2;
        ProductId breakingBadProductId = new ProductId(2);
        int quantityForBreakingBad = 5;
        BasketItem firstBasketItem = new BasketItem(theHobbitProductId, quantityForTheHobbit);
        BasketItem secondBasketItem = new BasketItem(breakingBadProductId, quantityForBreakingBad);
        Basket expectedBasket = new Basket(basketCreationDate);

        expectedBasket.add(firstBasketItem);
        expectedBasket.add(secondBasketItem);

        given(clock.dateNow()).willReturn(basketCreationDate);

        shoppingCartService.addItem(userId, theHobbitProductId, quantityForTheHobbit);
        shoppingCartService.addItem(userId, breakingBadProductId, quantityForBreakingBad);

        assertThat(shoppingCartService.basketFor(userId), is(expectedBasket));
    }
}
