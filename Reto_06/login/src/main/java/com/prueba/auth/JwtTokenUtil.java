package com.prueba.auth;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prueba.model.Users;
import com.prueba.service.IUsuarioService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {

    @Autowired
    private IUsuarioService usuarioService;

    private String jwtSecret = "\r\n" + 
    		"MIIEowIBAAKCAQEAwRRkppjNSOZkENkh8/JNk7vjVq1J7jRWT89ZrgOYbS7BkKDX\r\n" + 
    		"YYo3ylxi/Z7aQE5SYP1RIuz0Iw8ZuCsPr4Sh4nbkbWIPnthTB0ZWizmQCC2La81H\r\n" + 
    		"U78zMCw0AetdGVU0+P0LAh+9lZAdXiZiTqFviNbOj/pfJqyPz6r9/7/HUDVuxpeO\r\n" + 
    		"KXQO7PMhsQrwCAkj7oxVdXyoKjByyx1vz19A9sZgKrWhthhg409ngy3/RRTtzXqf\r\n" + 
    		"gg6RgUNIeKmhQjC4E+1K8JtG3dBCFpGuZaCc/Y+PO4aj7c2JaQ2BtQvw9ZmlQmIA\r\n" + 
    		"RlrD2wTfkld/gnsah9KPAUGzSK2Am2jiTTVnXwIDAQABAoIBAFYkOg+VxqjKmURn\r\n" + 
    		"C13h8biCsBfAsmZDFWsAEHuxgPTdUmCrUcxjtSZkd4m9sJPWHazF98gEPZvSpd/j\r\n" + 
    		"3lipbOwzrRAcGun8i3aIbB4rbVYos7ZB3JvBhx3r6rwcfOnLeRnJE3s8HAI5TNDv\r\n" + 
    		"gRahsbg0Ve4ofwErJfI50J6kulDO75w2yAMefthdLDy9wqtCTwE/elcmZ3318GB+\r\n" + 
    		"ctJeuemkDoyLTbaDrh98erewpz2WRCxXqTV9mDVzfsL/4Vqky9iCcF4ZhMYkhk22\r\n" + 
    		"26UVfHUAd0Ovfe5KMUzGRpH0gc6FdSrkGfsWV0gp9anm2cwFeTKiVJmv2eo93yEn\r\n" + 
    		"MySolpkCgYEA4IZIzyTSZ2pwRkM8QITjwYN7KsQlcu3kCxnf1h7LIPKWgbr5P6OU\r\n" + 
    		"t842TYFMODCb/CCoX4Oe9YON+hkP+BDlcMvlmArO296gQV3mtsuMWChkRTmQ0fB9\r\n" + 
    		"X7ryqpz9ry3i/RKydU+mPyFD5DbEn363gTv+K0OxmX26bItLn3rCgOMCgYEA3CWe\r\n" + 
    		"HLX33o5x1NHAKCB9c/fObX/60uQwVQocGWyfeDleI8G22PAIL5K3Ns+s0RFcHVDJ\r\n" + 
    		"IXicZ52ceVCCTpzw1KBX9f6ah9cVxIRV0UelSgC8nj4IivDsUcn8YIai1rNzfPCt\r\n" + 
    		"5d6iN2SqnP4vDpp8hTOZR5d0j0W2FbVwsFzLtFUCgYEAgLqdLh7Xf7GYE8Di378R\r\n" + 
    		"clb3HCr/qahZUkAQhQx8vDQ6NMFFvMYGM2hI3CEg2SqNlH4I61JkpjE6CsWp0Tmm\r\n" + 
    		"wwg0Z/ryZT98NF4pNG751WW7L7F0pdmzmFpwXX/LN1Agz6aTqQz0rUdeTI9WJngZ\r\n" + 
    		"sD/8V0PlpLJgW3F190cEE68CgYAj3YkN2mOAgapv5qAsqWZm30dlNYVymDR7lkMP\r\n" + 
    		"rU+psYbxwlx8qVZcEcYBiwH3qaFdMU0jQ9gPVXEpnoEsN4tQyLKr5Afe+56TPpAQ\r\n" + 
    		"oWB/VvFjwm133VpS1NpmC2k6G1BEWZ2rJoM9DQxyuUKHWYnR1Z8yN62Ire3FSaML\r\n" + 
    		"SILzZQKBgFzwPCDsrBbNi2I0zzSuPptinosyEMwp4Fx/V0JpOMcB37k2wEbpN7v5\r\n" + 
    		"LbwCiTtzS6oxWfY2yOA6eICTX2Eiu5a5MP/uShsBCKRR2dGuqxD3K3TmiWielY34\r\n" + 
    		"+E+YwC7OKZiSJ97cePVhMbTt5RrIkXSuQbjWAdZkC5BAUJEWTpvl\r\n";
    
    
    
    
    // Método para validar si el token es válido
    public boolean validateToken(String token) {
        try {
            // Verificar si el token ha expirado
            return !isTokenExpired(token);
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException e) {
            // Firma inválida o token mal formado
            return false;
        } catch (ExpiredJwtException e) {
            // El token ha expirado
            return false;
        }
    }
    
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
    public Date getExpirationDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }
    
    
    public String generateToken(Principal authentication) {
        // Usa authentication.getName() para obtener el email, ya que autenticamos por email
        Users usuario1 = (Users) usuarioService.findByEmailDOs(authentication.getName());  // Cambiar findByUsername por findByEmail
        Users  usuario = usuarioService.findByUsername(usuario1.getUsername());
    
        // Limpiar el jwtSecret para evitar espacios no deseados
        jwtSecret = jwtSecret.trim();
        jwtSecret = jwtSecret.replaceAll("[\\n\\r]", "").trim();
        
        List<String> roles = usuario.getRoles().stream()
        	    .map(role -> role.getRole().getName())
        	    .collect(Collectors.toList());
        
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("nombre", usuario.getLastname());
        additionalInfo.put("apellido", usuario.getFirstname());
        additionalInfo.put("email", usuario.getEmail());
        additionalInfo.put("id_user", usuario.getId_user());
        additionalInfo.put("roles", roles); 
        additionalInfo.put("info_adicional", "Hola que tal!: ".concat(authentication.getName()));
        System.out.println("--------------" + additionalInfo);

        return Jwts.builder()
                .setSubject(usuario.getEmail()) // Usamos el email como subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // Expiración en 1 hora
                .addClaims(additionalInfo)  // Agregamos los datos adicionales al payload
                .signWith(SignatureAlgorithm.HS256, jwtSecret) // Firmar con la clave secreta (simétrica)
                .compact();
    }

    
    
    // Verificar si el token ha expirado
    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());

    }
    // Otros métodos para validar y obtener información del token...
}