package persistentie;

import domein.Kaart;
import domein.Spel;
import domein.Speler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author flors
 */
public class SpelerMapper {

    private static final String INSERT_SPELER = "INSERT INTO ID222177_g17.Speler (naam, geslacht, niveau, ras, spel)"
            + "VALUES (?, ?, ?, ?, ?)";

    public void voegToeSpeler(Speler speler, String naam, int teller) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {
            query.setString(1, speler.getNaam());
            query.setString(2, speler.getGeslacht());
            query.setInt(3, speler.getNiveau());
            query.setString(4, speler.getRas());
            query.setString(5, naam);
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g17.KaartenInHand (kaartenInHandID, spelerInHand)"
                        + "VALUES (?, ?)")) {
            query.setInt(1, teller);
            query.setString(2, speler.getNaam());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g17.KaartenOpTafel (kaartenOpTafelID, spelerOpTafel)"
                        + "VALUES (?, ?)")) {
            query.setInt(1, teller);
            query.setString(2, speler.getNaam());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        for (Kaart kaart : speler.getKaartenInHand()) {
            switch (kaart.getClass().getSimpleName()) {
                case "Monster":
                    slaMonsterInHandOp(kaart, speler, teller);
                    break;
                case "Race":
                    slaRaceInHandOp(kaart, speler, teller);
                    break;
                case "Curse":
                    slaCurseInHandOp(kaart, speler, teller);
                    break;
                case "DConsumable":
                    slaDConsumableInHandOp(kaart, speler, teller);
                    break;
                case "Equipment":
                    slaEquipmentInHandOp(kaart, speler, teller);
                    break;
                case "TConsumable":
                    slaTConsumableInHandOp(kaart, speler, teller);
                    break;
            }
        }

        for (Kaart kaart : speler.getKaartenOpTafel()) {
            switch (kaart.getClass().getSimpleName()) {
                case "Equipment":
                    slaEquipmentOpTafelOp(kaart, speler, teller);
                    break;
                case "Race":
                    slaRaceOpTafelOp(kaart, speler, teller);
                    break;
            }
        }
    }

    public Spel geefSpel(String naam) {
        Spel spel = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g17.Spel WHERE naam = ?")) {
            query.setString(1, naam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    int aantal = rs.getInt("aantal");
                    spel = new Spel(naam, aantal);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spel;
    }

    public void spelOpslaan(Spel spel) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g17.Spel (naam, aantal)"
                        + "VALUES (?, ?)")) {
            query.setString(1, spel.getNaam());
            query.setInt(2, spel.getAantal());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        for (Kaart kaart : spel.getKerkerKaarten()) {
            switch (kaart.getClass().getSimpleName()) {
                case "Monster":
                    slaMonsterSpelOp(kaart, spel);
                    break;
                case "Race":
                    slaRaceSpelOp(kaart, spel);
                    break;
                case "Curse":
                    slaCurseSpelOp(kaart, spel);
                    break;
                case "DConsumable":
                    slaDConsumableSpelOp(kaart, spel);
                    break;
            }

        }

        for (Kaart kaart : spel.getSchatKaarten()) {
            switch (kaart.getClass().getSimpleName()) {
                case "Equipment":
                    slaEquipmentSpelOp(kaart, spel);
                    break;
                case "TConsumable":
                    slaTConsumableSpelOp(kaart, spel);
                    break;
            }
        }
    }

    private void slaMonsterSpelOp(Kaart kaart, Spel spel) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.Monster SET kaartSpelMonster = ? WHERE mCardID = ?")) {
            query.setString(1, spel.getNaam());
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaRaceSpelOp(Kaart kaart, Spel spel) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.Race SET kaartSpelRace = ? WHERE rCardID = ?")) {
            query.setString(1, spel.getNaam());
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaCurseSpelOp(Kaart kaart, Spel spel) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.Curse SET kaartSpelCurse = ? WHERE cCardID = ?")) {
            query.setString(1, spel.getNaam());
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaDConsumableSpelOp(Kaart kaart, Spel spel) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.DConsumables SET kaartSpelDConsumable = ? WHERE dConCardID = ?")) {
            query.setString(1, spel.getNaam());
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaEquipmentSpelOp(Kaart kaart, Spel spel) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.Equipment SET kaartSpelEquipment = ? WHERE eCardID = ?")) {
            query.setString(1, spel.getNaam());
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaTConsumableSpelOp(Kaart kaart, Spel spel) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.TConsumables SET kaartSpelTConsumable = ? WHERE tCardID = ?")) {
            query.setString(1, spel.getNaam());
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaEquipmentOpTafelOp(Kaart kaart, Speler speler, int teller) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.Equipment SET kaartenOpTafelEquipment = ? WHERE eCardID = ?")) {
            query.setInt(1, teller);
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaRaceOpTafelOp(Kaart kaart, Speler speler, int teller) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.Race SET kaartenOpTafelRace = ? WHERE rCardID = ?")) {
            query.setInt(1, teller);
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaMonsterInHandOp(Kaart kaart, Speler speler, int teller) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.Monster SET kaartenInHandMonster = ? WHERE mCardID = ?")) {
            query.setInt(1, teller);
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaRaceInHandOp(Kaart kaart, Speler speler, int teller) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.Race SET kaartenInHandRace = ? WHERE rCardID = ?")) {
            query.setInt(1, teller);
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaCurseInHandOp(Kaart kaart, Speler speler, int teller) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.Curse SET kaartenInHandCurse = ? WHERE cCardID = ?")) {
            query.setInt(1, teller);
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaDConsumableInHandOp(Kaart kaart, Speler speler, int teller) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.DConsumables SET kaartenInHandDConsumable = ? WHERE dConCardID = ?")) {
            query.setInt(1, teller);
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaEquipmentInHandOp(Kaart kaart, Speler speler, int teller) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.Equipment SET kaartenInHandEquipment = ? WHERE eCardID = ?")) {
            query.setInt(1, teller);
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void slaTConsumableInHandOp(Kaart kaart, Speler speler, int teller) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g17.TConsumables SET kaartenInHandTConsumable = ? WHERE tCardID = ?")) {
            query.setInt(1, teller);
            query.setInt(2, kaart.getCardID());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
