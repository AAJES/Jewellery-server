package com.ajes.controller;

import com.ajes.model.IdForm;
import com.ajes.service.IdFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewellery")
@CrossOrigin("http://localhost:4200")
public class IdFormController {

    @Autowired
    private IdFormService idFormService;

    @PostMapping("/idForm")
    public IdForm addIdForm(@RequestBody() IdForm idForm){
        return idFormService.addIdForm(idForm);
    }

    @GetMapping("/idForm/")
    public List<IdForm> getAll(){
        return idFormService.getAllIdForm();
    }

    @GetMapping("/idForm/{idFormId}")
    public IdForm getById(@PathVariable() Integer idFormId){
        return idFormService.getById(idFormId);
    }

    @PutMapping("/idForm/{idFormId}")
    public IdForm updateIdForm(@PathVariable() Integer idFormId,@RequestBody() IdForm idForm){
        idForm.setIdFormId(idFormId);
        return idFormService.updateIdForm(idForm);
    }
}
