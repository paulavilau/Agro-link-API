package com.myproject.agrolink.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.myproject.agrolink.entity.Category;
import com.myproject.agrolink.entity.Subcategory;
import com.myproject.agrolink.entity.Product;
import com.myproject.agrolink.entity.Farm;
import com.myproject.agrolink.entity.Cart;
import com.myproject.agrolink.entity.Client;
import com.myproject.agrolink.entity.CartItem;
import com.myproject.agrolink.entity.County;
import com.myproject.agrolink.entity.FarmUser;
import com.myproject.agrolink.entity.DeliveryCounty;
import com.myproject.agrolink.entity.FavouriteProduct;
import com.myproject.agrolink.entity.FundsDistrib;
import com.myproject.agrolink.entity.Image;
import com.myproject.agrolink.entity.Order;
import com.myproject.agrolink.entity.OrderItem;
import com.myproject.agrolink.entity.OrderStatus;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    private String[] theAllowedOrigins = {
            "https://www.paulayadeagro.online",
            "http://localhost:3000"
    };

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        /* Configurare CORS */
        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(theAllowedOrigins);

        config.exposeIdsFor(Product.class);

        config.exposeIdsFor(Category.class);

        config.exposeIdsFor(Subcategory.class);

        config.exposeIdsFor(FavouriteProduct.class);

        config.exposeIdsFor(FarmUser.class);

        config.exposeIdsFor(Image.class);

        config.exposeIdsFor(Farm.class);

        config.exposeIdsFor(Cart.class);

        config.exposeIdsFor(Client.class);

        config.exposeIdsFor(County.class);

        config.exposeIdsFor(DeliveryCounty.class);

        config.exposeIdsFor(CartItem.class);

        config.exposeIdsFor(Order.class);

        config.exposeIdsFor(OrderItem.class);

        config.exposeIdsFor(OrderStatus.class);

        config.exposeIdsFor(FundsDistrib.class);

    }

}
