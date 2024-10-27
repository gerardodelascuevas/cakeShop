package com.example.demo.domain.service;

import com.example.demo.domain.model.Flavor;

/**
 * @author Gerardo De Las Cuevas
 */
public interface FlavorDomainService {
    Flavor create(Flavor flavor);

    String update(Long id, String name);

    String destroy(Long id);
}
