package com.chubb.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chubb.repository.AddressRepository;
import com.chubb.repository.OrderRepository;
import com.chubb.request.Address;
import com.chubb.request.Order1;

import lombok.extern.slf4j.Slf4j;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    public void inserOrder(Order1 order) {
        if (order.getSharedAddresses() != null) {
            Set<Address> attached = new HashSet<>();
            for (Address a : order.getSharedAddresses()) {
                if (a.getId() != 0) {
                    Address existing = addressRepository.findById(a.getId())
                            .orElseThrow(() -> new RuntimeException("Address not found: " + a.getId()));
                    attached.add(existing);
                } else {
                    Address saved = addressRepository.save(a);
                    attached.add(saved);
                }
            }
            order.setSharedAddresses(attached);
        }
        orderRepository.save(order);
    }
}
