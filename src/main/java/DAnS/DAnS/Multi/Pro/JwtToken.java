package DAnS.DAnS.Multi.Pro;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtToken {

    //Ibaratnya password application 3rd party
    private final String SECRET_KEY = "ini-merupakan-kode-rahasia-dalam-menggunakan-json-web-token";

    //Ibaratnya username application 3rd party


    //Mendapatkan isi payload/claims dari token
    private Claims getClaims(String token){
        //Meng-convert secret jadi JWTParser
        JwtParser parser = Jwts.parser().setSigningKey(SECRET_KEY);

        //mem-verified String token, sekaligus meng-ekstrak signature dan claims
        Jws<Claims> signatureAndClaims = parser.parseClaimsJws(token);

        //Ambil claims/payloadnya saja
        Claims claims = signatureAndClaims.getBody();

        return claims;
    }

    public String getUsername(String token){
        try{
            Claims claims = getClaims(token);
            return claims.get("username", String.class);
        }catch (Exception exception){
            return null;
        }
    }

    //Memvalidasi apakah token ini benar atau tidak
    public Boolean validateToken(String token, UserDetails userDetails){

        Claims claims = getClaims(token);
        //Mendapatkan username
        String username = getUsername(token);


        //Jika username dan audience matched, return true
        return (username.equals(userDetails.getUsername())) ;
    }

    //Method yang digunakan untuk membuat token dari hasil yang diterima oleh request 3rd party application dan user
    public String generateToken(String username, String secretKey, String subject){
        JwtBuilder builder = Jwts.builder();
        builder = builder.setSubject(subject)
            .claim("username", username)
            .setIssuer("http://localhost:8070")
            .signWith(SignatureAlgorithm.HS256, secretKey);
        return builder.compact();
    }
}
