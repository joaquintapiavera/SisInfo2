package com.registro;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class VentanaPrincipal extends JFrame{
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private ElementoBD elementoBD;
    private JButton btnNuevo;
    public VentanaPrincipal(){
        elementoBD = new ElementoBD();
        inicializarComponentes();
        cargarDatos();
    }

    private void inicializarComponentes(){
        setTitle("Gestión de Elementos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        String[] columnas = {"Descripción", "Nombre"};
        modeloTabla = new DefaultTableModel(columnas, 0){
            @Override
            public boolean isCellEditable(int fila, int columna){
                return false; 
            }
        };
        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.setRowHeight(25);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Elementos"));
        btnNuevo = new JButton("Registrar Elemento");
        btnNuevo.setFont(new Font("Arial", Font.BOLD, 14));
        btnNuevo.setPreferredSize(new Dimension(200, 40));
        btnNuevo.addActionListener((ActionEvent e) -> registrarElemento());
        JPanel panelBoton = new JPanel(new FlowLayout());
        panelBoton.add(btnNuevo);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
    }

    private void cargarDatos(){
        modeloTabla.setRowCount(0);
        List<Elemento> lista = elementoBD.obtenerTodos();
        for (Elemento elem : lista){
            Object[] fila = {elem.getDescripcion(), elem.getNombre()};
            modeloTabla.addRow(fila);
        }
        System.out.println("Elementos cargados: " + lista.size());
    }

    private void registrarElemento(){
        String nombre = JOptionPane.showInputDialog(
                this,
                "Ingrese el nuevo elemento:",
                "Registrar",
                JOptionPane.QUESTION_MESSAGE
        );
        if (nombre == null || nombre.trim().isEmpty()){
            return;
        }

        nombre = nombre.trim();
        if (elementoBD.existeNombre(nombre)){
            JOptionPane.showMessageDialog(
                    this,
                    "El elemento '" + nombre + "' ya está registrado.",
                    "Duplicado",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        boolean guardado = elementoBD.agregarElemento(nombre);
        if (guardado){
            JOptionPane.showMessageDialog(
                    this,
                    "Se registró el elemento '" + nombre + "' correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );
            cargarDatos();
        }else{
            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrió un error al intentar guardar el elemento.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
