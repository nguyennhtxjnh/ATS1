package com.ats.service.impl;

import com.ats.dto.JobDTO3;
import com.ats.dto.UsersDTO;
import com.ats.dto.UsersDTO2;
import com.ats.entity.Job;
import com.ats.entity.Users;
import com.ats.repository.UsersRepository;
import com.ats.service.EmailService;
import com.ats.service.JobService;
import com.ats.service.RoleService;
import com.ats.service.UsersService;
import com.ats.token.TokenAuthenticationService;
import com.ats.util.EncrytedPasswordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JobService jobService;
    @Autowired
    TokenAuthenticationService tokenService;


    ModelMapper modelMapper;


    private static final Logger LOGGER = LogManager.getLogger(UsersServiceImpl.class);

    private EncrytedPasswordUtils passwordUtil;

    @Override
    public UsersDTO login(String email, String password) {
        LOGGER.info("Begin login in User Service with email - password: {}", email + " - " + password);
        UsersDTO usersDTO;
        UsersDTO reTurnUsersDTO = new UsersDTO();
        passwordUtil = new EncrytedPasswordUtils();
        modelMapper = new ModelMapper();
        if (email != null) {
            usersDTO = findUserByEmail(email);
            if (usersDTO != null) {
                if (passwordUtil.compare(password, usersDTO.getPassword())) {
                    if (usersDTO.getStatus().matches("active")) {
                        Date lastLoginDate = new Date();
                        usersRepository.editAccountLastLogin(lastLoginDate, usersDTO.getEmail(), usersDTO.getAccessToken());
                        reTurnUsersDTO = new UsersDTO(usersDTO.getId(), usersDTO.getFullname(),
                                usersDTO.getEmail(), usersDTO.getRoleId(), usersDTO.getAccessToken());
                        return reTurnUsersDTO;
                    }
                    if (usersDTO.getStatus().matches("new")) {
                        reTurnUsersDTO.setEmail("User not activated yet !!");
                    }
                    if (usersDTO.getStatus().matches("ban")) {
                        reTurnUsersDTO.setEmail("User is banned !! ");
                    }
                }
                if (!passwordUtil.compare(password, usersDTO.getPassword())) {
                    reTurnUsersDTO.setEmail("Wrong Password !!");
                }
            }
            if (findUserByEmail(email) == null) {
                reTurnUsersDTO.setEmail("User not Existed !!");
            }

            LOGGER.info("End login in User Service with result: {}", usersDTO);
        }
        return reTurnUsersDTO;
    }

    @Override
    public int registration(UsersDTO dto) {
        LOGGER.info("Begin registration in Account Service with Account DTO Email : {}", dto.getEmail());
        Users newUsers = null;
        modelMapper = new ModelMapper();
        EncrytedPasswordUtils passwordUtil = new EncrytedPasswordUtils();
        String newPassword = passwordUtil.encrytePassword(dto.getPassword());
        dto.setPassword(newPassword);
        Users users = modelMapper.map(dto, Users.class);
        UsersDTO existedUsers;
        existedUsers = findUserByEmail(dto.getEmail());

        if (existedUsers == null) {
            if (users != null) {
                try {
                    LOGGER.info("End registration in User Repository with result: {}", users.toString());
                    newUsers = usersRepository.save(users);
                    LOGGER.info("End registration in User Repository with result: {}", newUsers.toString());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (newUsers.getRoleId() == 5) {
                emailService.sendActiveUserEmail(newUsers.getAccessToken(), newUsers.getEmail(), true);
            }
            if (newUsers.getRoleId() == 1) {
                emailService.sendActiveUserEmail(newUsers.getAccessToken(), newUsers.getEmail(), false);
            }

        } else {
            return -1;
        }
        LOGGER.info("End registration in Account Service with result: {}", newUsers.getId());
        return newUsers.getId();
    }

    @Override
    public boolean checkPassword(String password, int id) {
        boolean same = true;
        passwordUtil = new EncrytedPasswordUtils();
        if (passwordUtil.compare(password, usersRepository.getOne(id).getPassword())) {
            same = false;
        }
        return same;
    }

    @Override
    public UsersDTO findUserByEmail(String email) {
        LOGGER.info("Begin findAccountByEmail in Account Service with email {}", email);
        modelMapper = new ModelMapper();
        UsersDTO usersDTO = null;
        Users users;
        if (email != null) {
            users = usersRepository.findAccountByEmail(email);
            if (users != null) {
                usersDTO = modelMapper.map(users, UsersDTO.class);
            }
        }
        LOGGER.info("End findAccountByEmail in Account Service with result: {}", usersDTO);
        return usersDTO;
    }

    @Override
    public UsersDTO findUserByUserId(int id) {
        LOGGER.info("Begin findAccountByEmail in Account Service with email {}", id);
        modelMapper = new ModelMapper();
        UsersDTO usersDTO = null;
        Users users;

        users = usersRepository.findOne(id);
        if (users != null) {
            usersDTO = modelMapper.map(users, UsersDTO.class);
            usersDTO.setPassword("");
            usersDTO.setAccessToken("");
        }

        LOGGER.info("End findAccountByEmail in Account Service with result: {}", usersDTO);
        return usersDTO;
    }

    @Override
    public UsersDTO2 findUserByToken(String token) {
        LOGGER.info("Begin findAccountByToken in User Service with token {}", token);
        UsersDTO2 usersDTO = null;
        modelMapper = new ModelMapper();
        Users users;

        if (token != null) {
            LOGGER.info("Begin findAccountByToken in User Repository with token: {}",
                    token);
            users = usersRepository.findAccountByToken(token);
            LOGGER.info("End findAccountByToken in User Repository with token: {}",
                    token);
            if (users != null) {
                usersDTO = modelMapper.map(users, UsersDTO2.class);
                if (users.getRoleId() != 1) {
                    List<JobDTO3> listofDTO;
                    java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO3>>() {
                    }.getType();
                    listofDTO = modelMapper.map(users.getJobsById(), targetListType);
                    usersDTO.setListOfJob(listofDTO);
                }
            } else {
                return null;
            }
        }
        LOGGER.info("End findAccountByToken in User Service with token: {}",
                usersDTO.getAccessToken());
        return usersDTO;
    }

    @Override
    public int updateUser(UsersDTO dto) {
        LOGGER.info("Begin updateUser in User Service with User DTO Email : {}", dto.getEmail());
        Users updateUsers = null;
        java.util.Date date = new java.util.Date();
        Users users = usersRepository.findOne(dto.getId());


        if (users != null) {
            users.setFullName(dto.getFullname());
            users.setAddress(dto.getAddress());
            users.setDescription(dto.getDescription());
            users.setTelephoneNumber(dto.getTelephoneNumber());
            users.setCityId(dto.getCityid());
            users.setJobLevelId(dto.getJobLevelID());
            users.setLastModify(new Timestamp(date.getTime()));
            try {
                LOGGER.info("End updateUser in User Repository with result: {}", users.toString());
                updateUsers = usersRepository.save(users);
                LOGGER.info("End updateUser in User Repository with result: {}", updateUsers.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("End updateUser in User Service with result: {}", updateUsers.getId());
        return updateUsers.getId();
    }

    @Override
    public int changeRole(int id, int role) {
        LOGGER.info("Begin changeRole in User Service with User id - newStatus : {}", id + role);
        int success;
        success = usersRepository.changeRole(id, role);
        LOGGER.info("End changeRole in User Service with result: {}", success);
        return success;
    }

    @Override
    public Page<Users> getAllUser(Pageable pageable, String search, String status) {
        LOGGER.info("Begin getAllUser in User Service ");

        Page<Users> usersPage = null;
        try {
            usersPage = usersRepository.findAll(pageable, search, status);

        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("End getAllUser in User Service ");
        return usersPage;
    }

    @Override
    public int changeStatus(int id, String newStatus) {
        LOGGER.info("Begin changeStatus in User Service with User id - newStatus : {}", id + newStatus);
        int success;
        success = usersRepository.changeStatus(id, newStatus);
        Users users = usersRepository.findOne(id);
        if(users.getRoleId() == 2){

        }
        if (newStatus.equals("ban") || newStatus.equals("active ban")) {

            List<Job> jobList = users.getJobsById();
            for (int i = 0; i < jobList.size(); i++) {
                if (jobList.get(i).getStatus().equals("new") ||
                        jobList.get(i).getStatus().equals("approved")) {
                    jobService.changeStatus(jobList.get(i).getId(), jobList.get(i).getStatus() + " ban");
                }
            }
        }
        if (newStatus.equals("active")) {

            List<Job> jobList = users.getJobsById();
            for (int i = 0; i < jobList.size(); i++) {
                if (jobList.get(i).getStatus().equals("new ban")) {
                    jobService.changeStatus(jobList.get(i).getId(), "new");
                }
                if (jobList.get(i).getStatus().equals("approved ban")) {
                    jobService.changeStatus(jobList.get(i).getId(), "approved");
                }
            }
        }
        emailService.sendEmailStatus(users.getEmail(), users.getFullName(), users.getFullName(),
                newStatus, "user");
        LOGGER.info("End changeStatus in User Service with result: {}", success);
        return success;
    }

    @Override
    public int confirmUser(int id, String newStatus) {
        LOGGER.info("Begin confirmUser in User Service  {}");
        int success = -1;
        try {
            Users user = usersRepository.findOne(id);
            System.out.println(user);
            if (user != null) {
                Calendar c1 = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();

                // Định nghĩa 2 mốc thời gian ban đầu

                c1.setTime(user.getCreatedDate());
                c2.setTime(new Date());

                // Công thức tính số ngày giữa 2 mốc thời gian:
                //1 ngày = 86.400.000
                long noDay = (c2.getTime().getTime() - c1.getTime().getTime());
                System.out.println(noDay);
                if (noDay < 24 * 3600 * 1000) {
                    if (user.getStatus().equals("new")) {
                        user.setStatus(newStatus);
                        usersRepository.save(user);
                        success = 1;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("End confirmUser in User Service with result: {}", success);
        return success;
    }

    @Override
    public int changePassword(int id, String newPassword, String oldPassword) {
        LOGGER.info("Begin changePassword in Account Service with Account id : {}", id);
        boolean existedPassword = checkPassword(oldPassword, id);
        int success = 0;
        if (existedPassword) {
            return success;
        } else {
            ModelMapper modelMapper = new ModelMapper();
            Users users = usersRepository.findOne(id);
            System.out.println("Account id : " + users.getId());
            UsersDTO usersDTO = modelMapper.map(users, UsersDTO.class);
            if (usersDTO.getStatus().matches("new")) {
                EncrytedPasswordUtils passwordUtil = new EncrytedPasswordUtils();
                newPassword = passwordUtil.encrytePassword(newPassword);
                success = usersRepository.changePassword(id, newPassword);
                LOGGER.info("End changePassword in Account Service with result: {}", success);
            }
        }
        return success;
    }

    @Override
    public int forgotPassword(String token, String password) {
        LOGGER.info("Begin forgotPassword in User Service");
        int success = 0;
        UsersDTO2 user = findUserByToken(token);
        if (user != null) {
            if (user.getStatus().matches("active")) {
                EncrytedPasswordUtils passwordUtil = new EncrytedPasswordUtils();
                password = passwordUtil.encrytePassword(password);
                success = usersRepository.forgotPassword(token, password);
                LOGGER.info("End changePassword in User Service ");
            }
        }
        return success;
    }

}
