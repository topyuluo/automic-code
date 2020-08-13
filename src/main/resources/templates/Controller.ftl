package ${packageController}

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import ${packageModel}.${upperCaseName};
import ${packageService}.${upperCaseName}Service;


/**
* ${comment}控制类.
*/
@RestController
@RequestMapping(value = "/${lowerCaseName}")
public class ${upperCaseName}Controller {

    @Autowired
    public ${upperCaseName}Service ${lowerCaseName}Service;


    @PostMapping(path = "/create")
    public void create(@RequestBody ${upperCaseName} io) {
        ${lowerCaseName}Service.insert(io);
    }

    @GetMapping("/{id}")
    public Object show(@PathVariable("id") ${idType} id) {
        ${upperCaseName} ${lowerCaseName} = ${lowerCaseName}Service.find(id);
        return ${lowerCaseName};
    }


    @PostMapping(path = "/{id}/update")
    public void update(@PathVariable("id") ${idType} id, @RequestBody ${upperCaseName} io) {
        io.setId(id);
        ${lowerCaseName}Service.update(io);

    }


    @PostMapping("/{id}/delete")
    public void delete(@PathVariable("id") ${idType} id) {
        ${lowerCaseName}Service.delete(id);
    }

    @GetMapping("/findAll")
    public Object findAll(){
        return ${lowerCaseName}Service.findAll();
    }

}

