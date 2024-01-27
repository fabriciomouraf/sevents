package com.fabricio.sevents.api.service;

import com.fabricio.sevents.api.model.domain.enumeration.Status;
import com.fabricio.sevents.api.model.dto.persist.PurchasePersist;
import com.fabricio.sevents.api.repository.PurchaseRepository;
import com.fabricio.sevents.api.util.GenericObjectContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseService extends GenericObjectContext {

    private final PurchaseRepository purchaseRepository;

    public void start(){

        var purchase = PurchasePersist.builder()
                .idUser(UUID.randomUUID()) //TODO: não existe login ainda
                .status(Status.OPEN)
                .build();

//        return convert(purchaseRepository.save(convert(purchase, Purchase.class), x));

    }

}