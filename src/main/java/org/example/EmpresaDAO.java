package org.example;

import java.sql.*;

public class EmpresaDAO {

    public void agregarEmpresa(String nombre, Date fechaIncorporacion, double facturacionAnual, int numeroVendedores, int paisSedeId) {
        String sql = "INSERT INTO Empresas (nombre, fecha_incorporacion, facturacion_anual, numero_vendedores, pais_sede_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setDate(2, fechaIncorporacion);
            pstmt.setDouble(3, facturacionAnual);
            pstmt.setInt(4, numeroVendedores);
            pstmt.setInt(5, paisSedeId);

            pstmt.executeUpdate();
            System.out.println("Empresa agregada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consultarEmpresaPorId(int empresaId) {
        String sql = "SELECT * FROM Empresas WHERE id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empresaId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Fecha Incorporación: " + rs.getDate("fecha_incorporacion"));
                System.out.println("Facturación Anual: " + rs.getDouble("facturacion_anual"));
                System.out.println("Número de Vendedores: " + rs.getInt("numero_vendedores"));
                System.out.println("ID País Sede: " + rs.getInt("pais_sede_id"));
            } else {
                System.out.println("Empresa no encontrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarEmpresa(int empresaId, String nombre, Date fechaIncorporacion, double facturacionAnual, int numeroVendedores, int paisSedeId) {
        String sql = "UPDATE Empresas SET nombre = ?, fecha_incorporacion = ?, facturacion_anual = ?, numero_vendedores = ?, pais_sede_id = ? WHERE id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setDate(2, fechaIncorporacion);
            pstmt.setDouble(3, facturacionAnual);
            pstmt.setInt(4, numeroVendedores);
            pstmt.setInt(5, paisSedeId);
            pstmt.setInt(6, empresaId);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Empresa actualizada exitosamente.");
            } else {
                System.out.println("No se encontró la empresa para actualizar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEmpresa(int empresaId) {
        String sql = "DELETE FROM Empresas WHERE id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empresaId);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Empresa eliminada exitosamente.");
            } else {
                System.out.println("No se encontró la empresa para eliminar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarEmpresas() {
        String sql = "SELECT * FROM Empresas";
        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Fecha Incorporación: " + rs.getDate("fecha_incorporacion"));
                System.out.println("Facturación Anual: " + rs.getDouble("facturacion_anual"));
                System.out.println("Número de Vendedores: " + rs.getInt("numero_vendedores"));
                System.out.println("ID País Sede: " + rs.getInt("pais_sede_id"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

