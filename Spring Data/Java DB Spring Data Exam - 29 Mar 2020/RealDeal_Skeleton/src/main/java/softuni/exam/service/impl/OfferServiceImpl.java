package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmlImports.OfferImportDto;
import softuni.exam.models.dto.xmlImports.OfferRootDto;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Offer;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

@Service
public class OfferServiceImpl implements OfferService {

    private final static String OFFERS_PATH = "src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, CarRepository carRepository, SellerRepository sellerRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(OFFERS_PATH)));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {

        StringBuilder sb = new StringBuilder();

        OfferRootDto data = this.xmlParser.importFromXml(OfferRootDto.class, OFFERS_PATH);


        for (OfferImportDto current : data.getOfferImportDtos()) {

            if (this.validationUtil.isValid(current)){

                Offer offer = this.modelMapper.map(current,Offer.class);

                Car findCar = this.carRepository.findCarById(current.getCar().getId());
                Seller findSeller = this.sellerRepository.findSellerById(current.getSeller().getId());

                offer.setCar(findCar);
                offer.setSeller(findSeller);
                offer.setPictures(new HashSet<>(findCar.getPictures()));

                this.offerRepository.saveAndFlush(offer);

                sb.append(String.format("Successfully import offer %s - %s",offer.getAddedOn(),offer.isHasGoldStatus()));

            }else{

                sb.append("Invalid offer");

            }
            sb.append(System.lineSeparator());


        }

        return sb.toString().trim();

    }
}
