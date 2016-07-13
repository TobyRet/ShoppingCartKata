package com.codurance.unit;

import com.codurance.Basket;
import com.codurance.BasketItem;
import com.codurance.BasketRepository;
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
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceShould {

    @Mock
    BasketRepository basketRepository;

    @Test
    public void addItemsToABasket() {
        ShoppingCartService shoppingCartService = new ShoppingCartService(basketRepository);
        UserId userId = new UserId(1);
        String creationDate = "2016-06-06";
        int quantityForTheHobbit = 2;
        ProductId productIdForTheHobbit = new ProductId(1);
        BasketItem basketItem = new BasketItem(productIdForTheHobbit, quantityForTheHobbit);
        Basket expectedBasket = new Basket(creationDate);
        expectedBasket.add(basketItem);

        given(basketRepository.basketFor(userId)).willReturn(expectedBasket);

        shoppingCartService.addItem(userId, productIdForTheHobbit, quantityForTheHobbit);
        verify(basketRepository).addBasketItem(userId, basketItem);

        assertThat(shoppingCartService.basketFor(userId), is(expectedBasket));
        verify(basketRepository).basketFor(userId);
    }
}
