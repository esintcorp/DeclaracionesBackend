package com.esintcorp.logic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esintcorp.data.model.Declaration;
import com.esintcorp.data.model.Question;
import com.esintcorp.data.repository.DeclarationRepository;
import com.esintcorp.data.repository.QuestionRepository;

@RestController
public class QuestionController {

    @Autowired
    private DeclarationRepository declarationRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/getIvaQuestions")
    public List<Question> getIvaQuestions(HttpServletRequest request) {
        System.out.println("** IVA QUESTIONS ** " );
        Declaration decl = declarationRepository.findByType("IVA");
        if (decl != null) {
            System.out.println("** DECL: **" + decl);
            return questionRepository.findByDeclaration(decl);
        }
        return null;
    }

    @PostMapping("/getAnexoQuestions")
    public List<Question> getAnexoQuestions(HttpServletRequest request) {
        System.out.println("** IVA QUESTIONS ** " );
        Declaration decl = declarationRepository.findByType("Anexo");
        if (decl != null) {
            return questionRepository.findByDeclaration(decl);
        }
        return null;
    }
}
