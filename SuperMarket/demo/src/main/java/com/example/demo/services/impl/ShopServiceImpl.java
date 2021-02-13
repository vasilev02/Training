package com.example.demo.services.impl;

import com.example.demo.models.entities.Product;
import com.example.demo.models.entities.Seller;
import com.example.demo.models.entities.Shop;
import com.example.demo.models.entities.Town;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SellerRepository;
import com.example.demo.repositories.ShopRepository;
import com.example.demo.repositories.TownRepository;
import com.example.demo.services.ShopService;
import com.example.demo.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final TownRepository townRepository;
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository, SellerRepository sellerRepository, ProductRepository productRepository, ValidationUtil validationUtil) {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.validationUtil = validationUtil;
    }


    @Override
    public String addShop(String data) {

        StringBuilder sb = new StringBuilder();
        String[] shopInfo = data.split("\\s+");

        if (shopInfo.length != 3) {
            return sb.append("Empty fields! - Try again!").append(System.lineSeparator())
                    .toString().trim();
        }

        Shop findShop = this.shopRepository.findByName(shopInfo[0]);
        Town findTown = this.townRepository.findByName(shopInfo[2]);

        if (findTown == null) {
            return sb.append("Town does not exist!").append(System.lineSeparator())
                    .toString().trim();
        }

        if (findShop == null) {

            Shop shop = new Shop();
            shop.setName(shopInfo[0]);
            shop.setAddress(shopInfo[1]);
            shop.setTown(findTown);

            if (validationUtil.isValid(shop)) {
                this.shopRepository.saveAndFlush(shop);
                sb.append("Successfully added shop!").append(System.lineSeparator());
                sb.append("============================").append(System.lineSeparator());
            } else {
                sb.append("Shop name or address must be between 2 and 20 characters!").append(System.lineSeparator());
            }

        } else {
            sb.append(String.format("Shop %s already exists!", shopInfo[0])).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String showAllSellers(String shopName) {

        StringBuilder sb = new StringBuilder();

        Shop findShop = this.shopRepository.findByName(shopName);

        if (findShop == null) {
            return sb.append("Shop does not exist!").append(System.lineSeparator())
                    .toString().trim();
        }

        Set<Seller> sellers = this.sellerRepository.getAllSellersByGivenShopName(shopName);

        for (Seller current : sellers) {

            sb.append(String.format("%s %s", current.getFirstName(), current.getLastName()))
                    .append(System.lineSeparator());

        }

        return sb.toString().trim();
    }

    @Override
    public String showAllProducts(String shopName) {

        StringBuilder sb = new StringBuilder();
        Shop findShop = this.shopRepository.findByName(shopName);

        if (findShop == null) {
            return sb.append("Shop does not exist!").append(System.lineSeparator())
                    .toString().trim();
        }

        for (Product product : findShop.getProducts()) {

            sb.append(String.format("%s - %.2f lv.", product.getName(), product.getPrice()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

}
