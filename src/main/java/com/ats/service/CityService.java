package com.ats.service;

import com.ats.entity.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {

    List<City> getAllCity();

    City getCityById(int id);
}
