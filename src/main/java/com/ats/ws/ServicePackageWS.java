package com.ats.ws;

import com.ats.dto.ServicePackageDTO;
import com.ats.dto.ServicePackageDTO2;
import com.ats.entity.Servicepackage;
import com.ats.service.ServicePackageService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/servicePack")
public class ServicePackageWS {
    @Autowired
    ServicePackageService servicePackageService;

    private static final Logger LOGGER = LogManager.getLogger(ServicePackageWS.class);

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/create")
    @ResponseBody
    public RestResponse createServicePack(@RequestBody ServicePackageDTO servicePackageDTO) {
        LOGGER.info("Begin Create Service Pack in ServicePackage WS ");
        int result = -1;
        try {
            servicePackageDTO.setCreatedDate(new Date());
            result = servicePackageService.createServicePack(servicePackageDTO);
            System.out.println(result);
            System.out.println(servicePackageDTO.getFunctionId());
            if (result > -1) {
                return new RestResponse(true, "createServicePack Successfull", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End Create Service Pack in ServicePackage WS ");
        return new RestResponse(false, "createServicePack Fail", null);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/update")
    @ResponseBody
    public RestResponse updateServicePack(@RequestBody ServicePackageDTO servicePackageDTO) {
        LOGGER.info("Begin update Service Pack in ServicePackage WS ");
        int result = -1;
        try {
            result = servicePackageService.updateServicePack(servicePackageDTO);
            if (result > -1) {
                return new RestResponse(true, "updateServicePack Successfull", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End update Service Pack in ServicePackage WS ");
        return new RestResponse(false, "updateServicePack Fail ", null);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/changeStatus")
    @ResponseBody
    public RestResponse changeStatusServicePack(@RequestBody ServicePackageDTO servicePackageDTO) {
        LOGGER.info("Begin changeStatus Service Pack in ServicePackage WS ");
        int result = -1;
        try {
            result = servicePackageService.changeServicePackStatus(servicePackageDTO.getId(), servicePackageDTO.getStatus());
            if (result > -1) {
                return new RestResponse(true, "changeStatus ServicePack Successfull", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End changeStatus Service Pack in ServicePackage WS ");
        return new RestResponse(false, "changeStatus ServicePack Fail ", null);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getAll")
    @ResponseBody
    public RestResponse getAllServicePack(@PageableDefault Pageable pageable,
                                          @RequestParam(value = "search") String search,
                                          @RequestParam(value = "status") String status) {
        LOGGER.info("Begin getAll Service Pack in ServicePackage WS ");
        Page<Servicepackage> servicepackagePage;
        pageable = new PageRequest(0, Integer.MAX_VALUE);
        try {
            servicepackagePage = servicePackageService.getAllServicePack(pageable, search, status);
            if (servicepackagePage.getContent().size() > 0) {
                return new RestResponse(true, "getAll ServicePack Successfull", servicepackagePage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAll Service Pack in ServicePackage WS ");
        return new RestResponse(false, "getAll ServicePack Fail ", null);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getServicePackDetail")
    @ResponseBody
    public RestResponse getServicePackDetail(@RequestParam(value = "id") int id) {
        LOGGER.info("Begin getServicePackDetail Service Pack in ServicePackage WS ");
        ServicePackageDTO2 servicepackage;
        try {
            servicepackage = servicePackageService.getServicePackDetail(id);
            if (servicepackage != null) {
                return new RestResponse(true, "get Detail ServicePack Successfull", servicepackage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End get Detail Service Pack in ServicePackage WS ");
        return new RestResponse(false, "get Detail ServicePack Fail ", null);
    }

}
