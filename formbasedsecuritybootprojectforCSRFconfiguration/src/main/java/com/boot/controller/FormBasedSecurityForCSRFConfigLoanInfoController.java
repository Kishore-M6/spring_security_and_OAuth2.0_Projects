package com.boot.controller;



import com.boot.dto.LoanInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.UUID;

@Controller
public class FormBasedSecurityForCSRFConfigLoanInfoController {
    @GetMapping(path = "/loan-info")
    public String renderLoanInfo(Model model, HttpSession httpSession) {
        LoanInfo loanInfo = LoanInfo.of()
                .loanNo(UUID.randomUUID().toString().substring(0, 10))
                .loanHolderName("jack richerd")
                .loanType("Health Insurance LoanI")
                .tenure(13)
                .emiAmount(679000).build();
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

        System.out.println("JSESSIONID: " + httpSession.getId());
        return "loan-info";

    }
    @GetMapping("/login")
    public String renderLoginPage() {
        return "login"; // Resolves to /WEB-INF/jsp/login.jsp
    }

    @GetMapping("/home")
    public String renderHomePage() {
        return "home"; // Resolves to /WEB-INF/jsp/home.jsp
    }


}
