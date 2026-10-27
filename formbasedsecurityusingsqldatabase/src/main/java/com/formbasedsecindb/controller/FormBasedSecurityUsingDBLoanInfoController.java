package com.formbasedsecindb.controller;


import com.formbasedsecindb.dto.LoanInfo;
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
public class FormBasedSecurityUsingDBLoanInfoController {
    @GetMapping(path = "/loan-info.htm")
    public String renderLoanInfo(Model model) {
        LoanInfo loanInfo = LoanInfo.of()
                .loanNo(UUID.randomUUID().toString().substring(0, 10))
                .loanHolderName("raja karna")
                .loanType("Personal Loan")
                .tenure(35)
                .emiAmount(36000).build();
        model.addAttribute("loanInfo", loanInfo);


        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            Authentication authentication = securityContext.getAuthentication();
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                UserDetails userDetails = (UserDetails) ((UsernamePasswordAuthenticationToken) authentication).getPrincipal();
                System.out.println(userDetails.getUsername());
            }
        }else{
            System.out.println("Security is not implemented yet");
        }
        return "loan-info";

    }


}
