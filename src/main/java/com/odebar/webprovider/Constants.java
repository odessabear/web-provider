package com.odebar.webprovider;

public final class Constants {
    public static final String CURRENT_SHOPPING_CART = "CURRENT_SHOPPING_CART";
    public static final int MAX_PRODUCT_COUNT_PER_ONE_SHOPPING_CART = 1;
    public static final int MAX_DIFFERENT_PRODUCTS_PER_SHOPPING_CART = 5;
    public static final String USER_ACTIONS_HISTORY = "ACCOUNT_ACTIONS_HISTORY";

    public enum Cookie {
        SHOPPING_CART("iSCC", 60 * 60 * 24 * 365);

        private final String name;
        private final int ttl;

        Cookie(String name, int ttl) {
            this.name = name;
            this.ttl = ttl;
        }

        public String getName() {
            return name;
        }

        public int getTtl() {
            return ttl;
        }
    }
}
