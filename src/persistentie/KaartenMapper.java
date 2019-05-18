package persistentie;

import domein.Curse;
import domein.DConsumable;
import domein.Equipment;
import domein.KerkerKaart;
import domein.Monster;
import domein.Race;
import domein.SchatKaart;
import domein.TConsumable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flors
 */
public class KaartenMapper {
    
    public List<KerkerKaart> geefAlleKerkerKaarten() {
        List<KerkerKaart> kerkerkaarten = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g17.Monster");
                ResultSet rs = query.executeQuery()) { 
            
        
          while (rs.next()) {
                int CardID = rs.getInt("mCardID");
                int badStuff = rs.getInt("badStuff");
                String bonusAgainstRace = rs.getString("bonusAgainstRace");
                int levelsGained = rs.getInt("levelsGained");
                int treasures = rs.getInt("treasures");
                int bonusAgainstRun = rs.getInt("bonusAgainstRun");
                String naam = rs.getString("naam");
                int level = rs.getInt("level");

                kerkerkaarten.add(new Monster(CardID, badStuff, bonusAgainstRace, levelsGained, treasures, bonusAgainstRun, naam, level));
            }
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
          try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g17.Curse");
                ResultSet rs = query.executeQuery()) { 
            
        
          while (rs.next()) {
                int CardID = rs.getInt("cCardID");
                int levelMin = rs.getInt("levelMin");
                String naam = rs.getString("naam");
                kerkerkaarten.add(new Curse(CardID, levelMin, naam));
            }
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
          
           try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g17.DConsumables");
                ResultSet rs = query.executeQuery()) { 
            
        
          while (rs.next()) {
                String naam = rs.getString("naam");
                int monsterBonus = rs.getInt("monsterBonus");
                int CardID = rs.getInt("dConCardID");
                
                kerkerkaarten.add(new DConsumable(CardID, monsterBonus, naam));
            }
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
       
             try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g17.Race");
                ResultSet rs = query.executeQuery()) { 
            
        
          while (rs.next()) {
                int CardID = rs.getInt("rCardID");
                String bonusOmschrijving = rs.getString("bonusOmschrijving");
                String naam = rs.getString("naam");
                
                kerkerkaarten.add(new Race(CardID, bonusOmschrijving, naam));
            }
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        
             
     return kerkerkaarten;
       
     }
     
      public List<SchatKaart> geefAlleSchatKaarten() {
         List<SchatKaart>  schatkaarten = new ArrayList<>();
         
            try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g17.Equipment");
                ResultSet rs = query.executeQuery()) { 
            
        
          while (rs.next()) {
                int CardID = rs.getInt("eCardID");
                String equipSlot = rs.getString("equipSlot");
                String specialBonus = rs.getString("specialBonus");
                int strengthBonus = rs.getInt("strengthBonus");
                String usableBy = rs.getString("usableBy");
                String naam = rs.getString("naam");
                int verkoopPrijs = rs.getInt("verkoopPrijs");

                schatkaarten.add(new Equipment(CardID, equipSlot, specialBonus, strengthBonus, usableBy, naam, verkoopPrijs));
            }
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
       
           try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g17.TConsumables");
                ResultSet rs = query.executeQuery()) { 
            
        
          while (rs.next()) {
                int CardID = rs.getInt("tCardID");
                int strengthBonus = rs.getInt("strengthBonus");
                int isLevelUp = rs.getInt("isLevelUp");
                int verkoopPrijs = rs.getInt("verkoopPrijs");
                String naam = rs.getString("naam");
                

                schatkaarten.add(new TConsumable(CardID, strengthBonus, isLevelUp, verkoopPrijs,naam));
            }
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
           
        
        
         return schatkaarten;
      }
}
