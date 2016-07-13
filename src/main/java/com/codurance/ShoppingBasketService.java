package com.codurance;

import java.math.BigDecimal;

import static java.lang.String.valueOf;

public class ShoppingBasketService {
    private BasketRepository basketRepository;
    private PriceService priceService;
    private Console console;
    private Clock clock;

    public ShoppingBasketService(BasketRepository basketRepository, PriceService priceService, Console console, Clock clock) {
        this.basketRepository = basketRepository;
        this.priceService = priceService;
        this.console = console;
        this.clock = clock;
    }

    public void addItem(com.codurance.UserId userId, ProductId productId, int quantity) {
        BigDecimal price = priceService.priceFor(productId);
        BasketItem basketItem = new BasketItem(productId, quantity, price);

        basketRepository.addBasketItem(userId, basketItem);
        log(userId, productId, quantity, price);
    }

    public Basket basketFor(UserId userId) {
        return basketRepository.basketFor(userId);
    }

    private void log(UserId userId, ProductId productId, int quantity, BigDecimal price) {
        console.print("[ITEM ADDED TO SHOPPING CART]: " +
                "Added[" + clock.dateNow() + "], " +
                "User[" + valueOf(userId.getId()) + "], " +
                "Product[" + valueOf(productId.getId()) + "], " +
                "Quantity[" + valueOf(quantity) + "], " +
                "Price[£" + price.toPlainString() + "]\n");
    }
}
