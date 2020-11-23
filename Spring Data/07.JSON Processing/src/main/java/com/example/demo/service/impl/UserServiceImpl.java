package com.example.demo.service.impl;

import com.example.demo.domain.dto.*;
import com.example.demo.domain.entities.Product;
import com.example.demo.domain.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsersInDB(UserSeedDto[] userSeedDtos) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();


        for (UserSeedDto current : userSeedDtos) {

            Set<ConstraintViolation<UserSeedDto>> violations = validator.validate(current);

            if (violations.size() > 0) {
                for (ConstraintViolation<UserSeedDto> violation : violations) {
                    System.out.println(violation);
                }
            } else {

                User user = modelMapper.map(current, User.class);

                user.setFriends(this.setRandomFriend());

                this.userRepository.saveAndFlush(user);

            }

        }

    }

    @Override
    public Set<UserDto> getUsersInfo() {

        Set<UserDto> dtos = new HashSet<>();

        Set<User> users = this.userRepository.getUsers();

        for (User current : users) {

            UserDto userDto = this.modelMapper.map(current,UserDto.class);

            Set<ProductDto> productDtos = new HashSet<>();

            for (Product product : current.getSeller()) {

                ProductDto productDto = this.modelMapper.map(product,ProductDto.class);

                if(product.getBuyer() != null) {
                    productDtos.add(productDto);
                }

            }

            userDto.setSoldProducts(productDtos);

            dtos.add(userDto);

        }

        return dtos;

    }

    @Override
    public UsersInfoDto getUsersWithAtLeastOneBuyer() {

        Set<User> users = this.userRepository.getUsersSales();

        UsersInfoDto info = new UsersInfoDto();
        info.setUsersCount(users.size());

        Set<UserSoldProductsDto> dtos = new HashSet<>();

        for (User current : users) {

            UserSoldProductsDto user = this.modelMapper.map(current, UserSoldProductsDto.class);

            SoldDto sold = new SoldDto();
            sold.setCount(current.getSeller().size());

            Set<ProductDto> productDtoSet = new HashSet<>();

            for (Product pro : current.getSeller()) {

               ProductDto productDto = new ProductDto();
               productDto.setName(pro.getName());
               productDto.setPrice(pro.getPrice());

               productDtoSet.add(productDto);

            }
            sold.setProducts(productDtoSet);

            user.setSoldProducts(sold);

            dtos.add(user);
        }

        info.setUsers(dtos);

        return info;
    }

    private Set<User> setRandomFriend() {

        Random random = new Random();

        Set<User> friends = new HashSet<>();

        if(this.userRepository.count() == 0){
            return new HashSet<>();
        }

        int id = random.nextInt((int) this.userRepository.count() + 1) + 1;


        friends.add(this.userRepository.findById(id));

        return friends;

    }
}
