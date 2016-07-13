package com.codurance.unit;

import java.util.HashMap;
import com.codurance.Basket;
import com.codurance.BasketItem;
import com.codurance.BasketNotFoundException;
import com.codurance.BasketRepository;
import com.codurance.Clock;
import com.codurance.ProductId;
import com.codurance.UserId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BasketRepositoryShould {

    private BasketRepository basketRepository;
    private UserId userId;
    private ProductId productIdForBreakingBad;
    private int quantityForBreakingBad;
    private BasketItem firstBasketItem;
    private String basketCreationDate;
    private Basket expectedBasket;

    @Mock
    Clock clock;

    @Before
    public void setUp() {
        basketRepository = new BasketRepository(clock, new HashMap<>());
        userId = new UserId(1);
        productIdForBreakingBad = new ProductId(1);
        quantityForBreakingBad = 2;
        firstBasketItem = new BasketItem(productIdForBreakingBad, quantityForBreakingBad);
        basketCreationDate = "2016-06-08";
        expectedBasket = new Basket(basketCreationDate);
        expectedBasket.add(firstBasketItem);

        given(clock.dateNow()).willReturn(basketCreationDate);
    }

    @Test
    public void addItemsToNewBasketIfItDoesNotExist() throws BasketNotFoundException {
        basketRepository.addBasketItem(userId, firstBasketItem);

        verify(clock).dateNow();

        assertThat(basketRepository.basketFor(userId), is(expectedBasket));
    }

    @Test
    public void addItemsToExistingBasketIfItExists() {
        basketRepository.addBasketItem(userId, firstBasketItem);

        ProductId productIdForTheHobbit = new ProductId(2);
        int quantityForTheHobbit = 1;

        BasketItem secondBasketItem = new BasketItem(productIdForTheHobbit, quantityForTheHobbit);

        Basket updatedExpectedBasket = new Basket(basketCreationDate);
        updatedExpectedBasket.add(firstBasketItem);
        updatedExpectedBasket.add(secondBasketItem);

        basketRepository.addBasketItem(userId, secondBasketItem);

        assertThat(basketRepository.basketFor(userId), is(updatedExpectedBasket));
    }
}
