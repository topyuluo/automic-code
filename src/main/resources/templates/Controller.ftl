package ${configInfo.packageController};

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import ${configInfo.packageModel}.${table.upperCaseName};
import ${configInfo.packageService}.${table.upperCaseName}Service;


/**
* ${table.comment}控制类.
*/
@RestController
@RequestMapping(value = "/${table.lowerCaseName}")
public class ${table.upperCaseName}Controller {

    @Autowired
    public ${table.upperCaseName}Service ${table.lowerCaseName}Service;


    @PostMapping(path = "/create")
    public void create(@RequestBody ${table.upperCaseName} io) {
        ${table.lowerCaseName}Service.insert(io);
    }

    @GetMapping("/{id}")
    public Object show(@PathVariable("id") ${table.idType} id) {
        ${table.upperCaseName} ${table.lowerCaseName} = ${table.lowerCaseName}Service.find(id);
        return ${table.lowerCaseName};
    }


    @PostMapping(path = "/{id}/update")
    public void update(@PathVariable("id") ${table.idType} id, @RequestBody ${table.upperCaseName} io) {
        io.setId(id);
        ${table.lowerCaseName}Service.update(io);

    }


    @PostMapping("/{id}/delete")
    public void delete(@PathVariable("id") ${table.idType} id) {
        ${table.lowerCaseName}Service.delete(id);
    }

    @GetMapping("/findAll")
    public Object findAll(){
        return ${table.lowerCaseName}Service.findAll();
    }

}

