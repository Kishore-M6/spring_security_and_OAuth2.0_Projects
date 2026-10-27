package com.basicconfsec.controller;

import com.basicconfsec.dto.LoanInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class LoanInfoController {
    @GetMapping(path="/loan-info.htm")
    public String renderLoanInfo(Model model, HttpSession httpSession){
        LoanInfo loanInfo= LoanInfo.of()
                .loanNo(UUID.randomUUID().toString().substring(0, 10))
                .loanHolderName("raja karna")
                .loanType("Personal Loan")
                .tenure(35)
                .emiAmount(36000).build();
        model.addAttribute("loanInfo", loanInfo);

//  this below logic not returning securitycontext object from httpsession  while working with 7.1.0-M2(spring-webmvc)(spring-security-core,spring-security-web,spring-security-taglibs,spring-security-config) version it leads to SecurityContext=null
//  now switching to older version 5.8.10(spring-security-core,spring-security-web,spring-security-taglibs,spring-security-config),5.3.32(spring-webmvc)
//
//
//

//        httpSession.getAttributeNames().asIterator().forEachRemaining(System.out::println);
//        SecurityContext securityContext= (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
//        Authentication authentication =securityContext.getAuthentication();
//        System.out.println(authentication.getCredentials());


        SecurityContext securityContext= SecurityContextHolder.getContext();
        Authentication authentication= securityContext.getAuthentication();
        if(authentication instanceof UsernamePasswordAuthenticationToken){
            UserDetails userDetails =(UserDetails) ((UsernamePasswordAuthenticationToken)authentication).getPrincipal();
            System.out.println(userDetails.getUsername());
        }
        return "loan-info";

    }

    @GetMapping(path = "/loan-info2.htm")
    public String renderLoanInfoUsingSpringSecurity7Version(Model model,Authentication authentication){
        LoanInfo loanInfo= LoanInfo.of()
                .loanNo(UUID.randomUUID().toString().substring(0, 10))
                .loanHolderName("lalitha manoj")
                .loanType("Agricultural Loan")
                .tenure(20)
                .emiAmount(450000).build();
        model.addAttribute("loanInfo", loanInfo);

        if(authentication != null){
            UserDetails userDetails=(UserDetails)authentication.getPrincipal();
            System.out.println(userDetails.getUsername());
            System.out.println(authentication.getAuthorities());
        }else{
            System.out.println("No authentication found for this request");
        }
        return  "loan-info";
    }
}
