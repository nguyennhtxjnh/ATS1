package com.ats.service.impl;

import com.ats.entity.City;
import com.ats.repository.CityRepository;
import com.ats.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    private static final Logger LOGGER = LogManager.getLogger(CityServiceImpl.class);

    @Override
    public List<City> getAllCity() {
        LOGGER.info("Begin getAllCity in City Service ");
        List<City> cityList = null;
        cityList = cityRepository.findAll();
        LOGGER.info("End getAllCity in City Service with Number of City: {}", cityList.size());
        return cityList;
    }

    @Override
    public City getCityById(int id) {
        LOGGER.info("Begin getCityById in City Service with id : {} " + id);
        City city = cityRepository.getCityNameById(id);
        LOGGER.info("End getCityById in City Service with id : {} " + id);
        return city;
    }
}
