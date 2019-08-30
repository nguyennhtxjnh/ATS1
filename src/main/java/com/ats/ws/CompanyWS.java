package com.ats.ws;

import com.ats.dto.CompanyDTO;
import com.ats.dto.CompanyDTO2;
import com.ats.dto.CompanyDTO3;
import com.ats.entity.Company;
import com.ats.repository.CompanyRepository;
import com.ats.service.CompanyService;
import com.ats.service.EmployercompanyService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyWS {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private EmployercompanyService employercompanyService;

    private static final Logger LOGGER = LogManager.getLogger(CompanyWS.class);

    //Create a new company
    @PostMapping("")
    @CrossOrigin("*")
    public RestResponse create(@Valid @RequestBody CompanyDTO newCompany, BindingResult result) {
        if (result.hasErrors())
            return null;
        return companyService.create(newCompany);
    }

    // edit info's company
    @PutMapping("")
    @CrossOrigin("")
    public ResponseEntity<Company> edit(@RequestBody Company editedCompany) {
        try {

            editedCompany.setLastModify(new Timestamp(new Date().getTime()));
            return ResponseEntity.ok().body(companyRepository.save(editedCompany));
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Paging
    @RequestMapping("/pagingAll")
    @CrossOrigin("")
    public ResponseEntity<Page<Company>> test(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(companyRepository.findAllPaging(pageable));
    }

    @GetMapping("/listCompany")
    @CrossOrigin("")
    public ResponseEntity<List<CompanyDTO2>> getCompanyById() {
        List<CompanyDTO2> company = companyService.listAll();
        return new ResponseEntity<List<CompanyDTO2>>(company, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/changeCompanyStatus")
    @ResponseBody
    public RestResponse changeCompanyStatus(@RequestBody CompanyDTO companyDTO) {
        LOGGER.info("Begin changeJobStatus in CompanyWS with Search value : {}" + companyDTO.getId());
        int success;
        try {
            success = companyService.changeStatus(companyDTO.getId(), companyDTO.getStatus());
            if (companyDTO.getStatus().equals("approved")) {
                employercompanyService.changeUserStatusAfterApproved(companyDTO.getId());
            }
            if (success > 0) {
                return new RestResponse(true, "changeStatus Successful with status " + companyDTO.getStatus(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End changeJobStatus in CompanyWS with Search value : {}" + companyDTO.getId());
        return new RestResponse(false, "changeStatus Fail", null);
    }


    @GetMapping("/getCompanyAdmin")
    @CrossOrigin("*")
    public RestResponse getCompanyAdmin(@RequestParam(value = "search") String search,
                                        @RequestParam(value = "status") String status,
                                        @PageableDefault Pageable pageable) {
        LOGGER.info("Begin getCompanyAdmin in CompanyWS");
        pageable = new PageRequest(0, Integer.MAX_VALUE);
        try {
            Page<CompanyDTO3> company = companyService.findAllCompanyByStatus(search, status, pageable);

            LOGGER.info("End getCompanyAdmin in CompanyWS");
            if (company != null) {
                return new RestResponse(true, "Success getCompanyAdmin : ", company);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail getCompanyAdmin ", null);

    }

    @GetMapping("/list-5")
    @CrossOrigin("*")
    public List<CompanyDTO> getFiveCompany() {
        return companyService.getFiveCompany();
    }


}
