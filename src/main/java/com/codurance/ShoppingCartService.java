package com.codurance;

public class ShoppingCartService {
    private BasketRepository basketRepository;

    public ShoppingCartService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public void addItem(com.codurance.UserId userId, ProductId productId, int quantity) {
        BasketItem basketItem = new BasketItem(productId, quantity);
        basketRepository.addBasketItem(userId, basketItem);
    }

    public Basket basketFor(UserId userId) {
        return basketRepository.basketFor(userId);
    }
}
