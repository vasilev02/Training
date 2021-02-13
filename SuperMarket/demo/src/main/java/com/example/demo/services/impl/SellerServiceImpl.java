package com.example.demo.services.impl;

import com.example.demo.models.entities.Seller;
import com.example.demo.models.entities.Shop;
import com.example.demo.repositories.SellerRepository;
import com.example.demo.repositories.ShopRepository;
import com.example.demo.services.SellerService;
import com.example.demo.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final ShopRepository shopRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, ShopRepository shopRepository, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.shopRepository = shopRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public String addSeller(String data) {
        StringBuilder sb = new StringBuilder();
        String[] sellerInfo = data.split("\\s+");

        if (sellerInfo.length != 5) {
            return sb.append("Empty fields! - Try again!").append(System.lineSeparator())
                    .toString().trim();
        }

        Seller findSeller = this.sellerRepository.findByFirstNameAndLastName(sellerInfo[0], sellerInfo[1]);
        Shop findShop = this.shopRepository.findByName(sellerInfo[4]);

        if (findShop == null) {
            return sb.append("Shop does not exist!").append(System.lineSeparator())
                    .toString().trim();
        }

        if (findSeller == null) {

            Seller seller = new Seller();
            seller.setFirstName(sellerInfo[0]);
            seller.setLastName(sellerInfo[1]);
            seller.setAge(Integer.parseInt(sellerInfo[2]));
            BigDecimal salary = new BigDecimal(sellerInfo[3]);
            seller.setSalary(salary);
            seller.setShop(findShop);

            if (validationUtil.isValid(seller)) {
                this.sellerRepository.saveAndFlush(seller);
                sb.append("Successfully added seller!").append(System.lineSeparator());
                sb.append("============================").append(System.lineSeparator());
            } else {
                sb.append("Invalid input data!").append(System.lineSeparator());
            }

        } else {
            sb.append(String.format("Seller ( %s %s ) already exists!", sellerInfo[0], sellerInfo[1])).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String addManagerToSeller(String sellerNames, String managerNames) {

        StringBuilder sb = new StringBuilder();

        String[] seller = sellerNames.split("\\s+");

        String[] manager = managerNames.split("\\s+");

        if (seller.length != 2 || manager.length != 2) {
            return sb.append("Empty fields! - Try again!").append(System.lineSeparator())
                    .toString().trim();
        }

        Seller findSeller = this.sellerRepository.findByFirstNameAndLastName(seller[0], seller[1]);

        if (findSeller == null) {
            return sb.append("Seller does not exist!").append(System.lineSeparator())
                    .toString().trim();
        }

        Seller findManager = this.sellerRepository.findByFirstNameAndLastName(manager[0], manager[1]);

        if (findManager == null) {
            return sb.append("Manager does not exist!").append(System.lineSeparator())
                    .toString().trim();
        }

        findSeller.setManager(findManager);
        sb.append("Successfully added manager!");

        this.sellerRepository.saveAndFlush(findSeller);

        return sb.toString().trim();
    }
}
