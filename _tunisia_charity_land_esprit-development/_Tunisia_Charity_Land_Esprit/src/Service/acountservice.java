/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.account;
import Utils.Datasourc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author leanbois
 */
public class acountservice {

    private static Statement ste;
    Connection con = Datasourc.getInstance().getConnexion();

    public acountservice() {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void ajouteraccount(account p) throws SQLException {
        String query = " insert into account (username, status)"
                + " values (?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, p.getUsername());
        preparedStmt.setString(2, p.getStatus());
        // execute the preparedstatement
        preparedStmt.execute();
    }

    public void updatecon(account ev) {
        ev.setStatus("in");
        String req = "UPDATE `esprit`.`account` SET `status` = ? WHERE `username` = ?;";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setString(1, "in");
            preStatement.setString(2, ev.getUsername());
            preStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void updatedescon(account ev) {
        ev.setStatus("out");
        String req = "UPDATE `esprit`.`account` SET `status` = ? WHERE `username` = ?;";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setString(1, "out");
            preStatement.setString(2, ev.getUsername());
            preStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public ArrayList<account> afficheracount(String user) throws SQLException {
        ArrayList<account> room = new ArrayList<account>();
        String request = "SELECT * FROM account WHERE (status = 'in') AND NOT (username = '" + user + "') ";
        try {
            ResultSet rs = ste.executeQuery(request);
            while (rs.next()) {
                String username = rs.getString(1);
                String status = rs.getString(2);
                account p = new account(username, status);
                room.add(p);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return room;
    }

    public ArrayList<account> afficherallacount(String user) throws SQLException {
        ArrayList<account> room = new ArrayList<account>();
        String request = "SELECT * FROM account where NOT (username = '" + user + "')";
        try {
            ResultSet rs = ste.executeQuery(request);
            while (rs.next()) {
                String username = rs.getString(1);
                String status = rs.getString(2);
                account p = new account(username, status);
                room.add(p);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return room;
    }

    public ArrayList<account> afficheroneacount(String user) throws SQLException {
        ArrayList<account> room = new ArrayList<account>();
        String request = "SELECT * FROM account where (username = '" + user + "')";
        try {
            ResultSet rs = ste.executeQuery(request);
            while (rs.next()) {
                String username = rs.getString(1);
                String status = rs.getString(2);
                account p = new account(username, status);
                room.add(p);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return room;
    }
}
