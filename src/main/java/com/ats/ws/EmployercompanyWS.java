package com.ats.ws;

import com.ats.dto.EmployercompanyDTO;
import com.ats.dto.EmployercompanyDTO2;
import com.ats.entity.Company;
import com.ats.service.CompanyService;
import com.ats.service.EmployercompanyService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/employercompany")
public class EmployercompanyWS {
    @Autowired
    EmployercompanyService employercompanyService;
    @Autowired
    CompanyService companyService;

    private static final Logger LOGGER = LogManager.getLogger(EmployercompanyWS.class);

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addNewEmployerCompany")
    public RestResponse addNewEmployerCompany(@RequestBody EmployercompanyDTO dto) {
        LOGGER.info("Begin addNewEmployerCompany in EmployercompanyWS with User id : {}" + dto.getUserId());
        try {
            dto.setCreatedDate(new Timestamp(new Date().getTime()));
            boolean finish = employercompanyService.createNewEmployerCompany(dto);
            LOGGER.info("End addNewEmployerCompany in EmployercompanyWS with User id : {}" + dto.getUserId());
            if (finish) {
                return new RestResponse(true, "Create New Employer Company Successfull", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail To Create New Employer Company ", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addNewEmployerCompanyExistedCompany")
    public RestResponse addNewEmployerCompanyExistedCompany(@RequestBody EmployercompanyDTO dto) {
        LOGGER.info("Begin addNewEmployerCompany in EmployercompanyWS with User id : {}" + dto.getUserId());
        try {
            dto.setCreatedDate(new Timestamp(new Date().getTime()));
            System.out.println();
            boolean finish = employercompanyService.createNewEmployerExistedCompany(dto);
            LOGGER.info("End addNewEmployerCompany in EmployercompanyWS with User id : {}" + dto.getUserId());
            if (finish) {
                return new RestResponse(true, "Create New Employer Company Successfull", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail To Create New Employer Company ", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/getCompanyId")
    public RestResponse getCompany(@RequestBody EmployercompanyDTO dto) {
        LOGGER.info("Begin addNewEmployerCompany in EmployercompanyWS with User id : {}" + dto.getUserId());
        try {
            EmployercompanyDTO employercompanyDTO = employercompanyService.findCompanyByUserId(dto.getUserId());
            if (employercompanyDTO != null) {
                return new RestResponse(true, "Get Employer Company Successful", employercompanyDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail Get Employer Company ", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/getCompanyByUserId")
    public RestResponse getCompanyByUserId(@RequestBody EmployercompanyDTO dto) {
        LOGGER.info("Begin addNewEmployerCompany in EmployercompanyWS with User id : {}" + dto.getUserId());
        try {
            EmployercompanyDTO employercompanyDTO = employercompanyService.findCompanyByUserId(dto.getUserId());
            Company company = companyService.findComanyByID(employercompanyDTO.getCompanyId());
            if (company != null) {
                return new RestResponse(true, "Get Company Successful", company);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail Get Company ", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/changeEmployerCompanyStatus")
    public RestResponse changeEmployerCompanyStatus(@RequestBody EmployercompanyDTO dto) {
        LOGGER.info("Begin addNewEmployerCompany in EmployercompanyWS with User id : {}" + dto.getUserId());
        try {
            int result = employercompanyService.changeStatus(dto);
            if (result > 0) {
                return new RestResponse(true, "changeEmployerCompanyStatus Successful", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail changeEmployerCompanyStatus ", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getAllEmployerCompanyByStatus")
    public RestResponse getAllEmployerCompanyByStatus(@RequestParam(value = "search") String search,
                                                      @RequestParam(value = "status") String status,
                                                      @RequestParam(value = "userId") int userId,
                                                      @PageableDefault Pageable pageable) {
        LOGGER.info("Begin getAllEmployerCompanyByStatus in EmployercompanyWS with search value : " + search);
        try {
            Page<EmployercompanyDTO2> employercompanyPage = employercompanyService.getAllEmployerCompanyByUserId(search, status, userId, pageable);
            if (employercompanyPage.getContent().size() > 0) {
                return new RestResponse(true, "getAllEmployerCompanyByStatus Successful", employercompanyPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail getAllEmployerCompanyByStatus", null);
    }

}
