package com.revature.bibimbop.menu;

import com.revature.bibimbop.order.OrderDao;
import com.revature.bibimbop.util.interfaces.Serviceable;

import java.util.List;

public class MenuServices implements Serviceable<Menu> {

    private final OrderDao orderDao;
    private final MenuDao menuDao;

    // DI - Dependency Injection
    public MenuServices(OrderDao orderDao, MenuDao menuDao) {
        this.orderDao = orderDao;
        this.menuDao = menuDao;
    }

    @Override
    public Menu create(Menu newMenu) {
        return menuDao.create(newMenu);
    }

    @Override
    public List<Menu> readAll() {
        return menuDao.findAll();

    }

    @Override
    public Menu readById(String menuName) {
        return menuDao.findById(menuName);
    }

    @Override
    public Menu update(Menu updatedMenu) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(Menu object) {
        return false;
    }
}
