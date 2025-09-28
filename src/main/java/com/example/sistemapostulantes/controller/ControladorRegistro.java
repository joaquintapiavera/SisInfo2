package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.Estudiante;
import com.example.sistemapostulantes.model.EstudianteCRUD;
import com.example.sistemapostulantes.view.CambiadorVista;
import com.example.sistemapostulantes.view.Vistas;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ControladorRegistro implements CambiadorVista {

    @FXML private TextField nombreField;
    @FXML private TextField ciField;
    @FXML private TextField correoField;
    @FXML private TextField direccionField;
    @FXML private TextField telefonoField;
    @FXML private PasswordField contraseniaField; // en tu FXML es PasswordField

    private static final PseudoClass INVALID = PseudoClass.getPseudoClass("invalid");

    private static final Pattern SOLO_LETRAS = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{3,60}$");
    private static final Pattern SOLO_DIGITOS = Pattern.compile("^\\d+$");
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @FXML
    public void initialize() {

        ciField.setTextFormatter(onlyDigitsMaxLen(12));
        telefonoField.setTextFormatter(onlyDigitsMaxLen(8));

        addClearInvalidOnEdit(nombreField, ciField, correoField, direccionField, telefonoField, contraseniaField);
    }

    private TextFormatter<String> onlyDigitsMaxLen(int max) {
        return new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.length() > max) return null;
            if (!newText.isEmpty() && !SOLO_DIGITOS.matcher(newText).matches()) return null;
            return change;
        });
    }

    private void addClearInvalidOnEdit(TextInputControl... fields) {
        for (TextInputControl f : fields) {
            f.textProperty().addListener((obs, o, n) -> f.pseudoClassStateChanged(INVALID, false));
        }
    }

    @FXML
    public void RegistrarUsuario(ActionEvent event) {
        Map<TextInputControl, String> errores = new LinkedHashMap<>();

        String nombre = safe(nombreField).trim();
        if (nombre.isEmpty() || !SOLO_LETRAS.matcher(nombre).matches()) {
            errores.put(nombreField, "Nombre: 3–60 letras y espacios, sin números.");
        }

        String ci = safe(ciField).trim();
        if (ci.isEmpty() || !SOLO_DIGITOS.matcher(ci).matches() || ci.length() < 6 || ci.length() > 12) {
            errores.put(ciField, "CI: solo dígitos, 6–12 caracteres.");
        }

        String correo = safe(correoField).trim();
        if (correo.isEmpty() || !EMAIL.matcher(correo).matches()) {
            errores.put(correoField, "Correo: formato no válido.");
        }

        String direccion = safe(direccionField).trim();
        if (direccion.isEmpty() || direccion.length() < 5) {
            errores.put(direccionField, "Dirección: mínimo 5 caracteres.");
        }

        String telefono = safe(telefonoField).trim();
        if (telefono.isEmpty() || !SOLO_DIGITOS.matcher(telefono).matches() || telefono.length() < 7 || telefono.length() > 8) {
            errores.put(telefonoField, "Teléfono: 7–8 dígitos.");
        }

        String contrasenia = safe(contraseniaField);
        if (contrasenia.isEmpty() || contrasenia.length() < 6) {
            errores.put(contraseniaField, "Contraseña: mínimo 6 caracteres.");
        }

        if (!errores.isEmpty()) {
            StringBuilder sb = new StringBuilder("Corrige lo siguiente:\n\n");
            errores.forEach((campo, msg) -> {
                campo.pseudoClassStateChanged(INVALID, true);
                sb.append("• ").append(msg).append("\n");
            });
            mostrarAlerta(Alert.AlertType.ERROR, "Datos inválidos", sb.toString());
            return;
        }

        try {
            int idCarrera = 1;

            Estudiante est = new Estudiante(
                    nombre, ci, correo, direccion, telefono, idCarrera, contrasenia
            );

            EstudianteCRUD.crearEstudiante(est);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Registro exitoso", "Tu cuenta fue creada correctamente.");


        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Fallo en el Registro", "No se pudo registrar.");
            e.printStackTrace();
        }
    }

    private String safe(TextInputControl c) {
        return c.getText() == null ? "" : c.getText();
    }

    @FXML
    public void VistaLogin(ActionEvent event) {
        cambiarVista(Vistas.VISTA_LOGIN.getVista(), event);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

