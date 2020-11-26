package hiberspring.service.impl;

import hiberspring.common.GlobalConstants;
import hiberspring.domain.dto.xml.ProductDto;
import hiberspring.domain.dto.xml.ProductRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.ProductService;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final TownRepository townRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, TownRepository townRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.townRepository = townRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        Path path = Paths.get(GlobalConstants.PRODUCTS_FILE_PATH);
        return Files.readAllLines(path).toString();
    }

    @Override
    public String importProducts() throws JAXBException, FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        ProductRootDto data = this.xmlParser.parseXml(ProductRootDto.class, GlobalConstants.PRODUCTS_FILE_PATH);

        for (ProductDto current : data.getProductDtos()) {

            if(validationUtil.isValid(current) && current.getClients() != 0){

                Product product = this.modelMapper.map(current, Product.class);

                Branch branch = this.branchRepository.findBranchByName(current.getBranch());

                product.setBranch(branch);

                this.productRepository.saveAndFlush(product);

                sb.append(String.format(GlobalConstants.SUCCESSFUL_IMPORT_MESSAGE,"Product", product.getName()));

            }else{
                sb.append(GlobalConstants.INCORRECT_DATA_MESSAGE);
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
