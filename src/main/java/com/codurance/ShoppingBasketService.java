package com.codurance;

import java.math.BigDecimal;

public class ShoppingBasketService {
    private BasketRepository basketRepository;
    private PriceService priceService;

    public ShoppingBasketService(BasketRepository basketRepository, PriceService priceService) {
        this.basketRepository = basketRepository;
        this.priceService = priceService;
    }

    public void addItem(com.codurance.UserId userId, ProductId productId, int quantity) {
        BigDecimal price = priceService.priceFor(productId);
        BasketItem basketItem = new BasketItem(productId, quantity, price);

        basketRepository.addBasketItem(userId, basketItem);
    }

    public Basket basketFor(UserId userId) {
        return basketRepository.basketFor(userId);
    }
}
