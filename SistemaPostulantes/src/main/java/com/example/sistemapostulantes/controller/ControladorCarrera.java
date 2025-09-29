package com.example.sistemapostulantes.controller;

import com.example.sistemapostulantes.model.CarrerasDAO;
import com.example.sistemapostulantes.view.Vistas;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class ControladorCarrera extends ControladorBarraSuperior {

    @FXML private TableView<CarreraVM> tblCarreras;
    @FXML private TableColumn<CarreraVM, String>  colCodigo;
    @FXML private TableColumn<CarreraVM, String>  colNombre;
    @FXML private TableColumn<CarreraVM, String>  colFacultad;
    @FXML private TableColumn<CarreraVM, String>  colTipo;

    @FXML private TextField txtBuscar;
    @FXML private Label lblTotal;

    private final ObservableList<CarreraVM> base = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCodigo()));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colFacultad.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFacultad()));
        colTipo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipo()));

        tblCarreras.setPlaceholder(new Label("No hay carreras para mostrar"));
        cargarCarreras();
    }

    @FXML
    public void cargarCarreras() {
        try {
            List<CarreraDAOView> lista = CarrerasDAO.listarCarrerasConFacultadTipo();
            base.clear();
            for (CarreraDAOView r : lista) {
                base.add(new CarreraVM(r.id, r.codigo, r.nombre, r.facultad, r.tipo));
            }
            tblCarreras.setItems(base);
            actualizarTotal();
        } catch (Exception ex) {
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "No se pudieron cargar las carreras.").showAndWait();
        }
    }

    private void actualizarTotal() {
        int n = (tblCarreras.getItems() == null) ? 0 : tblCarreras.getItems().size();
        if (lblTotal != null) lblTotal.setText(n + (n == 1 ? " resultado" : " resultados"));
    }

    @FXML
    public void onBuscar(KeyEvent e) {
        String q = (txtBuscar.getText() == null) ? "" : txtBuscar.getText().toLowerCase().trim();
        if (q.isEmpty()) {
            tblCarreras.setItems(base);
            actualizarTotal();
            return;
        }
        List<CarreraVM> filtrado = new ArrayList<>();
        for (CarreraVM c : base) {
            if ((c.getNombre()   != null && c.getNombre().toLowerCase().contains(q)) ||
                    (c.getCodigo()   != null && c.getCodigo().toLowerCase().contains(q)) ||
                    (c.getFacultad() != null && c.getFacultad().toLowerCase().contains(q)) ||
                    (c.getTipo()     != null && c.getTipo().toLowerCase().contains(q))) {
                filtrado.add(c);
            }
        }
        tblCarreras.setItems(FXCollections.observableArrayList(filtrado));
        actualizarTotal();
    }

    @FXML
    public void limpiarBusqueda() {
        if (txtBuscar != null) {
            txtBuscar.clear();
            tblCarreras.setItems(base);
            actualizarTotal();
            txtBuscar.requestFocus();
        }
    }

    @FXML
    public void verDetalle() {
        CarreraVM sel = (tblCarreras == null) ? null : tblCarreras.getSelectionModel().getSelectedItem();
        if (sel == null) {
            new Alert(Alert.AlertType.INFORMATION, "Selecciona una carrera.").showAndWait();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Código: ").append(val(sel.getCodigo())).append("\n");
        sb.append("Nombre: ").append(val(sel.getNombre())).append("\n");
        sb.append("Facultad: ").append(val(sel.getFacultad())).append("\n");
        sb.append("Tipo: ").append(val(sel.getTipo())).append("\n");

        Alert a = new Alert(Alert.AlertType.INFORMATION, sb.toString());
        a.setHeaderText("Detalle de Carrera");
        a.showAndWait();
    }

    private String val(String s) { return (s == null || s.isEmpty()) ? "-" : s; }

    @FXML
    public void volver(ActionEvent event) {
        cambiarVista(Vistas.VISTA_HOME.getVista(), event);
    }

    public static class CarreraVM {
        private final int id;              
        private final String codigo;
        private final String nombre;
        private final String facultad;
        private final String tipo;

        public CarreraVM(int id, String codigo, String nombre, String facultad, String tipo) {
            this.id = id; this.codigo = codigo; this.nombre = nombre; this.facultad = facultad; this.tipo = tipo;
        }
        public int getId() { return id; }
        public String getCodigo() { return codigo; }
        public String getNombre() { return nombre; }
        public String getFacultad() { return facultad; }
        public String getTipo() { return tipo; }
    }

    public static class CarreraDAOView {
        public int id; public String codigo; public String nombre; public String facultad; public String tipo;
        public CarreraDAOView(int id, String codigo, String nombre, String facultad, String tipo) {
            this.id = id; this.codigo = codigo; this.nombre = nombre; this.facultad = facultad; this.tipo = tipo;
        }
    }
}
