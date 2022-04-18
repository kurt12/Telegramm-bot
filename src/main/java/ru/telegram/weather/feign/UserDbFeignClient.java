package ru.telegram.weather.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.telegram.weather.model.dto.UsersDTO;

import java.util.List;

@FeignClient(name="UserDB", url = "http://localhost:8081/users")
public interface UserDbFeignClient {

    @GetMapping("/")
    List<UsersDTO> findAll();

    @GetMapping("/find-by-id{id}")
    UsersDTO findById(@PathVariable Long id);

    @PostMapping("/save")
    String save(@RequestBody UsersDTO usersDTO);

    @PostMapping("/update{id}")
    String update(@RequestBody UsersDTO user, @PathVariable Long id);

    @DeleteMapping("/delete{id}")
    String deleteById(@PathVariable Long id) ;
}
