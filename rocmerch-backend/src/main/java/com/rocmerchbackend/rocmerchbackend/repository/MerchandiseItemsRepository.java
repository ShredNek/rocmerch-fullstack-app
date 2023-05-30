package com.rocmerchbackend.rocmerchbackend.repository;

import com.rocmerchbackend.rocmerchbackend.model.MerchandiseItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchandiseItemsRepository extends JpaRepository<MerchandiseItems, Long> {

    public List<MerchandiseItems> getMerchandiseItemsByCategory(String category);

    public List<MerchandiseItems> getMerchandiseItemsByName(String name);

}
