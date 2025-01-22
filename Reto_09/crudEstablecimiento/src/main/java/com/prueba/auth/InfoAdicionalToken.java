package com.prueba.auth;

import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.stereotype.Component;
import com.prueba.model.Users;
import com.prueba.service.IUsuarioService;

@Component
public class InfoAdicionalToken implements OAuth2TokenCustomizer<JwtEncodingContext> {

    private final IUsuarioService usuarioService;

    public InfoAdicionalToken(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void customize(JwtEncodingContext context) {
        String username = context.getPrincipal().getName();
        Users usuario = usuarioService.findByUsername(username);

        context.getClaims().claim("info_adicional", "Hola que tal!: " + username);
        context.getClaims().claim("nombre", usuario.getLastname());
        context.getClaims().claim("apellido", usuario.getFirstname());
        context.getClaims().claim("email", usuario.getEmail());
        context.getClaims().claim("id_user", usuario.getId_user());
    }
}
