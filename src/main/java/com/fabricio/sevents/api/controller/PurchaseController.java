package com.fabricio.sevents.api.controller;

import com.fabricio.sevents.api.core.util.constants.ConstantsExternalResources;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConstantsExternalResources.RESOURCE_COMPRA)
@AllArgsConstructor
@Slf4j
public class PurchaseController {

    public void startPurchase(){

    }

}