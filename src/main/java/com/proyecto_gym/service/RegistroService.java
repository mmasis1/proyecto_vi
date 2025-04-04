package com.proyecto_gym.service;

import com.proyecto_gym.domain.User;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;

@Service
public class RegistroService {

    @Autowired
    private CorreoService correoService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;  //creado en semana 4...
    @Autowired
    private FirebaseStorageService firebaseStorageService;

    public Model activar(Model model, String username, String clave) {
        User user
                = userService.getUsuarioPorUsernameYPassword(username,
                clave);
        if (user != null) {
            model.addAttribute("usuario", user);
        } else {
            model.addAttribute(
                    "titulo",
                    messageSource.getMessage(
                            "registro.activar",
                            null, Locale.getDefault()));
            model.addAttribute(
                    "mensaje",
                    messageSource.getMessage(
                            "registro.activar.error",
                            null, Locale.getDefault()));
        }
        return model;
    }

    public void activar(User usuario) {
        var codigo = new BCryptPasswordEncoder();
        usuario.setContrasena(codigo.encode(usuario.getContrasena()));
        userService.save(usuario);
    }

    public Model crearUsuario(Model model, User usuario) throws MessagingException {
        String mensaje;
        if (!userService.existeUsuarioPorUsernameOCorreo(usuario.getNombre(), usuario.getCorreo())) {
            String clave = demeClave();
            usuario.setContrasena(clave);
            usuario.setEstado(false);
            userService.save(usuario);
            enviaCorreoActivar(usuario, clave);
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.activacion.ok",
                            null,
                            Locale.getDefault()),
                    usuario.getCorreo());
        } else {
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.usuario.o.correo",
                            null,
                            Locale.getDefault()),
                    usuario.getNombre(), usuario.getCorreo());
        }
        model.addAttribute(
                "titulo",
                messageSource.getMessage(
                        "registro.activar",
                        null,
                        Locale.getDefault()));
        model.addAttribute(
                "mensaje",
                mensaje);
        return model;
    }

    public Model recordarUsuario(Model model, User usuario)
            throws MessagingException {
        String mensaje;
        User usuario2 = userService.getUsuarioPorUsernameOCorreo(
                usuario.getNombre(),
                usuario.getCorreo());
        if (usuario2 != null) {
            String clave = demeClave();
            usuario2.setContrasena(clave);
            usuario2.setEstado(false);
            userService.save(usuario2);
            enviaCorreoRecordar(usuario2, clave);
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.recordar.ok",
                            null,
                            Locale.getDefault()),
                    usuario.getCorreo());
        } else {
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.usuario.o.correo",
                            null,
                            Locale.getDefault()),
                    usuario.getNombre(), usuario.getCorreo());
        }
        model.addAttribute(
                "titulo",
                messageSource.getMessage(
                        "registro.activar",
                        null,
                        Locale.getDefault()));
        model.addAttribute(
                "mensaje",
                mensaje);
        return model;
    }

    private String demeClave() {
        String tira = "ABCDEFGHIJKLMNOPQRSTUXYZabcdefghijklmnopqrstuvwxyz0123456789_*+-";
        String clave = "";
        for (int i = 0; i < 40; i++) {
            clave += tira.charAt((int) (Math.random() * tira.length()));
        }
        return clave;
    }

    //Ojo cómo le lee una informacion del application.properties
    @Value("${servidor.http}")
    private String servidor;

    private void enviaCorreoActivar(User usuario, String clave) throws MessagingException {
        String mensaje = String.format(
                "Hello %s,\n\nThanks for register your account\n\n",
                usuario.getNombre(), servidor, clave);
        String asunto = "Account Activation";
        correoService.enviarCorreoHtml(usuario.getCorreo(), asunto, mensaje);
    }

    private void enviaCorreoRecordar(User usuario, String clave) throws MessagingException {
        String mensaje = messageSource.getMessage(""
                        + "registro.correo.recordar",
                null,
                Locale.getDefault());
        mensaje = String.format(
                mensaje, usuario.getNombre(),
                servidor, clave);
        String asunto = messageSource.getMessage(
                "registro.mensaje.recordar",
                null, Locale.getDefault());
        correoService.enviarCorreoHtml(
                usuario.getCorreo(),
                asunto, mensaje);
    }
}
