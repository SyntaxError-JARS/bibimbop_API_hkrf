package com.revature.bibimbop.order;

import com.revature.bibimbop.util.exceptions.InvalidRequestException;

public class OrderServices {
    private OrderDao oDao;

    public OrderServices(OrderDao oDao) {
        this.oDao = oDao;
    }

    public OrderModel create(OrderModel newOrder){
        if(!validateInput(newOrder)){
            throw new InvalidRequestException("User input invalid. Order must have words for menu item, a new integer for id, comment can be nothing, is favorite must be 1 for true and 0 for false, a valid order date, and your correct customer username");
        }
        OrderModel persistedOrder = oDao.createCustomOrder(newOrder);

        return persistedOrder;
    }

    public boolean validateInput(OrderModel newOrder){
        if (newOrder.getId() == null) {return false;}
        if (newOrder.getMenuItem() == null || newOrder.getMenuItem().equals("")){return false;}
        if (newOrder.getIsFavorite() != 0 && newOrder.getIsFavorite() != 1){return false;}
        if (newOrder.getOrderDate() == null || newOrder.getOrderDate().equals("")){return false;}
        if (newOrder.getCustomerUsername() == null || newOrder.getCustomerUsername().equals("")){return false;}
        return true;
    }
}
