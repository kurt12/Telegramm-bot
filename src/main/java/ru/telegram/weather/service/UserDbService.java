package ru.telegram.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.telegram.weather.feign.UserDbFeignClient;
import ru.telegram.weather.model.dto.UsersDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDbService {
    private final UserDbFeignClient userDbFeignClient;


   public List<UsersDTO> findAll(){
       return userDbFeignClient.findAll();

    }


    public UsersDTO findById( Long id) {
       return userDbFeignClient.findById(id);
    }


    public String save( UsersDTO usersDTO){
       return userDbFeignClient.save(usersDTO);
    }


    public String update(UsersDTO usersDTO,Long id){
       return userDbFeignClient.update(usersDTO, id);

    }


    public String deleteById(Long id) {
       return userDbFeignClient.deleteById(id);

    }

}
