/*package com.example.sistemapostulantes.model;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DocumentosCRUD {

    
    public static void guardarDocumentos(int idUsuario, File ciFile, File diplomaFile, File fotoFile, File certNacFile) throws Exception {

        String selectSql = "SELECT id_documento FROM documentos WHERE id_usuario = ?";
        String insertSql = "INSERT INTO documentos (id_usuario, ci_pdf, diploma_pdf, fotografia_pdf, certificado_nacimiento_pdf) VALUES (?, ?, ?, ?, ?)";
        String updateSql = "UPDATE documentos SET ci_pdf = ?, diploma_pdf = ?, fotografia_pdf = ?, certificado_nacimiento_pdf = ? WHERE id_usuario = ?";

        try (Connection con = ConexionBaseDatos.getConexion()) {

            // Verificar si ya hay documentos para este usuario
            boolean existe = false;
            try (PreparedStatement psSelect = con.prepareStatement(selectSql)) {
                psSelect.setInt(1, idUsuario);
                try (ResultSet rs = psSelect.executeQuery()) {
                    if (rs.next()) {
                        existe = true;
                    }
                }
            }

            if (existe) {
                try (PreparedStatement psUpdate = con.prepareStatement(updateSql)) {
                    setArchivoOrNull(psUpdate, 1, ciFile);
                    setArchivoOrNull(psUpdate, 2, diplomaFile);
                    setArchivoOrNull(psUpdate, 3, fotoFile);
                    setArchivoOrNull(psUpdate, 4, certNacFile);
                    psUpdate.setInt(5, idUsuario);

                    psUpdate.executeUpdate();
                }
            } else {
                try (PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                    psInsert.setInt(1, idUsuario);
                    setArchivoOrNull(psInsert, 2, ciFile);
                    setArchivoOrNull(psInsert, 3, diplomaFile);
                    setArchivoOrNull(psInsert, 4, fotoFile);
                    setArchivoOrNull(psInsert, 5, certNacFile);

                    psInsert.executeUpdate();
                }
            }

            System.out.println("Documentos guardados correctamente para usuario ID: " + idUsuario);
        }
    }

    
    private static void setArchivoOrNull(PreparedStatement ps, int paramIndex, File file) throws Exception {
        if (file != null) {
            try (FileInputStream fis = new FileInputStream(file)) {
                ps.setBinaryStream(paramIndex, fis, (int) file.length());
            }
        } else {
            ps.setNull(paramIndex, Types.BLOB);
        }
    }
}*/
package com.example.sistemapostulantes.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class DocumentosCRUD {

    public static void guardarDocumentos(int idUsuario, File ciFile, File diplomaFile, File fotoFile, File certNacFile) throws Exception {

        String selectSql = "SELECT id_documento FROM documentos WHERE id_usuario = ?";
        String insertSql = "INSERT INTO documentos (id_usuario, ci_pdf, diploma_pdf, fotografia_pdf, certificado_nacimiento_pdf) VALUES (?, ?, ?, ?, ?)";
        String updateSql = "UPDATE documentos SET ci_pdf = ?, diploma_pdf = ?, fotografia_pdf = ?, certificado_nacimiento_pdf = ? WHERE id_usuario = ?";

        try (Connection con = ConexionBaseDatos.getConexion()) {

            // Verificar si ya hay documentos para este usuario
            boolean existe = false;
            try (PreparedStatement psSelect = con.prepareStatement(selectSql)) {
                psSelect.setInt(1, idUsuario);
                try (ResultSet rs = psSelect.executeQuery()) {
                    if (rs.next()) {
                        existe = true;
                    }
                }
            }

            if (existe) {
                try (PreparedStatement psUpdate = con.prepareStatement(updateSql)) {
                    setArchivoOrNull(psUpdate, 1, ciFile);
                    setArchivoOrNull(psUpdate, 2, diplomaFile);
                    setArchivoOrNull(psUpdate, 3, fotoFile);
                    setArchivoOrNull(psUpdate, 4, certNacFile);
                    psUpdate.setInt(5, idUsuario);

                    psUpdate.executeUpdate();
                }
            } else {
                try (PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                    psInsert.setInt(1, idUsuario);
                    setArchivoOrNull(psInsert, 2, ciFile);
                    setArchivoOrNull(psInsert, 3, diplomaFile);
                    setArchivoOrNull(psInsert, 4, fotoFile);
                    setArchivoOrNull(psInsert, 5, certNacFile);

                    psInsert.executeUpdate();
                }
            }

            System.out.println("✅ Documentos guardados correctamente para usuario ID: " + idUsuario);
        }
    }

    private static void setArchivoOrNull(PreparedStatement ps, int paramIndex, File file) throws Exception {
        if (file != null && file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            // usar long para evitar problemas con archivos grandes
            ps.setBinaryStream(paramIndex, fis, file.length());
        } else {
            ps.setNull(paramIndex, Types.BLOB);
        }
    }
}
