package com.denisgl.models;

import com.denisgl.cache.realization.OrderCache;
import com.denisgl.cache.realization.OrderDetailsCache;
import com.denisgl.cache.realization.ProductCache;
import com.denisgl.dao.entities.Order;
import com.denisgl.dao.entities.OrderDetails;
import com.denisgl.dao.entities.Product;
import com.denisgl.dao.entities.User;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BuyerProducts {

    private static final Logger logger = Logger.getLogger(BuyerProducts.class);

    public static void buyAllFromCard(ShoppingCard card){
        Integer userId =  card.getUser().getId();
        Timestamp orderDate = new Timestamp( new Date().getTime());

        Order order = new Order(1, userId,1, orderDate, null);
        saveOrder(order);

        Integer orderId = getOrderIdByUserIdAndDate(userId,orderDate);
        saveEveryDetailsFromCard(card, orderId);
    }

    private static void saveOrder(Order order) {
        if (OrderCache.getCache().save(order)) {
            logger.debug(order + " was saved");
        } else {
            logger.error(order + "was not saved in database");
        }
    }

    private static Integer getOrderIdByUserIdAndDate(Integer userId, Timestamp orderDate) {
        List<Order> listOrders  = OrderCache.getCache().getAllByCriteria("orderdate", orderDate);
        Order order = null;
        for (Order o : listOrders) {
            if(o.getUserid() == userId){
                order = o;
                break;
            }
        }
        if (order == null) {
            logger.warn("The was no such order " + order);
            throw new NullPointerException("The was no such order " + order);
        }
        return order.getUserid();
    }

    private static void saveEveryDetailsFromCard(ShoppingCard card, Integer orderId) {
        for (Map.Entry<Product, Integer> orderUnit : card.getProducts().entrySet()) {
            Product product = orderUnit.getKey();
            Integer quantity = orderUnit.getValue();
            OrderDetails od = new OrderDetails(1, orderId, card.getUser().getId(),
                    product.getPrice(), quantity,
                    getDiscount(card.getUser()));
            saveDetails(od);
            deductQuantityOfProductInDataBase(product,quantity);
            product = null;
            quantity = null;
            od = null;
        }
    }
    private static BigDecimal getDiscount(User user){
        //The one with groupId = 1  is administrator
        return user.getGroupid() == 1 ? new BigDecimal(0.2) : new BigDecimal(0);
    }
    private static void saveDetails(OrderDetails od){
        if (OrderDetailsCache.getCache().save(od)) {
            logger.debug(od + "was saved in database");
        } else {
            logger.error(od + "was not saved in database");
        }
    }

    private static void deductQuantityOfProductInDataBase(Product product, Integer quantity){
        int howMuchLeft = product.getQuantity() - quantity;
        product.setQuantity(howMuchLeft);
        ProductCache.getCache().update(product);
    }

}
