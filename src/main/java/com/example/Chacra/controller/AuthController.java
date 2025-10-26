package com.example.Chacra.controller;

import com.example.Chacra.entity.Pago;
import com.example.Chacra.entity.Rol;
import com.example.Chacra.entity.Usuario;
import com.example.Chacra.service.PagoService;
import com.example.Chacra.service.ProductService;
import com.example.Chacra.service.RolService;
import com.example.Chacra.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final RolService rolService;
    private final PagoService pagoService;
    private final ProductService productService;



    @GetMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
        }
        if (logout != null) {
            model.addAttribute("message", "Sesión cerrada exitosamente");
        }
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Usuario usuario,
                               @RequestParam(value = "confirmPassword") String confirmPassword,
                               RedirectAttributes redirectAttributes) {
        try {
            // Validar que las contraseñas coincidan
            if (!usuario.getPassword().equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden");
                return "redirect:/register";
            }

            // Validar que el email no exista
            if (usuarioService.existsByEmail(usuario.getEmail())) {
                redirectAttributes.addFlashAttribute("error", "El email ya está registrado");
                return "redirect:/register";
            }

            // Asignar rol por defecto (USER)
            Rol rolUser = rolService.findByNombre("ROLE_USER");
            if (rolUser == null) {
                // Si no existe, crear el rol
                rolUser = Rol.builder().nombre("ROLE_USER").build();
                rolUser = rolService.save(rolUser);
            }
            usuario.setRol(rolUser);

            // Guardar usuario (el password se encripta en el service)
            usuarioService.save(usuario);

            redirectAttributes.addFlashAttribute("success", "Usuario registrado exitosamente. Por favor inicia sesión.");
            return "redirect:/login";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar usuario: " + e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Usuario usuario = usuarioService.getCurrentUser();
        model.addAttribute("usuario", usuario);
        return "dashboard";
    }

    @GetMapping("dashboardLog")
    public String dashboardLog(Model model) {
        Usuario usuario = usuarioService.getCurrentUser();
        model.addAttribute("usuario", usuario);
        return "dashboardLog";
    }

    @GetMapping("/contactanos")
    public String contactanos(Model model) {

        return "contactanos";
    }
    @GetMapping("/contactanosLog")
    public String contactanosLog(Model model) {

        return "contactanosLog";
    }

    @GetMapping("/sobreNosotros")
    public String sobreNosotros(Model model) {
        Usuario usuario = usuarioService.getCurrentUser();
        model.addAttribute("usuario", usuario);
        return "sobreNosotros";

    }
    @GetMapping("/sobreNosotrosLog")
    public String sobreNosotrosLog(Model model) {
        Usuario usuario = usuarioService.getCurrentUser();
        model.addAttribute("usuario", usuario);
        return "sobreNosotrosLog";

    }








    @GetMapping("/pagos")
    public String showPagoForm(Model model) {
        model.addAttribute("pago", new Pago());
        return "pagos";
    }
    @PostMapping("/pagos")
    public String processPagoForm(@ModelAttribute Pago pago) {
        pagoService.guardarPago(pago);
        return "redirect:/pagos?success=true";
    }


    @GetMapping("/productos")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "productos";
    }

    @GetMapping("/productosLog")
    public String productosLog(Model model) {
        model.addAttribute("products", productService.findAll());
        return "productosLog";
    }


}