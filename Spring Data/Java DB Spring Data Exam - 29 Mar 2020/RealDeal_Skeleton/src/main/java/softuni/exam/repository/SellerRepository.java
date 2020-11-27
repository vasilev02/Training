package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entities.Seller;

//ToDo
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    Seller findSellerById(int id);

}
