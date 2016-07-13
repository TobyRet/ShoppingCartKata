package com.codurance;

import java.math.BigDecimal;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BasketShould {

    @Test
    public void calculateTheTotalOfItsItems() {
        String creationDate = "2016-04-04";
        Basket basket = new Basket(creationDate);

        BigDecimal priceOfFirstItem = new BigDecimal("5.00");
        ProductId productIdOfFirstItem = new ProductId(1);
        int quantityOfFirstItem = 1;
        BasketItem firstBasketItem = new BasketItem(productIdOfFirstItem, quantityOfFirstItem, priceOfFirstItem);
        ProductId productIdOfSecondItem = new ProductId(2);
        int quantityOfSecondItem = 2;
        BigDecimal priceOfSecondItem = new BigDecimal("7.00");
        BasketItem secondBasketItem = new BasketItem(productIdOfSecondItem, quantityOfSecondItem, priceOfSecondItem);

        basket.add(firstBasketItem);
        basket.add(secondBasketItem);

        assertThat(basket.total(), is("19.00"));


    }

}
