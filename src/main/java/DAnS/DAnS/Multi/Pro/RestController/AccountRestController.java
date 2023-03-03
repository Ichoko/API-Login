package DAnS.DAnS.Multi.Pro.RestController;
import DAnS.DAnS.Multi.Pro.JwtToken;
import DAnS.DAnS.Multi.Pro.dto.RequestTokenDTO;
import DAnS.DAnS.Multi.Pro.dto.ResponseTokenDTO;
import DAnS.DAnS.Multi.Pro.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/authenticate")
public class AccountRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private AccountService accountService;

    @Autowired
    private JwtToken jwtToken;

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody RequestTokenDTO dto){
        try{
            //token ini bukan JWT, ini adalah tokenisasi gaya spring security (sama seperti yang di cookies).
            var token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            //menggunakan spring security melalukan check apakah username dan password user valid.
            authenticationManager.authenticate(token);
        }catch (Exception exception){
            return ResponseEntity.status(403).body("Authentication gagal, username dan password not found.");
        }


        //dapatkan jwt token dari method generate token yang sudah kita buat di class JwtToken
        String jwt = jwtToken.generateToken(
            dto.getUsername(),
            dto.getSecretKey(),
            dto.getSubject());

        //dto untuk diberikan ke requester dalam Http Response bodynya
        ResponseTokenDTO responseTokenDTO = new ResponseTokenDTO(
            dto.getUsername(), jwt
        );

        return ResponseEntity.status(200).body(responseTokenDTO);
    }
}
