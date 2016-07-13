package com.codurance;

import java.util.HashMap;

public class BasketRepository {
    private HashMap<Integer, Basket> baskets;
    private Clock clock;

    public BasketRepository(Clock clock, HashMap<Integer, Basket> baskets) {
        this.clock = clock;
        this.baskets = baskets;
    }

    public Basket basketFor(UserId userId) {
        return baskets.get(userId.getId());
    }

    public void addBasketItem(UserId userId, BasketItem basketItem) {
        if(basketFor(userId) == null) {
            Basket basket = new Basket(clock.dateNow());
            basket.add(basketItem);
            baskets.put(userId.getId(), basket);
        } else {
            Basket basket = basketFor(userId);
            basket.add(basketItem);
            baskets.put(userId.getId(), basket);
        }
    }
}
