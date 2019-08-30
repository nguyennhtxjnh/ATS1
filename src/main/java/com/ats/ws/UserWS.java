package com.ats.ws;

import com.ats.dto.CompanyDTO2;
import com.ats.dto.UsersDTO;
import com.ats.dto.UsersDTO2;
import com.ats.entity.City;
import com.ats.entity.Joblevel;
import com.ats.entity.Users;
import com.ats.service.CityService;
import com.ats.service.CompanyService;
import com.ats.service.JoblevelService;
import com.ats.service.UsersService;
import com.ats.token.TokenAuthenticationService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserWS {

    @Autowired
    UsersService usersService;
    @Autowired
    TokenAuthenticationService tokenService;
    @Autowired
    JoblevelService joblevelService;
    @Autowired
    CityService cityService;
    @Autowired
    CompanyService companyService;

    private static final Logger LOGGER = LogManager.getLogger(UserWS.class);

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/login")
    @ResponseBody
    public RestResponse login(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin login inUserWS with username - password: {}", usersDTO.getEmail() + " - " + usersDTO.getPassword());

        try {
            if (!usersDTO.getPassword().isEmpty()) {
                System.out.println(usersDTO.getPassword());
                usersDTO = usersService.login(usersDTO.getEmail(), usersDTO.getPassword());
                LOGGER.info("End login in UserWS with username ", usersDTO.getEmail());
                if (usersDTO != null && usersDTO.getId() != 0) {
                    return new RestResponse(true, "Login Successful", usersDTO);
                }
            } else {
                return new RestResponse(false, "Null Password", usersDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Login Fail Because " + usersDTO.getEmail(), null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/registration")
    public RestResponse registration(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin Registration in UserWS with email - password - fullname: {}",
                usersDTO.getEmail() + " - " + usersDTO.getPassword() + " - " + usersDTO.getFullname());
        int result;
        try {
            java.util.Date date = new java.util.Date();
            String tokenString = tokenService.addAuthentication(usersDTO.getEmail());
            usersDTO.setAccessToken(tokenString);
            usersDTO.setCreatedDate(date);
            usersDTO.setLastModify(date);
            usersDTO.setStatus("new");
            result = usersService.registration(usersDTO);
            LOGGER.info("End Registration in UserWS with email - password - fullname: {}",
                    usersDTO.getEmail() + " - " + usersDTO.getPassword() + " - " + usersDTO.getFullname());

            if (result > -1) {
                return new RestResponse(true, "Create To Successful", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail Create To User", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/checkLogin", produces = "application/json;charset=UTF-8")
    public RestResponse checkLogin(@RequestHeader(value = "accessToken") String accessToken) {
        LOGGER.info("Begin login in UserWS with Token : {}", accessToken);
        UsersDTO2 usersDTO;
        try {
            usersDTO = usersService.findUserByToken(accessToken);
            LOGGER.info("End login in UserWS with Token : {}", accessToken);
            if (usersDTO != null) {
                return new RestResponse(true, "CheckLogin To Successful", usersDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "CheckLogin To Fail", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/changePassword", produces = "application/json;charset=UTF-8")
    public RestResponse changePassword(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin changePassword in UserWS with Account id {}" + usersDTO.getId());
        int success;
        try {
            success = usersService.changePassword(usersDTO.getId(), usersDTO.getNewPassword(), usersDTO.getPassword());
            if (success > 0) {
                return new RestResponse(true, "changePassword Successful with new password is " + usersDTO.getNewPassword(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End changePassword in UserWS with Account id {}" + usersDTO.getId());
        return new RestResponse(false, "changePassword Fail", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/changeUserStatus", produces = "application/json;charset=UTF-8")
    public RestResponse changeStatus(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin changeStatus in UserWS with Account id : {}" + usersDTO.getId());
        int success;
        try {
            success = usersService.changeStatus(usersDTO.getId(), usersDTO.getStatus());
            if (success > 0) {
                return new RestResponse(true, "changeStatus Successful with status " + usersDTO.getStatus(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Begin changeStatus in UserWS with Account id : {}" + usersDTO.getId());
        return new RestResponse(false, "changeStatus Fail", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/forgotPassword", produces = "application/json;charset=UTF-8")
    public RestResponse forgotPassword(@RequestParam("token") String token,
                                       @RequestParam("password") String password) {
        LOGGER.info("Begin forgotPassword in UserWS");
        int success;
        try {
            success = usersService.forgotPassword(token, password);
            LOGGER.info("End forgotPassword in UserWS");
            if (success > 0) {
                return new RestResponse(true, "forgotPassword Successful ", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "forgotPassword Fail", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/confirmUser", produces = "text/html")
    public String confirmUser(@RequestParam(value="token") String token) {
        LOGGER.info("Begin confirmUser in UserWS ");
        int success;
        try {
            System.out.println(token);
            String[] output = token.split("[, ?.@]+");
            System.out.println(output[0]);
            int id = Integer.parseInt(output[0]);
            success = usersService.confirmUser(id, "active");
            LOGGER.info("End confirmUser in UserWS");
            if (success > 0) {
                return "true";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/findUserByEmail", produces = "application/json;charset=UTF-8")
    public RestResponse findUserByEmail(@RequestParam("email") String email) {
        LOGGER.info("Begin findUserByEmail in UserWS with Email : {}" + email);
        UsersDTO dto = null;
        try {
            dto = usersService.findUserByEmail(email);
            if (dto != null) {
                return new RestResponse(true, "findUserByEmail Successful", dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Begin findUserByEmail in UserWS with Email : {}" + email);
        return new RestResponse(false, "findUserByEmail Fail", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/findUserByUserId", produces = "application/json;charset=UTF-8")
    public RestResponse findUserByUserId(@RequestParam("id") int id) {
        LOGGER.info("Begin findUserByUserId in UserWS with Id : {}" + id);
        UsersDTO dto = null;
        try {
            dto = usersService.findUserByUserId(id);
            if (dto != null) {
                return new RestResponse(true, "findUserByUserId Successful", dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Begin findUserByUserId in UserWS with Id : {}" + id);
        return new RestResponse(false, "findUserByEmail Fail", null);
    }


    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/registrationGoogle")
    public RestResponse registrationGoogle(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin Registration Google in UserWS with email - password - fullname: {}",
                usersDTO.getEmail() + " - " + usersDTO.getFullname());
        int result;
        try {
            java.util.Date date = new java.util.Date();
            if (usersDTO.getPassword().isEmpty()) {
                String tokenString = tokenService.addAuthentication(usersDTO.getEmail());
                usersDTO.setAccessToken(tokenString);
                usersDTO.setLastLogin(date);
                usersDTO.setCreatedDate(date);
                usersDTO.setStatus("active");
                result = usersService.registration(usersDTO);
                LOGGER.info("End Registration Google in UserWS with email - password - fullname: {}",
                        usersDTO.getEmail() + " - " + usersDTO.getFullname());

                if (result > -1) {
                    return new RestResponse(true, "Create To Successful", null);
                }
            } else {
                return new RestResponse(false, "Create Fail", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail Create To Account", null);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/loginGoogle")
    @ResponseBody
    public RestResponse loginGoogle(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin loginGoogle inUserWS with username - password: {}", usersDTO.getEmail() + " - " + usersDTO.getPassword());
        try {
            usersDTO = usersService.login(usersDTO.getEmail(), usersDTO.getPassword());
            LOGGER.info("End loginGoogle in UserWS with username - password : {}", usersDTO.getEmail() + " - " + usersDTO.getPassword());
            if (usersDTO != null && usersDTO.getId() != 0) {
                return new RestResponse(true, "Login Successful", usersDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Login Fail Because " + usersDTO.getEmail(), null);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getRegisterEmployerComponent")
    public RestResponse getRegisterEmployerComponent() {
        LOGGER.info("Begin getRegisterEmployerComponent in UserWS");
        List<City> listCity;
        List<Joblevel> listJobLevel;
        List<CompanyDTO2> listCompany;
        try {
            listCity = cityService.getAllCity();
            listCompany = companyService.listAll();
            listJobLevel = joblevelService.getAllJobLevel();
            HashMap<String, List> map = new HashMap<>();
            map.put("city", listCity);
            map.put("level", listJobLevel);
            map.put("company", listCompany);
            return new RestResponse(true, "Get Register Employer Component Successful", map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getRegisterEmployerComponent in UserWS");

        return new RestResponse(false, "Job is Not Available : ", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/updateUser")
    public RestResponse updateUser(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin updateUser in UserWS with email - password - fullname: ", usersDTO.getFullname());
        int result;
        try {
            result = usersService.updateUser(usersDTO);
            LOGGER.info("End updateUser in UserWS with email - password - fullname: {}",
                    usersDTO.getEmail() + " - " + usersDTO.getPassword() + " - " + usersDTO.getFullname());

            if (result > -1) {
                return new RestResponse(true, "Update Successful", usersDTO.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail Update To User", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getAllUser")
    public RestResponse getAllUser(@RequestParam(value = "search") String search,
                                   @PageableDefault Pageable pageable,
                                   @RequestParam(value = "status") String status) {
        LOGGER.info("Begin getAllUser in UserWS");
        Page<Users> usersPage;
        pageable = new PageRequest(0, Integer.MAX_VALUE);
        try {
            usersPage = usersService.getAllUser(pageable, search, status);
            LOGGER.info("End getAllUser in UserWS");
            if (usersPage.getContent().size() > 0) {
                return new RestResponse(true, "getAllUser Successful", usersPage);
            } else {
                return new RestResponse(true, "getAllUser Successful No Data", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail getAllUser in UserWS", null);
    }
}
