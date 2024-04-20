package gestionDeProjet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gestionDeProjet.db.Connect;
import gestionDeProjet.model.Avancement;

public class AvancementDao {
private Connection con;

public AvancementDao() {
	this.con=Connect.getInstance().getConnection();
}


public int add(Avancement avc) {
    int generatedKey = -1;

    try {
        String query = "INSERT INTO avancement (tacheId, dateAvancement, TempsPasse_T, ResteAfaire_R, avancement_A) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

        pstmt.setInt(1, avc.getTache().getId());
        pstmt.setDate(2, new java.sql.Date(avc.getDateAvanc().getTime()));
        pstmt.setString(3, avc.getTempPasse_T());
        pstmt.setString(4, avc.getResteAfaire_R());
        pstmt.setString(5, avc.getAvancement_A());

        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected == 1) {
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
        }

        pstmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return generatedKey; 
}
public boolean update(Avancement avc) {
    boolean success = false;

    try {
        String query = "UPDATE avancement SET tacheId=?, dateAvancement=?, TempsPasse_T=?, ResteAfaire_R=?, avancement_A=? WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(query);

        pstmt.setInt(1, avc.getTache().getId());
        pstmt.setDate(2, new java.sql.Date(avc.getDateAvanc().getTime()));
        pstmt.setString(3, avc.getTempPasse_T());
        pstmt.setString(4, avc.getResteAfaire_R());
        pstmt.setString(5, avc.getAvancement_A());
        pstmt.setInt(6, avc.getId());

        int rowsAffected = pstmt.executeUpdate();

        success = rowsAffected == 1;

        pstmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return success;
}
public Avancement getTotalDataForTache(int tacheId) {
    Avancement avancement = null;

    try {
        String query = "SELECT id ,SUM(TempsPasse_T) AS TempsPasse_Total, ResteAfaire_R, SUM(avancement_A) AS avancement_total FROM avancement WHERE tacheId=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, tacheId);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
        	avancement = new Avancement();
        	avancement.setId(rs.getInt("id"));
        	avancement.setTempPasse_T(rs.getString("TempsPasse_Total"));;
        	avancement.setResteAfaire_R(rs.getString("ResteAfaire_R"));
        	avancement.setAvancement_A(rs.getString("avancement_total"));
        }

        rs.close();
        pstmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return avancement;
}


}
